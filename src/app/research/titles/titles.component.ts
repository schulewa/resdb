import { Component, OnInit, ViewChild } from '@angular/core';
import { ColDef, GridApi, GridOptions } from 'ag-grid-community';
import { HttpErrorResponse } from '@angular/common/http';
import { DataStatus } from '../../refdata/data-status';
import { CoreOperationsMessages } from '../../core/core-operations-messages';
import { TitlesService } from './titles.service';
import { DateFormatters } from '../../core/formatters/date-formatters';
import { DataAction } from '../../refdata/data-action';
import { Title } from '../../model/entity/title';
import { BaseNameComponent } from '../../refdata/base-name.component';
import { PopupMenuComponent } from '../../core/popup-menu/popup-menu.component';
import { IAuditedDataType } from '../../model/entity/interfaces/audited-data-type';
import { DataPopupMenuAction } from '../../core/data-popup-menu-action';


@Component({
  selector: 'app-titles',
  templateUrl: './titles.component.html',
  styleUrls: ['./titles.component.scss']
})
export class TitlesComponent implements OnInit {

  @ViewChild(PopupMenuComponent) menu: PopupMenuComponent;

  private columnDefs: ColDef[];
  private gridApi: GridApi;
  private gridOptions: GridOptions;
  private haveEmptyRow: boolean;
  private rowData: Title[];
  private rowSelection: Title;
  private selectedRow: Title;

  protected httpError: HttpErrorResponse;
  protected operationMessage: string;

  protected liveStatuses: Array<DataStatus> = [];

  constructor(private titlesService: TitlesService) {
    console.log('AddressTypeComponent constructor');
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  ngOnInit() {
    this.initRowData();
    this.haveEmptyRow = false;
    this.liveStatuses.push(DataStatus.New);
    this.liveStatuses.push(DataStatus.Amend);
  }

  onGridReady(params) {
    this.gridApi = params.api;
  }

  initRowData() {
    this.rowData = [];
    //
    this.operationMessage = CoreOperationsMessages.FINDALL_TITLE;
    this.titlesService.findAll().subscribe(
      data => {
        for (const datum in data) {
          if (datum.hasOwnProperty(datum)) {
            const title: Title = data[datum];
            title.selected = false;
            title.isDataChanged = false;
          }
        }
        this.rowData = data;
      },
      err => {
        this.httpError = err;
      });
  }

  createColumnDefs(): any[] {
    return [
      {headerName: '', field: 'selected', width: 30, headerCheckboxSelection: true, checkboxSelection: true, editable: true},
      {headerName: 'ID', field: 'id', width: 60, editable: false, filter: true},
      {headerName: 'Title', field: 'title', width: 100, editable: true, filter: true},
      {headerName: 'Description', field: 'description', width: 300, editable: true, filter: true},
      {headerName: 'Type', field: 'titleType', width: 100, editable: true, filter: true},
      {headerName: 'Applies To', field: 'appliesTo', width: 120, editable: true, filter: true},
      {headerName: 'Status', field: 'status', width: 100, editable: false, filter: true},
      {headerName: 'Created by', field: 'createdBy', width: 110, editable: false, filter: true},
      {headerName: 'Last updated by', field: 'updatedBy', width: 150, editable: false, filter: true},
      {
        headerName: 'Last updated',
        field: 'lastUpdated',
        width: 160,
        editable: false,
        filter: true,
        valueFormatter: DateFormatters.dateWithTimeAsString
      },
      {headerName: 'Action', field: 'action', width: 100, editable: false, filter: true}
    ];
  }

  createGridOptions(cellValueChanged): GridOptions {
    return <GridOptions>{
      animateRows: true,
      enableCellChangeFlash: true,
      enableColResize: true,
      enableFilter: true,
      enableSorting: true,
      onCellValueChanged: cellValueChanged,
      rowDeselection: false,
      rowSelection: 'single',
      statusBar: {
        statusPanels: [
          {statusPanel: 'agTotalRowCountComponent', align: 'left'},
          {statusPanel: 'agSelectedRowCountComponent'}
        ]
      },
      unSortIcon: true
    };
  }

  enrichAuditData(auditData: IAuditedDataType) {
    if (!auditData.createdBy) {
      auditData.createdBy = localStorage.getItem('currentUser');
    }
    if (!auditData.updatedBy) {
      auditData.updatedBy = localStorage.getItem('currentUser');
    }
    if (!auditData.lastUpdated) {
      auditData.lastUpdated = new Date();
    }
  }

  createNewRowDatum(): any {
    const datum = new Title();
    datum.status = DataStatus.New;
    datum.action = DataAction.Add;
    return datum;
  }

  onContextPopupMenu(event) {
    event.preventDefault(); // Suppress the browser's context menu
    console.log('BaseNameComponent: onContextPopupMenu - event=' + event);
    this.menu.open(event);
  }

  getRowData(): any[] {
    return this.rowData;
  }

  onRowSelected(event) {
    //
    // need to de-select all rows first then select the newly selected one.
    // this is to ensure we don't leave the previously selected row marked as still selected.
    //
    this.deselectAllRows();
    event.data.selected = event.node.selected;
    this.selectedRow = event.node.data;
    this.menu.open(event);
  }

  onSelectionChanged(event) {
    const rowCount = event.api.getSelectedNodes().length;
    console.log('BaseNameComponent.onSelectionChanged: ' + rowCount + ' rows selected');
  }

  deselectAllRows() {
    this.rowData.forEach(entry => entry.selected = false);
  }

  onCellValueChanged(event) {
    // handle updated 'name' value
    console.log('onCellValueChanged - entry: event=' + event + ' haveEmptyRow=' + this.haveEmptyRow);
    if (event.data && event.data.id && event.data.id > 0) {
      event.data.action = DataAction.Update;
      this.gridApi.refreshCells();
    }
  }

  isSelected(element, index, array) {
    return element.selected;
  }

  getColumnDefs() {
    return this.columnDefs;
  }

  revertChanges() {
    console.log('TitlesComponent.revertChanges: TODO');
  }

  saveChanges() {
    console.log('TitlesComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const title of toBeSaved) {
        if (DataAction.Add === title.action) {
          this.operationMessage = CoreOperationsMessages.ADD_TITLE;
          this.enrichAuditData(title);
          this.titlesService.add(title).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              this.httpError = err;
            });
        } else if (DataAction.Update === title.action) {
          this.operationMessage = CoreOperationsMessages.UPDATE_TITLE;
          title.status = DataStatus.Amend;
          this.titlesService.update(title).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              this.httpError = err;
            }
          );
        } else if (DataAction.Delete === title.action) {
          this.operationMessage = CoreOperationsMessages.DELETE_TALE_TYPE;
          title.status = DataStatus.Delete;
          this.titlesService.delete(title).subscribe(
            data => {
              this.httpError = null;
              const remainingRows: Title[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
              this.gridApi.setRowData(remainingRows);
              this.gridApi.refreshCells();
            },
            err => {
              this.httpError = err;
            }
          );
        }
      }
    }

  }

  actionSelected(popupMenuAction: any) {
    console.log('TitlesComponent.actionSelected: popupMenuAction=' + popupMenuAction);
    switch (popupMenuAction) {
      case DataPopupMenuAction.AddEmptyRow:
        this.addEmptyRow();
        break;
      case DataPopupMenuAction.MarkForDeletion:
        this.markRowForDeletion();
        break;
      case DataPopupMenuAction.RemoveRow:
        this.removeEmptyRow();
        break;
      case DataPopupMenuAction.UnmarkForDeletion:
        this.unmarkForDeletion();
        break;
      default:
        console.error('TitlesComponent.actionSelected: popup menu action not recognized');
    }
  }

  addEmptyRow() {
    if (this.haveEmptyRow) {
      console.log('addEmptyRow: haveEmptyRow is true so return without adding another');
      return;
    }

    this.rowData.push(this.createNewRowDatum());
    this.gridApi.setRowData(this.rowData);

    //
    // set focus to Name column on new row; should be in edit mode and in that Name cell
    //
    const rowIndex = this.rowData.length  - 1;
    this.haveEmptyRow = true;
    this.gridApi.setFocusedCell(rowIndex, 'name');
  }

  protected  markRowForDeletion() {
    console.log('TitlesComponent.markRowForDeletion');
    let nameOfAction;
    this.rowData.filter(this.isSelected).forEach(function (value) {
      value.action = DataAction.Delete;
      nameOfAction = DataAction[value.action];
      console.log('TitlesComponent.delete: mark for deletion - ' + value.title + '     name of action=' + nameOfAction);
    } );
    this.gridApi.setRowData(this.rowData);
    this.gridApi.refreshCells();
  }

  protected removeEmptyRow() {
    //
    // TODO need to check that this removes the row from the grid AND from this.rowData; LOOKED LIKE ROW ADDED TWICE...
    //
    console.log('removeEmptyRow: Removing empty row');
    if (this.isUnsavedRow(this.selectedRow)) {
      const otherRows: IAuditedDataType[] = [];
      this.rowData.filter(entry => entry.selected === false).forEach(entry => otherRows.push(entry));
      console.log('removeEmptyRow: otherRows size = ' + otherRows.length);
      this.gridApi.setRowData(otherRows);
      this.gridApi.refreshCells();
    }
  }

  protected  unmarkForDeletion() {
    console.log('TODO: Unmarking row for deletion');
  }

  isUnsavedRow(row: IAuditedDataType) {
    return row && row.id === 0;
  }

  updateGrid(saved: Title) {
    if (saved && saved.title) {
      const newRowData: Title[] = [];
      this.rowData.forEach( (entry) => {
        if (saved.title === entry.title && saved.appliesTo === entry.appliesTo) {
          newRowData.push(saved);
        } else {
          newRowData.push(entry);
        }
      });
      this.gridApi.setRowData(newRowData);
      this.gridApi.refreshView();
    }

    console.log('Data updated');
  }

}

import {OnInit, TemplateRef, ViewChild} from '@angular/core';
import {ColDef, GridApi, GridOptions} from 'ag-grid-community';
import {IAuditedNameDataType} from '../model/entity/interfaces/audited-name-data-type';
import {PopupMenuComponent} from '../core/popup-menu/popup-menu.component';
import {DataStatus} from './data-status';
import {DataAction} from './data-action';
import {RefdataPopupMenuAction} from './refdata-popup-menu-action';
import {HttpErrorResponse} from '@angular/common/http';
import {DateFormatters} from '../core/formatters/date-formatters';


export abstract class BaseNameComponent<T extends IAuditedNameDataType> implements OnInit {

  @ViewChild(PopupMenuComponent) menu: PopupMenuComponent;

  @ViewChild('nameCell') nameCell: TemplateRef<any>;

  protected columnDefs: ColDef[];
  protected gridApi: GridApi;
  protected gridOptions: GridOptions;
  protected haveEmptyRow: boolean;
  protected rowData: IAuditedNameDataType[];
  protected rowSelection: IAuditedNameDataType;
  protected selectedRow: IAuditedNameDataType;

  protected httpError: HttpErrorResponse;
  protected operationMessage: string;

  protected liveStatuses: Array<DataStatus> = [];

  protected constructor(private child: new () => T) { }

  ngOnInit() {
    this.initRowData();
    this.haveEmptyRow = false;
    console.log('BaseNameComponent: user language=' + navigator.language);
    this.liveStatuses.push(DataStatus.New);
    this.liveStatuses.push(DataStatus.Amend);
  }

  onGridReady(params) {
    this.gridApi = params.api;
  }

  protected abstract initRowData();

  protected createColumnDefs(): any[] {
    return [
      { headerName: '', field: 'selected', width: 30, headerCheckboxSelection: true, checkboxSelection: true, editable: true },
      { headerName: 'ID', field: 'id', width: 60, editable: false, filter: true },
      { headerName: 'Name', field: 'name', width: 200, editable: true, filter: true },
      { headerName: 'Status', field: 'status', width: 100, editable: false, filter: true },
      { headerName: 'Created by', field: 'createdBy', width: 110, editable: false, filter: true },
      { headerName: 'Last updated by', field: 'updatedBy', width: 150, editable: false, filter: true },
      {
        headerName: 'Last updated',
        field: 'lastUpdated',
        width: 160,
        editable: false,
        filter: true,
        valueFormatter: DateFormatters.dateWithTimeAsString
      },
      { headerName: 'Action', field: 'action', width: 100, editable: false, filter: true }
    ];
  }

  protected createGridOptions(cellValueChanged): GridOptions {
    return <GridOptions> {
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
          { statusPanel: 'agTotalRowCountComponent', align: 'left' },
          { statusPanel: 'agSelectedRowCountComponent' }
        ]
      },
      unSortIcon: true
    };
  }

  protected enrichAuditData(auditData: IAuditedNameDataType) {
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

  protected createNewRowDatum(): any {
    const datum = new this.child();
    datum.status = DataStatus.New;
    datum.action = DataAction.Add;
    return datum;
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
    console.log('BaseNameComponent.markRowForDeletion');
    let nameOfAction;
    this.rowData.filter(this.isSelected).forEach(function (value) {
      value.action = DataAction.Delete;
      nameOfAction = DataAction[value.action];
      console.log('BaseNameComponent.delete: mark for deletion - ' + value.name + '     name of action=' + nameOfAction);
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
      const otherRows: IAuditedNameDataType[] = [];
      this.rowData.filter(entry => entry.selected === false).forEach(entry => otherRows.push(entry));
      console.log('removeEmptyRow: otherRows size = ' + otherRows.length);
      this.gridApi.setRowData(otherRows);
      this.gridApi.refreshCells();
    }
  }

  protected isSelected(element, index, array) {
    return element.selected;
  }

  protected getColumnDefs() {
    return this.columnDefs;
  }

  protected getRowData(): any[] {
    return this.rowData;
  }

  isUnsavedRow(row: IAuditedNameDataType) {
    return row && row.id === 0;
  }

  protected actionSelected(popupMenuAction: any) {
    console.log('BaseNameComponent.actionSelected: popupMenuAction=' + popupMenuAction);
    switch (popupMenuAction) {
      case RefdataPopupMenuAction.AddEmptyRow:
        this.addEmptyRow();
        break;
      case RefdataPopupMenuAction.MarkForDeletion:
        this.markRowForDeletion();
        break;
      case RefdataPopupMenuAction.RemoveRow:
        this.removeEmptyRow();
        break;
      case RefdataPopupMenuAction.UnmarkForDeletion:
        this.unmarkForDeletion();
        break;
      default:
        console.error('BaseNameComponent.actionSelected: popup menu action not recognized');
    }
  }

  protected  unmarkForDeletion() {
    console.log('TODO: Unmarking row for deletion');
  }

  protected   updateGrid(saved: IAuditedNameDataType) {
    if (saved && saved.name) {
      const newRowData: IAuditedNameDataType[] = [];
      this.rowData.forEach( (entry) => {
        if (saved.name === entry.name) {
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

  protected onCellValueChanged(event ) {
    // handle updated 'name' value
    console.log('onCellValueChanged - entry: event=' + event + ' haveEmptyRow=' + this.haveEmptyRow);
    if (event.data && event.data.id && event.data.id > 0) {
      event.data.action = DataAction.Update;
      this.gridApi.refreshCells();
    }

    const nameColDef = this.gridApi.getColumnDef('name');
    if (nameColDef === event.colDef) {
      console.log('onCellValueChanged: event.colDef == name column - set haveEmptyRow to false');
      this.haveEmptyRow = false;
    }

    console.log('onCellValueChanged - exit: haveEmptyRow=' + this.haveEmptyRow);
  }

  protected onRowSelected(event) {
    //
    // need to de-select all rows first then select the newly selected one.
    // this is to ensure we don't leave the previously selected row marked as still selected.
    //
    this.deselectAllRows();
    event.data.selected = event.node.selected;
    this.selectedRow = event.node.data;
    this.menu.open(event);
  }

  protected onSelectionChanged(event) {
    const rowCount = event.api.getSelectedNodes().length;
    console.log('BaseNameComponent.onSelectionChanged: ' + rowCount + ' rows selected');
  }

  protected deselectAllRows() {
    this.rowData.forEach(entry => entry.selected = false);
  }

  onContextPopupMenu(event) {
    event.preventDefault(); // Suppress the browser's context menu
    console.log('BaseNameComponent: onContextPopupMenu - event=' + event);
    this.menu.open(event);
  }

}

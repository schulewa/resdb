import {Component, OnInit, ViewChild} from '@angular/core';
import {PersonService} from './person.service';
import {Person} from '../../model/entity/person';
import {PopupMenuComponent} from '../../core/popup-menu/popup-menu.component';
import {ColDef, GridApi, GridOptions} from 'ag-grid-community';
import {DataStatus} from '../../core/model/data-status';
import {DataAction} from '../../core/model/data-action';
import {Router} from '@angular/router';
import {CoreOperationsMessages} from '../../core/core-operations-messages';
import {HttpErrorResponse} from '@angular/common/http';
import {ResearchdataPopupMenuAction} from '../resdata-popup-menu-action';


@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.scss']
})
export class PersonComponent implements OnInit {

  @ViewChild(PopupMenuComponent) menu: PopupMenuComponent;

  protected columnDefs: ColDef[];
  protected gridApi: GridApi;
  protected gridOptions: GridOptions;
  protected haveEmptyRow: boolean;
  protected rowData: Person[];
  protected rowSelection: Person;
  protected showGrid: boolean;
  protected selectedRow: Person;
  protected operationMessage: string;
  protected httpError: HttpErrorResponse;

  protected liveStatuses: Array<DataStatus> = [];

  constructor(private personService: PersonService, private router: Router) {
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  ngOnInit() {
    this.initRowData();
    this.liveStatuses.push(DataStatus.New);
    this.liveStatuses.push(DataStatus.Amend);
    this.haveEmptyRow = false;
    this.showGrid = true;
  }

  initRowData() {
    this.rowData = [];
    //
    this.personService.findAll().subscribe((resp) => {
      for (const datum in resp) {
        if (datum.hasOwnProperty(datum)) {
          const person: Person = resp[datum];
          person.selected = false;
          person.isDataChanged = false;
        }
      }
      this.rowData = resp;
    });
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

  protected createColumnDefs(): any[] {
    return [
      { headerName: '', field: 'selected', width: 30, headerCheckboxSelection: true, checkboxSelection: true, editable: true },
      { headerName: 'ID', field: 'id', width: 60, editable: false, filter: true },
      { headerName: 'First name', field: 'firstName', width: 200, editable: false, filter: true },
      { headerName: 'Middle name(s)', field: 'middleName', width: 200, editable: false, filter: true },
      { headerName: 'Family name', field: 'familyName', width: 200, editable: false, filter: true },
      { headerName: 'Status', field: 'status', valueGetter: this.statusValueGetter, width: 100, editable: false, filter: true },
      { headerName: 'Created by', field: 'createdBy', width: 110, editable: false, filter: true },
      { headerName: 'Last updated by', field: 'updatedBy', width: 150, editable: false, filter: true },
      { headerName: 'Last updated', field: 'lastUpdated', width: 220, editable: false, filter: true },
      { headerName: 'Action', field: 'action', valueGetter: this.actionValueGetter, width: 100, editable: false, filter: true }
    ];
  }

  protected actionValueGetter(params) {
    console.log('actionValueGetter: params.data.action=' + params.data.action +
                ' DataAction[params.data.action]=' + DataAction[params.data.action]);
    return params.data.action ? DataAction[params.data.action] : '';
  }

  protected statusValueGetter(params) {
    return params.data.status ? DataStatus[params.data.status] : DataStatus.New;
  }

  protected onCellValueChanged(event ) {
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

  mapStatus(value: any): DataStatus {
    if (value) {
      if ('NEW' === value) {
        return DataStatus.New;
      } else if ('AMEND' === value) {
        return DataStatus.Amend;
      } else {
        console.error('Unrecognized DataStatus returned from database: ' + value);
        return DataStatus.New;
      }
    } else {
      return DataStatus.New;
    }
  }

  addPerson() {
    console.log('TODO: redirect to /person but with null Person as Input');
    this.showGrid = false;
    this.router.navigate(['addperson']);
  }

  editPerson() {
    console.log('TODO: redirect to /person but with the Person as Input');
    this.showGrid = false;
  }

  //
  // TODO move revertChanges and saveChanges to person-detail component
  //

  revertChanges() {
    console.log('PersonComponent.revertChanges: TODO');
  }

  saveChanges() {
    console.log('PersonComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const raceType of toBeSaved) {
        if (DataAction.Add === raceType.action) {
          this.operationMessage = CoreOperationsMessages.ADD_TALE_TYPE;
          this.enrichAuditData(raceType);
          this.personService.add(raceType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              this.httpError = err;
            });
        } else if (DataAction.Update === raceType.action) {
          this.operationMessage = CoreOperationsMessages.UPDATE_TALE_TYPE;
          raceType.status = DataStatus.Amend;
          this.personService.update(raceType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              this.httpError = err;
            }
          );
        } else if (DataAction.Delete === raceType.action) {
          this.operationMessage = CoreOperationsMessages.DELETE_TALE_TYPE;
          raceType.status = DataStatus.Delete;
          this.personService.delete(raceType).subscribe(
            data => {
              this.httpError = null;
              const remainingRows: Person[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
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

  protected   updateGrid(saved: Person) {
    if (saved && saved.firstName && saved.familyName) {
      const newRowData: Person[] = [];
      this.rowData.forEach( (entry) => {
        // TODO what do we need to check here to determine if we have new entry?
        if (saved.firstName === entry.firstName && saved.familyName === entry.familyName) {
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

  protected enrichAuditData(auditData: Person) {
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

  // onContextPopupMenu(event) {
  //   event.preventDefault(); // Suppress the browser's context menu
  //   console.log("PersonComponent: onContextPopupMenu - event=" + event);
  //   this.menu.open(event);
  // }

  protected   getRowData(): any[] {
    return this.rowData;
  }

  protected onRowSelected(event) {
    //
    // need to de-select all rows first then select the newly selected one.
    // this is to ensure we don't leave the previously selected row marked as still selected.
    //
    this.deselectAllRows();
    event.data.selected = event.node.selected;
    this.selectedRow = event.node.data;
    // this.menu.open(event);
  }

  protected deselectAllRows() {
    this.rowData.forEach(entry => entry.selected = false);
  }

  protected getColumnDefs() {
    return this.columnDefs;
  }

  protected onSelectionChanged(event) {
    const rowCount = event.api.getSelectedNodes().length;
    console.log('PersonComponent.onSelectionChanged: ' + rowCount + ' rows selected');
  }

  onGridReady(params) {
    this.gridApi = params.api;
  }

  protected actionSelected(popupMenuAction: any) {
    console.log('PersonComponent.actionSelected: popupMenuAction=' + popupMenuAction);
    switch (popupMenuAction) {
      case ResearchdataPopupMenuAction.AddEmptyRow:
        this.addEmptyRow();
        break;
      case ResearchdataPopupMenuAction.MarkForDeletion:
        this.markRowForDeletion();
        break;
      case ResearchdataPopupMenuAction.RemoveRow:
        this.removeEmptyRow();
        break;
      case ResearchdataPopupMenuAction.UnmarkForDeletion:
        this.unmarkForDeletion();
        break;
      default:
        console.error('PersonComponent.actionSelected: popup menu action not recognized');
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
    console.log('PersonComponent.markRowForDeletion');
    let nameOfAction;
    this.rowData.filter(this.isSelected).forEach(function (value) {
      value.action = DataAction.Delete;
      nameOfAction = DataAction[value.action];
      console.log('PersonComponent.delete: mark for deletion - ' + value.firstName + ' ' +
                  value.familyName + '     name of action=' + nameOfAction);
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
      const otherRows: Person[] = [];
      this.rowData.filter(entry => entry.selected === false).forEach(entry => otherRows.push(entry));
      console.log('removeEmptyRow: otherRows size = ' + otherRows.length);
      this.gridApi.setRowData(otherRows);
      this.gridApi.refreshCells();
    }
  }

  protected  unmarkForDeletion() {
    console.log('TODO: Unmarking row for deletion');
  }

  protected createNewRowDatum(): any {
    const datum = new Person();
    datum.status = DataStatus.New;
    datum.action = DataAction.Add;
    return datum;
  }

  protected isSelected(element, index, array) {
    return element.selected;
  }

  isUnsavedRow(row: Person) {
    return row && row.id === 0;
  }

  // protected   updateGrid(saved: Person) {
  //   if (saved && saved.name) {
  //     let newRowData: Person[] = [];
  //     this.rowData.forEach( (entry) => {
  //       if (saved.name === entry.name) {
  //         newRowData.push(saved);
  //       } else {
  //         newRowData.push(entry);
  //       }
  //     });
  //     this.gridApi.setRowData(newRowData);
  //     this.gridApi.refreshView();
  //   }
  //
  //   console.log('Data updated');
  // }

}

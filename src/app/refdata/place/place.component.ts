import { Component, OnInit, ViewChild } from '@angular/core';
import { DataAction } from '../../core/model/data-action';
import { CoreOperationsMessages } from '../../core/core-operations-messages';
import { DataStatus } from '../../core/model/data-status';
import { DateFormatters } from '../../core/formatters/date-formatters';
import { PlaceService } from './place.service';
import { Place } from '../../model/entity/place';
import { PopupMenuComponent } from '../../core/popup-menu/popup-menu.component';
import { ColDef, GridApi, GridOptions } from 'ag-grid-community';
import { HttpErrorResponse } from '@angular/common/http';
import {IAuditedDataType} from '../../model/entity/interfaces/audited-data-type';
import {Title} from '../../model/entity/title';

@Component({
    selector: 'app-place',
    templateUrl: './place.component.html',
    styleUrls: ['./place.component.scss']
})
export class PlaceComponent implements OnInit {

    @ViewChild(PopupMenuComponent) menu: PopupMenuComponent;

    private columnDefs: ColDef[];
    private gridApi: GridApi;
    private gridOptions: GridOptions;
    private haveEmptyRow: boolean;
    private rowData: Place[];
    private rowSelection: Place;
    private selectedRow: Place;

    protected httpError: HttpErrorResponse;
    protected operationMessage: string;

    protected liveStatuses: Array<DataStatus> = [];

    constructor(private placeService: PlaceService) {
        this.columnDefs = this.createColumnDefs();
        this.gridOptions = this.createGridOptions(this.onCellValueChanged);
    }

    ngOnInit() {
        this.initRowData();
        this.haveEmptyRow = false;
        this.liveStatuses.push(DataStatus.New);
        this.liveStatuses.push(DataStatus.Amend);
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

    onCellValueChanged(event) {
        // handle updated 'name' value
        console.log('onCellValueChanged - entry: event=' + event + ' haveEmptyRow=' + this.haveEmptyRow);
        if (event.data && event.data.id && event.data.id > 0) {
            event.data.action = DataAction.Update;
            this.gridApi.refreshCells();
        }
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

    protected createColumnDefs(): any[] {
        return [
            { headerName: '', field: 'selected', width: 30, headerCheckboxSelection: true, checkboxSelection: true, editable: true },
            { headerName: 'ID', field: 'id', width: 60, editable: false, filter: true },
            { headerName: 'Code', field: 'code', width: 80, editable: false, filter: true },
            { headerName: 'Name', field: 'name', width: 200, editable: false, filter: true },
            { headerName: 'Latitude', field: 'latitude', width: 300, editable: false, filter: true },
            { headerName: 'Longitude', field: 'longitude', width: 300, editable: false, filter: true },
            { headerName: 'Altitude', field: 'altitude', width: 200, editable: false, filter: true },
            { headerName: 'River', field: 'river', width: 100, editable: false, filter: true },
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

    initRowData() {
        this.rowData = [];
        //
        this.operationMessage = CoreOperationsMessages.FINDALL_PLACE;
        this.placeService.findAll().subscribe(
            data => {
                for (const datum in data) {
                    if (datum.hasOwnProperty(datum)) {
                        const place: Place = data[datum];
                        place.selected = false;
                        place.isDataChanged = false;
                    }
                }
                this.rowData = data;
            },
            err => {
                this.httpError = err;
            });
    }

    getRowData(): any[] {
        return this.rowData;
    }

    getColumnDefs() {
        return this.columnDefs;
    }

    onGridReady(params) {
        this.gridApi = params.api;
    }

    revertChanges() {
        console.log('PlaceComponent.revertChanges: TODO');
    }

    saveChanges() {
        console.log('PlaceComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
        // save only new and modified rows
        const toBeSaved = this.rowData.filter(row => row.action != null);

        if (toBeSaved) {
            for (const place of toBeSaved) {
                if (DataAction.Add === place.action) {
                    this.enrichAuditData(place);
                    this.placeService.add(place).subscribe(
                        data => {
                            this.httpError = null;
                            this.updateGrid(data);
                        },
                        err => {
                            console.error('PlaceComponent.add: err="' + err);
                            this.httpError = err;
                            this.operationMessage = CoreOperationsMessages.ADD_PLACE;
                        });
                } else if (DataAction.Update === place.action) {
                    place.status = DataStatus.Amend;
                    this.placeService.update(place).subscribe(
                        data => {
                            this.httpError = null;
                            this.updateGrid(data);
                        },
                        err => {
                            console.error('PlaceComponent.update: err="' + err);
                            this.httpError = err;
                            this.operationMessage = CoreOperationsMessages.UPDATE_PLACE;
                        }
                    );
                } else if (DataAction.Delete === place.action) {
                    place.status = DataStatus.Delete;
                    this.placeService.delete(place).subscribe(
                        data => {
                            console.log('Place ' + place.action + 'ed - result=' + data);
                            this.httpError = null;
                            const remainingRows: Place[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
                            this.gridApi.setRowData(remainingRows);
                            this.gridApi.refreshCells();
                        },
                        err => {
                            console.error('PlaceComponent.delete: err="' + err);
                            this.httpError = err;
                            this.operationMessage = CoreOperationsMessages.DELETE_PLACE;
                        }
                    );
                }
            }
        }

    }

    updateGrid(saved: Place) {
        if (saved && saved.name) {
            const newRowData: Place[] = [];
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

}

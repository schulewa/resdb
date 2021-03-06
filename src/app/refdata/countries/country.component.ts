import { Component, OnInit } from '@angular/core';
import { DataAction } from '../../core/model/data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { AuditedNamedEntityGridComponent } from '../../core/audited-named-entity-grid.component';
import { CoreOperationsMessages } from '../../core/core-operations-messages';
import { DataStatus } from '../../core/model/data-status';
import { Country } from '../../model/entity/country';
import { CountryService } from './country.service';
import {DateFormatters} from '../../core/formatters/date-formatters';
import {ColDef, GridApi, GridOptions} from 'ag-grid-community';

@Component({
    selector: 'app-country',
    templateUrl: './country.component.html',
    styleUrls: ['./country.component.scss']
})
export class CountryComponent extends AuditedNamedEntityGridComponent<Country> implements OnInit {

    constructor(private countryService: CountryService) {
        super(Country);
        this.columnDefs = this.createColumnDefs();
        this.gridOptions = this.createGridOptions(this.onCellValueChanged);
    }

    protected createColumnDefs(): any[] {
        return [
            { headerName: '', field: 'selected', width: 30, headerCheckboxSelection: true, checkboxSelection: true, editable: true },
            { headerName: 'ID', field: 'id', width: 60, editable: false, filter: true },
            { headerName: 'Code', field: 'code', width: 80, editable: true, filter: true },
            { headerName: 'Name', field: 'name', width: 200, editable: true, filter: true },
            { headerName: 'State Name', field: 'stateName', width: 300, editable: true, filter: true },
            { headerName: 'Sovereignty', field: 'sovereignty', width: 300, editable: true, filter: true },
            { headerName: 'Flag Image', field: 'flagImage', width: 200, editable: true, filter: true },
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
        this.operationMessage = CoreOperationsMessages.FINDALL_COUNTRY;
        this.countryService.findAll().subscribe(
            data => {
                for (const datum in data) {
                    if (data.hasOwnProperty(datum)) {
                        const country: IAuditedNameDataType = data[datum];
                        country.selected = false;
                        country.isDataChanged = false;
                    }
                }
                this.rowData = data;
                this.httpError = null;
            },
            err => {
                console.error('CountryComponent.findall: err="' + err);
                this.httpError = err;
            });
    }

    revertChanges() {
        console.log('CountryComponent.revertChanges: TODO');
    }

    saveChanges() {
        console.log('CountryComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
        // save only new and modified rows
        const toBeSaved = this.rowData.filter(row => row.action != null);

        if (toBeSaved) {
            for (const country of toBeSaved) {
                if (DataAction.Add === country.action) {
                    this.enrichAuditData(country);
                    this.countryService.add(country).subscribe(
                        data => {
                            this.httpError = null;
                            this.updateGrid(data);
                        },
                        err => {
                            console.error('CountryComponent.add: err="' + err);
                            this.httpError = err;
                            this.operationMessage = CoreOperationsMessages.ADD_COUNTRY;
                        });
                } else if (DataAction.Update === country.action) {
                    country.status = DataStatus.Amend;
                    this.countryService.update(country).subscribe(
                        data => {
                            this.httpError = null;
                            this.updateGrid(data);
                        },
                        err => {
                            console.error('CountryComponent.update: err="' + err);
                            this.httpError = err;
                            this.operationMessage = CoreOperationsMessages.UPDATE_COUNTRY;
                        }
                    );
                } else if (DataAction.Delete === country.action) {
                    country.status = DataStatus.Delete;
                    this.countryService.delete(country).subscribe(
                        data => {
                            console.log('Country ' + country.action + 'ed - result=' + data);
                            this.httpError = null;
                            const remainingRows: IAuditedNameDataType[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
                            this.gridApi.setRowData(remainingRows);
                            this.gridApi.refreshCells();
                        },
                        err => {
                            console.error('CountryComponent.delete: err="' + err);
                            this.httpError = err;
                            this.operationMessage = CoreOperationsMessages.DELETE_COUNTRY;
                        }
                    );
                }
            }
        }

    }

}

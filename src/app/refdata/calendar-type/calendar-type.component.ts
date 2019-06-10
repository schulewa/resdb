import { Component, OnInit } from '@angular/core';
import { CalendarTypeService } from './calendar-type.service';
import { DataAction } from '../data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { CalendarType } from '../../model/entity/calendar-type';
import { BaseNameComponent } from '../base-name.component';
import { CoreOperationsMessages } from '../../core/core-operations-messages';
import { DataStatus } from '../data-status';

@Component({
  selector: 'app-calendar-type',
  templateUrl: './calendar-type.component.html',
  styleUrls: ['./calendar-type.component.scss']
})
export class CalendarTypeComponent extends BaseNameComponent<CalendarType> implements OnInit {

  constructor(private calendarTypeService: CalendarTypeService) {
    super(CalendarType);
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.calendarTypeService.findAll().subscribe((resp) => {
      for (const datum in resp) {
        if (resp.hasOwnProperty(datum)) {
          const calendarType: IAuditedNameDataType = resp[datum];
          calendarType.selected = false;
          calendarType.isDataChanged = false;
        }
      }
      this.rowData = resp;
    });
  }

  revertChanges() {
    console.log('CalendarTypeComponent.revertChanges: TODO');
  }

  saveChanges() {
    console.log('CalendarTypeComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const calendarType of toBeSaved) {
        if (DataAction.Add === calendarType.action) {
          this.enrichAuditData(calendarType);
          this.calendarTypeService.add(calendarType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('CalendarTypeComponent.add: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.ADD_CALENDAR_TYPE;
            });
        } else if (DataAction.Update === calendarType.action) {
          calendarType.status = DataStatus.Amend;
          this.calendarTypeService.update(calendarType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('CalendarTypeComponent.update: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.UPDATE_CALENDAR_TYPE;
            }
          );
        } else if (DataAction.Delete === calendarType.action) {
          calendarType.status = DataStatus.Delete;
          this.calendarTypeService.delete(calendarType).subscribe(
            data => {
              console.log('Calendar type ' + calendarType.action + 'ed - result=' + data);
              this.httpError = null;
              const remainingRows: IAuditedNameDataType[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
              this.gridApi.setRowData(remainingRows);
              this.gridApi.refreshCells();
            },
            err => {
              console.error('CalendarTypeComponent.delete: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.DELETE_CALENDAR_TYPE;
            }
          );
        }
      }
    }

  }

}
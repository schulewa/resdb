import { Component, OnInit } from '@angular/core';
import { EventTypeGroupService } from './event-type-group.service';
import { DataAction } from '../../core/model/data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { EventTypeGroup } from '../../model/entity/event-type-group';
import { AuditedNamedEntityGridComponent } from '../../core/audited-named-entity-grid.component';
import { CoreOperationsMessages } from '../../core/core-operations-messages';
import { DataStatus } from '../../core/model/data-status';

@Component({
  selector: 'app-event-type-group',
  templateUrl: './event-type-group.component.html',
  styleUrls: ['./event-type-group.component.scss']
})
export class EventTypeGroupComponent extends AuditedNamedEntityGridComponent<EventTypeGroup> implements OnInit {

  constructor(private eventTypeGroupService: EventTypeGroupService) {
    super(EventTypeGroup);
    console.log('EventTypeGroupComponent constructor');
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.operationMessage = CoreOperationsMessages.FINDALL_EVENT_TYPE_GROUP;
    this.eventTypeGroupService.findAll().subscribe(
      data => {
      for (const datum in data) {
        if (data.hasOwnProperty(datum)) {
          const eventTypeGroup: IAuditedNameDataType = data[datum];
          eventTypeGroup.selected = false;
          eventTypeGroup.isDataChanged = false;
        }
      }
      this.rowData = data;
      this.httpError = null;
    },
      err => {
        console.error('EventTypeGroupComponent.findall: err="' + err);
        this.httpError = err;
      });
  }

  revertChanges() {
    console.log('EventTypeGroupComponent.revertChanges: TODO');
  }

  saveChanges() {
    console.log('EventTypeGroupComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const eventTypeGroup of toBeSaved) {
        if (DataAction.Add === eventTypeGroup.action) {
          this.operationMessage = CoreOperationsMessages.ADD_EVENT_TYPE_GROUP;
          this.enrichAuditData(eventTypeGroup);
          this.eventTypeGroupService.add(eventTypeGroup).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('EventTypeGroupComponent.add: err="' + err);
              this.httpError = err;
            });
        } else if (DataAction.Update === eventTypeGroup.action) {
          this.operationMessage = CoreOperationsMessages.UPDATE_EVENT_TYPE_GROUP;
          eventTypeGroup.status = DataStatus.Amend;
          this.eventTypeGroupService.update(eventTypeGroup).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('EventTypeGroupComponent.update: err="' + err);
              this.httpError = err;
            }
          );
        } else if (DataAction.Delete === eventTypeGroup.action) {
          this.operationMessage = CoreOperationsMessages.DELETE_EVENT_TYPE_GROUP;
          eventTypeGroup.status = DataStatus.Delete;
          this.eventTypeGroupService.delete(eventTypeGroup).subscribe(
            data => {
              this.httpError = null;
              const remainingRows: IAuditedNameDataType[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
              this.gridApi.setRowData(remainingRows);
              this.gridApi.refreshCells();
            },
            err => {
              console.error('EventTypeGroupComponent.delete: err="' + err);
              this.httpError = err;
            }
          );
        }
      }
    }

  }

}

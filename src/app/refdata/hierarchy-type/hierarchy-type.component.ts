import { Component, OnInit } from '@angular/core';
import { HierarchyTypeService } from './hierarchy-type.service';
import { DataAction } from '../data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { HierarchyType } from '../../model/entity/hierarchy-type';
import {BaseNameComponent} from '../base-name.component';
import {CoreOperationsMessages} from '../../core/core-operations-messages';
import {DataStatus} from '../data-status';

@Component({
  selector: 'app-hierarchy-type',
  templateUrl: './hierarchy-type.component.html',
  styleUrls: ['./hierarchy-type.component.scss']
})
export class HierarchyTypeComponent extends BaseNameComponent<HierarchyType> implements OnInit {

  constructor(private hierarchyTypeService: HierarchyTypeService) {
    super(HierarchyType);
    console.log('HierarchyTypeComponent constructor');
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.operationMessage = CoreOperationsMessages.FINDALL_HIERARCHY_TYPE;
    this.hierarchyTypeService.findAll().subscribe(
      data => {
      for (const datum in data) {
        if (data.hasOwnProperty(datum)) {
          const calendarType: IAuditedNameDataType = data[datum];
          calendarType.selected = false;
          calendarType.isDataChanged = false;
        }
      }
      this.rowData = data;
      this.httpError = null;
    },
      err => {
        console.error('HierarchyTypeComponent.findall: err="' + err);
        this.httpError = err;
      });
  }

  revertChanges() {
    console.log('HierarchyTypeComponent.revertChanges: TODO');
  }

  saveChanges() {
    console.log('HierarchyTypeComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const hierarchyType of toBeSaved) {
        if (DataAction.Add === hierarchyType.action) {
          this.operationMessage = CoreOperationsMessages.ADD_HIERARCHY_TYPE;
          this.enrichAuditData(hierarchyType);
          this.hierarchyTypeService.add(hierarchyType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('HierarchyTypeComponent.add: err="' + err);
              this.httpError = err;
            });
        } else if (DataAction.Update === hierarchyType.action) {
          this.operationMessage = CoreOperationsMessages.UPDATE_HIERARCHY_TYPE;
          hierarchyType.status = DataStatus.Amend;
          this.hierarchyTypeService.update(hierarchyType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('HierarchyTypeComponent.update: err="' + err);
              this.httpError = err;
            }
          );
        } else if (DataAction.Delete === hierarchyType.action) {
          this.operationMessage = CoreOperationsMessages.DELETE_HIERARCHY_TYPE;
          hierarchyType.status = DataStatus.Delete;
          this.hierarchyTypeService.delete(hierarchyType).subscribe(
            data => {
              this.httpError = null;
              const remainingRows: IAuditedNameDataType[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
              this.gridApi.setRowData(remainingRows);
              this.gridApi.refreshCells();
            },
            err => {
              console.error('HierarchyTypeComponent.delete: err="' + err);
              this.httpError = err;
            }
          );
        }
      }
    }

  }

}

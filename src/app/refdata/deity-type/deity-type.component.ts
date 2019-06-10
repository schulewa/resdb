import { Component, OnInit } from '@angular/core';
import { DeityTypeService } from './deity-type.service';
import { DataAction } from '../data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { DeityType } from '../../model/entity/deity-type';
import { BaseNameComponent } from '../base-name.component';
import {CoreOperationsMessages} from '../../core/core-operations-messages';
import {DataStatus} from '../data-status';

@Component({
  selector: 'app-deity-type',
  templateUrl: './deity-type.component.html',
  styleUrls: ['./deity-type.component.scss']
})
export class DeityTypeComponent extends BaseNameComponent<DeityType> implements OnInit {

  constructor(private deityTypeService: DeityTypeService) {
    super(DeityType);
    console.log('DeityTypeComponent constructor');
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.deityTypeService.findAll().subscribe((resp) => {
      for (const datum in resp) {
        if (resp.hasOwnProperty(datum)) {
          const deityType: IAuditedNameDataType = resp[datum];
          deityType.selected = false;
          deityType.isDataChanged = false;
        }
      }
      this.rowData = resp;
    });
  }

  revertChanges() {
    console.log('DeityTypeComponent.revertChanges: TODO');
  }

  saveChanges() {
    console.log('DeityTypeComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const deityType of toBeSaved) {
        if (DataAction.Add === deityType.action) {
          this.enrichAuditData(deityType);
          this.deityTypeService.add(deityType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('DeityTypeComponent.add: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.ADD_DEITY_TYPE;
            });
        } else if (DataAction.Update === deityType.action) {
          deityType.status = DataStatus.Amend;
          this.deityTypeService.update(deityType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('DeityTypeComponent.update: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.UPDATE_DEITY_TYPE;
            }
          );
        } else if (DataAction.Delete === deityType.action) {
          deityType.status = DataStatus.Delete;
          this.deityTypeService.delete(deityType).subscribe(
            data => {
              this.httpError = null;
              const remainingRows: IAuditedNameDataType[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
              this.gridApi.setRowData(remainingRows);
              this.gridApi.refreshCells();
            },
            err => {
              console.error('DeityTypeComponent.delete: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.DELETE_DEITY_TYPE;
            }
          );
        }
      }
    }

  }

}

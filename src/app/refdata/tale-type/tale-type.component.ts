import { Component, OnInit } from '@angular/core';
import { TaleTypeService } from './tale-type.service';
import { DataAction } from '../data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { BaseNameComponent } from '../base-name.component';
import { TaleType } from '../../model/entity/tale-type';
import { CoreOperationsMessages } from '../../core/core-operations-messages';
import { DataStatus } from '../data-status';

@Component({
  selector: 'app-tale-type',
  templateUrl: './tale-type.component.html',
  styleUrls: ['./tale-type.component.scss']
})
export class TaleTypeComponent extends BaseNameComponent<TaleType> implements OnInit {

  constructor(private taleTypeService: TaleTypeService) {
    super(TaleType);
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.operationMessage = CoreOperationsMessages.FINDALL_TALE_TYPE;
    this.taleTypeService.findAll().subscribe(
      data => {
      for (const datum in data) {
        if (datum.hasOwnProperty(datum)) {
          const taleType: IAuditedNameDataType = data[datum];
          taleType.selected = false;
          taleType.isDataChanged = false;
        }
      }
      this.rowData = data;
    },
      err => {
        this.httpError = err;
      });
  }

  revertChanges() {
    console.log('TaleTypeService.revertChanges: TODO');
  }

  saveChanges() {
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const raceType of toBeSaved) {
        if (DataAction.Add === raceType.action) {
          this.operationMessage = CoreOperationsMessages.ADD_TALE_TYPE;
          this.enrichAuditData(raceType);
          this.taleTypeService.add(raceType).subscribe(
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
          this.taleTypeService.update(raceType).subscribe(
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
          this.taleTypeService.delete(raceType).subscribe(
            data => {
              this.httpError = null;
              const remainingRows: IAuditedNameDataType[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
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

}

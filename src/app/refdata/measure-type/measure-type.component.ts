import { Component, OnInit } from '@angular/core';
import { MeasureTypeService } from './measure-type.service';
import { DataAction } from '../../core/model/data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { MeasureType } from '../../model/entity/measure-type';
import { AuditedNamedEntityGridComponent } from '../../core/audited-named-entity-grid.component';
import { CoreOperationsMessages } from '../../core/core-operations-messages';
import {DataStatus} from '../../core/model/data-status';

@Component({
  selector: 'app-measure-type',
  templateUrl: './measure-type.component.html',
  styleUrls: ['./measure-type.component.scss']
})
export class MeasureTypeComponent extends AuditedNamedEntityGridComponent<MeasureType> implements OnInit {

  constructor(private measureTypeService: MeasureTypeService) {
    super(MeasureType);
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.operationMessage = CoreOperationsMessages.FINDALL_MEASURE_TYPE;
    this.measureTypeService.findAll().subscribe(
      data => {
      for (const datum in data) {
        if (data.hasOwnProperty(datum)) {
          const measureType: IAuditedNameDataType = data[datum];
          measureType.selected = false;
          measureType.isDataChanged = false;
        }
      }
      this.rowData = data;
      this.httpError = null;
    },
      err => {
        this.httpError = err;
      });
  }

  revertChanges() {
    console.log('MeasureTypeComponent.revertChanges: TODO');
  }

  saveChanges() {
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const measureType of toBeSaved) {
        if (DataAction.Add === measureType.action) {
          this.operationMessage = CoreOperationsMessages.ADD_MEASURE_TYPE;
          this.enrichAuditData(measureType);
          this.measureTypeService.add(measureType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              this.httpError = err;
            });
        } else if (DataAction.Update === measureType.action) {
          this.operationMessage = CoreOperationsMessages.UPDATE_MEASURE_TYPE;
          measureType.status = DataStatus.Amend;
          this.measureTypeService.update(measureType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              this.httpError = err;
            }
          );
        } else if (DataAction.Delete === measureType.action) {
          this.operationMessage = CoreOperationsMessages.DELETE_MEASURE_TYPE;
          measureType.status = DataStatus.Delete;
          this.measureTypeService.delete(measureType).subscribe(
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

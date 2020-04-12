import { Component, OnInit } from '@angular/core';
import { DataAction } from '../../core/model/data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { AuditedNamedEntityGridComponent } from '../../core/audited-named-entity-grid.component';
import { CoreOperationsMessages } from '../../core/core-operations-messages';
import { DataStatus } from '../../core/model/data-status';
import { ArtefactTypeService } from './artefact-type.service';
import { ArtefactType } from '../../model/entity/artefact-type';


@Component({
  selector: 'app-artefact-type',
  templateUrl: './artefact-type.component.html',
  styleUrls: ['./artefact-type.component.scss']
})
export class ArtefactTypeComponent extends AuditedNamedEntityGridComponent<ArtefactType> implements OnInit {

  constructor(private artefactTypeService: ArtefactTypeService) {
    super(ArtefactType);
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.operationMessage = CoreOperationsMessages.FINDALL_ARTEFACT_TYPE;
    this.artefactTypeService.findAll().subscribe(
      data => {
      for (const datum in data) {
        if (data.hasOwnProperty(datum)) {
          const artefactType: IAuditedNameDataType = data[datum];
          artefactType.selected = false;
          artefactType.isDataChanged = false;
        }
      }
      this.rowData = data;
      this.httpError = null;
    },
      err => {
        console.error('ArtefactTypeComponent.findall: err="' + err);
        this.httpError = err;
      });
  }

  revertChanges() {
    console.log('ArtefactTypeComponent.revertChanges: TODO');
  }

  saveChanges() {
    console.log('ArtefactTypeComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const artefactType of toBeSaved) {
        if (DataAction.Add === artefactType.action) {
          this.enrichAuditData(artefactType);
          this.artefactTypeService.add(artefactType).subscribe(
            data => {
              this.updateGrid(data);
            },
            err => {
              console.error('ArtefactTypeComponent.add: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.ADD_ARTEFACT_TYPE;
            });
        } else if (DataAction.Update === artefactType.action) {
          artefactType.status = DataStatus.Amend;
          this.artefactTypeService.update(artefactType).subscribe(
            data => {
              this.updateGrid(data);
            },
            err => {
              console.error('ArtefactTypeComponent.update: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.UPDATE_ARTEFACT_TYPE;
            }
          );
        } else if (DataAction.Delete === artefactType.action) {
          artefactType.status = DataStatus.Delete;
          this.artefactTypeService.delete(artefactType).subscribe(
            data => {
              console.log('Artefact type ' + artefactType.action + 'ed - result=' + data);
              const remainingRows: IAuditedNameDataType[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
              this.gridApi.setRowData(remainingRows);
              this.gridApi.refreshCells();
            },
            err => {
              console.error('ArtefactTypeComponent.delete: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.DELETE_ARTEFACT_TYPE;
            }
          );
        }
      }
    }

  }

}

import { Component, OnInit } from '@angular/core';
import { ArtefactGroupService } from './artefact-group.service';
import { DataAction } from '../data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { BaseNameComponent } from '../base-name.component';
import { ArtefactGroup } from '../../model/entity/artefact-group';
import { CoreOperationsMessages } from '../../core/core-operations-messages';
import { DataStatus } from '../data-status';


@Component({
  selector: 'app-artefact-group',
  templateUrl: './artefact-group.component.html',
  styleUrls: ['./artefact-group.component.scss']
})
export class ArtefactGroupComponent extends BaseNameComponent<ArtefactGroup> implements OnInit {

  constructor(private artefactGroupService: ArtefactGroupService) {
    super(ArtefactGroup);
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.artefactGroupService.findAll().subscribe((resp) => {
      for (const datum in resp) {
        if (resp.hasOwnProperty(datum)) {
          const artefactGroup: IAuditedNameDataType = resp[datum];
          artefactGroup.selected = false;
          artefactGroup.isDataChanged = false;
        }
      }
      this.rowData = resp;
    });
  }

  revertChanges() {
    console.log('ArtefactGroupComponent.revertChanges: TODO');
  }

  saveChanges() {
    console.log('ArtefactGroupComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const artefactGroup of toBeSaved) {
        if (DataAction.Add === artefactGroup.action) {
          this.enrichAuditData(artefactGroup);
          this.artefactGroupService.add(artefactGroup).subscribe(
            data => {
            this.httpError = null;
            this.updateGrid(data);
          },
            err => {
              console.error('ArtefactGroupComponent.add: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.ADD_ARTEFACT_GROUP;
            });
        } else if (DataAction.Update === artefactGroup.action) {
          artefactGroup.status = DataStatus.Amend;
          this.artefactGroupService.update(artefactGroup).subscribe(
            data => {
            this.httpError = null;
            this.updateGrid(data);
          },
            err => {
              console.error('ArtefactGroupComponent.update: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.UPDATE_ARTEFACT_GROUP;
            }
          );
        } else if (DataAction.Delete === artefactGroup.action) {
          artefactGroup.status = DataStatus.Delete;
          this.artefactGroupService.delete(artefactGroup).subscribe(
            data => {
              console.log('Artefact group ' + artefactGroup.action + 'ed - result=' + data);
              this.httpError = null;
              const remainingRows: IAuditedNameDataType[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
              this.gridApi.setRowData(remainingRows);
              this.gridApi.refreshCells();
            },
            err => {
              console.error('ArtefactGroupComponent.delete: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.DELETE_ARTEFACT_GROUP;
            }
          );
        }
      }
    }

  }

}

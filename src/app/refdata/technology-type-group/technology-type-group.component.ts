import { Component, OnInit } from '@angular/core';
import { TechnologyTypeGroupService } from './technology-type-group.service';
import { DataAction } from '../data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { BaseNameComponent } from '../base-name.component';
import { TechnologyTypeGroup } from '../../model/entity/technology-type-group';
import {CoreOperationsMessages} from '../../core/core-operations-messages';
import {DataStatus} from '../data-status';

@Component({
  selector: 'app-technology-type-group',
  templateUrl: './technology-type-group.component.html',
  styleUrls: ['./technology-type-group.component.scss']
})
export class TechnologyTypeGroupComponent extends BaseNameComponent<TechnologyTypeGroup> implements OnInit {

  constructor(private technologyTypeGroupService: TechnologyTypeGroupService) {
    super(TechnologyTypeGroup);
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.operationMessage = CoreOperationsMessages.FINDALL_TECHNOLOGY_TYPE_GROUP;
    this.technologyTypeGroupService.findAll().subscribe(
      data => {
      for (const datum in data) {
        if (datum.hasOwnProperty(datum)) {
          const technologyTypeGroup: IAuditedNameDataType = data[datum];
          technologyTypeGroup.selected = false;
          technologyTypeGroup.isDataChanged = false;
        }
      }
      this.rowData = data;
    },
      err => {
        this.httpError = err;
      });
  }

  revertChanges() {
    console.log('TechnologyTypeGroupComponent.revertChanges: TODO');
  }

  saveChanges() {
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const technologyTypeGroup of toBeSaved) {
        if (DataAction.Add === technologyTypeGroup.action) {
          this.operationMessage = CoreOperationsMessages.ADD_TECHNOLOGY_TYPE_GROUP;
          this.enrichAuditData(technologyTypeGroup);
          this.technologyTypeGroupService.add(technologyTypeGroup).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              this.httpError = err;
            });
        } else if (DataAction.Update === technologyTypeGroup.action) {
          this.operationMessage = CoreOperationsMessages.UPDATE_TECHNOLOGY_TYPE_GROUP;
          technologyTypeGroup.status = DataStatus.Amend;
          this.technologyTypeGroupService.update(technologyTypeGroup).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              this.httpError = err;
            }
          );
        } else if (DataAction.Delete === technologyTypeGroup.action) {
          this.operationMessage = CoreOperationsMessages.DELETE_TECHNOLOGY_TYPE_GROUP;
          technologyTypeGroup.status = DataStatus.Delete;
          this.technologyTypeGroupService.delete(technologyTypeGroup).subscribe(
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

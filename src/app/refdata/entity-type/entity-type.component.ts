import { Component, OnInit } from '@angular/core';
import { EntityTypeService } from './entity-type.service';
import { DataAction } from '../../core/model/data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { EntityType } from '../../model/entity/entity-type';
import { AuditedNamedEntityGridComponent } from '../../core/audited-named-entity-grid.component';
import { CoreOperationsMessages } from '../../core/core-operations-messages';
import { DataStatus } from '../../core/model/data-status';

@Component({
  selector: 'app-entity-type',
  templateUrl: './entity-type.component.html',
  styleUrls: ['./entity-type.component.scss']
})
export class EntityTypeComponent extends AuditedNamedEntityGridComponent<EntityType> implements OnInit {

  constructor(private entityTypeService: EntityTypeService) {
    super(EntityType);
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.operationMessage = CoreOperationsMessages.FINDALL_ENTITY_TYPE;
    this.entityTypeService.findAll().subscribe(
      data => {
      for (const datum in data) {
        if (data.hasOwnProperty(datum)) {
          const entityType: IAuditedNameDataType = data[datum];
          entityType.selected = false;
          entityType.isDataChanged = false;
        }
      }
      this.rowData = data;
      this.httpError = null;
    },
      err => {
        console.error('EnityTypeComponent.findall: err="' + err);
        this.httpError = err;
      });
  }

  revertChanges() {
    console.log('EnityTypeComponent.revertChanges: TODO');
  }

  saveChanges() {
    console.log('EnityTypeComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const entityType of toBeSaved) {
        if (DataAction.Add === entityType.action) {
          this.enrichAuditData(entityType);
          this.entityTypeService.add(entityType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('EntityTypeComponent.add: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.ADD_ENTITY_TYPE;
            });
        } else if (DataAction.Update === entityType.action) {
          entityType.status = DataStatus.Amend;
          this.entityTypeService.update(entityType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('EntityTypeComponent.update: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.UPDATE_ENTITY_TYPE;
            }
          );
        } else if (DataAction.Delete === entityType.action) {
          entityType.status = DataStatus.Delete;
          this.entityTypeService.delete(entityType).subscribe(
            data => {
              this.httpError = null;
              const remainingRows: IAuditedNameDataType[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
              this.gridApi.setRowData(remainingRows);
              this.gridApi.refreshCells();
            },
            err => {
              console.error('EntityTypeComponent.delete: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.DELETE_ENTITY_TYPE;
            }
          );
        }
      }
    }

  }

}

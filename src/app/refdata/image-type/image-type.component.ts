import { Component, OnInit } from '@angular/core';
import { ImageTypeService } from './image-type.service';
import { DataAction } from '../data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { ImageType } from '../../model/entity/image-type';
import {BaseNameComponent} from '../base-name.component';
import {CoreOperationsMessages} from '../../core/core-operations-messages';
import {DataStatus} from '../data-status';

@Component({
  selector: 'app-image-type',
  templateUrl: './image-type.component.html',
  styleUrls: ['./image-type.component.scss']
})
export class ImageTypeComponent extends BaseNameComponent<ImageType> implements OnInit {

  constructor(private imageTypeService: ImageTypeService) {
    super(ImageType);
    console.log('ImageTypeComponent constructor');
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.imageTypeService.findAll().subscribe((resp) => {
      for (const datum in resp) {
        if (resp.hasOwnProperty(datum)) {
          const imageType: IAuditedNameDataType = resp[datum];
          imageType.selected = false;
          imageType.isDataChanged = false;
        }
      }
      this.rowData = resp;
    });
  }

  revertChanges() {
    console.log('ImageTypeComponent.revertChanges: TODO');
  }

  saveChanges() {
    console.log('ImageTypeComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const imageType of toBeSaved) {
        if (DataAction.Add === imageType.action) {
          this.operationMessage = CoreOperationsMessages.ADD_HIERARCHY_TYPE;
          this.enrichAuditData(imageType);
          this.imageTypeService.add(imageType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('ImageTypeComponent.add: err="' + err);
              this.httpError = err;
            });
        } else if (DataAction.Update === imageType.action) {
          this.operationMessage = CoreOperationsMessages.UPDATE_HIERARCHY_TYPE;
          imageType.status = DataStatus.Amend;
          this.imageTypeService.update(imageType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('ImageTypeComponent.update: err="' + err);
              this.httpError = err;
            }
          );
        } else if (DataAction.Delete === imageType.action) {
          this.operationMessage = CoreOperationsMessages.DELETE_HIERARCHY_TYPE;
          imageType.status = DataStatus.Delete;
          this.imageTypeService.delete(imageType).subscribe(
            data => {
              this.httpError = null;
              const remainingRows: IAuditedNameDataType[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
              this.gridApi.setRowData(remainingRows);
              this.gridApi.refreshCells();
            },
            err => {
              console.error('ImageTypeComponent.delete: err="' + err);
              this.httpError = err;
            }
          );
        }
      }
    }

  }

}

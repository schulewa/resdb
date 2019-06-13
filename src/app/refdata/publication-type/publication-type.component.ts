import { Component, OnInit } from '@angular/core';
import { PublicationTypeService } from './publication-type.service';
import { DataAction } from '../data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { PublicationType } from '../../model/entity/publication-type';
import { BaseNameComponent } from '../base-name.component';
import {CoreOperationsMessages} from '../../core/core-operations-messages';
import {DataStatus} from '../data-status';

@Component({
  selector: 'app-publication-type',
  templateUrl: './publication-type.component.html',
  styleUrls: ['./publication-type.component.scss']
})
export class PublicationTypeComponent extends BaseNameComponent<PublicationType> implements OnInit {

  constructor(private publicationTypeService: PublicationTypeService) {
    super(PublicationType);
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.operationMessage = CoreOperationsMessages.FINDALL_PUBLICATION_TYPE;
    this.publicationTypeService.findAll().subscribe(
      data => {
      for (const datum in data) {
        if (datum.hasOwnProperty(datum)) {
          const publicationType: IAuditedNameDataType = data[datum];
          publicationType.selected = false;
          publicationType.isDataChanged = false;
        }
      }
      this.rowData = data;
    });
  }

  revertChanges() {
    console.log('PublicationTypeService.revertChanges: TODO');
  }

  saveChanges() {
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const publicationType of toBeSaved) {
        if (DataAction.Add === publicationType.action) {
          this.operationMessage = CoreOperationsMessages.ADD_PUBLICATION_TYPE;
          this.enrichAuditData(publicationType);
          this.publicationTypeService.add(publicationType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              this.httpError = err;
            });
        } else if (DataAction.Update === publicationType.action) {
          this.operationMessage = CoreOperationsMessages.UPDATE_PUBLICATION_TYPE;
          publicationType.status = DataStatus.Amend;
          this.publicationTypeService.update(publicationType).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              this.httpError = err;
            }
          );
        } else if (DataAction.Delete === publicationType.action) {
          this.operationMessage = CoreOperationsMessages.DELETE_PUBLICATION_TYPE;
          publicationType.status = DataStatus.Delete;
          this.publicationTypeService.delete(publicationType).subscribe(
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

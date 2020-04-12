import { Component, OnInit } from '@angular/core';
import { LanguageGroupService } from './language-group.service';
import { DataAction } from '../../core/model/data-action';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';
import { LanguageGroup } from '../../model/entity/language-group';
import { AuditedNamedEntityGridComponent } from '../../core/audited-named-entity-grid.component';
import {CoreOperationsMessages} from '../../core/core-operations-messages';
import {DataStatus} from '../../core/model/data-status';

@Component({
  selector: 'app-language-group',
  templateUrl: './language-group.component.html',
  styleUrls: ['./language-group.component.scss']
})
export class LanguageGroupComponent extends AuditedNamedEntityGridComponent<LanguageGroup> implements OnInit {

  constructor(private languageGroupService: LanguageGroupService) {
    super(LanguageGroup);
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.operationMessage = CoreOperationsMessages.FINDALL_LANGUAGE_GROUP;
    this.languageGroupService.findAll().subscribe(
      data => {
      for (const datum in data) {
        if (data.hasOwnProperty(datum)) {
          const languageGroup: IAuditedNameDataType = data[datum];
          languageGroup.selected = false;
          languageGroup.isDataChanged = false;
        }
      }
      this.rowData = data;
      this.httpError = null;
    },
      err => {
        console.error('LanguageGroupComponent.findAll: err="' + err);
        this.httpError = err;
      });
  }

  revertChanges() {
    console.log('LanguageGroupComponent.revertChanges: TODO');
  }

  saveChanges() {
    console.log('LanguageGroupComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const languageGroup of toBeSaved) {
        if (DataAction.Add === languageGroup.action) {
          this.operationMessage = CoreOperationsMessages.ADD_LANGUAGE_GROUP;
          this.enrichAuditData(languageGroup);
          this.languageGroupService.add(languageGroup).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('LanguageGroupComponent.add: err="' + err);
              this.httpError = err;
            });
        } else if (DataAction.Update === languageGroup.action) {
          this.operationMessage = CoreOperationsMessages.UPDATE_LANGUAGE_GROUP;
          languageGroup.status = DataStatus.Amend;
          this.languageGroupService.update(languageGroup).subscribe(
            data => {
              this.httpError = null;
              this.updateGrid(data);
            },
            err => {
              console.error('LanguageGroupComponent.update: err="' + err);
              this.httpError = err;
            }
          );
        } else if (DataAction.Delete === languageGroup.action) {
          this.operationMessage = CoreOperationsMessages.DELETE_LANGUAGE_GROUP;
          languageGroup.status = DataStatus.Delete;
          this.languageGroupService.delete(languageGroup).subscribe(
            data => {
              this.httpError = null;
              const remainingRows: IAuditedNameDataType[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
              this.gridApi.setRowData(remainingRows);
              this.gridApi.refreshCells();
            },
            err => {
              console.error('LanguageGroupComponent.delete: err="' + err);
              this.httpError = err;
            }
          );
        }
      }
    }

  }

}

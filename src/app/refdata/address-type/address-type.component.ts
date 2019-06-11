import { Component, HostListener, OnInit } from '@angular/core';
import { AddressType } from '../../model/entity/address-type';
import { AddressTypeService } from './address-type.service';
import { BaseNameComponent } from '../base-name.component';
import { DataAction } from '../data-action';
import { CoreOperationsMessages } from '../../core/core-operations-messages';
import { DataStatus } from '../data-status';
import { IAuditedNameDataType } from '../../model/entity/interfaces/audited-name-data-type';


@Component({
  selector: 'app-address-type',
  templateUrl: './address-type.component.html',
  styleUrls: ['./address-type.component.scss']
})
export class AddressTypeComponent extends BaseNameComponent<AddressType> implements OnInit {

  constructor(private addressTypeService: AddressTypeService) {
    super(AddressType);
    console.log('AddressTypeComponent constructor');
    this.columnDefs = this.createColumnDefs();
    this.gridOptions = this.createGridOptions(this.onCellValueChanged);
  }

  initRowData() {
    this.rowData = [];
    //
    this.operationMessage = CoreOperationsMessages.FINDALL_ADDRESS_TYPE;
    this.addressTypeService.findAll().subscribe(
      data => {
      for (const datum in data) {
        if (data.hasOwnProperty(datum)) {
          const addressType: AddressType = data[datum];
          addressType.selected = false;
          addressType.isDataChanged = false;
        }
      }
      this.rowData = data;
      this.httpError = null;
    },
      err => {
        console.error('AddressTypeComponent.findAll: err="' + err);
        this.httpError = err;
      });
  }

  revertChanges() {
    console.log('AddressTypeComponent.revertChanges: TODO');
  }

  saveChanges() {
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);

    if (toBeSaved) {
      for (const addressType of toBeSaved) {
        if (DataAction.Add === addressType.action) {
          this.operationMessage = CoreOperationsMessages.ADD_ADDRESS_TYPE;
          this.enrichAuditData(addressType);
          this.addressTypeService.add(addressType).subscribe(
            data => {
            console.log('Address type ' + addressType.action + 'ed - typeof result=' + typeof data);
            this.httpError = null;
            this.updateGrid(data);
          },
            err => {
              console.error('AddressTypeComponent.add: err="' + err);
              this.httpError = err;
          });
        } else if (DataAction.Update === addressType.action) {
          this.operationMessage = CoreOperationsMessages.UPDATE_ADDRESS_TYPE;
          addressType.status = DataStatus.Amend;
          this.addressTypeService.update(addressType).subscribe(
            data => {
            console.log('Address type ' + addressType.action + 'ed - result=' + data);
            this.httpError = null;
            this.updateGrid(data);
          },
            err => {
              console.error('AddressTypeComponent.update: err="' + err);
              this.httpError = err;
            }
          );

        } else if (DataAction.Delete === addressType.action) {
          this.operationMessage = CoreOperationsMessages.DELETE_ADDRESS_TYPE;
          addressType.status = DataStatus.Delete;
          this.addressTypeService.delete(addressType).subscribe(
            data => {
              console.log('Address type ' + addressType.action + 'ed - result=' + data);
              this.httpError = null;
              const remainingRows: IAuditedNameDataType[] = this.rowData.filter(r => (this.liveStatuses.includes(r.status)));
              // this.updateGrid(remainingRows);
              this.gridApi.setRowData(remainingRows);
              this.gridApi.refreshCells();
            },
            err => {
              console.error('AddressTypeComponent.delete: err="' + err);
              this.httpError = err;
            }
          );
        }
      }
    }

  }

  @HostListener('blur', ['$event'])
  onBlur(): void {
    console.log('onBlur: event=' + event);
  }

  @HostListener('edit', ['$event'])
  onCellEditingStopped(event) {
    console.log('onCellEditingStopped: event=' + event);
  }

}

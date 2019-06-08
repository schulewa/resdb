import {Component, HostListener, OnInit} from '@angular/core';
import {AddressType} from '../../model/entity/address-type';
import {AddressTypeService} from './address-type.service';
import {BaseNameComponent} from '../base-name.component';
import {DataAction} from '../data-action';
import {CoreOperationsMessages} from '../../core/core-operations-messages';
import {DataStatus} from '../data-status';


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
    this.addressTypeService.findAll().subscribe((resp) => {
      for (const datum in resp) {
        if (resp.hasOwnProperty(datum)) {
          const addressType: AddressType = resp[datum];
          addressType.selected = false;
          addressType.isDataChanged = false;
          console.log('Mapped address type [{}]', addressType);
        }
      }
      this.rowData = resp;
    });
  }

  //
  // TODO
  // 1. Set focus on blank row (after "Add empty row") has been executed
  // 2. disable "Add empty row" once a blank one has been added until focus is lost or the row is deleted
  // 3. check that "Name" is not duplicated; when focus exits Name field on current row
  // 4.


  revertChanges() {
    console.log('AddressTypeComponent.revertChanges: TODO');
  }

  saveChanges() {
    console.log('AddressTypeComponent.saveChanges - entry - number of rows = ' + this.rowData.length);
    // save only new and modified rows
    const toBeSaved = this.rowData.filter(row => row.action != null);
    const results: AddressType[] = [];

    if (toBeSaved) {
      for (const addressType of toBeSaved) {
        if (DataAction.Add === addressType.action) {
          this.enrichAuditData(addressType);
          this.addressTypeService.add(addressType).subscribe(
            data => {
            console.log('Address type ' + addressType.action + 'ed - typeof result=' + typeof data);
            this.updateGrid(data);
          },
            err => {
              console.error('AddressTypeComponent.add: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.ADD_ADDRESS_TYPE;
          });
        } else if (DataAction.Update === addressType.action) {
          addressType.status = DataStatus.Amend;
          this.addressTypeService.update(addressType).subscribe(
            data => {
            console.log('Address type ' + addressType.action + 'ed - result=' + data);
            this.updateGrid(data);
          },
            err => {
              console.error('AddressTypeComponent.update: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.UPDATE_ADDRESS_TYPE;
            }
          );

        } else if (DataAction.Delete === addressType.action) {
          addressType.status = DataStatus.Delete;
          this.addressTypeService.update(addressType).subscribe(
            data => {
              console.log('Address type ' + addressType.action + 'ed - result=' + data);
              this.updateGrid(data);
            },
            err => {
              console.error('AddressTypeComponent.delete: err="' + err);
              this.httpError = err;
              this.operationMessage = CoreOperationsMessages.DELETE_ADDRESS_TYPE;
            }
          );
        }
      }
    }

    console.log('AddressTypeComponent.saveChanges - after POST - results=' + results);
    console.log('AddressTypeComponent.saveChanges - exit');
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

import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxPermissionsService } from 'ngx-permissions';
import { NonEmptyDropdownValidator } from '../../core/validators/non-empty-dropdown.validator';

@Component({
  selector: 'app-simple-refdata',
  templateUrl: './simple-refdata.component.html',
  styleUrls: ['./simple-refdata.component.scss']
})
export class SimpleRefdataComponent implements OnInit, OnChanges {

  refDataTypeForm: FormGroup;
  selectedAddressType: boolean;
  selectedArtefactGroup: boolean;

  constructor(private fb: FormBuilder,
              private router: Router,
              private ngxPermissionsService: NgxPermissionsService) {
    console.log('simple-refdata constructor');
    this.createRefDataTypeForm();
  }

  ngOnInit() {
    console.log('simple-refdata.ngOnit');
    this.selectedAddressType = false;
    this.selectedArtefactGroup = false;
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log('simple-refdata.ngOnChanges: changes=' + changes);
  }

  createRefDataTypeForm() {
    console.log('simple-refdata.createRefDataTypeForm');
    this.refDataTypeForm = this.fb.group({
      refDataType : ['Please select', [Validators.required, NonEmptyDropdownValidator]]
    });
  }

  onChangedRefDataType(event: any) {
    console.log('simple-refdata.onChangedRefDataType: refDataTypeForm.controls[\'refDataType\']=' +
                this.refDataTypeForm.controls['refDataType']);
    console.log('simple-refdata.onChangedRefDataType: event.target.value=' + event.target.value);
  }

  loadSelectedRefDataType() {
    const selectedRefDataType = this.refDataTypeForm.controls['refDataType'];
    console.log('Loading selected reference data type = ' + selectedRefDataType);
    // display appropriate grid/form in panel
    this.disableAllRefDataTypeForms();
    switch (this.refDataTypeForm.controls['refDataType'].value) {
      case 'Address type':
        this.selectedAddressType = true;
        break;
      case 'Artefact group':
        this.selectedArtefactGroup = true;
    }
  }
  disableRefDataTypeSubmit(): boolean {
    if (this.refDataTypeForm.controls['refDataType'].value) {
      if (this.refDataTypeForm.controls['refDataType'].value === 'Please select') {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  disableAllRefDataTypeForms() {
    this.selectedAddressType = false;
    this.selectedArtefactGroup = false;
  }

}

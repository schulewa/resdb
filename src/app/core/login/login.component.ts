import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SecurityService } from '../services/security.service';
import { NgxPermissionsService } from 'ngx-permissions';
import { User } from '../../model/entity/user';
import { HttpErrorResponse } from '@angular/common/http';
import {CoreOperationsMessages} from '../core-operations-messages';

@Component({
  selector: 'app-login',
  providers: [SecurityService],
  templateUrl: './login.component.html',
  styleUrls: [
    './login.component.scss'
  ]
})
export class LoginComponent implements OnInit, OnChanges {

  private loginForm: FormGroup;
  private httpError: HttpErrorResponse;
  private operationMessage: string;

  constructor(private fb: FormBuilder,
              private router: Router,
              private securityService: SecurityService,
              private ngxPermissionsService: NgxPermissionsService) {
    this.createForm();
  }

  ngOnInit() {
    this.httpError = null;
    this.operationMessage = null;
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log('ngOnChanges: changes=' + changes);
  }

  createForm() {
    this.loginForm = this.fb.group({
      userName : [null, [Validators.max(20), Validators.required]],
      userPassword : [null, [Validators.max(30), Validators.required]],
    });
  }

  authenticate(user: User) {
    this.securityService.login(this.loginForm.controls['userName'].value, this.loginForm.controls['userPassword'].value)
      .subscribe(
        data => {
          console.log('user=' + user);
          this.onSuccessfulLogin(data);
          // localStorage.setItem('currentUser', this.loginForm.controls['userName'].value); // store the current user for later use
        },
        err => {
          console.error('login.authenticate: err="' + err);
          this.httpError = err;
          this.operationMessage = CoreOperationsMessages.AUTHENTICATE_USER;
        }
      );
  }

  private onSuccessfulLogin(data: any): void {
    console.log('onSuccessfulLogin: data=' + data);
    const perms: Array<string> = [];
    data.permissions.forEach(function (permission) {
      // console.log("permission.name=" + permission.name);
      perms.push(permission.name);
    });
    this.ngxPermissionsService.loadPermissions(perms);
    const loadedPerms = this.ngxPermissionsService.getPermissions();
    localStorage.setItem('currentUser', this.loginForm.controls['userName'].value); // store the current user for later use
    //
    this.httpError = null;
    //
    this.router.navigate(['home']);
  }
}

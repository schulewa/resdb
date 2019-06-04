import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SecurityService } from '../services/security.service';
import { NgxPermissionsService } from 'ngx-permissions';
import { User } from '../../model/entity/user';

@Component({
  selector: 'app-login',
  providers: [SecurityService],
  templateUrl: './login.component.html',
  styleUrls: [
    './login.component.scss'
  ]
})
export class LoginComponent implements OnInit, OnChanges {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder,
              private router: Router,
              private securityService: SecurityService,
              private ngxPermissionsService: NgxPermissionsService) {
    console.log('login constructor');
    this.createForm();
  }

  ngOnInit() {
    console.log('login.ngOnit');
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log('ngOnChanges: changes=' + changes);
  }

  createForm() {
    console.log('login.createForm');
    this.loginForm = this.fb.group({
      userName : [null, [Validators.max(20), Validators.required]],
      userPassword : [null, [Validators.max(30), Validators.required]],
    });
  }

  authenticate(user: User) {
    console.log('login.authenticate: user.userName=' + user.loginName + ' user.userPassword=' + user.loginPassword);
    console.log('authenticate: userName=' + this.loginForm.controls['userName'].value +
      '   password=' + this.loginForm.controls['userPassword'].value);
    this.securityService.login(this.loginForm.controls['userName'].value, this.loginForm.controls['userPassword'].value)
      .subscribe(
        data => {
          console.log('user=' + user);
          this.onSuccessfulLogin(data);
          // localStorage.setItem('currentUser', this.loginForm.controls['userName'].value); // store the current user for later use
        },
        err => console.error('login.authenticate: err="' + err)
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
    this.router.navigate(['home']);
  }
}

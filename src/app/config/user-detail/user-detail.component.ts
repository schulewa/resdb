import {Component, OnInit} from '@angular/core';
import {User} from '../../model/entity/user';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {UserService} from '../user.service';
import {ColDef, GridApi, GridOptions} from 'ag-grid-community';

@Component({
    selector: 'app-user-detail',
    templateUrl: './user-detail.component.html',
    styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {

    private userForm: FormGroup;

    constructor(private fb: FormBuilder,
                private router: Router,
                private userService: UserService) {
        // super(User);
        console.log('UserComponent constructor');
        this.createForm();
    }


    createForm() {
        console.log('UserComponent.createForm');
        this.userForm = this.fb.group({
            userName: [null, [Validators.max(20), Validators.required]],
            userPassword: [null, [Validators.max(30), Validators.required]],
        });
    }

    ngOnInit() {
    }


}

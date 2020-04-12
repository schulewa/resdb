import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Country} from '../model/entity/country';
import { ResdbUrlEndpoints } from '../resdb-url-endpoints';
import { User } from '../model/entity/user';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

    constructor(private router: Router, private http: HttpClient) { }

    findByLogonName(userName: string, password: string): Observable<User> {
        const body = JSON.stringify({userName, password});
        console.log('securityservice.login: post request to url ' + ResdbUrlEndpoints.LOGIN_URL + ' body=' + body);
        const headers = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
        return this.http.post<User>(ResdbUrlEndpoints.LOGIN_URL, body, {headers});
    }

    findAll(): Observable<User[]> {
        const url = ResdbUrlEndpoints.USER_URL;
        console.log('Invoking call to ' + url);
        return this.http.get<User[]>(url);
    }

    add(toBeSaved: User): Observable<User> {
        const url = ResdbUrlEndpoints.USER_URL;
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json; charset=utf-8'
            })
        };
        return this.http.post<User>(url, toBeSaved, httpOptions);
    }

    delete(user: User): Observable<User> {
        const url = this.constructUrlWithLogonName(ResdbUrlEndpoints.USER_URL, user.logonName);
        console.log('URL for marking user for deletion=[' + url + ']');
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json; charset=utf-8'
            })
        };
        console.log('UserService: marking ' + user.logonName + ' for deletion');
        return this.http.delete<User>(url, httpOptions);
    }

    private constructUrlWithLogonName(baseUrl: string, logonName: string): string {
        if (logonName) {
            return baseUrl + '/' + logonName;
        }
        return '';
    }

    update(toBeSaved: User): Observable<User> {
        const url = ResdbUrlEndpoints.USER_URL;
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json; charset=utf-8'
            })
        };
        return this.http.put<User>(url, toBeSaved, httpOptions);
    }

}

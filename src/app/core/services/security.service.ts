import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable} from 'rxjs';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';
import { User } from '../../model/entity/user';


@Injectable()
export class SecurityService {

  readonly CURRENT_USER = 'currentUser';

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  private authenticated: boolean;

  constructor(private router: Router, private http: HttpClient) { // , private ngxRolesService: NgxRolesService
    // this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem(this.CURRENT_USER)));
    // this.currentUser = this.currentUserSubject.asObservable();
    this.authenticated = false;
  }

  login(userName: string, password: string): Observable<User> {
    const body = JSON.stringify({userName, password});
    const headers = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
    return this.http.post<User>(ResdbUrlEndpoints.LOGIN_URL, body, {headers});
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem(this.CURRENT_USER);
    this.authenticated = false;
    this.router.navigate(['Login']); // TODO should this be 'login' ?
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  public get isAuthenticated(): boolean {
    return this.authenticated === true;
  }

}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';
import { catchError } from 'rxjs/operators';
import { Person } from '../../model/entity/person';
import {TaleType} from '../../model/entity/tale-type';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<Person[]> {
    console.log('PersonService.findAll');
    const url = ResdbUrlEndpoints.PERSON_URL;
    console.log('PersonService.findAll: initiating REST call to url ' + url);
    return this.http.get<Person[]>(url).pipe(catchError(this.handleError));
  }

  add(toBeSaved: Person): Observable<any> {
    const url = ResdbUrlEndpoints.PERSON_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.post(url, toBeSaved, httpOptions).pipe(catchError(this.handleError));
  }

  delete(person: Person): Observable<Person> {
    const url = this.constructUrlWithId(ResdbUrlEndpoints.PERSON_URL, person.id);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.delete<Person>(url, httpOptions);
  }

  private constructUrlWithId(baseUrl: string, id: number): string {
    if (id) {
      return baseUrl + '/' + id.toString();
    }
    return '';
  }

  update(toBeSaved: Person): Observable<any> {
    const url = ResdbUrlEndpoints.PERSON_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.put(url, toBeSaved, httpOptions).pipe(catchError(this.handleError));
  }

  private handleError(error: any) {
    const errmsg = error.message || error;
    console.log('Error: ' + errmsg);
    return of([]);
  }

}

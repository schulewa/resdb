import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';
import { catchError } from 'rxjs/operators';
import { DeityType } from '../../model/entity/deity-type';


@Injectable({
  providedIn: 'root'
})
export class DeityTypeService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<DeityType[]> {
    const url = ResdbUrlEndpoints.DEITY_TYPE_URL;
    return this.http.get<DeityType[]>(url).pipe(catchError(this.handleError));
  }

  add(toBeSaved: DeityType): Observable<DeityType> {
    const url = ResdbUrlEndpoints.DEITY_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.post<DeityType>(url, toBeSaved, httpOptions);
  }

  delete(deityType: DeityType): Observable<DeityType> {
    const url = this.constructUrlWithId(ResdbUrlEndpoints.DEITY_TYPE_URL, deityType.id);
    console.log('URL for marking deity type for deletion=[' + url + ']');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    console.log('DeityTypeService: marking ' + deityType.name + ' for deletion');
    return this.http.delete<DeityType>(url, httpOptions);
  }

  private constructUrlWithId(baseUrl: string, id: number): string {
    if (id) {
      return baseUrl + '/' + id.toString();
    }
    return '';
  }

  update(toBeSaved: DeityType): Observable<DeityType> {
    const url = ResdbUrlEndpoints.DEITY_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.put<DeityType>(url, toBeSaved, httpOptions);
  }

  private handleError(error: any) {
    const errmsg = error.message || error;
    console.log('Error: ' + errmsg);
    return of([]);
  }

}

import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of, throwError} from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AddressType } from '../../model/entity/address-type';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';



@Injectable({
  providedIn: 'root'
})
export class AddressTypeService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<AddressType[]> {
    console.log('AddressTypesService.findAll');
    const url = ResdbUrlEndpoints.ADDRESS_TYPE_URL;
    console.log('AddressTypesService.findAll: initiating REST call to url ' + url);
    return this.http.get<AddressType[]>(url).pipe(catchError(this.handleError));
  }

  // add(addressType: AddressType): Observable<AddressType> {
  //   const url = ResdbUrlEndpoints.ADDRESS_TYPE_URL;
  //   const httpOptions = {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/json; charset=utf-8'
  //     })
  //   };
  //   return this.http.post<AddressType>(url, addressType, httpOptions).pipe(catchError(this.handleError));
  // }
  //
  // update(addressType: AddressType): Observable<AddressType> {
  //   const url = ResdbUrlEndpoints.ADDRESS_TYPE_URL;
  //   const httpOptions = {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/json; charset=utf-8'
  //     })
  //   };
  //   return this.http.put<AddressType>(url, addressType, httpOptions).pipe(catchError(this.handleError));
  // }

  private handleError(error: any) {
    const errmsg = error.message || error;
    console.log('Error: ' + errmsg);
    // return throwError(error.message || error);
    return of([]);
  }

}

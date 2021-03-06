import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { AddressType } from '../../model/entity/address-type';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';



@Injectable({
  providedIn: 'root'
})
export class AddressTypeService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<AddressType[]> {
    const url = ResdbUrlEndpoints.ADDRESS_TYPE_URL;
    return this.http.get<AddressType[]>(url);
  }

  add(addressType: AddressType): Observable<AddressType> {
    const url = ResdbUrlEndpoints.ADDRESS_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.post<AddressType>(url, addressType, httpOptions);
  }

  delete(addressType: AddressType): Observable<AddressType> {
    const url = this.constructUrlWithId(ResdbUrlEndpoints.ADDRESS_TYPE_URL, addressType.id);
    console.log('URL for marking address type for deletion=[' + url + ']');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    console.log('AddressTypeService: marking ' + addressType.name + ' for deletion');
    return this.http.delete<AddressType>(url, httpOptions);
  }

  private constructUrlWithId(baseUrl: string, id: number): string {
    if (id) {
      return baseUrl + '/' + id.toString();
    }
    return '';
  }

  update(addressType: AddressType): Observable<AddressType> {
    const url = ResdbUrlEndpoints.ADDRESS_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.put<AddressType>(url, addressType, httpOptions);
  }

  private handleError(error: any) {
    const errmsg = error.message || error;
    console.log('Error: ' + errmsg);
    return of([]);
  }

}

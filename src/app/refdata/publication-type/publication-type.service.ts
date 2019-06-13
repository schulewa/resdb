import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';
import { PublicationType } from '../../model/entity/publication-type';
import {PersonType} from '../../model/entity/person-type';
import {MeasureType} from '../../model/entity/measure-type';

@Injectable({
  providedIn: 'root'
})
export class PublicationTypeService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<PublicationType[]> {
    console.log('PublicationTypeService.findAll');
    const url = ResdbUrlEndpoints.PUBLICATION_TYPE_URL;
    console.log('PublicationTypeService.findAll: initiating REST call to url ' + url);
    return this.http.get<PublicationType[]>(url);
  }

  add(toBeSaved: PublicationType): Observable<PublicationType> {
    const url = ResdbUrlEndpoints.PUBLICATION_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.post<PublicationType>(url, toBeSaved, httpOptions);
  }

  delete(publicationType: PublicationType): Observable<MeasureType> {
    const url = this.constructUrlWithId(ResdbUrlEndpoints.PUBLICATION_TYPE_URL, publicationType.id);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.delete<PublicationType>(url, httpOptions);
  }

  private constructUrlWithId(baseUrl: string, id: number): string {
    if (id) {
      return baseUrl + '/' + id.toString();
    }
    return '';
  }

  update(toBeSaved: PublicationType): Observable<PublicationType> {
    const url = ResdbUrlEndpoints.PUBLICATION_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.put<PublicationType>(url, toBeSaved, httpOptions);
  }

}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';
import { catchError } from 'rxjs/operators';
import { ArtefactGroup } from '../../model/entity/artefact-group';
import {AddressType} from '../../model/entity/address-type';

@Injectable({
  providedIn: 'root'
})
export class ArtefactGroupService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<ArtefactGroup[]> {
    console.log('ArtefactGroupService.findAll');
    const url = ResdbUrlEndpoints.ARTEFACT_GROUP_URL;
    console.log('ArtefactGroupService.findAll: initiating REST call to url ' + url);
    return this.http.get<ArtefactGroup[]>(url).pipe(catchError(this.handleError));
  }

  add(toBeSaved: ArtefactGroup): Observable<ArtefactGroup> {
    const url = ResdbUrlEndpoints.ARTEFACT_GROUP_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.post<ArtefactGroup>(url, toBeSaved, httpOptions);
  }

  delete(artefactGroup: ArtefactGroup): Observable<ArtefactGroup> {
    const url = this.constructUrlWithId(ResdbUrlEndpoints.ARTEFACT_GROUP_URL, artefactGroup.id);
    console.log('URL for marking artefact group for deletion=[' + url + ']');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    console.log('AddressTypeService: marking ' + artefactGroup.name + ' for deletion');
    return this.http.delete<ArtefactGroup>(url, httpOptions);
  }

  private constructUrlWithId(baseUrl: string, id: number): string {
    if (id) {
      return baseUrl + '/' + id.toString();
    }
    return '';
  }

  update(toBeSaved: ArtefactGroup): Observable<ArtefactGroup> {
    const url = ResdbUrlEndpoints.ARTEFACT_GROUP_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.put<ArtefactGroup>(url, toBeSaved, httpOptions);
  }

  private handleError(error: any) {
    const errmsg = error.message || error;
    console.log('Error: ' + errmsg);
    return of([]);
  }

}

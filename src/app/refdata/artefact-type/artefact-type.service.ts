import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';
import { catchError } from 'rxjs/operators';
import { ArtefactType } from '../../model/entity/artefact-type';

@Injectable({
  providedIn: 'root'
})
export class ArtefactTypeService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<ArtefactType[]> {
    const url = ResdbUrlEndpoints.ARTEFACT_TYPE_URL;
    return this.http.get<ArtefactType[]>(url).pipe(catchError(this.handleError));
  }

  add(toBeSaved: ArtefactType): Observable<ArtefactType> {
    const url = ResdbUrlEndpoints.ARTEFACT_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.post<ArtefactType>(url, toBeSaved, httpOptions);
  }

  delete(artefactType: ArtefactType): Observable<ArtefactType> {
    const url = this.constructUrlWithId(ResdbUrlEndpoints.ARTEFACT_TYPE_URL, artefactType.id);
    console.log('URL for marking artefact type for deletion=[' + url + ']');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    console.log('AddressTypeService: marking ' + artefactType.name + ' for deletion');
    return this.http.delete<ArtefactType>(url, httpOptions);
  }

  private constructUrlWithId(baseUrl: string, id: number): string {
    if (id) {
      return baseUrl + '/' + id.toString();
    }
    return '';
  }

  update(toBeSaved: ArtefactType): Observable<ArtefactType> {
    const url = ResdbUrlEndpoints.ARTEFACT_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.put<ArtefactType>(url, toBeSaved, httpOptions);
  }

  private handleError(error: any) {
    const errmsg = error.message || error;
    console.log('Error: ' + errmsg);
    return of([]);
  }

}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';
import { catchError } from 'rxjs/operators';
import { HierarchyType } from '../../model/entity/hierarchy-type';

@Injectable({
  providedIn: 'root'
})
export class HierarchyTypeService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<HierarchyType[]> {
    console.log('HierarchyTypeService.findAll');
    const url = ResdbUrlEndpoints.HIERARCHY_TYPE_URL;
    console.log('HierarchyTypeService.findAll: initiating REST call to url ' + url);
    return this.http.get<HierarchyType[]>(url).pipe(catchError(this.handleError));
  }

  add(toBeSaved: HierarchyType): Observable<HierarchyType> {
    const url = ResdbUrlEndpoints.HIERARCHY_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.post<HierarchyType>(url, toBeSaved, httpOptions);
  }

  delete(entityType: HierarchyType): Observable<HierarchyType> {
    const url = this.constructUrlWithId(ResdbUrlEndpoints.HIERARCHY_TYPE_URL, entityType.id);
    console.log('URL for marking hierarchy type group for deletion=[' + url + ']');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.delete<HierarchyType>(url, httpOptions);
  }

  private constructUrlWithId(baseUrl: string, id: number): string {
    if (id) {
      return baseUrl + '/' + id.toString();
    }
    return '';
  }

  update(toBeSaved: HierarchyType): Observable<HierarchyType> {
    const url = ResdbUrlEndpoints.HIERARCHY_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.put<HierarchyType>(url, toBeSaved, httpOptions);
  }

  private handleError(error: any) {
    const errmsg = error.message || error;
    console.log('Error: ' + errmsg);
    return of([]);
  }

}

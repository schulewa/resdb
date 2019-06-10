import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';
import { catchError } from 'rxjs/operators';
import { EntityType } from '../../model/entity/entity-type';


@Injectable({
  providedIn: 'root'
})
export class EntityTypeService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<EntityType[]> {
    const url = ResdbUrlEndpoints.ENTITY_TYPE_URL;
    return this.http.get<EntityType[]>(url).pipe(catchError(this.handleError));
  }

  add(toBeSaved: EntityType): Observable<EntityType> {
    const url = ResdbUrlEndpoints.ENTITY_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.post<EntityType>(url, toBeSaved, httpOptions);
  }

  delete(entityType: EntityType): Observable<EntityType> {
    const url = this.constructUrlWithId(ResdbUrlEndpoints.ENTITY_TYPE_URL, entityType.id);
    console.log('URL for marking entity type for deletion=[' + url + ']');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    console.log('EntityTypeService: marking ' + entityType.name + ' for deletion');
    return this.http.delete<EntityType>(url, httpOptions);
  }

  private constructUrlWithId(baseUrl: string, id: number): string {
    if (id) {
      return baseUrl + '/' + id.toString();
    }
    return '';
  }

  update(toBeSaved: EntityType): Observable<EntityType> {
    const url = ResdbUrlEndpoints.ENTITY_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.put<EntityType>(url, toBeSaved, httpOptions);
  }

  private handleError(error: any) {
    const errmsg = error.message || error;
    console.log('Error: ' + errmsg);
    return of([]);
  }

}

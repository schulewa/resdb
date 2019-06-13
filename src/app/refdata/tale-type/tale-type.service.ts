import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';
import { TaleType } from '../../model/entity/tale-type';

@Injectable({
  providedIn: 'root'
})
export class TaleTypeService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<TaleType[]> {
    const url = ResdbUrlEndpoints.TALE_TYPE_URL;
    return this.http.get<TaleType[]>(url);
  }

  add(toBeSaved: TaleType): Observable<TaleType> {
    const url = ResdbUrlEndpoints.TALE_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.post<TaleType>(url, toBeSaved, httpOptions);
  }

  delete(taleType: TaleType): Observable<TaleType> {
    const url = this.constructUrlWithId(ResdbUrlEndpoints.TALE_TYPE_URL, taleType.id);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.delete<TaleType>(url, httpOptions);
  }

  private constructUrlWithId(baseUrl: string, id: number): string {
    if (id) {
      return baseUrl + '/' + id.toString();
    }
    return '';
  }

  update(toBeSaved: TaleType): Observable<TaleType> {
    const url = ResdbUrlEndpoints.TALE_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.put<TaleType>(url, toBeSaved, httpOptions);
  }

}

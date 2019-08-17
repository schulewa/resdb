import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';
import { Title } from '../../model/entity/title';
import {TitleDto} from '../../model/dto/TitleDto';


@Injectable({
  providedIn: 'root'
})
export class TitlesService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<TitleDto[]> {
    const url = ResdbUrlEndpoints.TITLE_URL;
    return this.http.get<TitleDto[]>(url);
  }

  add(toBeSaved: Title): Observable<Title> {
    const url = ResdbUrlEndpoints.TITLE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.post<Title>(url, toBeSaved, httpOptions);
  }

  delete(title: Title): Observable<Title> {
    const url = this.constructUrlWithId(ResdbUrlEndpoints.TITLE_URL, title.id);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.delete<Title>(url, httpOptions);
  }

  private constructUrlWithId(baseUrl: string, id: number): string {
    if (id) {
      return baseUrl + '/' + id.toString();
    }
    return '';
  }

  update(toBeSaved: Title): Observable<Title> {
    const url = ResdbUrlEndpoints.TITLE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.put<Title>(url, toBeSaved, httpOptions);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';
import { RaceType } from '../../model/entity/race-type';

@Injectable({
  providedIn: 'root'
})
export class RaceTypeService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<RaceType[]> {
    const url = ResdbUrlEndpoints.RACE_TYPE_URL;
    return this.http.get<RaceType[]>(url);
  }

  add(toBeSaved: RaceType): Observable<RaceType> {
    const url = ResdbUrlEndpoints.RACE_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.post<RaceType>(url, toBeSaved, httpOptions);
  }

  delete(raceType: RaceType): Observable<RaceType> {
    const url = this.constructUrlWithId(ResdbUrlEndpoints.RACE_TYPE_URL, raceType.id);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.delete<RaceType>(url, httpOptions);
  }

  private constructUrlWithId(baseUrl: string, id: number): string {
    if (id) {
      return baseUrl + '/' + id.toString();
    }
    return '';
  }

  update(toBeSaved: RaceType): Observable<RaceType> {
    const url = ResdbUrlEndpoints.RACE_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.put<RaceType>(url, toBeSaved, httpOptions);
  }

}

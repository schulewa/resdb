import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';
import { TechnologyTypeGroup } from '../../model/entity/technology-type-group';
import {RaceType} from '../../model/entity/race-type';

@Injectable({
  providedIn: 'root'
})
export class TechnologyTypeGroupService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<TechnologyTypeGroup[]> {
    const url = ResdbUrlEndpoints.TECHNOLOGY_TYPE_GROUP_URL;
    return this.http.get<TechnologyTypeGroup[]>(url);
  }

  add(toBeSaved: TechnologyTypeGroup): Observable<TechnologyTypeGroup> {
    const url = ResdbUrlEndpoints.TECHNOLOGY_TYPE_GROUP_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.post<TechnologyTypeGroup>(url, toBeSaved, httpOptions);
  }

  delete(technologyTypeGroup: TechnologyTypeGroup): Observable<RaceType> {
    const url = this.constructUrlWithId(ResdbUrlEndpoints.TECHNOLOGY_TYPE_GROUP_URL, technologyTypeGroup.id);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.delete<TechnologyTypeGroup>(url, httpOptions);
  }

  private constructUrlWithId(baseUrl: string, id: number): string {
    if (id) {
      return baseUrl + '/' + id.toString();
    }
    return '';
  }

  update(toBeSaved: TechnologyTypeGroup): Observable<TechnologyTypeGroup> {
    const url = ResdbUrlEndpoints.TECHNOLOGY_TYPE_GROUP_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.put<TechnologyTypeGroup>(url, toBeSaved, httpOptions);
  }

}

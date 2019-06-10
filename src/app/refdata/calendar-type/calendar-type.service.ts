import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ResdbUrlEndpoints } from '../../resdb-url-endpoints';
import { catchError } from 'rxjs/operators';
import { CalendarType } from '../../model/entity/calendar-type';


@Injectable({
  providedIn: 'root'
})
export class CalendarTypeService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<CalendarType[]> {
    console.log('CalendarTypeService.findAll');
    const url = ResdbUrlEndpoints.CALENDAR_TYPE_URL;
    console.log('CalendarTypeService.findAll: initiating REST call to url ' + url);
    return this.http.get<CalendarType[]>(url).pipe(catchError(this.handleError));
  }

  add(toBeSaved: CalendarType): Observable<CalendarType> {
    const url = ResdbUrlEndpoints.CALENDAR_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.post<CalendarType>(url, toBeSaved, httpOptions);
  }

  delete(calendarType: CalendarType): Observable<CalendarType> {
    const url = this.constructUrlWithId(ResdbUrlEndpoints.CALENDAR_TYPE_URL, calendarType.id);
    console.log('URL for marking calendar type for deletion=[' + url + ']');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    console.log('CalendarTypeService: marking ' + calendarType.name + ' for deletion');
    return this.http.delete<CalendarType>(url, httpOptions);
  }

  private constructUrlWithId(baseUrl: string, id: number): string {
    if (id) {
      return baseUrl + '/' + id.toString();
    }
    return '';
  }

  update(toBeSaved: CalendarType): Observable<CalendarType> {
    const url = ResdbUrlEndpoints.CALENDAR_TYPE_URL;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8'
      })
    };
    return this.http.put<CalendarType>(url, toBeSaved, httpOptions);
  }

  private handleError(error: any) {
    const errmsg = error.message || error;
    console.log('Error: ' + errmsg);
    return of([]);
  }

}

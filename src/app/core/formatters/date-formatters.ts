import {formatDate} from '@angular/common';

export class DateFormatters {

  public static dateWithTimeAsString(params): string {
    if (params && params.value ) {
      return formatDate(params.value, 'dd MMM yyyy HH:MM', navigator.language);
    } else {
      return null;
    }
  }
}

import { HistoricalDate } from './historical-date';
import { Title } from './title';
import { Place} from './place';
import { DataAction } from '../../refdata/data-action';
import { DataStatus } from '../../refdata/data-status';
import { PersonTitle } from './person-title';
import {IAuditedDataType} from './interfaces/audited-data-type';

export class Person implements IAuditedDataType {

  id: number;
  firstName: string;
  middleName: string;
  familyName: string;
  dateOfBirth: HistoricalDate;
  dateOfDeath: HistoricalDate;
  prefixTitle: Title;
  suffixTitle: Title;
  birthPlace: Place;
  deathPlace: Place;

  status: DataStatus;
  createdBy: string;
  updatedBy: string;
  lastUpdated: Date;
  selected: boolean;
  action: DataAction;
  isDataChanged: boolean;

  titles: PersonTitle[];

}

import { HistoricalDate } from './historical-date';
import { Title } from './title';
import { Place} from './place';
import { DataAction } from '../../core/model/data-action';
import { DataStatus } from '../../core/model/data-status';
import { PersonTitle } from './person-title';
import {IAuditedDataType} from './interfaces/audited-data-type';
import {CodeLabel} from './code-label';
import {IAuditedNameDataType} from './interfaces/audited-name-data-type';

export class Person implements IAuditedDataType {

  id: number;
  firstName: string;
  middleName: string;
  familyName: string;
  gender: CodeLabel;
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

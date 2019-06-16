import { Person } from './person';
import { Title } from './title';
import { DataStatus } from '../../refdata/data-status';
import { DataAction } from '../../refdata/data-action';

export class PersonTitle {

  person: Person;
  title: Title;

  status: DataStatus;
  createdBy: string;
  updatedBy: string;
  lastUpdated: Date;
  selected: boolean;
  action: DataAction;
  isDataChanged: boolean;

}

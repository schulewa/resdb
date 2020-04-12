import { TitleType } from './title-type';
import {DataStatus} from '../../core/model/data-status';
import {DataAction} from '../../core/model/data-action';
import {Gender} from './gender';
import {IAuditedDataType} from './interfaces/audited-data-type';
import {CodeLabel} from './code-label';

export class Title implements IAuditedDataType {

  id: number;
  title: string;
  description: string;
  titleType: TitleType;
  appliesTo: Gender; // CodeLabel;
  status: DataStatus;
  createdBy: string;
  updatedBy: string;
  lastUpdated: Date;
  selected: boolean;
  action: DataAction;
  isDataChanged: boolean;

  constructor() {
    this.isDataChanged = false;
    this.selected = false;
    this.id = 0;
    this.title = '';
    this.action = DataAction.Add;
    this.status = DataStatus.New;
  }

  public toString(): string {
    return 'Title: id=' + this.id + ' title=' + this.title + ' description=' + this.description + ' titleType=' + this.titleType +
      ' appliesTo=' + this.appliesTo +
      ' status=' + this.status + ' createdBy=' + this.createdBy +
      ' updatedBy=' + this.updatedBy + ' lastUpdated=' + this.lastUpdated + ' selected=' + this.selected + ' action=' + this.action +
      ' isDataChanged=' + this.isDataChanged;
  }
}

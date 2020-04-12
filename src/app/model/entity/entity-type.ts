import { DataStatus } from '../../core/model/data-status';
import { IAuditedNameDataType } from './interfaces/audited-name-data-type';
import { DataAction } from '../../core/model/data-action';

export class EntityType implements IAuditedNameDataType {

  id: number;
  name: string;
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
    this.name = '';
    this.action = DataAction.Add;
    this.status = DataStatus.New;
  }

  public toString(): string {
    return 'EntityType: id=' + this.id + ' name=' + this.name + ' status=' + this.status + ' createdBy=' + this.createdBy +
      ' updatedBy=' + this.updatedBy + ' lastUpdated=' + this.lastUpdated + ' selected=' + this.selected + ' action=' + this.action +
      ' isDataChanged=' + this.isDataChanged;
  }

}

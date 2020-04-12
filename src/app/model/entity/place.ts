import {River} from './river';
import {IDataChanged} from './interfaces/data-changed';
import {IAuditedNameDataType} from './interfaces/audited-name-data-type';
import {DataStatus} from '../../core/model/data-status';
import {DataAction} from '../../core/model/data-action';

export class Place implements IAuditedNameDataType {

    id: number;
    name: string;
    latitude: string;
    longitude: string;
    altitude: string;
    river: River;
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

    isSaveButtonEnabled(): boolean {
        return this.isDataChanged;
    }

    public toString(): string {
        return 'Place: id=' + this.id + ' name=' + this.name +
            ' latitude=' + this.latitude + ' longitude=' + this.longitude + ' altitude=' + this.altitude +
            ' status=' + this.status + ' createdBy=' + this.createdBy +
            ' updatedBy=' + this.updatedBy + ' lastUpdated=' + this.lastUpdated + ' selected=' + this.selected + ' action=' + this.action +
            ' isDataChanged=' + this.isDataChanged;
    }
}

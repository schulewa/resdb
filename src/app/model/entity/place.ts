import { River } from './river';
import { IDataChanged } from './interfaces/data-changed';

export class Place implements IDataChanged {
  id: number;
  name: string;
  latitude: string;
  longitude: string;
  altitude: string;
  river: River;

  isDataChanged: boolean;

  constructor() {
    this.isDataChanged = false;
  }

  isSaveButtonEnabled(): boolean {
    return this.isDataChanged;
  }

}

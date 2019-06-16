import { IDataChanged } from './interfaces/data-changed';

export class River implements IDataChanged {
  id: number;
  name: string;

  isDataChanged: boolean;

  constructor() {
    this.isDataChanged = false;
  }

  isSaveButtonEnabled(): boolean {
    return this.isDataChanged;
  }

}

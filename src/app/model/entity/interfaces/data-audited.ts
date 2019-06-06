import { DataStatus } from '../../../refdata/data-status';

export interface IDataAudited {
  status: DataStatus;
  createdBy: string;
  updatedBy: string;
  lastUpdated: Date;
}

import { DataStatus } from '../../../core/model/data-status';

export interface IDataAudited {
  status: DataStatus;
  createdBy: string;
  updatedBy: string;
  lastUpdated: Date;
}

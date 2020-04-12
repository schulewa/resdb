import {PermissionStatus} from '../../../main/java/com/apschulewitz/resdb/security/model/entity/permission-status';
import {OperationType} from './operation-type';

export class Permission {
    id: number;
    name: string;
    description: string;
    status: PermissionStatus;
    operationType: OperationType;
}

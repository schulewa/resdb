import {UserGroupPermission} from './user-group-permission';
import {AccountStatus} from './account-status';

export class UserGroup {
    id: number;
    name: string;
    displayName: string;
    status: AccountStatus;
    groupPermissions: UserGroupPermission;
}

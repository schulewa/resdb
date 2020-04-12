import { UserGroup } from './user-group';
import { Permission } from './permission';

export class UserGroupPermission {
    id: number;
    group: UserGroup;
    permission: Permission;
}

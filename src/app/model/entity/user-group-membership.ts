import { User } from './user';
import {UserGroup} from './user-group';

export class UserGroupMembership {
    id: number;
    user: User;
    group: UserGroup;
    validFrom: Date;
    validTo: Date;
}

import { DataAction } from '../../core/model/data-action';
import { DataStatus } from '../../core/model/data-status';
import { IAuditedDataType } from './interfaces/audited-data-type';
import { AuthenticationResult } from './authentication-result';
import { UserGroupMembership } from './user-group-membership';

export class User implements IAuditedDataType {
    firstName: string;
    familyName: string;
    groupMemberships: UserGroupMembership[];
    id: number;
    loginPassword: string;
    logonName: string;
    preferredLanguage: string;
    jwtToken: string;
    status: DataStatus;
    createdBy: string;
    updatedBy: string;
    lastUpdated: Date;
    selected: boolean;
    action: DataAction;
    isDataChanged: boolean;
    invalidAccessCount: number;
    authenticationResult: AuthenticationResult;
    passwordUpdated: Date;
    lastLogon: Date;
    sessionId: string;

    constructor() {
        this.isDataChanged = false;
        this.selected = false;
        this.id = 0;
        this.action = DataAction.Add;
        this.status = DataStatus.New;
        this.invalidAccessCount = 0;
    }

    public toString(): string {
        return 'User: logonName=' + this.logonName +
            ' loginPassword=******' + ' firstName=' + this.firstName + ' familyName=' + this.familyName;
    }

}

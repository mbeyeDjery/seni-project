import {IAppRole} from "../app-role-model";

export interface IAuthResponse {
    accessToken: string;
    expiresIn: number;
    refreshExpiresIn: number;
    refreshToken: string;
    tokenType: string;
    notBeforePolicy: number;
    sessionState: string;
    scope: string;
    roles: IAppRole[];
}
export function convertToAuthResponse(val: any): IAuthResponse {
    let authResponse: IAuthResponse;
    return authResponse = {
        accessToken: val['access_token'],
        expiresIn: null,
        refreshExpiresIn: null,
        refreshToken: val['refresh_token'],
        tokenType: '',
        notBeforePolicy: null,
        sessionState: '',
        scope: '',
        roles: [],
    };
}

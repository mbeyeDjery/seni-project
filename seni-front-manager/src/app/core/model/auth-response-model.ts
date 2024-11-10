import {AppRole} from "./app-role-model";

export interface AuthResponse {
    accessToken: string;
    expiresIn: number;
    refreshExpiresIn: number;
    refreshToken: string;
    tokenType: string;
    notBeforePolicy: number;
    sessionState: string;
    scope: string;
    roles: AppRole[];
}
export function convertToAuthResponse(val: any): AuthResponse {
    let authResponse: AuthResponse;
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

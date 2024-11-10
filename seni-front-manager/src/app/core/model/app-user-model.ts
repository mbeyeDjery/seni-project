import {AppRole} from "./app-role-model";

export interface AppUser {
    username: string
    password: string
    firstName: string
    lastName: string
    email: string
    telephone: string
    enabled: boolean
    imageUrl: string
    roles: AppRole[]
}

export function convertToAppUser(val: any): AppUser {
    let appUser: AppUser;
    return appUser = {
        username: val['username'] ?? '',
        password: val['password'] ?? '',
        firstName: val['firstName'] ?? '',
        lastName: val['lastName'] ?? '',
        email: val['email'] ?? '',
        telephone: val['telephone'] ?? '',
        enabled: val['enabled'] ?? '',
        imageUrl: val['imageUrl'] ?? '',
        roles: null,
    };
}

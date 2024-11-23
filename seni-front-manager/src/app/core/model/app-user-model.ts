import {IAppRole} from "./app-role-model";

export interface IAppUser {
    username: string
    password: string
    firstName: string
    lastName: string
    email: string
    telephone: string
    enabled: boolean
    imageUrl: string
    roles: IAppRole[]
}

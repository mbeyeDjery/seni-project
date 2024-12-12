import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ApplicationConfigService} from "../config/application-config.service";
import {Observable} from "rxjs";
import {IAppUser} from "../model/app-user-model";
import {IAddRemoveRole} from "../model/request/add-remove-role-model";
import {AUTH_GESTIONNAIRE_SERVER} from "../utils/constants";

@Injectable({
  providedIn: 'root'
})
export class UserGestionnaireService {
    private http = inject(HttpClient);
    private applicationConfigService = inject(ApplicationConfigService);
    private serverApiUrl = this.applicationConfigService.getEndpointFor('/users', AUTH_GESTIONNAIRE_SERVER);

    createUser(appUser: IAppUser): Observable<IAppUser> {
        return this.http.post<IAppUser>(this.serverApiUrl, appUser);
    }

    updateUser(appUser: IAppUser): Observable<IAppUser> {
        console.warn(appUser);
        return this.http.put<IAppUser>(this.serverApiUrl, appUser);
    }

    deleteUser(idUser: number): Observable<void> {
        return this.http.delete<void>(`${this.serverApiUrl}/${idUser}`);
    }

    getAllUsers(): Observable<IAppUser[]>{
        return this.http.get<IAppUser[]>(this.serverApiUrl);
    }

    getUser(idUser: number): Observable<IAppUser>{
        return this.http.get<IAppUser>(`${this.serverApiUrl}/${idUser}`);
    }

    searchUserByUsername(username: string): Observable<IAppUser[]>{
        return this.http.get<IAppUser[]>(`${this.serverApiUrl}/username/${username}`);
    }

    searchUserByEmail(email: string): Observable<IAppUser[]>{
        return this.http.get<IAppUser[]>(`${this.serverApiUrl}/email/${email}`);
    }

    searchUserByPhone(telephone: string): Observable<IAppUser[]>{
        return this.http.get<IAppUser[]>(`${this.serverApiUrl}/telephone/${telephone}`);
    }

    searchUserByEnabled(enabled: boolean): Observable<IAppUser[]>{
        return this.http.get<IAppUser[]>(`${this.serverApiUrl}/enabled/${enabled}`);
    }

    addRoleToUser(addRemoveRoleRequest: IAddRemoveRole): Observable<void> {
        return this.http.put<void>(`${this.serverApiUrl}/add-role`, addRemoveRoleRequest);
    }

    removeRoleFromUser(addRemoveRoleRequest: IAddRemoveRole): Observable<void> {
        return this.http.put<void>(`${this.serverApiUrl}/remove-role`, addRemoveRoleRequest);
    }
}

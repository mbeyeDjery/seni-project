import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ApplicationConfigService} from "../config/application-config.service";
import {Observable} from "rxjs";
import {AUTH_MANAGER_SERVER} from "../utils/constants";
import {AppUser} from "../model/app-user-model";
import {AddRemoveRole} from "../model/add-remove-role-model";

@Injectable({
  providedIn: 'root'
})
export class AppUserService {
    private http = inject(HttpClient);
    private applicationConfigService = inject(ApplicationConfigService);
    private serverApiUrl = this.applicationConfigService.getEndpointFor('/users', AUTH_MANAGER_SERVER);

    createUser(appUser: AppUser): Observable<AppUser> {
        return this.http.post<AppUser>(this.serverApiUrl, appUser);
    }

    updateUser(appUser: AppUser): Observable<AppUser> {
        return this.http.put<AppUser>(this.serverApiUrl, appUser);
    }

    deleteUser(idUser: number): Observable<void> {
        return this.http.delete<void>(`${this.serverApiUrl}/${idUser}`);
    }

    getAllUsers(): Observable<AppUser[]>{
        return this.http.get<AppUser[]>(this.serverApiUrl);
    }

    getUser(idUser: number): Observable<AppUser>{
        return this.http.get<AppUser>(`${this.serverApiUrl}/${idUser}`);
    }

    searchUserByUsername(username: string): Observable<AppUser[]>{
        return this.http.get<AppUser[]>(`${this.serverApiUrl}/username/${username}`);
    }

    searchUserByEmail(email: string): Observable<AppUser[]>{
        return this.http.get<AppUser[]>(`${this.serverApiUrl}/email/${email}`);
    }

    searchUserByPhone(telephone: string): Observable<AppUser[]>{
        return this.http.get<AppUser[]>(`${this.serverApiUrl}/telephone/${telephone}`);
    }

    searchUserByEnabled(enabled: boolean): Observable<AppUser[]>{
        return this.http.get<AppUser[]>(`${this.serverApiUrl}/enabled/${enabled}`);
    }

    addRoleToUser(addRemoveRoleRequest: AddRemoveRole): Observable<void> {
        return this.http.put<void>(`${this.serverApiUrl}/add-role`, addRemoveRoleRequest);
    }

    removeRoleFromUser(addRemoveRoleRequest: AddRemoveRole): Observable<void> {
        return this.http.put<void>(`${this.serverApiUrl}/remove-role`, addRemoveRoleRequest);
    }
}

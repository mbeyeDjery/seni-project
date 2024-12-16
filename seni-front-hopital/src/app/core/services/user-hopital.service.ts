import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ApplicationConfigService} from "../config/application-config.service";
import {Observable} from "rxjs";
import {AUTH_HOPITAL_SERVER} from "../utils/constants";
import {IAppUser} from "../model/app-user-model";

@Injectable({
  providedIn: 'root'
})
export class UserHopitalService {
    private http = inject(HttpClient);
    private applicationConfigService = inject(ApplicationConfigService);
    private serverApiUrl = this.applicationConfigService.getEndpointFor('/users', AUTH_HOPITAL_SERVER);

    createUser(appUser: IAppUser): Observable<IAppUser> {
        return this.http.post<IAppUser>(this.serverApiUrl, appUser);
    }

    updateUser(appUser: IAppUser): Observable<IAppUser> {
        return this.http.put<IAppUser>(this.serverApiUrl, appUser);
    }

    deleteUser(idUser: number): Observable<void> {
        return this.http.delete<void>(`${this.serverApiUrl}/${idUser}`);
    }

    getAllUsers(idHopital: string): Observable<IAppUser[]>{
        return this.http.get<IAppUser[]>(`${this.serverApiUrl}/${idHopital}`);
    }
}

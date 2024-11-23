import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ApplicationConfigService} from "../config/application-config.service";
import {Observable} from "rxjs";
import {AUTH_MANAGER_SERVER} from "../utils/constants";
import {IAppUser} from "../model/app-user-model";
import {IChangePasswordRequest} from "../model/change-password-model";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

    private http = inject(HttpClient);
    private applicationConfigService = inject(ApplicationConfigService);
    private serverApiUrl = this.applicationConfigService.getEndpointFor('/account', AUTH_MANAGER_SERVER);

    updateUser(appUser: IAppUser): Observable<IAppUser> {
        return this.http.put<IAppUser>(this.serverApiUrl, appUser);
    }

    changePassword(chagePasswordRequest: IChangePasswordRequest): Observable<void> {
        return this.http.put<void>(`${this.serverApiUrl}/change-password`, chagePasswordRequest);
    }
}

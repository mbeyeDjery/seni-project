import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ApplicationConfigService} from "../config/application-config.service";
import {Observable} from "rxjs";
import {AUTH_MANAGER_SERVER} from "../utils/constants";
import {AppUser} from "../model/app-user-model";
import {ChangePasswordRequest} from "../model/change-password-model";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

    private http = inject(HttpClient);
    private applicationConfigService = inject(ApplicationConfigService);
    private serverApiUrl = this.applicationConfigService.getEndpointFor('/account', AUTH_MANAGER_SERVER);

    updateUser(appUser: AppUser): Observable<AppUser> {
        return this.http.put<AppUser>(this.serverApiUrl, appUser);
    }

    changePassword(chagePasswordRequest: ChangePasswordRequest): Observable<void> {
        return this.http.put<void>(`${this.serverApiUrl}/change-password`, chagePasswordRequest);
    }
}

import { Injectable, inject } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {AuthStorageService} from "./auth-storage.service";
import {ApplicationConfigService} from "../../config/application-config.service";
import {AUTH_MANAGER_SERVER} from "../../utils/constants";
import {IAuthResponse} from "../../model/auth-response-model";
import {IAuthRequest} from "../../model/auth-request-model";

@Injectable({
  providedIn: 'root'
})
export class AuthProviderService {
  private http = inject(HttpClient);
  private stateStorageService = inject(AuthStorageService);
  private applicationConfigService = inject(ApplicationConfigService);
  private serverApiUrl = this.applicationConfigService.getEndpointFor('/auth', AUTH_MANAGER_SERVER);

  getToken(): IAuthResponse | null {
    return this.stateStorageService.getAuthenticationToken() ?? null;
  }

  login(authRequest: IAuthRequest): Observable<void> {
    return this.http
        .post<IAuthResponse>(`${this.serverApiUrl}/login`, authRequest)
        .pipe(map(response => this.authenticateSuccess(response, authRequest.rememberMe)));
  }

  refreshToken(authRequest: IAuthRequest): Observable<void> {
    return this.http
        .post<IAuthResponse>(`${this.serverApiUrl}/refresh`, authRequest)
        .pipe(map(response => this.authenticateSuccess(response, authRequest.rememberMe)));
  }

  logout(): Observable<void> {
    return new Observable(observer => {
      this.stateStorageService.clearAuthenticationToken();
      observer.complete();
    });
  }

  private authenticateSuccess(response: IAuthResponse, rememberMe: boolean): void {
    this.stateStorageService.storeAuthenticationToken(response, rememberMe);
  }
}

import { Injectable, inject } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {AuthStorageService} from "./auth-storage.service";
import {ApplicationConfigService} from "../../config/application-config.service";
import {AUTH_MANAGER_SERVER} from "../../utils/constants";
import {AuthResponse} from "../../model/auth-response-model";
import {AuthRequest} from "../../model/auth-request-model";

@Injectable({
  providedIn: 'root'
})
export class AuthProviderService {
  private http = inject(HttpClient);
  private stateStorageService = inject(AuthStorageService);
  private applicationConfigService = inject(ApplicationConfigService);
  private serverApiUrl = this.applicationConfigService.getEndpointFor('/auth', AUTH_MANAGER_SERVER);

  getToken(): AuthResponse | null {
    return this.stateStorageService.getAuthenticationToken() ?? null;
  }

  login(authRequest: AuthRequest): Observable<void> {
    return this.http
        .post<AuthResponse>(`${this.serverApiUrl}/login`, authRequest)
        .pipe(map(response => this.authenticateSuccess(response, authRequest.rememberMe)));
  }

  refreshToken(authRequest: AuthRequest): Observable<void> {
    return this.http
        .post<AuthResponse>(`${this.serverApiUrl}/refresh`, authRequest)
        .pipe(map(response => this.authenticateSuccess(response, authRequest.rememberMe)));
  }

  logout(): Observable<void> {
    return new Observable(observer => {
      this.stateStorageService.clearAuthenticationToken();
      observer.complete();
    });
  }

  private authenticateSuccess(response: AuthResponse, rememberMe: boolean): void {
    this.stateStorageService.storeAuthenticationToken(response, rememberMe);
  }
}

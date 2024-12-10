import {inject, Injectable} from '@angular/core';
import {LocalStorageService} from "../storage/local-storage.service";
import {SessionStorageService} from "../storage/session-storage.service";
import {SENI_ADMIN_AUTH_TOKEN, STORE_URL} from "../../utils/constants";
import {IAuthResponse, convertToAuthResponse} from "../../model/request/auth-response-model";

@Injectable({
  providedIn: 'root'
})
export class AuthStorageService {
  private localStorageService = inject(LocalStorageService);
  private  sessionStorageService = inject(SessionStorageService);

  storeUrl(url: string): void {
    this.sessionStorageService.setItem(STORE_URL, JSON.stringify(url));
  }

  getUrl(): string | null {
    const previousUrl = this.sessionStorageService.getItem(STORE_URL);
    return this.sessionStorageService.getItem(STORE_URL) ? (JSON.parse(previousUrl) as string | null) : previousUrl;
  }

  clearUrl(): void {
    this.sessionStorageService.removeItem(STORE_URL);
  }

  storeAuthenticationToken(authResponse: IAuthResponse, rememberMe: boolean): void {
    this.clearAuthenticationToken();
    if (rememberMe) {
      this.localStorageService.setItem(SENI_ADMIN_AUTH_TOKEN, JSON.stringify(authResponse));
    } else {
      this.sessionStorageService.setItem(SENI_ADMIN_AUTH_TOKEN, JSON.stringify(authResponse));
    }
  }

  getAuthenticationToken(): IAuthResponse | null {
    const authenticationToken = this.localStorageService.getItem(SENI_ADMIN_AUTH_TOKEN) ?? this.sessionStorageService.getItem(SENI_ADMIN_AUTH_TOKEN);
    return authenticationToken ? convertToAuthResponse(JSON.parse(authenticationToken)) : null;
  }
  clearAuthenticationToken(): void {
    this.localStorageService.removeItem(SENI_ADMIN_AUTH_TOKEN);
    this.sessionStorageService.removeItem(SENI_ADMIN_AUTH_TOKEN);
  }
}

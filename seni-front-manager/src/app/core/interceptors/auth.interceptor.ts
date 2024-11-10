import { Injectable, inject } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthStorageService} from "../services/auth/auth-storage.service";
import {ApplicationConfigService} from "../config/application-config.service";
import {AUTH_MANAGER_SERVER} from "../utils/constants";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    private readonly authStorageService = inject(AuthStorageService);
    private readonly applicationConfigService = inject(ApplicationConfigService);

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const serverApiUrl = this.applicationConfigService.getEndpointFor('/auth', AUTH_MANAGER_SERVER);

        if (request.url.startsWith(serverApiUrl)){
            return next.handle(request);
        }

        const authResponse= this.authStorageService.getAuthenticationToken();
        if (authResponse) {
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${authResponse.accessToken}`,
                },
            });
        }
        return next.handle(request);
    }
}

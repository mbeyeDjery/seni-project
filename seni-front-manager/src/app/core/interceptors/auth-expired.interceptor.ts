import { Injectable, inject } from '@angular/core';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import {LoginService} from "../services/auth/login.service";
import {AuthStorageService} from "../services/auth/auth-storage.service";

@Injectable()
export class AuthExpiredInterceptor implements HttpInterceptor {
    private readonly router = inject(Router);
    private readonly loginService = inject(LoginService);
    private readonly authStorageService = inject(AuthStorageService);

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(
            tap({
                error: (err: HttpErrorResponse) => {
                    if (err.status === 401 && err.url && !err.url.includes('api/auth/login')) {
                        this.authStorageService.storeUrl(this.router.routerState.snapshot.url);
                        this.loginService.logout();
                        this.router.navigate(['/auth/login']).then(r => {});
                    }
                },
            }),
        );
    }

}

import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import {UserService} from "./user.service";
import {AuthProviderService} from "./auth-provider.service";
import {AuthRequest} from "../../model/auth-request-model";
import {AppUser} from "../../model/app-user-model";

@Injectable({ providedIn: 'root' })
export class LoginService {
    private userService = inject(UserService);
    private authProviderService = inject(AuthProviderService);

    login(authRequest: AuthRequest): Observable<AppUser | null> {
        return this.authProviderService.login(authRequest).pipe(mergeMap(() => this.userService.identity(true)));
    }

    refreshToken(authRequest: AuthRequest): Observable<AppUser | null> {
        return this.authProviderService.refreshToken(authRequest).pipe(mergeMap(() => this.userService.identity(true)));
    }

    logout(): void {
        this.authProviderService.logout().subscribe({ complete: () => this.userService.authenticate(null) });
    }
}

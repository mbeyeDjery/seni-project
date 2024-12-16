import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import {UserService} from "./user.service";
import {AuthProviderService} from "./auth-provider.service";
import {IAuthRequest} from "../../model/request/auth-request-model";
import {IAppUser} from "../../model/app-user-model";

@Injectable({ providedIn: 'root' })
export class LoginService {
    private userService = inject(UserService);
    private authProviderService = inject(AuthProviderService);

    login(authRequest: IAuthRequest): Observable<IAppUser | null> {
        return this.authProviderService.login(authRequest).pipe(mergeMap(() => this.userService.identity(true)));
    }

    refreshToken(authRequest: IAuthRequest): Observable<IAppUser | null> {
        return this.authProviderService.refreshToken(authRequest).pipe(mergeMap(() => this.userService.identity(true)));
    }

    logout(): void {
        this.authProviderService.logout().subscribe({ complete: () => this.userService.authenticate(null) });
    }
}

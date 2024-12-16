import { inject, isDevMode } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { map } from 'rxjs/operators';

import { AuthStorageService } from './auth-storage.service';
import {UserService} from "./user.service";

export const UserRouteAccessService: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    const router = inject(Router);
    const userService = inject(UserService);
    const stateStorageService = inject(AuthStorageService);
    return userService.identity().pipe(
        map(appUser => {
            if (appUser) {
                const { authorities } = next.data;

                if (!authorities || authorities.length === 0 || userService.hasAnyAuthority(authorities)) {
                    return true;
                }

                if (isDevMode()) {
                    console.error('User does not have any of the required authorities:', authorities);
                }
                router.navigate(['access']).then(r => {});
                window.location.replace("/access")
                return false;
            }

            stateStorageService.storeUrl(state.url);
            router.navigate(['/auth/login']).then(r => {});
            return false;
        }),
    );
};

import {Routes} from '@angular/router';
import {AppLayoutComponent} from "./shared/layout/app.layout.component";
import {NotfoundComponent} from "./shared/components/notfound/notfound.component";
import {ErrorComponent} from "./shared/components/error/error.component";
import {AccessComponent} from "./shared/components/access/access.component";
import {LoginComponent} from "./features/auth/login/login.component";
import {UserRouteAccessService} from "./core/services/auth/user-route-access.service";
import {Authority} from "./core/config/authority.constants";


export const routes: Routes = [
    {
        path: '#', component: AppLayoutComponent,
        canActivate: [UserRouteAccessService],
        children: [
            {
                path: 'account',
                loadChildren: () => import('./features/auth/account/account.route'),
            },
            {
                path: 'user',
                data: {
                    authorities: [Authority.SUPER_ADMIN],
                },
                canActivate: [UserRouteAccessService],
                loadChildren: () => import('./features/user/user.route'),
            }
        ]
    },
    { path: 'error', component: ErrorComponent },
    { path: 'access', component: AccessComponent },
    { path: 'notfound', component: NotfoundComponent },
    { path: 'login', component: LoginComponent },
    { path: '**', redirectTo: '/login', pathMatch: "full" },
];

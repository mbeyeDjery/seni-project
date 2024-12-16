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
        ]
    },
    { path: 'error', loadComponent: () => import('./shared/components/error/error.component').then(c => ErrorComponent) },
    { path: 'access', loadComponent: () => import('./shared/components/access/access.component').then(c => AccessComponent) },
    { path: 'notfound', loadComponent: () => import('./shared/components/notfound/notfound.component').then(c => NotfoundComponent) },
    { path: 'login', loadComponent: () => import('./features/auth/login/login.component').then(c => LoginComponent) },
    { path: '**', redirectTo: '/login', pathMatch: "full" },
];

import { Routes } from '@angular/router';
import {ListHopitalComponent} from "./list/list-hopital.component";
import hopitalResolve from "./route/hopital-routing-resolve.service";

const typeHopitalRoutes: Routes = [
    { path: '', component:  ListHopitalComponent },
    {
        path: '',
        loadComponent: () => import('./list/list-hopital.component').then(m => m.ListHopitalComponent),
    },
    {
        path: ':id/manage',
        loadComponent: () => import('./manage/manage-hopital.component').then(m => m.ManageHopitalComponent),
        resolve: {
            hopital: hopitalResolve,
        },
    },
];

export default typeHopitalRoutes;

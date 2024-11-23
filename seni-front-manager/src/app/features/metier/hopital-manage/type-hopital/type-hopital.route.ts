import { Routes } from '@angular/router';
import {ListTypeHopitalComponent} from "./list/list-type-hopital.component";

const typeHopitalRoutes: Routes = [
    { path: '', loadComponent: () => import('./list/list-type-hopital.component').then(m =>  ListTypeHopitalComponent) },
];

export default typeHopitalRoutes;

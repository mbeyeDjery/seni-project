import { Routes } from '@angular/router';
import {ListManagerComponent} from "./list/list-manager.component";

const managerRoutes: Routes = [
    {path: 'list', loadComponent: () => import('./list/list-manager.component').then(c => ListManagerComponent)}
];

export default managerRoutes;

import { Routes } from '@angular/router';
import {ListManagerComponent} from "./list/list-manager.component";

const managerRoutes: Routes = [
    { path: 'list', component: ListManagerComponent },
];

export default managerRoutes;

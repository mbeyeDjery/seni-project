import { Routes } from '@angular/router';

const hopitalManageRoutes: Routes = [
    {
        path: 'type-hopital',
        data: { pageTitle: 'Gestion Type Hopital' },
        loadChildren: () => import('./type-hopital/type-hopital.route'),
    },
    {
        path: 'hopital',
        data: { pageTitle: 'Gestion Hopital' },
        loadChildren: () => import('./hopital/hopital.route'),
    },
];

export default hopitalManageRoutes;

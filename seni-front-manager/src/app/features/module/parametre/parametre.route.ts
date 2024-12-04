import { Routes } from '@angular/router';

const parametreRoutes: Routes = [
    {
        path: 'localite',
        data: { pageTitle: 'Gestion de la localite' },
        loadChildren: () => import('./localite/localite.route'),
    },
];

export default parametreRoutes;

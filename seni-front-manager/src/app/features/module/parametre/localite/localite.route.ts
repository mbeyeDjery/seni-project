import { Routes } from '@angular/router';

const localiteRoutes: Routes = [
    {
        path: 'region',
        loadComponent: () => import('./region/list/list-region.component').then(c => c.ListRegionComponent),
    },
    {
        path: 'province',
        loadComponent: () => import('./province/list/list-province.component').then(c => c.ListProvinceComponent),
    },
    {
        path: 'ville',
        loadComponent: () => import('./ville/list/list-ville.component').then(c => c.ListVilleComponent),
    },
];

export default localiteRoutes;

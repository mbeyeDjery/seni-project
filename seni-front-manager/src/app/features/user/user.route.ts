import { Routes } from '@angular/router';

const userRoutes: Routes = [
    {
        path: 'manager',
        data: { pageTitle: 'User manager' },
        loadChildren: () => import('./gestionnaire/user.manager.route'),
    },
];

export default userRoutes;

import { Routes } from '@angular/router';

const userRoutes: Routes = [
    {
        path: 'manager',
        data: { pageTitle: 'User manager' },
        loadChildren: () => import('./manager/user.manager.route'),
    },
];

export default userRoutes;

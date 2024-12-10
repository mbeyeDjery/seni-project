import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';
import { AppMenuitemComponent } from './app.menuitem.component';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html',
    standalone: true,
    imports: [AppMenuitemComponent]
})
export class AppMenuComponent implements OnInit {

    model: any[] = [];

    constructor(public layoutService: LayoutService) { }

    ngOnInit() {
        this.model = [
            {
                label: 'Dashboard',
                items: [
                    { label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/'] }
                ]
            },
            {
                label: 'Hopital',
                icon: 'pi pi-fw pi-briefcase',
                items: [
                    { label: 'Type d\'hopitaux', icon: 'pi pi-fw pi-list', routerLink: ['hopital-manage/type-hopital'] },
                    {
                        label: 'Gestion des hopitaux',
                        icon: 'pi pi-fw pi-home',
                        items: [
                            {
                                label: 'Liste des hopitaux',
                                icon: 'pi pi-fw pi-list',
                                routerLink: ['hopital-manage/hopital']
                            },
                        ]
                    },
                ]
            },
            {
                label: 'Parametrage',
                icon: 'pi pi-fw pi-briefcase',
                items: [
                    {
                        label: 'Comptes gestionnaire',
                        icon: 'pi pi-fw pi-users',
                        routerLink: ['user/manager/list'],
                    },
                    {
                        label: 'Localisation',
                        icon: 'pi pi-map',
                        items: [
                            {
                                label: 'Ville',
                                icon: 'pi pi-map-marker',
                                routerLink: ['parametre/localite/ville']
                            },
                            {
                                label: 'Région',
                                icon: 'pi pi-map-marker',
                                routerLink: ['parametre/localite/region']
                            },
                            {
                                label: 'Province',
                                icon: 'pi pi-map-marker',
                                routerLink: ['parametre/localite/province']
                            },
                        ]
                    },
                ]
            },
        ];
    }
}

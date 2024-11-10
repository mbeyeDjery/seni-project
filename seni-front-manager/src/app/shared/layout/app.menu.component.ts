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
                label: 'Parametrage',
                icon: 'pi pi-fw pi-briefcase',
                items: [
                    {
                        label: 'Comptes utilisateurs',
                        icon: 'pi pi-fw pi-users',
                        items: [
                            {
                                label: 'Gestionnaire',
                                icon: 'pi pi-fw pi-eye',
                                routerLink: ['user/manager/list']
                            },
                        ]
                    },
                ]
            },
        ];
    }
}

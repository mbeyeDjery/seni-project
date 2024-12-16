import {Component, ElementRef, inject, ViewChild} from '@angular/core';
import { MenuItem } from 'primeng/api';
import { LayoutService } from "./service/app.layout.service";
import {Router, RouterLink} from '@angular/router';
import { NgClass } from '@angular/common';
import {BadgeModule} from "primeng/badge";
import {UserService} from "../../core/services/auth/user.service";
import {LoginService} from "../../core/services/auth/login.service";

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html',
    standalone: true,
    imports: [RouterLink, NgClass, BadgeModule]
})
export class AppTopBarComponent {

    items!: MenuItem[];

    account = inject(UserService).trackCurrentAccount();

    @ViewChild('menubutton') menuButton!: ElementRef;

    @ViewChild('topbarmenubutton') topbarMenuButton!: ElementRef;

    @ViewChild('topbarmenu') menu!: ElementRef;

    private loginService = inject(LoginService);

    private router = inject(Router);
    constructor(public layoutService: LayoutService) { }

    logout(): void {
        this.loginService.logout();
        this.router.navigate(['']).then(r => {});
    }
}

import {AfterViewInit, Component, ElementRef, inject, OnInit, signal, viewChild} from '@angular/core';
import { LayoutService } from 'src/app/shared/layout/service/app.layout.service';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { CheckboxModule } from 'primeng/checkbox';
import { ButtonModule } from 'primeng/button';
import {Router, RouterLink} from '@angular/router';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {LoginService} from "../../../core/services/auth/login.service";
import {UserService} from "../../../core/services/auth/user.service";
import {MessagesModule} from "primeng/messages";
import {Message} from "primeng/api";
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {AuthRequest} from "../../../core/model/auth-request-model";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styles: [`
        :host ::ng-deep .pi-eye,
        :host ::ng-deep .pi-eye-slash {
            transform:scale(1.6);
            margin-right: 1rem;
            color: var(--primary-color) !important;
        }
    `],
    standalone: true,
    imports: [InputTextModule, PasswordModule, FormsModule, ReactiveFormsModule, CheckboxModule, ButtonModule, MessagesModule, RouterLink, ProgressSpinnerModule]
})
export class LoginComponent implements OnInit, AfterViewInit {
    authResquest: AuthRequest;
    messages: Message[] | undefined;
    isLoading = signal(false);
    username = viewChild.required<ElementRef>('username');

    loginForm = new FormGroup({
        username: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
        password: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
        rememberMe: new FormControl(false, { nonNullable: true, validators: [Validators.required] }),
    });

    private router = inject(Router);
    private _httpClient = inject(HttpClient);
    private userService = inject(UserService);
    private loginService = inject(LoginService);
    public layoutService = inject(LayoutService);

    ngOnInit(): void {
        this.userService.identity().subscribe(() => {
            if (this.userService.isAuthenticated()) {
                this.router.navigate(['/#']);
            }
        });
    }

    ngAfterViewInit(): void {
        this.username().nativeElement.focus();
    }

    login(): void {
        this.isLoading.set(true);
        this.authResquest = {
            username: this.loginForm.getRawValue().username,
            password: this.loginForm.getRawValue().password,
            token: '',
            rememberMe: this.loginForm.getRawValue().rememberMe,
        };
        this.loginService.login(this.authResquest).subscribe({
            next: () => {
                this.isLoading.set(false);
                if (!this.router.getCurrentNavigation()) {
                    window.location.replace("/#");
                }
                window.location.replace("/#");
            },
            error: (err) => {
                this.isLoading.set(false);
                this.messages = [{severity: 'error', detail: err['error']['message']}];
            },
        });
    }
}

import {Component, inject, signal} from '@angular/core';
import {DynamicDialogRef} from "primeng/dynamicdialog";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {PasswordModule} from "primeng/password";
import {ButtonModule} from "primeng/button";
import {ToastModule} from "primeng/toast";
import {Message} from "primeng/api";
import {MessagesModule} from "primeng/messages";
import {AccountService} from "../../../../core/services/account.service";
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {NgxSpinnerComponent, NgxSpinnerService} from "ngx-spinner";

@Component({
  selector: 'app-password',
  standalone: true,
    imports: [
        FormsModule,
        ReactiveFormsModule,
        PasswordModule,
        ButtonModule,
        ToastModule,
        MessagesModule,
        ProgressSpinnerModule,
        NgxSpinnerComponent
    ],
  templateUrl: './password.component.html',
  styles: ``
})
export class PasswordComponent {

    messages: Message[] | undefined;
    private accountService = inject(AccountService);
    protected ngxSpinnerService = inject(NgxSpinnerService);
    private dialogPassword = inject(DynamicDialogRef);

    passwordForm = new FormGroup({
        oldPassword: new FormControl(''),
        newPassword: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
        newPasswordConfirm: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
    });
    changePassword(){
        this.ngxSpinnerService.show();
        if (this.passwordForm.getRawValue().newPassword != this.passwordForm.getRawValue().newPasswordConfirm){
            this.messages = [{severity: 'error', detail: 'Les nouveaux mot de passe ne correspondent pas'}];
        }else {
            this.accountService.changePassword(this.passwordForm.getRawValue()).subscribe(
                {
                    next: () => {
                        this.dialogPassword.close(true);
                    },
                    error: (err) => {
                        this.ngxSpinnerService.hide();
                        this.messages = [{severity: 'error', detail: 'Erreur lors de changement de mot de passe'}];
                    },
                });
        }
    }
}

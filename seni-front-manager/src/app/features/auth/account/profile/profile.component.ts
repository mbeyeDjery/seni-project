import {Component, inject, signal} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {UserService} from "../../../../core/services/auth/user.service";
import {FieldsetModule} from "primeng/fieldset";
import {PanelModule} from "primeng/panel";
import {InputTextModule} from "primeng/inputtext";
import {CalendarModule} from "primeng/calendar";
import {ToastModule} from "primeng/toast";
import {MessageService} from "primeng/api";
import {ImageModule} from "primeng/image";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {PasswordComponent} from "../password/password.component";
import {AccountService} from "../../../../core/services/account.service";
import {AppUser, convertToAppUser} from "../../../../core/model/app-user-model";

@Component({
  selector: 'app-profile',
  standalone: true,
  templateUrl: './profile.component.html',
    styles: ``,
    imports: [FormsModule, ReactiveFormsModule, FieldsetModule, PanelModule, InputTextModule, CalendarModule, ToastModule, ImageModule],
    providers: [MessageService, DialogService]
})
export class ProfileComponent {

    isLoading = signal(false);
    account = inject(UserService).trackCurrentAccount();

    protected dialogService = inject(DialogService);
    protected accountService = inject(AccountService);
    protected messageService = inject(MessageService);

    profileForm = new FormGroup({
        firstName: new FormControl(this.account().firstName, { nonNullable: true, validators: [Validators.required, Validators.minLength(3)] }),
        lastName: new FormControl(this.account().lastName, { nonNullable: true, validators: [Validators.required, Validators.minLength(3)] }),
        email: new FormControl(this.account().email, { nonNullable: true, validators: [Validators.required, Validators.minLength(3)] }),
        telephone: new FormControl(this.account().telephone, { nonNullable: true, validators: [Validators.required, Validators.minLength(3)] }),
    });

    sauvegarder(){
        this.isLoading.set(true);
        console.warn(this.profileForm.getRawValue());
        let appUser: AppUser = convertToAppUser(this.profileForm.getRawValue());
        this.accountService.updateUser(appUser).subscribe(
            {
                next: () => {
                    this.isLoading.set(false);
                    this.messageService.add({ severity: 'success', summary: 'Succès', detail: 'Profile sauvegardé' });
                },
                error: () => {
                    this.isLoading.set(false);
                    this.messageService.add({ severity: 'warn', summary: 'Erreur', detail: 'Erreur lors de la sauvegarde' });
                },
            });
    }

    changerPassword(){
        const dialogPassword = this.dialogService.open(PasswordComponent, {
            position: 'top',
            header: 'Changer de mot de passe',
            contentStyle: { overflow: 'auto' },
        });

        dialogPassword.onClose.subscribe((finish: boolean) => {
            if (finish) {
                this.messageService.add({severity:'success', summary: 'Succès', detail:'Mot de passe modifié avec succès'});
            }else {
                this.messageService.add({severity:'warn', summary: 'Information', detail:'Opération avortée'});
            }
        });
    }

    refresh(): void {
        window.location.reload();
    }

}

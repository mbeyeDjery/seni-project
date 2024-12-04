import {Component, inject} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UserService} from "../../../../core/services/auth/user.service";
import {FieldsetModule} from "primeng/fieldset";
import {PanelModule} from "primeng/panel";
import {InputTextModule} from "primeng/inputtext";
import {CalendarModule} from "primeng/calendar";
import {ToastModule} from "primeng/toast";
import {MessageService} from "primeng/api";
import {ImageModule} from "primeng/image";
import {DialogService} from "primeng/dynamicdialog";
import {PasswordComponent} from "../password/password.component";
import {AccountService} from "../../../../core/services/account.service";
import {NgxSpinnerComponent, NgxSpinnerService} from "ngx-spinner";
import {ProfileFormService} from "../../../../core/services/form-group/profile-form.service";

@Component({
  selector: 'app-profile',
  standalone: true,
  templateUrl: './profile.component.html',
    styles: ``,
    imports: [FormsModule, ReactiveFormsModule, FieldsetModule, PanelModule, InputTextModule, CalendarModule, ToastModule, ImageModule, NgxSpinnerComponent],
    providers: [MessageService, DialogService]
})
export class ProfileComponent {

    account = inject(UserService).trackCurrentAccount();
    protected dialogService = inject(DialogService);
    protected accountService = inject(AccountService);
    protected messageService = inject(MessageService);
    protected ngxSpinnerService = inject(NgxSpinnerService);
    protected profileFormService = inject(ProfileFormService);

    profileForm = this.profileFormService.createProfileFormGroup(this.account());

    sauvegarder(){
        this.ngxSpinnerService.show().then();
        this.accountService.updateUser(this.profileFormService.get(this.profileForm)).subscribe(
            {
                next: () => {
                    this.ngxSpinnerService.hide().then();
                    this.messageService.add({ severity: 'info', summary: 'Succès', detail: 'Profile sauvegardé' });
                },
                error: () => {
                    this.ngxSpinnerService.hide().then();
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
                this.messageService.add({severity:'info', summary: 'Succès', detail:'Mot de passe modifié avec succès'});
            }else {
                this.messageService.add({severity:'warn', summary: 'Information', detail:'Opération avortée'});
            }
        });
    }

    refresh(): void {
        window.location.reload();
    }

}

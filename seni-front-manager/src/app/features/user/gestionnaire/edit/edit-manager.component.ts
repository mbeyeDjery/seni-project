import {Component, inject, OnInit} from '@angular/core';
import {MessagesModule} from "primeng/messages";
import {Message} from "primeng/api";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {SelectButtonModule} from "primeng/selectbutton";
import {Button, ButtonDirective} from "primeng/button";
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {UserGestionnaireService} from "../../../../core/services/user-gestionnaire.service";
import {NgxSpinnerComponent, NgxSpinnerService} from "ngx-spinner";
import {ProfileFormService} from "../../../../core/services/form-group/profile-form.service";
import {IAppRole} from "../../../../core/model/app-role-model";
import {DropdownModule} from "primeng/dropdown";
import {AutoFocus} from "primeng/autofocus";
import {IManagerAuthority} from "../../../../core/model/enum/authority";
import {ListboxModule} from "primeng/listbox";

@Component({
  selector: 'app-edit-manager',
  standalone: true,
    imports: [
        MessagesModule,
        FormsModule,
        ReactiveFormsModule,
        InputTextModule,
        SelectButtonModule,
        ButtonDirective,
        ProgressSpinnerModule,
        NgxSpinnerComponent,
        DropdownModule,
        AutoFocus,
        Button,
        ListboxModule
    ],
  templateUrl: './edit-manager.component.html',
  styles: ``
})
export class EditManagerComponent implements OnInit{

    messages: Message[] | undefined;
    config = inject(DynamicDialogConfig);
    enableds: any[] = [{ label: 'Activer', value: true },{ label: 'Desactiver', value: false }];

    protected ngxSpinnerService = inject(NgxSpinnerService);
    protected profileFormService = inject(ProfileFormService);
    protected dialog = inject(DynamicDialogRef);
    protected appUserService = inject(UserGestionnaireService);

    appRoles : IAppRole[] = Object.values(IManagerAuthority).map(value => ({roleId: '', roleName:value}));
    editForm = this.profileFormService.createUserFormGroup();

    ngOnInit(): void {
        this.ngxSpinnerService.show().then();
        if (this.config.data) {
            this.editForm = this.profileFormService.createUserFormGroup(this.config.data.appUser);
        }
        this.ngxSpinnerService.hide().then();
    }

    save(): void {
        this.ngxSpinnerService.show().then();
        const appUser = this.profileFormService.get(this.editForm);
        console.warn(appUser);
        if (this.editForm.getRawValue().idUser){
            console.warn('un');
            this.appUserService.updateUser(appUser).subscribe({
                next: () => this.dialog.close(true),
                error: (err) => {
                    this.ngxSpinnerService.hide().then();
                    console.warn(err);
                    this.messages = [{severity: 'error', detail: err['error']['message']}];
                }
            });
        }else {
            console.warn('Deux')
            appUser.password = '123456';
            this.appUserService.createUser(appUser).subscribe({
                next: () => this.dialog.close(true),
                error: (err) => {
                    this.ngxSpinnerService.hide().then();
                    this.editForm = this.profileFormService.createUserFormGroup();
                    this.messages = [{severity: 'error', detail: err['error']['message']}];
                }
            });
        }
    }

    cancel(){
        this.dialog.close(false);
    }

}

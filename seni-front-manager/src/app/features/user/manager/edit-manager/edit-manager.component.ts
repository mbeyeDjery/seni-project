import {Component, inject, OnInit, signal} from '@angular/core';
import {MessagesModule} from "primeng/messages";
import {Message} from "primeng/api";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {SelectButtonModule} from "primeng/selectbutton";
import {ButtonDirective} from "primeng/button";
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {AppUserService} from "../../../../core/services/app-user.service";
import {AppUser} from "../../../../core/model/app-user-model";

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
        ProgressSpinnerModule
    ],
  templateUrl: './edit-manager.component.html',
  styles: ``
})
export class EditManagerComponent implements OnInit{

    appUser?: AppUser;
    messages: Message[] | undefined;
    isLoading = signal(false);
    config = inject(DynamicDialogConfig);
    enableds: any[] = [{ label: 'Activer', value: true },{ label: 'Desactiver', value: false }];

    editForm = new FormGroup({
        userName: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
        firstName: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
        lastName: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
        telephone: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.minLength(8), Validators.maxLength(8)] }),
        role: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
        email: new FormControl('', { nonNullable: true, validators: [Validators.email] }),
        enabled: new FormControl(true),

    });

    protected appUserService = inject(AppUserService);

    ngOnInit(): void {
        if (this.config.data){
            this.appUser = this.config.data.appUser;
            this.editForm.reset({
                userName: this.appUser.username,
                firstName: this.appUser.firstName,
                lastName: this.appUser.lastName,
                email: this.appUser.email,
                telephone: this.appUser.telephone,
                enabled: this.appUser.enabled,
                role: this.appUser.roles[0].roleName
            });
        }else {
            console.warn(this.editForm.getRawValue());
        }
    }

    private dialog = inject(DynamicDialogRef);

    save(){
        this.isLoading.set(true);
        if (this.appUser){
            this.appUser.firstName = this.editForm.getRawValue().firstName;
            this.appUser.lastName = this.editForm.getRawValue().lastName;
            this.appUser.enabled = this.editForm.getRawValue().enabled;
            if (!this.appUser.roles[0].roleName.includes(this.editForm.getRawValue().role)){
                this.appUser.roles[0].roleName = this.editForm.getRawValue().role;
            }
            this.appUserService.updateUser(this.appUser).subscribe({
                next: (response) => {
                    this.dialog.close(true);
                }, error: (err) => {
                    this.isLoading.set(false);
                    this.messages = [{severity: 'error', detail: err}];
                }
            });
        }else {
            this.appUser = {
                username: this.editForm.getRawValue().userName,
                password: '123456',
                firstName: this.editForm.getRawValue().firstName,
                lastName: this.editForm.getRawValue().lastName,
                email: this.editForm.getRawValue().email,
                telephone: this.editForm.getRawValue().telephone,
                enabled: this.editForm.getRawValue().enabled,
                imageUrl: '',
                roles: [
                    {
                        roleId: null,
                        roleName: this.editForm.getRawValue().role
                    }
                ]
            };

            this.appUserService.createUser(this.appUser).subscribe({
                next: (response) => {
                    this.dialog.close(true);
                }, error: (err) => {
                    this.isLoading.set(false);
                    this.appUser = null;
                    this.messages = [{severity: 'error', detail: err['error']['message']}];
                }
            });
        }
    }

    cancel(){
        this.dialog.close(false);
    }

}

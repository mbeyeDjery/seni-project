import {Component, inject, OnInit, signal} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {UserGestionnaireService} from "../../../../core/services/user-gestionnaire.service";
import {NgStyle} from "@angular/common";
import {ToastModule} from "primeng/toast";
import {ButtonModule} from "primeng/button";
import {Table, TableModule} from "primeng/table";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {TagModule} from "primeng/tag";
import {DialogService} from "primeng/dynamicdialog";
import {EditManagerComponent} from "../edit/edit-manager.component";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {IAppUser} from "../../../../core/model/app-user-model";
import {NgxSpinnerService} from "ngx-spinner";
import {ChipModule} from "primeng/chip";

@Component({
  selector: 'app-list-manager',
  standalone: true,
  templateUrl: './list-manager.component.html',
    styles: `
        :host ::ng-deep {
            .p-progressbar {
                height: .5rem;
                background-color: #D8DADC;

                .p-progressbar-value {
                    background-color: #607D8B;
                }
            }
        }`,
    imports: [
        ToastModule,
        ButtonModule,
        NgStyle,
        TableModule,
        FormsModule,
        InputTextModule,
        ReactiveFormsModule,
        TagModule,
        ConfirmDialogModule,
        ChipModule,
    ],
    providers: [MessageService, DialogService, ConfirmationService]
})
export class ListManagerComponent implements OnInit {

    appUsers?: IAppUser[];
    searchValue: string | undefined;

    protected dialogService = inject(DialogService);
    protected messageService = inject(MessageService);
    protected ngxSpinnerService = inject(NgxSpinnerService);
    protected confirmationService = inject(ConfirmationService);
    protected appUserService = inject(UserGestionnaireService);

    ngOnInit(): void {
        this.ngxSpinnerService.show().then();
        this.load();
    }

    load(): void {
        this.appUserService.getAllUsers().subscribe(
            {
                next: (response) => {
                    this.appUsers = response;
                    this.ngxSpinnerService.hide().then();
                }, error: (err) => {
                    this.ngxSpinnerService.hide().then();
                    this.messageService.add({severity:'error', summary: 'Erreur', detail: 'Certaine données ne peuvent pas être chargées'});
                }
            }
        );
    }

    resetPassword(appUserSelect: IAppUser){
        this.confirmationService.confirm({
            header: 'Etes vous sûr ?',
            message: 'Reinitialiser le mot de passe.',
            accept: () => {
                this.messageService.add({ severity: 'info', summary: 'Succès', detail: 'Mot de passe renitialisé avec succès', life: 3000 });
            }
        });
    }

    edit(appUserSelect: IAppUser){
        const dialog = this.dialogService.open(EditManagerComponent, {
            position: 'top',
            closable: false,
            closeOnEscape: false,
            draggable: true,
            data: {
                appUser: appUserSelect
            },
            header: 'Modifier un compte gestionnaire',
            contentStyle: { overflow: 'auto' },
        });

        dialog.onClose.subscribe((finish: boolean) => {
            if (finish) {
                this.messageService.add({severity:'info', summary: 'Succès', detail:'Modification de compte effectuée avec succès'});
                this.load();
            }else {
                // this.messageService.add({severity:'warn', summary: 'Information', detail:'Modification de compte annulée'});
            }
        });
    }

    nouveau(){
        const dialog = this.dialogService.open(EditManagerComponent, {
            closable: false,
            draggable: true,
            position: 'top',
            closeOnEscape: false,
            header: 'Créer un nouveau gestionnaire',
            contentStyle: { overflow: 'auto' },
        });

        dialog.onClose.subscribe((finish: boolean) => {
            if (finish) {
                this.messageService.add({severity:'info', summary: 'Succès', detail:'Nouveau compte crée avec succès'});
                this.load();
            }else {
                // this.messageService.add({severity:'warn', summary: 'Information', detail:'Création de compte annulée'});
            }
        });
    }

    getSeverity(enabled: boolean) {

        if (enabled){
            return 'success';
        }else {
            return 'danger';
        }
    }

    reset(table: Table) {
        table.clear();
        this.searchValue = ''
    }
}

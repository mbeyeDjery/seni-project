import {Component, inject, OnInit, signal} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {AppUserService} from "../../../../core/services/app-user.service";
import {JsonPipe, NgStyle} from "@angular/common";
import {ToastModule} from "primeng/toast";
import {ButtonModule} from "primeng/button";
import {Table, TableModule} from "primeng/table";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {TagModule} from "primeng/tag";
import {DialogService} from "primeng/dynamicdialog";
import {EditManagerComponent} from "../edit-manager/edit-manager.component";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {AppUser} from "../../../../core/model/app-user-model";

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
        JsonPipe,
        ToastModule,
        ButtonModule,
        NgStyle,
        TableModule,
        FormsModule,
        InputTextModule,
        ReactiveFormsModule,
        TagModule,
        ConfirmDialogModule,
    ],
    providers: [MessageService, DialogService, ConfirmationService]
})
export class ListManagerComponent implements OnInit{

    appUsers?: AppUser[];
    searchValue: string | undefined;
    isLoading = signal(true);

    protected dialogService = inject(DialogService);
    protected appUserService = inject(AppUserService);
    protected messageService = inject(MessageService);
    protected confirmationService = inject(ConfirmationService);

    ngOnInit(): void {
        this.load();
    }

    load(): void {
        this.appUserService.getAllUsers().subscribe(
            {
                next: (response) => {
                    this.appUsers = response;
                    this.isLoading.set(false);
                }, error: (err) => {
                    this.isLoading.set(false);
                }
            }
        );
    }

    resetPassword(appUserSelect: AppUser){
        this.confirmationService.confirm({
            header: 'Etes vous sûr ?',
            message: 'Reinitialiser le mot de passe.',
            accept: () => {
                this.messageService.add({ severity: 'success', summary: 'Succès', detail: 'Mot de passe renitialisé avec succès', life: 3000 });
            }
        });
    }

    edit(appUserSelect: AppUser){
        const dialog = this.dialogService.open(EditManagerComponent, {
            position: 'center',
            closable: false,
            closeOnEscape: false,
            data: {
                appUser: appUserSelect
            },
            header: 'Modifier un compte gestionnaire',
            contentStyle: { overflow: 'auto' },
        });

        dialog.onClose.subscribe((finish: boolean) => {
            if (finish) {
                this.messageService.add({severity:'success', summary: 'Succès', detail:'Modification de compte effectuée avec succès'});
                this.load();
            }else {
                // this.messageService.add({severity:'warn', summary: 'Information', detail:'Modification de compte annulée'});
            }
        });
    }

    nouveau(){
        const dialog = this.dialogService.open(EditManagerComponent, {
            position: 'center',
            closable: false,
            closeOnEscape: false,
            header: 'Créer un nouveau gestionnaire',
            contentStyle: { overflow: 'auto' },
        });

        dialog.onClose.subscribe((finish: boolean) => {
            if (finish) {
                this.messageService.add({severity:'success', summary: 'Succès', detail:'Nouveau compte crée avec succès'});
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

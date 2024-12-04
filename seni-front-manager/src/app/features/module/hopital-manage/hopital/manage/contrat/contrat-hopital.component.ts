import {Component, inject, Input, OnInit} from '@angular/core';
import {IHopital} from "../../../../../../core/model/hopital-model";
import {ConfirmationService, MessageService} from "primeng/api";
import {NgxSpinnerComponent, NgxSpinnerService} from "ngx-spinner";
import {ToastModule} from "primeng/toast";
import {IContratHopital} from "../../../../../../core/model/contrat-hopital";
import {ContratHopitalService} from "../../../../../../core/services/contrat-hopital.service";
import {Button, ButtonDirective} from "primeng/button";
import {Ripple} from "primeng/ripple";
import {Table, TableModule} from "primeng/table";
import {FormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {TagModule} from "primeng/tag";
import {IContratHopitalStatus} from "../../../../../../core/model/enum/contrat-hopital-status";
import {DialogService} from "primeng/dynamicdialog";
import {EditContratComponent} from "./edit/edit-contrat.component";
import {NgIf} from "@angular/common";
import {DataViewModule} from "primeng/dataview";
import {ConfirmPopupModule} from "primeng/confirmpopup";
import {IContratStatutRequest} from "../../../../../../core/model/request/contrat-statut-request";

@Component({
  selector: 'app-contrat-hopital',
  standalone: true,
  imports: [
    NgxSpinnerComponent,
    ToastModule,
    Button,
    ButtonDirective,
    Ripple,
    TableModule,
    FormsModule,
    InputTextModule,
    TagModule,
    NgIf,
    DataViewModule,
    ConfirmPopupModule
  ],
  templateUrl: './contrat-hopital.component.html',
  styles: ``,
  providers: [MessageService, DialogService, ConfirmationService]
})
export class ContratHopitalComponent implements  OnInit{
  @Input({required: true}) hopital: IHopital;

  contratActif?: IContratHopital;
  contrats: IContratHopital[] = [];
  protected dialogService = inject(DialogService);
  protected messageService = inject(MessageService);
  protected ngxSpinnerService = inject(NgxSpinnerService);
  protected confirmationService = inject(ConfirmationService);
  protected contratHopitalService = inject(ContratHopitalService);

  ngOnInit(): void {
    this.ngxSpinnerService.show().then();
    this.load();
    this.ngxSpinnerService.hide().then();
  }

  load(): void {
    this.contratHopitalService.getAll(this.hopital.idHopital).subscribe({
      next: responce => (this.contrats = responce),
      error: err => this.messageService.add({severity: 'error', summary: 'Erreur', detail: 'Chargement impossible'}),
    });

    this.contratHopitalService.getByHopitalAndStatut(this.hopital.idHopital, IContratHopitalStatus.ACTIVATED).subscribe({
      next: response => {
        if (response.length > 0) {
          this.contratActif = response[0];
        }
      },
    });
  }

  nouveau(){
    const dialog = this.dialogService.open(EditContratComponent, {
      position: 'top',
      closable: false,
      closeOnEscape: false,
      draggable: false,
      data: {
        hopital: this.hopital
      },
      header: 'Initier un nouveau contrat',
      contentStyle: { overflow: 'auto' },
    });

    dialog.onClose.subscribe((finish: boolean) => {
      if (finish) {
        this.load();
        this.messageService.add({severity:'info', summary: 'Succès', detail:'Contrat initié avec succès'});
      }else {
        this.messageService.add({severity:'warn', summary: 'Information', detail:'Opération avortée'});
      }
    });
  }

  editer(contratSelect: IContratHopital){
    const dialog = this.dialogService.open(EditContratComponent, {
      position: 'top',
      closable: false,
      closeOnEscape: false,
      draggable: false,
      data: {
        hopital: this.hopital,
        contrat: contratSelect
      },
      header: 'Editer le contrat',
      contentStyle: { overflow: 'auto' },
    });

    dialog.onClose.subscribe((finish: boolean) => {
      if (finish) {
        this.load();
        this.messageService.add({severity:'info', summary: 'Succès', detail:'Contrat éditer avec succès'});
      }else {
        this.messageService.add({severity:'warn', summary: 'Information', detail:'Opération avortée'});
      }
    });
  }

  changeStatut(statutRequest: IContratStatutRequest){
    this.ngxSpinnerService.show().then();
    this.contratHopitalService.updateStatut(statutRequest).subscribe({
      next: value => {
        window.location.reload();
        this.messageService.add({severity:'success', summary: 'Succès', detail:'Statut modifié avec succès'});
      },
      error: err => this.messageService.add({severity:'error', summary: 'Erreur', detail:'Echec de modification du statut'}),
    });
    this.ngxSpinnerService.hide().then();
  }

  getStatut(statut: string) {
    if (statut.includes(IContratHopitalStatus.SUSPENDED)){
      return 'danger';
    }else {
      return 'success';
    }
  }

  reset(table: Table) {
    table.clear();
  }

  confirmChabgeStatut(event: Event, contratSelect: IContratHopital) {
    this.confirmationService.confirm({
      target: event.target as EventTarget,
      message: 'Changer le statut de ce contrat ?',
      icon: 'pi pi-exclamation-triangle',
      acceptLabel: 'Oui',
      rejectLabel: 'Non',
      accept: () => {
        let statutRequest = {
          idContrattHopital: contratSelect.idContrattHopital,
          statut: contratSelect.statut.includes(IContratHopitalStatus.ACTIVATED) ? IContratHopitalStatus.SUSPENDED : IContratHopitalStatus.ACTIVATED,
          motif: 'Non defini',
        } as IContratStatutRequest;
        this.changeStatut(statutRequest);
      },
    });
  }
}

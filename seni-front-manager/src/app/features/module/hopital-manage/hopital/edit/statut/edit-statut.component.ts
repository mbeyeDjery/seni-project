import {Component, inject, OnInit} from '@angular/core';
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {HopitalService} from "../../../../../../core/services/hopital.service";
import {NgxSpinnerComponent, NgxSpinnerService} from "ngx-spinner";
import {HopitalFormService} from "../../../../../../core/services/form-group/hopital-form.service";
import {MessagesModule} from "primeng/messages";
import {Message} from "primeng/api";
import {ReactiveFormsModule} from "@angular/forms";
import {PanelModule} from "primeng/panel";
import {IHopitalStatus} from "../../../../../../core/model/enum/hopital-status";
import {AutoFocus} from "primeng/autofocus";
import {DropdownModule} from "primeng/dropdown";
import {InputTextModule} from "primeng/inputtext";
import {Button} from "primeng/button";
import {InputTextareaModule} from "primeng/inputtextarea";
import {ChipModule} from "primeng/chip";
import {IContratHopital} from "../../../../../../core/model/contrat-hopital";
import {IContratHopitalStatus} from "../../../../../../core/model/enum/contrat-hopital-status";
import {ContratHopitalService} from "../../../../../../core/services/contrat-hopital.service";

@Component({
  selector: 'app-edit-statut',
  standalone: true,
  imports: [
    MessagesModule,
    NgxSpinnerComponent,
    ReactiveFormsModule,
    PanelModule,
    AutoFocus,
    DropdownModule,
    InputTextModule,
    Button,
    InputTextareaModule,
    ChipModule,
  ],
  templateUrl: './edit-statut.component.html',
  styles: ``
})
export class EditStatutComponent implements OnInit {
  statut = '';
  messages: Message[] | undefined;
  contratActif?: IContratHopital;
  config = inject(DynamicDialogConfig);

  protected hopitalService = inject(HopitalService);
  protected ngxSpinnerService = inject(NgxSpinnerService);
  protected hopitalFormService = inject(HopitalFormService);
  protected dialog = inject(DynamicDialogRef);
  protected contratHopitalService = inject(ContratHopitalService);

  editForm = this.hopitalFormService.createStatusFormGroup();
  hopitalStatus : string[] = Object.values(IHopitalStatus).map(value => value);
  ngOnInit(): void {
    this.ngxSpinnerService.show().then();
    if (this.config.data) {
      this.statut = this.config.data.hopital.statut;
      this.editForm = this.hopitalFormService.createStatusFormGroup(this.config.data.hopital);
      this.contratHopitalService.getByHopitalAndStatut(this.config.data.hopital.idHopital, IContratHopitalStatus.ACTIVATED).subscribe({
        next: response => {
          if (response.length > 0) {
            this.contratActif = response[0];
          }
        },
      });
    }
    this.ngxSpinnerService.hide().then();
  }

  save(): void {
    if (this.editForm.getRawValue().statut.includes(IHopitalStatus.PENDING)){
      this.messages = [{severity: 'error', detail: 'Statut non assignable'}];
    } else if (this.editForm.getRawValue().statut.includes(this.statut)) {
      this.messages = [{severity: 'error', detail: 'Statut déja assigné'}];
    }else {
      if (this.editForm.getRawValue().statut.includes(IHopitalStatus.ACTIVATED) && !this.contratActif){
        this.messages = [{severity: 'error', detail: 'Aucun contrat actif : activation impossible'}];
      }else {
        this.ngxSpinnerService.show().then();
        this.hopitalService.updateStatut(this.hopitalFormService.getStatutRequest(this.editForm)).subscribe({
          next: () => this.dialog.close(true),
          error: (err) => {
            this.ngxSpinnerService.hide().then();
            this.messages = [{severity: 'error', detail: err['message']}];
          }
        });
      }
    }
  }

  getStatut(statut: string) {
    if (statut.includes(IHopitalStatus.PENDING)){
      return 'bg-orange-200';
    }else if (statut.includes(IHopitalStatus.ACTIVATED)){
      return 'bg-green-200';
    }else {
      return 'bg-red-200';
    }
  }

  cancel(): void {
    this.dialog.close(false);
  }

}

import {Component, inject, OnInit} from '@angular/core';
import {MessagesModule} from "primeng/messages";
import {NgxSpinnerComponent, NgxSpinnerService} from "ngx-spinner";
import {Message} from "primeng/api";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {HopitalService} from "../../../../../core/services/hopital.service";
import {TypeHopitalService} from "../../../../../core/services/type-hopital.service";
import {ReactiveFormsModule} from "@angular/forms";
import {PanelModule} from "primeng/panel";
import {InputTextModule} from "primeng/inputtext";
import {EditorModule} from "primeng/editor";
import {AutoFocus} from "primeng/autofocus";
import {Button} from "primeng/button";
import {HopitalFormService} from "../../../../../core/services/form-group/hopital-form.service";
import {ITypeHopital} from "../../../../../core/model/type-hopital-model";
import {DropdownModule} from "primeng/dropdown";
import {TagModule} from "primeng/tag";
import {IHopitalStatus} from "../../../../../core/model/enum/hopital-status";
import {ChipModule} from "primeng/chip";
import {KeyFilterModule} from "primeng/keyfilter";
import {IVille} from "../../../../../core/model/ville";
import {LocaliteService} from "../../../../../core/services/localite.service";
import {InputTextareaModule} from "primeng/inputtextarea";

@Component({
  selector: 'app-edit-hopital',
  standalone: true,
  imports: [
    MessagesModule,
    NgxSpinnerComponent,
    ReactiveFormsModule,
    PanelModule,
    InputTextModule,
    EditorModule,
    AutoFocus,
    Button,
    DropdownModule,
    TagModule,
    ChipModule,
    KeyFilterModule,
    InputTextareaModule,
  ],
  templateUrl: './edit-hopital.component.html',
  styles: ``
})
export class EditHopitalComponent implements OnInit {
  villes?: IVille[];
  messages: Message[] | undefined;
  typeHopitaux: ITypeHopital[] = [];
  config = inject(DynamicDialogConfig);

  protected hopitalService = inject(HopitalService);
  protected localiteService = inject(LocaliteService);
  protected ngxSpinnerService = inject(NgxSpinnerService);
  protected hopitalFormService = inject(HopitalFormService);
  protected typeHopitalService = inject(TypeHopitalService);
  protected dialog = inject(DynamicDialogRef);

  editForm = this.hopitalFormService.createFormGroup();

  ngOnInit(): void {
    this.ngxSpinnerService.show().then();
    this.load();
    if (this.config.data) {
      this.editForm = this.hopitalFormService.createFormGroup(this.config.data.hopital);
    }
    this.ngxSpinnerService.hide().then();
  }

  load(): void {
    this.typeHopitalService.getAll().subscribe({
      next: response => (this.typeHopitaux = response),
      error: err => this.cancel(),
    });

    this.localiteService.getAllVille().subscribe({
      next: response => (this.villes = response),
      error: err => this.messages = [{severity: 'error', detail: err['error']['message']}]
    });
  }

  save(): void {
    this.ngxSpinnerService.show().then();
    const hopital = this.hopitalFormService.get(this.editForm);
    hopital.ville.province = null;
    if (hopital.idHopital){
      this.hopitalService.update(hopital).subscribe({
        next: value => {
          this.ngxSpinnerService.hide().then();
          this.dialog.close(true);
        },
        error: err => {
          this.ngxSpinnerService.hide().then();
          this.messages = [{severity: 'error', detail: err['error']['message']}];
        }
      });
    }else {
      this.hopitalService.create(hopital).subscribe({
        next: value => {
          this.ngxSpinnerService.hide().then();
          this.dialog.close(true);
        },
        error: err => {
          this.ngxSpinnerService.hide().then();
          this.messages = [{severity: 'error', detail: err['error']['message']}];
        }
      });
    }
  }

  cancel(): void {
    this.dialog.close(false);
  }

  getStatut(statut: string) {
    if (statut.includes(IHopitalStatus.PENDING)){
      return 'warning';
    }else if (statut.includes(IHopitalStatus.ACTIVATED)){
      return 'success';
    }else {
      return 'danger';
    }
  }
}

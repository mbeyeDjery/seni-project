import {Component, inject} from '@angular/core';
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
  ],
  templateUrl: './edit-hopital.component.html',
  styles: ``
})
export class EditHopitalComponent {
  messages: Message[] | undefined;
  typeHopitaux: ITypeHopital[] = [];
  config = inject(DynamicDialogConfig);

  protected hopitalService = inject(HopitalService);
  protected ngxSpinnerService = inject(NgxSpinnerService);
  protected hopitalFormService = inject(HopitalFormService);
  protected typeHopitalService = inject(TypeHopitalService);
  protected dialog = inject(DynamicDialogRef);

  editForm = this.hopitalFormService.createHopitalFormGroup();

  ngOnInit(): void {
    this.load();
    if (this.config.data) {
      this.editForm = this.hopitalFormService.createHopitalFormGroup(this.config.data.hopital);
    }
  }

  load(): void {
    this.typeHopitalService.getAll().subscribe({
      next: response => (this.typeHopitaux = response),
      error: err => this.cancel(),
    });
  }

  save(): void {
    const hopital = this.hopitalFormService.getHopital(this.editForm);
    if (hopital.idHopital){
      this.hopitalService.update(this.hopitalFormService.getHopital(this.editForm)).subscribe({
        next: value => this.dialog.close(true),
        error: err => this.messages = [{severity: 'error', detail: err['error']['message']}],
      });
    }else {
      this.hopitalService.create(this.hopitalFormService.getHopital(this.editForm)).subscribe({
        next: value => this.dialog.close(true),
        error: err => this.messages = [{severity: 'error', detail: err['error']['message']}],
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

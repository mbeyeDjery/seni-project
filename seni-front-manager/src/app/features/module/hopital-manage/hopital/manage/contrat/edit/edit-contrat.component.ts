import {Component, inject, OnInit} from '@angular/core';
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {Message} from "primeng/api";
import {NgxSpinnerComponent, NgxSpinnerService} from "ngx-spinner";
import {ContratHopitalFormService} from "../../../../../../../core/services/form-group/contrat-hopital-form.service";
import {MessagesModule} from "primeng/messages";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {PanelModule} from "primeng/panel";
import {AutoFocus} from "primeng/autofocus";
import {CalendarModule} from "primeng/calendar";
import {InputTextareaModule} from "primeng/inputtextarea";
import {ChipModule} from "primeng/chip";
import {NgIf} from "@angular/common";
import {ContratHopitalService} from "../../../../../../../core/services/contrat-hopital.service";

@Component({
  selector: 'app-edit-contrat',
  standalone: true,
  imports: [
    MessagesModule,
    NgxSpinnerComponent,
    FormsModule,
    ReactiveFormsModule,
    InputTextModule,
    PanelModule,
    AutoFocus,
    CalendarModule,
    InputTextareaModule,
    ChipModule,
    NgIf
  ],
  templateUrl: './edit-contrat.component.html',
  styles: ``
})
export class EditContratComponent implements OnInit {
  minDate: Date | undefined;
  messages: Message[] | undefined;
  config = inject(DynamicDialogConfig);

  protected ngxSpinnerService = inject(NgxSpinnerService);
  protected dialog = inject(DynamicDialogRef);
  protected contratHopitalService = inject(ContratHopitalService);
  protected contratFormService = inject(ContratHopitalFormService);

  editForm = this.contratFormService.createFormGroup();

  ngOnInit(): void {
    this.ngxSpinnerService.show().then();
    if (this.config.data.contrat) {
      this.editForm = this.contratFormService.createFormGroup(this.config.data.contrat);
      console.warn(this.config.data.contrat);
      console.warn(this.editForm.getRawValue());
    }else {
      this.editForm.get('hopital').setValue(this.config.data.hopital);
    }
    let today = new Date();
    let month = today.getMonth();
    let year = today.getFullYear();
    let prevMonth = (month === 0) ? 11 : month -1;
    let prevYear = (prevMonth === 11) ? year - 1 : year;
    this.minDate = new Date();
    this.minDate.setMonth(prevMonth);
    this.minDate.setFullYear(prevYear);
    this.ngxSpinnerService.hide().then();
  }

  save(): void {
    this.ngxSpinnerService.show().then();
    const contrat = this.contratFormService.get(this.editForm);
    if (contrat.idContrattHopital){
      this.contratHopitalService.update(this.contratFormService.get(this.editForm)).subscribe({
        next: value => {
          this.ngxSpinnerService.hide().then();
          this.dialog.close(true);
        },
        error: err => {
          this.ngxSpinnerService.hide().then();
          this.messages = [{severity: 'error', detail: err['error']['message']}];
        }
      });
    } else {
      this.contratHopitalService.create(this.contratFormService.get(this.editForm)).subscribe({
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
}

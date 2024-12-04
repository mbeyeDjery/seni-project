import {Component, inject, OnInit, signal} from '@angular/core';
import {CardModule} from "primeng/card";
import {ITypeHopital} from "../../../../../core/model/type-hopital-model";
import {TypeHopitalService} from "../../../../../core/services/type-hopital.service";
import {Button, ButtonDirective} from "primeng/button";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {Table, TableModule} from "primeng/table";
import {TagModule} from "primeng/tag";
import {PanelModule} from "primeng/panel";
import {SelectButtonModule} from "primeng/selectbutton";
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {MessageService} from "primeng/api";
import {ToastModule} from "primeng/toast";
import {NgxSpinnerComponent, NgxSpinnerService} from "ngx-spinner";
import {TypeHopitalFormService} from "../../../../../core/services/form-group/type-hopital-form.service";
import {InputTextareaModule} from "primeng/inputtextarea";


@Component({
  selector: 'app-list-type-hopital',
  standalone: true,
  imports: [
    CardModule,
    Button,
    FormsModule,
    InputTextModule,
    TableModule,
    TagModule,
    ReactiveFormsModule,
    PanelModule,
    SelectButtonModule,
    ButtonDirective,
    ProgressSpinnerModule,
    ToastModule,
    NgxSpinnerComponent,
    InputTextareaModule
  ],
  templateUrl: './list-type-hopital.component.html',
  styles: ``,
  providers: [MessageService]
})
export class ListTypeHopitalComponent implements OnInit {

  typeHopitals?: ITypeHopital[];
  searchValue: string | undefined;
  enableds: any[] = [{ label: 'Activer', value: true },{ label: 'Desactiver', value: false }];

  protected messageService = inject(MessageService);
  protected ngxSpinnerService = inject(NgxSpinnerService);
  protected typeHopitalService = inject(TypeHopitalService);
  protected typeHopitalFormService = inject(TypeHopitalFormService);

  editForm = this.typeHopitalFormService.createFormGroup();

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.ngxSpinnerService.show().then();
    this.resetForm();
    this.typeHopitalService.getAll().subscribe({
      next: response => this.typeHopitals = response,
      error: err => this.messageService.add({severity:'error', summary: 'Erreur', detail: 'Certaines données ne peuvent pas être chargées'})
    });
    this.ngxSpinnerService.hide().then();
  }

  save(){
    this.ngxSpinnerService.show().then();
    const typeHopital = this.typeHopitalFormService.get(this.editForm);

    if (typeHopital.idTypeHopital){
      this.typeHopitalService.update(typeHopital).subscribe({
        next: value => {
          this.load();
          this.messageService.add({severity: 'info', summary: 'Succès', detail: 'Enregistrer avec succès'});
        },
        error: err => {
          this.ngxSpinnerService.hide().then();
          this.messageService.add({severity: 'error', summary: 'Erreur', detail: err['message']});
        }
      });
    }else {
      this.typeHopitalService.create(typeHopital).subscribe({
        next: value => {
          this.load();
          this.messageService.add({severity: 'info', summary: 'Succès', detail: value.message});
        },
        error: err => {
          this.ngxSpinnerService.hide().then();
          this.messageService.add({severity: 'error', summary: 'Erreur', detail: err['message']});
        }
      });
    }
  }

  onSelect(selection: ITypeHopital, edit: boolean){
    if (edit){
      this.resetForm(selection);
    }
  }

  getSeverity(enabled: boolean) {
    if (enabled){
      return 'success';
    }else {
      return 'danger';
    }
  }

  resetForm(typeHopital?: ITypeHopital): void {
    this.editForm = this.typeHopitalFormService.createFormGroup(typeHopital);
  }

  reset(table: Table) {
    table.clear();
    this.searchValue = ''
  }
}

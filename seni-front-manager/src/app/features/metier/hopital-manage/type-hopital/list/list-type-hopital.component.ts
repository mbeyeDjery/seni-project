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
    NgxSpinnerComponent
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

  editForm = this.typeHopitalFormService.createTypeHopitalFormGroup();

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.ngxSpinnerService.show();
    this.resetForm();
    this.typeHopitalService.getAll().subscribe({
      next: response => this.typeHopitals = response,
      error: err => this.messageService.add({severity:'error', summary: 'Erreur', detail: 'Certaines données ne peuvent pas être chargées'})
    });
    this.ngxSpinnerService.hide();
  }

  save(){
    this.ngxSpinnerService.show();
    const typeHopital = this.typeHopitalFormService.getTypeHopital(this.editForm);

    if (typeHopital.idTypeHopital){
      this.typeHopitalService.update(typeHopital).subscribe({
        next: value => {
          this.messageService.add({severity: 'info', summary: 'Succès', detail: 'Enregistrer avec succès'});
          this.load();
        },
        error: err => {
          this.ngxSpinnerService.hide();
          this.messageService.add({severity: 'error', summary: 'Erreur', detail: err['message']});
        }
      });
    }else {
      this.typeHopitalService.create(typeHopital).subscribe({
        next: value => {
          this.messageService.add({severity: 'info', summary: 'Succès', detail: value.message});
          this.load();
        },
        error: err => {
          this.ngxSpinnerService.hide();
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
    this.editForm = this.typeHopitalFormService.createTypeHopitalFormGroup(typeHopital);
  }

  reset(table: Table) {
    table.clear();
    this.searchValue = ''
  }
}

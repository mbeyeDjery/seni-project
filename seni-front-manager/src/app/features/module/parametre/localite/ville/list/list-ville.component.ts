import {Component, inject, OnInit} from '@angular/core';
import {IProvince} from "../../../../../../core/model/province";
import {MessageService, PrimeTemplate} from "primeng/api";
import {LocaliteService} from "../../../../../../core/services/localite.service";
import {NgxSpinnerComponent, NgxSpinnerService} from "ngx-spinner";
import {Table, TableModule} from "primeng/table";
import {Button, ButtonDirective} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";
import {InputTextModule} from "primeng/inputtext";
import {PanelModule} from "primeng/panel";
import {ReactiveFormsModule} from "@angular/forms";
import {ToastModule} from "primeng/toast";
import {IVille} from "../../../../../../core/model/ville";
import {VilleFormService} from "../../../../../../core/services/form-group/ville-form.service";

@Component({
  selector: 'app-list-ville',
  standalone: true,
  imports: [
    Button,
    ButtonDirective,
    DropdownModule,
    InputTextModule,
    NgxSpinnerComponent,
    PanelModule,
    PrimeTemplate,
    ReactiveFormsModule,
    TableModule,
    ToastModule
  ],
  templateUrl: './list-ville.component.html',
  styles: ``,
  providers: [MessageService]
})
export class ListVilleComponent implements OnInit{
  villes?: IVille[];
  provinces?: IProvince[];
  protected messageService = inject(MessageService);
  protected localiteService = inject(LocaliteService);
  protected villeFormService = inject(VilleFormService);
  protected ngxSpinnerService = inject(NgxSpinnerService);

  editForm = this.villeFormService.createFormGroup();

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.ngxSpinnerService.show().then().then();
    this.resetForm();
    this.localiteService.getAllProvince().subscribe({
      next: response => (this.provinces = response),
      error: err => {
        console.warn(err);
        this.messageService.add({
          severity: 'error',
          summary: 'Erreur',
          detail: 'Certaines données ne peuvent pas être chargées'
        });
      }
    });

    this.localiteService.getAllVille().subscribe({
      next: response => (this.villes = response),
      error: err => {
        console.warn(err);
        this.messageService.add({
          severity: 'error',
          summary: 'Erreur',
          detail: 'Certaines données ne peuvent pas être chargées'
        });
      }
    });

    this.ngxSpinnerService.hide().then();
  }

  save(): void  {
    this.ngxSpinnerService.show().then();
    const ville = this.villeFormService.get(this.editForm);
    if (ville.idVille){
      this.localiteService.updateVille(ville).subscribe({
        next: () => {
          this.load();
          this.messageService.add({severity: 'info', summary: 'Succès', detail: 'Sauvergarder avec succès'});
        },
        error: err => {
          this.ngxSpinnerService.hide().then();
          this.messageService.add({severity: 'error', summary: 'Erreur', detail: err['message']});
        }
      });
    }else {
      this.localiteService.createVille(ville).subscribe({
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
    this.ngxSpinnerService.hide().then();
  }

  onSelect(selection: IVille, edit: boolean){
    if (edit){
      this.editForm = this.villeFormService.createFormGroup(selection);
    }else {
      this.editForm = this.villeFormService.createFormGroup();
    }
  }

  onGlobalFilter(table: Table, event: Event) {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }

  reset(table: Table) {
    table.clear();
  }

  resetForm() {
    this.editForm = this.villeFormService.createFormGroup();
  }
}

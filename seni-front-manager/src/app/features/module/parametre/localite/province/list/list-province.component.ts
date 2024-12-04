import {Component, inject} from '@angular/core';
import {LocaliteService} from "../../../../../../core/services/localite.service";
import {MessageService, PrimeTemplate} from "primeng/api";
import {NgxSpinnerComponent, NgxSpinnerService} from "ngx-spinner";
import {Table, TableModule} from "primeng/table";
import {ProvinceFormService} from "../../../../../../core/services/form-group/province-form.service";
import {IProvince} from "../../../../../../core/model/province";
import {Button, ButtonDirective} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {PanelModule} from "primeng/panel";
import {ReactiveFormsModule} from "@angular/forms";
import {ToastModule} from "primeng/toast";
import {IRegion} from "../../../../../../core/model/region";
import {DropdownModule} from "primeng/dropdown";

@Component({
  selector: 'app-list-province',
  standalone: true,
  imports: [
    Button,
    ButtonDirective,
    InputTextModule,
    NgxSpinnerComponent,
    PanelModule,
    PrimeTemplate,
    ReactiveFormsModule,
    TableModule,
    ToastModule,
    DropdownModule
  ],
  templateUrl: './list-province.component.html',
  styles: ``,
  providers: [MessageService]
})
export class ListProvinceComponent {
  regions?: IRegion[];
  provinces?: IProvince[];
  protected messageService = inject(MessageService);
  protected localiteService = inject(LocaliteService);
  protected ngxSpinnerService = inject(NgxSpinnerService);
  protected provinceFormService = inject(ProvinceFormService);

  editForm = this.provinceFormService.createFormGroup();

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.ngxSpinnerService.show().then();
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

    this.localiteService.getAllRegion().subscribe({
      next: response => (this.regions = response),
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
    const province = this.provinceFormService.get(this.editForm);
    if (province.idProvince){
      this.localiteService.updateProvince(province).subscribe({
        next: value => {
          this.load();
          this.messageService.add({severity: 'info', summary: 'Succès', detail: 'Sauvergarder avec succès'});
        },
        error: err => {
          this.ngxSpinnerService.hide().then();
          this.messageService.add({severity: 'error', summary: 'Erreur', detail: err['message']});
        }
      });
    }else {
      this.localiteService.createProvince(province).subscribe({
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

  onSelect(selection: IProvince, edit: boolean){
    if (edit){
      this.editForm = this.provinceFormService.createFormGroup(selection);
    }else {
      this.editForm = this.provinceFormService.createFormGroup();
    }
  }

  onGlobalFilter(table: Table, event: Event) {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }

  reset(table: Table) {
    table.clear();
  }

  resetForm() {
    this.editForm = this.provinceFormService.createFormGroup();
  }
}

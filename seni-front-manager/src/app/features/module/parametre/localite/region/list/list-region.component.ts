import {Component, inject, OnInit} from '@angular/core';
import {CardModule} from "primeng/card";
import {NgxSpinnerComponent, NgxSpinnerService} from "ngx-spinner";
import {ToastModule} from "primeng/toast";
import {PanelModule} from "primeng/panel";
import {MessageService} from "primeng/api";
import {LocaliteService} from "../../../../../../core/services/localite.service";
import {IRegion} from "../../../../../../core/model/region";
import {Button, ButtonDirective} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {PaginatorModule} from "primeng/paginator";
import {Table, TableModule} from "primeng/table";
import {TagModule} from "primeng/tag";
import {RegionFormService} from "../../../../../../core/services/form-group/region-form.service";
import {ReactiveFormsModule} from "@angular/forms";
import {InputTextareaModule} from "primeng/inputtextarea";

@Component({
  selector: 'app-list-region',
  standalone: true,
  imports: [
    CardModule,
    NgxSpinnerComponent,
    ToastModule,
    PanelModule,
    Button,
    InputTextModule,
    PaginatorModule,
    TableModule,
    TagModule,
    ReactiveFormsModule,
    ButtonDirective,
    InputTextareaModule
  ],
  templateUrl: './list-region.component.html',
  styles: ``,
  providers: [MessageService]
})
export class ListRegionComponent implements OnInit {

  regions?: IRegion[];
  protected regionService = inject(LocaliteService);
  protected messageService = inject(MessageService);
  protected ngxSpinnerService = inject(NgxSpinnerService);
  protected regionFormService = inject(RegionFormService);

  editForm = this.regionFormService.createFormGroup();

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.ngxSpinnerService.show().then();
    this.resetForm();
    this.regionService.getAllRegion().subscribe({
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
    const region = this.regionFormService.get(this.editForm);
    if (region.idRegion){
      this.regionService.updateRegion(region).subscribe({
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
      this.regionService.createRegion(region).subscribe({
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

  onSelect(selection: IRegion, edit: boolean){
    if (edit){
      this.editForm = this.regionFormService.createFormGroup(selection);
    }else {
      this.editForm = this.regionFormService.createFormGroup();
    }
  }

  onGlobalFilter(table: Table, event: Event) {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }

  reset(table: Table) {
    table.clear();
  }

  resetForm() {
    this.editForm = this.regionFormService.createFormGroup();
  }
}

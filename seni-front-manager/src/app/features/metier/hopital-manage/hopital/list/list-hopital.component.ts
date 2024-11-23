import {Component, inject, OnInit, signal} from '@angular/core';
import {MessageService} from "primeng/api";
import {ToastModule} from "primeng/toast";
import {HopitalService} from "../../../../../core/services/hopital.service";
import {NgxSpinnerComponent, NgxSpinnerService} from "ngx-spinner";
import {IHopital} from "../../../../../core/model/hopital-model";
import {Button} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {PaginatorModule} from "primeng/paginator";
import {Table, TableModule} from "primeng/table";
import {TagModule} from "primeng/tag";
import {ToolbarModule} from "primeng/toolbar";
import {CardModule} from "primeng/card";
import {RatingModule} from "primeng/rating";
import {IHopitalStatus} from "../../../../../core/model/enum/hopital-status";
import {NgStyle} from "@angular/common";
import {DialogService} from "primeng/dynamicdialog";
import {EditHopitalComponent} from "../edit/edit-hopital.component";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-list-hopital',
  standalone: true,
  imports: [
    ToastModule,
    NgxSpinnerComponent,
    Button,
    InputTextModule,
    PaginatorModule,
    TableModule,
    TagModule,
    ToolbarModule,
    CardModule,
    RatingModule,
    NgStyle,
    RouterLink
  ],
  templateUrl: './list-hopital.component.html',
  styles: ``,
  providers: [MessageService, DialogService]
})
export class ListHopitalComponent  implements OnInit {

  hopitaux?: IHopital[];
  hopitauxSelect?: IHopital[];
  searchValue: string | undefined;

  protected dialogService = inject(DialogService);
  protected hopitalService = inject(HopitalService);
  protected messageService = inject(MessageService);
  protected ngxSpinnerService = inject(NgxSpinnerService);

  ngOnInit(): void {
    this.ngxSpinnerService.show().then();
    this.load();
    this.ngxSpinnerService.hide().then();
  }

  load(): void {
    this.hopitalService.getAll().subscribe({
      next: value => this.hopitaux = value,
      error: err => {
        this.messageService.add({severity: 'error', summary: 'Erreur', detail: 'Chargement impossible'});
      }
    });
  }

  edition(hopitalEdit: IHopital){
    const dialog = this.dialogService.open(EditHopitalComponent, {
      position: 'top',
      closable: false,
      closeOnEscape: false,
      draggable: false,
      data: {
        hopital: hopitalEdit
      },
      header: 'Editer un hopital',
      contentStyle: { overflow: 'auto' },
    });

    dialog.onClose.subscribe((finish: boolean) => {
      if (finish) {
        this.messageService.add({severity:'info', summary: 'Succès', detail:'Modification effectuée avec succès'});
        this.load();
      }else {
        // this.messageService.add({severity:'warn', summary: 'Information', detail:'Modification de compte annulée'});
      }
    });
  }

  nouveau(){
    const dialog = this.dialogService.open(EditHopitalComponent, {
      position: 'top',
      closable: false,
      closeOnEscape: false,
      draggable: false,
      header: 'Créer un nouvel hopital',
      contentStyle: { overflow: 'auto' },
    });

    dialog.onClose.subscribe((finish: boolean) => {
      if (finish) {
        this.messageService.add({severity:'info', summary: 'Succès', detail:'Hopital créé avec succès'});
        this.load();
      }else {
        // this.messageService.add({severity:'warn', summary: 'Information', detail:'Modification de compte annulée'});
      }
    });
  }

  getOnline(online: boolean) {
    if (online){
      return 'success';
    }else {
      return 'danger';
    }
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

  resetTable(table: Table) {
    table.clear();
    this.searchValue = ''
  }
}

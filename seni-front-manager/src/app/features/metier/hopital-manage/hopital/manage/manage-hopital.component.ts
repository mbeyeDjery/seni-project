import {Component, inject, OnInit} from '@angular/core';
import {IHopital} from "../../../../../core/model/hopital-model";
import {ActivatedRoute} from "@angular/router";
import {NgxSpinnerComponent} from "ngx-spinner";
import {ToastModule} from "primeng/toast";
import {MenuItem, MessageService} from "primeng/api";
import {Button} from "primeng/button";
import {CardModule} from "primeng/card";
import {ImageModule} from "primeng/image";
import {TagModule} from "primeng/tag";
import {IHopitalStatus} from "../../../../../core/model/enum/hopital-status";
import {PanelModule} from "primeng/panel";
import {AvatarModule} from "primeng/avatar";
import {MenuModule} from "primeng/menu";
import {EditHopitalComponent} from "../edit/edit-hopital.component";
import {DialogService} from "primeng/dynamicdialog";
import {TabViewModule} from "primeng/tabview";

@Component({
  selector: 'app-manage-hopital',
  standalone: true,
  imports: [
    NgxSpinnerComponent,
    ToastModule,
    Button,
    CardModule,
    ImageModule,
    TagModule,
    PanelModule,
    AvatarModule,
    MenuModule,
    TabViewModule
  ],
  templateUrl: './manage-hopital.component.html',
  styles: ``,
  providers: [MessageService, DialogService]
})
export class ManageHopitalComponent implements OnInit {
  hopital: IHopital | null = null;
  items: MenuItem[] = this.getOptions();

  protected dialogService = inject(DialogService);
  protected activatedRoute = inject(ActivatedRoute);
  protected messageService = inject(MessageService);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hopital }) => {
      this.hopital = hopital;

    });
  }

  modifierHopital(){
    console.warn('fghlkjsfhlk')
    const dialog = this.dialogService.open(EditHopitalComponent, {
      position: 'top',
      closable: false,
      closeOnEscape: false,
      draggable: false,
      data: {
        hopital: this.hopital
      },
      header: 'Editer un hopital',
      contentStyle: { overflow: 'auto' },
    });

    dialog.onClose.subscribe((finish: boolean) => {
      if (finish) {
        this.messageService.add({severity:'info', summary: 'Succès', detail:'Modification effectuée avec succès'});
        window.location.reload();
      }else {
        // this.messageService.add({severity:'warn', summary: 'Information', detail:'Modification de compte annulée'});
      }
    });
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

  getOptions(): MenuItem[] {
    return [
      {
        label: 'Options',
        items: [
          {
            label: 'Modifier',
            icon: 'pi pi-pencil',
            command: () => {
              this.modifierHopital();
            }
          },
          {
            label: 'Imprimer',
            icon: 'pi pi-print',
          }
        ]
      }
    ]
  }
}

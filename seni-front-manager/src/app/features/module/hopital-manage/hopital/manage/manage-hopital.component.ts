import {Component, inject, OnInit} from '@angular/core';
import {IHopital} from "../../../../../core/model/hopital-model";
import {ActivatedRoute} from "@angular/router";
import {NgxSpinnerComponent, NgxSpinnerService} from "ngx-spinner";
import {ToastModule} from "primeng/toast";
import {MenuItem, MessageService} from "primeng/api";
import {Button, ButtonDirective} from "primeng/button";
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
import {ContratHopitalComponent} from "./contrat/contrat-hopital.component";
import {ChipModule} from "primeng/chip";
import {Ripple} from "primeng/ripple";
import {NgIf} from "@angular/common";
import {RatingModule} from "primeng/rating";
import {FormsModule} from "@angular/forms";
import {EditStatutComponent} from "../edit/statut/edit-statut.component";
import {HopitalService} from "../../../../../core/services/hopital.service";
import {ListUserHopitalComponent} from "../../../../user/hopital/list/list-user-hopital.component";
import {ListUserHopitalIntegreComponent} from "../../../../user/hopital/list/list-user-hopital-integre.component";

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
        TabViewModule,
        ContratHopitalComponent,
        ChipModule,
        ButtonDirective,
        Ripple,
        NgIf,
        RatingModule,
        FormsModule,
        ListUserHopitalIntegreComponent,
    ],
  templateUrl: './manage-hopital.component.html',
  styles: ``,
  providers: [MessageService, DialogService]
})
export class ManageHopitalComponent implements OnInit {
  value: number = 5;
  hopital: IHopital | null = null;
  items: MenuItem[] = this.getOptions();

  protected dialogService = inject(DialogService);
  protected hopitalService = inject(HopitalService);
  protected activatedRoute = inject(ActivatedRoute);
  protected messageService = inject(MessageService);
  protected ngxSpinnerService = inject(NgxSpinnerService);

  ngOnInit(): void {
    this.load(true);
  }

  load(onLoad?: boolean): void {
    this.ngxSpinnerService.show().then();
    this.activatedRoute.data.subscribe(({ hopital }) => {
      if (onLoad){
        this.hopital = hopital;
      } else {
        this.hopitalService.findOne(this.hopital.idHopital).subscribe({
          next: response => (this.hopital = response),
          error: err => this.messageService.add({severity:'error', summary: 'Erreur', detail:'Erreur de chargement'}),
        });
      }
    });
    this.ngxSpinnerService.hide().then();
  }

  modifierHopital(){
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
        this.load();
      }else {
        // this.messageService.add({severity:'warn', summary: 'Information', detail:'Modification de compte annulée'});
      }
    });
  }

  modifierStatut(){
    const dialog = this.dialogService.open(EditStatutComponent, {
      position: 'top',
      closable: false,
      closeOnEscape: false,
      draggable: false,
      data: {
        hopital: this.hopital
      },
      header: 'Changer de statut',
      contentStyle: { overflow: 'auto' },
    });

    dialog.onClose.subscribe((finish: boolean) => {
      if (finish) {
        this.messageService.add({severity:'info', summary: 'Succès', detail:'Le statut a été changé avec succès'});
        this.load();
      }else {
        // this.messageService.add({severity:'warn', summary: 'Information', detail:'Modification de compte annulée'});
      }
    });
  }

  getStatut(statut: string) {
    if (statut.includes(IHopitalStatus.PENDING)){
      return 'bg-orange-200';
    }else if (statut.includes(IHopitalStatus.ACTIVATED)){
      return 'bg-green-200';
    }else {
      return 'bg-red-200';
    }
  }

  getOptions(): MenuItem[] {
    return [
      {
        label: 'Options',
        items: [
          {
            label: 'Statut',
            icon: 'pi pi-hourglass',
            command: () => {
              this.modifierStatut();
            }
          },
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

  protected readonly fetch = fetch;
}

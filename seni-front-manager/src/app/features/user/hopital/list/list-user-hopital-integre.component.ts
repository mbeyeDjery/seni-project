import {Component, inject, Input, OnInit} from '@angular/core';
import {IHopital} from "../../../../core/model/hopital-model";
import {IAppUser} from "../../../../core/model/app-user-model";
import {DialogService} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {NgxSpinnerService} from "ngx-spinner";
import {UserHopitalService} from "../../../../core/services/user-hopital.service";

@Component({
  selector: 'app-list-user-hopital-integre',
  standalone: true,
  imports: [],
  templateUrl: './list-user-hopital-integre.component.html',
  styles: `
        :host ::ng-deep {
            .p-progressbar {
                height: .5rem;
                background-color: #D8DADC;

                .p-progressbar-value {
                    background-color: #607D8B;
                }
            }
        }`,
  providers: [MessageService, DialogService, ConfirmationService]
})
export class ListUserHopitalIntegreComponent implements OnInit  {
  @Input({required: true}) hopital: IHopital;

  appUsers?: IAppUser[];

  protected dialogService = inject(DialogService);
  protected messageService = inject(MessageService);
  protected appUserService = inject(UserHopitalService);
  protected ngxSpinnerService = inject(NgxSpinnerService);
  protected confirmationService = inject(ConfirmationService);

  ngOnInit(): void {
    this.ngxSpinnerService.show().then();
    this.load();
  }

  load(): void {
    this.appUserService.getAllUsers(this.hopital.idHopital).subscribe(
        {
          next: (response) => {
            this.appUsers = response;
            console.warn(response);
            this.ngxSpinnerService.hide().then();
          }, error: (err) => {
            this.ngxSpinnerService.hide().then();
            this.messageService.add({severity:'error', summary: 'Erreur', detail: 'Certaine données ne peuvent pas être chargées'});
          }
        }
    );
  }
}

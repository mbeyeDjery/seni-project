import {Injectable} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {IHopital} from "../../model/hopital-model";
import {IContratHopital} from "../../model/contrat-hopital";
import {IContratHopitalStatus} from "../../model/enum/contrat-hopital-status";

@Injectable({
  providedIn: 'root'
})
export class ContratHopitalFormService {
  createFormGroup(contrat?: IContratHopital) {
    return new FormGroup({
      idContrattHopital: new FormControl(contrat ? contrat.idContrattHopital : ''),
      reference: new FormControl(contrat ? contrat.reference :'', { nonNullable: true, validators: [Validators.required] }),
      hopital : new FormControl<IHopital>( contrat ? contrat.hopital : null, { nonNullable: true, validators: [Validators.required] }),
      dateDebut: new FormControl(contrat ? contrat.dateDebut : null, { nonNullable: true, validators: [Validators.required] }),
      dateFin: new FormControl(contrat ? contrat.dateFin : null, { nonNullable: true, validators: [Validators.required] }),
      statut: new FormControl(contrat ? contrat.statut : IContratHopitalStatus.ACTIVATED, { nonNullable: true, validators: [Validators.required] }),
      note: new FormControl( contrat ? contrat.note :''),
      enabled: new FormControl<boolean>(contrat ? contrat.enabled : true),
    });
  }

  get(form: FormGroup): IContratHopital {
    return form.getRawValue() as IContratHopital;
  }
}

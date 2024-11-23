import { Injectable } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ITypeHopital} from "../../model/type-hopital-model";
import {IHopitalStatus} from "../../model/enum/hopital-status";
import {IHopital} from "../../model/hopital-model";

@Injectable({
  providedIn: 'root'
})
export class HopitalFormService {
  createHopitalFormGroup(hopital?: IHopital) {
    return new FormGroup({
      idHopital: new FormControl(hopital ? hopital.idHopital :''),
      codeHopital: new FormControl(hopital ? hopital.codeHopital :''),
      typeHopital : new FormControl<ITypeHopital>( hopital ? hopital.typeHopital : null, { nonNullable: true, validators: [Validators.required] }),
      nom: new FormControl(hopital ? hopital.nom : '', { nonNullable: true, validators: [Validators.required] }),
      sigle: new FormControl(hopital ? hopital.sigle : ''),
      slogan: new FormControl( hopital ? hopital.slogan :''),
      telephone: new FormControl(hopital ? hopital.telephone : '', { nonNullable: true, validators: [Validators.required] }),
      email: new FormControl(hopital ? hopital.email : ''),
      siteWeb: new FormControl(hopital ? hopital.siteWeb : ''),
      statut: new FormControl(hopital ? hopital.statut : IHopitalStatus.PENDING),
      description: new FormControl(''),
      addresse: new FormControl(hopital ? hopital.addresse : ''),
      online: new FormControl(hopital ? hopital.online : false),
    });
  }

  getHopital(form: FormGroup): IHopital {
    return form.getRawValue() as IHopital;
  }
}

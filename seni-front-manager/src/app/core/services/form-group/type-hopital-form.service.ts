import { Injectable } from '@angular/core';
import {ITypeHopital} from "../../model/type-hopital-model";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class TypeHopitalFormService {

  createFormGroup(typeHopital?: ITypeHopital){
    return new FormGroup({
      idTypeHopital: new FormControl(typeHopital ? typeHopital.idTypeHopital : ''),
      enabled: new FormControl(typeHopital ? typeHopital.enabled : true),
      description: new FormControl(typeHopital ? typeHopital.description : '', { nonNullable: false }),
      libelle: new FormControl(typeHopital ? typeHopital.libelle : '', { nonNullable: true, validators: [Validators.required] }),
    });
  }

  get(form: FormGroup): ITypeHopital {
    return form.getRawValue() as ITypeHopital;
  }
}

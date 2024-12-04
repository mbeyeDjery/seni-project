import { Injectable } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {IProvince} from "../../model/province";
import {IVille} from "../../model/ville";

@Injectable({
  providedIn: 'root'
})
export class VilleFormService {

  createFormGroup(ville?: IVille){
    return new FormGroup({
      idVille: new FormControl(ville ? ville.idVille : ''),
      nom: new FormControl(ville ? ville.nom : '', { nonNullable: false, validators: [Validators.required] }),
      province : new FormControl<IProvince>( ville ? ville.province : null, { nonNullable: true, validators: [Validators.required] }),
      description: new FormControl(''),
    });
  }

  get(form: FormGroup): IVille {
    return form.getRawValue() as IVille;
  }
}

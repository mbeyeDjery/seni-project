import { Injectable } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {IProvince} from "../../model/province";
import {IRegion} from "../../model/region";

@Injectable({
  providedIn: 'root'
})
export class ProvinceFormService {

  createFormGroup(province?: IProvince){
    return new FormGroup({
      idProvince: new FormControl(province ? province.idProvince : ''),
      nom: new FormControl(province ? province.nom : '', { nonNullable: false, validators: [Validators.required] }),
      region : new FormControl<IRegion>( province ? province.region : null, { nonNullable: true, validators: [Validators.required] }),
      description: new FormControl(''),
    });
  }

  get(form: FormGroup): IProvince {
    return form.getRawValue() as IProvince;
  }
}

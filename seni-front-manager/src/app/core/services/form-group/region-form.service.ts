import { Injectable } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {IRegion} from "../../model/region";

@Injectable({
  providedIn: 'root'
})
export class RegionFormService {

  createFormGroup(region?: IRegion){
    return new FormGroup({
      idRegion: new FormControl(region ? region.idRegion : ''),
      codeInsd: new FormControl(region ? region.codeInsd : ''),
      nom: new FormControl(region ? region.nom : '', { nonNullable: false, validators: [Validators.required] }),
      description: new FormControl(region ? region.description : ''),
    });
  }

  get(form: FormGroup): IRegion {
    return form.getRawValue() as IRegion;
  }
}

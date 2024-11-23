import { Injectable } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {IAppUser} from "../../model/app-user-model";
import {IAppRole} from "../../model/app-role-model";

@Injectable({
  providedIn: 'root'
})
export class ProfileFormService {
  createProfileFormGroup(appUser?: IAppUser){
    return new FormGroup({
      firstName: new FormControl(appUser ? appUser.firstName : '', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
      lastName: new FormControl(appUser ? appUser.lastName :'', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
      email: new FormControl(appUser ? appUser.email : '', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
      telephone: new FormControl(appUser ? appUser.telephone : '', { nonNullable: true, validators: [Validators.required, Validators.minLength(13)] }),
    });
  }

  createUserFormGroup(appUser?: IAppUser){
    return new FormGroup({
      userName: new FormControl(appUser ? appUser.username : '', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
      firstName: new FormControl(appUser ? appUser.firstName : '', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
      lastName: new FormControl(appUser ? appUser.lastName :'', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
      email: new FormControl(appUser ? appUser.email : ''),
      telephone: new FormControl(appUser ? appUser.telephone : '', { nonNullable: true, validators: [Validators.required, Validators.minLength(8)] }),
      role: new FormControl<IAppRole>(appUser ? appUser.roles[0] : null, { nonNullable: true, validators: [Validators.required] }),
      enabled: new FormControl(appUser ? appUser.enabled : true),
      nouvreau: new FormControl(!!appUser),
    });
  }

  getAppUser(form: FormGroup): IAppUser {
    return form.getRawValue() as IAppUser;
  }
}
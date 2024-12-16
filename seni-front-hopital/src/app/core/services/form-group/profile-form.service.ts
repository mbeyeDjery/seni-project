import { Injectable } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {IAppUser} from "../../model/app-user-model";
import {IAppRole} from "../../model/app-role-model";

@Injectable({
  providedIn: 'root'
})
export class ProfileFormService {

  createUserHopitalFormGroup(appUser?: IAppUser){
    return new FormGroup({
      idUser: new FormControl(appUser ? appUser.idUser : null),
      username: new FormControl(appUser ? appUser.username : '', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
      hopital: new FormControl(appUser ? appUser.hopital : '', { nonNullable: true, validators: [Validators.required] }),
      firstName: new FormControl(appUser ? appUser.firstName : '', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
      lastName: new FormControl(appUser ? appUser.lastName :'', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
      email: new FormControl(appUser ? appUser.email : ''),
      telephone: new FormControl(appUser ? appUser.telephone : '', { nonNullable: true, validators: [Validators.required, Validators.minLength(8)] }),
      roles: new FormControl<IAppRole[]>(appUser ? appUser.roles : null, { nonNullable: true, validators: [Validators.required] }),
      enabled: new FormControl(appUser ? appUser.enabled : true),
    });
  }

  get(form: FormGroup): IAppUser {
    return form.getRawValue() as IAppUser;
  }
}

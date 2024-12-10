import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ApplicationConfigService} from "../config/application-config.service";
import {HOPITAL_COMMAND, HOPITAL_QUERY} from "../utils/constants";
import {Observable} from "rxjs";
import {ITypeHopital} from "../model/type-hopital-model";
import {IAggregateCreateResponseModel} from "../model/request/aggregate-create-response-model";

@Injectable({
  providedIn: 'root'
})
export class TypeHopitalService {

  private http = inject(HttpClient);
  private applicationConfigService = inject(ApplicationConfigService);
  private serverQuery = this.applicationConfigService.getEndpointFor('/query/type-hopital', HOPITAL_QUERY);
  private serverCommand = this.applicationConfigService.getEndpointFor('/manager/command/type-hopital', HOPITAL_COMMAND);


  create(typeHopital: ITypeHopital): Observable<IAggregateCreateResponseModel> {
    return this.http.post<IAggregateCreateResponseModel>(this.serverCommand, typeHopital);
  }

  update(typeHopital: ITypeHopital): Observable<void> {
    return this.http.put<void>(this.serverCommand, typeHopital);
  }

  delete(idTypeHopital: string): Observable<void> {
    return this.http.delete<void>(`${this.serverCommand}/${idTypeHopital}`);
  }

  getAll(): Observable<ITypeHopital[]>{
    return this.http.get<ITypeHopital[]>(this.serverQuery);
  }

  findOne(idTypeHopital: string): Observable<ITypeHopital>{
    return this.http.get<ITypeHopital>(`${this.serverQuery}/${idTypeHopital}`);
  }

  findByEnabled(enabled: boolean): Observable<ITypeHopital[]>{
    return this.http.get<ITypeHopital[]>(`${this.serverQuery}/enabled/${enabled}`);
  }
}

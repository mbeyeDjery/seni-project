import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ApplicationConfigService} from "../config/application-config.service";
import {HOPITAL_COMMAND, HOPITAL_QUERY} from "../utils/constants";
import {Observable} from "rxjs";
import {IAggregateCreateResponseModel} from "../model/request/aggregate-create-response-model";
import {IContratHopital} from "../model/contrat-hopital";
import {IHopitalStatutRequest} from "../model/request/hopital-statut-request";
import {IContratStatutRequest} from "../model/request/contrat-statut-request";
import {IContratHopitalStatus} from "../model/enum/contrat-hopital-status";

@Injectable({
  providedIn: 'root'
})
export class ContratHopitalService {

  private http = inject(HttpClient);
  private applicationConfigService = inject(ApplicationConfigService);
  private serverQuery = this.applicationConfigService.getEndpointFor('/query/contrat-hopital', HOPITAL_QUERY);
  private serverCommand = this.applicationConfigService.getEndpointFor('/manager/command/contrat-hopital', HOPITAL_COMMAND);

  create(contrat: IContratHopital): Observable<IAggregateCreateResponseModel> {
    return this.http.post<IAggregateCreateResponseModel>(this.serverCommand, contrat);
  }

  update(contrat: IContratHopital): Observable<void> {
    return this.http.put<void>(this.serverCommand, contrat);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.serverCommand}/${id}`);
  }

  getAll(idHopital: string): Observable<IContratHopital[]>{
    return this.http.get<IContratHopital[]>(`${this.serverQuery}/${idHopital}`);
  }

  findOne(id: string): Observable<IContratHopital>{
    return this.http.get<IContratHopital>(`${this.serverQuery}/${id}`);
  }

  updateStatut(statutRequest: IContratStatutRequest): Observable<void> {
    return this.http.patch<void>(`${this.serverCommand}/statut`, statutRequest);
  }

  getByHopitalAndStatut(idHopital: string, statut: IContratHopitalStatus): Observable<IContratHopital[]>{
    return this.http.get<IContratHopital[]>(`${this.serverQuery}/${idHopital}/${statut}`);
  }
}

import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ApplicationConfigService} from "../config/application-config.service";
import {HOPITAL_COMMAND, HOPITAL_QUERY} from "../utils/constants";
import {Observable} from "rxjs";
import {IAggregateCreateResponseModel} from "../model/aggregate-create-response-model";
import {IHopital} from "../model/hopital-model";
import {IHopitalStatutRequest} from "../model/request/hopital-statut-request";

@Injectable({
  providedIn: 'root'
})
export class HopitalService {

  private http = inject(HttpClient);
  private applicationConfigService = inject(ApplicationConfigService);
  private serverQuery = this.applicationConfigService.getEndpointFor('/query/hopital', HOPITAL_QUERY);
  private serverCommand = this.applicationConfigService.getEndpointFor('/manager/command/hopital', HOPITAL_COMMAND);

  create(hopital: IHopital): Observable<IAggregateCreateResponseModel> {
    return this.http.post<IAggregateCreateResponseModel>(this.serverCommand, hopital);
  }

  update(hopital: IHopital): Observable<void> {
    return this.http.put<void>(this.serverCommand, hopital);
  }

  delete(idHopital: string): Observable<void> {
    return this.http.delete<void>(`${this.serverCommand}/${idHopital}`);
  }

  getAll(): Observable<IHopital[]>{
    return this.http.get<IHopital[]>(this.serverQuery);
  }

  findOne(idHopital: string): Observable<IHopital>{
    return this.http.get<IHopital>(`${this.serverQuery}/${idHopital}`);
  }

  updateStatut(statutRequest: IHopitalStatutRequest): Observable<void> {
    return this.http.patch<void>(`${this.serverCommand}/statut`, statutRequest);
  }
}

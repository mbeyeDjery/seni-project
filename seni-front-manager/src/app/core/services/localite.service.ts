import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ApplicationConfigService} from "../config/application-config.service";
import {PARAMETRE_COMMAND, PARAMETRE_QUERY} from "../utils/constants";
import {Observable} from "rxjs";
import {IAggregateCreateResponseModel} from "../model/aggregate-create-response-model";
import {IRegion} from "../model/region";
import {IProvince} from "../model/province";
import {IVille} from "../model/ville";
import {ISecteur} from "../model/secteur";

@Injectable({
  providedIn: 'root'
})
export class LocaliteService {
  private http = inject(HttpClient);
  private applicationConfigService = inject(ApplicationConfigService);
  private serverQuery = this.applicationConfigService.getEndpointFor('/query/localite', PARAMETRE_QUERY);
  private serverCommand = this.applicationConfigService.getEndpointFor('/manager/command', PARAMETRE_COMMAND);

  // Gestion des regions
  createRegion(region: IRegion): Observable<IAggregateCreateResponseModel> {
    return this.http.post<IAggregateCreateResponseModel>(`${this.serverCommand}/region`, region);
  }

  updateRegion(region: IRegion): Observable<void> {
    return this.http.put<void>(`${this.serverCommand}/region`, region);
  }

  deleteRegion(id: string): Observable<void> {
    return this.http.delete<void>(`${this.serverCommand}/region/${id}`);
  }

  getAllRegion(): Observable<IRegion[]>{
    return this.http.get<IRegion[]>(`${this.serverQuery}/region`);
  }

  findOneRegion(id: string): Observable<IRegion>{
    return this.http.get<IRegion>(`${this.serverQuery}/region/${id}`);
  }

  // Gestion des Province
  createProvince(region: IProvince): Observable<IAggregateCreateResponseModel> {
    return this.http.post<IAggregateCreateResponseModel>(`${this.serverCommand}/province`, region);
  }

  updateProvince(region: IProvince): Observable<void> {
    return this.http.put<void>(`${this.serverCommand}/province`, region);
  }

  deleteProvince(id: string): Observable<void> {
    return this.http.delete<void>(`${this.serverCommand}/province/${id}`);
  }

  getAllProvince(): Observable<IProvince[]>{
    return this.http.get<IProvince[]>(`${this.serverQuery}/province`);
  }

  findOneProvince(id: string): Observable<IProvince>{
    return this.http.get<IProvince>(`${this.serverQuery}/province/${id}`);
  }

  // Gestion des Villes
  createVille(ville: IVille): Observable<IAggregateCreateResponseModel> {
    return this.http.post<IAggregateCreateResponseModel>(`${this.serverCommand}/ville`, ville);
  }

  updateVille(ville: IVille): Observable<void> {
    return this.http.put<void>(`${this.serverCommand}/ville`, ville);
  }

  deleteVille(id: string): Observable<void> {
    return this.http.delete<void>(`${this.serverCommand}/ville/${id}`);
  }

  getAllVille(): Observable<IVille[]>{
    return this.http.get<IVille[]>(`${this.serverQuery}/ville`);
  }

  findOneVille(id: string): Observable<IVille>{
    return this.http.get<IVille>(`${this.serverQuery}/ville/${id}`);
  }

  // Gestion des Secteurs
  createSecteur(secteur: ISecteur): Observable<IAggregateCreateResponseModel> {
    return this.http.post<IAggregateCreateResponseModel>(`${this.serverCommand}/secteur`, secteur);
  }

  updateSecteur(secteur: ISecteur): Observable<void> {
    return this.http.put<void>(`${this.serverCommand}/secteur`, secteur);
  }

  deleteSecteur(id: string): Observable<void> {
    return this.http.delete<void>(`${this.serverCommand}/secteur/${id}`);
  }

  getAllSecteur(): Observable<ISecteur[]>{
    return this.http.get<ISecteur[]>(`${this.serverQuery}/secteur`);
  }

  findOneSecteur(id: string): Observable<ISecteur>{
    return this.http.get<ISecteur>(`${this.serverQuery}/secteur/${id}`);
  }
}

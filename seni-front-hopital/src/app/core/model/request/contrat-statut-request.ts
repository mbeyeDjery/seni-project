import {IContratHopitalStatus} from "../enum/contrat-hopital-status";

export interface IContratStatutRequest {
    idContrattHopital: string;
    statut: IContratHopitalStatus;
    motif: string;
}

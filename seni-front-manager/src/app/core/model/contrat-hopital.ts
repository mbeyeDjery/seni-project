import {IHopital} from "./hopital-model";
import {IContratHopitalStatus} from "./enum/contrat-hopital-status";

export interface IContratHopital {

    idContrattHopital: string;
    hopital: IHopital;
    reference: string;
    dateDebut: Date;
    dateFin: Date;
    note: string;
    statut: IContratHopitalStatus;
    enabled: boolean;
}

import {IHopitalStatus} from "../enum/hopital-status";

export interface IHopitalStatutRequest {
    idHopital: string;
    status: IHopitalStatus;
    motif: string;
}


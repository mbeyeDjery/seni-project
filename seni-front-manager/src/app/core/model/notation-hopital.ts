import {IHopital} from "./hopital-model";

export interface INotationHopital {
    idNotation: string;
    hopital: IHopital;
    idUser: string;
    note: number;
}

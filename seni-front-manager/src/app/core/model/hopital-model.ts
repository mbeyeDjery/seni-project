import {ITypeHopital} from "./type-hopital-model";
import {IHopitalStatus} from "./enum/hopital-status";

export interface IHopital {
    idHopital: string;
    typeHopital: ITypeHopital;
    codeHopital: string;
    nom: string;
    sigle: string;
    slogan: string;
    logo: string;
    addresse: string;
    telephone: string;
    mobile: string;
    email: string;
    fax: string;
    siteWeb: string;
    longitude: string;
    latitude: string;
    statut: IHopitalStatus;
    online: boolean;
}

import {IAppUser} from "./app-user-model";
import {IHopital} from "./hopital-model";


export interface IUserHospitalId {
    idUser: string;
    idHospital: string;
}
export interface IUserHospital {
    id: IUserHospitalId;
    user: IAppUser;
    hospital: IHopital;
}
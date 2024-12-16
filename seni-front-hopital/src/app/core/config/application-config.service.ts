import { Injectable } from '@angular/core';
import {BACKEND_SUFFIX, BACKEND_URL_RESOURCE} from "../utils/constants";

@Injectable({
  providedIn: 'root'
})
export class ApplicationConfigService {
    getEndpointFor(api: string, microservice: string): string {
        return `${BACKEND_URL_RESOURCE}/${microservice}/${BACKEND_SUFFIX}${api}`;
    }
}

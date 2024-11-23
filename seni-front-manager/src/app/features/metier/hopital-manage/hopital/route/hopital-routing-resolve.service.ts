import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import {IHopital} from "../../../../../core/model/hopital-model";
import {HopitalService} from "../../../../../core/services/hopital.service";


const hopitalResolve = (route: ActivatedRouteSnapshot): Observable<null | IHopital> => {
    const id = route.params['id'];
    if (id) {
        return inject(HopitalService)
            .findOne(id)
            .pipe(
                mergeMap((hopital: IHopital) => {
                    if (hopital) {
                        return of(hopital);
                    }
                    window.location.href = '/notfound';
                    // inject(Router).navigate(['/notfound']).then();
                    return EMPTY;
                }),
            );
    }
    return of(null);
};

export default hopitalResolve;
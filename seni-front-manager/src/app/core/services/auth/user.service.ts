import {inject, Injectable, Signal, signal} from '@angular/core';
import {catchError, Observable, of, ReplaySubject, shareReplay, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AuthStorageService} from "./auth-storage.service";
import {Router} from "@angular/router";
import {ApplicationConfigService} from "../../config/application-config.service";
import {IAppUser} from "../../model/app-user-model";
import {IAppRole} from "../../model/app-role-model";
import {AUTH_GESTIONNAIRE_SERVER} from "../../utils/constants";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private accountCache$?: Observable<IAppUser> | null;
  private userIdentity = signal<IAppUser | null>(null);
  private authenticationState = new ReplaySubject<IAppUser | null>(1);

  private router = inject(Router);
  private http = inject(HttpClient);
  private stateStorageService = inject(AuthStorageService);
  private applicationConfigService = inject(ApplicationConfigService);

  authenticate(identity: IAppUser | null): void {
    this.userIdentity.set(identity);
    this.authenticationState.next(this.userIdentity());
    if (!identity) {
      this.accountCache$ = null;
    }
  }

  trackCurrentAccount(): Signal<IAppUser | null> {
    return this.userIdentity.asReadonly();
  }

  hasAnyAuthority(authorities: string[] | string): boolean {
    const userIdentity = this.userIdentity();
    if (!userIdentity) {
      return false;
    }
    if (!Array.isArray(authorities)) {
      authorities = [authorities];
    }
    return userIdentity.roles.some((authority: IAppRole) => authorities.includes(authority.roleName));
  }

  identity(force?: boolean): Observable<IAppUser | null> {
    if (!this.accountCache$ || force) {
      this.accountCache$ = this.fetch().pipe(
          tap((account: IAppUser) => {
            this.authenticate(account);
            this.navigateToStoredUrl();
          }),
          shareReplay(),
      );
    }
    return this.accountCache$.pipe(catchError(() => of(null)));
  }

  isAuthenticated(): boolean {
    return this.userIdentity() !== null;
  }

  getAuthenticationState(): Observable<IAppUser | null> {
    return this.authenticationState.asObservable();
  }

  private fetch(): Observable<IAppUser> {
    return this.http.get<IAppUser>(this.applicationConfigService.getEndpointFor('/account', AUTH_GESTIONNAIRE_SERVER));
  }

  private navigateToStoredUrl(): void {
    const previousUrl = this.stateStorageService.getUrl();
    if (previousUrl) {
      this.stateStorageService.clearUrl();
      this.router.navigateByUrl(previousUrl).then(r => {});
    }
  }
}

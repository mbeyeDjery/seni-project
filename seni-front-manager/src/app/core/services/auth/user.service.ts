import {inject, Injectable, Signal, signal} from '@angular/core';
import {catchError, Observable, of, ReplaySubject, shareReplay, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AuthStorageService} from "./auth-storage.service";
import {Router} from "@angular/router";
import {ApplicationConfigService} from "../../config/application-config.service";
import {AppUser} from "../../model/app-user-model";
import {AppRole} from "../../model/app-role-model";
import {AUTH_MANAGER_SERVER} from "../../utils/constants";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private accountCache$?: Observable<AppUser> | null;
  private userIdentity = signal<AppUser | null>(null);
  private authenticationState = new ReplaySubject<AppUser | null>(1);

  private router = inject(Router);
  private http = inject(HttpClient);
  private stateStorageService = inject(AuthStorageService);
  private applicationConfigService = inject(ApplicationConfigService);

  authenticate(identity: AppUser | null): void {
    this.userIdentity.set(identity);
    this.authenticationState.next(this.userIdentity());
    if (!identity) {
      this.accountCache$ = null;
    }
  }

  trackCurrentAccount(): Signal<AppUser | null> {
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
    return userIdentity.roles.some((authority: AppRole) => authorities.includes(authority.roleName));
  }

  identity(force?: boolean): Observable<AppUser | null> {
    if (!this.accountCache$ || force) {
      this.accountCache$ = this.fetch().pipe(
          tap((account: AppUser) => {
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

  getAuthenticationState(): Observable<AppUser | null> {
    return this.authenticationState.asObservable();
  }

  private fetch(): Observable<AppUser> {
    return this.http.get<AppUser>(this.applicationConfigService.getEndpointFor('/account', AUTH_MANAGER_SERVER));
  }

  private navigateToStoredUrl(): void {
    const previousUrl = this.stateStorageService.getUrl();
    if (previousUrl) {
      this.stateStorageService.clearUrl();
      this.router.navigateByUrl(previousUrl).then(r => {});
    }
  }
}

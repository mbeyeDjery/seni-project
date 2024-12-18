import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {
  setItem(key: string, value: string): void {
    sessionStorage.setItem(key, value);
  }

  // Get a value from local storage
  getItem(key: string): string | null {
    return sessionStorage.getItem(key);
  }

  // Remove a value from local storage
  removeItem(key: string): void {
    sessionStorage.removeItem(key);
  }

  // Clear all items from local storage
  clear(): void {
    sessionStorage.clear();
  }
}

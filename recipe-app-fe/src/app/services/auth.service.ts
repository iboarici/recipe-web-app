import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { BehaviorSubject, Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public isLoggedin = new BehaviorSubject(false);
  public isAdmin = new BehaviorSubject(false);

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.checkSession();
  }

  /**
   * 
   */
  private checkSession(): void {

    const token = this.getUser() || null;
    const isUserAdmin = localStorage.getItem('isUserAdmin') || null;

    if (token) {
      this.isLoggedin.next(true);

      if (isUserAdmin) {
        this.isAdmin.next(true);
      }

    } else {
      this.isLoggedin.next(false);
      this.router.navigate(['/login']);
    }
  }

  /**
   * 
   * @param token 
   * @returns 
   */
  public setUser(token: string): any {
    return localStorage.setItem('token', token)
  }

  /**
   * 
   * @param token 
   * @returns 
   */
  public getUser(): any {
    return localStorage.getItem('token')
  }

  /**
   * 
   */
  public logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('isUserAdmin');
    this.isLoggedin.next(false);
    this.isAdmin.next(false);
    this.router.navigate(['/login']);
  }

  /**
   * 
   */
  public getAuth({ username, password }: { username: string, password: string }): Observable<any> {

    const auth: string = window.btoa(`${username}:${password}`);
    const headers = new HttpHeaders({
      'Authorization': `Basic ${auth}`,
      'Accept': 'application/text'
    });

    return this.http.get(`${environment.api}/auth`, { headers, responseType: 'text', withCredentials: true })
      .pipe(map(res => {
        const isAdmin = res && res.toLowerCase() === 'admin';

        if (isAdmin) {
          this.isAdmin.next(true);
          localStorage.setItem('isUserAdmin', 'true');
        }

        return { isAdmin, token: auth }
      }))
      .pipe(catchError((err) => err));
  }
}

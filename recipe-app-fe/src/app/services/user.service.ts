import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  /**
   * 
   * @param param0 
   */
  public addUser({ username, password, isAdmin }): Observable<any> {

    return this.http.post(`${environment.api}/user`, { username, password, role: isAdmin ? 'ADMIN' : 'USER' }, { responseType: 'text', withCredentials: true })
      .pipe(map(res => res))
      .pipe(catchError((err) => err));

  }

  /**
   * 
   * @param param0 
   */
  public removeUser(username): Observable<any> {

    return this.http.delete(`${environment.api}/user/${username}`, { responseType: 'text', withCredentials: true })
      .pipe(map(res => res))
      .pipe(catchError((err) => err));

  }


  /**
   * 
   * @returns 
   */
  public getUsers(): Observable<any> {

    return this.http.get(`${environment.api}/user`, { withCredentials: true })
      .pipe(map(res => res))
      .pipe(catchError((err) => err));

  }
}

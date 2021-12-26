import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private http: HttpClient) { }

  /**
   * 
   */
  public getAllRecipes(): Observable<any> {
    return this.http.get(`${environment.api}/recipe/`, { withCredentials: true })
      .pipe(map(res => res))
      .pipe(catchError((err) => err));
  }

  /**
   * 
   */
  public getRecipeDetail(id: any): Observable<any> {
    return this.http.get(`${environment.api}/recipe/${id}`, { withCredentials: true })
      .pipe(map(res => res))
      .pipe(catchError((err) => err));
  }

  /**
   * 
   */
  public removeRecipe(id: any): Observable<any> {
    return this.http.delete(`${environment.api}/recipe/${id}`, { responseType: 'text', withCredentials: true })
      .pipe(map(res => res))
      .pipe(catchError((err) => err));
  }

  /**
   * 
   */
  public addRecipe2Favorites(id: any): Observable<any> {
    return this.http.post(`${environment.api}/recipe/favorites/${id}`, {}, { responseType: 'text', withCredentials: true })
      .pipe(map(res => res))
      .pipe(catchError((err) => err));
  }

  /**
   * 
   */
  public deleteFavorites(id: any): Observable<any> {
    return this.http.delete(`${environment.api}/recipe/favorites/${id}`, { responseType: 'text', withCredentials: true })
      .pipe(map(res => res))
      .pipe(catchError((err) => err));
  }

  /**
   * 
   */
  public getFavorites(): Observable<any> {
    return this.http.get(`${environment.api}/recipe/favorites`, { withCredentials: true })
      .pipe(map(res => res))
      .pipe(catchError((err) => err));
  }

  /**
   * 
   */
  public addRecipe(data): Observable<any> {
    return this.http.post(`${environment.api}/recipe`, data, { withCredentials: true })
      .pipe(map(res => res))
      .pipe(catchError((err) => err));
  }

  /**
   * 
   */
  public upddateRecipe(data): Observable<any> {
    return this.http.put(`${environment.api}/recipe/${data.id}`, data, { withCredentials: true })
      .pipe(map(res => res))
      .pipe(catchError((err) => err));
  }

  /**
   * 
   */
  public searchRecipes(data): Observable<any> {
    let createDateSTART = '';
    let createDateEND = '';

    if (data.date) {
      const startDate = new Date(data.date[0]);
      const endDate = new Date(data.date[1]);

      createDateSTART = `${String(startDate.getDay()).padStart(2, '0')}-${String(startDate.getMonth() + 1).padStart(2, '0')}-${startDate.getFullYear()} ${String(startDate.getHours()).padStart(2, '0')}:${String(startDate.getMinutes()).padStart(2, '0')}`;
      createDateEND = `${String(endDate.getDay()).padStart(2, '0')}-${String(endDate.getMonth() + 1).padStart(2, '0')}-${endDate.getFullYear()} ${String(endDate.getHours()).padStart(2, '0')}:${String(endDate.getMinutes()).padStart(2, '0')}`;

    }

    return this.http.post(`${environment.api}/search/recipe`, {
      suitableForNumberOfPeopleEQUAL: data.people,
      vegetarian: data.vegetarian,
      createDateSTART,
      createDateEND
    }, { withCredentials: true })
      .pipe(map(res => res))
      .pipe(catchError((err) => err));
  }
}

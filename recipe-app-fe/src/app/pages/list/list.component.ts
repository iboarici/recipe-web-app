import { Component, OnInit } from '@angular/core';
import { RecipeService } from 'src/app/services/recipe.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.sass']
})
export class ListComponent implements OnInit {

  public recipeList: any[] = [];

  constructor(
    private recipeService: RecipeService,
    public authService: AuthService,
    private notify: NzNotificationService) { }

  ngOnInit(): void {
    this.getRecipes();
  }

  /**
   * 
   */
  public getRecipes(): void {
    this.recipeService.getAllRecipes().subscribe(resp => {

      if (resp && !!resp.length) {
        this.recipeList = resp;
      }

    }, () => {
      this.recipeList = [];
    });
  }

  /**
   * 
   * @param id 
   * @param status 
   */
  public doFavoritesAction(data): void {

    const id = data.id;
    const action = !data.existInFavorites;
    const request = action ? this.recipeService.addRecipe2Favorites(id) : this.recipeService.deleteFavorites(id);

    request.subscribe(resp => {

      const msg = action ? 'Added to favorites' : 'removed from favorites';

      this.notify.create('success', 'Success', msg);

      data.existInFavorites = action;

    }, () => {
      this.notify.create('error', 'Fail', 'Please try again later');
    })

  }

  /**
   * 
   * @param recipe 
   */
  public removeRecipe(recipe): void {

    this.recipeService.removeRecipe(recipe.id).subscribe(resp => {

      this.notify.create('success', 'Success', 'Removed recipe');
      this.getRecipes();
    }, () => {
      this.notify.create('error', 'Fail', 'Please try again later');
    })
  }

}

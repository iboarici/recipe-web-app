import { Component, OnInit } from '@angular/core';
import { RecipeService } from 'src/app/services/recipe.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';


@Component({
  selector: 'app-favorite-recipes',
  templateUrl: './favorite-recipes.component.html',
  styleUrls: ['./favorite-recipes.component.sass']
})
export class FavoriteRecipesComponent implements OnInit {

  public favoritesList: any[] = [];
  constructor(private recipeService: RecipeService,
    private notify: NzNotificationService) { }

  ngOnInit(): void {
    this.getList();
  }

  /**
   * 
   */
  private getList(): void {
    this.recipeService.getFavorites().subscribe(resp => {

      if (resp && !!resp.length) {
        this.favoritesList = resp;
      } else {
        this.favoritesList = [];
      }

    }, () => {
      this.favoritesList = [];
    });

  }

  /**
   * 
   * @param id 
   */
  public deleteFavorite(id): void {
    this.recipeService.deleteFavorites(id).subscribe(resp => {
      this.notify.create(
        'success', 'Success', 'removed from favorites'
      );

      this.getList();

    }, () => {
      this.notify.create('error', 'Fail', 'Please try again later');
    })
  }

}

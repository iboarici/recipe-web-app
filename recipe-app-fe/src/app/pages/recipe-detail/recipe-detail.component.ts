import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.sass']
})
export class RecipeDetailComponent implements OnInit {

  public aciteRecipe: any = null;
  private activeRecipeID: string = null;

  constructor(
    private recipeService: RecipeService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.checkParams();
  }

  /**
   * 
   */
  private checkParams(): void {
    this.route.params.subscribe((data) => {
      this.activeRecipeID = data['recipeID'] || null;

      if (this.activeRecipeID) {
        this.getRecipeDetail();
      } else {
        this.returnList();
      }
    });
  }

  /**
   * 
   */
  private getRecipeDetail(): void {

    this.recipeService.getRecipeDetail(this.activeRecipeID).subscribe(resp => {

      if (resp) {
        this.aciteRecipe = resp;        
      } else {
        this.returnList();
      }

    }, () => {
      this.returnList();
    });
  }

  /**
   * 
   */
  private returnList(): void {
    this.router.navigate(['recipe-list']);
  }

}

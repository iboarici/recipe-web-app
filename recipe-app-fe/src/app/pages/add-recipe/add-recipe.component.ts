import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.sass']
})
export class AddRecipeComponent implements OnInit {
  form: FormGroup;
  public activeRecipeID = null;
  private exampleData = {
    "title": `fake food ${Number(Math.random() * 100).toFixed(0)} `,
    "creationDate": "22-05-2020 12:45",
    "vegetarian": true,
    "suitableForNumberOfPeople": 4,
    "prepareTimeInMinutes": 90,
    "cookingTimeInMinutes": 98,
    "calories": 2000,
    "videoUrl": "fake_data",
    "imageUrl": "fake_data",
    "createdUser": "fake_data",
    "category": "Lunch",
    "ingredients": [
      {
        "quantity": 68.53,
        "unit": "fake_data",
        "name": "fake_data",
        "size": "fake_data",
        "additionalInfo": "fake_data"
      }
    ],
    "cookingInstructions": [
      {
        "orderId": 1,
        "description": "fake_data",
        "imageUrl": "fake_data"
      }
    ]
  }

  constructor(
    private fb: FormBuilder,
    private recipeService: RecipeService,
    private router: Router,
    private notify: NzNotificationService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.initForm();
    this.checkParams();
  }

  /**
   * 
   */
  public checkParams() {
    this.route.params.subscribe((data) => {
      this.activeRecipeID = data['recipeID'] || null;

      if (this.activeRecipeID) {
        this.getRecipeDetail();
      }

    });
  }

  /**
   * 
   */
  private getRecipeDetail(): void {

    this.recipeService.getRecipeDetail(this.activeRecipeID).subscribe(resp => {

      if (resp) {

        this.form.setValue({
          obj: JSON.stringify(resp, undefined, 6)
        });

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


  /**
   * 
   */
  private initForm() {
    this.form = this.fb.group({
      obj: [null, [Validators.required]]
    });

    this.form.setValue({
      obj: JSON.stringify(this.exampleData, undefined, 6)
    });

  }


  submitForm(): void {

    const { obj } = this.form.value;
    const request = this.activeRecipeID ? this.recipeService.upddateRecipe(JSON.parse(obj)) : this.recipeService.addRecipe(JSON.parse(obj))

    request.subscribe(resp => {
      this.notify.create('success', 'Success', 'recipe added');
      this.router.navigate(['/recipe-list']);
    }, () => {
      this.notify.create('error', 'Fail', 'Please try again later');
    })

  }

}

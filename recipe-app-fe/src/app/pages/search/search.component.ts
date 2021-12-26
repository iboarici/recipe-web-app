import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.sass']
})
export class SearchComponent implements OnInit {
  form: FormGroup;
  public recipeList: any[] = [];

  constructor(
    private fb: FormBuilder,
    private recipeService: RecipeService,
    private router: Router,
    private notify: NzNotificationService
  ) { }

  ngOnInit(): void {
    this.initForm();
  }

  /**
   * 
   */
  private initForm() {

    this.form = this.fb.group({
      title: [null],
      people: [null],
      vegetarian: [false],
      date: [null]
    });

  }

  submitForm(): void {

    const { title, date, people, vegetarian } = this.form.value;

    this.recipeService.searchRecipes({ title, date, people, vegetarian }).subscribe(resp => {

      if (resp) {
        this.recipeList = resp;
      } else {
        this.recipeList = [];
      }

    }, () => {
      this.recipeList = [];
    });
  }

}

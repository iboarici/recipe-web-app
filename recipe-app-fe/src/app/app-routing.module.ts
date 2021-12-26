import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { UserManagmentComponent } from './pages/user-managment/user-managment.component';
import { FavoriteRecipesComponent } from './pages/favorite-recipes/favorite-recipes.component';
import { ListComponent } from './pages/list/list.component';

// pages
import { LoginComponent } from './pages/login/login.component';
import { RecipeDetailComponent } from './pages/recipe-detail/recipe-detail.component';
import { AddRecipeComponent } from './pages/add-recipe/add-recipe.component';
import { SearchComponent } from './pages/search/search.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  }, {
    path: 'recipe-list',
    component: ListComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'favorite-recipes',
    component: FavoriteRecipesComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'recipe-detail/:recipeID',
    component: RecipeDetailComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'user-managment',
    component: UserManagmentComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'add-recipe',
    component: AddRecipeComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'add-recipe/:recipeID',
    component: AddRecipeComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'search',
    component: SearchComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

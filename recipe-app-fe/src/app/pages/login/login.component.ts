import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  public validateForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.initForm();
    this.checkUserLogin();
  }

  private checkUserLogin(): boolean {
    if (this.authService.isLoggedin) {
      this.router.navigate(['/recipe-list']);
      return false;
    }
  }

  submitForm(): void {
    const { username, password } = this.validateForm.value;

    this.authService.getAuth({ username, password }).subscribe(resp => {

      if (resp) {
        this.authService.isLoggedin.next(true);
        this.authService.setUser(resp.token);
        this.router.navigate(['/recipe-list']);
      } else {
        this.authService.isLoggedin.next(false)
      }

    })
  }

  private initForm(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]]
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-user-managment',
  templateUrl: './user-managment.component.html',
  styleUrls: ['./user-managment.component.sass']
})
export class UserManagmentComponent implements OnInit {

  validateForm!: FormGroup;
  public userList: any = [];

  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private authService: AuthService,
    private notify: NzNotificationService,
    private router: Router) { }

  ngOnInit(): void {
    this.initForm();
    this.getUsers();
  }

  private initForm(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
      isAdmin: [false]
    });
  }

  submitForm(): void {
    const { username, password, isAdmin } = this.validateForm.value;

    this.userService.addUser({ username, password, isAdmin }).subscribe(resp => {
      this.notify.create('success', 'Success', 'User Added');
      this.validateForm.reset();
      this.getUsers();
    }, () => {
      this.notify.create('error', 'Fail', 'Please try again later');
    })
  }

  /**
   * 
   */
  private getUsers(): void {
    this.userService.getUsers().subscribe((resp) => {
      this.userList = resp ? resp : [];
    }, () => {
      this.userList = [];
    })
  }

  /**
   * 
   * @param user 
   */
  public deleteUser(user): void {
    this.userService.removeUser(user.username).subscribe(resp => {
      this.notify.create('success', 'Success', 'User Removed');
      this.getUsers();
    }, () => {
      this.notify.create('error', 'Fail', 'Please try again later');
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/Service/user-service';
import { User } from '../shared/Model/user';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  user:User =new User();
  email : string = '';
  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  resetPassword(){

  }

}

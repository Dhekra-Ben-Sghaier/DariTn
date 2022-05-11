import { Component, OnInit } from '@angular/core';
import { User } from '../shared/Model/user';
import { UserService } from '../shared/Service/user-service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user:User =new User();
  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  userRegister(){
    console.log(this.user);
    this.userService.signUp(this.user).subscribe(data=>{
     alert("Successfully User is register?")
    },error=>alert("Sorry User not register"));
  }

}

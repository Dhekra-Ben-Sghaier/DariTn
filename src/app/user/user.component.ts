import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../shared/Model/user';
import { UserService } from '../shared/Service/user-service';



@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {


  user:User =new User();
  recaptcha:string | undefined;
  constructor(private userService: UserService) { }

  ngOnInit() {
    
  }
  //siteKey:string = "6LfPO8AfAAAAAMIe-9_6VXb4A5-dY7t0pskuNJTt";
 
 resolved(captchaResponse: string){
   this.recaptcha = captchaResponse;

 }
  userRegister(){
    console.log(this.user);
    
    this.userService.signUp(this.user).subscribe(data=>{
     alert("Successfully User is register?")
    },error=>alert("Sorry User not register"));
    
  }
  

}

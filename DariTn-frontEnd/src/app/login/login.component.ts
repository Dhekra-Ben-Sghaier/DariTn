import { Component, OnInit } from '@angular/core';
import { User } from '../shared/Model/user';
import { UserService } from '../shared/Service/user-service';
import { TokenStorageService } from '../shared/Service/token-storage-service'; 

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {
    email: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  user:User =new User();
  constructor(private userService: UserService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit(): void {
    const { email, password } = this.form;
    console.log("LOOOG"+email);
    this.userService.signIn(email, password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }
  
  
  
  /////
 // userLogin(){
   // console.log(this.user);
    
    //this.userService.signIn(this.user.email,this.user.password).subscribe(data=>{
     //alert("Successfully User is register?")
    //},error=>alert("Sorry User not register"));
    
  //}

}

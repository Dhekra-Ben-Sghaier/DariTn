import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../shared/Model/user';
import { TokenStorageService } from '../shared/Service/token-storage-service';
import { UserService } from '../shared/Service/user-service';

@Component({
  selector: 'app-nav-user',
  templateUrl: './nav-user.component.html',
  styleUrls: ['./nav-user.component.css']
})
export class NavUserComponent implements OnInit {

  user:User =new User();
  constructor(private userService: UserService, private tokenStorage: TokenStorageService,private router: Router) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
       this.user = this.tokenStorage.getUser();
    }
  }

  logout(){
    this.tokenStorage.signOut();
    this.router.navigate(["/home"]);
  }

}

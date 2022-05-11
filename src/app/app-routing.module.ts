import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user/user.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { ProfileClientComponent } from './profile-client/profile-client.component';



const routes: Routes =[
  {path: 'home', component: HomeComponent},
  {path: 'signup', component: UserComponent} ,
  {path: 'signin', component: LoginComponent} ,
  { path: 'profile', component: ProfileComponent },
  { path: 'profileClient', component: ProfileClientComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
  
]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

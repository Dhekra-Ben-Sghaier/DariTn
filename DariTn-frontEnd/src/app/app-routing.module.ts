import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user/user.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { ProfileClientComponent } from './profile-client/profile-client.component';
import { CrudUserComponent } from './crud-user/crud-user.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { DetailsUserComponent } from './details-user/details-user.component';



const routes: Routes =[
  {path: 'home', component: HomeComponent},
  {path: 'signup', component: UserComponent} ,
  {path: 'signin', component: LoginComponent} ,
  { path: 'profile', component: ProfileComponent },
  { path: 'profileClient', component: ProfileClientComponent },
  { path: 'crudUser', component: CrudUserComponent },
  {path: 'signin/home', component: HomeComponent},
  { path: 'signin/crudUser', component: CrudUserComponent },
  {path: 'crudUser/signup', component: LoginComponent},
  {path: 'resetPwd', component: ResetPasswordComponent},
  {path: 'userDetails', component: DetailsUserComponent},
  {path: 'signin/userDetails', component: DetailsUserComponent},
  {path: 'crudUser/userDetails/forgetPwd', component: ForgotPasswordComponent},
  {path: 'signin/resetPwd', component: ResetPasswordComponent},
  {path: 'forgetPwd', component: ForgotPasswordComponent},
  {path: 'signin/forgetPwd', component: ForgotPasswordComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
  
]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

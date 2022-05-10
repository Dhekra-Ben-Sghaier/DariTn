import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LogoComponent } from './logo/logo.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SearchComponent } from './search/search.component';
import { BodyComponent } from './body/body.component';
import { NgbModal, NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { ReactiveFormsModule } from '@angular/forms';

import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { UserComponent } from './user/user.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NgxCaptchaModule } from 'ngx-captcha';
import { RecaptchaModule } from 'ng-recaptcha';
import { ProfileComponent } from './profile/profile.component';
import { ProfileClientComponent } from './profile-client/profile-client.component';
import { NavUserComponent } from './nav-user/nav-user.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { VerifyEmailComponent } from './verify-email/verify-email.component';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LogoComponent,
    NavbarComponent,
    SearchComponent,
    BodyComponent,
    UserComponent,
    HomeComponent,
    LoginComponent,
    ProfileComponent,
    ProfileClientComponent,
    NavUserComponent,
    ResetPasswordComponent,
    VerifyEmailComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    NgbModalModule,
    NgbModule,
    ReactiveFormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgxCaptchaModule, 
    RecaptchaModule,
   
 
    

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

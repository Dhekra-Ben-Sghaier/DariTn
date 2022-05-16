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
import {TableModule} from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import {DynamicDialogModule} from 'primeng/dynamicdialog';

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
import { VisitComponent } from './visit/visit.component';
import { ToastrModule } from 'ngx-toastr';
import { VisitFormComponent } from './visit/visit-form/visit-form.component';
import { DatePipe } from '@angular/common';
import { FeedbackComponent } from './feedback/feedback.component';
import { FeedbackFormComponent } from './feedback/feedback-form/feedback-form.component';
import { UpdateVisitComponent } from './visit/update-visit/update-visit.component';

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
    VisitComponent,
    VisitFormComponent,
    FeedbackComponent,
    FeedbackFormComponent,
    UpdateVisitComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    NgbModalModule,
    NgbModule,
    ReactiveFormsModule,
    AppRoutingModule,
    NgxCaptchaModule, 
    RecaptchaModule,
    TableModule,
    DynamicDialogModule,
    ButtonModule,
    ToastrModule.forRoot({
      positionClass :'toast-bottom-right'
    })
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }

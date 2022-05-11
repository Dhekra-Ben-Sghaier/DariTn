import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LogoComponent } from './logo/logo.component';
import { MenuComponent } from './menu/menu.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SearchComponent } from './search/search.component';
import { UserComponent } from './user/user.component';
import { SharedComponent } from './components/shared/shared.component';
import { AppRoutingModule } from './app-routing.module';
import {HttpClientModule} from "@angular/common/http";
import {ToastModule} from "primeng/toast";
import {ToolbarModule} from "primeng/toolbar";
import {ButtonModule} from "primeng/button";
import {TableModule} from "primeng/table";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputNumberModule} from "primeng/inputnumber";
import {RadioButtonModule} from "primeng/radiobutton";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {DropdownModule} from "primeng/dropdown";
import {DialogModule} from "primeng/dialog";
import {RatingModule} from "primeng/rating";
import {PaginatorModule} from "primeng/paginator";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastrModule} from "ngx-toastr";
import {RippleModule} from "primeng/ripple";
import {TreeSelectModule} from "primeng/treeselect";
import {CalendarModule} from "primeng/calendar";
import {InputTextModule} from "primeng/inputtext";
import { PubliciteComponent } from './publicite/publicite.component';
import { BodyComponent } from './components/body/body.component';
import { ContratComponent } from './contrat/contrat.component';
import { AbonnementComponent } from './abonnement/abonnement.component';

@NgModule({
  declarations: [
    PubliciteComponent,
    BodyComponent,
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LogoComponent,
    MenuComponent,
    NavbarComponent,
    SearchComponent,
    UserComponent,
    SharedComponent,
    ContratComponent,
    AbonnementComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ToastModule,
    ToolbarModule,
    ButtonModule,
    TableModule,
    FormsModule,
    InputNumberModule,
    RadioButtonModule,
    ConfirmDialogModule,
    DropdownModule,
    DialogModule,
    RatingModule,
    PaginatorModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    RippleModule,
    TreeSelectModule,
    CalendarModule,
    InputTextModule,
    ReactiveFormsModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

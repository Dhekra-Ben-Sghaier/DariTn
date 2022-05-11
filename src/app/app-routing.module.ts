import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PubliciteComponent} from "./publicite/publicite.component";
import {ContratComponent} from "./contrat/contrat.component";
import {AbonnementComponent} from "./abonnement/abonnement.component";


const routes: Routes = [
  {path : 'pub', component : PubliciteComponent},
  {path : 'contrat', component : ContratComponent},
  {path : 'abonnement', component : AbonnementComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {  }

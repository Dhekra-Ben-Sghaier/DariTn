import { Component, OnInit } from '@angular/core';
import {ContratService} from "../services/contrat.service";
import {ToastrService} from "ngx-toastr";
import {UserService} from "../services/user.service";
import {Contrat} from "../models/Contrat";
import {User} from "../models/User";
import Swal from "sweetalert2";
import {Abonnement} from "../models/Abonnement";
import {AbonnementService} from "../services/abonnement.service";

@Component({
  selector: 'app-abonnement',
  templateUrl: './abonnement.component.html',
  styleUrls: ['./abonnement.component.css']
})
export class AbonnementComponent implements OnInit {

  constructor(private abonnementService:AbonnementService ) { }
abonnements:Abonnement[]
  ngOnInit(): void {
    this.getAbonnements()


  }
  getAbonnements(){
    this.abonnementService.getAllAbonnement().subscribe(data=>{

        this.abonnements=data;

      },

      error =>{

      })

  }








  applyFilterGlobal($event: any, stringVal: any, dt: any) {
    dt!.filterGlobal(($event.target as HTMLInputElement).value, 'contains');

  }





}

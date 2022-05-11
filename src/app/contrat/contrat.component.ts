import { Component, OnInit } from '@angular/core';
import {Publicite} from "../models/Publicite";
import {Contrat} from "../models/Contrat";
import {PubliciteService} from "../services/publicite.service";
import {ToastrService} from "ngx-toastr";
import {UserService} from "../services/user.service";
import {ContratService} from "../services/contrat.service";
import Swal from "sweetalert2";
import {User} from "../models/User";

@Component({
  selector: 'app-contrat',
  templateUrl: './contrat.component.html',
  styleUrls: ['./contrat.component.css']
})
export class ContratComponent implements OnInit {
  users:User[]
  user =new User();
  contrat:Contrat[];
  contrats =new Contrat();
  productDialog: boolean;
  NewDialog =false ;


  constructor(private contratService:ContratService ,private toast: ToastrService ,private userService:UserService) { }

  ngOnInit(): void {
this.getAllcontrat()
    this.getAlluser()

  }
  getAllcontrat(){
    this.contratService.getContrat().subscribe(data=>{

        this.contrat=data;

      },

      error =>{

      })

  }
  getAlluser(){
    this.userService.getUser().subscribe(data=>this.users=data);

  }

  openNew() {
    this.contrats =new Contrat();
    this.NewDialog = true;

  }

  save() {
    console.log(this.contrats)
    this.contrats.user=this.user
    this.contratService.addContrat(this.contrats).subscribe(res => {
        this.toast.success("done")
        this.ngOnInit()
        this.NewDialog = false

      },
      error => this.toast.error('some things wrong')
    )

  }
  getUser(id:number){
    let user=new User();
   this.userService.getUserByContrat(id).subscribe(res=>user=res)
    return user;
  }

  openDialog(contrat: Contrat) {
    this.contrats = contrat;
    this.productDialog = true;

  }

  applyFilterGlobal($event: any, stringVal: any, dt: any) {
    dt!.filterGlobal(($event.target as HTMLInputElement).value, 'contains');
  }
  Ondelete(id:number) {
    Swal.fire({
      title: 'Etes-vous sur?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Oui!'
    }).then((result) => {

      //let confirm = window.confirm('do you want to delete this GC')
      if (result.value) {
        this.contratService.removeContrat(id).subscribe(data => {
            console.table(data)

            this.ngOnInit()

            this.toast.success('pub supprimé avec succés ');

          },
          error => {

            console.log(error)
          })

      }
    })

  }

  editpub(contrat: any) {
    this.contratService.updateContrat(contrat.id, contrat).subscribe(data => {
        this.toast.success('done');
        this.productDialog = false;
      },
      error => this.toast.error('some things wrong'))


  }


}

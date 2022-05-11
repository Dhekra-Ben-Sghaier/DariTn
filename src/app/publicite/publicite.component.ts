import { Component, OnInit } from '@angular/core';
import {PubliciteService} from "../services/publicite.service";
import {ToastrService} from "ngx-toastr";
import {Publicite} from "../models/Publicite";

import Swal from "sweetalert2";
import {Router} from "@angular/router";
import {FileDB} from "../models/FileDB";


@Component({
  selector: 'app-publicite',
  templateUrl: './publicite.component.html',
  styleUrls: ['./publicite.component.css']
})
export class PubliciteComponent implements OnInit {
  message: File;
  photo: File;
  publicite:Publicite[];
  pubs =new Publicite();
  base64Data: Int8Array;
  retrievedImage: string;
  image: string;
  productDialog: boolean;
  NewDialog =false ;
  applyFilterGlobal($event: any, stringVal: any, dt: any) {
    dt!.filterGlobal(($event.target as HTMLInputElement).value, 'contains');
  }

  constructor(private publiciteService:PubliciteService ,private toast: ToastrService,private  route: Router) { }

  ngOnInit(): void {

    this.getallpubs()


  }
  getallpubs(){
    this.publiciteService.getPub().subscribe(data=>{
        console.table(data)
        this.publicite=data;
      },

      error =>{
        console.log(error)
      })

  }


  openNew() {

    this.pubs =new Publicite();
    this.NewDialog = true;

  }



  openDialog(publicite:Publicite) {
    this.pubs = publicite;
    this.productDialog = true;
  }

  Ondelete(id: number) {
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
        this.publiciteService.removePub(id).subscribe(data => {
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
  editpub(publicite:any) {
    this.publiciteService.updatePub(publicite.id, publicite).subscribe(data => {
        this.toast.success('done');
        this.productDialog = false;
      },
      error => this.toast.error('some things wrong'))


  }




  onSelectFile(event: any) {
    if (event.target.files.length > 0) {
      this.photo = event.target.files[0];
      this.message = this.photo;
      const reader = new FileReader();
      reader.onload = () => {
        this.image = reader.result as string;
      }
      reader.readAsDataURL(this.photo)

    }
  }

  save() {

    const formData = new FormData();

    formData.append('image', this.photo);
    formData.append('publicite', JSON.stringify(this.pubs));
    console.log(this.pubs)
    this.publiciteService.addPub(formData)
      .subscribe(() => {
        this.toast.success('pub added  successfully !', '', {
          timeOut: 3000,
          positionClass: 'toast-bottom-right'
        });
        this.ngOnInit();
        this.image=' '
        this.NewDialog=false
      });

  }
  getImage(pub:Publicite) {

    console.log(this.retrievedImage)
    this.base64Data = pub.image.data;
    this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;

    return this.retrievedImage;
  }

}

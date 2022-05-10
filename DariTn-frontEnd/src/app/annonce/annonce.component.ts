import { Component, OnInit, ViewChild } from '@angular/core';
import { Annonce } from '../shared/model/annonce';
import { AnnonceService } from '../shared/service/annonce-service';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { FileService } from '../shared/service/file-service';
import { FormControl ,FormBuilder, FormGroup ,Validators } from '@angular/forms';
import { FileComponent } from '../file/file.component';


@Component({
  selector: 'app-annonce',
  templateUrl: './annonce.component.html',
  styleUrls: ['./annonce.component.css'],
 
})
export class AnnonceComponent implements OnInit {
 listAnnonces: any;
 form: boolean = false;
 annonce!: Annonce;
 closeResult!: string;
 sendData:any;
 File: FormGroup;
 info:any;
 file:any;
 images: any = [];
 submitted = false;
 private notify: FileComponent;
constructor(config: NgbModalConfig, private modalService: NgbModal ,private formBuilder: FormBuilder,private router:Router,private annonceService : AnnonceService,private fileService : FileService) {config.backdrop = 'static';
this.notify=new FileComponent(fileService,annonceService);
config.keyboard = false; }

  ngOnInit(): void {
    this.getAllAnnonces();
    this.ShowAllFiles()
    this.File = this.formBuilder.group({
     
      title : [''],
      image: [''],
      description :[''],
      area:[''],
      governorate :[''],
      delegation :[''],
      transaction:[''],
      nbRoom:[''],
      nbBathRoom:[''],
      price:[''],
      dateDepot:[''],

    });
    this.annonce = {
      idAnnonce:null,
      title: null,
      description: null,
      area: null ,
      governorate: null,
    delegation: null,
    transaction: null,
    nbRoom: null,
    nbBathRoom: null,
    closureDate: null,
    dateDepot: null,
    price: null,
    locationType: null
    }
}
ShowAllFiles(){
  console.log("abcd");
  this.fileService.ShowAllFiles().subscribe(res => this.sendData=res)
  console.log('mariem',this.sendData);
}
getAllAnnonces(){
  this.annonceService.getAllAnnonces().subscribe(res => this.listAnnonces=res)
}
addAnnonce(a: any){
  this.annonceService.addAnnonce(a).subscribe((res) => {
    this.getAllAnnonces();
    this.form = false;
    this.notify.listFiles=this.notify.ShowAllFiles();
    
  });
}


open(content) {
  this.modalService.open(content);
}
updateAnnonce(annonce: Annonce){
  this.annonceService.updateAnnonce(annonce).subscribe();
}
deleteAnnonce(idAnnonce: any){
  this.annonceService.deleteAnnonce(idAnnonce).subscribe(() => this.getAllAnnonces());
  
  }





}

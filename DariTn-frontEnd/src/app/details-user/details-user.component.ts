import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../shared/Model/user';
import { TokenStorageService } from '../shared/Service/token-storage-service';
import { UserService } from '../shared/Service/user-service';

@Component({
  selector: 'app-details-user',
  templateUrl: './details-user.component.html',
  styleUrls: ['./details-user.component.css']
})
export class DetailsUserComponent implements OnInit {

  user:User =new User();
  form : boolean = false;
  closeResult!: string;
  listUsers: any;
  constructor(private userService: UserService, private tokenStorage: TokenStorageService,private router: Router,private modalService: NgbModal) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.user = this.tokenStorage.getUser();
   }
  }

  updateUser(idUser : any){
    console.log(idUser)
    this.userService.editUser(idUser).subscribe()
  }
  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
    }

    private getDismissReason(reason: any): string {
      if (reason === ModalDismissReasons.ESC) {
        return 'by pressing ESC';
      } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
        return 'by clicking on a backdrop';
      } else {
        return  `with: ${reason}`;
      }
    }

    closeForm(){

    }
    cancel(){
      this.form = false;
    }


}

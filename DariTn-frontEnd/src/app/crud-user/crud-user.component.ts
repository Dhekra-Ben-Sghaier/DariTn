import { Component, OnInit } from '@angular/core';
import { NgbModal , ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../shared/Model/user';
import { UserService } from '../shared/Service/user-service';

@Component({
  selector: 'app-crud-user',
  templateUrl: './crud-user.component.html',
  styleUrls: ['./crud-user.component.css']
})
export class CrudUserComponent implements OnInit {

  listUsers: any;
  form : boolean = false;
  user!: User;
  closeResult!: string;
  constructor(private userService: UserService,private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getAllUsers();
    this.user = {
      id: null,
      username: null,
      lastname: null,
      email: null,
      password: null,
      phoneNumber: null,
      roles: null

    }
  }
  getAllUsers(){
    this.userService.getAllUsers().subscribe(res => this.listUsers = res)
  }

  deleteUser(idUser : any){
    this.userService.deleteUSer(idUser).subscribe(() => this.getAllUsers())
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

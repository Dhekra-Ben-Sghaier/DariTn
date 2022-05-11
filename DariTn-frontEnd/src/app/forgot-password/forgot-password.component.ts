import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../shared/Model/user';
import { TokenStorageService } from '../shared/Service/token-storage-service';
import { UserService } from '../shared/Service/user-service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  form: any = {
    email: null,
   
  };

  errorMessage = '';
  closeResult! : string;
  user:User =new User();
  constructor(private userService: UserService, private tokenStorage: TokenStorageService, private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  resetPassword(){

  }

  onReset(): void {
    const { email} = this.form;
    console.log("LOOOG"+email);
    this.userService.forgetPassword(email).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        

       
        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
      
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }
  
  openResetPasswordDialog(){
    
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

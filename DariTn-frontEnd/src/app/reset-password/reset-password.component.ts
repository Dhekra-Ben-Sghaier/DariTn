import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/Service/user-service';
import { ResetPassword} from '../shared/Model/reset-password';
import { TokenStorageService } from '../shared/Service/token-storage-service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';




@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  reset:ResetPassword  =new ResetPassword();
 
  errorMessage = '';
  closeResult! : string;
  form: any = {
    email: null,
    password: null,
    token: null
  };
  constructor(private userService: UserService, private tokenStorage: TokenStorageService, private modalService: NgbModal) { }

  ngOnInit(): void {
    
  }

  resetPassword(){

  }

  onCreateNewPwd(): void {

    const { email, password, token} = this.form;  
    
   
    console.log("the email is:   "+email);
    console.log("the password is:   "+password);
    console.log("the token is:   "+token);
    this.userService.resetPassword(email,password,token).subscribe(data=>{
      
      alert("Successfully User is register?")
     },error=>alert("Sorry User not updated!"));
     
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

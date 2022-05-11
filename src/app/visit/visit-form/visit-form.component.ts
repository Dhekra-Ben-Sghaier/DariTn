import { Component, OnInit } from '@angular/core';
import { FormBuilder, NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { VisitDetail } from 'src/app/shared/Model/visit';
import { VisitService } from 'src/app/shared/Service/visit-service';


@Component({
  selector: 'app-visit-form',
  templateUrl: './visit-form.component.html',
  styleUrls: ['./visit-form.component.css']
})
export class VisitFormComponent implements OnInit {
  visiteDetail: VisitDetail = new VisitDetail;
  checkoutForm = this.formBuilder.group({
    state: '',
    dateAppointement: '',
    heure: '',
    visibility: '',
    attendance: ''
  });
  constructor(public service: VisitService,
    private formBuilder: FormBuilder,
    private toastr: ToastrService) { }

  ngOnInit(): void {
    
  }

  

  onSubmit() {
    this.visiteDetail = new VisitDetail(this.checkoutForm.value);
    console.log("aaaaaaa" , this.checkoutForm.value)
    this.service.postVisit(new VisitDetail(this.checkoutForm.value)).subscribe(
      res => {
        this.service.refreshList();
        this.toastr.success('Submitted successfully', 'Payment Detail Register')
      },
      err => { console.log(err); }
    );
  }



  
}

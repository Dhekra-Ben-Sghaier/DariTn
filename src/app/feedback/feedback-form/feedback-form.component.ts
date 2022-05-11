import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Feedback } from 'src/app/shared/Model/feedback';
import { VisitDetail } from 'src/app/shared/Model/visit';
import { FeedbackService } from 'src/app/shared/Service/feedback-service';
import { VisitService } from 'src/app/shared/Service/visit-service';

@Component({
  selector: 'app-feedback-form',
  templateUrl: './feedback-form.component.html',
  styleUrls: ['./feedback-form.component.css']
})
export class FeedbackFormComponent implements OnInit {

  feedback: Feedback = new Feedback;
  checkoutForm = this.formBuilder.group({
    descriptionFeedBack: '',
    likes: '',
    appointment:''
  });
  constructor(public service: FeedbackService, private visitService: VisitService,
    private formBuilder: FormBuilder) { }
    list : VisitDetail[] = []
  ngOnInit(): void {
    this.visitService.getListVisit().subscribe(res => {
      this.list = res
      console.log(res)
    })
  }

  onSubmit() {
    this.feedback = new Feedback(this.checkoutForm.value);
    console.log("aaaaaaa" , this.checkoutForm.value)
    console.log("bbbbbbbbbbbbbb" , this.checkoutForm.controls.appointment.value)

    this.service.postFeedback(new Feedback(this.checkoutForm.value),this.checkoutForm.controls.appointment.value).subscribe(
      res => {
        this.service.refreshList();
      },
      err => { console.log(err); }
    );
  }

}

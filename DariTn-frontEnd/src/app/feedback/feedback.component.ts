import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Feedback } from '../shared/Model/feedback';
import { FeedbackService } from '../shared/Service/feedback-service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css'],
 
})
export class FeedbackComponent implements OnInit {
  list : any
  constructor(public service: FeedbackService, public datepipe: DatePipe) { }



  ngOnInit(): void {
    //console.log(this.service.refreshList())
    this.service.refreshList().subscribe(res => {
      this.list = res
      console.log(res)
    })
  }

  populateForm(selectedRecord: Feedback) {
    this.service.formData = Object.assign({}, selectedRecord);
  }

  onDelete(id: number) {
    if (confirm('Are you sure to delete this visit?')) {
      this.service.deleteFeedback(id)
        .subscribe(
          res => {
            this.service.refreshList();
          },
          err => { console.log(err) }
        )
    }
  }

}



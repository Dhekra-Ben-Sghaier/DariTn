import { Injectable } from '@angular/core';
import { VisitDetail } from '../Model/visit';
import { HttpClient } from "@angular/common/http";
import { map } from 'rxjs';
import { FormGroup } from '@angular/forms';
import { Feedback } from '../Model/feedback';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  constructor(private http: HttpClient) { }

  readonly baseURL = 'http://localhost:8084'
  formData: Feedback = new Feedback();
  list: any = this.refreshList()

  postFeedback(form: any , id : number) {
    return this.http.post(`${this.baseURL}/Add-appointmentfeedback/${id}`,form);
  }

//   putPaymentDetail() {
//     return this.http.put(`${this.baseURL}/${this.formData.visitId}`, this.formData);
//   }

  deleteFeedback(id: number) {
    return this.http.delete(`${this.baseURL}/appointmentfeedback/${id}`);
  }

  refreshList() {
    return this.http.get(this.baseURL+'/feedbacks')
      
  }


}
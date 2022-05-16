import { Injectable } from '@angular/core';
import { VisitDetail } from '../Model/visit';
import { HttpClient } from "@angular/common/http";
import { map } from 'rxjs';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class VisitService {

  constructor(private http: HttpClient) { }

  readonly baseURL = 'http://localhost:8084'
  formData: VisitDetail = new VisitDetail();
  list: any = this.refreshList()

  postVisit(form: VisitDetail) {
    return this.http.post(this.baseURL+'/addappointment',form);
  }

//   putPaymentDetail() {
//     return this.http.put(`${this.baseURL}/${this.formData.visitId}`, this.formData);
//   }

  deleteVisit(id: number) {
    return this.http.delete(`${this.baseURL}/remove-user/${id}`);
  }

  refreshList() {
    return this.http.get(this.baseURL+'/retrieve-all-appointment')
      
  }


  getApp(id:number) {
    return this.http.get(this.baseURL+'/getuser/'+id)
      
  }
  updateap(id:number,app:VisitDetail) {
    return this.http.put(this.baseURL+'/updateappointment/'+id,app)
      
  }

  getListVisit() {
    return this.http.get(this.baseURL+'/retrieve-all-appointment').pipe(
        map((res => {
            return <VisitDetail[]>res 
        }))
    )
      
  }


}
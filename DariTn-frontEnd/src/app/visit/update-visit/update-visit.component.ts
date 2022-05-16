import { Component, OnInit } from '@angular/core';
import { VisitDetail } from 'src/app/shared/Model/visit';
import { VisitService } from 'src/app/shared/Service/visit-service';

@Component({
  selector: 'app-update-visit',
  templateUrl: './update-visit.component.html',
  styleUrls: ['./update-visit.component.css']
})
export class UpdateVisitComponent implements OnInit {
visit!:VisitDetail;
  constructor(public service: VisitService) { }

  ngOnInit(): void {
    this.visit= new VisitDetail();
    this.service.getApp(this.visit.idAppointement);

  }

}

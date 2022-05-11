import { Component, OnInit } from '@angular/core';
import { VisitDetail } from '../shared/Model/visit';
import { ToastrService } from 'ngx-toastr';
import { DatePipe } from '@angular/common';
import { VisitService } from '../shared/Service/visit-service';

@Component({
  selector: 'app-visit',
  templateUrl: './visit.component.html',
  styleUrls: ['./visit.component.css']
})
export class VisitComponent implements OnInit {
list : any;
visit!:any;
update:boolean=false;
  constructor(public service: VisitService, private toastr: ToastrService, public datepipe: DatePipe) { }

  ngOnInit(): void {
    this.visit=new VisitDetail();
    

    //console.log(this.service.refreshList())
    this.service.refreshList().subscribe(res => {
      this.list = res
      console.log(res)
    })
  }

  populateForm(selectedRecord: VisitDetail) {
    this.service.formData = Object.assign({}, selectedRecord);
  }

  onDelete(id: number) {
    if (confirm('Are you sure to delete this visit?')) {
      this.service.deleteVisit(id)
        .subscribe(
          res => {
            this.service.refreshList();
            this.toastr.error("Deleted successfully", 'Visit Detail');
          },
          err => { console.log(err) }
        )
    }
  }
  onUpdate(id:VisitDetail){
    this.visit=new VisitDetail();
    this.visit=id;
    this.update=true;
  
  }
  Update(app:VisitDetail){
    this.service.updateap(app.idAppointement,app).subscribe();
    this.update=false;
  
  }

}

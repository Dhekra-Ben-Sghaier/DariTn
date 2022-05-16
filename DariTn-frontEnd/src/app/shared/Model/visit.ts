export class VisitDetail {
    visitId : number=0;
    visitDate : Date = new Date()
    visibility : number=0;
    state : string='';
    heure : number=0;
    attendance : string='';
    idAppointement : number=0
    public constructor(init?: Partial<VisitDetail>) {
        Object.assign(this, init);
    }
}

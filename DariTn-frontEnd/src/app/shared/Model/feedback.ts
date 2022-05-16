export class Feedback {
    
    feedbackId : number=0;
    descriptionFeedBack : string=''
    likes : boolean=false;
    appointment: {}= {};

    public constructor(init?: Partial<Feedback>) {
        Object.assign(this, init);
    }
  }
  
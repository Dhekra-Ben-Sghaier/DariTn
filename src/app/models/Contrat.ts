import {ContratType} from "./ContratType";

import {User} from "./User";



export class Contrat {
  id:number;
  idProduct:number;
  price:number;
  dateContrat:Date;
  dateStart:Date;
  dateEnd:Date;
  contractType:ContratType;
  user:User



}

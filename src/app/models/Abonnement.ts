import {User} from "./User";


export class Abonnement{
  id:number;
  user:User;
  typeAbonnement:TypeAbonnement;

}
export enum TypeAbonnement {
  GOLD,SILVER,BRONZE
}

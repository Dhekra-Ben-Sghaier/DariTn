import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Abonnement} from "../models/Abonnement";

@Injectable({
  providedIn: 'root'
})
export class AbonnementService {
  constructor(public httpClient: HttpClient) { }
  getAllAbonnement():Observable<Abonnement[]>{
    return this.httpClient.get<Abonnement[]>('http://localhost:8082/Abonnement');
  }
}

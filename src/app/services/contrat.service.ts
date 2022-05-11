import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Publicite} from "../models/Publicite";
import {Contrat} from "../models/Contrat";

@Injectable({
  providedIn: 'root'
})
export class ContratService {

  constructor(public httpClient: HttpClient) { }

  getContrat() {
    return this.httpClient.get<Contrat[]>("http://localhost:8085/contrat");
  }
  addContrat(data: Contrat) {
    return this.httpClient.post("http://localhost:8085/Add-contrat", data);
  }
  updateContrat(id: number, data: any) {
    return this.httpClient.put("http://localhost:8085/updatecontrat/"+ id, data)
  }
  removeContrat(id: number) {
    return this.httpClient.delete("http://localhost:8085/contrat/" + id)
  }


}

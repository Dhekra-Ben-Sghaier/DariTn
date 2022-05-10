import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})

export class AnnonceService {
readonly API_URL = 'http://localhost:8089'
constructor(private httpClient: HttpClient){}

getAllAnnonces(){
    return this.httpClient.get(`${this.API_URL}/retrieveAnnonce`)
}

addAnnonce(annonce: any){
    return this.httpClient.post(`${this.API_URL}/addAnnonce`, annonce)
}
updateAnnonce(annonce: any){
    return this.httpClient.put(`${this.API_URL}/updateAnnonce`, annonce)
}
deleteAnnonce(idAnnonce: any){
    return this.httpClient.delete(`${this.API_URL}/deleteAnnonce/${idAnnonce}`)
}
AfficherAnnonceByGovernorate(governorate: any){
return this.httpClient.get(`${this.API_URL}/AfficherAnnonceByGovernorate/${governorate}`)
}
GetFilesByAnnonce(idAnnonce:any){
    return this.httpClient.get(`${this.API_URL}/GetFilesByAnnonce/${idAnnonce}`)

}
}

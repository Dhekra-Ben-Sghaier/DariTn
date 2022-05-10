import { HttpClientModule,HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})


export class FileService {
  
        readonly API_URL = 'http://localhost:8089'
        constructor(private httpClient: HttpClient){}
        
        ShowAllFiles(){
            return this.httpClient.get(`${this.API_URL}/ShowFiles`)
        }
        ShowOne(idAnnonce: any){
            return this.httpClient.get(`${this.API_URL}/retrieveFile/${idAnnonce}`)
        }
        AddFileToAnnonce(id,annonce: any){
                return this.httpClient.post(`${this.API_URL}/addFile/${id}`, annonce)
        }
        AddFile(file: any){
            return this.httpClient.post(`${this.API_URL}/addFile`, file)
        }
        afficherAllFiles (pageNo:any , pageSize:any): Observable<any>{
            return this.httpClient.get(`${this.API_URL}/retrieveAllFiles/${pageNo}/${pageSize}`);
        }
}

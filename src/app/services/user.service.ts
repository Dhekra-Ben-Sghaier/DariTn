import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Publicite} from "../models/Publicite";
import {User} from "../models/User";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(public httpClient: HttpClient) { }
  getUser() {
    return this.httpClient.get<User[]>("http://localhost:8085/Alluser");
  }
  getUserByContrat(id:number):Observable<User> {
    return this.httpClient.get<User>("http://localhost:8085/user/"+id);
  }
  addUser(data: any) {
    return this.httpClient.post("http://localhost:8085/addUser", data);
  }
  updateUser(id: number, data: any) {
    return this.httpClient.put("http://localhost:8085/updateUser/"+ id, data)
  }
  removeUser(id: number) {
    return this.httpClient.delete("http://localhost:8085/deleteUser/" + id)
  }
}

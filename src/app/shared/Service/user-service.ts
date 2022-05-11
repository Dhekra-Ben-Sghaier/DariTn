import { Injectable } from '@angular/core';

import { HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })
export class UserService {
    readonly API_URL = 'http://localhost:8084';
  
  constructor(private httpClient: HttpClient) { }

  getAllUsers() {
    return this.httpClient.get(`${this.API_URL}/admin/listUsers`)
  }
  addUser(user : any) {
    return this.httpClient.post(`${this.API_URL}/register`, user)
  }
  editUser(user : any){
    return this.httpClient.put(`${this.API_URL}/Mng/updateUser`, user)
  }
  deleteUSer(id : any){
    return  this.httpClient.delete(`${this.API_URL}/admin/deleteUser/${id}`)
  }
  signUp(user : any) {
    return this.httpClient.post(`${this.API_URL}/SpringPiDariTN/api/auth/signup`, user)
  }
  signIn(user : any) {
    return this.httpClient.post(`${this.API_URL}/SpringPiDariTN/api/auth/signin`, user)
  }

}
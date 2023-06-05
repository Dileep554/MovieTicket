import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import { User } from './User';
import { catchError, Observable } from 'rxjs';
import { ForgotPassword } from './ForgotPassword';

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  adminStatus:boolean=false
  status:boolean=false
  user!:User;
  private url='http://localhost:8080/api/v1.0/moviebooking';
  constructor(private httpClient:HttpClient) { }

  authenticate(loginId:string, password:string):Observable<boolean>{
     return this.httpClient.get<boolean>(this.url+'/login/'+loginId+'/'+password);

  }

  addUser(user:User):Observable<User>{
    return this.httpClient.post<User>(this.url+'/register',user);
  }
  changePassword(loginId:string,forgotPassword:ForgotPassword){
    return this.httpClient.put(this.url+"/"+loginId+'/forgot',forgotPassword, { responseType: 'text' });
  }
}

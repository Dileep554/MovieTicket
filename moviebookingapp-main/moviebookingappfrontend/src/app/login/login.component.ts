import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { BackendService } from '../backend.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  status!: boolean;
  errorMessage:string='username or password is not correct'
  form: any = {
    username:'' ,
    password!: ''
  };

  constructor(private service:BackendService, private route:Router , private aservice:AppComponent) { }
  authenticateUser(){
    this.service.authenticate(this.form.username, this.form.password).subscribe(data=>{
      this.status=data;
      this.service.status=data
      this.aservice.status=data
      if(this.status==true){
        this.route.navigate(['movieslist'])
      }
    })
    
  }

  signUp(){
    this.route.navigate(['register'])
  }

  adminLogin(){
    this.service.authenticate(this.form.username, this.form.password).subscribe(data=>{
      this.status=data;
      this.service.adminStatus=data
      this.aservice.status=data
      this.service.status=true
      if(this.status==true && this.service.adminStatus==true){
        //this.service.adminStatus=true
        this.route.navigate(['movieslist'])
      }
    })
  }
  ngOnInit(): void {
  }

}

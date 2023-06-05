import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../backend.service';
import { ForgotPassword } from '../ForgotPassword';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent implements OnInit {

  status!: boolean;
  errorMessage:string='Password is not matching.'
  message!:string
  errormsg='';
  //updatePassword!:ForgotPassword
  form: any = {
    loginId:'' ,
    password: '',
    confirmPassword:''
  };
  constructor(private service:BackendService,private route:Router) { }

  ngOnInit(): void {
  }

  forgotPassword(){

    if(this.form.password!=this.form.confirmPassword){
      //error("password not matching")
      this.status=false;
       this.errorMessage = "Password is not matching";
   }
   else{
     this.status=true
     console.log(this.form.password)
     //updatePassword :new ForgotPassword(this.form.password,this.form.confirmPassword);
     //console.log(updatePassword)
     this.service.changePassword(this.form.loginId,new ForgotPassword(this.form.password,this.form.confirmPassword) ).subscribe(data=>{
       this.message=data;
       console.log(data)
       alert(this.message);
     this.route.navigate(['']);
    },
    error =>{
      this.errormsg="Please Enter Correct Login ID";
    })
   }
  }
  cancel(){
    this.route.navigate(['']);
  }
}

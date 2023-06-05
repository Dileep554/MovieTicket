import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { error } from 'console';
import { BackendService } from '../backend.service';
import { User } from '../User';

@Component({
  selector: 'app-registeruser',
  templateUrl: './registeruser.component.html',
  styleUrls: ['./registeruser.component.css']
})
export class RegisteruserComponent implements OnInit {
  errorMessage = '';
  user!:User;
  user1!:User;
  status!:boolean;
  UserForm = new FormGroup({
    loginId: new FormControl('',Validators.required),
    email:new FormControl('',[Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
    firstName: new FormControl('',Validators.required),
    lastName: new FormControl('',Validators.required),
    password:new FormControl('',Validators.required),
    confirmPassword:new FormControl('',Validators.required),
    contactNumber:new FormControl('',[Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]),
    
  });
  get loginId(){
    return this.UserForm.controls['loginId']
  }
  get email(){
    return this.UserForm.controls['email']
  }
  get firstName(){
    return this.UserForm.controls['firstName']
  }
  get lastName(){
    return this.UserForm.controls['lastName']
  }
  get password(){
    return this.UserForm.controls['password']
  }
  get confirmPassword(){
    return this.UserForm.controls['confirmPassword']
  }
  get contactNumber(){
    return this.UserForm.controls['contactNumber']
  }

  
  constructor(private service:BackendService, private route:Router) { }

  add(){
    if(this.UserForm.controls['password'].value!=this.UserForm.controls['confirmPassword'].value){
       //error("password not matching")
       this.status=false;
        this.errorMessage = "Password is not matching";
    }
    else{
      this.status=true
      //console.log(this.UserForm.value)
      this.service.addUser(this.UserForm.value).subscribe(data=>{
        this.user=data;
        console.log(data)
      })
      alert("Registration is successful");
      this.route.navigate(['']);
      
    }
  }
  cancel(){
    this.route.navigate(['']);
  }
  ngOnInit(): void {
    // this.user1=this.UserForm.value;
  }

}

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { BackendService } from '../backend.service';

import { RegisteruserComponent } from './registeruser.component';

describe('RegisteruserComponent', () => {
  let component: RegisteruserComponent;
  let fixture: ComponentFixture<RegisteruserComponent>;
  let service: BackendService
  let router: Router

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisteruserComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FormsModule,ReactiveFormsModule],
      providers:[RegisteruserComponent]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisteruserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component = TestBed.inject(RegisteruserComponent)
    service = TestBed.inject(BackendService)
    router = TestBed.inject(Router)
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  describe('register user',()=>{
    it('add user fail',()=>{
      component.UserForm.controls['password'].setValue('12345')
      component.UserForm.controls['confirmPassword'].setValue('123459')
      component.add()
      expect(component.status).toBeFalsy()
    })
    it('add user pass',()=>{
      const user = {loginId:'nehal99',email:'nehal@gmail.com',firstName:'nehal',lastname:'ahmad',password:'12345'
    ,confirmPassword:'12345',contactNumber:'9876543212'}
      component.UserForm.controls['password'].setValue('12345')
      component.UserForm.controls['confirmPassword'].setValue('12345')
      component.add()
      spyOn(service,'addUser').and.returnValue(of(user))
      service.addUser(user).subscribe(data=>{
        expect(data).toEqual(user)
      })
      const navigateSpy = spyOn(router, 'navigate')
      component.add()
      expect(navigateSpy).toHaveBeenCalledWith(['']);
    })

    it('cancel',()=>{
      //component.cancel()
      const navigateSpy = spyOn(router, 'navigate')
      component.cancel()
      expect(navigateSpy).toHaveBeenCalledWith(['']);
    })
  })
});

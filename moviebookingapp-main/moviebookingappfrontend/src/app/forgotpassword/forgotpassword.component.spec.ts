import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { BackendService } from '../backend.service';
import { ForgotPassword } from '../ForgotPassword';

import { ForgotpasswordComponent } from './forgotpassword.component';

describe('ForgotpasswordComponent', () => {
  let component: ForgotpasswordComponent;
  let fixture: ComponentFixture<ForgotpasswordComponent>;
  let service: BackendService
  let router: Router
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ForgotpasswordComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FormsModule],
      providers:[ForgotpasswordComponent]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ForgotpasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component = TestBed.inject(ForgotpasswordComponent)
    service = TestBed.inject(BackendService)
    router = TestBed.inject(Router)
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  describe('forgot password',()=>{
    it('change password',()=>{
      const status='password changed'
      component.form.password='12345'
      component.form.confirmPassword='12345'
      component.forgotPassword();
      spyOn(service,'changePassword').and.returnValue(of(status))
      service.changePassword('12345',new ForgotPassword('12345','12345')).subscribe(data=>{
        expect(data).toEqual(status)
      })
      const navigateSpy = spyOn(router, 'navigate')
      component.forgotPassword();
      expect(navigateSpy).toHaveBeenCalledWith(['']);
    })

    it('reset form',()=>{
      component.cancel()
      const navigateSpy = spyOn(router, 'navigate')
      component.cancel();
      expect(navigateSpy).toHaveBeenCalledWith(['']);
    })
  })
});

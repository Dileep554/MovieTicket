import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { AppComponent } from '../app.component';
import { BackendService } from '../backend.service';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let service: BackendService
  let router: Router
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FormsModule],
      providers: [AppComponent, LoginComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component = TestBed.inject(LoginComponent)
    service = TestBed.inject(BackendService)
    router = TestBed.inject(Router)
  });


  it('should create', () => {
    expect(component).toBeTruthy();
  });
  describe('login component', () => {
    it('authenticate user', () => {
      const status = true
      component.authenticateUser();
      spyOn(service, 'authenticate').and.returnValue(of(status));
      service.authenticate('1234', '12345').subscribe(res => {
        expect(res).toBeTruthy();
      });
      const navigateSpy = spyOn(router, 'navigate')
      component.authenticateUser()
      expect(navigateSpy).toHaveBeenCalledWith(['movieslist']);
    })
    it('sign up',()=>{
      component.signUp()
      const navigateSpy = spyOn(router, 'navigate')
    component.signUp()
    expect(navigateSpy).toHaveBeenCalledWith(['register']);
    })
    it('authenticate admin', () => {
      const status = true
      component.adminLogin();
      spyOn(service, 'authenticate').and.returnValue(of(status));
      service.authenticate('1234', '12345').subscribe(res => {
        expect(res).toBeTruthy();
      });
      const navigateSpy = spyOn(router, 'navigate')
      component.adminLogin()
      expect(navigateSpy).toHaveBeenCalledWith(['movieslist']);
    })
  })
});

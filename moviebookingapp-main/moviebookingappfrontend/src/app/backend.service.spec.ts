import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { BackendService } from './backend.service';
import { ForgotPassword } from './ForgotPassword';
import { HttpClient } from '@angular/common/http';

describe('BackendService', () => {
  let service: BackendService;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;
  beforeEach(() => {
    
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers:[BackendService]
    });
    service = TestBed.get(BackendService);
    httpMock = TestBed.inject(HttpTestingController);
    httpClient = TestBed.inject(HttpClient);
  });
  describe('authenticate',()=>{
    it('should return true or false',()=>{
  const status=true
    // spyOn(service,'authenticate').and.returnValue(of(status));
    service.authenticate('1234','12345').subscribe(res=>{
      expect(res).toBeTruthy();
    });
    const mockRequest = httpMock.expectOne('http://localhost:8080/api/v1.0/moviebooking/login/1234/12345');
    expect(mockRequest.request.method).toEqual('GET')
    mockRequest.flush(status)
  });
  it('should return user',()=>{
    const user={loginId!:'1234',email!:'nehal@gmail.com',firstName!:'nehal',lastname!:'ahmad',
  password!:'12345',confirmPassword!:'12345',contactNumber!:'9876543211'
};
  let responce={};
  //spyOn(service,'addUser').and.returnValue(of(user));
  service.addUser(user).subscribe(res=>{
    expect(res).toEqual(user);
  });
  const mockRequest = httpMock.expectOne('http://localhost:8080/api/v1.0/moviebooking/register');
    expect(mockRequest.request.method).toEqual('POST')
    mockRequest.flush(user)
});
it('should return string',()=>{
 const msg = 'Password changed successfully';let responce='';
// spyOn(service,'changePassword').and.returnValue(of(msg));
service.changePassword('nehal123', new ForgotPassword('12345','123456')).subscribe(res=>{
  expect(res).toEqual(msg);
});
const mockRequest = httpMock.expectOne('http://localhost:8080/api/v1.0/moviebooking/nehal123/forgot');
expect(mockRequest.request.method).toEqual('PUT')
mockRequest.flush(msg)
});
  });
});

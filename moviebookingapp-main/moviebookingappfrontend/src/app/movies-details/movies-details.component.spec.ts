import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { MoviesService } from '../movies.service';

import { MoviesDetailsComponent } from './movies-details.component';

describe('MoviesDetailsComponent', () => {
  let component: MoviesDetailsComponent;
  let fixture: ComponentFixture<MoviesDetailsComponent>;
  let service: MoviesService
  let router: Router
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MoviesDetailsComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers:[MoviesDetailsComponent]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MoviesDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component = TestBed.inject(MoviesDetailsComponent)
    service = TestBed.inject(MoviesService)
    router = TestBed.inject(Router)
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  describe('movie detail',()=>{
    it('movie details',()=>{
      component.movieDetails();
      spyOn(service,'movieDetails').and.returnValue(of(5))
      service.movieDetails('RRR','PVR').subscribe(data=>{
        expect(data).toEqual(5)
      })
    })
  })
});

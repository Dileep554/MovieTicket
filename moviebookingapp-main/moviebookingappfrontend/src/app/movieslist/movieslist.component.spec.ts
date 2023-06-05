import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NgModule } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { LoginComponent } from '../login/login.component';
import { MoviesService } from '../movies.service';
import { NgxPaginationModule } from 'ngx-pagination';

import { MovieslistComponent } from './movieslist.component';

describe('MovieslistComponent', () => {
  let component: MovieslistComponent;
  let fixture: ComponentFixture<MovieslistComponent>;
  let service: MoviesService
  let router: Router
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MovieslistComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]),NgxPaginationModule],
      providers:[MovieslistComponent]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component = TestBed.inject(MovieslistComponent)
    service = TestBed.inject(MoviesService)
    router = TestBed.inject(Router)
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  describe('movies list',()=>{
    it('get movies list',()=>{
      const movies =[{key:{movieName:'RRR',theatreName:"PVR"},totalNoOfTickets:250},
      {key:{movieName:'Avenger',theatreName:"PVR"},totalNoOfTickets:350}];
      component.getMoviesList();
      spyOn(service,'getAllMovies').and.returnValue(of(movies));
      service.getAllMovies().subscribe(data=>{
        expect(data).toEqual(movies)
      })
      
    })
    it('get movie',()=>{
        component.getMovie();
      })
      it('get movie',()=>{
        component.name='RRR'
        component.getMovie();
        const navigateSpy = spyOn(router, 'navigate')
      component.getMovie()
      expect(navigateSpy).toHaveBeenCalledWith(['search']);
      })
    it('book ticket',()=>{
      const movie ={key:{movieName:'RRR',theatreName:"PVR"},totalNoOfTickets:250};
      component.bookTicket(movie);
      const navigateSpy = spyOn(router, 'navigate')
      component.bookTicket(movie)
      expect(navigateSpy).toHaveBeenCalledWith(['bookticket']);
    })

    it('delete movie',()=>{
      const msg='Movie Deleted Successfully'
      let response=''
      component.deleteMovie('RRR','PVR');
      spyOn(service,'deleteMovie').and.returnValue(of(msg));
      service.deleteMovie('RRR','PVR').subscribe(data=>{
        response= data
      })
      expect(response).toEqual(msg)
    })
    it('movie details',()=>{
      const movie ={key:{movieName:'RRR',theatreName:"PVR"},totalNoOfTickets:250};
      component.movieDetails(movie);
      const navigateSpy = spyOn(router, 'navigate')
      component.movieDetails(movie)
      expect(navigateSpy).toHaveBeenCalledWith(['moviedetails']);

    })

    it('change',()=>{
      component.onTableDataChange('')
    })
  })
});

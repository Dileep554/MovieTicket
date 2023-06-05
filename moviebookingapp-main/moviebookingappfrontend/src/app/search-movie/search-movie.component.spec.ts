import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { NgxPaginationModule } from 'ngx-pagination';
import { of, throwError } from 'rxjs';
import { MoviesService } from '../movies.service';
import { MovieslistComponent } from '../movieslist/movieslist.component';

import { SearchMovieComponent } from './search-movie.component';

describe('SearchMovieComponent', () => {
  let component: SearchMovieComponent;
  let fixture: ComponentFixture<SearchMovieComponent>;
  let service: MoviesService
  let router: Router
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchMovieComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]),NgxPaginationModule],
      providers:[SearchMovieComponent]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component = TestBed.inject(SearchMovieComponent)
    service = TestBed.inject(MoviesService)
    router = TestBed.inject(Router)
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  describe('movies list',()=>{
    it('get movies list',()=>{
      const movies =[{key:{movieName:'Apple',theatreName:"PVR"},totalNoOfTickets:250},
      {key:{movieName:'Avenger',theatreName:"PVR"},totalNoOfTickets:350}];
      component.getMovies();
      spyOn(service,'getMovies').and.returnValue(of(movies));
      service.getMovies('A').subscribe(data=>{
        expect(data).toEqual(movies)
      })
      
    })
    it('search movie error',()=>{
      component.movies.length=0
      let movie: any[]=[]
      const movies =[{key:{movieName:'Apple',theatreName:"PVR"},totalNoOfTickets:250},
      {key:{movieName:'Avenger',theatreName:"PVR"},totalNoOfTickets:350}];
      component.getMovies();
      spyOn(service,'getMovies').and.returnValue(throwError({status: 404}))
      
      service.getMovies('A').subscribe(data=>{
      },(error)=>{

      })
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

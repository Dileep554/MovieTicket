import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { MoviesService } from './movies.service';
import { of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

describe('MoviesService', () => {
  let service: MoviesService;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers:[MoviesService]
    });
    service = TestBed.inject(MoviesService);
    httpMock = TestBed.inject(HttpTestingController);
    httpClient = TestBed.inject(HttpClient);
  });
  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  describe('',()=>{
    it('get all movies',()=>{
      let response=[{}]
      const movies =[{key:{movieName:'RRR',theatreName:"PVR"},totalNoOfTickets:250},
      {key:{movieName:'Avenger',theatreName:"PVR"},totalNoOfTickets:350}];
      service.getAllMovies().subscribe(data=>{
        expect(data).toEqual(movies)
      })
      const mockRequest = httpMock.expectOne('http://localhost:8080/api/v1.0/moviebooking/all');
    expect(mockRequest.request.method).toEqual('GET')
    mockRequest.flush(movies)
    })

    it('get searched movies',()=>{
      let response=[{}]
      const movies =[{key:{movieName:'RRR',theatreName:"PVR"},totalNoOfTickets:250},
      {key:{movieName:'Avenger',theatreName:"PVR"},totalNoOfTickets:350}];
      service.getMovies('RRR').subscribe(data=>{
        expect(data).toEqual(movies)
      })
      const mockRequest = httpMock.expectOne('http://localhost:8080/api/v1.0/moviebooking/movies/search/RRR');
    expect(mockRequest.request.method).toEqual('GET')
    mockRequest.flush(movies)
    })

    it('delete movie',()=>{
      const movie ={key:{movieName:'RRR',theatreName:"PVR"},totalNoOfTickets:250};
      service.deleteMovie('RRR','PVR').subscribe(data=>{
        expect(data).toEqual("movie deleted successfully")
      })
      const mockRequest = httpMock.expectOne('http://localhost:8080/api/v1.0/moviebooking/RRR/PVR/delete');
    expect(mockRequest.request.method).toEqual('DELETE')
    })

    it('movie details',()=>{
      const movie ={key:{movieName:'RRR',theatreName:"PVR"},totalNoOfTickets:250};
      service.movieDetails('RRR','PVR').subscribe(data=>{
        expect(data).toEqual(0)
      })
      const mockRequest = httpMock.expectOne('http://localhost:8080/api/v1.0/moviebooking/bookedmovies/RRR/PVR');
    expect(mockRequest.request.method).toEqual('GET')
    })
  })
});

import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisteruserComponent } from './registeruser/registeruser.component';
import { MovieslistComponent } from './movieslist/movieslist.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { SearchMovieComponent } from './search-movie/search-movie.component';
import { TicketBookingComponent } from './ticket-booking/ticket-booking.component';
import { MoviesDetailsComponent } from './movies-details/movies-details.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisteruserComponent,
    MovieslistComponent,
    ForgotpasswordComponent,
    SearchMovieComponent,
    TicketBookingComponent,
    MoviesDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

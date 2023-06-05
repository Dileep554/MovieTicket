import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthguardService } from './authguard.service';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { LoginComponent } from './login/login.component';
import { MoviesDetailsComponent } from './movies-details/movies-details.component';
import { MovieslistComponent } from './movieslist/movieslist.component';
import { RegisteruserComponent } from './registeruser/registeruser.component';
import { SearchMovieComponent } from './search-movie/search-movie.component';
import { TicketBookingComponent } from './ticket-booking/ticket-booking.component';

const routes: Routes = [{path:'',component:LoginComponent},
{path:'register', component:RegisteruserComponent},
{path:'movieslist', component: MovieslistComponent, canActivate: [AuthguardService]},
{path :'forgotpassword',component:ForgotpasswordComponent},
{path:'search', component:SearchMovieComponent,canActivate: [AuthguardService]},
{path:'bookticket', component:TicketBookingComponent,canActivate: [AuthguardService]},
{path:'moviedetails',component:MoviesDetailsComponent,canActivate: [AuthguardService]}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

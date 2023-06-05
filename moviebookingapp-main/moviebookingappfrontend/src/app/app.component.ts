import { Component } from '@angular/core';
import { BackendService } from './backend.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'moviebookingapp';
  status:boolean=false
  constructor( private service:BackendService){}
  
  logout(){
    this.service.status=false
    this.service.adminStatus=false
    this.status=false
  }
}

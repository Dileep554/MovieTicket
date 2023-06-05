import { Movies } from "./Movies";

export class Tickets{
    constructor(
   public movie:Movies,
    public noOfTickets:number,
   public seatNumber:string[]=[]){

    }
}
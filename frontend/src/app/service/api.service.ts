import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ResultDto} from "../../model/dto/result-dto";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getResult(): Observable<ResultDto>{
    return this.http.get<ResultDto>('http://localhost:4200/v1/game-room/asd/player/sd/result');
  }
}

import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ResultDto} from "../../model/dto/result-dto";
import {ParameterService} from "./parameter.service";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient, private parameterService: ParameterService) {

  }

  getResult(): Observable<ResultDto> {
    let userName = this.parameterService.userName;
    let gameRoom = this.parameterService.gameRoom;
    return this.http.get<ResultDto>(`http://localhost:4200/v1/game-room/${gameRoom}/player/${userName}/result`);
  }

  resetGameRoom(): Observable<ResultDto> {
    let userName = this.parameterService.userName;
    let gameRoom = this.parameterService.gameRoom;
    return this.http.get<ResultDto>(`http://localhost:4200/v1/game-room/${gameRoom}/player/${userName}/reset`);
  }

  sendCode(code: string): Observable<any> {
    let userName = this.parameterService.userName;
    let gameRoom = this.parameterService.gameRoom;
    return this.http.post(`http://localhost:4200/v1/game-room/${gameRoom}/player/${userName}/register`, code);
    //todo change
    //  return this.http.post('http://localhost:4200/v1/game-room/asd/player/sd/register', code);
  }
}

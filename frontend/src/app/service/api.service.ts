import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ResultDto} from "../../model/dto/result-dto";
import {ParameterService} from "./parameter.service";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  // todo change for local development
  readonly BASE_URL = `https://some-assembly-required-backend.amapolis.dev`;
  // readonly BASE_URL = `http://localhost:4200`;

  constructor(private http: HttpClient, private parameterService: ParameterService) {

  }

  getResult(): Observable<ResultDto> {
    let userName = this.parameterService.userName;
    let gameRoom = this.parameterService.gameRoom;
    return this.http.get<ResultDto>(`${this.BASE_URL}/v1/game-room/${gameRoom}/player/${userName}/result`);
  }

  resetGameRoom(): Observable<ResultDto> {
    let userName = this.parameterService.userName;
    let gameRoom = this.parameterService.gameRoom;
    return this.http.get<ResultDto>(`${this.BASE_URL}/v1/game-room/${gameRoom}/player/${userName}/reset`);
  }

  sendCode(code: string): Observable<any> {
    let userName = this.parameterService.userName;
    let gameRoom = this.parameterService.gameRoom;
    return this.http.post(`${this.BASE_URL}/v1/game-room/${gameRoom}/player/${userName}/register`, code);
  }
}

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ParameterService {
  userName: string;
  gameRoom: string;

  constructor() {
    this.userName = "user-1"
    this.gameRoom = "game-room-1"
  }
}

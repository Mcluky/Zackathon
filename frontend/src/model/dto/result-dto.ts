import {GridDto} from "./grid-dto";

export interface ResultDto {
  startGrid: GridDto;
  turns: GridDto[];
  result: object;
  winner: string;
}

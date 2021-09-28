import {FieldDto} from "./field-dto";

export interface GridDto {
  name: string;
  field: FieldDto[][]
  isLastGrid: boolean;
}

import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'distributeType'
})
export class DistributeTypePipe implements PipeTransform {

  private dataValue = {
    1: '自取',
    2: '配送'
  };

  transform(value: any): any {
    return this.dataValue[value] || '';
  }

}

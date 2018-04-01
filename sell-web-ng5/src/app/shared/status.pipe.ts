import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'status'
})
export class StatusPipe implements PipeTransform {
  private statusMap = {
    'shop': {
      0: '',
      1: ''
    }
  };

  transform(status: any, type: string): any {
    if (this.statusMap[type]) {
      return this.statusMap[type][status];
    }
    return status;
  }
}

import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'datePipe'
})
export class DatePipePipe implements PipeTransform {

  transform(value: string): string {
    let date = value.split('T')[0];
    let time = value.split('T')[1].substring(0, 5).replace(":", "u");
    let year = date.split('-')[0]
    let month = date.split('-')[1]
    let day = date.split('-')[2]

    return `${day}/${month}/${year} - ${time}`;
  }

}

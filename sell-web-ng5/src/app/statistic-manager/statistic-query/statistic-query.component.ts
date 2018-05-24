import {Component, OnInit} from '@angular/core';
import {StatisticItem, StatisticService} from "../statistic.service";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-statistic-query',
  templateUrl: './statistic-query.component.html',
  styles: []
})
export class StatisticQueryComponent implements OnInit {

  dataList: StatisticItem[] = [];
  _loading = false;

  rangeTime: Date[];

  private datePipe: DatePipe = new DatePipe('en-US');

  constructor(private statisticService: StatisticService) {
  }

  ngOnInit() {
    this.queryData();
  }

  async queryData() {
    let startDate = '', endDate = '';
    if (this.rangeTime) {
      startDate = this.datePipe.transform(this.rangeTime[0], 'yyyy-MM-dd');
      endDate = this.datePipe.transform(this.rangeTime[1], 'yyyy-MM-dd');
    }

    this._loading = true;
    this.dataList = (await this.statisticService.count(startDate, endDate)).data || [];
    this._loading = false;
  }

}

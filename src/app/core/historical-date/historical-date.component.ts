import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: 'app-historical-date',
    templateUrl: './historical-date.component.html',
    styleUrls: ['./historical-date.component.scss']
})
export class HistoricalDateComponent implements OnInit {

    @Input() historicalDateDayOfMonthId: string;
    @Input() historicalDateMonthId: string;
    @Input() historicalDateYearId: string;

    constructor() {
    }

    ngOnInit() {
    }

}

import {Component, Input, OnInit} from '@angular/core';
import {ChoiceSelection} from './choice-selection';

@Component({
    selector: 'app-multi-column-choice-selector-modal',
    templateUrl: './multi-column-choice-selector-modal.component.html',
    styleUrls: ['./multi-column-choice-selector-modal.component.scss']
})
export class MultiColumnChoiceSelectorModalComponent implements OnInit {

    @Input()
    title: string;

    @Input()
    unselectedTitle: string;

    @Input()
    selectedTitle: string;

    @Input()
    unselectedChoices: Array<ChoiceSelection>;

    @Input()
    selectedChoices: Array<ChoiceSelection>;

    constructor() {
    }

    ngOnInit() {
    }

}

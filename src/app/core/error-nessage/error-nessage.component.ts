import {Component, Input, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-error-nessage',
  templateUrl: './error-nessage.component.html',
  styleUrls: ['./error-nessage.component.scss']
})
export class ErrorNessageComponent implements OnInit {

  @Input() private error: HttpErrorResponse;
  @Input() private failedOperation: string;

  constructor() { }

  ngOnInit() {
  }

}

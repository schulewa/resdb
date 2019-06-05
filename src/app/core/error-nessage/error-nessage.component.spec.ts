import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorNessageComponent } from './error-nessage.component';

describe('ErrorNessageComponent', () => {
  let component: ErrorNessageComponent;
  let fixture: ComponentFixture<ErrorNessageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ErrorNessageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ErrorNessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

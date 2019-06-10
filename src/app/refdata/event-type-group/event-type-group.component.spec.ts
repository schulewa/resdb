import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventTypeGroupComponent } from './event-type-group.component';

describe('EventTypeGroupComponent', () => {
  let component: EventTypeGroupComponent;
  let fixture: ComponentFixture<EventTypeGroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventTypeGroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventTypeGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimpleRefdataComponent } from './simple-refdata.component';

describe('SimpleRefdataComponent', () => {
  let component: SimpleRefdataComponent;
  let fixture: ComponentFixture<SimpleRefdataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimpleRefdataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimpleRefdataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

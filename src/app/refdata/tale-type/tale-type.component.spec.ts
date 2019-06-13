import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaleTypeComponent } from './tale-type.component';

describe('TaleTypeComponent', () => {
  let component: TaleTypeComponent;
  let fixture: ComponentFixture<TaleTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaleTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaleTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MultiColumnChoiceSelectorModalComponent } from './multi-column-choice-selector-modal.component';

describe('MultiColumnChoiceSelectorModalComponent', () => {
  let component: MultiColumnChoiceSelectorModalComponent;
  let fixture: ComponentFixture<MultiColumnChoiceSelectorModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MultiColumnChoiceSelectorModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MultiColumnChoiceSelectorModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

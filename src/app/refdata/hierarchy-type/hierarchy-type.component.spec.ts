import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HierarchyTypeComponent } from './hierarchy-type.component';

describe('HierarchyTypeComponent', () => {
  let component: HierarchyTypeComponent;
  let fixture: ComponentFixture<HierarchyTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HierarchyTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HierarchyTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

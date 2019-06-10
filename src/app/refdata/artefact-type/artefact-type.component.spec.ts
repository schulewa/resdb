import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtefactTypeComponent } from './artefact-type.component';

describe('ArtefactTypeComponent', () => {
  let component: ArtefactTypeComponent;
  let fixture: ComponentFixture<ArtefactTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArtefactTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArtefactTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

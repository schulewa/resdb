import { TestBed } from '@angular/core/testing';

import { MeasureTypeService } from './measure-type.service';

describe('MeasureTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MeasureTypeService = TestBed.get(MeasureTypeService);
    expect(service).toBeTruthy();
  });
});

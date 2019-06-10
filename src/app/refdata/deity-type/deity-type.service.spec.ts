import { TestBed } from '@angular/core/testing';

import { DeityTypeService } from './deity-type.service';

describe('DeityTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeityTypeService = TestBed.get(DeityTypeService);
    expect(service).toBeTruthy();
  });
});

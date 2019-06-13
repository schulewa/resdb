import { TestBed } from '@angular/core/testing';

import { RaceTypeService } from './race-type.service';

describe('RaceTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RaceTypeService = TestBed.get(RaceTypeService);
    expect(service).toBeTruthy();
  });
});

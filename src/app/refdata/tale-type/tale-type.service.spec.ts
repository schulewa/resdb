import { TestBed } from '@angular/core/testing';

import { TaleTypeService } from './tale-type.service';

describe('TaleTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TaleTypeService = TestBed.get(TaleTypeService);
    expect(service).toBeTruthy();
  });
});

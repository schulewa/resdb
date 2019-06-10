import { TestBed } from '@angular/core/testing';

import { EntityTypeService } from './entity-type.service';

describe('EntityTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EntityTypeService = TestBed.get(EntityTypeService);
    expect(service).toBeTruthy();
  });
});

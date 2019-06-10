import { TestBed } from '@angular/core/testing';

import { HierarchyTypeService } from './hierarchy-type.service';

describe('HierarchyTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HierarchyTypeService = TestBed.get(HierarchyTypeService);
    expect(service).toBeTruthy();
  });
});

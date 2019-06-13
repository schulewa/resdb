import { TestBed } from '@angular/core/testing';

import { TechnologyTypeGroupService } from './technology-type-group.service';

describe('TechnologyTypeGroupService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TechnologyTypeGroupService = TestBed.get(TechnologyTypeGroupService);
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { ArtefactGroupService } from './artefact-group.service';

describe('ArtefactGroupService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ArtefactGroupService = TestBed.get(ArtefactGroupService);
    expect(service).toBeTruthy();
  });
});

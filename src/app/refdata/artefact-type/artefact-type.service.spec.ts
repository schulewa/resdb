import { TestBed } from '@angular/core/testing';

import { ArtefactTypeService } from './artefact-type.service';

describe('ArtefactTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ArtefactTypeService = TestBed.get(ArtefactTypeService);
    expect(service).toBeTruthy();
  });
});

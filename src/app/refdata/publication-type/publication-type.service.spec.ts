import { TestBed } from '@angular/core/testing';

import { PublicationTypeService } from './publication-type.service';

describe('PublicationTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PublicationTypeService = TestBed.get(PublicationTypeService);
    expect(service).toBeTruthy();
  });
});

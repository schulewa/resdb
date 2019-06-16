import { TestBed } from '@angular/core/testing';

import { PersonDetailService } from './person-detail.service';

describe('PersonDetailService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PersonDetailService = TestBed.get(PersonDetailService);
    expect(service).toBeTruthy();
  });
});

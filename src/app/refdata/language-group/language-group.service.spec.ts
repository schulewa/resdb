import { TestBed } from '@angular/core/testing';

import { LanguageGroupService } from './language-group.service';

describe('LanguageGroupService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LanguageGroupService = TestBed.get(LanguageGroupService);
    expect(service).toBeTruthy();
  });
});

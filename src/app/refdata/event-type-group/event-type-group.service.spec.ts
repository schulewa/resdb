import { TestBed } from '@angular/core/testing';

import { EventTypeGroupService } from './event-type-group.service';

describe('EventTypeGroupService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EventTypeGroupService = TestBed.get(EventTypeGroupService);
    expect(service).toBeTruthy();
  });
});

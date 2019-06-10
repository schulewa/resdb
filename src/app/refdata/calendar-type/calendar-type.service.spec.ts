import { TestBed } from '@angular/core/testing';

import { CalendarTypeService } from './calendar-type.service';

describe('CalendarTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CalendarTypeService = TestBed.get(CalendarTypeService);
    expect(service).toBeTruthy();
  });
});

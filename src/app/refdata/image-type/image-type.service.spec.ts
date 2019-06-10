import { TestBed } from '@angular/core/testing';

import { ImageTypeService } from './image-type.service';

describe('ImageTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ImageTypeService = TestBed.get(ImageTypeService);
    expect(service).toBeTruthy();
  });
});

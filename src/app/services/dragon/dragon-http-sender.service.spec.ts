import { TestBed } from '@angular/core/testing';

import { DragonHttpSenderService } from './dragon-http-sender.service';

describe('DragonHttpSenderService', () => {
  let service: DragonHttpSenderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DragonHttpSenderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

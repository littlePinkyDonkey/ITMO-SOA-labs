import { TestBed } from '@angular/core/testing';

import { ArrayHttpSenderService } from './array-http-sender.service';

describe('ArrayHttpSenderService', () => {
  let service: ArrayHttpSenderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ArrayHttpSenderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

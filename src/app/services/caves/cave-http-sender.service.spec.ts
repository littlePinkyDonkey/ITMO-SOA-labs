import { TestBed } from '@angular/core/testing';

import { CaveHttpSenderService } from './cave-http-sender.service';

describe('CaveHttpSenderService', () => {
  let service: CaveHttpSenderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CaveHttpSenderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

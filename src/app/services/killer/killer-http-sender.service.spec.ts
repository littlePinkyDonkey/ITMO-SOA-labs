import { TestBed } from '@angular/core/testing';

import { KillerHttpSenderService } from './killer-http-sender.service';

describe('KillerHttpSenderService', () => {
  let service: KillerHttpSenderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KillerHttpSenderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

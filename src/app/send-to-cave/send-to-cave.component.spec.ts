import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendToCaveComponent } from './send-to-cave.component';

describe('SendToCaveComponent', () => {
  let component: SendToCaveComponent;
  let fixture: ComponentFixture<SendToCaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendToCaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SendToCaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

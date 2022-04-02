import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddKillerComponent } from './add-killer.component';

describe('AddKillerComponent', () => {
  let component: AddKillerComponent;
  let fixture: ComponentFixture<AddKillerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddKillerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddKillerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetParametersComponent } from './set-parameters.component';

describe('SetParametersComponent', () => {
  let component: SetParametersComponent;
  let fixture: ComponentFixture<SetParametersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SetParametersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SetParametersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

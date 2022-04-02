import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllCavesComponent } from './all-caves.component';

describe('AllCavesComponent', () => {
  let component: AllCavesComponent;
  let fixture: ComponentFixture<AllCavesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllCavesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllCavesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDragonComponent } from './add-dragon.component';

describe('AddDragonComponent', () => {
  let component: AddDragonComponent;
  let fixture: ComponentFixture<AddDragonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDragonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDragonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

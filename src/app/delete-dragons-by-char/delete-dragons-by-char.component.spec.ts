import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteDragonsByCharComponent } from './delete-dragons-by-char.component';

describe('DeleteDragonsByCharComponent', () => {
  let component: DeleteDragonsByCharComponent;
  let fixture: ComponentFixture<DeleteDragonsByCharComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteDragonsByCharComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteDragonsByCharComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

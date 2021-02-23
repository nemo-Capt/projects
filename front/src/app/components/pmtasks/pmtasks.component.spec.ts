import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PmtasksComponent } from './pmtasks.component';

describe('PmtasksComponent', () => {
  let component: PmtasksComponent;
  let fixture: ComponentFixture<PmtasksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PmtasksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PmtasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

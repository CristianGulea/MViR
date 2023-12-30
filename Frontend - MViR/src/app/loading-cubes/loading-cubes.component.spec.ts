import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadingCubesComponent } from './loading-cubes.component';

describe('LoadingCubesComponent', () => {
  let component: LoadingCubesComponent;
  let fixture: ComponentFixture<LoadingCubesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoadingCubesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoadingCubesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

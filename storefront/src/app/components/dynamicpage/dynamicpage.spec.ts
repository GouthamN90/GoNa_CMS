import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Dynamicpage } from './dynamicpage';

describe('Dynamicpage', () => {
  let component: Dynamicpage;
  let fixture: ComponentFixture<Dynamicpage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Dynamicpage],
    }).compileComponents();

    fixture = TestBed.createComponent(Dynamicpage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

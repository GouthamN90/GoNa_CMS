import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Previewpage } from './previewpage';

describe('Previewpage', () => {
  let component: Previewpage;
  let fixture: ComponentFixture<Previewpage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Previewpage],
    }).compileComponents();

    fixture = TestBed.createComponent(Previewpage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

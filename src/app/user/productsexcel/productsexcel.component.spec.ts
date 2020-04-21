import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductsexcelComponent } from './productsexcel.component';

describe('ProductsexcelComponent', () => {
  let component: ProductsexcelComponent;
  let fixture: ComponentFixture<ProductsexcelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductsexcelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductsexcelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

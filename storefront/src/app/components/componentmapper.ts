import { Type } from '@angular/core';
import { BannerComponent } from '../components/banner-component/banner-component';
import { Headercomponent } from './headercomponent/headercomponent';

export const COMPONENT_MAP: Record<string, Type<any>> = {
  'BannerComponent': BannerComponent,
  'SimpleHeaderComponent': Headercomponent
};
import { Routes } from '@angular/router';
import { DynamicPage } from './components/dynamicpage/dynamicpage'

export const routes: Routes = [
    {
        path:'**',
        component: DynamicPage
    }
];

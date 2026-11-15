import { Routes } from '@angular/router';
import { DynamicPage } from './components/dynamicpage/dynamicpage'
import { PreviewPage } from './components/dynamicpage/previewpage'

export const routes: Routes = [
    {
        path:'**',
        component: DynamicPage
    },
    {
        path:'/preview-page',
        component: PreviewPage
    }
];

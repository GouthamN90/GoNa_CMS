import { Routes } from '@angular/router';
import { DynamicPage } from './components/dynamicpage/dynamicpage'
import { PreviewPage } from './components/previewpage/previewpage'

export const routes: Routes = [
    {
        path:'preview-page',
        component: PreviewPage
    },
    {
        path:'**',
        component: DynamicPage
    }
    
];

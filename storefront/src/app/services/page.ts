import { Service, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface PageData {
    uid: string;
    name: string;
    title: string;
    metadata: string;
    components: Array<{
        uid: string;
        name: string;
        template: string;
    }>;
}

@Service()
export class PageService {

    private http = inject(HttpClient);

    getPageInfo(uid: string): Observable<PageData> {
        return this.http.get<PageData>(`${environment.apiUrl}/page/${uid}`);
    }

    getPageInfoPreviewMode(uid: string): Observable<PageData> {
        return this.http.get<PageData>(`${environment.apiUrl}/preview-page/${uid}`);
    }

}

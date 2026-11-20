import { Service, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Service()
export class ComponentService {

    private http = inject(HttpClient);

    getComponentInfo(uid: string, previewMode: boolean, version: string): Observable<any> {
        if (previewMode) {
            return this.http.get<any>(`${environment.apiUrl}/preview-component/${uid}/${version}`);
        }
        return this.http.get<any>(`${environment.apiUrl}/component/${uid}`);
    }

    savePreviewImage(uid: string, version: string, screenshotString: string): Observable<any> {

        const request = {
            uid: uid,            
            version: version,           
            screenshotString: screenshotString
        };

        return this.http.post<any>(`${environment.apiUrl}/save_previewimage`, request);

    }

}

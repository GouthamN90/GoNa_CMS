import { Service, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Service()
export class ComponentService {

    private http = inject(HttpClient);

    getComponentInfo(uid: string): Observable<any> {
        return this.http.get<any>(`${environment.apiUrl}/component/${uid}`);
    }

}

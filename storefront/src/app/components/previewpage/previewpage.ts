import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  imports: [],
  selector: 'app-previewpage',
  styleUrl: './previewpage.css',
  templateUrl: './previewpage.html',
})
export class PreviewPage implements OnInit {
  private route = inject(ActivatedRoute);

  pageUid: string | null = null;
  componentUid: string | null = null;

  ngOnInit(): void {
    this.route.queryParamMap.subscribe((params) => {
      this.pageUid = params.get('pageUid');
      this.componentUid = params.get('componentUid');

      //if componentUid is present, call getComponentInfoPreviewMode.
      if (this.componentUid != null) {
        // Call the service method to fetch component info in preview mode
      }

      //if pageUid is present, call getPageInfoPreviewMode.
      if (this.pageUid != null) {
        // Call the service method to fetch page info in preview mode
      }
    });
  }
}

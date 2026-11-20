import { ChangeDetectorRef, Component, OnInit, inject, ElementRef, ViewChild } from '@angular/core';
import { COMPONENT_MAP } from '../../components/componentmapper';
import { NgComponentOutlet } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { PageService, PageData } from '../../services/page';
import html2canvas from 'html2canvas';
import { ComponentService } from '../../services/component-service';

@Component({
  imports: [NgComponentOutlet],
  selector: 'app-previewpage',
  styleUrl: './previewpage.css',
  templateUrl: './previewpage.html',
})
export class PreviewPage implements OnInit {

  private route = inject(ActivatedRoute);
  private pageService = inject(PageService);
  private changeDetector = inject(ChangeDetectorRef);
  private componentService = inject(ComponentService);


  pageUid: string | null = null;
  componentUid: string | null = null;
  componentTemplate: string | null = null;
  version: string | null = null;

  pageData: any = {};
  
  components: any[] = [{}];

  showPageCaptureButton = false;
  isSavingPreview = false;
  previewMessage = '';
  previewSaveFailed = false;

  @ViewChild('captureArea')
  private captureArea!: ElementRef<HTMLElement>;


  ngOnInit(): void {
    this.route.queryParamMap.subscribe((params) => {
      this.pageUid = params.get('pageUid');
      this.componentUid = params.get('componentUid');
      this.componentTemplate = params.get('componentTemplate');
      if (params.get('version') != null || params.get('version') != '') {
        this.version = params.get('version');
      } else {
        this.version = 'Draft';
      }
      

      if (this.componentUid != null && this.componentTemplate != null) {
        this.components = [{
          uid : this.componentUid,
          name : '',
          template : COMPONENT_MAP[this.componentTemplate],
          version : this.version
        }]
        this.showPageCaptureButton = true;
      }

      if (this.pageUid != null) {
        const pageUid = this.pageUid;
        const version = this.version ?? 'Draft';
        this.pageService.getPageInfoPreviewMode(pageUid, version).subscribe({
          next:(result) => {
              this.pageData = result;
              this.components = this.pageData.components.map((comp: { name: string; uid: string; template: string }) => ({
                name: comp.name,
                uid: comp.uid,
                template: COMPONENT_MAP[comp.template],
                version : this.version
              }));
            this.changeDetector.markForCheck();
          },
          error:(error) => {
            //TODO decide what needs to be done
          }
        });
      }
    });
  }

  async captureAndSend(): Promise<void> {

    this.isSavingPreview = true;
    this.previewMessage = '';

    const canvas = await html2canvas(this.captureArea.nativeElement, {
      useCORS: true,
      scale: 1
    });

    const screenshotString = canvas.toDataURL('image/png');
    const compUid = this.componentUid;
    const version = this.version;
    if (compUid === null || version === null) {
      return;
    }

    this.componentService.savePreviewImage(compUid, version, screenshotString).subscribe({
      next: () => { 
        console.log('POST succeeded; response:');
        this.previewMessage = 'Preview image saved successfully.';
        this.previewSaveFailed = false;
        this.isSavingPreview = false;
        this.changeDetector.markForCheck();
       },
      error: (error) => { 
        console.error('Failed to save preview image', error);
        this.previewMessage = 'Could not save the preview image.';
        this.previewSaveFailed = true;
        this.isSavingPreview = false;
        this.changeDetector.markForCheck();
       }
    });
  }
}

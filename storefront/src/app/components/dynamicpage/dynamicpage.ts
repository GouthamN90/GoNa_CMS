import { ChangeDetectorRef, Component, OnInit, inject, DestroyRef } from '@angular/core';
import { COMPONENT_MAP } from '../../components/componentmapper';
import { NgComponentOutlet } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { PageService, PageData } from '../../services/page';
import { Title, Meta } from '@angular/platform-browser';
import { Router, NavigationEnd } from '@angular/router';
import { filter, switchMap } from 'rxjs/operators';
import { merge, of } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-dynamicpage',
  imports: [NgComponentOutlet],
  templateUrl: './dynamicpage.html',
  styleUrl: './dynamicpage.css',
})
export class DynamicPage implements OnInit {

  private pageService = inject(PageService);
  private titleService = inject(Title);
  private metaService = inject(Meta);
  private router = inject(Router);
  private destroyRef = inject(DestroyRef);
  private changeDetector = inject(ChangeDetectorRef);

  //TODO check why signal is not working. if I call applyMetadata(page: PageData) throws error
  //pageData = signal<PageData | null>(null);

  pageData: any = {};
  
  components: any[] = [{}];

  currentUrl : string = '';

  ngOnInit() {
    merge(
      of(null),
      this.router.events.pipe(filter((event) => event instanceof NavigationEnd))
    ).pipe(
      switchMap(() => {
        this.getCurrentUrl();
        this.pageData = {};
        this.components = [];
        return this.pageService.getPageInfo(this.currentUrl);
      }),
      takeUntilDestroyed(this.destroyRef)
    ).subscribe({
      next:(result) => {
        this.pageData = result;
        this.applyMetadata(this.pageData);
        this.components = this.pageData.components.map((comp: { name: string; uid: string; template: string }) => ({
            name: comp.name,
            uid: comp.uid,
            template: COMPONENT_MAP[comp.template]
        }));
        this.changeDetector.markForCheck();
      },
      error:(error) => {
        //TODO go to maintenance page
      }
    });
    
  }

  private getCurrentUrl() : void {
    var routerUrl = this.router.url;
    var url = routerUrl.replace('/','');
    this.currentUrl = url != '' ? url : 'homepage';
  }

  private applyMetadata(page: any): void {
    // Dynamic SEO title
    this.titleService.setTitle(`${page.title} | Storefront`);
    
    // Dynamic Meta Description for search crawlers in SSR
    if (page.metadata) {
      this.metaService.updateTag({ name: 'description', content: page.metadata });
    }
  }
}

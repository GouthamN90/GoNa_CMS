import { Component, OnInit, inject, Input, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { NgComponentOutlet } from '@angular/common';
import { ComponentService } from '../../services/component-service';

@Component({
  selector: 'app-banner-component',
  imports: [],
  templateUrl: './banner-component.html',
  styleUrl: './banner-component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class BannerComponent implements OnInit {

  bannerText: string = '';
  imageUrl: string = '';
  ctaLink: string = '';

  @Input() componentUid : string = '';
  @Input() componentName : string = '';

  private componentService = inject(ComponentService);
  private cdr = inject(ChangeDetectorRef);

  //TODO try this call in ngAfterContentInit()
  ngOnInit() {
    this.componentService.getComponentInfo(this.componentUid).subscribe({
      next:(result) => {
        // TODO if result is empty decide what needs to be shown
        this.imageUrl = result.imageUrl;
        this.ctaLink = result.ctaLink;
        this.bannerText = result.bannerText;
        this.cdr.markForCheck();
      },
      error:(error) => {
        //TODO decide what needs to be done
      }
    });
  }

}

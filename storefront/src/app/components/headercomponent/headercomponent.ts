import { Component, ElementRef, HostListener,  OnInit, inject, Input, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ComponentService } from '../../services/component-service';

export interface SubNavItem {
  label: string;
  link: string;
}

export interface NavItem {
  label: string;
  link?: string;
  children?: SubNavItem[];
}

@Component({
  selector: 'app-headercomponent',
  imports: [CommonModule, RouterModule],
  templateUrl: './headercomponent.html',
  styleUrl: './headercomponent.css',
})
export class Headercomponent {

  isMobileMenuOpen = false;
  openDesktopIndex: number | null = null;
  openMobileIndex: number | null = null;

  imageUrl: string = '';
  tagline: string = '';

  navItems: NavItem[] = [];

  @Input() componentUid : string = '';
  @Input() componentName : string = '';

  private componentService = inject(ComponentService);
  private cdr = inject(ChangeDetectorRef);
  private elementRef: ElementRef = inject(ElementRef);


  ngOnInit() {
    console.log(this.componentUid);
    this.componentService.getComponentInfo(this.componentUid).subscribe({
      next:(result) => {
        this.imageUrl = result.imageUrl;
        this.tagline = result.tagLine;
        // loop through the result.navbar.navnodes and assign them to this.navItems
        var resultNavNodes = result.navbar.navnodes;
        resultNavNodes.forEach((navNode: {childNavNodes: any; name: any; link: any;}) => {
          var subNavItems: SubNavItem[] = [];
          // check if navNode.childNodes is not empty and loop through it to create subNavItems
          if (navNode.childNavNodes && navNode.childNavNodes.length > 0) {
            navNode.childNavNodes.forEach((childNode: { name: any; link: any;}) => {
              var subNavItem: SubNavItem = {
                label: childNode.name,
                link: childNode.link
              };
              subNavItems.push(subNavItem);
            });
          }
          var navItem: NavItem = {
            label: navNode.name,
            link: navNode.link,
            children: subNavItems
          };
          this.navItems.push(navItem);
        });
        

        this.cdr.markForCheck();
      },
      error:(error) => {
        //TODO decide what needs to be done
      }
    });
  }

  // Desktop click handler
  toggleDesktopMenu(index: number, event: MouseEvent): void {
    event.stopPropagation();
    this.openDesktopIndex = this.openDesktopIndex === index ? null : index;
  }

  // Mobile accordion click handler
  toggleMobileMenuAccordion(index: number): void {
    this.openMobileIndex = this.openMobileIndex === index ? null : index;
  }

  toggleMobileNav(): void {
    this.isMobileMenuOpen = !this.isMobileMenuOpen;
    if (!this.isMobileMenuOpen) {
      this.openMobileIndex = null;
    }
  }

  closeMobileNav(): void {
    this.isMobileMenuOpen = false;
    this.openMobileIndex = null;
  }

  closeAllDropdowns(): void {
    this.openDesktopIndex = null;
  }

  // Close desktop dropdown when clicking anywhere outside
  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent): void {
    if (!this.elementRef.nativeElement.contains(event.target)) {
      this.closeAllDropdowns();
    }
  }

  // Close on Escape key press
  @HostListener('document:keydown.escape')
  onEscapePress(): void {
    this.closeAllDropdowns();
    this.closeMobileNav();
  }

}

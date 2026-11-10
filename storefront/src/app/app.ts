import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DynamicPage } from './components/dynamicpage/dynamicpage'

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, DynamicPage],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('storefront');
}

import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
    selector: 'sidebar-menu',
    template:`
  <div class="container-fluid">
    <div class="row">
        <div class="col-lg-1">
            <ul class="nav nav-pills nav-stacked" style="display: inline-block;">
              <li *ngFor="let item of items" [class.active]="item.active">
                <a href="{{item.id}}" (click)="select(item, $event)"><i *ngIf="item.icon" class="{{item.icon}}"></i>&nbsp;{{item.title}}</a>
              </li>
            </ul>
        </div>
        <div class="col-lg-11">
          <ng-content></ng-content>
        </div>
    </div>
  </div>  
  `
})
export class SidebarMenuComponent {
    @Output() selected = new EventEmitter(true);

    items: MenuItemComponent[];

    constructor() {
        this.items = [];
    }

    select(item: MenuItemComponent, event:MouseEvent) {
        this.items.forEach((item: MenuItemComponent) => {
            item.active = false;
        });
        item.active = true;

        this.selected.emit(item);

        event.stopPropagation();
        return false;
    }

    addMenuItem(item: MenuItemComponent) {
        this.items.push(item);
    }
}

@Component({
    selector: 'item',
    template: `
    <div id="{{id}}" class="tab-pane" [hidden]="!active">
      <ng-content></ng-content>
    </div>
  `
})
export class MenuItemComponent {
    @Input('item-id') id: string;
    @Input('item-title') title: string;
    @Input('item-icon') icon: string;
    @Input() active: boolean;

    constructor(items: SidebarMenuComponent){
        items.addMenuItem(this);
    }
}

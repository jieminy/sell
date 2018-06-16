import {Component, OnInit} from '@angular/core';
import {makeMenu, MenuItem} from "./menu-item";
import {Router} from "@angular/router";
import {AuthService} from "../shared/auth.service";
import {AudioService} from "../shared/audio.service";
import {SocketService} from "../shared/socket.service";

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrls: ['./main-layout.component.less']
})
export class MainLayoutComponent implements OnInit {

  menus: MenuItem[];

  constructor(private router: Router,
              private authService: AuthService,
              private audioService: AudioService,
              private socketService: SocketService) {
  }

  ngOnInit() {
    this.menus = makeMenu(this.authService.currentUser);
    this.activeCurrentMenu();

    this.socketService.onmessage = (ev: MessageEvent) => {
      console.log('socket onmessage', ev);
      this.audioService.play();
    };
  }

  // 默认激活选中当前的菜单
  activeCurrentMenu() {
    const {url} = this.router;
    for (let i = 0; i < this.menus.length; i++) {
      const menu = this.menus[i];
      if (menu.route && menu.route === url) {
        menu.selected = true;
        break;
      } else if (menu.children) {
        for (let j = 0; j < menu.children.length; j++) {
          const child = menu.children[j];
          if (child.route && child.route === url) {
            child.selected = true;
            menu.open = true;
            break;
          }
        }
      }
    }
  }
}

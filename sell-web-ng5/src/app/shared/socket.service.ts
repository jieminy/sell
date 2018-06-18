import {Injectable} from '@angular/core';

@Injectable()
export class SocketService {

  private socket: WebSocket;
  private host = location.host;
  private socketPath = '/sell/webSocket';

  constructor() {
    this.initSocket();
  }

  private initSocket() {
    const self = this;

    createSocket();

    this.socket.onmessage = (ev: MessageEvent) => {
      this.onmessage(ev);
    };

    this.socket.onerror = (ev: Event) => {
      if (this.socket.readyState !== 1) {
        createSocket();
      }
    };

    function createSocket() {
      const ws = location.protocol === 'https:' ? 'wss' : 'ws';
      self.socket = new WebSocket(`${ws}:${self.host}${self.socketPath}`);
    }
  }

  public onmessage(ev: MessageEvent) {
  }

}

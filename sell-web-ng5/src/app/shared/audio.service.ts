import {Injectable} from '@angular/core';

@Injectable()
export class AudioService {
  private audio: HTMLAudioElement;
  private mp3 = 'assets/new-message.mp3';

  constructor() {
    this.initAudio();
  }

  private initAudio() {
    this.audio = document.createElement('audio');
    this.audio.autoplay = true;
  }

  public play(mp3?: string) {
    this.audio.src = mp3 || this.mp3;
  }

}

import {Injectable} from '@angular/core';

@Injectable()
export class AuthService {

  currentUser: User = {admin: true};

  constructor() {
  }

}


export interface User {
  admin?: boolean;
}

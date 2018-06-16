import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../shared/auth.service";
import {Router} from "@angular/router";
import {NzMessageService} from "ng-zorro-antd";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent implements OnInit {

  validateForm: FormGroup;


  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private ngMessageService: NzMessageService,
              private router: Router) {
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true],
    });
  }

  async _submitForm() {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
    }

    if (this.validateForm.valid) {
      const res = await this.authService.login(this.validateForm.value);
      if (res.code === 0) {
        this.router.navigate(['/main/index']);
      } else {
        this.ngMessageService.warning(res.msg);
      }
    }
  }
}

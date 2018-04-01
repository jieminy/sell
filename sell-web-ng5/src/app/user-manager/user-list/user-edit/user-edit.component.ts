import {Component, Input, OnInit} from '@angular/core';
import {UserItem} from "../../user-item";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzModalSubject} from "ng-zorro-antd";
import {CommonModal} from "../../../shared/common-modal";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styles: []
})
export class UserEditComponent extends CommonModal implements OnInit {
  @Input()
  user: UserItem;

  validateForm: FormGroup;

  constructor(subject: NzModalSubject,
              private fb: FormBuilder) {
    super(subject);
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      sellerId: [null],
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
      admin: [false]
    });
    this.validateForm.patchValue(this.user);
  }
}

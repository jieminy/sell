import {FormGroup} from "@angular/forms";
import {NzModalSubject} from "ng-zorro-antd";

export class CommonModal {
  validateForm: FormGroup;

  constructor(protected subject: NzModalSubject) {
  }

  emitDataOutside() {
    this.subject.next(this.validateForm.value);
  }

  handleCancel(e) {
    this.subject.destroy('onCancel');
  }

  _submitForm() {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
    }

    if (this.validateForm.valid) {
      this.emitDataOutside();
    }
  }

  getFormControl(name): any {
    return this.validateForm.controls[name] || {};
  }
}

/* SystemJS module definition */
declare var module: NodeModule;

interface NodeModule {
  id: string;
}

interface AjaxResult<T> {
  code: number; // 0 成功，1 失败，2 未登录
  msg: string;
  data: T;
}

interface PagingResult<T> {
  content: T;
  first: boolean;
  last: boolean;
  number: number;
  size: number;
  totalElements: number;
  totalPages: number;
}

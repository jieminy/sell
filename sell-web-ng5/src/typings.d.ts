/* SystemJS module definition */
declare var module: NodeModule;

interface NodeModule {
  id: string;
}

interface AjaxResult<T> {
  code: number;
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

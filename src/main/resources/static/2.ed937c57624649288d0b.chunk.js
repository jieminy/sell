webpackJsonp([2],{rI0G:function(n,l,t){"use strict";Object.defineProperty(l,"__esModule",{value:!0});var _=t("WT6e"),u=function(){},e=t("fg6F"),a=t("GoJJ"),i=t("7DMc"),o=t("Xjw4"),r=t("ItHS"),c=function(){function n(n){this.http=n}return n.prototype.count=function(n,l){return this.http.get("statistic/product/num",{params:{stDate:n,edDate:l}}).toPromise()},n}(),s=this&&this.__awaiter||function(n,l,t,_){return new(t||(t=Promise))(function(u,e){function a(n){try{o(_.next(n))}catch(n){e(n)}}function i(n){try{o(_.throw(n))}catch(n){e(n)}}function o(n){n.done?u(n.value):new t(function(l){l(n.value)}).then(a,i)}o((_=_.apply(n,l||[])).next())})},d=this&&this.__generator||function(n,l){var t,_,u,e,a={label:0,sent:function(){if(1&u[0])throw u[1];return u[1]},trys:[],ops:[]};return e={next:i(0),throw:i(1),return:i(2)},"function"==typeof Symbol&&(e[Symbol.iterator]=function(){return this}),e;function i(e){return function(i){return function(e){if(t)throw new TypeError("Generator is already executing.");for(;a;)try{if(t=1,_&&(u=_[2&e[0]?"return":e[0]?"throw":"next"])&&!(u=u.call(_,e[1])).done)return u;switch(_=0,u&&(e=[0,u.value]),e[0]){case 0:case 1:u=e;break;case 4:return a.label++,{value:e[1],done:!1};case 5:a.label++,_=e[1],e=[0];continue;case 7:e=a.ops.pop(),a.trys.pop();continue;default:if(!(u=(u=a.trys).length>0&&u[u.length-1])&&(6===e[0]||2===e[0])){a=0;continue}if(3===e[0]&&(!u||e[1]>u[0]&&e[1]<u[3])){a.label=e[1];break}if(6===e[0]&&a.label<u[1]){a.label=u[1],u=e;break}if(u&&a.label<u[2]){a.label=u[2],a.ops.push(e);break}u[2]&&a.ops.pop(),a.trys.pop();continue}e=l.call(n,a)}catch(n){e=[6,n],_=0}finally{t=u=0}if(5&e[0])throw e[1];return{value:e[0]?e[1]:void 0,done:!0}}([e,i])}}},h=function(){function n(n){this.statisticService=n,this.dataList=[],this._loading=!1,this.datePipe=new o.f("en-US")}return n.prototype.ngOnInit=function(){this.queryData()},n.prototype.queryData=function(){return s(this,void 0,void 0,function(){var n,l,t;return d(this,function(_){switch(_.label){case 0:return n="",l="",this.rangeTime&&(n=this.datePipe.transform(this.rangeTime[0],"yyyy-MM-dd"),l=this.datePipe.transform(this.rangeTime[1],"yyyy-MM-dd")),this._loading=!0,t=this,[4,this.statisticService.count(n,l)];case 1:return t.dataList=_.sent().data||[],this._loading=!1,[2]}})})},n}(),p=_._4({encapsulation:2,styles:[],data:{}});function f(n){return _._30(0,[(n()(),_._6(0,0,null,null,10,"tr",[["nz-tbody-tr",""]],[[2,"ant-table-row",null]],null,null,null,null)),_._5(1,16384,null,0,a._85,[],null,null),(n()(),_._28(-1,null,["\n    "])),(n()(),_._6(3,0,null,null,2,"td",[["nz-td",""]],[[2,"ant-table-selection-column",null],[2,"ant-table-row-expand-icon-cell",null]],null,null,null,null)),_._5(4,16384,null,0,a._86,[_.l],null,null),(n()(),_._28(5,null,["",""])),(n()(),_._28(-1,null,["\n    "])),(n()(),_._6(7,0,null,null,2,"td",[["nz-td",""]],[[2,"ant-table-selection-column",null],[2,"ant-table-row-expand-icon-cell",null]],null,null,null,null)),_._5(8,16384,null,0,a._86,[_.l],null,null),(n()(),_._28(9,null,["",""])),(n()(),_._28(-1,null,["\n  "]))],null,function(n,l){n(l,0,0,!0),n(l,3,0,_._18(l,4).nzCheckbox,_._18(l,4).nzExpand),n(l,5,0,l.context.$implicit.productName),n(l,7,0,_._18(l,8).nzCheckbox,_._18(l,8).nzExpand),n(l,9,0,l.context.$implicit.num)})}function g(n){return _._30(0,[(n()(),_._6(0,0,null,null,42,"nz-table",[["nzBordered","true"],["nzIsPagination","true"],["nzShowTitle","true"],["nzSize","small"]],null,null,null,e.R,e.x)),_._5(1,4308992,[["nzTable",4]],2,a._75,[_.l,_.h],{nzSize:[0,"nzSize"],nzBordered:[1,"nzBordered"],nzIsPagination:[2,"nzIsPagination"],nzLoading:[3,"nzLoading"],nzShowTitle:[4,"nzShowTitle"],nzDataSource:[5,"nzDataSource"],nzPageSize:[6,"nzPageSize"]},null),_._26(335544320,1,{fixedHeader:0}),_._26(603979776,2,{setThs:1}),(n()(),_._28(-1,1,["\n  "])),(n()(),_._6(5,0,null,0,12,"div",[["class","text-right"],["nz-table-title",""]],null,null,null,null,null)),(n()(),_._28(-1,null,["\n    "])),(n()(),_._6(7,0,null,null,5,"nz-rangepicker",[["nzFormat","YYYY-MM-DD"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"ngModelChange"]],function(n,l,t){var _=!0;return"ngModelChange"===l&&(_=!1!==(n.component.rangeTime=t)&&_),_},e.L,e.r)),_._5(8,114688,null,0,a._48,[_.l,_.h,a._17],{nzFormat:[0,"nzFormat"]},null),_._24(1024,null,i.i,function(n){return[n]},[a._48]),_._5(10,671744,null,0,i.n,[[8,null],[8,null],[8,null],[2,i.i]],{model:[0,"model"]},{update:"ngModelChange"}),_._24(2048,null,i.j,null,[i.n]),_._5(12,16384,null,0,i.k,[i.j],null,null),(n()(),_._28(-1,null,["\n    "])),(n()(),_._6(14,0,null,null,2,"button",[["nz-button",""],["nzType","primary"],["type","button"]],null,[[null,"click"]],function(n,l,t){var u=!0,e=n.component;return"click"===l&&(u=!1!==_._18(n,15)._onClick()&&u),"click"===l&&(u=!1!==e.queryData()&&u),u},e.A,e.g)),_._5(15,1097728,null,0,a.w,[_.l,_.D],{nzType:[0,"nzType"]},null),(n()(),_._28(-1,0,["\u67e5\u8be2\n    "])),(n()(),_._28(-1,null,["\n  "])),(n()(),_._28(-1,1,["\n  "])),(n()(),_._6(19,0,null,1,15,"thead",[["nz-thead",""]],[[2,"ant-table-thead",null]],null,null,null,null)),_._5(20,16384,null,0,a._88,[],null,null),(n()(),_._28(-1,null,["\n  "])),(n()(),_._6(22,0,null,null,11,"tr",[],null,null,null,null,null)),(n()(),_._28(-1,null,["\n    "])),(n()(),_._6(24,0,null,null,3,"th",[["nz-th",""]],[[2,"ant-table-selection-column",null],[2,"ant-table-expand-icon-th",null]],null,null,null,null)),_._5(25,16384,[[2,4]],0,a._87,[_.l],null,null),(n()(),_._6(26,0,null,null,1,"span",[],null,null,null,null,null)),(n()(),_._28(-1,null,["\u5546\u54c1"])),(n()(),_._28(-1,null,["\n    "])),(n()(),_._6(29,0,null,null,3,"th",[["nz-th",""]],[[2,"ant-table-selection-column",null],[2,"ant-table-expand-icon-th",null]],null,null,null,null)),_._5(30,16384,[[2,4]],0,a._87,[_.l],null,null),(n()(),_._6(31,0,null,null,1,"span",[],null,null,null,null,null)),(n()(),_._28(-1,null,["\u9500\u91cf"])),(n()(),_._28(-1,null,["\n  "])),(n()(),_._28(-1,null,["\n  "])),(n()(),_._28(-1,1,["\n  "])),(n()(),_._6(36,0,null,1,5,"tbody",[["nz-tbody",""]],[[2,"ant-table-tbody",null]],null,null,null,null)),_._5(37,16384,null,0,a._84,[],null,null),(n()(),_._28(-1,null,["\n  "])),(n()(),_._1(16777216,null,null,1,null,f)),_._5(40,802816,null,0,o.l,[_.P,_.L,_.r],{ngForOf:[0,"ngForOf"]},null),(n()(),_._28(-1,null,["\n  "])),(n()(),_._28(-1,1,["\n"])),(n()(),_._28(-1,null,["\n"]))],function(n,l){var t=l.component;n(l,1,0,"small","true","true",t._loading,"true",t.dataList,10),n(l,8,0,"YYYY-MM-DD"),n(l,10,0,t.rangeTime),n(l,15,0,"primary"),n(l,40,0,_._18(l,1).data)},function(n,l){n(l,7,0,_._18(l,12).ngClassUntouched,_._18(l,12).ngClassTouched,_._18(l,12).ngClassPristine,_._18(l,12).ngClassDirty,_._18(l,12).ngClassValid,_._18(l,12).ngClassInvalid,_._18(l,12).ngClassPending),n(l,19,0,!0),n(l,24,0,_._18(l,25).nzCheckbox,_._18(l,25).nzExpand),n(l,29,0,_._18(l,30).nzCheckbox,_._18(l,30).nzExpand),n(l,36,0,!0)})}var z=_._2("app-statistic-query",h,function(n){return _._30(0,[(n()(),_._6(0,0,null,null,1,"app-statistic-query",[],null,null,null,g,p)),_._5(1,114688,null,0,h,[c],null,null)],function(n,l){n(l,1,0)},null)},{},{},[]),b=t("9Sd6"),y=t("XHgV"),m=t("1T37"),k=t("+j5Y"),x=t("6sdf"),v=t("ZS/n"),w=t("VoDg"),S=t("bfOx"),T=t("HeVE"),C=t("zfLu"),D=t("Fvxx"),M=t("Cz3F"),P=t("bkcK"),q=t("fAE3"),E=function(){};t.d(l,"StatisticManagerModuleNgFactory",function(){return F});var F=_._3(u,[],function(n){return _._14([_._15(512,_.k,_.Z,[[8,[e.b,e.c,e.a,e.d,e.e,e.f,e.U,z]],[3,_.k],_.w]),_._15(5120,a._114,a._115,[[3,a._114],a.b]),_._15(5120,a._17,a._104,[[3,a._17],a.a,a._114]),_._15(4608,o.o,o.n,[_.t,[2,o.w]]),_._15(4608,i.t,i.t,[]),_._15(6144,b.b,null,[o.e]),_._15(4608,b.c,b.c,[[2,b.b]]),_._15(4608,y.a,y.a,[]),_._15(5120,m.c,m.a,[[3,m.c],_.y,y.a]),_._15(5120,m.f,m.e,[[3,m.f],y.a,_.y]),_._15(4608,k.h,k.h,[m.c,m.f,_.y,o.e]),_._15(5120,k.d,k.i,[[3,k.d],o.e]),_._15(4608,k.g,k.g,[m.f,o.e]),_._15(5120,k.e,k.l,[[3,k.e],o.e]),_._15(4608,k.c,k.c,[k.h,k.d,_.k,k.g,k.e,_.g,_.q,_.y,o.e]),_._15(5120,k.j,k.k,[k.c]),_._15(4608,a._30,a._30,[]),_._15(4608,a._29,a._29,[_.g,_.k,a._17]),_._15(4608,a._124,a._124,[]),_._15(4608,x.b,x.b,[]),_._15(5120,_.d,function(n,l){return[a._120(n,l)]},[o.e,[2,a.e]]),_._15(5120,a._107,a._108,[o.e,[3,a._107]]),_._15(4608,i.e,i.e,[]),_._15(4608,r.l,r.r,[o.e,_.A,r.p]),_._15(4608,r.s,r.s,[r.l,r.q]),_._15(5120,r.a,function(n,l,t){return[n,new v.a(l,t),new w.a]},[r.s,a._29,S.k]),_._15(4608,r.o,r.o,[]),_._15(6144,r.m,null,[r.o]),_._15(4608,r.k,r.k,[r.m]),_._15(6144,r.b,null,[r.k]),_._15(4608,r.g,r.n,[r.b,_.q]),_._15(4608,r.c,r.c,[r.g]),_._15(4608,T.a,T.a,[r.c]),_._15(4608,C.a,C.a,[T.a,S.k]),_._15(4608,D.a,D.a,[]),_._15(4608,M.a,M.a,[]),_._15(4608,c,c,[r.c]),_._15(512,a._113,a._113,[]),_._15(512,a._16,a._16,[]),_._15(512,o.c,o.c,[]),_._15(512,a.y,a.y,[]),_._15(512,a.j,a.j,[]),_._15(512,a.s,a.s,[]),_._15(512,i.r,i.r,[]),_._15(512,i.h,i.h,[]),_._15(512,b.a,b.a,[]),_._15(512,P.c,P.c,[]),_._15(512,y.b,y.b,[]),_._15(512,m.b,m.b,[]),_._15(512,k.f,k.f,[]),_._15(512,a._56,a._56,[]),_._15(512,a._47,a._47,[]),_._15(512,a.A,a.A,[]),_._15(512,a._12,a._12,[]),_._15(512,a.G,a.G,[]),_._15(512,a.K,a.K,[]),_._15(512,a._102,a._102,[]),_._15(512,a._91,a._91,[]),_._15(512,a.T,a.T,[]),_._15(512,a._5,a._5,[]),_._15(512,a._13,a._13,[]),_._15(512,a._7,a._7,[]),_._15(512,a._25,a._25,[]),_._15(512,a._28,a._28,[]),_._15(512,a._33,a._33,[]),_._15(512,a._37,a._37,[]),_._15(512,a._39,a._39,[]),_._15(512,a._41,a._41,[]),_._15(512,a._50,a._50,[]),_._15(512,a._64,a._64,[]),_._15(512,a._96,a._96,[]),_._15(512,a._60,a._60,[]),_._15(512,a._70,a._70,[]),_._15(512,a._43,a._43,[]),_._15(512,a._78,a._78,[]),_._15(512,x.c,x.c,[]),_._15(512,a._80,a._80,[]),_._15(512,a._83,a._83,[]),_._15(512,a._67,a._67,[]),_._15(512,a._22,a._22,[]),_._15(512,a.X,a.X,[]),_._15(512,a.v,a.v,[]),_._15(512,a._15,a._15,[]),_._15(131584,a._51,a._51,[o.e,_.q,_.k]),_._15(512,a.E,a.E,[]),_._15(512,a.C,a.C,[]),_._15(512,a.O,a.O,[]),_._15(512,a._94,a._94,[]),_._15(512,a.q,a.q,[]),_._15(512,a.h,a.h,[]),_._15(512,a.m,a.m,[]),_._15(512,a.o,a.o,[]),_._15(512,a._99,a._99,[]),_._15(512,a._101,a._101,[]),_._15(512,a.f,a.f,[]),_._15(512,i.p,i.p,[]),_._15(512,r.e,r.e,[]),_._15(512,r.d,r.d,[]),_._15(512,q.a,q.a,[]),_._15(512,S.m,S.m,[[2,S.r],[2,S.k]]),_._15(512,E,E,[]),_._15(512,u,u,[]),_._15(256,a.b,!1,[]),_._15(256,a.a,a._103,[]),_._15(256,a._118,{nzDuration:1500,nzAnimate:!0,nzPauseOnHover:!0,nzMaxStack:7},[]),_._15(256,a._119,{nzTop:"24px",nzRight:"0px",nzDuration:4500,nzMaxStack:7,nzPauseOnHover:!0,nzAnimate:!0},[]),_._15(256,r.p,"XSRF-TOKEN",[]),_._15(256,r.q,"X-XSRF-TOKEN",[]),_._15(1024,S.i,function(){return[[{path:"",redirectTo:"list"},{path:"list",component:h}]]},[])])})}});
import {User} from "../shared/auth.service";

export interface MenuItem {
  name?: string;
  icon?: string;
  route?: string;
  selected?: boolean;
  open?: boolean;
  children?: MenuItem[];
}

export function makeMenu(currentUser: User): MenuItem[] {
  const menus: MenuItem[] = [
    {name: '欢迎', route: '/main/index', icon: 'home'},
    {
      name: '订单管理', icon: 'bars',
      children: [
        {name: '订单查询', route: '/main/order-manager/list'},
        {name: '历史订单', route: '/main/order-manager/history-list'},
      ]
    },
    {
      name: '商品管理', icon: 'appstore',
      children: [
        {name: '已上架商品查询', route: '/main/shop-manager/list'},
        {name: '已下架商品查询', route: '/main/shop-manager/offline-list'},
      ]
    },
    {name: '类目管理', route: '/main/category-manager/list', icon: 'fork'},
    {name: '活动管理', route: '/main/activity-manager/list', icon: 'fork'},
    {name: '广告管理', route: '/main/adv-product-manager/list', icon: 'fork'},
    {name: '统计管理', route: '/main/statistic-manager/list', icon: 'fork'},
  ];
  if (currentUser.admin) {
    menus.push({name: '用户管理', route: '/main/user-manager/list', icon: 'user'});
  }
  return menus;
}

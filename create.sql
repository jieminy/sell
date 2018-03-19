CREATE TABLE product_info (
	product_id VARCHAR (32) PRIMARY KEY ,
	product_name VARCHAR (64) NOT NULL COMMENT '商品名称',
	product_price DECIMAL (8, 2) NOT NULL COMMENT '单价',
	product_stock INT NOT NULL COMMENT '库存',
	product_description VARCHAR (64) COMMENT '描述',
	product_icon VARCHAR (512) COMMENT '小图',
	category_type INT NOT NULL COMMENT '类目编码',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT '商品表';


CREATE TABLE product_category (
	categoryt_id INT NOT NULL PRIMARY KEY auto_increment,
	category_name VARCHAR (64) NOT NULL COMMENT '类目名称',
	category_type INT NOT NULL UNIQUE COMMENT '类目编码',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT '类目表';

CREATE TABLE order_master (
	order_id VARCHAR (32) PRIMARY KEY,
	buyer_name VARCHAR (32) NOT NULL COMMENT '类目名称',
	buyer_phone VARCHAR (32) NOT NULL COMMENT '电话',
	buyer_address VARCHAR (128) NOT NULL COMMENT '地址',
	buyer_openid VARCHAR (64) NOT NULL COMMENT '微信openid',
	order_amount DECIMAL (8, 2) NOT NULL COMMENT '订单金额',
	order_status TINYINT (3) NOT NULL COMMENT '订单状态，默认0新下单',
	pay_status TINYINT (3) NOT NULL COMMENT '支付状态，默认0未支付',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	key idx_buyer_oper (buyer_openid)
) COMMENT '订单表';

CREATE TABLE order_detail (
	detail_id VARCHAR (32) NOT NULL,
	order_id VARCHAR (32) NOT NULL,
	product_id VARCHAR (32) NOT NULL,
	product_name VARCHAR (64) NOT NULL COMMENT '商品名称',
	product_price DECIMAL (8, 2) NOT NULL COMMENT '价格',
	product_quantity INT NOT NULL COMMENT '数量',
	product_icon VARCHAR (512) COMMENT '图标',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (detail_id),
	KEY idx_order_id (order_id)
) COMMENT '订单表';
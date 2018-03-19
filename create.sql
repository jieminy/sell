CREATE TABLE product_info (
	product_id VARCHAR (32) PRIMARY KEY ,
	product_name VARCHAR (64) NOT NULL COMMENT '��Ʒ����',
	product_price DECIMAL (8, 2) NOT NULL COMMENT '����',
	product_stock INT NOT NULL COMMENT '���',
	product_description VARCHAR (64) COMMENT '����',
	product_icon VARCHAR (512) COMMENT 'Сͼ',
	category_type INT NOT NULL COMMENT '��Ŀ����',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '�޸�ʱ��'
) COMMENT '��Ʒ��';


CREATE TABLE product_category (
	categoryt_id INT NOT NULL PRIMARY KEY auto_increment,
	category_name VARCHAR (64) NOT NULL COMMENT '��Ŀ����',
	category_type INT NOT NULL UNIQUE COMMENT '��Ŀ����',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '�޸�ʱ��'
) COMMENT '��Ŀ��';

CREATE TABLE order_master (
	order_id VARCHAR (32) PRIMARY KEY,
	buyer_name VARCHAR (32) NOT NULL COMMENT '��Ŀ����',
	buyer_phone VARCHAR (32) NOT NULL COMMENT '�绰',
	buyer_address VARCHAR (128) NOT NULL COMMENT '��ַ',
	buyer_openid VARCHAR (64) NOT NULL COMMENT '΢��openid',
	order_amount DECIMAL (8, 2) NOT NULL COMMENT '�������',
	order_status TINYINT (3) NOT NULL COMMENT '����״̬��Ĭ��0���µ�',
	pay_status TINYINT (3) NOT NULL COMMENT '֧��״̬��Ĭ��0δ֧��',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '�޸�ʱ��',
	key idx_buyer_oper (buyer_openid)
) COMMENT '������';

CREATE TABLE order_detail (
	detail_id VARCHAR (32) NOT NULL,
	order_id VARCHAR (32) NOT NULL,
	product_id VARCHAR (32) NOT NULL,
	product_name VARCHAR (64) NOT NULL COMMENT '��Ʒ����',
	product_price DECIMAL (8, 2) NOT NULL COMMENT '�۸�',
	product_quantity INT NOT NULL COMMENT '����',
	product_icon VARCHAR (512) COMMENT 'ͼ��',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '�޸�ʱ��',
	PRIMARY KEY (detail_id),
	KEY idx_order_id (order_id)
) COMMENT '������';
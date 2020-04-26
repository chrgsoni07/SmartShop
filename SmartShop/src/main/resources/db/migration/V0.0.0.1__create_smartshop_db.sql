create database if not exists smart_shop;

use smart_shop;

CREATE TABLE smart_shop.category (
  id bigint auto_increment primary key,
  name varchar(255) NOT NULL,
  details text NOT NULL
);

CREATE TABLE smart_shop.contact (
  id bigint auto_increment primary key,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  message varchar(255) NOT NULL,
  created_at    timestamp    not null
) ;

CREATE TABLE smart_shop.user (
  id  bigint auto_increment primary key,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  address varchar(255) NOT NULL,
  phone varchar(255) NOT NULL,
  type varchar(255) NOT NULL,
  created_at    timestamp    not null
);

CREATE TABLE smart_shop.order (
  id bigint auto_increment primary key,
  user_id bigint NOT NULL,
  shipping_address varchar(255) DEFAULT NULL,
  shipping_date timestamp    not null,
  shipping_status varchar(255) DEFAULT NULL,
	created_at     timestamp    not null,

	constraint order_user_id_fk
  foreign key (user_id) references smart_shop.user (id)
);

CREATE TABLE smart_shop.product (
  id  bigint auto_increment primary key,
  name varchar(255) NOT NULL,
  description text NOT NULL,
  image varchar(255) NOT NULL,
  category_id bigint NOT NULL,
  quantity varchar(255) NOT NULL,
  brand varchar(255) DEFAULT NULL,
  model varchar(255) DEFAULT NULL,
  configuration varchar(255) DEFAULT NULL,
  price varchar(255) NOT NULL,
  featured varchar(255) NOT NULL,
  created_at    timestamp    not null,

  constraint product_category_id_fk
  foreign key (category_id) references smart_shop.category (id)
);

CREATE TABLE smart_shop.order_detail (
  id bigint auto_increment primary key,
  order_id bigint NOT NULL,
  product_id bigint NOT NULL,
  quantity int(11) NOT NULL,

   constraint order_detail_order_id_fk
   foreign key (order_id) references smart_shop.order (id),

   constraint order_detail_product_id_fk
   foreign key (product_id) references smart_shop.product (id)
);

CREATE TABLE smart_shop.sale (
  id  bigint auto_increment primary key,
  order_id bigint NOT NULL,
  sales_amount FLOAT not null,
  created_at    timestamp    not null,

  constraint sale_order_id_fk
  foreign key (order_id) references smart_shop.order (id)
);

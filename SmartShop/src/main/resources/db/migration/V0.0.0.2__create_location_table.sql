use smart_shop;

CREATE TABLE smart_shop.location (
  id bigint auto_increment primary key,
  name varchar(255) NOT NULL
);

CREATE TABLE smart_shop.product_location (
  id bigint auto_increment primary key,
  product_id bigint not null,
  location_id bigint not null,

  constraint product_location_prod_id_fk
   foreign key (product_id) references smart_shop.product (id),

  constraint product_location_local_id_fk
  foreign key (location_id) references smart_shop.location (id)
);


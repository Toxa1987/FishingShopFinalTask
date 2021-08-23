
create table roles
(
	id int auto_increment
		primary key,
	role varchar(45) not null
)
;

create table status
(
	id int auto_increment
		primary key,
	status varchar(45) not null
)

;

create table users
(
	id int auto_increment
		primary key,
	login varchar(45) charset utf8mb4 not null,
	email varchar(45) charset utf8mb4 not null,
	password varchar(45) charset utf8mb4 null,
	name varchar(45) charset utf8mb4 null,
	last_name varchar(45) charset utf8mb4 null,
	phone varchar(45) charset utf8mb4 null,
	role_id int not null,
	status_id int not null,
	constraint login_UNIQUE
		unique (login),
	constraint users_ibfk_1
		foreign key (role_id) references roles (id),
	constraint users_ibfk_2
		foreign key (status_id) references status (id)
)
;

create index roleId
	on users (role_id)
;

create index statusId
	on users (status_id)
;


create table order_status
(
	id int auto_increment
		primary key,
	status varchar(45) null
)
;

create table orders
(
	id int auto_increment
		primary key,
	`order` varchar(45) charset utf8mb4 not null,
	date date not null,
	order_status_id int not null,
	summary_price decimal not null,
	user_id int null,
	constraint order_UNIQUE
		unique (`order`),
	constraint orders_ibfk_1
		foreign key (order_status_id) references order_status (id),
	constraint orders_ibfk_2
		foreign key (user_id) references users (id)
)
;
create index order_status_id
	on orders (order_status_id)
;

create index orders_ibfk_2
	on orders (user_id)
;

create table product_manufacture
(
	id int auto_increment
		primary key,
	manufacture varchar(60) not null,
	constraint manufacture_UNIQUE
		unique (manufacture)
)
;

create table product_types
(
	id int auto_increment
		primary key,
	type varchar(50) not null,
	discount int default '0' null,
	constraint product_types_type_uindex
		unique (type)
)
;

create table products
(
	id int auto_increment
		primary key,
	vendor varchar(35) not null,
	name varchar(50) null,
	manufacture_id int not null,
	type_id int not null,
	description tinytext not null,
	image blob null,
	price decimal null,
	number_in_stock int null,
	constraint vendor_UNIQUE
		unique (vendor),
	constraint products_ibfk_2
		foreign key (manufacture_id) references product_manufacture (id),
	constraint products_ibfk_1
		foreign key (type_id) references product_types (id)
)
;

create index manufactureId
	on products (manufacture_id)
;

create index typeId
	on products (type_id)
;

create table purchases
(
	id int auto_increment
		primary key,
	order_id int not null,
	product_id int not null,
	constraint purchases_ibfk_3
		foreign key (order_id) references orders (id),
	constraint purchases_ibfk_2
		foreign key (product_id) references products (id)
)
;

create index order_id
	on purchases (order_id)
;

create index productId
	on purchases (product_id)
;

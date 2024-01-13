CREATE TABLE product (
    id bigInt not null auto_increment,
    name varchar(100) not null,
    price double(19,3) not null,
    description varchar(200) not null,
    sector int not null,
    stock int not null,
    created_at varchar(30) not null,
    primary key (id)
);


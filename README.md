## Persistence and Analytics Fundamentals - Insert Order Application

### Workflow/Thought Process
1. Create tables and set PK FK
2. Set up environment i.e pom.xml, application.properties, dockerfile, schema.sql and test compile
3. Repository, Model, Service, Exception, Controller, HTML.

### mySQL Query
drop database if exists rsvpdb2;

create database rsvpdb2;

use rsvpdb2;

create table purchase_order (
    order_id char(8) not null,
    name varchar(128) not null,
    order_date date not null,

    primary key(order_id)
);

create table line_item (
    item_id int auto_increment not null,
    description text not null,
    quantity int default '1',
    order_id char(8) not null,

    primary key(item_id),
    constraint fk_order_id
        foreign key(order_id) references purchase_order(order_id)
);

### Functionalities
1. Able to insert new records and store values in rsvpdb2 when queried.
2. Exception handling using OrderException
3. Can't add more than 5 items in one transaction.


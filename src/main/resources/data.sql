drop table if exists user;

create table user (
    id varchar(60) primary key,
    first_name varchar(100) not null,
    middle_name varchar(100) null,
    last_name varchar(100) not null,
    phone_number varchar(10) not null,
    updated timestamp not null default current_timestamp()
);

insert into user (id, first_name, middle_name, last_name, phone_number) values
 ('097e1c2e-8ba3-463e-96d2-7a08f32944a6','Justin','Marquis','Wheeler','123456789'),
 ('b1f6c877-c4a2-4e00-a986-2cbed56e257f','James',null,'Wagon','1112223333'),
 ('d7d77a1b-d0e4-49bb-804d-895db58027cb','John','Smith','Doe','9887654321'),
 ('5f99aae9-7426-4e5b-932a-4f7066785e63','Jack',null,'Pool','1231231234'),
 ('d54cbbb5-96f7-496e-8142-ce4966061940','Joan','Marie','River','9879879876');

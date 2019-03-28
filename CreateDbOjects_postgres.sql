
CREATE TABLE spend
( op_date DATE DEFAULT CURRENT_DATE NOT NULL
, income                        FLOAT          DEFAULT 0
, consumption                   FLOAT          DEFAULT 0
, description                   VARCHAR(255)  DEFAULT NULL
, balance                       FLOAT
, id                            SERIAL         NOT NULL
, category_id		  	INTEGER	       default 0
, user_id 		 	INTEGER  default 0 NOT NULL
);

alter table spend add column username varchar(255) default 'Не задан';

insert into test (balance, income) values (500, 500);

create table category
(id                            SERIAL         NOT NULL
,name                          VARCHAR(255)  DEFAULT NULL
);


insert into category (id, name) values (0, 'Любая');
insert into category (id, name) values (1, 'Еда');
insert into category (id, name) values (2, 'Кафе и ресторан');
insert into category (id, name) values (3, 'Развлечения');
insert into category (id, name) values (4, 'Одежда');
insert into category (id, name) values (5, 'Покупки');
insert into category (id, name) values (6, 'Отпуск');
insert into category (id, name) values (7, 'Спорт');
insert into category (id, name) values (8, 'Путешествия');
insert into category (id, name) values (9, 'Подарки');
insert into category (id, name) values (10, 'Основное');
insert into category (id, name) values (11, 'Дети');


create table users (username VARCHAR(255)
, id SERIAL );

create UNIQUE index username_idx on users(username);



C:\Users\yuriy>tnsping xe

TNS Ping Utility for 64-bit Windows: Version 11.2.0.2.0 - Production on 27-=+--2
016 13:12:58

Copyright (c) 1997, 2014, Oracle.  All rights reserved.

Used parameter files:
C:\oraclexe\app\oracle\product\11.2.0\server\network\admin\sqlnet.ora


Used TNSNAMES adapter to resolve the alias
Attempting to contact (DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = montreal)(PORT = 1521))
(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = XE)))
OK (0 msec)

C:\Users\yuriy>sqlplus sys/oracle@xe as sysdba

SQL> select tablespace_name from dba_tablespaces;

TABLESPACE_NAME
------------------------------
SYSTEM
SYSAUX
UNDOTBS1
TEMP
USERS


SQL> CREATE TABLESPACE XE_TS datafile 'C:\oraclexe\app\oracle\oradata\XE\xe_ts_1.dbf' SIZE 256M REUSE AUTOEXTEND ON NEXT 128M MAXSIZE 1024M LOGGING EXTENT MANAGEMENT LOCAL SEGMENT SPACE MANAGEMENT AUTO;

Tablespace created.

SQL> CREATE TEMPORARY TABLESPACE XE_TEMP_1 TEMPFILE 'C:\oraclexe\app\oracle\orad
ata\XE\xe_temp_1.dbf' SIZE 64M REUSE;

Tablespace created.

SQL> select tablespace_name from dba_tablespaces;

TABLESPACE_NAME
------------------------------
SYSTEM
SYSAUX
UNDOTBS1
TEMP
USERS
XE_TS
XE_TEMP_1

7 rows selected.

SQL> create user TEST identified by 123 DEFAULT TABLESPACE XE_TS TEMPORARY TABLESPACE XE_TEMP_1 QUOTA UNLIMITED ON XE_TS ACCOUNT UNLOCK;

User created.

SQL> GRANT "AQ_ADMINISTRATOR_ROLE" TO TEST;

Grant succeeded.

SQL> GRANT "AQ_USER_ROLE" TO TEST;

Grant succeeded.

SQL> GRANT "CONNECT" TO TEST;

Grant succeeded.

SQL> GRANT CREATE ANY VIEW TO TEST;

Grant succeeded.

SQL> GRANT DROP ANY VIEW TO TEST;

Grant succeeded.

SQL> GRANT "RESOURCE" TO TEST;

Grant succeeded.

SQL> COMMIT;

Commit complete.

SQL> exit;
Disconnected from Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit
 Production

C:\Users\yuriy>
C:\Users\yuriy>
C:\Users\yuriy>^Rsqll
C:\Users\yuriy>sqlplus

SQL*Plus: Release 11.2.0.2.0 Production on Tё =ю  27 13:40:41 2016

Copyright (c) 1982, 2014, Oracle.  All rights reserved.

Enter user-name: test
Enter password:

Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production

CREATE TABLE spend
( op_date                          DATE           DEFAULT SYSDATE NOT NULL
, income                        FLOAT          DEFAULT 0
, consumption                   FLOAT          DEFAULT 0
, description                   VARCHAR2(255)  DEFAULT NULL
, balance                       FLOAT
, id                            NUMBER         NOT NULL
) TABLESPACE xe_ts
/


CREATE SEQUENCE  seq_spend_id START WITH 1 increment BY 1 MAXVALUE 2147483647 CYCLE NOCACHE ORDER;
select seq_spend_id.nextval from dual;

CREATE OR REPLACE TRIGGER test.tseq_spend
BEFORE INSERT  ON test.SPEND
REFERENCING NEW AS NEWEST OLD AS OLD
FOR EACH ROW
WHEN (newest.id is NULL)
begin
	select seq_spend_id.nextval into :newest.id from dual;
end tseq_spend;

/

alter table spend add (category_id NUMBER default 0 NOT NULL);

create table category
(id                            NUMBER         NOT NULL
,name                          VARCHAR2(255)  DEFAULT NULL
)
/


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

create table users (username VARCHAR 255, id NUMBER default NULL);

create UNIQUE index username_idx on users(username);

CREATE SEQUENCE  "TEST"."SEQ_USER_ID"  MINVALUE 1 MAXVALUE 2147483647 INCREMENT BY 1 START WITH 20 NOCACHE  ORDER  CYCLE ;

create or replace TRIGGER test.tseq_users
BEFORE INSERT  ON test.USERS
REFERENCING NEW AS NEWEST OLD AS OLD
FOR EACH ROW
WHEN (newest.id is NULL)
begin
	select seq_user_id.nextval into :newest.id from dual;
end tseq_users;
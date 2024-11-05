
-- 오라클 

CREATE TABLE memberTest
(	
	id			varchar2(20) primary key,
	password	varchar2(20) not null,
	name		varchar2(10) not null,
	tel			varchar2(20),
	addr		varchar2(400)
);

-- Mysql / mariadb

CREATE TABLE memberTest
(	
	id			varchar(20) primary key,
	password	varchar(20) not null,
	name		varchar(10) not null,
	tel			varchar(20),
	addr		varchar(400)
);
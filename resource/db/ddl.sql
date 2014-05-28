DROP TABLE t_user;
DROP TABLE t_log;

CREATE TABLE t_user(
	id VARCHAR(50) PRIMARY KEY NOT NULL, 
	password VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE, 
	nickname VARCHAR(50) NOT NULL UNIQUE, 
	birthdate VARCHAR(10), 
	sex CHAR(1), 
	mobile CHAR(11), 
	location VARCHAR(50),
	company VARCHAR(100),
	job VARCHAR(50),
	reputation INT,
	intro VARCHAR(300), 
	register_time TIMESTAMP, 
	last_signin_time TIMESTAMP,
	activated CHAR(1) NOT NULL DEFAULT '1',
	valid char(1) NOT NULL DEFAULT '1');
--0 indicates true while 1 stands for false
	
CREATE TABLE t_log(
	
	id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	user_id VARCHAR(50) NOT NULL,
	opreate_time TIMESTAMP,
	ip_addr VARCHAR(50) NOT NULL,
	host_name VARCHAR(50),
	operate_des VARCHAR(100),
	result CHAR(1) NOT NULL DEFAULT '1',
	method VARCHAR(50));
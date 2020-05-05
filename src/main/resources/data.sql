drop table IF EXISTS user;

create TABLE user(
	  id INT PRIMARY KEY Auto_Increment,
	  name VARCHAR(100) NOT NULL,
    account_id VARCHAR(100) NOT NULL,
    token CHAR(36) NOT NULL,
    gmt_create BIGINT NOT NULL,
    gmt_modified BIGINT NOT NULL
    );
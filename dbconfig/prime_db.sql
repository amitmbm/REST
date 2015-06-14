drop DATABASE IF EXISTS `prime_db`;
CREATE DATABASE IF NOT EXISTS `prime_db`;
use `prime_db`;

CREATE table item (
  item_id varchar(36) primary key not null,
  item_name varchar(128) unique key not null,
  item_desc varchar(512) not null,
  created_by varchar(128),
  updated_by varchar(128),
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE =InnoDB DEFAULT CHARSET=utf8;
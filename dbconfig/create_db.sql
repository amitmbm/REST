drop DATABASE IF EXISTS `rest`;
CREATE DATABASE IF NOT EXISTS `rest`;
use `rest`;

CREATE table product (
  id varchar(36) primary key not null,
  name varchar(128) unique key not null,
  description varchar(512) not null,
  created_by varchar(128),
  updated_by varchar(128),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE =InnoDB DEFAULT CHARSET=utf8;

DROP table if exists department
DROP table if exists employee

CREATE table department(
                           id int(11) not null auto_increment COMMENT '部门id',
                           department_name VARCHAR(50) not null COMMENT '部门名',
                           PRIMARY key (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '部门表'

CREATE table employee(
                         id int(11) not null auto_increment comment '员工id',
                         last_name VARCHAR(50) NOT NULL COMMENT '员工姓名',
                         email VARCHAR(50) not null COMMENT '员工邮箱',
                         gender int(1) NOT NULL COMMENT '性别',
                         department_id int(11) NOT NULL COMMENT '部门id',
                         birth DATETIME DEFAULT NULL COMMENT '生日',
                         PRIMARY KEY (id),
                         KEY fk_department_id (department_id),
                         CONSTRAINT fk_department_id
                             FOREIGN KEY (department_id)
                                 REFERENCES department (id)
)ENGINE=innodb DEFAULT CHARSET=utf8 COMMENT '员工表'

insert into
department(id,department_name)
VALUES
(101,'教学部'),
(102,'市场部'),
(103,'教研部'),
(104,'运营部'),
(105,'后勤部')

INSERT INTO
employee
(id,last_name,email,gender,department_id,birth)
VALUES
(1001,'AA','A2473@qq.com',0,101,'2026-08-12 12:22:22'),
(1002,'BB','A2473@qq.com',1,102,'2026-08-13 16:21:21'),
(1003,'DD','A2473@qq.com',0,103,'2026-08-14 17:22:22'),
(1004,'CC','A2473@qq.com',1,104,'2026-08-15 18:23:23'),
(1005,'EE','A2473@qq.com',0,105,'2026-08-16 19:24:24')

SELECT * FROM employee WHERE id = 1010;
explain SELECT id, birth FROM employee WHERE id = 1010;
create table user(
    id int(11) not null auto_increment comment '用户id',
    name varchar(50) not null comment '用户姓名',
    password varchar(50) not null comment '用户密码',
        PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 comment '用户表';

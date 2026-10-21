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
    employee_id int(11) null comment '关联的员工id，可为空（该账号不是员工）。移动端靠它拿 employeeId',
        PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 comment '用户表';

-- 建完库记得给账号绑定员工，否则移动端登录后拿不到 employeeId
-- update user set employee_id = 1 where name = 'admin';

CREATE TABLE attendance(
    id int(11) not null auto_increment comment '考勤id',
    employee_id int(11) not null comment '员工id',
    work_date date not null comment '工作日期',
    check_in_time datetime default null comment '上班打卡时间',
    check_out_time datetime default null comment '下班打卡时间',
    status int(1) not null default 0 comment '考勤状态（0=正常，1=迟到，2=早退，3=迟到+早退）',
    PRIMARY KEY (id),
    KEY fk_attendance_employee (employee_id),
    CONSTRAINT fk_attendance_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 comment '考勤表';

CREATE TABLE `leave`(
    id int(11) not null auto_increment comment '请假id',
    employee_id int(11) not null comment '员工id',
    leave_type int(1) not null comment '请假类型（1=事假，2=病假，3=年假）',
    start_date date not null comment '开始日期',
    end_date date not null comment '结束日期',
    reason varchar(255) default null comment '请假原因',
    status int(1) not null default 0 comment '审批状态（0=待审批，1=已通过，2=已驳回）',
    apply_time datetime not null comment '申请时间',
    approve_time datetime default null comment '审批时间',
    PRIMARY KEY (id),
    KEY fk_leave_employee (employee_id),
    CONSTRAINT fk_leave_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 comment '请假表';

CREATE TABLE salary(
    id int(11) not null auto_increment comment '工资id',
    employee_id int(11) not null comment '员工id',
    salary_month varchar(7) not null comment '工资月份（如2026-10）',
    base_salary decimal(10,2) not null default 0 comment '基本工资',
    bonus decimal(10,2) default 0 comment '奖金',
    deduction decimal(10,2) default 0 comment '扣款',
    real_salary decimal(10,2) not null default 0 comment '实发工资',
    PRIMARY KEY (id),
    KEY fk_salary_employee (employee_id),
    UNIQUE KEY uk_employee_month (employee_id, salary_month),
    CONSTRAINT fk_salary_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 comment '工资表';

-- 工资测试数据
INSERT INTO salary (employee_id, salary_month, base_salary, bonus, deduction, real_salary)
VALUES 
(1001, '2026-09', 8000, 1000, 500, 8500),
(1001, '2026-10', 8000, 1200, 300, 8900),
(1002, '2026-09', 7000, 800, 400, 7400);

-- 请假测试数据
INSERT INTO `leave` (employee_id, leave_type, start_date, end_date, reason, status)
VALUES 
(1001, 1, '2026-10-01', '2026-10-02', '家里有事', 1),
(1002, 2, '2026-09-20', '2026-09-21', '感冒发烧', 0);

-- 考勤测试数据
INSERT INTO attendance (employee_id, work_date, check_in_time, check_out_time, status)
VALUES 
(1001, '2026-10-03', '2026-10-03 08:55:00', '2026-10-03 18:10:00', 0),
(1002, '2026-10-03', '2026-10-03 09:15:00', '2026-10-03 17:50:00', 1),
(1003, '2026-10-03', '2026-10-03 08:45:00', '2026-10-03 18:05:00', 0);

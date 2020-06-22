DROP DATABASE if exists gymmanager;
CREATE DATABASE gymmanager DEFAULT CHARACTER SET utf8mb4;

USE gymmanager;

#用户基础信息
CREATE TABLE gymmanager.user_basic
(
    user_id VARCHAR(32) NOT NULL COMMENT '用户主键id',
    username VARCHAR(32) NOT NULL UNIQUE COMMENT '用户名/手机号',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    PRIMARY KEY(user_id),
    INDEX username_idx(username)
) ENGINE = InnoDB;

#管理员账号
#username: admin
#password: admin
INSERT INTO user_basic VALUES("1","admin","$2a$10$kd9hAAZavkJ9Lj0X5sb9C.mN7zmopjODKtt1q1ngiWBiQba4BVPmq");

CREATE TABLE gymmanager.vip_info(
    vip_id VARCHAR(32) NOT NULL COMMENT '会员id',
    vip_id_card VARCHAR(32) NOT NULL COMMENT '身份证号',
    vip_name VARCHAR(50) NOT NULL COMMENT '名字',
    vip_phone_number VARCHAR(32) NOT NULL COMMENT '手机号码',
    vip_birthday DATE NOT NULL COMMENT '生日',
    PRIMARY KEY(vip_id)
) ENGINE = InnoDB;

CREATE TABLE gymmanager.vip_card(
    vip_id VARCHAR(32) NOT NULL COMMENT '会员主键',
    valid_Time INT NOT NULL COMMENT '有效次数',
    card_Status BOOL NOT NULL DEFAULT(FALSE) COMMENT '卡状态',
    class_Status BOOL NOT NULL DEFAULT(FALSE) COMMENT '上课状态',
    last_on_class_id VARCHAR(32) COMMENT '最后的上课id',
    PRIMARY KEY(vip_id)
) ENGINE = InnoDB;

CREATE TABLE gymmanager.course_info(
    course_id VARCHAR(32) NOT NULL COMMENT '课程id',
    coach_id VARCHAR(32) NOT NULL COMMENT '教练id',
    course_name VARCHAR(20) NOT NULL COMMENT '课程名称',
    course_time INT NOT NULL COMMENT '开课次数',
    max_number INT NOT NULL COMMENT '最大选课人数',
    student_num INT NOT NULL DEFAULT(0) COMMENT '当前选课人数',
    classroom VARCHAR(20) NOT NULL COMMENT '教室',
    PRIMARY KEY(course_id)
) ENGINE = InnoDB;

CREATE TABLE gymmanager.on_lesson(
    record_id VARCHAR(32) NOT NULL COMMENT '上课记录id',
    course_id VARCHAR(32) NOT NULL COMMENT '课程id',
    classroom VARCHAR(20) NOT NULL COMMENT '教室',
    vip_id VARCHAR(32) NOT NULL COMMENT	'会员id',
    start_time DATETIME COMMENT '开始时间',
    leave_time DATETIME COMMENT '结束时间',
    PRIMARY KEY(record_id),
    FOREIGN KEY(course_id) REFERENCES course_info(course_id),
    FOREIGN KEY(vip_id) REFERENCES vip_info(vip_id)
)ENGINE = InnoDB;


CREATE TABLE gymmanager.course_cancel(
    course_id VARCHAR(32) NOT NULL COMMENT '课程id',
    coach_id VARCHAR(32) NOT NULL COMMENT '教练id',
    course_name VARCHAR(20) NOT NULL COMMENT '课程名称',
    course_time INT NOT NULL COMMENT '开课次数',
    max_Number INT NOT NULL COMMENT '最大选课人数',
    student_num INT NOT NULL DEFAULT(0) COMMENT '当前选课人数',
    classroom VARCHAR(20) NOT NULL COMMENT '教室',
    PRIMARY KEY(course_id)
) ENGINE = InnoDB;

CREATE TABLE gymmanager.course_time(
    course_id VARCHAR(32) NOT NULL COMMENT '课程id',
    day VARCHAR(20) NOT NULL COMMENT '星期几',
    time_slot TIME NOT NULL COMMENT '时间',
    PRIMARY KEY(course_id,day,time_slot),
    FOREIGN KEY(course_id) REFERENCES course_info(course_id)
)	ENGINE = InnoDB;

CREATE TABLE gymmanager.takes(
    course_id VARCHAR(32) NOT NULL COMMENT '课程id',
    vip_id VARCHAR(32) NOT NULL COMMENT '会员id',
    PRIMARY KEY(course_id,vip_id),
    FOREIGN KEY(course_id) REFERENCES course_info(course_id),
    FOREIGN KEY(vip_id) REFERENCES vip_info(vip_id)
) ENGINE = InnoDB;

CREATE TABLE gymmanager.coach_info(
    coach_id VARCHAR(32) NOT NULL COMMENT '教练id',
    coach_id_card VARCHAR(32) NOT NULL COMMENT '身份证',
    coach_name VARCHAR(50) NOT NULL COMMENT '教练名称',
    coach_phone_number VARCHAR(32) NOT NULL COMMENT '电话号码',
    coach_birth DATE NOT NULL COMMENT '教练生日',
    coach_sex VARCHAR(10) NOT NULL COMMENT '教练性别',
    coach_sport_event VARCHAR(100) COMMENT '教练擅长项目',
    PRIMARY KEY(coach_id)
) ENGINE = InnoDB;

CREATE TABLE gymmanager.coach_cancel(
    coach_id VARCHAR(32) NOT NULL COMMENT '教练id',
    coach_id_card VARCHAR(32) NOT NULL COMMENT '身份证',
    coach_name VARCHAR(50) NOT NULL COMMENT '教练名称',
    coach_phone_number VARCHAR(32) NOT NULL COMMENT '电话号码',
    coach_birth DATE NOT NULL COMMENT '教练生日',
    coach_sex VARCHAR(10) NOT NULL COMMENT '教练性别',
    coach_sport_event VARCHAR(100) COMMENT '教练擅长项目',
    PRIMARY KEY(coach_id)
) ENGINE = InnoDB;


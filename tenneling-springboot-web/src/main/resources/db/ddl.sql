--创建数据库
create DATABASE tenneling DEFAULT CHARACTER SET utf8;
--创建用户
create USER 'springuser'@'localhost' IDENTIFIED BY 'springuser';
--授权用户数据库权限
GRANT SELECT, INSERT, UPDATE, REFERENCES, DELETE, CREATE,
DROP, ALTER, INDEX, CREATE VIEW, SHOW VIEW
ON `tenneling`.* to 'springuser'@'%' ;
--创建微信用户表
CREATE TABLE tenneling.`wx_user` (
        id INT NOT NULL AUTO_INCREMENT COMMENT '流水ID',
        phoneNumber varchar(128) COMMENT '手机号码',
        openId varchar(128) ,
        nickName varchar(128) ,
        gender varchar(128) COMMENT '性别',
        country varchar(128) COMMENT '国别',
        city varchar(128) COMMENT '城市',
        province varchar(128) COMMENT '省份',
        avatarUrl varchar(1000) ,
        unionId varchar(128) ,
        watermark varchar(128) ,
        rawData varchar(128) ,
        cloudId varchar(128) ,
        birthday date COMMENT '生日',
        email varchar(128) COMMENT '邮箱',
        create_user varchar(50) COMMENT '创建人',
	    create_time varchar(50) COMMENT '创建时间',
        update_user varchar(50) COMMENT '更新人',
	    update_time varchar(50) COMMENT '更新时间',
        PRIMARY KEY (id)
);

CREATE TABLE tenneling.`sys_para` (
    id INT NOT NULL AUTO_INCREMENT COMMENT '流水ID',
    PARA_KEY varchar(128) COMMENT 'key',
    PARA_VALUE	varchar(500) COMMENT 'value',
    DESCRIPTION	varchar(128) COMMENT '描述',
    Status date COMMENT '状态',
    create_user varchar(50) COMMENT '创建人',
    create_time varchar(50) COMMENT '创建时间',
    update_user varchar(50) COMMENT '更新人',
    update_time varchar(50) COMMENT '更新时间',
    PRIMARY KEY (id)
);



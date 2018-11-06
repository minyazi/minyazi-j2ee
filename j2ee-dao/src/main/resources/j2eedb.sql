##删除数据库
drop database if exists j2eedb;

##创建数据库
create database j2eedb default character set utf8 collate utf8_bin;

set names utf8;
use j2eedb;

##序列表
drop table if exists Sequence;
create table Sequence (
    code                          char(2)             not null                      comment '序列号',
    name                          varchar(50)         not null                      comment '序列名',
    value                         int                 not null default 1            comment '序列值',
    increment                     int                 not null default 1            comment '递增步长',
    primary key (name)
) comment='序列表';
insert into Sequence values ("##","系统唯一序号",1,1);

##系统升级信息表
drop table if exists UpgradeInfo;
create table UpgradeInfo (
    sequence                      int                 not null auto_increment       comment '序号',
    systemVersion                 char(7)             not null                      comment '系统版本号',
    upgradeDate                   char(10)            not null                      comment '升级日期',
    operator                      varchar(50)         not null                      comment '操作员',
    primary key (sequence)
) comment='系统升级信息表';
insert into UpgradeInfo values (1,"v1.0.00","2018-01-01","minyazi");

##节假日信息表
drop table if exists HolidayInfo;
create table HolidayInfo (
    date                          char(10)            not null                      comment '日期',
    holidayFlag                   char(4)             not null                      comment '节假日标志（HF00：节假日，HF01：非节假日）',
    primary key (date)
) comment='节假日信息表';

##系统参数表
drop table if exists SystemParam;
create table SystemParam (
    paramCode                     varchar(50)         not null                      comment '参数代码',
    paramName                     varchar(50)         not null                      comment '参数名称',
    paramValue                    varchar(100)        not null                      comment '参数值',
    primary key (paramCode)
) comment='系统参数表';
insert into SystemParam values ("PageSize"      ,"每页记录数","10");
insert into SystemParam values ("ThreadPoolSize","线程池大小","5" );

##处理信息表
drop table if exists ProcessInfo;
create table ProcessInfo (
    processCode                   varchar(10)         not null                      comment '处理码',
    processMesg                   varchar(100)        not null                      comment '处理信息',
    primary key (processCode)
) comment='处理信息表';
insert into ProcessInfo values ("PC000000","处理成功");
insert into ProcessInfo values ("PC999999","处理失败");

##业务检查配置表
drop table if exists BusinessCheckConfig;
create table BusinessCheckConfig (
    busiType                      varchar(20)         not null                      comment '业务类型',
    busiName                      varchar(50)         not null                      comment '业务名称',
    bcrId                         char(7)             not null                      comment '规则ID，如：BCR0001',
    bccIndex                      int                 not null                      comment '检查索引',
    bccValidity                   char(1)             not null                      comment '配置有效性（0：无效，1：有效）',
    primary key (busiType,bcrId)
) comment='业务检查配置表';

##业务检查规则表
drop table if exists BusinessCheckRule;
create table BusinessCheckRule (
    bcrId                         char(7)             not null                      comment '规则ID，如：BCR0001',
    description                   varchar(100)        not null                      comment '规则描述',
    rule                          varchar(500)        not null                      comment '规则',
    referValue                    varchar(20)         not null                      comment '参照值',
    referType                     varchar(10)         not null                      comment '参照值类型（int，double，string）',
    referCompare                  varchar(5)          not null                      comment '参照值比较类型（=，>，<，!=，>=，<=）',
    prcCd                         varchar(10)         not null                      comment '处理码',
    prcMesg                       varchar(100)            null default ''           comment '处理信息',
    bcrValidity                   char(1)             not null                      comment '规则有效性（0：无效，1：有效）',
    primary key (bcrId)
) comment='业务检查规则表';

##业务检查条件表
drop table if exists BusinessCheckItem;
create table BusinessCheckItem (
    bciId                         char(7)             not null                      comment '条件ID，如：BCI0001',
    bcrId                         char(7)             not null                      comment '规则ID，如：BCR0001',
    item                          varchar(1000)       not null                      comment '条件项',
    itemTable                     varchar(50)             null default ''           comment '条件项取值表',
    itemValue                     varchar(500)            null default ''           comment '条件项取值字段',
    itemType                      varchar(10)             null default ''           comment '条件项类型（int，double，string）',
    itemCompare                   varchar(5)              null default ''           comment '条件项比较类型（=，>，<，!=，>=，<=）',
    primary key (bciId)
) comment='业务检查条件表';



drop table if exists test;
create table test (
    FD_A     char(10)          not null,
    FD_B     varchar(20)       not null,
    FD_C     decimal(16,2)     not null,
    FD_D     text              not null,
    FD_E     int               not null,
    FD_F     decimal(16,2)         null
);

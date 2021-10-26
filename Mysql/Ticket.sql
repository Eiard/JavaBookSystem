/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/9/27 14:13:41                           */
/*==============================================================*/


drop table if exists Driver;

drop table if exists Pulish;

drop table if exists car;

drop table if exists notice;

drop table if exists police;

/*==============================================================*/
/* Table: Driver                                                */
/*==============================================================*/
create table Driver
(
   Name                 varchar(10) not null,
   DriverID             char(12) not null,
   Adress               varchar(50) not null,
   Zipcode           char(6) not null,
   Telephone            char(11) not null,
   primary key (DriverID)
);

alter table Driver comment '司机';

/*==============================================================*/
/* Table: Pulish                                                */
/*==============================================================*/
create table Pulish
(
   PoliceID             char(7) not null,
   DriverID             char(12) not null,
   ID                   char(7) not null,
   Pulish               char(20),
   primary key (PoliceID, DriverID)
);

alter table Pulish comment '处罚';

/*==============================================================*/
/* Table: car                                                   */
/*==============================================================*/
create table car
(
   CarID                varchar(7) not null,
   Model                varchar(20) not null,
   
   Manufacturer varchar(50) not null,
   生产日期                 datetime not null,
   Attribute_10         char(10),
   primary key (CarID)
);

alter table car comment '机动车';

/*==============================================================*/
/* Table: notice                                                */
/*==============================================================*/
create table notice
(
   ID                   char(7) not null,
   PoliceID             char(7) not null,
   DriverID             char(12) not null,
   CarID                varchar(7) not null,
   Day                  date not null,
   Time                 datetime not null,
   place                char(50) not null,
   Violationrecord   char(100) not null,
   DriverID1            char(12) not null,
   CarID1               char(7) not null,
   PID                  char(7) not null,
   primary key (ID)
);

alter table notice comment '罚单';

/*==============================================================*/
/* Table: police                                                */
/*==============================================================*/
create table police
(
   PoliceID             char(7) not null,
   PoliceName           varchar(20) not null,
   primary key (PoliceID)
);

alter table police comment '警察';

alter table Pulish add constraint FK_Pulish foreign key (PoliceID)
      references police (PoliceID);

alter table Pulish add constraint FK_Pulish2 foreign key (DriverID)
      references Driver (DriverID);

alter table notice add constraint FK_Handle foreign key (PoliceID)
      references police (PoliceID);

alter table notice add constraint FK_about foreign key (CarID)
      references car (CarID);

alter table notice add constraint FK_received foreign key (DriverID)
      references Driver (DriverID);


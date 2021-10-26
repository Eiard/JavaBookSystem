/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/9/26 12:22:18                           */
/*==============================================================*/


drop table if exists Book;

drop table if exists Borrow;

drop table if exists Reader;

drop table if exists ReaderType;

/*==============================================================*/
/* Table: Book                                                  */
/*==============================================================*/
create table Book
(
   bkID                 char(9) not null,
   bkName               varchar(50),
   bkAuthor             varchar(50),
   bkPress              varchar(50),
   bkPrice              decimal(5,2),
   bkState              bool,
   primary key (bkID)
);

/*==============================================================*/
/* Table: Borrow                                                */
/*==============================================================*/
create table Borrow
(
   rdID                 char(9) not null,
   bkID                 char(9) not null,
   DateBorrow           datetime,
   DateLendPlan         datetime,
   DateLendAct          datetime,
   primary key (rdID, bkID)
);

/*==============================================================*/
/* Table: Reader                                                */
/*==============================================================*/
create table Reader
(
   rdID                 char(9) not null,
   rdType               int not null,
   rdName               varchar(20),
   rdDept               varchar(40),
   rdQQ                 varchar(13),
   rdBorrowQty          int default 0,
   primary key (rdID)
);

/*==============================================================*/
/* Table: ReaderType                                            */
/*==============================================================*/
create table ReaderType
(
   rdType               int not null,
   rdTypeName           varchar(20),
   canlendQty           int,
   canlendDay           int,
   primary key (rdType)
);

alter table Borrow add constraint FK_Borrow foreign key (rdID)
      references Reader (rdID) on delete restrict on update restrict;

alter table Borrow add constraint FK_Borrow2 foreign key (bkID)
      references Book (bkID) on delete restrict on update restrict;

alter table Reader add constraint FK_Have foreign key (rdType)
      references ReaderType (rdType) on delete restrict on update restrict;


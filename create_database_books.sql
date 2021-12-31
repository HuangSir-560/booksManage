--创建books数据库，先建好相应路径
CREATE DATABASE books ON  PRIMARY 
( NAME = 'books', FILENAME = 'G:\java_study\Books_Management_java\books.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = 'books_log', FILENAME = 'G:\java_study\Books_Management_java\books_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO

use books
go

/*创建管理员表 */
CREATE TABLE [dbo].[管理员](
	[姓名] [nvarchar](50) NOT NULL,
	[密码] [nvarchar](50) NULL
) ON [PRIMARY]

GO


--创建读者表
CREATE TABLE [dbo].[读者](
	[借书证号] [nchar](6) NOT NULL,
	[类别] [nchar](2) NULL,
	[已借书数] [tinyint] NULL,
	[最多借书数] [tinyint] NULL,
	[编号] [nvarchar](50) NULL,
	[姓名] [nvarchar](10) NULL,
	[系别] [nvarchar](10) NULL,
	[密码] [nchar](10) NULL,
 CONSTRAINT [PK_读者] PRIMARY KEY CLUSTERED (借书证号 ASC)
 );
 GO
 
 /* 创建图书表 */
CREATE TABLE [dbo].[图书](
	[书号] [nvarchar](50) NOT NULL,
	[ISBN] [nvarchar](50) NULL,
	[书名] [nvarchar](20) NULL,
	[作者] [nvarchar](20) NULL,
	[出版社] [nvarchar](50) NULL,
	[出版年] [nchar](4) NULL,
	[状态] [nchar](2) NULL DEFAULT '未借',
	[书库] [nvarchar](50) NULL,
 CONSTRAINT [PK_图书] PRIMARY KEY CLUSTERED ([书号] ASC)
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[图书]  WITH CHECK 
ADD  CONSTRAINT [CK_图书] CHECK  (([状态]='未借' OR [状态]='借出'))
GO
ALTER TABLE [dbo].[图书] CHECK CONSTRAINT [CK_图书]
GO

/* 创建借阅表 */
CREATE TABLE [dbo].[借阅](
	[借书证号] [nchar](6) NOT NULL,
	[书号] [nvarchar](50) NOT NULL,
	[借出日期] [smalldatetime] NOT NULL,
	[还书日期]  AS (dateadd(month,(6),[借出日期])),
 CONSTRAINT [PK_借阅_1] PRIMARY KEY CLUSTERED (	[书号] ASC) 
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[借阅]  WITH CHECK 
ADD  CONSTRAINT [FK_借阅_读者] FOREIGN KEY([借书证号])
REFERENCES [dbo].[读者] ([借书证号])
GO
ALTER TABLE [dbo].[借阅] CHECK CONSTRAINT [FK_借阅_读者]
GO
ALTER TABLE [dbo].[借阅]  WITH CHECK 
ADD  CONSTRAINT [FK_借阅_图书] FOREIGN KEY([书号])
REFERENCES [dbo].[图书] ([书号])
GO
ALTER TABLE [dbo].[借阅] CHECK CONSTRAINT [FK_借阅_图书]
GO
ALTER TABLE [dbo].[借阅] 
ADD  CONSTRAINT [DF_借阅_借出日期]  DEFAULT (getdate()) FOR [借出日期]
GO

--向管理员表插入数据 
insert into dbo.管理员
values('1','1')
 
insert into dbo.管理员
values('admin','admin') 

--向读者表插入数据
INSERT INTO 读者(借书证号,类别,已借书数,最多借书数,编号,姓名,系别,密码)
VALUES('005001','学生',0,4,'08050101','李笑笑','计算机','1')

INSERT INTO 读者(借书证号,类别,已借书数,最多借书数,编号,姓名,系别,密码)
VALUES('111','教师',0,10,'11111111','王','计算机','1')

INSERT INTO 读者(借书证号,类别,已借书数,最多借书数,编号,姓名,系别,密码)
VALUES('222','教师',0,10,'22222222','高','计算机','1')



--向图书表插入数据 
insert into dbo.图书
values('1','1-22-33','SQL SERVER 2008','王','电子','2009','未借','2') 

insert into dbo.图书
values('1111','7-8900-44-5','大数据时代','孟','11','2011','未借','3')

insert into dbo.图书                                       
values('88-999-77','788-999-77-9999','云计算','电子','张怡','2015','未借','3')

insert into dbo.图书
values('TN123 ','7-8-55','电路','童诗白','电子','2005','未借','3') 

insert into dbo.图书
values('TP30101','7-456-1-123','数据库','高','电子','2009','未借','2')

insert into dbo.图书
values('TP30201','7-332-562-1 ','.NET',' 刘','清华','2008','未借','1')

insert into dbo.图书
values('TP30208','7-302-856-2','数据结构','张','电子','2008','未借','1')

insert into dbo.图书
values('TP339','NULL','SQL SERVER 2005','林雨 ','高教 ','2012','未借','2')

insert into dbo.图书
values('TP370',' 7-33-222-1','三毛流浪记','张乐平','文化','1953','未借','4') 

insert into dbo.图书
values('TP888','7-32-45-6 ','计算机网络','张明','清华大学出版社','2009','未借','1') 

insert into dbo.图书
values('TP987','7-9-45-2','数据库系统概论','林美','高教','2008','未借','2') 

--创建视图----
CREATE VIEW borrow_info 
AS
SELECT 借阅.借书证号, 类别, 姓名, 系别, 借阅.书号,书名,作者,状态,还书日期
FROM  读者 INNER JOIN 借阅 ON 读者.借书证号 = 借阅.借书证号 
      		INNER JOIN 图书 ON 借阅.书号 = 图书.书号
      		

----创建2个触发器（在借阅表上）-------
--在借阅表上创建第1个触发器。当向借阅表中插入一条记录时（借书时），由触发器更新读者表，使已借书数加1，也更新图书表，将图书的状态改为“借出”。
Create trigger  in_borrow  on  借阅 
for insert
as
begin
	if exists (select * from inserted )
	begin
		update 读者 set 已借书数=已借书数+1
		where 借书证号=(select 借书证号 from inserted);
		update 图书 set 状态='借出'
		where 书号=(select 书号 from inserted);
	end
end

--在借阅表上创建第2个触发器。还书时，将从借阅表中删除一条记录，由该触发器更新读者表，使已借书数减1，也更新图书表，将图书的状态改为“未借”。
CREATE TRIGGER returnbook ON 借阅 
for DELETE 
AS 
BEGIN
	if exists (select * from deleted )
	begin
		update 读者 set 已借书数=已借书数-1
		where 借书证号=(select 借书证号 from deleted);
		update 图书 set 状态='未借'
		where 书号=(select 书号 from deleted);
	end
END

-----创建2个存储过程--------------
--输入借书证号、书号，查询是否允许该读者借出该书（当该读者已借书数没达到最多借书数时可以借书，并向借阅表中插入一条记录），输出借出成功与否的信息（成功；不成功）。
Create procedure if_borrow
@borrowno nchar(6),@bookno nchar(15),@succ int output
as
begin
	if (select 已借书数 from 读者 where 借书证号=@borrowno)
	<(select 最多借书数 from 读者 where 借书证号=@borrowno)
	begin
		insert into  借阅(借书证号,书号,借出日期)
        values(@borrowno,@bookno,getdate() );
        set @succ=1
	end
	else set @succ=0
end

--查询是否有书名含有一个给定字符串的图书存在。
Create procedure  dbo.searchbook   
@bookname nvarchar(15)
as
select  * from 图书 where 书名 like '%'+ @bookname +'%'



 
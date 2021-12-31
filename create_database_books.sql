--����books���ݿ⣬�Ƚ�����Ӧ·��
CREATE DATABASE books ON  PRIMARY 
( NAME = 'books', FILENAME = 'G:\java_study\Books_Management_java\books.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = 'books_log', FILENAME = 'G:\java_study\Books_Management_java\books_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO

use books
go

/*��������Ա�� */
CREATE TABLE [dbo].[����Ա](
	[����] [nvarchar](50) NOT NULL,
	[����] [nvarchar](50) NULL
) ON [PRIMARY]

GO


--�������߱�
CREATE TABLE [dbo].[����](
	[����֤��] [nchar](6) NOT NULL,
	[���] [nchar](2) NULL,
	[�ѽ�����] [tinyint] NULL,
	[��������] [tinyint] NULL,
	[���] [nvarchar](50) NULL,
	[����] [nvarchar](10) NULL,
	[ϵ��] [nvarchar](10) NULL,
	[����] [nchar](10) NULL,
 CONSTRAINT [PK_����] PRIMARY KEY CLUSTERED (����֤�� ASC)
 );
 GO
 
 /* ����ͼ��� */
CREATE TABLE [dbo].[ͼ��](
	[���] [nvarchar](50) NOT NULL,
	[ISBN] [nvarchar](50) NULL,
	[����] [nvarchar](20) NULL,
	[����] [nvarchar](20) NULL,
	[������] [nvarchar](50) NULL,
	[������] [nchar](4) NULL,
	[״̬] [nchar](2) NULL DEFAULT 'δ��',
	[���] [nvarchar](50) NULL,
 CONSTRAINT [PK_ͼ��] PRIMARY KEY CLUSTERED ([���] ASC)
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ͼ��]  WITH CHECK 
ADD  CONSTRAINT [CK_ͼ��] CHECK  (([״̬]='δ��' OR [״̬]='���'))
GO
ALTER TABLE [dbo].[ͼ��] CHECK CONSTRAINT [CK_ͼ��]
GO

/* �������ı� */
CREATE TABLE [dbo].[����](
	[����֤��] [nchar](6) NOT NULL,
	[���] [nvarchar](50) NOT NULL,
	[�������] [smalldatetime] NOT NULL,
	[��������]  AS (dateadd(month,(6),[�������])),
 CONSTRAINT [PK_����_1] PRIMARY KEY CLUSTERED (	[���] ASC) 
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[����]  WITH CHECK 
ADD  CONSTRAINT [FK_����_����] FOREIGN KEY([����֤��])
REFERENCES [dbo].[����] ([����֤��])
GO
ALTER TABLE [dbo].[����] CHECK CONSTRAINT [FK_����_����]
GO
ALTER TABLE [dbo].[����]  WITH CHECK 
ADD  CONSTRAINT [FK_����_ͼ��] FOREIGN KEY([���])
REFERENCES [dbo].[ͼ��] ([���])
GO
ALTER TABLE [dbo].[����] CHECK CONSTRAINT [FK_����_ͼ��]
GO
ALTER TABLE [dbo].[����] 
ADD  CONSTRAINT [DF_����_�������]  DEFAULT (getdate()) FOR [�������]
GO

--�����Ա��������� 
insert into dbo.����Ա
values('1','1')
 
insert into dbo.����Ա
values('admin','admin') 

--����߱��������
INSERT INTO ����(����֤��,���,�ѽ�����,��������,���,����,ϵ��,����)
VALUES('005001','ѧ��',0,4,'08050101','��ЦЦ','�����','1')

INSERT INTO ����(����֤��,���,�ѽ�����,��������,���,����,ϵ��,����)
VALUES('111','��ʦ',0,10,'11111111','��','�����','1')

INSERT INTO ����(����֤��,���,�ѽ�����,��������,���,����,ϵ��,����)
VALUES('222','��ʦ',0,10,'22222222','��','�����','1')



--��ͼ���������� 
insert into dbo.ͼ��
values('1','1-22-33','SQL SERVER 2008','��','����','2009','δ��','2') 

insert into dbo.ͼ��
values('1111','7-8900-44-5','������ʱ��','��','11','2011','δ��','3')

insert into dbo.ͼ��                                       
values('88-999-77','788-999-77-9999','�Ƽ���','����','����','2015','δ��','3')

insert into dbo.ͼ��
values('TN123 ','7-8-55','��·','ͯʫ��','����','2005','δ��','3') 

insert into dbo.ͼ��
values('TP30101','7-456-1-123','���ݿ�','��','����','2009','δ��','2')

insert into dbo.ͼ��
values('TP30201','7-332-562-1 ','.NET',' ��','�廪','2008','δ��','1')

insert into dbo.ͼ��
values('TP30208','7-302-856-2','���ݽṹ','��','����','2008','δ��','1')

insert into dbo.ͼ��
values('TP339','NULL','SQL SERVER 2005','���� ','�߽� ','2012','δ��','2')

insert into dbo.ͼ��
values('TP370',' 7-33-222-1','��ë���˼�','����ƽ','�Ļ�','1953','δ��','4') 

insert into dbo.ͼ��
values('TP888','7-32-45-6 ','���������','����','�廪��ѧ������','2009','δ��','1') 

insert into dbo.ͼ��
values('TP987','7-9-45-2','���ݿ�ϵͳ����','����','�߽�','2008','δ��','2') 

--������ͼ----
CREATE VIEW borrow_info 
AS
SELECT ����.����֤��, ���, ����, ϵ��, ����.���,����,����,״̬,��������
FROM  ���� INNER JOIN ���� ON ����.����֤�� = ����.����֤�� 
      		INNER JOIN ͼ�� ON ����.��� = ͼ��.���
      		

----����2�����������ڽ��ı��ϣ�-------
--�ڽ��ı��ϴ�����1����������������ı��в���һ����¼ʱ������ʱ�����ɴ��������¶��߱�ʹ�ѽ�������1��Ҳ����ͼ�����ͼ���״̬��Ϊ���������
Create trigger  in_borrow  on  ���� 
for insert
as
begin
	if exists (select * from inserted )
	begin
		update ���� set �ѽ�����=�ѽ�����+1
		where ����֤��=(select ����֤�� from inserted);
		update ͼ�� set ״̬='���'
		where ���=(select ��� from inserted);
	end
end

--�ڽ��ı��ϴ�����2��������������ʱ�����ӽ��ı���ɾ��һ����¼���ɸô��������¶��߱�ʹ�ѽ�������1��Ҳ����ͼ�����ͼ���״̬��Ϊ��δ�衱��
CREATE TRIGGER returnbook ON ���� 
for DELETE 
AS 
BEGIN
	if exists (select * from deleted )
	begin
		update ���� set �ѽ�����=�ѽ�����-1
		where ����֤��=(select ����֤�� from deleted);
		update ͼ�� set ״̬='δ��'
		where ���=(select ��� from deleted);
	end
END

-----����2���洢����--------------
--�������֤�š���ţ���ѯ�Ƿ�����ö��߽�����飨���ö����ѽ�����û�ﵽ��������ʱ���Խ��飬������ı��в���һ����¼�����������ɹ�������Ϣ���ɹ������ɹ�����
Create procedure if_borrow
@borrowno nchar(6),@bookno nchar(15),@succ int output
as
begin
	if (select �ѽ����� from ���� where ����֤��=@borrowno)
	<(select �������� from ���� where ����֤��=@borrowno)
	begin
		insert into  ����(����֤��,���,�������)
        values(@borrowno,@bookno,getdate() );
        set @succ=1
	end
	else set @succ=0
end

--��ѯ�Ƿ�����������һ�������ַ�����ͼ����ڡ�
Create procedure  dbo.searchbook   
@bookname nvarchar(15)
as
select  * from ͼ�� where ���� like '%'+ @bookname +'%'



 
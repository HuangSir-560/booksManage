# booksManage


开发环境安装：
0-1.下载JDK，安装（无需设置环境变量）
1-2.下载EClipse，安装

（1-2可跳过，用默认工作空间也可
设置工作空间：
1.新建文件夹，作为工作目录，如e:\mw
2.打开eclipse，选择工作空间为e:\mw
）

3.在eclipse中选择菜单“文件”-“import”-“general”-“existing projects into workspace”，然后“browse”选择示例程序的根目录(可再选择“copy projects into workspace”，复制一份到工作目录下)
5.修改jre版本和加载jdbc驱动程序
  项目名 - 右键 - buil path - configure build path - libirares – 选择列表中的项目-  点击remove按钮删除- 选择列表中下一行 -点击remove按钮删除，然后：
   add library - jre system libray(即你的系统安装的jre)     //指定jre环境为本机安装的jre
   add external build path - 项目目录下的sqljdbc4.jar     //加载jdbc driver
6.安装window builder
  到http://www.eclipse.org/windowbuilder/download.php,找到与你的eclipse版本(help-about中可以查看)对应的window builder版本，点相应的链接，复制网址（http//....）;
  eclipse-help- install new software - "work with”中粘贴网址 - 点击“add”-下面出现的列表全选 -next
7.附加books数据库（或执行创建数据库的sql语句）
9.设置数据库服务器的登陆验证方式为混合验证模式：
  在连接上（它代表了服务器实例）-右键-属性-安全性-sql server 和windows混合验证模式
10.重新启动数据库服务器
  在连接上（它代表了服务器实例）-右键-重新启动
11.设置sa密码为sa，并启用sa账号
   服务器- 安全性- 账号名 - sa - 右键 - 属性 -常规- 密码改为sa, 勾掉“强制实施密码策略”
   点击左边“状态” - "启用".
   点“确定”
12.设置端口和协议
   服务器端：打开sql server配置管理器 - sql server网络配置 -mssqlserver的协议-TCP/IP设置为“启用”；
             在TCP/IP上- 右键 - 属性 -默认端口设置为1433
   客户端：打开SQL Native Client 10.0配置- 客户端协议 -TCP/IP设置为“启用”；
           在TCP/IP上- 右键 - 属性 -默认端口设置为1433
   （注：如果是sqlserver2016、2014，打开配置管理器的方法是：
       我的电脑- 右键 - 管理 -服务和应用程序 - sql server配置管理器）
    设置完根据提示，可能要重启数据库服务器，参照10）
13.如果是默认实例（即名字为MSSQLSER的实例），程序不用改；如果是命名实例（如SQLExpress,mssqlserver1等），还要把DBUtil类中的Url该为命名实例的格式，即用url上面那一行代码的形式。
(14.如果使用oracle数据库，无需另外下载jdbc驱动，安装好oracle就有了，目录为D:\oracle\product\11.2.0\dbhome_1\jdbc\lib ，ojdbc14.jar)
(15.如果使用mysql数据库，需要单独下载安装驱动程序)


开发：
1.打开Eclipse代码提示功能
  首先打开Eclipse开发软件，然后在工具栏的【Window】-Preferences- java-editor-content Assist, 改成.abcdefghijklmnopqrstuvwxyz
2.用windowbuilder打开窗口类
   在类上右键- open with -windowbuilder editor；
   单击“code/ design”进行“代码/图形界面”的切换
3.控件添加事件的方法：
  控件上- 右键 -add event handler - (mouse - MousePressed)

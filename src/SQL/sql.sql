create table 20students(
id int,
name varchar(50),
age int,
sex char(1),
brief varchar(200)
);
create table courses(
id int,
course char(9),
teacher char(1),
book char(6),
class char(5)
);
create table student(
id int,
sn int comment "学号",
name varchar(20) comment "姓名",
qq_mail varchar(20) comment "qq 邮箱"
);

create table exam_result(
id int,
name varchar(200),
chinese double,
math double,
English double
);
insert into exam_result values
(1,'唐三藏',11,22,33),
(2,'孙猴子',22,33,44),
(3,'猪悟能',33,44,55),
(4,'沙悟净',44,55,66),
(5,'吒儿',55,66,77),
(6,'姜子牙',66,77,88),
(7,'myself',77,88,99);

select English > 60 from exam_result;
select name, English > 60 from exam_result;
//查询英语成绩大于60 分的人
select * from exam_result where English > 60;
select * from exam_result where English > chinese;
select * from exam_result where English = 77;
select * from exam_result where English != 77;
select * from exam_result where name = '吒儿';
select * from exam_result where name != '吒儿';

//查询英语成绩在[]区间的人，左闭右闭
select * from exam_result where English between 60 and 100;
select * from exam_result where English >= 70 and English <= 100;

//查询某人的成绩    ----- 只要有一个为true，就可以返回
select * from exam_result where name in ('孙悟空','唐三藏');
select * from exam_result where name = '孙悟空' or name = '唐三藏';
//查询除了某人的成绩
select * from exam_result where name not in ('孙猴子','吒儿');
select * from exam_result where not (name = '孙猴子' or name = '吒儿');
select * from exam_result where name != '孙猴子' and name != '吒儿';

//查询登记过qq邮箱的人
select * from student where qq_mail != null; --- 错误用法
select * from student where null;            ---错误用法

select * from student where qq_mail is not null;---正确用法

//查询没有登记过邮箱的同学
select * from student where qq_mail = null;   ---错误用法
select * from student where null;             ---错误用法

select * from student where qq_mail is null;  ---正确用法   ---常用
select * from student where qq_mail <=> null; ---正确用法   ---认识即可

--查询某个同学的成绩，条件是该同学姓孙
select * from exam_result where name like '孙%';
--  %代表
--  0个或者多个字符              数目待定
--  如果出现，可以是任意字符     字符待定

--查询某个同学的成绩，条件是该同学叫 什么孙
select * from exam_result where name like '%孙';

--查询某个同学的成绩，条件是该同学名字中带孙
select * from exam_result where name like '%孙%';


 insert into exam_result values
   (8,'行者孙',12,23,34),
   (9,'者行孙',23,34,45);

   -- _代表

   -- 字符数目固定，必须是一个
   -- 字符是什么待定

--查询某个同学的成绩，条件是该同学叫孙某/孙某某
select * from exam_result where name like '孙__';

--查询某个同学的成绩，条件是该同学不叫孙某/孙某某
select * from exam_result where name not like '孙__';

select * from exam_result where(chinese > 50 or math > 50) and English > 50;
select * from exam_result where chinese > 50 or math > 50 and English > 50;

--asc 默认排序  从低到高
--desc  从高到低
select * from exam_result order by English desc;

--查询所有人成绩，按照英语成绩从高到低排序，如果相等，按照数学从低到高排序，其次id从高到低排序
select * from exam_result order by English desc,math asc,id desc;

--选出id排序，从低到高，前三个---从 0 开始，筛选 n 条结果
select * from exam_result order by id limit 3;
--选出id排序，从低到高，5，6，7---从 4 开始，筛选 3 条结果
select * from exam_result order by id limit 3 offset 4;---建议使用
select * from exam_result order by id limit 4,3;


--修改学生信息，把唐三藏的请求邮箱修改为"tangtang@fo.com"
update student set qq_mail = "tangtang@fo.com" where name = '唐三藏';

--如果英语成绩小于60分， 把英语成绩改为60分
--如果大于等于60分，成绩+5分
update exam_result set English = English + 5 where English >60;
update exam_result set English = 60 where English < 60;
--或者
select id from exam_result where English < 60;
update exam_result set English = 60 where id in(上一步找出的id);
update exam_result set English = 60 where id not in(id);

修改学生信息，把孙悟空改为孙猴子，同时更改学号
update student set name = '孙猴子',sn = '666'where name = '孙悟空';

 --删除
 delete from student where id = 100;
 delete from student;

 --使用约束的学生表
 create table student_0929(
 id int primary key auto_increment comment '选择自增的数列作为主键',
 sn int unique comment '学号，可以不填，视为null，但填了就不能重复',
 name varchar(100) not null comment '不允许为null',
 sex char(1) not null default '女' comment '性别'
 );
 --有约束时插入
 insert into student_0929(sn,name)values (102,'白骨精'),(103,'嫦娥');
 insert into student_0929(sn,name,sex) values(101,'孙悟空','男');
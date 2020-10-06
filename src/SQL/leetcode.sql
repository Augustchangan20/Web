create database leetcode_182 charset utf8mb4;
use leetcode_182;

create table Person (
  Id int primary key,
  Email varchar(20)
);

insert into Person (Id, Email) values (1, 'a@b.com'), (2, 'c@d.com'), (3, 'a@b.com');

-- 方法 1 利用聚合查询完成我们的操作

select Email from Person group by Email having count(*) > 1;


-- 方法 2 利用子查询的 in 完成
select distinct Email from Person p1 where Email in (select Email from Person p2 where p1.Id != p2.Id);


-- 方法 3 利用子查询的 exists 完成
select distinct Email from Person p1 where exists (
  select Email from Person p2 where p1.Id != p2.Id and p1.Email = p2.Email
);




select FirstName,LastName,City,State
from Person
left join Adddress
on Person.PersonId = Address.PersonId;

--力扣 181. 超过经理收入的员工
select e.Name Employee
from Employee e
join Employee m
on e.ManagerId = m.Id
where e.Salary > m.Salary;

--力扣183 ，从没订购过东西的人
select Customers.Name Customers
from Customers
left join Orders
on Customers.Id = Orders.CustomerId
where Orders.Id is null;


--力扣  176第二高的薪水
select (
        select distinct Salary
		from Employee
		order by Salary desc limit 1 offset 1
)SecondHighestSalary;











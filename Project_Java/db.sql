create database project_Java;
use project_Java;

create table admin
(
    id       int primary key auto_increment,
    username varchar(50)  not null unique,
    password varchar(255) not null
);

create table student
(
    id        int primary key auto_increment,
    name      varchar(100) not null,
    dob       date         not null,
    email     varchar(100) not null unique,
    sex       bit          not null,
    phone     varchar(20),
    password  varchar(255),
    create_at date
);

create table course
(
    id         int primary key auto_increment,
    name       varchar(100) not null,
    duration   int          not null,
    instructor varchar(100) not null,
    create_at  date
);

create table enrollment
(
    id            int primary key auto_increment,
    student_id    int not null,
    course_id     int not null,
    registered_ad datetime                                        default current_timestamp,
    status        enum ('WAITING', 'DENIED', 'CANCER', 'CONFIRM') default 'WAITING',
    foreign key (student_id) references student (id) ON DELETE CASCADE,
    foreign key (course_id) references course (id) ON DELETE CASCADE
);

DELIMITER &&
create procedure login_by_admin(
    username_in varchar(50),
    password_in varchar(255),
    out count int
)
begin
select count(*)
into count
from admin
where username = username_in
  and password = password_in;
end;

create procedure login_by_student(
    email_in varchar(100),
    password_in varchar(255),
    out count int
)
begin
select count(*)
into count
from student
where email = email_in
  and password = password_in;
end;

create procedure create_student(
    name_in varchar(100),
    dob_in date,
    email_in varchar(100),
    sex_in bit,
    phone_in varchar(20),
    password_in varchar(255)
)
begin
insert into student(name, dob, email, sex, phone, password, create_at)
    value (name_in, dob_in, email_in, sex_in, phone_in, password_in, current_date);
end;

create procedure get_all_student()
begin
select * from student;
end;

create procedure update_student(
    id_in int,
    name_in varchar(100),
    dob_in date,
    email_in varchar(100),
    sex_in bit,
    phone_in varchar(20)
)
begin
update student
set name  = name_in,
    dob   = dob_in,
    email = email_in,
    sex   = sex_in,
    phone = phone_in
where id = id_in;
end;

create procedure delete_student_by_id(
    id_in int
)
begin
delete
from student
where id = id_in;
end;

create procedure find_student(
    string_in varchar(100)
)
begin
select *
from student
where name like concat('%', string_in, '%')
   or email like concat('%', string_in, '%');
end;

create procedure sort_student_by_id(
    sort_choice int
)
begin
    if sort_choice = 1 then
select * from student order by id ASC;
elseif sort_choice = 2 then
select * from student order by id DESC;
end if;
end;

create procedure sort_student_by_name(
    sort_choice int
)
begin
    if sort_choice = 1 then
select * from student order by name ASC ;
elseif sort_choice = 2 then
select * from student order by name DESC ;
end if;
end;

create procedure create_course(
    name_in varchar(100),
    duration_in int,
    instructor_in varchar(100)
)
begin
insert into course(name, duration, instructor, create_at)
    value (name_in, duration_in, instructor_in, current_date);
end;

create procedure get_all_course()
begin
select * from course;
end;

create procedure update_course_by_id(
    id_in int,
    name_in varchar(100),
    duration_in int,
    instructor_in varchar(100)
)
begin
update course
set name       = name_in,
    duration   = duration_in,
    instructor = instructor_in
where id = id_in;
end;

create procedure delete_course_by_id(
    id_in int
)
begin
delete
from course
where id = id_in;
end;

create procedure find_course_by_name(
    name_in varchar(100)
)
begin
select *
from course
where name like concat('%', name_in, '%');
end;

create procedure sort_course_by_id(
    sort_choice int
)
begin
    if sort_choice = 1 then
select * from course order by id ASC;
elseif sort_choice = 2 then
select * from course order by id DESC;
end if;
end;

create procedure sort_course_by_name(
    sort_choice int
)
begin
    if sort_choice = 1 then
select * from course order by name ASC ;
elseif sort_choice = 2 then
select * from course order by name DESC ;
end if;
end;

create procedure enrollment_student(
    student_id_in int,
    course_id_in int
)
begin
insert into enrollment(student_id, course_id)
    value (student_id_in, course_id_in);
end;

create procedure get_all_enrollment()
begin
select e.id,
       s.name as 'Student_name',
    c.name as 'Course_name',
    e.registered_ad,
       e.status

from enrollment e
         join course c
              on c.id = e.course_id
         join student s
              on s.id = e.student_id;
end;

create procedure delete_student_from_course(
    student_id_in int,
    course_id_in int
)
begin
delete
from enrollment
where student_id = student_id_in
  and course_id = course_id_in;
end;

create procedure total_course_and_students()
begin
select (select count(course.id) from course)   as total_courses,
       (select count(student.id) from student) as total_students;
end;

create procedure total_students_in_course()
begin
select c.name              as course_name,
       count(e.student_id) as student_count
from course c
         left join enrollment e on c.id = e.course_id
group by c.id;
end;

create procedure top_5_course()
begin
select c.name,
       count(e.student_id) as total_students
from course c
         join enrollment e on c.id = e.course_id
group by c.id
order by total_students DESC
    limit 5;
end;

create procedure student_with_more_10_course()
begin
select s.name,
       count(e.course_id) as course_count
from student s
         join enrollment e on s.id = e.student_id
group by s.id
having course_count > 10;
end;
DELIMITER &&

DELIMITER &&

CREATE PROCEDURE check_course_id_exists (
    IN p_id INT,
    OUT p_exists INT
)
BEGIN
SELECT COUNT(*) INTO p_exists FROM course WHERE id = p_id;
END &&

DELIMITER ;


DELIMITER &&

CREATE PROCEDURE check_student_id_exists (
    IN s_id INT,
    OUT s_exists INT
)
BEGIN
SELECT COUNT(*) INTO s_exists FROM student WHERE id = s_id;
END &&

DELIMITER ;

DELIMITER &&

CREATE PROCEDURE check_enrollment_id_exists (
    IN e_id INT,
    OUT e_exists INT
)
BEGIN
SELECT COUNT(*) INTO e_exists FROM enrollment WHERE id = e_id;
END &&

DELIMITER ;

create database project_Java;
use project_Java;

create table admin
(
    id       int primary key auto_increment,
    username varchar(50)  not null unique,
    password varchar(255) not null
);

create table student
(
    id        int primary key auto_increment,
    name      varchar(100) not null,
    dob       date         not null,
    email     varchar(100) not null unique,
    sex       bit          not null,
    phone     varchar(20),
    password  varchar(255),
    create_at date
);

create table course
(
    id         int primary key auto_increment,
    name       varchar(100) not null,
    duration   int          not null,
    instructor varchar(100) not null,
    create_at  date
);

create table enrollment
(
    id            int primary key auto_increment,
    student_id    int not null,
    course_id     int not null,
    registered_ad datetime                                        default current_timestamp,
    status        enum ('WAITING', 'DENIED', 'CANCER', 'CONFIRM') default 'WAITING',
    foreign key (student_id) references student (id) ON DELETE CASCADE,
    foreign key (course_id) references course (id) ON DELETE CASCADE
);

DELIMITER &&
create procedure login_by_admin(
    username_in varchar(50),
    password_in varchar(255),
    out count int
)
begin
select count(*)
into count
from admin
where username = username_in
  and password = password_in;
end;

create procedure login_by_student(
    email_in varchar(100),
    password_in varchar(255),
    out count int
)
begin
select count(*)
into count
from student
where email = email_in
  and password = password_in;
end;

create procedure create_student(
    name_in varchar(100),
    dob_in date,
    email_in varchar(100),
    sex_in bit,
    phone_in varchar(20),
    password_in varchar(255)
)
begin
insert into student(name, dob, email, sex, phone, password, create_at)
    value (name_in, dob_in, email_in, sex_in, phone_in, password_in, current_date);
end;

create procedure get_all_student()
begin
select * from student;
end;

create procedure update_student(
    id_in int,
    name_in varchar(100),
    dob_in date,
    email_in varchar(100),
    sex_in bit,
    phone_in varchar(20)
)
begin
update student
set name  = name_in,
    dob   = dob_in,
    email = email_in,
    sex   = sex_in,
    phone = phone_in
where id = id_in;
end;

create procedure delete_student_by_id(
    id_in int
)
begin
delete
from student
where id = id_in;
end;

create procedure find_student(
    string_in varchar(100)
)
begin
select *
from student
where name like concat('%', string_in, '%')
   or email like concat('%', string_in, '%');
end;

create procedure sort_student_by_id(
    sort_choice int
)
begin
    if sort_choice = 1 then
select * from student order by id ASC;
elseif sort_choice = 2 then
select * from student order by id DESC;
end if;
end;

create procedure sort_student_by_name(
    sort_choice int
)
begin
    if sort_choice = 1 then
select * from student order by name ASC ;
elseif sort_choice = 2 then
select * from student order by name DESC ;
end if;
end;

create procedure create_course(
    name_in varchar(100),
    duration_in int,
    instructor_in varchar(100)
)
begin
insert into course(name, duration, instructor, create_at)
    value (name_in, duration_in, instructor_in, current_date);
end;

create procedure get_all_course()
begin
select * from course;
end;

create procedure update_course_by_id(
    id_in int,
    name_in varchar(100),
    duration_in int,
    instructor_in varchar(100)
)
begin
update course
set name       = name_in,
    duration   = duration_in,
    instructor = instructor_in
where id = id_in;
end;

create procedure delete_course_by_id(
    id_in int
)
begin
delete
from course
where id = id_in;
end;

create procedure find_course_by_name(
    name_in varchar(100)
)
begin
select *
from course
where name like concat('%', name_in, '%');
end;

create procedure sort_course_by_id(
    sort_choice int
)
begin
    if sort_choice = 1 then
select * from course order by id ASC;
elseif sort_choice = 2 then
select * from course order by id DESC;
end if;
end;

create procedure sort_course_by_name(
    sort_choice int
)
begin
    if sort_choice = 1 then
select * from course order by name ASC ;
elseif sort_choice = 2 then
select * from course order by name DESC ;
end if;
end;

create procedure enrollment_student(
    student_id_in int,
    course_id_in int
)
begin
insert into enrollment(student_id, course_id)
    value (student_id_in, course_id_in);
end;

create procedure get_all_enrollment()
begin
select e.id,
       s.name as 'Student_name',
    c.name as 'Course_name',
    e.registered_ad,
       e.status

from enrollment e
         join course c
              on c.id = e.course_id
         join student s
              on s.id = e.student_id;
end;

create procedure delete_student_from_course(
    student_id_in int,
    course_id_in int
)
begin
delete
from enrollment
where student_id = student_id_in
  and course_id = course_id_in;
end;

create procedure total_course_and_students()
begin
select (select count(course.id) from course)   as total_courses,
       (select count(student.id) from student) as total_students;
end;

create procedure total_students_in_course()
begin
select c.name              as course_name,
       count(e.student_id) as student_count
from course c
         left join enrollment e on c.id = e.course_id
group by c.id;
end;

create procedure top_5_course()
begin
select c.name,
       count(e.student_id) as total_students
from course c
         join enrollment e on c.id = e.course_id
group by c.id
order by total_students DESC
    limit 5;
end;

create procedure student_with_more_10_course()
begin
select s.name,
       count(e.course_id) as course_count
from student s
         join enrollment e on s.id = e.student_id
group by s.id
having course_count > 10;
end;
DELIMITER &&

DELIMITER &&

CREATE PROCEDURE check_course_id_exists (
    IN p_id INT,
    OUT p_exists INT
)
BEGIN
SELECT COUNT(*) INTO p_exists FROM course WHERE id = p_id;
END &&

DELIMITER ;


DELIMITER &&

CREATE PROCEDURE check_student_id_exists (
    IN s_id INT,
    OUT s_exists INT
)
BEGIN
SELECT COUNT(*) INTO s_exists FROM student WHERE id = s_id;
END &&

DELIMITER ;

DELIMITER &&

CREATE PROCEDURE check_enrollment_id_exists (
    IN e_id INT,
    OUT e_exists INT
)
BEGIN
SELECT COUNT(*) INTO e_exists FROM enrollment WHERE id = e_id;
END &&

DELIMITER ;


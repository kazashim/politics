create database Vvote;
use Vvote;

create table CAND_SIGNUP(
cand_id int auto_increment primary key,
name varchar(50) not null,
password varchar(50) not null,
party varchar(50) not null,
email varchar(200) not null unique,
sex varchar(6) not null,
dob date not null,
city varchar(100) not null,
constituency_id int not null 
);


create table notice_like(
	no int primary key auto_increment,
    user_no int not null,
    notice_no int not null,
    foreign key (user_no) references user(no),
    foreign key (notice_no) references notice(no)
);
DROP TABLE IF EXISTS notice_comment;
-- 댓글
CREATE TABLE notice_comment(
  comment_no int PRIMARY key AUTO_INCREMENT,
  user_no int not null,
  notice_no int not null,
  contents varchar(500) not null,
  recomment int,
  regist_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  delete_date TIMESTAMP default null,
  foreign key (user_no) references user(no),
  foreign key (notice_no) references notice(no)
);
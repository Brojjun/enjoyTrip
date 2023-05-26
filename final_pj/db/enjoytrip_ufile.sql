-- file_info 테이블 생성
CREATE TABLE file_info (
  file_no INT PRIMARY KEY AUTO_INCREMENT,
  saveFolder VARCHAR(100),
  originalFile VARCHAR(100),
  saveFile VARCHAR(100)
);

-- notice 테이블의 컬럼명 변경 + 왜래키로 지정
ALTER TABLE notice
CHANGE COLUMN is_notice file_no varchar(100);

update notice set file_no = null;

ALTER TABLE notice ADD hits INT DEFAULT 0;


-- 여기까지 파일 관련 + 게시판

-- 댓글
CREATE TABLE notice_comment(
  comment_no int PRIMARY key AUTO_INCREMENT,
  user_no int not null,
  notice_no int not null,
  contents varchar(500) not null,
  regist_date TIMESTAMP null DEFAULT CURRENT_TIMESTAMP,
  delete_date TIMESTAMP,
  foreign key (user_no) references user(no),
  foreign key (notice_no) references notice(no)
);


ALTER TABLE attraction_info
ADD COLUMN hits INT DEFAULT 0,
ADD COLUMN uses INT DEFAULT 0;

alter table my_route
add column hits int default 0,
add column regist_date DATETIME DEFAULT NOW(),
add column contents text;
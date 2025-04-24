
-- 회원
CREATE TABLE members(user_id int auto_increment primary key,
	user_email varchar(200) unique,
    user_pw varchar(100),
    user_name varchar(100) unique,
    user_phone varchar(50),
    user_addr varchar(200),
    user_grade char(1) default 'B',
    join_date timestamp default now(),
    user_status boolean default true
);
alter table members alter column user_grade set default 'B';
-- 상품
CREATE TABLE item (item_id int auto_increment primary key,
     seller_id VARCHAR(100), -- user_name 
     user_email VARCHAR(100), -- user_email
     item_name VARCHAR(50), 
     price INT,
     quantity INT,
     description_detail VARCHAR(300) DEFAULT '설명이 없습니다.',
     image_detail VARCHAR(500),
     reg_date timestamp default now(),  -- 추가
     FOREIGN KEY (seller_id) REFERENCES members(user_name) ON DELETE CASCADE
);
-- 경매 진행 내역
CREATE TABLE auction(
	auction_id INT AUTO_INCREMENT PRIMARY KEY,
    item_id INT,
    start_price INT,
    current_price INT,
    bid_unit INT default 5000, -- 입찰단위
    start_time DATETIME default now(),
    end_time DATETIME default now(),
    item_status ENUM('waitng', 'progress', 'end') DEFAULT 'progress',
    foreign key (item_id) references item(item_id) ON DELETE CASCADE
);
-- 입찰내역
CREATE TABLE bid(
	bid_id INT auto_increment primary key, -- 입찰id
	auction_id INT, -- 어떤경매에 입찰했는가
    bidder_id INT,
    bid_price INT,
    bid_time DATETIME default current_timestamp,
    foreign key(auction_id) references auction(auction_id) ON DELETE CASCADE,
    foreign key(bidder_id) references members(user_id) ON DELETE CASCADE
);
-- 알림메시지
CREATE TABLE notification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    message TEXT NOT NULL,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- 경매시간이 종료되면 auction테이블의 item_status가 progress에서 end로 바뀌는 event
SET GLOBAL event_scheduler = ON;
CREATE EVENT update_auction_status_event
ON SCHEDULE EVERY 1 MINUTE
DO
  UPDATE auction
  SET item_status = 'end'
  WHERE end_time <= NOW()
    AND item_status = 'progress';
SHOW EVENTS;
ALTER EVENT update_auction_status_event ON SCHEDULE EVERY 1 MINUTE;

select * from members;
select * from item;
insert into item(item_id, seller_id, item_name, price, quantity, description_detail) value(null, "jinseo", "itemA", 10000, 1, "잘관리되었습니다");
insert into item value(null, "jinseo", "itemB", 20000, 2, "상태 좋습니다");
insert into members(user_id, user_email, user_pw, user_name, user_phone, user_addr, user_grade, user_status) value(null, "jinseo@gmail.com", 1234, "jinseo", "010-4152-5729", "쌍촌동", "A", true); 
insert into members(user_id, user_email, user_pw, user_name, user_phone, user_addr) value(null, "admin@admin.com", 1234, "jinseo", "010-4152-5729", "쌍촌동");

insert into item (item_id, seller_id, user_email, item_name, price, quantity, description_detail, image_Detail) 
values(null,'userA', 'userA@user', 'itemA', 10000, 1, '상태 좋습니다', null);
insert into auction(item_Id, start_price, current_price, start_time, end_time)
values (61, 10000, 10000, now(), DATE_ADD(NOW(), INTERVAL 1 DAY));



select * from auction where item_name like concat('%', 'item', '%');
SELECT i.*
FROM auction a
JOIN item i ON a.item_id = i.item_id
WHERE i.item_name like concat('%', 'item', '%');

select * from members;
select * from item;
select * from auction;
select * from bid;
select * from auction where auction_id=2;
select * from auction where item_id=13;
select * from notification;
SELECT *
        FROM bid
        WHERE auction_id = 8
        ORDER BY bid_price DESC
        LIMIT 1;
delete from members where user_email="user@user";
delete from bid where bidder_id=5;
update members set user_id=1 where user_email='admin@admin.com';

select * from item where item_name like concat('%', 'item', '%');
select count(*) from members where user_name like concat('%','유저','%');
show create table auction;
select * from (select max(bid_price) from bid) max_price where (select * from bid where auction_id=4);
select * from bid where auction_id=4;
select b.bid_id, a.auction_id, m.user_id, m.user_name, b.bid_price from members m, auction a, bid b, item i where a.auction_id=4;
SELECT * FROM bid WHERE auction_id = 4 ORDER BY bid_price DESC LIMIT 1;
update auction set start_time=now(), item_status='progress' where item_id=9;

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
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	foreign key(user_id) references members(user_id) ON DELETE CASCADE
);

-- DM 채팅방 테이블
CREATE TABLE dm_rooms (
    room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    recipient_email varchar(200) NOT NULL,
    sender_email varchar(200) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (recipient_email, sender_email)
);

-- DM 메시지 테이블
CREATE TABLE dm_messages (
    message_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_id BIGINT NOT NULL,
    sender_email varchar(200) NOT NULL,
    recipient_email varchar(200) NOT NULL,
    content TEXT NOT NULL,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    foreign key(room_id) references dm_rooms(room_id) ON DELETE CASCADE
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
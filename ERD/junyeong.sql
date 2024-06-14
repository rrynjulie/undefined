select table_name from information_schema.TABLES
WHERE TABLE_SCHEMA = 'db_undefined'
;

select *from user;
select *from lodging;
select *from room;

INSERT INTO user
values
    ('nejunyoung', '1234','정준영','nejunyoung@gmail.com',now(),'dun','010-6572-7204');

INSERT INTO lodging
VALUES
    (DEFAULT, '1', '2', '3', '신라호텔', '호텔/리조트', '서울특별시', '중구', '서울 중구 동호로 249', '02-2233-3131', 'https://www.shilla.net/seoul/index.do','14:00', '11:00', '철학이 있는 서비스와 격조 높은 휴식을 제공하는 프리미엄 라이프스타일 호텔입니다.', '실내·외 수영장, 실내 체육관, 실내 사우나, 아케이드, 면세점', '', '', '임예나','010-4408-0242','rynjulie0917@gmail.com','19-393-939393','nejunyoung');

INSERT INTO room
VALUES
    (DEFAULT,'1','2','3','스텐다드 룸','2','2','580000','','14','1','더블 (킹 사이즈)','1','NO','1');

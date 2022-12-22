INSERT INTO address (address_code, state_name, city_name)
VALUES ('05300', '서울특별시', '관악구');
INSERT INTO address (address_code, state_name, city_name)
VALUES ('05301', '서울특별시', '서대문구');

INSERT INTO user (email, nickname, user_code, role)
VALUES ('say01v@naver.com', 'jji','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjmFtZSI6IkpvaG4gRG9lIi', 'USER'),
       ('say02v@naver.com', 'j2', 'hytnfgncCI6IkpXVCJ9.eyJzdWIiOiIvsdvnksdnklfsdRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeK', 'USER'),
       ('say03v@naver.com', 'j3', 'sgdsvdsXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5M', 'USER'),
       ('say04v@naver.com', 'j4', 'oloiopitjt5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0Ij', 'USER'),
       ('say05v@naver.com', 'j5', 'nbvnvCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0I', 'USER'),
       ('say06v@naver.com', 'j6', 'hfghJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9l', 'USER'),
       ('say07v@naver.com', 'j7', 'ritrursInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9l', 'USER'),
       ('say08v@naver.com', 'j8', 'lyilhjh.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MD', 'USER');

INSERT INTO accommodation (accommodation_name, address_code, user_id, working_start, working_end , weekend_working_start, weekend_working_end, location, lot_number, address_detail, accommodation_type, average_rate)
VALUES ('정정일 애견 호텔', '05300', 1, '09:00:00', '19:00:00', '11:00:00', '19:00:00', '신림동', '255-11', '행복행복빌 63962호', 0, 1.6),
       ('정정일 애견 유치원', '05300', 1, '09:00:00', '15:00:00', '12:00:00', '15:00:00', '신림동', '255-99', '행복행복빌 23962호', 0, 4.3),
       ('정정일 애묘 호텔', '05300', 1, '09:00:00', '16:00:00', '13:00:00', '16:00:00', '신림동', '255-88', '행복행복빌 33962호', 0, 4.4),
       ('정정일 애묘 유치원', '05300', 1, '09:00:00', '20:00:00', '14:00:00', '20:00:00', '신림동', '255-77', '행복행복빌 43962호', 0, 4.9);

INSERT INTO room (accommodation_id, room_name, amount, pet_category, weight)
VALUES (1, '애견 호텔 5KG 이하', 30000, 'DOG', 5),
       (1, '애견 호텔 5KG 이상', 50000, 'DOG', 10),
       (2, '애견 유치원 5KG 이하', 20000, 'DOG', 5),
       (2, '애견 유치원 5KG 이상', 40000, 'DOG', 10),
       (3, '애묘 호텔 5KG 이하', 25000, 'CAT', 5),
       (3, '애묘 호텔 5KG 이상', 40000, 'CAT', 10),
       (4, '애묘 유치원 5KG 이하', 40000, 'CAT', 5),
       (4, '애묘 유치원 5KG 이상', 70000, 'CAT', 10);

INSERT INTO reservation (room_id, user_id, check_in_date, check_out_date, amount)
VALUES (1, 2, '2022-11-21 14:00:00', '2022-11-25 20:15:00', 100000),
       (1, 3, '2022-11-21 14:00:00', '2022-11-23 16:00:00', 110000),
       (1, 4, '2022-11-21 14:00:00', '2022-11-23 16:00:00', 120000),
       (1, 5, '2022-01-21 14:00:00', '2022-01-23 16:00:00', 130000),
       (2, 5, '2022-02-21 14:00:00', '2022-02-23 16:00:00', 140000),
       (3, 5, '2022-03-21 14:00:00', '2022-03-23 16:00:00', 150000),
       (4, 5, '2022-04-21 14:00:00', '2022-04-23 16:00:00', 160000),
       (1, 5, '2022-05-21 14:00:00', '2022-05-23 16:00:00', 170000),
       (1, 5, '2022-06-21 14:00:00', '2022-06-23 16:00:00', 180000),
       (2, 5, '2022-07-21 14:00:00', '2022-07-23 16:00:00', 190000),
       (2, 5, '2023-12-21 14:00:00', '2023-12-23 16:00:00', 200000);

INSERT INTO review (user_id, reservation_id, rate, content)
VALUES (3, 2, 5, 'very good'),
       (5, 4, 5, 'good'),
       (5, 5, 4, 'good good'),
       (5, 6, 3, 'not bad'),
       (5, 7, 1, 'very bad'),
       (5, 8, 5, 'very very good'),
       (5, 9, 4, 'good'),
       (5, 10, 5, 'wonderful');

INSERT INTO bookmark(user_id, accommodation_id, state)
VALUES (5, 1, false),
       (5, 2, false),
       (5, 3, true);

INSERT INTO image_file(url)
VALUES ('testurl1'),
       ('testurl2'),
       ('testurl3'),
       ('testurl4'),
       ('testurl5');

INSERT INTO file(accommodation_id, room_id, image_file_id)
VALUES (1, null, 1),
       (1, null, 2),
       (1, null, 3),
       (1, null, 4),
       (1, null, 5);
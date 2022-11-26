INSERT INTO address (address_code, state_name, city_name)
VALUES ('05300', '서울특별시', '관악구');


INSERT INTO user (email, nickname, user_code)
VALUES ('say01v@naver.com', 'jji', 'bjfajid12ighsi9832h'),
       ('say02v@naver.com', 'j2', 'bfkdgmdfk832h'),
       ('say03v@naver.com', 'j3', 'jtytthsi9832h'),
       ('say04v@naver.com', 'j4', 'qrqwg832h'),
       ('say05v@naver.com', 'j5', 'bumtjjtjt9832h'),
       ('say06v@naver.com', 'j6', 'jejetjghj32h'),
       ('say07v@naver.com', 'j7', 'nhmkhnmk832h'),
       ('say08v@naver.com', 'j8', 'tirtohm32h');

-- INSERT INTO host (user_id, registration_number, host_name, open_date)
-- VALUES (9, '111222333444', '김훈기', '20220101'),
--        (2, '111222333555', '김훈기', '20220101'),
--        (3, '111222333666', '김훈기', '20220101'),
--        (4, '111222333777', '김훈기', '20220101'),
--        (5, '111222333888', '김훈기', '20220101'),
--        (6, '111222333999', '김훈기', '20220101'),
--        (7, '111222333111', '김훈기', '20220101'),
--        (8, '111222333222', '김훈기', '20220101');

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

INSERT INTO reservation (accommodation_id, user_id, check_in_date, check_out_date, pet_category, weight)
VALUES (1, 2, '20221121 140000', '20221123 160000', 'DOG', 5.9),
       (1, 3, '20221121 140000', '20221123 160000', 'DOG', 4.9),
       (1, 4, '20221121 140000', '20221123 160000', 'DOG', 4.3),
       (1, 5, '20221121 140000', '20221123 160000', 'DOG', 5.2);

INSERT INTO review (user_id, reservation_id, rate, content)
VALUES (3, 2, 5, 'very good');
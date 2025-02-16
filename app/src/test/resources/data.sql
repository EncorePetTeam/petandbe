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
       ('김훈기 애묘 호텔', '05300', 1, '09:00:00', '21:00:00', '13:00:00', '16:00:00', '홍제동', '444-44', '응애오피스텔 33962호', 0, 4.4),
       ('김훈기 애견 호텔', '05300', 1, '09:00:00', '21:00:00', '13:00:00', '16:00:00', '구산동', '555-55', '취업플라자 33962호', 0, 4.4),
       ('장형주 애묘 호텔', '05300', 1, '09:00:00', '20:00:00', '13:00:00', '16:00:00', '구산동', '666-66', '시켜줘빌딩 33962호', 0, 4.4),
       ('장형주 애견 호텔', '05300', 1, '09:00:00', '20:00:00', '13:00:00', '16:00:00', '구산동', '777-97', '네카라쿠배 33962호', 0, 4.5),
       ('엄준식 애견 호텔', '05300', 1, '09:00:00', '19:00:00', '13:00:00', '16:00:00', '한남동', '234-25', '애플빌딩 33962호', 0, 1.1),
       ('엄준식 애묘 호텔', '05300', 1, '09:00:00', '19:00:00', '13:00:00', '16:00:00', '한남동', '123-45', '스페이스X빌딩 33962호', 0, 5.0),
       ('좋은 애견 호텔', '05300', 1, '09:00:00', '18:00:00', '13:30:00', '16:00:00', '갈현동', '765-43', '마소빌딩 33962호', 0, 4.4),
       ('좋은 애묘 유치원', '05300', 1, '09:00:00', '18:00:00', '12:00:00', '20:00:00', '갈현동', '777-77', '펫앤비렛츠고 43962호', 0, 5.0);

INSERT INTO room (accommodation_id, room_name, amount, pet_category, weight)
VALUES (1, '애견 호텔 5KG 이하', 30000, 'DOG', 5),
       (1, '애견 호텔 10KG 이상', 50000, 'DOG', 10),
       (1, '애견 호텔 15KG 이하', 20000, 'DOG', 15),
       (2, '애견 유치원 5KG 이하', 20000, 'DOG', 5),
       (2, '애견 유치원 5KG 이상 10KG 이하', 30000, 'DOG', 10),
       (2, '애견 유치원 10KG 이상 15KG 이하', 40000, 'DOG', 15),
       (3, '김훈기의 자취방 5KG 이하', 40000, 'CAT', 5),
       (3, '애묘 유치원 5KG 이상', 70000, 'DOG', 10),
       (3, '비트 유치원 5KG 이상', 70000, 'CAT', 15),
       (4, '이더리움 유치원 5KG 이상', 70000, 'DOG', 5),
       (4, '삼성전자 유치원 5KG 이상', 70000, 'CAT', 10),
       (4, '김훈기의 작업실', 70000, 'DOG', 15),
       (5, '어떤방이름으로 할까요', 70000, 'CAT', 5),
       (5, '숙박시설의 방이름', 70000, 'DOG', 10),
       (5, '도마뱀도 가능한 방', 70000, 'CAT', 15),
       (6, '강아지들의 천국', 70000, 'DOG', 5),
       (6, '고양이들의 천국', 70000, 'CAT', 10),
       (6, '뱀도 가능한 방', 70000, 'DOG', 15),
       (7, '엄준식 애견호텔의 5KG 이하방', 70000, 'CAT', 5),
       (7, '엄준식 애견호텔의 5KG 이상 10KG 이하방', 70000, 'DOG', 10),
       (7, '엄준식 애견호텔의 10KG 이상 15KG 이하방', 70000, 'CAT', 15),
       (8, '어떤식의 방이름이 방', 70000, 'DOG', 5),
       (8, '제일 괜찮은지 알려주세요 방', 70000, 'CAT', 10),
       (8, '추후에 수정하도록 하겠습니다 방', 70000, 'DOG', 15),
       (9, '애묘 유치원 5KG 이상', 70000, 'CAT', 5),
       (9, '애묘 유치원 5KG 이상', 70000, 'DOG', 10),
       (9, '애묘 유치원 5KG 이상', 70000, 'CAT', 15),
       (10, '좋은 애묘 유치원의 훌륭한 방', 70000, 'CAT', 5),
       (10, '좋은 애묘 유치원 아름다운 방', 70000, 'CAT', 10),
       (10, '좋은 애묘 유치원 5KG 따듯한 방', 70000, 'CAT', 15);

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
       (2, 5, '2023-12-21 14:00:00', '2023-12-23 16:00:00', 200000),
       (4, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (4, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (4, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (5, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (5, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (5, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (6, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (6, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (6, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (7, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (7, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (7, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (8, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (8, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (8, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (9, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (9, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (9, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (10, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (10, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (10, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (11, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (11, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (11, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (12, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (12, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (12, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (13, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (13, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (13, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (14, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (14, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (14, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (15, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (15, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (15, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (16, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (16, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (16, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (17, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (17, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (17, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (18, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (18, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (18, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (19, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (19, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (19, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (20, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (20, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (20, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (21, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (21, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (21, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (22, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (22, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (22, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (23, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (23, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (23, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (24, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (24, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (24, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (25, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (25, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (25, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (26, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (26, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (26, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (27, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (27, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (27, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (28, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (28, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (28, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (29, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (29, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (29, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (30, 1, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (30, 2, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000),
       (30, 3, '2022-12-22 13:00:00', '2023-12-24 17:00:00', 120000);

INSERT INTO review (user_id, reservation_id, rate, content)
VALUES (3, 2, 5, 'very good'),
       (5, 4, 5, 'good'),
       (5, 5, 4, 'good good'),
       (5, 6, 3, 'not bad'),
       (5, 7, 1, 'very bad'),
       (5, 8, 5, 'very very good'),
       (5, 9, 4, 'good'),
       (5, 10, 5, 'wonderful'),
       (1, 12, 5, '아주 만족해요 ㅎㅎ'),
       (2, 13, 5, '사장님이 친절해요'),
       (3, 14, 5, '친절한 사장님 감사합니다'),
       (1, 15, 5, '자주 애용합니다 아주 좋아요'),
       (2, 16, 5, '덕분에 마음편히 여행 다녀왔습니다'),
       (3, 17, 5, '아주 강추드려요'),
       (1, 18, 5, null),
       (2, 19, 5, '저는 만족요'),
       (3, 20, 5, '안심하고 여행다녀왔어요'),
       (1, 21, 5, '만족 그자체'),
       (2, 22, 5, '사랑합니다'),
       (3, 23, 5, '감사합니다'),
       (1, 24, 5, '친절함에 치여요'),
       (2, 25, 5, '아주 좋네요'),
       (3, 26, 5, null),
       (1, 27, 5, null),
       (2, 28, 5, '5점 만점에 만점'),
       (3, 29, 5, '저의 희망'),
       (1, 30, 5, '애용할 수 밖에 없어요'),
       (2, 31, 5, '여행가기전 필수 예약'),
       (3, 32, 5, '아이들이 다녀오면 행복해 해요'),
       (1, 33, 5, '사회화 교육도 잘 진행되는 것 같습니다'),
       (2, 34, 5, '애기들이 더 좋아해요'),
       (3, 35, 5, '여기 가는걸 애기들이 기대해요'),
       (1, 36, 5, '마음편히 여행 다녀올 수 있네요 ㅎㅎ'),
       (2, 37, 5, '5점 드릴 수 밖에 없네요'),
       (3, 38, 5, '사장님이 매우 친절합니다'),
       (1, 39, 5, '기가 막혀요'),
       (2, 40, 5, '코도 막혀요'),
       (3, 41, 5, '굉장합니다'),
       (1, 42, 5, '친절해요'),
       (2, 43, 5, '감사합니다'),
       (3, 44, 5, '나쁘지 않아요'),
       (1, 45, 5, '아주 만족스럽습니다'),
       (2, 46, 5, '굉장하네요 ㅎㅎ'),
       (3, 47, 5, '환경이 너무 깨끗해요'),
       (1, 48, 5, '좋네요'),
       (2, 49, 5, '그저 빛'),
       (1, 51, 5, '빛'),
       (2, 52, 5, '빛 그자체'),
       (3, 53, 5, '감사합니다'),
       (1, 54, 5, '매우 친절해요'),
       (2, 55, 5, '너무 만족스럽습니다'),
       (3, 56, 4, '나쁘지 않았습니다'),
       (1, 57, 3, '그저 그래요'),
       (2, 58, 1, '별로입니다'),
       (3, 59, 5, '환상적이에요'),
       (1, 60, 4, '사장님이 친절해요'),
       (2, 61, 3, '평범합니다'),
       (3, 62, 1, '다시는 안갈래요'),
       (1, 63, 5, '너무 만족스럽습니다!!'),
       (2, 64, 5, '꼭 가보세요!'),
       (3, 65, 5, '고민중이시라면 무조건 가봐야합니다'),
       (1, 66, 5, '별점 5점도 낮은거같아요 100점 드리고 싶어요!'),
       (2, 67, 5, '아이들이 너무 좋아해요'),
       (3, 68, 5, '애기들이 다녀오면 아주 잘자요'),
       (1, 69, 5, '저희 집보다 환경이 좋은거 같습니'),
       (2, 70, 5, '아주 만족해요!'),
       (3, 71, 3, '그저 그렇습니다'),
       (1, 72, 5, '여기만큼 좋은 곳은 못봤어요'),
       (2, 73, 5, '사장님이 아주 친절하십니다!'),
       (3, 74, 5, '세상에 세상에 꼭 다시 아이들 보내고 싶어요!'),
       (1, 75, 5, '예약하기 힘들었는데 드디어 했네요! 아주 만족합니다!'),
       (2, 76, 5, '사장님이 친절해요'),
       (3, 77, 5, '좋네요'),
       (1, 78, 5, '굿굿'),
       (2, 79, 5, '5점드립니다'),
       (3, 80, 5, '5점 만점에 100점!'),
       (1, 81, 5, '꼭 다시 연락 드리고 싶습니다'),
       (2, 82, 5, '아이들이 행복해해요'),
       (3, 83, 5, '이게 애견 호텔이죠'),
       (1, 84, 5, '너무 좋습니다'),
       (2, 85, 5, '아주 좋아요'),
       (3, 86, 5, '좋네요'),
       (1, 87, 5, '좋습니다'),
       (2, 88, 5, '괜찮아요'),
       (3, 89, 5, '만족해요!'),
       (1, 90, 5, '행복합니다'),
       (2, 91, 5, '감사해요!'),
       (3, 92, 5, '완전 좋아요');

INSERT INTO bookmark(user_id, accommodation_id, state)
VALUES (5, 1, false),
       (5, 2, false),
       (5, 3, true);

INSERT INTO image_file(url)
VALUES ('https://img6.yna.co.kr/etc/inner/KR/2017/10/02/AKR20171002029400064_02_i_P2.jpg'),
       ('https://img7.yna.co.kr/etc/inner/KR/2017/10/02/AKR20171002029400064_03_i_P2.jpg'),
       ('https://www.lottehotel.com/content/dam/lotte-hotel/lotte/world/promotion/package/3000-768-pkg-LTWO.jpg.thumb.768.768.jpg'),
       ('https://www.qplace.kr/content/images/2021/09/9-14.jpg'),
       ('https://pds.joongang.co.kr//news/component/htmlphoto_mmdata/201710/02/181442ff-591a-4b6e-ae80-cd274e79d46a.jpg'),
       ('https://cdn.imweb.me/upload/S201807095b42ebdeddc68/717f268c7e38a.jpeg'),
       ('https://img1.daumcdn.net/thumb/R1280x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/STq/image/B2Ckxdgq1XokNonGAz-eLC56fuo'),
       ('http://petbrothers24.com/file_data/losati1/2017/12/28/ba66c9c38fb71ebfe2f21d075385e15a.jpg'),
       ('https://image.kmib.co.kr/online_image/2019/0830/611718110013659911_2.jpg'),
       ('https://i.pinimg.com/550x/ea/42/f8/ea42f872abf4e8683e0cf40de1994977.jpg'),
       ('http://photo.sentv.co.kr/photo/2020/01/17/20200117041544.jpg'),
       ('https://tgzzmmgvheix1905536.cdn.ntruss.com/2020/11/8ee176f1f86546fc99aeb84ba7c6745f'),
       ('https://www.qplace.kr/content/images/2021/09/5-15.jpg'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRBooz9qPdjrW8Yp6iVaZpswmPoRFuYkGzqA&usqp=CAU'),
       ('https://naverbooking-phinf.pstatic.net/20181210_34/15444112890520VPqx_JPEG/DSC_4471.JPG?type=f804_408_60_sharpen'),
       ('https://www.qplace.kr/content/images/2021/09/6-14.jpg'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2T-U71ReouvvXgVu2MIlwVwigXQGTtbLoTw&usqp=CAU'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR0MEdCRbNCCKiEgSSna9rwwvd8QFZcG2OBww&usqp=CAU'),
       ('https://cdn.imweb.me/upload/S2020070711adc3d6c6f4e/567543569755f.jpeg'),
       ('https://blog.kakaocdn.net/dn/bNQrlc/btqwYTX1W7o/6AftDEGwp7WSp8TksxBKMk/img.jpg'),
       ('https://lh3.googleusercontent.com/4ks2FmzJ-vluww6OPGIAa_9ORVjJ67pZbBWpGRpV8cvd1bNY7jRDVvklfNSh5QpOPKl2dDNwPPG3tSj9EncWjAlZupH_24MXYOLhQBwB=w650-h370-n'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7KQ9IbJMaQKTworb3RMmLHgUzN7sHkBycxA&usqp=CAU'),
       ('https://mblogthumb-phinf.pstatic.net/MjAyMDA4MjRfMTM2/MDAxNTk4MjAwNTY5NDQ3.RbVZkWmr8ZfQT_K4hyU-TBPyBJm8G2PI6-Ipdoht-isg.o05U93lp0n2I0byF02Sc-Tj2AFqxoYyCu1WDm49po-cg.JPEG.royalpets/1598200568452.jpg?type=w800'),
       ('https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/201902/03/ab9ab86b-61b0-4f92-b8b9-6904b7339636.jpg'),
       ('https://lh5.googleusercontent.com/p/AF1QipOsyoOR75Bm1OvY7GF2zAi_LO8GZgQys3IlnPx7'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRK34odDjbPnQgEhk2ctOCjBRAy4P_P7fZElw&usqp=CAU'),
       ('http://appdata.hungryapp.co.kr/images/hatdog/ar/2020_07/M15945729457998887.jpg'),
       ('https://blog.kakaocdn.net/dn/bBesqC/btqEXIFMDxy/Lh5OKYIT7z4DaZeaVWcaLk/img.jpg'),
       ('http://www.petissue.co.kr/news/data/20171207/p179521688418497_309.jpg'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_H3Nh_VUFYrsoD6R-FQvbiTN3pRQqNwxNrQuLlLH7PK8X3FawtXhMjkvGP1rIeNl2Dtw&usqp=CAU'),
       ('https://blog.kakaocdn.net/dn/bOaO9p/btqwZZQXPuv/nmN2P4LRxuLeATn46jkCC1/img.jpg'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGFX7NmvvxOvnyjmUjpiQsbsbCBvvCSkbvCg&usqp=CAU'),
       ('https://cdn.imweb.me/upload/S201807095b42ebdeddc68/f0a316fa03c8e.jpg'),
       ('https://www.kdfnews.com/news/photo/202002/42540_23972_424.jpg'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLUXBGlCnlnGQWUa1TRSX4wWd4wFGy9fm0ig&usqp=CAU'),
       ('https://blog.kakaocdn.net/dn/uZBhs/btqw1ubtTrJ/wKALvOwmfv0J3M81QfHla1/img.jpg'),
       ('https://blog.kakaocdn.net/dn/dor0aL/btqwYTjl2t5/QWLOowW9nvGQy6K44xj9IK/img.jpg'),
       ('https://www.qplace.kr/content/images/2021/08/11-6.jpg'),
       ('https://t1.daumcdn.net/thumb/R720x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/1fD8/image/EbAZB7HONm_t0Qb5L5IZ-fbSEJA.jpg'),
       ('https://www.qplace.kr/content/images/2021/08/11-6.jpg'),
       ('http://storage.enuri.info/pic_upload/knowbox2/202012/11323871720201211ca93c465-2b8f-4dc3-a6c9-6e70ab420d6f.jpg'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrMgpFcK6p4kdgHaHYEzjEwosIDSQISGt_TQ&usqp=CAU'),
       ('https://cdn.imweb.me/upload/S20190722adb7f12fdba1a/53bc531f45f9d.jpg'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuXcSqg7mIJmWNdXFW93NKjccrYwPvrYK7qw&usqp=CAU'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR36uqBipnBzIv3m2KEDJnLnY_Nr-iOyYbAMg&usqp=CAU'),
       ('https://www.ddpet.kr/wp-content/uploads/2022/01/2-1.png'),
       ('https://i.pinimg.com/736x/bc/16/7a/bc167ac1424173da74816eb6d773e386.jpg'),
       ('https://www.qplace.kr/data/portfolio/2589/08c742e102dbb7ffdf2f354bf637692c_w800.jpg'),
       ('http://www.newsworker.co.kr/news/photo/201909/43718_43630_3453.jpg'),
       ('https://www.qplace.kr/data/portfolio/2589/259c583958190fdfe0ddbb661d618ca3_w800.jpg'),
       ('https://www.qplace.kr/data/portfolio/3032/e12534fbc1a91ad6b660e86405bb342e_c800x800.jpg'),
       ('https://krsc.kr/program/operation/mainupload/1652086851_6278d8435e9db.jpg'),
       ('https://naverbooking-phinf.pstatic.net/20191126_69/1574738569126Hfapm_JPEG/DSC09632.JPG?type=f804_408_60_sharpen'),
       ('https://img1.daumcdn.net/thumb/S1200x630/?fname=https://t1.daumcdn.net/news/201701/26/akn/20170126173027838rrjm.jpg'),
       ('https://www.localnaeil.com/FileData/UserFiles/Image/News/%EB%A7%88%EC%9D%B4%EC%BA%A3%EB%A9%94%EC%9D%B8.jpg'),
       ('https://img.gokorea.kr/news/photo/201909/417139_1009305_3651.jpg'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2B-7vJvbTITA1-KwJ8iE_vVhemqdQzI76pm5sWzzWjvCiwiskZDwAqh0eE6PEcja3ZBw&usqp=CAU'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr1nx8eTZdNH3jZQinU6Z23OBI1KFaa5yqxsMm5dd6jnwQV4_KuCXVdfPE5RUcR3Gjx0k&usqp=CAU'),
       ('https://www.qplace.kr/content/images/2021/08/1-16.jpg'),
       ('https://cf.zipdoc.co.kr/static/product/8740/20220621113155455_c3s5JigDd8.png'),
       ('https://post-phinf.pstatic.net/MjAxOTA5MThfMjYw/MDAxNTY4NzczOTYyNzM4.8M_41YA14BaW8PJeAhbXDHepIZPTfMXMFyNy3W7Czw0g.D5epivMqi-IZjm4oVWDwGOf_V4WUoZIYxcJXx4hV3nIg.JPEG/image_7182764971568773937769.jpg?type=w1200'),
       ('https://s3.ap-northeast-2.amazonaws.com/event-localnaeil/FileData/Article/202205/d07138cb-6a62-447e-9fe7-bf34926a025f.jpg'),
       ('https://mblogthumb-phinf.pstatic.net/MjAxNzA1MTNfODMg/MDAxNDk0NjU0NTY5MzE0.wXoU4C495-3k9Z_sBvcMk1YlIFSqJIDWwwMmEVqyLBMg.Nixm2I6aJ6-RmICC4GaEsvy6Cf5_M8iDrQvcEVZ0W3Qg.JPEG.ztoe12/%EC%95%A0%EA%B2%AC%ED%98%B8%ED%85%94234.jpg?type=w2'),
       ('http://appdata.hungryapp.co.kr/images/hatdog/ar/2022_04/M164913526586145222.jpg'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjCMSpm3cLzZ3sRdDVMpNvjgl2S8WpHEWaBw&usqp=CAU'),
       ('https://maripetcare.files.wordpress.com/2021/06/hotel.jpg?w=1024'),
       ('https://t3.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/2UUt/image/wsAmky1R50bUWWAs86FBh1oVYyg'),
       ('https://mblogthumb-phinf.pstatic.net/MjAxOTA0MjFfMTUx/MDAxNTU1ODUzMzU4NzM1.1-0DMeJZe55dxdMeO77O8F-rheKsykPxknA3O-q-0Osg.O3GjwdzrhpawFkt8YBZLL-h0SWdINACv4LnGPFFc2pkg.JPEG.pet-t/%EC%95%A0%EA%B2%AC%ED%98%B8%ED%85%944-1.jpg?type=w800'),
       ('https://www.qplace.kr/data/portfolio/2589/259c583958190fdfe0ddbb661d618ca3_w800.jpg'),
       ('https://www.qplace.kr/data/portfolio/3032/e12534fbc1a91ad6b660e86405bb342e_c800x800.jpg'),
       ('https://krsc.kr/program/operation/mainupload/1652086851_6278d8435e9db.jpg'),
       ('https://naverbooking-phinf.pstatic.net/20191126_69/1574738569126Hfapm_JPEG/DSC09632.JPG?type=f804_408_60_sharpen'),
       ('https://img1.daumcdn.net/thumb/S1200x630/?fname=https://t1.daumcdn.net/news/201701/26/akn/20170126173027838rrjm.jpg'),
       ('https://www.localnaeil.com/FileData/UserFiles/Image/News/%EB%A7%88%EC%9D%B4%EC%BA%A3%EB%A9%94%EC%9D%B8.jpg'),
       ('https://img.gokorea.kr/news/photo/201909/417139_1009305_3651.jpg'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2B-7vJvbTITA1-KwJ8iE_vVhemqdQzI76pm5sWzzWjvCiwiskZDwAqh0eE6PEcja3ZBw&usqp=CAU'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr1nx8eTZdNH3jZQinU6Z23OBI1KFaa5yqxsMm5dd6jnwQV4_KuCXVdfPE5RUcR3Gjx0k&usqp=CAU'),
       ('https://www.qplace.kr/content/images/2021/08/1-16.jpg'),
       ('https://cf.zipdoc.co.kr/static/product/8740/20220621113155455_c3s5JigDd8.png'),
       ('https://post-phinf.pstatic.net/MjAxOTA5MThfMjYw/MDAxNTY4NzczOTYyNzM4.8M_41YA14BaW8PJeAhbXDHepIZPTfMXMFyNy3W7Czw0g.D5epivMqi-IZjm4oVWDwGOf_V4WUoZIYxcJXx4hV3nIg.JPEG/image_7182764971568773937769.jpg?type=w1200'),
       ('https://s3.ap-northeast-2.amazonaws.com/event-localnaeil/FileData/Article/202205/d07138cb-6a62-447e-9fe7-bf34926a025f.jpg'),
       ('https://mblogthumb-phinf.pstatic.net/MjAxNzA1MTNfODMg/MDAxNDk0NjU0NTY5MzE0.wXoU4C495-3k9Z_sBvcMk1YlIFSqJIDWwwMmEVqyLBMg.Nixm2I6aJ6-RmICC4GaEsvy6Cf5_M8iDrQvcEVZ0W3Qg.JPEG.ztoe12/%EC%95%A0%EA%B2%AC%ED%98%B8%ED%85%94234.jpg?type=w2'),
       ('http://appdata.hungryapp.co.kr/images/hatdog/ar/2022_04/M164913526586145222.jpg'),
       ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjCMSpm3cLzZ3sRdDVMpNvjgl2S8WpHEWaBw&usqp=CAU'),
       ('https://maripetcare.files.wordpress.com/2021/06/hotel.jpg?w=1024'),
       ('https://t3.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/2UUt/image/wsAmky1R50bUWWAs86FBh1oVYyg');

INSERT INTO file(accommodation_id, room_id, image_file_id)
VALUES (1, null, 1),
       (1, 1, 2),
       (1, 2, 3),
       (1, 3, 4),
       (2, null, 5),
       (2, 4, 6),
       (2, 5, 7),
       (2, 6, 8),
       (3, null, 9),
       (3, 7, 10),
       (3, 8, 11),
       (3, 9, 12),
       (4, null, 13),
       (4, 10, 14),
       (4, 11, 15),
       (4, 12, 16),
       (5, null, 17),
       (5, 13, 18),
       (5, 14, 19),
       (5, 15, 20),
       (6, null, 21),
       (6, 16, 22),
       (6, 17, 23),
       (6, 18, 24),
       (7, null, 25),
       (7, 19, 26),
       (7, 20, 27),
       (7, 21, 28),
       (8, null, 29),
       (8, 22, 30),
       (8, 23, 31),
       (8, 24, 32),
       (9, null, 33),
       (9, 25, 34),
       (9, 26, 35),
       (9, 27, 36),
       (10, null, 37),
       (10, 28, 38),
       (10, 29, 39),
       (10, 30, 40),
       (1, null, 41),
       (1, 1, 42),
       (1, 2, 43),
       (1, 3, 44),
       (2, null, 45),
       (2, 4, 46),
       (2, 5, 47),
       (2, 6, 48),
       (3, null, 49),
       (3, 7,50),
       (3, 8, 51),
       (3, 9, 52),
       (4, null, 53),
       (4, 10, 54),
       (4, 11, 55),
       (4, 12, 56),
       (5, null, 57),
       (5, 13, 58),
       (5, 14, 59),
       (5, 15, 60),
       (6, null, 61),
       (6, 16, 62),
       (6, 17, 63),
       (6, 18, 64),
       (7, null, 65),
       (7, 19, 66),
       (7, 20, 67),
       (7, 21, 68),
       (8, null, 69),
       (8, 22, 70),
       (8, 23, 71),
       (8, 24, 72),
       (9, null, 73),
       (9, 25, 74),
       (9, 26, 75),
       (9, 27, 76),
       (10, null, 77),
       (10, 28, 78),
       (10, 29, 79),
       (10, 30, 80);
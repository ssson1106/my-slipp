INSERT INTO USER (USER_ID, PASSWORD, NAME, EMAIL, create_date, modified_date) VALUES ('admin', '1', '관리자', 'admin@naver.com',sysdate, sysdate);
INSERT INTO USER (USER_ID, PASSWORD, NAME, EMAIL,create_date, modified_date) VALUES ('user', '1', '유저', 'user@naver.com', sysdate, sysdate);

INSERT INTO QUESTION (writer_id, title, contents, create_date, count_Of_Answers, modified_date) values (1, '미리 입력한거', 'ㅎㅎㅎㅎㅎ ^^ ;;', sysdate, 0, sysdate);

INSERT INTO QUESTION (writer_id, title, contents, create_date, count_Of_Answers, modified_date) values (1, '미리 입력한거22', 'ㅎㅎㅎㅎㅎ 22222222^^ ;;', sysdate, 0, sysdate);

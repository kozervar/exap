-- You can use this file to load seed data into the database using SQL statements
insert into Registrant(id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212') 
insert into Registrant(id, name, email, phone_number) values (1, 'Andrew Slint', 'andrew32@mailinator.com', '4561247864') 

INSERT INTO exam_type (id, description, name) VALUES (1, 'Egzamin typu OTWARTY', 'OPEN');
INSERT INTO exam_type (id, description, name) VALUES (2, 'Egzamin typu ZAMKNIĘTY', 'CLOSED');

INSERT INTO question_type (id, description, name) VALUES (1, 'Pytanie typu OTWARTE', 'OPEN');
INSERT INTO question_type (id, description, name) VALUES (2, 'Pytanie typu ZAMKNIĘTE - jedna odpowiedz', 'CLOSED-SINGLE');
INSERT INTO question_type (id, description, name) VALUES (3, 'Pytanie typu ZAMKNIĘTE - wiele odpowiedzi', 'CLOSED-MULTI');

INSERT INTO tag (id, name) VALUES (1, 'C++');
INSERT INTO tag (id, name) VALUES (2, 'java');
INSERT INTO tag (id, name) VALUES (3, 'c#');
INSERT INTO tag (id, name) VALUES (4, 'scala');
INSERT INTO tag (id, name) VALUES (5, 'inne');
INSERT INTO tag (id, name) VALUES (6, 'otwarte');

INSERT INTO question (id, version, creationdate, creationuser, updatedate, updateuser, content, description, subject, total_score, question_answer_id, question_type_id) VALUES (4, 1, NULL, NULL, NULL, NULL, 'test', NULL, 'test', 1, NULL, 1);
INSERT INTO question (id, version, creationdate, creationuser, updatedate, updateuser, content, description, subject, total_score, question_answer_id, question_type_id) VALUES (6, 1, NULL, NULL, NULL, NULL, 'test1', 'test1', 'test1', 1, NULL, 1);

INSERT INTO question_tag (id, version, question_id, tag_id) VALUES (1, 1, 4, 1);
INSERT INTO question_tag (id, version, question_id, tag_id) VALUES (2, 1, 4, 2);
INSERT INTO question_tag (id, version, question_id, tag_id) VALUES (3, 1, 4, 6);
INSERT INTO question_tag (id, version, question_id, tag_id) VALUES (4, 1, 6, 3);
INSERT INTO question_tag (id, version, question_id, tag_id) VALUES (5, 1, 6, 4);



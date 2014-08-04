-- You can use this file to load seed data into the database using SQL statements
insert into Registrant(id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212') 
insert into Registrant(id, name, email, phone_number) values (1, 'Andrew Slint', 'andrew32@mailinator.com', '4561247864') 

INSERT INTO exam_type (id, description, name) VALUES (1, 'Egzamin typu OTWARTY', 'OTWARTY');
INSERT INTO exam_type (id, description, name) VALUES (2, 'Egzamin typu ZAMKNIĘTY', 'ZAMKNIĘTY');

INSERT INTO question_type (id, description, name) VALUES (1, 'Pytanie typu OTWARTE', 'OTWARTE');
INSERT INTO question_type (id, description, name) VALUES (2, 'Pytanie typu ZAMKNIĘTE', 'ZAMKNIĘTE');

INSERT INTO question_answer (id, version, creationdate, creationuser, updatedate, updateuser, binary_value, comment, long_text_value, short_text_value) VALUES (1, 1, NULL, NULL, NULL, NULL, 100, 'test', 'Sto', 'Sto');

INSERT INTO question_subject (id, version, creationdate, creationuser, updatedate, updateuser, content, total_score, question_type_id) VALUES (2, 1, NULL, NULL, NULL, NULL, 'TEST', 100, 1);

INSERT INTO question_header (id, version, creationdate, creationuser, updatedate, updateuser, description, score, question_answer_id, question_subject_id, question_type_id) VALUES (1, 3, NULL, NULL, NULL, NULL, 'TEST', 100, 1, 2, 1);

INSERT INTO question_detail (id, content, sort_order, question_header_id) VALUES (1, 'TEST DETAIL', NULL, 1);

INSERT INTO exam_paper (id, version, creationdate, creationuser, updatedate, updateuser, active, description, name, exam_type_id) VALUES (4, 1, NULL, NULL, NULL, NULL, true, 'test', 'test', 1);

INSERT INTO exam_paper_question_subject (id, sort_order, exam_paper_id, question_subject_id) VALUES (5, 1, 4, 2);

INSERT INTO submit_question_answer (id, version, creationdate, creationuser, updatedate, updateuser, binary_value, comment, long_text_value, short_text_value) VALUES (1, 1, NULL, NULL, NULL, NULL, 100, 'TESST', 'TESST', 'TESST');
INSERT INTO submit_question_answer (id, version, creationdate, creationuser, updatedate, updateuser, binary_value, comment, long_text_value, short_text_value) VALUES (3, 1, NULL, NULL, NULL, NULL, 200, 'YZY', 'YZY', 'YZY');

INSERT INTO submit_question_header (id, version, creationdate, creationuser, updatedate, updateuser, comment, obtained_score, review_date, submit_question_answer_id) VALUES (2, 3, NULL, NULL, NULL, NULL, 'test', 100, '1970-06-29 22:57:15.354', 3);

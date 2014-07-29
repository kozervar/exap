-- You can use this file to load seed data into the database using SQL statements
insert into Registrant(id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212') 
insert into Registrant(id, name, email, phone_number) values (1, 'Andrew Slint', 'andrew32@mailinator.com', '4561247864') 

INSERT INTO exam_type VALUES (1, 'Egzamin typu OTWARTY', 'OTWARTY');
INSERT INTO exam_type VALUES (2, 'Egzamin typu ZAMKNIĘTY', 'ZAMKNIĘTY');

INSERT INTO question_type VALUES (1, 'Pytanie typu OTWARTE', 'OTWARTE');
INSERT INTO question_type VALUES (2, 'Pytanie typu ZAMKNIĘTE', 'ZAMKNIĘTE');
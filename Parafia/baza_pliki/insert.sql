DROP SEQUENCE  seq_userr;
DROP SEQUENCE  seq_adress;
DROP SEQUENCE  seq_course;
DROP SEQUENCE  seq_event;
DROP SEQUENCE  seq_orderr;
DROP SEQUENCE  seq_actuals;



CREATE  SEQUENCE seq_userr MINVALUE 0 MAXVALUE 999999  START WITH 1 INCREMENT BY 1 ORDER;
CREATE  SEQUENCE seq_adress MINVALUE 0 MAXVALUE 999999  START WITH 1 INCREMENT BY 1 ORDER;
CREATE  SEQUENCE seq_course MINVALUE 0 MAXVALUE 999999  START WITH 2 INCREMENT BY 1 ORDER;
CREATE  SEQUENCE seq_event MINVALUE 0 MAXVALUE 999999  START WITH 1 INCREMENT BY 1 ORDER;
CREATE  SEQUENCE seq_orderr MINVALUE 0 MAXVALUE 999999  START WITH 1 INCREMENT BY 1 ORDER;
CREATE  SEQUENCE seq_actuals MINVALUE 0 MAXVALUE 999999  START WITH 1 INCREMENT BY 1 ORDER;




------------------------------DELETE-------------------------------------------
DELETE FROM Orderr;
DELETE FROM Parishioner;
DELETE FROM Priest;
DELETE FROM Event;
DELETE FROM Course;
DELETE FROM Adress;
DELETE FROM Userr;

------------------------------INSERT-------------------------------------------

------------Adress------------
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Warszawska','15/9','25-140');
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Szpitalna','33','25-073');
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Studencka','21c','25-110');
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Jagiellonska','1/19','25-062');
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Powstancow','48','25-001');

------------Course------------
INSERT INTO Course VALUES(1,null,null,null,null,null,null); --Specjalna dla ksiezy
INSERT INTO Course VALUES(seq_course.nextval,to_date('12/03/2011','mm/dd/yyyy'),null,null,null,null,null);
INSERT INTO Course VALUES(seq_course.nextval,to_date('12/03/2008','mm/dd/yyyy'),to_date('05/06/2009','mm/dd/yyyy'),null,null,null,null);
INSERT INTO Course VALUES(seq_course.nextval,to_date('12/03/2000','mm/dd/yyyy'),to_date('05/06/2001','mm/dd/yyyy'),to_date('05/06/2010','mm/dd/yyyy'),null,null,null);
INSERT INTO Course VALUES(seq_course.nextval,to_date('12/03/1991','mm/dd/yyyy'),to_date('05/08/1991','mm/dd/yyyy'),to_date('08/05/2000','mm/dd/yyyy'),to_date('11/18/2004','mm/dd/yyyy'),to_date('10/28/2012','mm/dd/yyyy'),null);


------------Userr------------
INSERT INTO Userr VALUES(seq_userr.nextval,'ania','an11',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'krzys','krz22',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'mario','ma33',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'tadek','ta44',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'pop_master','pop55',4,44);
INSERT INTO Userr VALUES(seq_userr.nextval,'pop2','pop66',3,33);

------------Event------------
--INSERT INTO Event VALUES(seq_event.nextval,'Chrzest','Przyjecie chrztu sw');
--INSERT INTO Event VALUES(seq_event.nextval,'Slub','Przyjecie slubu');
--INSERT INTO Event VALUES(seq_event.nextval,'Msza','Odprawienie mszy sw');
--INSERT INTO Event VALUES(seq_event.nextval,'Pogrzeb','Odprawienie pogrzebu');
--INSERT INTO Event VALUES(seq_event.nextval,'Gregorianka','Combo msza x30');
--INSERT INTO Event VALUES(seq_event.nextval,'Urlop','Karaiby dla ksiedza');

INSERT INTO Event VALUES(1,'Chrzest','Przyjecie chrztu sw');
INSERT INTO Event VALUES(2,'Przyjecie slubu','slub mariana');
INSERT INTO Event VALUES(3,'Msza','Odprawienie mszy sw');
INSERT INTO Event VALUES(4,'Pogrzeb','Odprawienie pogrzebu');
INSERT INTO Event VALUES(5,'Gregorianka','Combo msza x30');
INSERT INTO Event VALUES(6,'Urlop','Karaiby dla ksiedza');

------------Parishioner------------
INSERT INTO Parishioner VALUES('11031254796',2,1,1,'Anna','Ciosk');
INSERT INTO Parishioner VALUES('08031223846',3,2,2,'Krzysztof','Kowalski');
INSERT INTO Parishioner VALUES('00031268745',4,3,3,'Mariusz','Piotrowski');
INSERT INTO Parishioner VALUES('91031254875',5,4,4,'Tadeusz','Maliniak');

---PR--
INSERT INTO Parishioner VALUES('59040665847',1,5,5,'Kazimierz','Ksiazecki');
INSERT INTO Parishioner VALUES('69010566803',1,6,6,'Jan','Wojciechowski');
-------


------------Priest------------
INSERT INTO Priest VALUES('59040665847',5,5,'Kazimierz','Ksiazecki','Proboszcz',to_date('02/18/1995','mm/dd/yyyy'),to_date('01/05/1987','mm/dd/yyyy'));
INSERT INTO Priest VALUES('69010566803',6,5,'Jan','Wojciechowski','Wikary',to_date('09/25/2002','mm/dd/yyyy'),to_date('02/15/1995','mm/dd/yyyy'));

------------Orderr------------
INSERT INTO Orderr VALUES (seq_orderr.nextval,1,'59040665847','11031254796','Chcilem zamowic chrzest;p','NEW',to_date('07/15/2012','mm/dd/yyyy'),to_date('07/15/2012','mm/dd/yyyy'));
INSERT INTO Orderr VALUES (seq_orderr.nextval,3,'69010566803','00031268745','Chcilem zamowic msze','NEW',to_date('12/20/2012','mm/dd/yyyy'),to_date('12/20/2012','mm/dd/yyyy'));
INSERT INTO Orderr VALUES (seq_orderr.nextval,5,'59040665847','91031254875','Chcilem gregorianke','NEW',to_date('12/21/2012','mm/dd/yyyy'),to_date('12/21/2012','mm/dd/yyyy'));
INSERT INTO Orderr VALUES (seq_orderr.nextval,1,'59040665847','11031254796','Chcilem zamowic chrzest;p','NEW',to_date('07/15/2012 08:00:00','mm/dd/yyyy HH24:MI:SS'),to_date('07/15/2012','mm/dd/yyyy'));


------------Actuals-----------
INSERT INTO Actuals VALUES (seq_actuals.nextval,'59040665847','Start parafii','Dzis wystartowala nasza e parafia',to_date('09/25/2012','mm/dd/yyyy'));
INSERT INTO Actuals VALUES (seq_actuals.nextval,'59040665847','temat 1','Ogloszenie 1',to_date('12/20/2012','mm/dd/yyyy'));
INSERT INTO Actuals VALUES (seq_actuals.nextval,'59040665847','temat 2','Ogloszenie 2',to_date('01/01/2013','mm/dd/yyyy'));
INSERT INTO Actuals VALUES (seq_actuals.nextval,'59040665847','temat 3','Ogloszenie 3',to_date('06/01/2013','mm/dd/yyyy'));
commit;

/*
------------------------------INSERT-------------------------------------------

------------Adress------------
INSERT INTO Adress VALUES(1,'Kielce','Warszawska','15/9','25-140');
INSERT INTO Adress VALUES(2,'Morawica','Szpitalna','33','25-073');
INSERT INTO Adress VALUES(3,'Warszawa','Studencka','21c','25-110');
INSERT INTO Adress VALUES(4,'Kraków','Jagiellonska','1/19','25-062');
INSERT INTO Adress VALUES(5,'Gdynia','Powstancow','48','25-001');

------------Course------------
INSERT INTO Course VALUES(1,to_date('12/03/2011','mm/dd/yyyy'),null,null,null,null,null);
INSERT INTO Course VALUES(2,to_date('12/03/2008','mm/dd/yyyy'),to_date('05/06/2009','mm/dd/yyyy'),null,null,null,null);
INSERT INTO Course VALUES(3,to_date('12/03/2000','mm/dd/yyyy'),to_date('05/06/2001','mm/dd/yyyy'),to_date('05/06/2010','mm/dd/yyyy'),null,null,null);
INSERT INTO Course VALUES(4,to_date('12/03/1991','mm/dd/yyyy'),to_date('05/08/1991','mm/dd/yyyy'),to_date('08/05/2000','mm/dd/yyyy'),to_date('11/18/2004','mm/dd/yyyy'),to_date('10/28/2012','mm/dd/yyyy'),null);


------------Userr------------
INSERT INTO Userr VALUES(1,'ania','an11',0,10);
INSERT INTO Userr VALUES(2,'krzys','krz22',0,20);
INSERT INTO Userr VALUES(3,'mario','ma33',0,30);
INSERT INTO Userr VALUES(4,'tadek','ta44',0,40);
INSERT INTO Userr VALUES(5,'pop_master','pop55',2,50);
INSERT INTO Userr VALUES(6,'pop2','pop66',1,60);

------------Event------------
INSERT INTO Event VALUES(1,'Chrzest','Przyjecie chrztu sw');
INSERT INTO Event VALUES(2,'Slub','Przyjecie slubu');
INSERT INTO Event VALUES(3,'Msza','Odprawienie mszy sw');
INSERT INTO Event VALUES(4,'Pogrzeb','Odprawienie pogrzebu');
INSERT INTO Event VALUES(5,'Gregorianka','Combo msza x30');
INSERT INTO Event VALUES(6,'Urlop','Karaiby dla ksiedza');

------------Parishioner------------
INSERT INTO Parishioner VALUES('11031254796',1,1,1,'Anna','Ciosk');
INSERT INTO Parishioner VALUES('08031223846',2,2,2,'Krzysztof','Kowalski');
INSERT INTO Parishioner VALUES('00031268745',3,3,3,'Mariusz','Piotrowski');
INSERT INTO Parishioner VALUES('91031254875',4,4,4,'Tadeusz','Maliniak');


------------Priest------------
INSERT INTO Priest VALUES('59040665847',5,5,'Kazimierz','Ksiazecki','Proboszcz',to_date('02/18/1995','mm/dd/yyyy'),to_date('01/05/1987','mm/dd/yyyy'));
INSERT INTO Priest VALUES('69010566803',6,5,'Jan','Wojciechowski','Wikary',to_date('09/25/2002','mm/dd/yyyy'),to_date('02/15/1995','mm/dd/yyyy'));

------------Orderr------------
INSERT INTO Orderr VALUES (1,1,'59040665847','11031254796','Chcilem zamowic chrzest;p',1,to_date('07/15/2012','mm/dd/yyyy'),to_date('07/15/2012','mm/dd/yyyy'));
INSERT INTO Orderr VALUES (2,3,'69010566803','00031268745','Chcilem zamowic msze',1,to_date('12/20/2012','mm/dd/yyyy'),to_date('12/20/2012','mm/dd/yyyy'));
INSERT INTO Orderr VALUES (3,5,'59040665847','91031254875','Chcilem gregorianke',1,to_date('12/21/2012','mm/dd/yyyy'),to_date('12/21/2012','mm/dd/yyyy'));
*/

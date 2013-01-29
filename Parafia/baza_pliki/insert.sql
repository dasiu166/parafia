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
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Al. Legionow','18','25-026');
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Jesionowa','18','25-028');
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Pocieszka','18','25-031');
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Radiowa','18','25-056');
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Polna','18','25-753');
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Krakowska','18','25-573');
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Al. IX Wiekow','18','25-245');
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Targowa','18','25-786');
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Ogrodowa','18','25-960');
INSERT INTO Adress VALUES(seq_adress.nextval,'Kielce','Al. Legionow','18','25-260');

------------Course------------
INSERT INTO Course VALUES(1,null,null,null,null,null,null); 
--Specjalna dla ksiezy
INSERT INTO Course VALUES(seq_course.nextval,to_date('03/12/2011','mm/dd/yyyy'),null,null,null,null,null);
INSERT INTO Course VALUES(seq_course.nextval,to_date('11/12/2008','mm/dd/yyyy'),to_date('05/19/2009','mm/dd/yyyy'),null,null,null,null);
INSERT INTO Course VALUES(seq_course.nextval,to_date('02/12/2000','mm/dd/yyyy'),to_date('07/11/2001','mm/dd/yyyy'),to_date('05/06/2010','mm/dd/yyyy'),null,null,null);
INSERT INTO Course VALUES(seq_course.nextval,to_date('12/12/1991','mm/dd/yyyy'),to_date('08/02/1993','mm/dd/yyyy'),to_date('08/05/2000','mm/dd/yyyy'),to_date('11/18/2004','mm/dd/yyyy'),to_date('10/28/2012','mm/dd/yyyy'),null);

INSERT INTO Course VALUES(seq_course.nextval,to_date('07/11/1985','mm/dd/yyyy'),to_date('05/14/1986','mm/dd/yyyy'),to_date('05/05/1995','mm/dd/yyyy'),to_date('08/28/2001','mm/dd/yyyy'),null,null);
INSERT INTO Course VALUES(seq_course.nextval,to_date('03/12/1975','mm/dd/yyyy'),to_date('04/17/1976','mm/dd/yyyy'),to_date('06/09/1985','mm/dd/yyyy'),to_date('10/15/1991','mm/dd/yyyy'),to_date('09/18/2000','mm/dd/yyyy'),null);
INSERT INTO Course VALUES(seq_course.nextval,to_date('03/12/1965','mm/dd/yyyy'),to_date('02/23/1966','mm/dd/yyyy'),to_date('06/08/1975','mm/dd/yyyy'),to_date('09/13/1981','mm/dd/yyyy'),to_date('05/25/1995','mm/dd/yyyy'),null);
INSERT INTO Course VALUES(seq_course.nextval,to_date('03/12/1949','mm/dd/yyyy'),to_date('01/22/1950','mm/dd/yyyy'),to_date('07/07/1959','mm/dd/yyyy'),to_date('11/11/1965','mm/dd/yyyy'),null,null);
INSERT INTO Course VALUES(seq_course.nextval,to_date('07/11/1998','mm/dd/yyyy'),to_date('08/11/1999','mm/dd/yyyy'),to_date('04/01/2008','mm/dd/yyyy'),to_date('07/11/2012','mm/dd/yyyy'),null,null);
INSERT INTO Course VALUES(seq_course.nextval,to_date('03/12/1972','mm/dd/yyyy'),to_date('09/09/1973','mm/dd/yyyy'),to_date('05/03/1982','mm/dd/yyyy'),to_date('09/25/1988','mm/dd/yyyy'),to_date('02/21/1997','mm/dd/yyyy'),null);
INSERT INTO Course VALUES(seq_course.nextval,to_date('03/12/1968','mm/dd/yyyy'),to_date('10/02/1969','mm/dd/yyyy'),to_date('05/05/1978','mm/dd/yyyy'),to_date('11/28/1984','mm/dd/yyyy'),to_date('06/05/1989','mm/dd/yyyy'),null);
INSERT INTO Course VALUES(seq_course.nextval,to_date('03/14/1959','mm/dd/yyyy'),to_date('07/04/1960','mm/dd/yyyy'),to_date('06/06/1969','mm/dd/yyyy'),to_date('04/05/1975','mm/dd/yyyy'),to_date('08/10/1981','mm/dd/yyyy'),to_date('11/01/2008','mm/dd/yyyy'));


------------Userr------------
INSERT INTO Userr VALUES(seq_userr.nextval,'ania1','an111',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'krzys','krz22',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'mario','ma333',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'tadek','ta444',2,22);
--ksiadz
INSERT INTO Userr VALUES(seq_userr.nextval,'pop_master','pop55',4,44);
INSERT INTO Userr VALUES(seq_userr.nextval,'pop22','pop66',3,33);
INSERT INTO Userr VALUES(seq_userr.nextval,'pop33','pop77',3,33);
--/ksiadz

INSERT INTO Userr VALUES(seq_userr.nextval,'malgos','mal88',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'marek','mar99',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'julia','jul10',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'stas1','sta11',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'kacper','kac12',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'michal','mich13',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'szymon','szym14',2,22);
INSERT INTO Userr VALUES(seq_userr.nextval,'maja1','maj15',2,22);




------------Event------------
--INSERT INTO Event VALUES(seq_event.nextval,'Chrzest','Przyjecie chrztu sw');
--INSERT INTO Event VALUES(seq_event.nextval,'Slub','Przyjecie slubu');
--INSERT INTO Event VALUES(seq_event.nextval,'Msza','Odprawienie mszy sw');
--INSERT INTO Event VALUES(seq_event.nextval,'Pogrzeb','Odprawienie pogrzebu');
--INSERT INTO Event VALUES(seq_event.nextval,'Gregorianka','Combo msza x30');
--INSERT INTO Event VALUES(seq_event.nextval,'Urlop','Karaiby dla ksiedza');

INSERT INTO Event VALUES(1,'Chrzest','Przyjecie chrztu swietetgo - sakrament obmycia woda.');
INSERT INTO Event VALUES(2,'Sakrament mal¿eñstwa','Zwi¹zek mê¿czyzny i kobiety bêd¹cy sakramentalnym znakiem mi³oœci.');
INSERT INTO Event VALUES(3,'Msza œw.','Odprawienie mszy sw - porz¹dek celebracji liturgicznej bêd¹cej odniesieniem do Ostatniej Wieczerzy Jezusa.');
INSERT INTO Event VALUES(4,'Pogrzeb','Odprawienie pogrzebu - ogó³ obrzêdów towarzysz¹cych grzebaniu zw³ok zmar³ego');
INSERT INTO Event VALUES(5,'Gregorianka','Trzydzieœci mszy œwiêtych odprawianych codziennie przez kolejne dni w intencji jednej osoby zmar³ej');
--INSERT INTO Event VALUES(6,'Urlop','Wakacje dla ksiedza');


------------Parishioner------------
INSERT INTO Parishioner VALUES('11031254796',2,1,1,'Anna','Ciosk');
INSERT INTO Parishioner VALUES('08031223846',3,2,2,'Krzysztof','Kowalski');
INSERT INTO Parishioner VALUES('00031268745',4,3,3,'Mariusz','Piotrowski');
INSERT INTO Parishioner VALUES('91031254875',5,4,4,'Tadeusz','Maliniak');

---PR--
INSERT INTO Parishioner VALUES('59040665847',1,5,5,'Kazimierz','Ksiazecki');
INSERT INTO Parishioner VALUES('69010566803',1,6,5,'Jan','Wojciechowski');
INSERT INTO Parishioner VALUES('79020554812',1,7,5,'Wojciech','Drozdowicz');
-------
--dalsi parafianie
INSERT INTO Parishioner VALUES('85071154796',6,8,6,'Malgorzata','Grabowska');
INSERT INTO Parishioner VALUES('75031223456',7,9,7,'Marek','Klimek');
INSERT INTO Parishioner VALUES('65031264515',8,10,8,'Julia','Krawczyk');
INSERT INTO Parishioner VALUES('49031253245',9,11,9,'Stanislaw','Ostrowski');
INSERT INTO Parishioner VALUES('89071152579',10,12,10,'Kacper','Walczak');
INSERT INTO Parishioner VALUES('72031224521',11,13,11,'Michal','Glowacki');
INSERT INTO Parishioner VALUES('68031265647',12,14,12,'Szymon','Michalik');
INSERT INTO Parishioner VALUES('59031458745',13,15,13,'Maja','Adamczyk');


------------Priest------------
INSERT INTO Priest VALUES('59040665847',5,5,'Kazimierz','Ksiazecki','Proboszcz',to_date('02/18/1995','mm/dd/yyyy'),to_date('01/05/1987','mm/dd/yyyy'));
INSERT INTO Priest VALUES('69010566803',6,5,'Jan','Wojciechowski','Wikary',to_date('09/25/2002','mm/dd/yyyy'),to_date('02/15/1995','mm/dd/yyyy'));
INSERT INTO Priest VALUES('79020554812',7,5,'Wojciech','Drozdowicz','Wikary',to_date('04/05/2008','mm/dd/yyyy'),to_date('10/24/2001','mm/dd/yyyy'));

------------Orderr------------
INSERT INTO Orderr VALUES (seq_orderr.nextval,1,'59040665847','11031254796','Chrzest dla mojego syna','Nowy',to_date('07/15/2012 10:00:00','mm/dd/yyyy HH24:MI:SS'),to_date('07/15/2012','mm/dd/yyyy'));
INSERT INTO Orderr VALUES (seq_orderr.nextval,3,'69010566803','00031268745','Msza za zmarlych','Nowy',to_date('12/20/2012 12:00:00','mm/dd/yyyy HH24:MI:SS'),to_date('12/20/2012','mm/dd/yyyy'));
INSERT INTO Orderr VALUES (seq_orderr.nextval,5,'59040665847','91031254875','Gregorianka w intencji babci i dziadka','Nowy',to_date('12/21/2012 08:00:00','mm/dd/yyyy HH24:MI:SS'),to_date('12/21/2012','mm/dd/yyyy'));
INSERT INTO Orderr VALUES (seq_orderr.nextval,1,'59040665847','11031254796','Chrzest dla trzech córek','Nowy',to_date('07/15/2012 08:00:00','mm/dd/yyyy HH24:MI:SS'),to_date('07/15/2012','mm/dd/yyyy'));

INSERT INTO Orderr VALUES (seq_orderr.nextval,1,'79020554812','11031254796','Chrzest dla córki - wybrane imiê Magdalena','Nowy',to_date('07/15/2012 08:00:00','mm/dd/yyyy HH24:MI:SS'),to_date('07/15/2012','mm/dd/yyyy'));
INSERT INTO Orderr VALUES (seq_orderr.nextval,3,'69010566803','00031268745','Msza za œw. pamiêcie Jacka Dorote Trzaskalskich','Nowy',to_date('12/20/2012 08:00:00','mm/dd/yyyy HH24:MI:SS'),to_date('12/20/2012','mm/dd/yyyy'));
INSERT INTO Orderr VALUES (seq_orderr.nextval,5,'79020554812','91031254875','Gregorianka w intencji rodziców','Nowy',to_date('12/21/2012 16:00:00','mm/dd/yyyy HH24:MI:SS'),to_date('12/21/2012','mm/dd/yyyy'));
INSERT INTO Orderr VALUES (seq_orderr.nextval,1,'59040665847','11031254796','Chrzest dla syna - Stanis³aw','Nowy',to_date('07/15/2012 08:00:00','mm/dd/yyyy HH24:MI:SS'),to_date('07/15/2012','mm/dd/yyyy'));


------------Actuals-----------
INSERT INTO Actuals VALUES (seq_actuals.nextval,'59040665847','Start parafii','Dzis wystartowala nasza e parafia, serdecznie zapraszamy do korzystania z systemu.',to_date('09/25/2012','mm/dd/yyyy'));
INSERT INTO Actuals VALUES (seq_actuals.nextval,'79020554812','Mozna juz zakupiæ oplatek wigilijny','Informujemy, ¿e mo¿na juz zakupic oplatki wigilijne rozprowadzane prze Caritas Kielce, cena co laska, ale ks. Probosz bardzo prosi aby nie byla ni¿sza ni¿ 25zl.',to_date('12/22/2012','mm/dd/yyyy'));
INSERT INTO Actuals VALUES (seq_actuals.nextval,'69010566803','Tworzenie kont dla parafian','Ks. Proboszcz prosi wszyskich chêtnych do zalozenia konta w nowym sytemie, aby zglosili siê dnia 29.12.2012 w kancelarii parafialnej.',to_date('12/20/2012','mm/dd/yyyy'));
INSERT INTO Actuals VALUES (seq_actuals.nextval,'79020554812','Zmiana wizyty duszpasterkiej','Ze wzglêdu na chorobê ks. Wikariusza, jesteœmy zmuszeni do zmiany terminarza wizyty duszpasterkiej. Dokladne informajce pojawia sie wkrótce w naszym systemie.',to_date('01/14/2013','mm/dd/yyyy'));
INSERT INTO Actuals VALUES (seq_actuals.nextval,'59040665847','Spotkanie komunijne','Ks. Wikariusz zaprasza wszyskie dzieci klasy II na spotkanie komunijne wraz z rodzicami. Spotaknie odbêdzie siê po mszy sw. o godz 16 dnia 13.06.2013r.',to_date('06/01/2013','mm/dd/yyyy'));
INSERT INTO Actuals VALUES (seq_actuals.nextval,'79020554812','Pogrzeb œp. Ks. Drozdka','Z przykroœcia informujemy, ¿e dnia 02.01.2013 zmarl w wieku 89 lat wieloletni proboszcz naszej parafii ks. Jan Drozdek. Uroczystoœci pogrzebowe odbeda sie dnia 10.01.2013r.',to_date('01/06/2013','mm/dd/yyyy'));

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

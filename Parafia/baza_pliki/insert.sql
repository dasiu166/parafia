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
INSERT INTO Orderr VALUES (1,1,'59040665847','11031254796','Chcilem zamowic chrzest;p',to_date('07/15/2012','mm/dd/yyyy'),to_date('07/15/2012','mm/dd/yyyy'));
INSERT INTO Orderr VALUES (2,3,'69010566803','00031268745','Chcilem zamowic msze',to_date('12/20/2012','mm/dd/yyyy'),to_date('12/20/2012','mm/dd/yyyy'));
INSERT INTO Orderr VALUES (3,5,'59040665847','91031254875','Chcilem gregorianke',to_date('12/21/2012','mm/dd/yyyy'),to_date('12/21/2012','mm/dd/yyyy'));

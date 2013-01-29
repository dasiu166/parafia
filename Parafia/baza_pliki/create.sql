DROP TABLE Userr CASCADE CONSTRAINTS;
DROP TABLE Adress CASCADE CONSTRAINTS;
DROP TABLE Course CASCADE CONSTRAINTS;
DROP TABLE Event CASCADE CONSTRAINTS;
DROP TABLE Parishioner CASCADE CONSTRAINTS;
DROP TABLE Priest CASCADE CONSTRAINTS;
DROP TABLE Orderr CASCADE CONSTRAINTS;
DROP TABLE Actuals CASCADE CONSTRAINTS;


CREATE TABLE Adress (
  id_adress NUMBER CONSTRAINT adres_pk PRIMARY KEY,
  city VARCHAR2(30),
  street VARCHAR2(30),
  house_numb VARCHAR2(30),
  postcode VARCHAR2(30)
);

CREATE TABLE Userr (
  id_userr NUMBER CONSTRAINT userr_pk PRIMARY KEY,
  login VARCHAR2(30),
  password VARCHAR2(30),
  restrictions NUMBER,
  rank NUMBER
);

CREATE TABLE Course (
  id_course NUMBER CONSTRAINT course_pk PRIMARY KEY,
  birthday DATE,
  baptism DATE,
  communion DATE,
  confirmation DATE,
  marriage DATE,
  death DATE
);

CREATE TABLE Event (
  id_event NUMBER CONSTRAINT event_pk PRIMARY KEY,
  name VARCHAR2(30),
  describee VARCHAR2(300)
);

CREATE TABLE Parishioner (
  pesel  NUMBER(11) CONSTRAINT parishioner_pk PRIMARY KEY,
  id_course NUMBER,
  id_userr NUMBER,
  id_adress NUMBER,
  name VARCHAR2(30),
  surname VARCHAR(30),
  CONSTRAINT pa_ad_fk FOREIGN KEY (id_adress) REFERENCES Adress(id_adress),
  CONSTRAINT pa_co_fk FOREIGN KEY (id_course) REFERENCES Course(id_course),
  CONSTRAINT pa_us_fk FOREIGN KEY (id_userr) REFERENCES Userr(id_userr)
);

CREATE TABLE Priest (
  pesel  NUMBER(11) CONSTRAINT priest_pk PRIMARY KEY,
  id_userr NUMBER,
  id_adress NUMBER,
  name VARCHAR2(30),
  surname VARCHAR(30),
  position VARCHAR(30),
  beginWork DATE,
  holyOrders DATE,
  CONSTRAINT pr_us_fk1 FOREIGN KEY (id_userr) REFERENCES Userr(id_userr),
  CONSTRAINT pr_ad_fk2 FOREIGN KEY (id_adress) REFERENCES Adress(id_adress)
);

CREATE TABLE Orderr  (
  id_orderr  NUMBER CONSTRAINT orderr_pk PRIMARY KEY,
  id_event NUMBER,
  odprawiajacy_pesel NUMBER(11),
  zamawiajacy_pesel NUMBER(11),
  describe VARCHAR2(400),
  status VARCHAR2(15),
  beginD DATE,
  endD DATE,
  CONSTRAINT or_ev_fk1 FOREIGN KEY (id_event) REFERENCES Event(id_event),
  CONSTRAINT or_pr_fk2 FOREIGN KEY(odprawiajacy_pesel) REFERENCES Priest(pesel),
  CONSTRAINT or_pa_fk3 FOREIGN KEY(zamawiajacy_pesel) REFERENCES Parishioner(pesel)
);

CREATE TABLE Actuals(
  id_actuals NUMBER CONSTRAINT actuals_pk PRIMARY KEY,
  pesel_priest NUMBER(11),
  subject VARCHAR(100),
  describe VARCHAR(1200),
  add_date DATE,
  CONSTRAINT ac_pr_fk FOREIGN KEY (pesel_priest) REFERENCES Priest(pesel)
);





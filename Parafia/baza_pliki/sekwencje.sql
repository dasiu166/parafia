DROP SEQUENCE  seq_userr;
DROP SEQUENCE  seq_adress;
DROP SEQUENCE  seq_course;
DROP SEQUENCE  seq_event;
DROP SEQUENCE  seq_orderr;
DROP SEQUENCE  seq_actuals;


CREATE  SEQUENCE seq_userr MINVALUE 0 MAXVALUE 999999  START WITH 1 INCREMENT BY 1;
CREATE  SEQUENCE seq_adress MINVALUE 0 MAXVALUE 999999  START WITH 1 INCREMENT BY 1;
CREATE  SEQUENCE seq_course MINVALUE 0 MAXVALUE 999999  START WITH 1 INCREMENT BY 1;
CREATE  SEQUENCE seq_event MINVALUE 0 MAXVALUE 999999  START WITH 1 INCREMENT BY 1;
CREATE  SEQUENCE seq_orderr MINVALUE 0 MAXVALUE 999999  START WITH 1 INCREMENT BY 1;
CREATE  SEQUENCE seq_actuals MINVALUE 0 MAXVALUE 999999  START WITH 1 INCREMENT BY 1;


--SELECT seq_order.currval FROM dual;
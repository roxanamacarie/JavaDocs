CREATE USER RoxanaM IDENTIFIED BY RoxanaM; -- you should replace my user name with yours. “IDENTIFIED BY�? means the password.
GRANT CREATE SESSION TO RoxanaM; -- necessary for connecting to the database with your new user.
GRANT CREATE ANY INDEX TO RoxanaM; -- we use indexes for faster queries.
GRANT ALTER ANY INDEX TO RoxanaM;
GRANT DROP ANY INDEX TO RoxanaM;
GRANT CREATE ANY PROCEDURE TO RoxanaM; -- we will use them in following workshops.
GRANT ALTER ANY PROCEDURE TO RoxanaM;
GRANT DROP ANY PROCEDURE TO RoxanaM;
GRANT EXECUTE ANY PROCEDURE TO RoxanaM;
GRANT CREATE ANY SEQUENCE TO RoxanaM; -- we need sequences for inserting data.
GRANT ALTER ANY SEQUENCE TO RoxanaM;
GRANT DROP ANY SEQUENCE TO RoxanaM;
GRANT SELECT ANY SEQUENCE TO RoxanaM;
GRANT CREATE ANY TABLE TO RoxanaM;
GRANT ALTER ANY TABLE TO RoxanaM;
GRANT DELETE ANY TABLE TO RoxanaM;
GRANT DROP ANY TABLE TO RoxanaM;
GRANT INSERT ANY TABLE TO RoxanaM;
GRANT LOCK ANY TABLE TO RoxanaM;
GRANT SELECT ANY TABLE TO RoxanaM;
GRANT UPDATE ANY TABLE TO RoxanaM;
GRANT CREATE TABLESPACE TO RoxanaM;
GRANT ALTER TABLESPACE TO RoxanaM;
GRANT DROP TABLESPACE TO RoxanaM;
GRANT CREATE ANY VIEW TO RoxanaM;
GRANT DROP ANY VIEW TO RoxanaM;
GRANT UNDER ANY VIEW TO RoxanaM;
alter user RoxanaM quota 50m on system;

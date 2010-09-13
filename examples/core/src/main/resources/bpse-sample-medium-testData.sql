INSERT INTO users VALUES('test@qse','2fff0ef4536a16baf1e79a81d9b0fca6',false);
INSERT INTO authorities VALUES('test@qse','ROLE_USER');
INSERT INTO users VALUES('user@qse','a66fd7c7ca83651b4b8fe70ab732fc2c',true);
INSERT INTO authorities VALUES('user@qse','ROLE_USER');
INSERT INTO users VALUES('admin@qse','e0dd6bc7a0357fb0e7a257ffee1315a6',true);
INSERT INTO authorities VALUES('admin@qse','ROLE_ADMIN');

INSERT INTO student VALUES(42,'8027164','Alex','Schatten','test@qse');
INSERT INTO student VALUES(41,'1234027','Test','Student','user@qse');
INSERT INTO student VALUES(40,'0027226','Bender','Rodrigues','admin@qse');

INSERT INTO users VALUES('tutor@qse','admin',true);
INSERT INTO tutor VALUES(1,'8925164','Test','Tutor','tutor@qse');

INSERT INTO course VALUES(1,'EasyCourse',8.2);
INSERT INTO course VALUES(2,'SEPM',4.5);
INSERT INTO course VALUES(3,'SE',2.5);

INSERT INTO users VALUES('prof@qse','prof',true);
INSERT INTO professor VALUES(3,'188','Assi','1','prof@qse');

INSERT INTO course_professor VALUES(1, 3);
INSERT INTO course_professor VALUES(2, 3);

INSERT INTO exam VALUES(509,'2008-08-08 20:08:08','Frogger',2);
INSERT INTO student_exam VALUES(50, 42, 1, 509);

INSERT INTO users VALUES('user@student','a66fd7c7ca83651b4b8fe70ab732fc2c',false);
INSERT INTO authorities VALUES('user@student','ROLE_USER');

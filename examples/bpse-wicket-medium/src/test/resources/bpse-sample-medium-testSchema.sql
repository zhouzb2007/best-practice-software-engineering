CREATE table student (
	id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    matnr char(7) NOT NULL,
    firstname varchar(20) NOT NULL,
    lastname varchar(40) NOT NULL,
    email varchar(50) NOT NULL
);
CREATE table tutor (
	id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    matnr char(7) NOT NULL,
    firstname varchar(20) NOT NULL,
    lastname varchar(40) NOT NULL,
    email varchar(50) NOT NULL
);
CREATE table course (
	id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title varchar(80) NOT NULL,
    ects decimal NOT NULL,
);
CREATE table professor (
	id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	officenr varchar(80) NOT NULL,
	firstname varchar(20) NOT NULL,
    lastname varchar(40) NOT NULL,
    email varchar(50) NOT NULL,
);
CREATE table course_professor (
	courseId integer FOREIGN KEY REFERENCES course(id) ON DELETE CASCADE,
	professorId integer FOREIGN KEY REFERENCES professor(id) ON DELETE CASCADE
);
CREATE table exam (
	id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	exdate timestamp NOT NULL,
	location varchar(20) NOT NULL,
    courseId integer FOREIGN KEY REFERENCES course(id) ON DELETE CASCADE,
);
CREATE table student_exam (
	id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	studentId integer FOREIGN KEY REFERENCES student(id) ON DELETE CASCADE,
	tutorId integer FOREIGN KEY REFERENCES tutor(id) ON DELETE CASCADE,
	examId integer FOREIGN KEY REFERENCES exam(id) ON DELETE CASCADE
);
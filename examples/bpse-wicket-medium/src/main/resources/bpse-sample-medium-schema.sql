create table students (
	id int not null,
    matnr varchar(80) not null,
    firstname varchar(80) null,
    lastname varchar(80) null,
    email varchar(80) null,
    constraint pk_students primary key (id)
);
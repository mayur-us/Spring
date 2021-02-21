DROP TABLE IF EXISTS Person;
DROP TABLE IF EXISTS User;

CREATE TABLE Person (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  age VARCHAR(100) NOT NULL,
  favourite_colour VARCHAR(250) DEFAULT NULL
);	


insert into Person(first_name, last_name, age,favourite_colour)values('John','Keynes','29','Red');
insert into Person(first_name, last_name, age,favourite_colour)values('Joe','Blocks','30','Yellow');
insert into Person(first_name, last_name, age,favourite_colour)values('Mayur','Doshi','31','Green');
insert into Person(first_name, last_name, age,favourite_colour)values('EML','EBI','32','Orange');

CREATE TABLE User (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL
);
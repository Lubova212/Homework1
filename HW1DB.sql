CREATE TABLE users (
userID int NOT NULL AUTO_INCREMENT,
username varchar (30) NOT NULL,
password varchar (30) NOT NULL,
email varchar (20) NOT NULL,
name varchar (100) NOT NULL,
surname varchar (100) NOT NULL,
phoneNumber varchar (20) NOT NULL,
accountNumber varchar (21),
Balance float,
PRIMARY KEY(userID, username, accountNumber)
);

SELECT * FROM users;

DROP TABLE users; 
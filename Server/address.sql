CREATE SCHEMA ADMIN;

DROP TABLE Addresses;

CREATE TABLE Addresses
(
	AddressID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
	FirstName VARCHAR (15) NOT NULL,
	LastName VARCHAR (30) NOT NULL,
	Email VARCHAR (30) NOT NULL,
	PhoneNumber VARCHAR (15) NOT NULL
);
INSERT INTO Addresses (FirstName,LastName,Email,PhoneNumber)
	VALUES ('Sipho','Ncube','sncube@wigets.co.za','078 467 8526'),
	('Robin','Kelly','robin.kelly@java.com','011 768 9487');
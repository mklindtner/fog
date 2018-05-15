SET @@global.time_zone = '+01:00';
DROP DATABASE IF EXISTS `fogdb`;
CREATE DATABASE IF NOT EXISTS `fogdb`;

USE `fogdb`;


CREATE TABLE customers (
	`id` int(6) UNSIGNED auto_increment PRIMARY KEY,
	`username` varchar(30) NOT NULL UNIQUE,
	`password` int(6) NOT NULL,
        `phone` int(11),
	`reg_date` TIMESTAMP NOT NULL
);


CREATE TABLE roles (
	id int(6) UNSIGNED auto_increment PRIMARY KEY,
	role varchar(30) NOT NULL UNIQUE
);

CREATE TABLE employees(
     id int(6) UNSIGNED auto_increment PRIMARY KEY,
    `username` varchar(30) NOT NULL UNIQUE,
    `password` varchar(30) NOT NULL, 
    `phone` int unsigned,
    `role_id` int(6) UNSIGNED NOT NULL,
    `reg_date` TIMESTAMP NOT NULL,
     FOREIGN KEY (`role_id`) REFERENCES roles(id) 
);


CREATE TABLE materials ( 
    id int(6) unsigned auto_increment PRIMARY KEY,
    pricePrUnit int NOT NULL,
    description varchar(300) default "None" NOT NULL
);

create table orderLine (
    id int(6) unsigned auto_increment PRIMARY KEY,
    amount int NOT NULL,
    unit varchar(30) NOT NULL,
    description varchar(300),
    orderId int(6) unsigned,
    materialId int(6) unsigned, 
    foreign key(orderId) REFERENCES orders(id),
    foreign key(materialId) REFERENCES materials(id)
);

CREATE TABLE sheds (
    id int UNSIGNED NOT NULL auto_increment PRIMARY KEY,
    length int UNSIGNED NOT NULL,
    width int UNSIGNED NOT NULL,
    height int UNSIGNED NOT NULL, 
    hasFloor boolean
);


CREATE TABLE orders (
    id int(6) unsigned NOT NULL auto_increment PRIMARY KEY,
    height int unsigned NOT NULL, 
    width int unsigned NOT NULL,
    length int unsigned NOT NULL,    
    `status` enum('AVAILABLE', 'TAKEN', 'SEND', 'ACCEPTED'),    
    slope int unsigned NOT NULL,            
    price int,
    created_at TIMESTAMP,
    shedId int(6) unsigned,
    customerId int(6) unsigned NOT NULL,
    `employeeId` int(6) unsigned, 
    FOREIGN KEY (customerId) 
		REFERENCES customers(id) 
        ON DELETE CASCADE,
    FOREIGN KEY (shedId) 
		REFERENCES sheds(id) 
        ON DELETE CASCADE,
    FOREIGN KEY (`employeeId`) 
		REFERENCES employees(id)
        ON DELETE CASCADE
);


Insert Into roles(role) VALUES("CENTERCHEF");
Insert Into roles(role) VALUES("MATERIALEANSVARLIG");
Insert Into roles(role) VALUES("SALGSMEDARBEJDER");

Insert Into materials(description, pricePrUnit) VALUES ("25x200mm. trykimp. Brædt", 80);
Insert Into materials(description, pricePrUnit) VALUES ("25x125mm. trykimp. Brædt", 70);
Insert Into materials(description, pricePrUnit) VALUES ("38x73mm. Lægte ubh.", 100);
Insert Into materials(description, pricePrUnit) VALUES ("45x95mm. Reglar ubh.", 85);
Insert Into materials(description, pricePrUnit) VALUES ("45x195mm. spærtræ ubh.", 300);
Insert Into materials(description, pricePrUnit) VALUES ("97x97mm. trykimp. Stolpe", 65);
Insert Into materials(description, pricePrUnit) VALUES ("19x100mm. trykimp. Brædt", 120);
Insert Into materials(description, pricePrUnit) VALUES ("Plastmo Ecolite blåtonet", 70);
Insert Into materials(description, pricePrUnit) VALUES ("Plastmo bundskruer 200stk", 45);
Insert Into materials(description, pricePrUnit) VALUES ("hulbånd 1x20mm. 10mtr.", 25);
Insert Into materials(description, pricePrUnit) VALUES ("universal 190mm højre", 15);
Insert Into materials(description, pricePrUnit) VALUES ("universal 190mm venstre", 15);
Insert Into materials(description, pricePrUnit) VALUES ("4,5 x 60 mm. skruer 200stk", 30);
Insert Into materials(description, pricePrUnit) VALUES ("4,0 x 50 mm. beslagsskruer 250stk", 40);
Insert Into materials(description, pricePrUnit) VALUES ("bræddebolt 10 x 120 mm", 78);
Insert Into materials(description, pricePrUnit) VALUES ("firkantskiver 40x40x11mm", 95);
Insert Into materials(description, pricePrUnit) VALUES ("4,5 x 70 mm. skruer 400 stk", 50);
Insert Into materials(description, pricePrUnit) VALUES ("4,5 x 50 mm. skruer 300 stk", 65);
Insert Into materials(description, pricePrUnit) VALUES ("stalddørsgreb 50x75", 45);
Insert Into materials(description, pricePrUnit) VALUES ("t hængsel 390 mm", 200);
Insert Into materials(description, pricePrUnit) VALUES ("vinkelbeslag 35", 280);



Insert Into employees(username, password, phone, role_id, reg_date) VALUES("emp@compnayEmail.com", "123", 1234567, 3, now());
Insert into employees(username, password, phone, role_id, reg_date) VALUES ("empTest", "123", 123456, 3, now());

Insert Into customers(username, password, reg_date, phone) VALUES ("customer", "123", now(), 123456);

Insert Into orders(height, width, `length`, slope, customerId, created_at, employeeId, `status`) VALUES (480, 670, 840, 45, 1, now(), 2, "PENDING"); 
Insert Into orders(height, width, `length`, slope, customerId, created_at, employeeId, `status`) VALUES (980, 780, 690, 45, 1, now(), 2, "PENDING"); 
Insert Into orders(height, width, `length`, slope, customerId, created_at, employeeId, `status`) VALUES (980, 670, 540, 45, 1, now(), 2, "PENDING"); 





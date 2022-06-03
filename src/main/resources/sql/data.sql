INSERT INTO hotels VALUES(1,'2022-04-10','2022-04-15','CH-0002',false,'Cataratas Hotel','Puerto Iguazú',6300.0,'Single')
INSERT INTO hotels VALUES(2,'2022-04-15','2022-04-20','CH-0003',false,'Cataratas Hotel','Puerto Iguazú',6300.0,'Single')
INSERT INTO hotels VALUES(3,'2022-02-10','2022-03-19','HB-001',false,'Hotel Bristol','Buenos Aires',5435.0,'Single')
INSERT INTO hotels VALUES(4,'2022-02-08','2022-04-17','BH-002',false,'Hotel Bristol 2','Buenos Aires',7200.0,'Doble')
INSERT INTO hotels VALUES(5,'2022-04-17','2022-05-23','SH-002',false,'Sheraton','Tucuman',5790.0,'Doble')

INSERT INTO users VALUES (1, "123","manager","team")
INSERT INTO users VALUES (2, "123","employee","teamemployee")

INSERT INTO clients VALUES (1, "Bernardo","Norberto","nor")
INSERT INTO clients VALUES (2, "Juan","Carlos","juan")
INSERT INTO clients VALUES (3, "Marcelo","Martin","mar")

INSERT INTO flights VALUES (1,"2022-02-10","2022-02-15","Pompeya","NUEVO-1233", "Lugano 1 y 2",2500,"Economy")
INSERT INTO payment_method VALUES (1,6,"34343434335","credit")
INSERT INTO flights_reservation VALUES (1,"2022-06-03","Pompeya","2022-02-10",1,"Lugano 1 y 2","2022-02-15","Economy",1,2750,"juan",2,1,1)

INSERT INTO touristic_package_discounts VALUES (1, 0.10)
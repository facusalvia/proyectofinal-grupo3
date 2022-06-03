INSERT INTO hotels VALUES(1,'2022-04-10','2022-04-15','CH-0002',false,'Cataratas Hotel','Puerto Iguazú',6300.0,'Single')
INSERT INTO hotels VALUES(2,'2022-04-15','2022-04-20','CH-0003',false,'Cataratas Hotel','Puerto Iguazú',6300.0,'Single')
INSERT INTO hotels VALUES(3,'2022-02-10','2022-03-19','HB-001',false,'Hotel Bristol','Buenos Aires',5435.0,'Single')
INSERT INTO hotels VALUES(4,'2022-02-08','2022-04-17','BH-002',false,'Hotel Bristol 2','Buenos Aires',7200.0,'Doble')
INSERT INTO hotels VALUES(5,'2022-04-17','2022-05-23','SH-002',false,'Hotel Tucuman','Tucuman',5790.0,'Doble')

INSERT INTO hotels VALUES(6,'2022-02-08','2022-04-17','BA-002',false,'Park Hyatt','Buenos Aires',17000.0,'Doble')
INSERT INTO hotels VALUES(7,'2022-04-17','2022-05-23','TU-002',false,'Sheraton','Tucuman',22000.0,'Doble')

INSERT INTO users VALUES (1, "123","manager","team")
INSERT INTO users VALUES (2, "123","employee","teamemployee")

INSERT INTO clients VALUES (1, "Bernardo","Norberto","nor")
INSERT INTO clients VALUES (2, "Juan","Carlos","juan")
INSERT INTO clients VALUES (3, "Marcelo","Martin","mar")



INSERT INTO flights VALUES(1,'2023-01-01','2023-01-15','Cancun','CANC-001','Buenos Aires',150000.0,'FirstClass')
INSERT INTO flights VALUES(2,'2023-01-01','2023-01-15','Cancun','CANC-002','Buenos Aires',90000.0,'Economy')
INSERT INTO flights VALUES(3,'2023-01-01','2023-01-15','Punta Cana','PUN-001','Buenos Aires',85000.0,'Economy')

INSERT INTO flights VALUES(4,'2023-01-15','2023-01-31','Costa Rica','COS-001','Buenos Aires',85000.0,'Economy')
INSERT INTO flights VALUES(5,'2023-01-15','2023-01-31','Costa Rica','COS-002','Buenos Aires',190000.0,'FirstClass')
INSERT INTO flights VALUES(6,'2023-01-15','2023-01-31','Rio de Janeiro','RIO-001','Buenos Aires',45000.0,'Economy')
INSERT INTO flights VALUES(7,'2023-01-15','2023-01-31','Lima','LIM-001','Buenos Aires',75000.0,'Economy')

INSERT INTO payment_method VALUES(1,6,'1234','credit')
INSERT INTO payment_method VALUES(2,6,'1234','credit')
INSERT INTO payment_method VALUES(3,6,'1234','credit')
INSERT INTO payment_method VALUES(4,6,'1234','credit')
INSERT INTO payment_method VALUES(5,6,'1234','credit')
INSERT INTO payment_method VALUES(6,6,'1234','credit')
INSERT INTO payment_method VALUES(7,6,'1234','credit')
INSERT INTO payment_method VALUES(8,6,'1234','credit')
INSERT INTO payment_method VALUES(9,6,'1234','credit')
INSERT INTO payment_method VALUES(10,6,'1234','credit')

INSERT INTO flights_reservation VALUES(1,'2022-02-10','2022-02-08','Tucuman','2023-05-15',false,'Buenos Aires','2023-05-20','Economy',1,1000.0,'juan',2,5,1)
INSERT INTO flights_reservation VALUES(7,'2022-02-10','2022-02-08','Tucuman','2023-05-15',false,'Buenos Aires','2023-05-20','Economy',1,1000.0,'juan',2,5,2)
INSERT INTO flights_reservation VALUES(3,'2022-05-10','2022-02-08','Tucuman','2023-05-15',false,'Buenos Aires','2023-05-20','Economy',1,1000.0,'juan',2,5,3)
INSERT INTO flights_reservation VALUES(5,'2022-07-10','2022-02-08','Tucuman','2023-05-15',false,'Buenos Aires','2023-05-20','Economy',1,1000.0,'juan',2,5,4)
INSERT INTO flights_reservation VALUES(4,'2022-08-10','2022-02-08','Tucuman','2023-05-15',false,'Buenos Aires','2023-05-20','Economy',1,1000.0,'juan',2,5,5)
INSERT INTO flights_reservation VALUES(6,'2022-08-10','2022-02-08','Tucuman','2023-05-15',false,'Buenos Aires','2023-05-20','Economy',1,1000.0,'juan',2,5,6)
INSERT INTO flights_reservation VALUES(2,'2022-08-10','2022-02-08','Tucuman','2023-05-15',false,'Buenos Aires','2023-05-20','Economy',1,1000.0,'juan',2,5,7)

INSERT INTO flights_reservation VALUES(8,'2021-06-10','2020-02-08','Tucuman','2023-05-15',false,'Buenos Aires','2023-05-20','Economy',1,1000.0,'juan',2,5,8)
INSERT INTO flights_reservation VALUES(10,'2021-06-10','2020-02-08','Tucuman','2023-05-15',false,'Buenos Aires','2023-05-20','Economy',1,1000.0,'juan',2,5,9)
INSERT INTO flights_reservation VALUES(9,'2021-12-10','2020-02-08','Tucuman','2023-05-15',false,'Buenos Aires','2023-05-20','Economy',1,1000.0,'juan',2,5,10)




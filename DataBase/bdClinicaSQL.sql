CREATE DATABASE `bdClinica`;

USE bdClinica;


CREATE TABLE `tipos_usuario`
(

`cod_Tipo` varchar(1) NOT NULL PRIMARY KEY,

`descripcion` varchar(20) NOT NULL,

INDEX(`cod_Tipo`)

);


CREATE TABLE `estados`
(

`cod_Estado` varchar(1) NOT NULL PRIMARY KEY,

`descripcion` varchar(20) NOT NULL,

INDEX(`cod_Estado`));



CREATE TABLE `especialidades`
(

`cod_Especialidad` varchar(10) NOT NULL PRIMARY KEY,

`descripcion` varchar(50) NOT NULL,

INDEX(`cod_Especialidad`)

);



CREATE TABLE `usuarios`
(

`dni` varchar(20) NOT NULL PRIMARY KEY,

`cod_Tipo` varchar(20) NOT NULL,

`localidad` varchar(45) NOT NULL,

`provincia` varchar(45) NOT NULL,

`nacionalidad` varchar(45) NOT NULL,

`sexo` varchar(1) NOT NULL,

`nombre` varchar(20) NOT NULL,

`apellido` varchar(20) NOT NULL,

`fecha_Nacimiento` varchar(10) NOT NULL,

`telefono` varchar(10) NOT NULL,

`mail` varchar(45) NOT NULL,

`direccion` varchar(45) NOT NULL,

`contra` varchar(45) NOT NULL,

`estado` boolean,

FOREIGN KEY(`cod_Tipo`) REFERENCES `tipos_usuario`(`cod_Tipo`),


INDEX(`Dni`));



CREATE TABLE `medicos`
(

`dni` varchar(20) NOT NULL PRIMARY KEY,

`cod_Especialidad` varchar(10) NOT NULL,

`dias_Atencion` varchar(45) NOT NULL,

`horarios_Atencion` varchar(45) NOT NULL,

FOREIGN KEY(`dni`) REFERENCES `usuarios`(`dni`),

FOREIGN KEY(`cod_Especialidad`) REFERENCES `especialidades`(`cod_Especialidad`)
);



CREATE TABLE `turnos`
(

`cod_Turno` MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

`dni_Medico` varchar(20) NOT NULL,

`dni_Paciente` varchar(20) NOT NULL,

`cod_Especialidad` varchar(10) NOT NULL,

`cod_Estado` varchar(1) NOT NULL,

`dia` varchar(20) NOT NULL,

`horario` varchar(20) NOT NULL,

FOREIGN KEY(`dni_Medico`) REFERENCES usuarios(`dni`),

FOREIGN KEY(`dni_Paciente`) REFERENCES usuarios(`dni`),

FOREIGN KEY(`cod_Especialidad`) REFERENCES especialidades(`cod_Especialidad`),


FOREIGN KEY(`cod_Estado`) REFERENCES estados(`cod_Estado`));

/* --------------------------INSERT TIPOS DE USUARIO---------------------------*/

INSERT INTO `bdClinica`.`tipos_usuario`(`cod_Tipo`, descripcion) VALUES('1','Administrador');
INSERT INTO `bdClinica`.`tipos_usuario`(`cod_Tipo`, descripcion) VALUES('2','Medico');
INSERT INTO `bdClinica`.`tipos_usuario`(`cod_Tipo`, descripcion) VALUES('3','Paciente');

/* --------------------------INSERT ESTADOS---------------------------*/

INSERT INTO `bdClinica`.`estados` (`cod_Estado`, `descripcion`) VALUES ('1', 'LIBRE');
INSERT INTO `bdClinica`.`estados` (`cod_Estado`, `descripcion`) VALUES ('2', 'OCUPADO');
INSERT INTO `bdClinica`.`estados` (`cod_Estado`, `descripcion`) VALUES ('3', 'AUSENTE');
INSERT INTO `bdClinica`.`estados` (`cod_Estado`, `descripcion`) VALUES ('4', 'PRESENTE');

/* --------------------------INSERT ESPECIALIDADES---------------------------*/
-- Especialidades clínicas	
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC1','Alergología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC2','Anestesiología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC3','Angiología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC4','Cardiología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC5','Endocrinología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC6','Estomatología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC7','Farmacología Clínica');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC8','Gastroenterología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC9','Genética');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC10','Geriatría');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC11','Hematología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC12','Hepatología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC13','Infectología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC14','Medicina aeroespacial');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC15','Medicina del deporte');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC16','Medicina familiar y comunitaria');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC17','Medicina física y rehabilitación');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC18','Medicina forense');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC19','Medicina intensiva');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC20','Medicina interna');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC21','Medicina preventiva y salud pública');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC22','Medicina del trabajo');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC23','Nefrología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC24','Neumología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC25','Neurología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC26','Nutriología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC27','Oncología médica');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC28','Oncología radioterápica');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC29','Pediatría');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC30','Psiquiatría');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC31','Reumatología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EC32','Toxicología');
-- Especialidades quirúrgicas
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EQ1','Cirugía cardíaca');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EQ2','Cirugía general');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EQ3','Cirugía oral y maxilofacial');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EQ4','Cirugía ortopédica');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EQ5','Cirugía pediátrica');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EQ6','Cirugía plástica');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EQ7','Cirugía torácica');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EQ8','Cirugía vascular');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EQ9','Neurocirugía');
-- Especialidades médico-quirúrgicas
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EMQ1','Dermatología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EMQ2','Ginecología y obstetricia o tocología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EMQ3','Medicina de emergencia');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EMQ4','Oftalmología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EMQ5','Otorrinolaringología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EMQ6','Traumatología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('EMQ7','Urología');
-- Especialidades de laboratorio o diagnósticas
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('ELD1','Análisis clínico');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('ELD2','Anatomía patológica');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('ELD3','Bioquímica clínica');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('ELD4','Farmacología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('ELD5','Genética médica');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('ELD6','Inmunología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('ELD7','Medicina nuclear');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('ELD8','Microbiología y parasitología');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('ELD9','Neurofisiología clínica');
INSERT INTO `bdClinica`.`especialidades`(`cod_Especialidad`, `descripcion`) VALUES ('ELD10','Radiología');


/* --------------------------INSERT USUARIOS---------------------------*/

/* --------------------------ADMINISTRADORES---------------------------*/
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('12345678','1','Escobar','Buenos Aires','Argentina','M','Jorge','Fernandez','2000-01-01','1111111111','jorgefernandez@gmail.com','calle123','admin','1');
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('30960319','1','San Miguel','Buenos Aires','Argentina','F','Julia','Ortiz','1990-05-20','4639432068','juliaortiz@gmail.com','Frias Pedro J 138','eVeNtIangHbE','1');
/* --------------------------MEDICOS---------------------------*/
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('54730908','2','General Las Heras','Buenos Aires','Argentina','M','Andres','Gonzales','1992-10-20','0956525823','andresGonzales@gmail.com','Av Córdoba 2525','mpLebLatErsd','1');
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('54109830','2','9 DE JULIO','Buenos Aires','Argentina','M','Horacio ','Martinez ','1980-02-15','5191258183','horaciomartinez@gmail.com','Tte Gral J D Perón 1647','LtoRItIcater','1');
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('28114697','2','ADROGUE','Buenos Aires','Argentina','F','Natalia','Gomez','1979-09-11','1225431272','nataliagomez@gmail.com','Av. Rafael Nuñez 3682','WeliAlthYdaZ','1');
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('98548789','2','ALFREDO DEMARCHI','Buenos Aires','Argentina','F','Mónica ','Sanchez','1992-07-27','2874356732','monicasanchez@gmail.com','Prof R C De Paula 690','coNgeRanTaCI','1');
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('29625135','2','ANTONIO CARBONI','Buenos Aires','Argentina','M','Ricardo ','Gonzales','1977-08-18','3318227519','ricardogonzales@gmail.com','Av Tomas Espora 2209','RiCKeNTiOcha','1');
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('29131943','2','ARBOLITO','Buenos Aires','Argentina','F','María','Benitez','1999-06-20','3426553776','mariabenitez@gmail.com','Valparaiso 922','roMaRFLuNGen','1');
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('02171025','2','WILDE','Buenos Aires','Argentina','M','Raúl','Herrera','1996-12-30','4000673857','raulherrera@gmail.com','Necochea 132','ulSeVemiCKeR','1');
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('94856829','2','VILLANUEVA','Buenos Aires','Argentina','F','Susana','Suarez','1979-03-05','7650301284','susanasuarez@gmail.com','Liniers 424','anDShaNTeRAC','1');
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('32944857','2','SAN EMILIO','Buenos Aires','Argentina','M','Cesar','Lopez','1991-08-23','0126503177','cesarlopez@gmail.com','Dragones 2250','RoliEFERvEnA','1');
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('52393500','2','RAMOS MEJIA','Buenos Aires','Argentina','F','Cintia','Sosa','1975-10-10','9000692108','cintiasosa@gmail.com','Ing. Marconi 686 8 B','infostionveR','1');
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('03293778','2','CHASCOMUS','Buenos Aires','Argentina','M','Martín','Medina','1983-06-09','9781966280','martinmedina@gmail.com','San Lorenzo 4499','NgeTRogiandE','1');
/* --------------------------PACIENTES---------------------------*/
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('50294588','3','CORONEL PRINGLES','Buenos Aires','Argentina','M','Joaquin','Lopez','1962-07-10','2441652471','lopezjoaquin3@gmail.com','Lavalle 1885','mAtINdeVEspI','1');
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('64860099','3','LAS MARIAS','Buenos Aires','Argentina','F','Norma','Torres','1970-08-13','2441652471','normatores@gmail.com','Santa Fe 155','rcHIalEcUmeN','1');
INSERT INTO `bdClinica`.`usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES ('59420840','3','Malvinas Argentinas','Buenos Aires','Argentina','F','Ana','Fernandez','1992-03-30','2441652471','anafernandez@gmail.com','España 782','tRerincentOc','1');

/* --------------------------INSERT MEDICOS---------------------------*/
INSERT INTO `bdClinica`.`medicos` (`dni`,`cod_Especialidad`,`dias_Atencion`,`horarios_Atencion`) VALUES ('54730908','EMQ6', 'Lunes','09:00 - 16:00');
INSERT INTO `bdClinica`.`medicos` (`dni`,`cod_Especialidad`,`dias_Atencion`,`horarios_Atencion`) VALUES ('54109830','EC13', 'Martes','07:00 - 15:00');
INSERT INTO `bdClinica`.`medicos` (`dni`,`cod_Especialidad`,`dias_Atencion`,`horarios_Atencion`) VALUES ('28114697','EQ4', 'Miercoles','08:00 - 17:00');
INSERT INTO `bdClinica`.`medicos` (`dni`,`cod_Especialidad`,`dias_Atencion`,`horarios_Atencion`) VALUES ('98548789','EMQ6', 'Jueves','10:00 - 18:00');
INSERT INTO `bdClinica`.`medicos` (`dni`,`cod_Especialidad`,`dias_Atencion`,`horarios_Atencion`) VALUES ('29625135','EMQ6', 'Viernes','11:00 - 19:00');
INSERT INTO `bdClinica`.`medicos` (`dni`,`cod_Especialidad`,`dias_Atencion`,`horarios_Atencion`) VALUES ('29131943','EMQ6', 'Sabado','07:00 - 13:00');
INSERT INTO `bdClinica`.`medicos` (`dni`,`cod_Especialidad`,`dias_Atencion`,`horarios_Atencion`) VALUES ('02171025','EQ4', 'Domingo','012:00 - 20:00');
INSERT INTO `bdClinica`.`medicos` (`dni`,`cod_Especialidad`,`dias_Atencion`,`horarios_Atencion`) VALUES ('94856829','EQ4', 'Lunes','06:00 - 10:00');
INSERT INTO `bdClinica`.`medicos` (`dni`,`cod_Especialidad`,`dias_Atencion`,`horarios_Atencion`) VALUES ('32944857','ELD10', 'Martes','13:00 - 21:00');
INSERT INTO `bdClinica`.`medicos` (`dni`,`cod_Especialidad`,`dias_Atencion`,`horarios_Atencion`) VALUES ('52393500','ELD10', 'Miercoles','10:00 - 14:00');
INSERT INTO `bdClinica`.`medicos` (`dni`,`cod_Especialidad`,`dias_Atencion`,`horarios_Atencion`) VALUES ('03293778','ELD10', 'Jueves','014:00 - 22:00');

/* --------------------------INSERT TURNOS---------------------------*/
INSERT INTO `bdClinica`.`turnos` (`dni_Medico`,`dni_Paciente`, `cod_Especialidad`, `cod_Estado`,`dia` , `horario`) VALUES ('54730908', '50294588', 'EMQ6' ,'2', 'Lunes', '10:00');
INSERT INTO `bdClinica`.`turnos` (`dni_Medico`,`dni_Paciente`, `cod_Especialidad`, `cod_Estado`,`dia` , `horario`) VALUES ('54730908', '59420840', 'EC13','2', 'Martes' , '13:00');
INSERT INTO `bdClinica`.`turnos` (`dni_Medico`,`dni_Paciente`, `cod_Especialidad`, `cod_Estado`,`dia` , `horario`) VALUES ('28114697', '50294588', 'EQ4' ,'2', 'Miercoles', '15:00');
INSERT INTO `bdClinica`.`turnos` (`dni_Medico`,`dni_Paciente`, `cod_Especialidad`, `cod_Estado`,`dia` , `horario`) VALUES ('32944857', '64860099', 'ELD10' ,'2', 'Martes', '20:00');
INSERT INTO `bdClinica`.`turnos` (`dni_Medico`,`dni_Paciente`, `cod_Especialidad`, `cod_Estado`,`dia` , `horario`) VALUES ('52393500', '59420840', 'ELD10' ,'2', 'Miercoles', '11:00');
INSERT INTO `bdClinica`.`turnos` (`dni_Medico`,`dni_Paciente`, `cod_Especialidad`, `cod_Estado`,`dia` , `horario`) VALUES ('29131943', '64860099', 'EMQ6' ,'2', 'Sabado', '09:00');
INSERT INTO `bdClinica`.`turnos` (`dni_Medico`,`dni_Paciente`, `cod_Especialidad`, `cod_Estado`,`dia` , `horario`) VALUES ('29625135', '50294588', 'EMQ6' ,'2', 'Viernes', '18:00');
/*Eliminar todo!*/
DROP ROLE ClinicaRol;
DROP ROLE Clientes;
DROP USER smiling;
DROP USER cliente;

DROP TRIGGER FechaNacimientoAnterior;
DROP TRIGGER FechaNacimientoAnteriorEmpl;
DROP PROCEDURE totalCosteEquipamiento;

DROP TABLE historiales_clientes cascade;
DROP TABLE empleados cascade;
DROP TABLE equipamiento cascade;
DROP TABLE habitaciones cascade;
DROP TABLE citas cascade;
DROP TABLE clinicas cascade;
DROP TABLE telefonos cascade;
DROP TABLE clientes cascade;
DROP TABLE puestos cascade;
DROP TABLESPACE ClinicaOdontologica;

/*Creando TableSpace, Rol, Usuarios*/
CREATE TABLESPACE ClinicaOdontologica Engine=InnoDB;

CREATE ROLE ClinicaRol;
CREATE ROLE Clientes;

CREATE USER 'smiling'@'%' IDENTIFIED BY 'smiling';
CREATE USER 'cliente'@'%' IDENTIFIED BY 'cliente';

GRANT ClinicaRol TO smiling;
GRANT Clientes TO cliente;

/*Asignando permisos, actualizando la zona horaria y aplicando los cambios*/
GRANT ALL PRIVILEGES ON smilingbbdd.* TO ClinicaRol WITH GRANT OPTION;
ALTER USER 'smiling' DEFAULT ROLE ClinicaRol;
ALTER USER cliente DEFAULT ROLE Clientes;

SET time_zone = '+02:00';
FLUSH PRIVILEGES;
COMMIT;

/*CREANDO LAS TABLAS*/
CREATE TABLE clientes (
  DNI varchar(9) NOT NULL,
  Nombre varchar(255) NOT NULL,
  Apellidos varchar(255) NOT NULL,
  Correo varchar(255) NOT NULL,
  Contraseña varchar(255) NOT NULL,
  Fecha_Nacimiento date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
tablespace ClinicaOdontologica;

CREATE TABLE clinicas (
  ID_Clinica int(11) NOT NULL,
  Nombre_Clinica varchar(255) NOT NULL,
  Direccion varchar(255) NOT NULL,
  Telefono int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
tablespace ClinicaOdontologica;

CREATE TABLE empleados (
  DNI_Emp varchar(9) NOT NULL,
  Nombre varchar(255) NOT NULL,
  Apellidos varchar(255) NOT NULL,
  Correo varchar(255) NOT NULL,
  Contraseña varchar(255) NOT NULL,
  Fecha_Nacimiento date NOT NULL,
  ID_Puesto int(3) NOT NULL,
  ID_Clinica int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
tablespace ClinicaOdontologica;

CREATE TABLE equipamiento (
  ID_Equipamiento int(11) NOT NULL,
  Nombre_Equipamiento varchar(255) NOT NULL,
  Precio float NOT NULL,
  Stock int(7) NOT NULL,
  ID_Clinica int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
tablespace ClinicaOdontologica;

CREATE TABLE habitaciones (
  ID_Habitacion int(11) NOT NULL,
  Num_Habitacion int(3) NOT NULL,
  Especialidad varchar(255) NOT NULL,
  ID_Clinica int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
tablespace ClinicaOdontologica;

CREATE TABLE historiales_clientes (
  DNI varchar(9) NOT NULL,
  Fecha_Revision date NOT NULL,
  Hora_Revision time NOT NULL,
  Observaciones varchar(255) NOT NULL,
  Atendido varchar(9) DEFAULT NULL,
  ID_Clinica int(11) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
tablespace ClinicaOdontologica;

CREATE TABLE puestos (
  ID_Puesto int(3) NOT NULL,
  Nombre_Puesto varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
tablespace ClinicaOdontologica;

CREATE TABLE citas (
  ID_Clinica int(11) NOT NULL,
  DNI_Cliente varchar(255) NOT NULL,
  Fecha_Cita date NOT NULL,
  Hora_Cita time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
tablespace ClinicaOdontologica;

CREATE TABLE telefonos (
  DNI varchar(9) NOT NULL,
  Telefono int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
tablespace ClinicaOdontologica;
COMMIT;

/*PERMISOS ROL DEL CLIENTE*/
GRANT INSERT, SELECT, UPDATE, ALTER ON smilingbbdd.clientes TO Clientes;
GRANT SELECT ON smilingbbdd.empleados TO Clientes;
GRANT SELECT ON smilingbbdd.clinicas TO Clientes;
GRANT SELECT ON smilingbbdd.historiales_clientes TO Clientes;
GRANT INSERT, SELECT, UPDATE, ALTER ON smilingbbdd.citas TO Clientes;
GRANT INSERT, SELECT, UPDATE, ALTER ON smilingbbdd.telefonos TO Clientes;

FLUSH PRIVILEGES;
COMMIT;

/*UNIQUES*/
ALTER TABLE clinicas ADD CONSTRAINT uniqueClinica UNIQUE (ID_Clinica);

ALTER TABLE equipamiento ADD CONSTRAINT uniqueEquipamiento UNIQUE (ID_Equipamiento);

ALTER TABLE habitaciones ADD CONSTRAINT uniqueHabitaciones UNIQUE (ID_Habitacion);

/*Creando los Primary Keys*/
ALTER TABLE clientes ADD CONSTRAINT `pk_clientes` PRIMARY KEY (`DNI`);

ALTER TABLE clinicas ADD CONSTRAINT `pk_clinicas` PRIMARY KEY (`Direccion`);

ALTER TABLE empleados ADD CONSTRAINT pk_empleados PRIMARY KEY (DNI_Emp);

ALTER TABLE equipamiento ADD CONSTRAINT pk_equipamiento PRIMARY KEY (Nombre_Equipamiento, ID_Clinica);

ALTER TABLE habitaciones ADD CONSTRAINT pk_habitaciones PRIMARY KEY (Num_Habitacion, ID_Clinica);

ALTER TABLE historiales_clientes ADD CONSTRAINT pk_historiales_clientes PRIMARY KEY (DNI, Atendido, Fecha_Revision, Hora_Revision);

ALTER TABLE puestos ADD CONSTRAINT pk_puestos PRIMARY KEY (ID_Puesto);

ALTER TABLE citas ADD CONSTRAINT pk_citas PRIMARY KEY (ID_Clinica, Fecha_Cita, Hora_Cita, DNI_Cliente);

ALTER TABLE telefonos ADD CONSTRAINT pk_telefonos PRIMARY KEY (DNI, Telefono);

COMMIT;
/*AUTO INCREMENTS*/
ALTER TABLE clinicas
  MODIFY ID_Clinica int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
  
ALTER TABLE equipamiento
  MODIFY ID_Equipamiento int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE habitaciones
  MODIFY ID_Habitacion int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
  
ALTER TABLE puestos
  MODIFY ID_Puesto int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
  
/*FOREIGN KEYS*/
ALTER TABLE habitaciones ADD CONSTRAINT fk_habitacionesClinica FOREIGN KEY (ID_Clinica) REFERENCES clinicas(ID_Clinica);

ALTER TABLE equipamiento ADD CONSTRAINT fk_equipamientoClinica FOREIGN KEY (ID_Clinica) REFERENCES clinicas(ID_Clinica);

ALTER TABLE empleados ADD CONSTRAINT fk_empleadosClinica FOREIGN KEY (ID_Clinica) REFERENCES clinicas(ID_Clinica);

ALTER TABLE empleados ADD CONSTRAINT fk_empleadosPuesto FOREIGN KEY (ID_Puesto) REFERENCES puestos(ID_Puesto);

ALTER TABLE citas ADD CONSTRAINT fk_citasClinica FOREIGN KEY (ID_Clinica) REFERENCES clinicas(ID_Clinica);

ALTER TABLE citas ADD CONSTRAINT fk_citasClientes FOREIGN KEY (DNI_Cliente) REFERENCES clientes(DNI);

ALTER TABLE historiales_clientes ADD CONSTRAINT fk_historialesClientes FOREIGN KEY (DNI) REFERENCES clientes(DNI);

ALTER TABLE historiales_clientes ADD CONSTRAINT fk_historialesEmpleados FOREIGN KEY (Atendido) REFERENCES empleados(DNI_Emp);

ALTER TABLE historiales_clientes ADD CONSTRAINT fk_historialesCitas FOREIGN KEY (ID_Clinica, Fecha_Revision, Hora_Revision) REFERENCES citas(ID_Clinica, Fecha_Cita, Hora_Cita);

ALTER TABLE telefonos ADD CONSTRAINT fk_telefonosClientes FOREIGN KEY (DNI) REFERENCES clientes (DNI);

/*Añadiendo Checks*/
ALTER TABLE equipamiento
ADD CONSTRAINT CHK_Equipamiento CHECK (Stock >=0 AND Precio >= 0); 

/*Añadiendo Triggers*/
DELIMITER //
CREATE TRIGGER FechaNacimientoAnterior
BEFORE INSERT ON clientes
FOR EACH ROW
BEGIN
  IF NEW.Fecha_Nacimiento >= CURDATE() THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La fecha de nacimiento debe ser anterior a hoy.';
  END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER FechaNacimientoAnteriorEmpl
BEFORE INSERT ON empleados
FOR EACH ROW
BEGIN
  IF NEW.Fecha_Nacimiento >= CURDATE() THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La fecha de nacimiento debe ser anterior a hoy.';
  END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER EvitarMultiCita
BEFORE INSERT ON citas
FOR EACH ROW
BEGIN
  IF NEW.Fecha_Cita IN(SELECT Fecha_Cita FROM citas) AND NEW.Hora_Cita IN(SELECT Hora_Cita FROM citas) AND NEW.DNI_Cliente IN(SELECT DNI_Cliente FROM citas WHERE Fecha_Cita = NEW.Fecha_Cita AND Hora_Cita = New.Hora_Cita) THEN
    SIGNAL SQLSTATE '45001' SET MESSAGE_TEXT = 'Esa cita ya esta reservada!';
  END IF;
END //
DELIMITER ;

/*Insertando ejemplos*/
INSERT INTO clinicas (ID_Clinica, Nombre_Clinica, Direccion, Telefono) VALUES
(1, 'EtxaiDent SM', 'Calle Mayor 789', '688718520'),
(2, 'CamaRisas SM', 'Avenida del Sol 456', '617616657');

INSERT INTO equipamiento (ID_Equipamiento, Nombre_Equipamiento, Precio, Stock, ID_Clinica) VALUES
(2, 'Cepillo de Dientes', 2.5, 32, 1),
(3, 'Invisalign', 1900.2, 5, 2),
(4, 'Paste de dientes', 3.99, 20, 2),
(10, 'Brackets metálicos', 1800, 100, 1),
(11, 'Lápiz de blanqueamiento dental', 15, 50, 1),
(12, 'Fresas dentales', 10, 200, 1),
(13, 'Cera para ortodoncia', 10, 100, 1),
(14, 'Ligadura elástica', 5, 500, 1),
(15, 'Brackets cerámicos', 1600, 50, 2),
(17, 'Arco de níquel-titanio', 25, 200, 2),
(18, 'Adhesivo para brackets', 40, 30, 2);

INSERT INTO clientes (DNI, Nombre, Apellidos, Correo, Contraseña, Fecha_Nacimiento) VALUES
('11111111A', 'Oier', 'Lertxundi', 'olertxundi@gmail.com', 'bb8a8b5f24a7e79bdc67e09538d97f6996c27961', '2004-06-09'),
('12345678A', 'Aitor', 'Etxeberria', 'aitoretxaide@plaiaundi.net', 'bb8a8b5f24a7e79bdc67e09538d97f6996c27961', '2008-10-12'),
('22222222A', 'Pepe', 'Perez', 'pperez@hotmail.com', 'bb8a8b5f24a7e79bdc67e09538d97f6996c27961', '2004-04-23'),
('77777777V', 'Miguel', 'Torres', 'migueltorres@gmail.com', 'bb8a8b5f24a7e79bdc67e09538d97f6996c27961', '2019-03-31');

INSERT INTO puestos (ID_Puesto, Nombre_Puesto) VALUES
(1, 'Director'),
(2, 'Odontólogo'),
(3, 'Auxiliar dental'),
(4, 'Recepcionista'),
(5, 'Higienista Dental'),
(6, 'Endodoncista');

INSERT INTO empleados (DNI_Emp, Nombre, Apellidos, Correo, Contraseña, Fecha_Nacimiento, ID_Puesto, ID_Clinica) VALUES
('12345678B', 'Julen', 'Cano', 'jcano@gmail.com', 'bb8a8b5f24a7e79bdc67e09538d97f6996c27961', '2004-11-17', 1, 1),
('12345678C', 'Arnold', 'Bermell', 'abermell@yahoo.com', 'bb8a8b5f24a7e79bdc67e09538d97f6996c27961', '2008-07-14', 2, 2);

INSERT INTO habitaciones (ID_Habitacion, Num_Habitacion, Especialidad, ID_Clinica) VALUES
(1, 1, 'Ortodoncias', 1),
(2, 2, 'Prueba', 1),
(3, 3, 'Limpiezas', 1),
(4, 4, 'Rayos-X', 1),
(5, 2, 'Rayos-X', 2),
(6, 1, 'Habitacion Nº1', 2);

INSERT INTO citas (ID_Clinica, DNI_Cliente, Fecha_Cita, Hora_Cita) VALUES
(1, '11111111A', '2023-04-22', '12:00:00'),
(1, '11111111A', '2023-05-02', '10:50:00'),
(1, '11111111A', '2023-10-15', '12:30:00'),
(1, '11111111A', '2023-05-14', '16:30:00'),
(1, '12345678A', '2023-04-19', '11:11:00'),
(1, '12345678A', '2023-04-24', '12:00:00'),
(1, '12345678A', '2023-10-15', '12:00:00'),
(1, '12345678A', '2023-10-15', '12:01:00'),
(1, '12345678A', '2023-10-15', '12:03:00'),
(2, '12345678A', '2023-05-03', '11:40:00'),
(2, '12345678A', '2023-10-15', '12:02:00'),
(2, '77777777V', '2023-04-15', '13:45:00'),
(1, '77777777V', '2023-04-12', '09:34:00');

INSERT INTO historiales_clientes (DNI, Fecha_Revision, Hora_Revision, Observaciones, Atendido, ID_Clinica) VALUES
('12345678A', '2023-04-19', '11:11:00', 'Se le ha roto el la paleta izquierda', '12345678C', '1'),
('12345678A', '2023-04-24', '12:00:00', 'Se le ha roto el la paleta derecha', '12345678C', '1'),
('11111111A', '2023-04-22', '12:00:00', 'Limpieza Dental', '12345678C', '1'),
('11111111A', '2023-05-02', '10:50:00', 'Tiene caries', '12345678B', '1'),
('12345678A', '2023-05-03', '11:40:00', 'Empaste en el colmillo', '12345678B', '2'),
('77777777V', '2023-04-15', '13:45:00', 'Se le ha puesto un empaste en la muela', '12345678C', '2'),
('77777777V', '2023-04-12', '09:34:00', 'Se le ha puesto un empaste en la paleta', '12345678B', '1');

INSERT INTO telefonos (DNI, Telefono) VALUES
('11111111A', 610951578),
('12345678A', 666666666),
('22222222A', 617658745),
('77777777V', 600000003);
COMMIT;

/*Creando Procedimientos*/

/*

--------PROCEDURE PARA ORACLE (sin probar, si sobra tiempo probar a pasar toda la bbdd a oracle y probar el procedimiento, 
		creemos que funciona ya que el de MySQL lo hemos hecho siguiendo la lógica de este porque al principio no sabiamos que MySQL tenia otro lenguaje)------------

CREATE OR REPLACE PROCEDURE totalCosteEquipamiento (ID_ClinicRecibido IN NUMBER) AS
    totalCosteEquip NUMBER := 0;
    CURSOR CursorEquipamiento IS SELECT precio FROM equipamiento;
    precioEquipamiento equipamiento.precio%TYPE;
BEGIN
    OPEN CursorEquipamiento;
    LOOP
        FETCH CursorEquipamiento INTO precioEquipamiento WHERE ID_Clinica = ID_ClinicaA;
        EXIT WHEN CursorEquipamiento%NOTFOUND;
        totalCosteEquip := totalCosteEquip + precioEquipamiento;
    END LOOP;
    CLOSE CursorEquipamiento;
    DBMS_OUTPUT.PUT_LINE('El precio total de todo el equipamiento es ' || totalCosteEquip);
END;
/
DELIMITER ;*/

DELIMITER //

CREATE PROCEDURE totalCosteEquipamiento(IN id_clinicaRecibido INT)
BEGIN
    DECLARE totalCosteEquip DECIMAL(10, 2) DEFAULT 0;
    DECLARE precioEquipamiento DECIMAL(10, 2);
    DECLARE done INT DEFAULT FALSE;
    
    DECLARE curEquipamiento CURSOR FOR SELECT precio FROM equipamiento WHERE ID_Clinica = id_clinicaRecibido;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN curEquipamiento;
    
    read_loop: LOOP
        FETCH curEquipamiento INTO precioEquipamiento;
        IF done THEN
            LEAVE read_loop;
        END IF;
        SET totalCosteEquip = totalCosteEquip + precioEquipamiento;
    END LOOP;
    
    CLOSE curEquipamiento;
    
    SELECT CONCAT('El precio total de todo el equipamiento es ', totalCosteEquip,'€') AS totalCosteEquipamiento;
END //

DELIMITER ;

call totalCosteEquipamiento('1');

SELECT SUM(precio) FROM equipamiento WHERE ID_Clinica = 1;
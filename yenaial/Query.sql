-- 1. Crear y seleccionar la base de datos
CREATE DATABASE IF NOT EXISTS yenaial_db;
USE yenaial_db;

-- 2. Tabla: Usuarios (Fusión de Clientes + Datos de Login)
CREATE TABLE IF NOT EXISTS usuarios (
                                        id_usuario    INT AUTO_INCREMENT PRIMARY KEY,
                                        nombre        VARCHAR(100) NOT NULL,
                                        email         VARCHAR(100) NOT NULL UNIQUE,
                                        rol           ENUM('admin', 'cliente') NOT NULL DEFAULT 'cliente',
                                        password_hash VARCHAR(255) NOT NULL
);

-- 3. Tabla: Bicicletas
CREATE TABLE IF NOT EXISTS bicicletas (
                                          id_bicicleta INT AUTO_INCREMENT PRIMARY KEY,
                                          marca        VARCHAR(50) NOT NULL,
                                          modelo       VARCHAR(50) NOT NULL,
                                          id_usuario   INT NOT NULL,
                                          FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);

-- 4. Tabla: Componentes
CREATE TABLE IF NOT EXISTS componentes (
                                           id_componente  INT AUTO_INCREMENT PRIMARY KEY,
                                           tipo           VARCHAR(50)  NOT NULL,
                                           marca_modelo   VARCHAR(100) NOT NULL,
                                           estado_salud   VARCHAR(30)  NOT NULL,
                                           necesita_cambio BOOLEAN     NOT NULL DEFAULT FALSE,
                                           id_bicicleta   INT NOT NULL,
                                           FOREIGN KEY (id_bicicleta) REFERENCES bicicletas(id_bicicleta) ON DELETE CASCADE
);

-- 5. Tabla: Citas
CREATE TABLE IF NOT EXISTS citas (
                                     id_cita      INT AUTO_INCREMENT PRIMARY KEY,
                                     id_usuario   INT NOT NULL,
                                     id_bicicleta INT NOT NULL,
                                     fecha_hora   DATETIME NOT NULL,
                                     motivo       VARCHAR(255),
                                     FOREIGN KEY (id_usuario)   REFERENCES usuarios(id_usuario)     ON DELETE CASCADE,
                                     FOREIGN KEY (id_bicicleta) REFERENCES bicicletas(id_bicicleta) ON DELETE CASCADE
);

-- 6. Tabla: Reparaciones
CREATE TABLE IF NOT EXISTS reparaciones (
                                            id_reparacion     INT AUTO_INCREMENT PRIMARY KEY,
                                            id_bicicleta      INT NOT NULL,
                                            descripcion_trabajo TEXT,
                                            fecha             DATE NOT NULL,
                                            estado_progreso   ENUM('EN_DIAGNOSTICO','EN_REPARACION','LISTA_PARA_PRUEBA','LISTA_PARA_RETIRAR')
                                                NOT NULL DEFAULT 'EN_DIAGNOSTICO',
                                            FOREIGN KEY (id_bicicleta) REFERENCES bicicletas(id_bicicleta) ON DELETE CASCADE
);

-- 7. Usuario por defecto (contraseña: la que tenías para 'cliente')
--    Hash bcrypt correspondiente a la contraseña original
INSERT IGNORE INTO usuarios (nombre, email, rol, password_hash)
VALUES (
           'Alumno DAM',
           'alumno@dam.es',
           'cliente',
           '$2b$12$EKC9OYQXwpXvRUo5LdEDYeaQXYgr.va3B9vK52F2MeeHuYCj9l5k6'
       );
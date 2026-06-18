DROP DATABASE IF EXISTS elcortijodb;
CREATE DATABASE elcortijodb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE elcortijodb;

-- TABLAS CATÁLOGO --

CREATE TABLE `rol_empleado_catalogo` (
                                         `id_rol_empleado` INT PRIMARY KEY AUTO_INCREMENT,
                                         `nombre_rol` VARCHAR(50) NOT NULL UNIQUE
) COMMENT 'Catálogo de roles para los empleados';

CREATE TABLE `tipo_alimento_catalogo` (
                                          `id_tipo_alimento` INT PRIMARY KEY AUTO_INCREMENT,
                                          `nombre_tipo_alimento` VARCHAR(100) NOT NULL UNIQUE
) COMMENT 'Catálogo de tipos de alimento balanceado';

CREATE TABLE `estado_pedido_catalogo` (
                                          `id_estado_pedido` INT PRIMARY KEY AUTO_INCREMENT,
                                          `nombre_estado` VARCHAR(50) NOT NULL UNIQUE
) COMMENT 'Catálogo de estados de un pedido';

CREATE TABLE `forma_pago_catalogo` (
                                       `id_forma_pago` INT PRIMARY KEY AUTO_INCREMENT,
                                       `nombre_forma_pago` VARCHAR(50) NOT NULL UNIQUE
) COMMENT 'Catálogo de formas de pago';


-- TABLAS PRINCIPALES --

CREATE TABLE `cliente` (
                           `id_cliente` INT PRIMARY KEY AUTO_INCREMENT,
                           `nombre` VARCHAR(50) NOT NULL,
                           `apellido` VARCHAR(50) NOT NULL,
                           `dni` VARCHAR(8) UNIQUE NOT NULL,
                           `telefono` VARCHAR(15),
                           `direccion` VARCHAR(150),
                           `activo` BOOLEAN NOT NULL DEFAULT TRUE
) COMMENT 'Información de los clientes';

CREATE TABLE `empleado` (
                            `id_empleado` INT PRIMARY KEY AUTO_INCREMENT,
                            `dni` VARCHAR(8) UNIQUE NOT NULL,
                            `nombre` VARCHAR(50) NOT NULL,
                            `apellido` VARCHAR(50) NOT NULL,
                            `password` VARCHAR(255) NOT NULL COMMENT 'Contraseña encriptada',
                            `fecha_nac` DATE,
                            `telefono` VARCHAR(15),
                            `direccion` VARCHAR(150),
                            `id_rol_empleado` INT NOT NULL,
                            `activo` BOOLEAN NOT NULL DEFAULT TRUE,
                            FOREIGN KEY (`id_rol_empleado`) REFERENCES `rol_empleado_catalogo` (`id_rol_empleado`)
) COMMENT 'Información de los empleados y su rol';

CREATE TABLE `producto` (
                            `id_producto` INT PRIMARY KEY AUTO_INCREMENT,
                            `nombre` VARCHAR(100) NOT NULL,
                            `id_tipo_alimento` INT NOT NULL,
                            `precio_unitario` DECIMAL(10,2) NOT NULL,
                            `stock` INT UNSIGNED NOT NULL DEFAULT 0,
                            `activo` BOOLEAN NOT NULL DEFAULT TRUE,
                            FOREIGN KEY (`id_tipo_alimento`) REFERENCES `tipo_alimento_catalogo` (`id_tipo_alimento`),
                            UNIQUE KEY `idx_producto_unico` (`nombre`, `id_tipo_alimento`)
) COMMENT 'Productos de alimento balanceado';

CREATE TABLE `proveedor` (
                             `id_proveedor` INT PRIMARY KEY AUTO_INCREMENT,
                             `nombre` VARCHAR(100) NOT NULL,
                             `ruc` VARCHAR(11) UNIQUE,
                             `direccion` VARCHAR(150),
                             `telefono` VARCHAR(15)
) COMMENT 'Información de los proveedores de insumos (informativo)';

CREATE TABLE `pedido` (
                          `id_pedido` INT PRIMARY KEY AUTO_INCREMENT,
                          `fecha` DATE NOT NULL,
                          `hora` TIME NOT NULL,
                          `id_estado_pedido` INT NOT NULL,
                          `id_cliente` INT NOT NULL,
                          `id_empleado_vendedor` INT NOT NULL,
                          FOREIGN KEY (`id_estado_pedido`) REFERENCES `estado_pedido_catalogo` (`id_estado_pedido`),
                          FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
                          FOREIGN KEY (`id_empleado_vendedor`) REFERENCES `empleado` (`id_empleado`)
) COMMENT 'Cabecera de los pedidos realizados';

CREATE TABLE `detalle_pedido` (
                                  `id_detalle_pedido` INT PRIMARY KEY AUTO_INCREMENT,
                                  `id_pedido` INT NOT NULL,
                                  `id_producto` INT NOT NULL,
                                  `cantidad` INT UNSIGNED NOT NULL,
                                  `precio_unitario_venta` DECIMAL(10,2) NOT NULL,
                                  `subtotal` DECIMAL(10,2) NOT NULL,
                                  FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`),
                                  FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
                                  UNIQUE (`id_pedido`, `id_producto`)
) COMMENT 'Detalle de los productos en cada pedido';

CREATE TABLE `comprobante` (
                               `id_comprobante` INT PRIMARY KEY AUTO_INCREMENT,
                               `fecha` DATE NOT NULL,
                               `total` DECIMAL(10,2) NOT NULL,
                               `id_forma_pago` INT NOT NULL,
                               `id_pedido` INT NOT NULL UNIQUE,
                               FOREIGN KEY (`id_forma_pago`) REFERENCES `forma_pago_catalogo` (`id_forma_pago`),
                               FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`)
) COMMENT 'Información general del comprobante de pago';

CREATE TABLE `despacho` (
                            `id_despacho` INT PRIMARY KEY AUTO_INCREMENT,
                            `fecha_despacho` DATETIME NOT NULL,
                            `id_empleado_despachador` INT NOT NULL,
                            `id_pedido` INT NOT NULL UNIQUE,
                            `observaciones` TEXT,
                            FOREIGN KEY (`id_empleado_despachador`) REFERENCES `empleado` (`id_empleado`),
                            FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`)
) COMMENT 'Registro del despacho de un pedido';


-- INSERCIÓN DE DATOS --

-- Catálogos
INSERT INTO `rol_empleado_catalogo` (`nombre_rol`) VALUES ('Administrador'), ('Vendedor'), ('Cajero'), ('Despachador');

INSERT INTO `tipo_alimento_catalogo` (`nombre_tipo_alimento`) VALUES
                                                                  ('Cachorros Iniciador'), ('Gestacion Raza Pekeña'),
                                                                  ('cachorros Crecimiento'), ('Gestacion Raza Grande'),
                                                                  ('Adultos Raza Pekeña'), ('Lactancia raza pekeña'),
                                                                  ('Adultos Raza Grande'), ('Lactancia raza grande'), ('crecimiento raza grande');

INSERT INTO `estado_pedido_catalogo` (`nombre_estado`) VALUES
                                                           ('Pendiente'), ('En Preparación'), ('Listo para Despacho'), ('Despachado'), ('Cancelado');

INSERT INTO `forma_pago_catalogo` (`nombre_forma_pago`) VALUES
                                                            ('Efectivo'), ('Yape'), ('Plin'), ('Transferencia BCP'), ('Tarjeta Visa');

-- Empleados
INSERT INTO `empleado` (`dni`, `nombre`, `apellido`, `password`, `fecha_nac`, `telefono`, `direccion`, `id_rol_empleado`) VALUES
                                                                                                                              ('74567890', 'Elena', 'Chávez Vargas', '$2a$10$Hzr9PXLwOIv4R3LbZpS96uHQTWPts8H3JH7/RROdPS9yGxViUM4aC', '1980-07-25', '945678901', 'Av. América Sur 321, Trujillo', 1),
                                                                                                                              ('70123456', 'Ana', 'García Pérez', '$2a$10$Hzr9PXLwOIv4R3LbZpS96uHQTWPts8H3JH7/RROdPS9yGxViUM4aC', '1990-05-15', '987654321', 'Av. España 123, Trujillo', 2),
                                                                                                                              ('71234567', 'Carlos', 'Rodríguez Quispe', '$2a$10$Hzr9PXLwOIv4R3LbZpS96uHQTWPts8H3JH7/RROdPS9yGxViUM4aC', '1985-08-20', '912345678', 'Jr. Pizarro 456, Trujillo', 3),
                                                                                                                              ('73456789', 'Luis', 'Martínez Silva', '$2a$10$Hzr9PXLwOIv4R3LbZpS96uHQTWPts8H3JH7/RROdPS9yGxViUM4aC', '1988-11-30', '934567890', 'Calle Los Laureles 789, Víctor Larco', 4);

-- Clientes
INSERT INTO `cliente` (`nombre`, `apellido`, `dni`, `telefono`, `direccion`) VALUES
                                                                                 ('Juan', 'Paredes Solano', '40123456', '998877665', 'Psj. Las Begonias 102, La Esperanza'),
                                                                                 ('Maria', 'Flores Castillo', '41234567', '988776655', 'Av. Mansiche 2030, Trujillo'),
                                                                                 ('Pedro', 'Gonzales Diaz', '42345678', '977665544', 'Calle San Martin 505, Moche'),
                                                                                 ('Lucia', 'Ramirez Torres', '43456789', '966554433', 'Urb. Primavera, Calle Las Rosas 11, Trujillo'),
                                                                                 ('Jorge', 'Mendoza Lee', '44567890', '955443322', 'Av. El Golf 555, Trujillo');

-- Proveedores
INSERT INTO `proveedor` (`nombre`, `ruc`, `direccion`, `telefono`) VALUES
                                                                       ('Agroinsumos del Norte S.A.C.', '20123456789', 'Parque Industrial, Trujillo', '044-200100'),
                                                                       ('Nutrimentos Perú S.R.L.', '20987654321', 'Av. Industrial 458, Lima', '01-5002030');

-- Productos
INSERT INTO `producto` (`nombre`, `id_tipo_alimento`, `precio_unitario`, `stock`) VALUES
                                                                                      ('Ricocan Plus', 1, 65.50, 200),
                                                                                      ('Zeus Premium', 2, 62.00, 350),
                                                                                      ('Roya Canin', 4, 70.00, 150),
                                                                                      ('cambo', 5, 68.50, 300),
                                                                                      ('Mimaskot', 7, 75.20, 180),
                                                                                      ('Dog Chow', 3, 60.80, 120),
                                                                                      ('Gran Plus', 9, 25.00, 500),
                                                                                      ('Gran Perro', 1, 85.00, 80);

-- Pedidos
INSERT INTO `pedido` (`fecha`, `hora`, `id_estado_pedido`, `id_cliente`, `id_empleado_vendedor`) VALUES
                                                                                                     ('2025-12-20', '09:30:00', 4, 1, 2),
                                                                                                     ('2025-12-21', '11:15:00', 3, 2, 2),
                                                                                                     ('2025-12-22', '10:00:00', 2, 3, 3),
                                                                                                     ('2025-12-23', '14:45:00', 1, 5, 2),
                                                                                                     ('2025-12-23', '16:00:00', 1, 4, 3);

-- Detalles de pedidos
INSERT INTO `detalle_pedido` (`id_pedido`, `id_producto`, `cantidad`, `precio_unitario_venta`, `subtotal`) VALUES
                                                                                                               (1, 1, 10, 65.50, 655.00), (1, 2, 5, 62.00, 310.00),
                                                                                                               (2, 4, 20, 68.50, 1370.00),
                                                                                                               (3, 5, 15, 75.20, 1128.00),
                                                                                                               (4, 7, 30, 25.00, 750.00),
                                                                                                               (5, 8, 2, 85.00, 170.00), (5, 3, 10, 60.80, 608.00);

-- Comprobantes
INSERT INTO `comprobante` (`fecha`, `total`, `id_forma_pago`, `id_pedido`) VALUES
                                                                               ('2023-10-20', 965.00, 1, 1),
                                                                               ('2023-10-21', 1370.00, 2, 2),
                                                                               ('2023-10-22', 1128.00, 4, 3);

-- Despacho
INSERT INTO `despacho` (`fecha_despacho`, `id_empleado_despachador`, `id_pedido`, `observaciones`) VALUES
    ('2023-10-20 15:00:00', 4, 1, 'Cliente recogió en tienda. Todo conforme.');
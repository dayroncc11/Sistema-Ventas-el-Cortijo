# Sistema de Ventas El Cortijo

Sistema web desarrollado con Spring Boot y Java para la gestión de ventas, clientes y productos del molino "El Cortijo". El sistema registra pedidos, emite comprobantes y controla el inventario de alimentos balanceados.

## Módulos Principales

- **Dashboard:** KPIs en tiempo real, gráficas de ventas y generación de reportes en PDF.
- **Tablas (CRUD):** Administración completa de Productos, Clientes, Empleados y visualización de Pedidos.
- **Ventas (Checkout):** Flujo de venta de 4 pasos (Carrito, Despacho, Pago, Confirmación).
- **Seguridad y Perfil:** Autenticación con contraseñas encriptadas (BCrypt), roles y gestión del perfil del empleado.

## Tecnologías y Herramientas

**Backend:**
- Java 21
- Spring Boot 3.5.3 (Web, Data JPA, Security)
- PostgreSQL (Base de datos principal)
- OpenPDF (Generación de reportes PDF)
- Maven (Gestión de dependencias)
- Lombok

**Frontend:**
- HTML5 / CSS3 / Vanilla JavaScript
- Thymeleaf (Motor de plantillas con arquitectura de fragmentos)
- Tailwind CSS (Estilos y UI)
- Chart.js (Gráficos interactivos)

## Instalación y Ejecución Local

1. Clona el repositorio:
   ```sh
   git clone https://github.com/daycc11/Sistema-Ventas-el-Cortijo.git
   cd spring-java-sistema-ventas-elcortijo
   ```

2. Configura tu base de datos PostgreSQL y actualiza las credenciales en `src/main/resources/application.properties`.

3. Ejecuta el proyecto con Maven:
   ```sh
   mvn spring-boot:run
   ```
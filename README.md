# 🛍️ BataPeru - Sistema de E-commerce

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Sistema de e-commerce completo inspirado en la tienda Bata Perú, desarrollado con Spring Boot y arquitectura RESTful.

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Configuración](#-configuración)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Entidades Principales](#-entidades-principales)
- [API Endpoints](#-api-endpoints)
- [Documentación Adicional](#-documentación-adicional)
- [Contribución](#-contribución)
- [Licencia](#-licencia)

## ✨ Características

- 🔐 **Gestión de Usuarios**: Registro, autenticación y gestión de perfiles
- 👟 **Catálogo de Productos**: Sistema completo de productos con variantes (tallas y colores)
- 🛒 **Sistema de Pedidos**: Gestión de carritos de compra y procesamiento de pedidos
- 📦 **Direcciones de Envío**: Múltiples direcciones por usuario con dirección principal
- ⭐ **Sistema de Reseñas**: Comentarios y calificaciones de productos
- 🏷️ **Marcas y Categorías**: Organización jerárquica de productos
- 📸 **Galería de Imágenes**: Múltiples imágenes por producto organizadas por color
- 💰 **Precios y Descuentos**: Soporte para precios regulares y ofertas

## 🚀 Tecnologías

### Backend
- **Spring Boot 4.0.0** - Framework principal
- **Spring Data JPA** - Capa de persistencia
- **Spring Web MVC** - API RESTful
- **Hibernate** - ORM
- **MySQL Connector** - Driver de base de datos

### Utilidades
- **Lombok** - Reducción de código boilerplate
- **MapStruct 1.5.5** - Mapeo de entidades a DTOs
- **Maven** - Gestión de dependencias

### Base de Datos
- **MySQL 8.0** - Sistema de gestión de base de datos

## 📦 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

- **JDK 17** o superior
- **Maven 3.6+**
- **MySQL 8.0** o superior
- **IDE** (IntelliJ IDEA, Eclipse, VS Code)

## 🔧 Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/Evac04BP.git
cd Evac04BP
```

### 2. Configurar la base de datos

Crea la base de datos en MySQL:

```sql
CREATE DATABASE BataPeruDB;
```

### 3. Configurar las credenciales

Edita el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/BataPeruDB?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
```

### 4. Compilar el proyecto

```bash
mvn clean install
```

### 5. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

O ejecutar directamente:

```bash
java -jar target/BataPeru-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en: `http://localhost:8081`

## ⚙️ Configuración

### application.properties

```properties
# Nombre de la aplicación
spring.application.name=BataPeru

# Configuración de MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/BataPeruDB?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Puerto del servidor
server.port=8081

# Configuración de JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Configuración de Hibernate

- **ddl-auto=update**: Actualiza automáticamente el esquema de la base de datos
- **show-sql=true**: Muestra las consultas SQL en la consola
- **format_sql=true**: Formatea las consultas SQL para mejor legibilidad

## 📁 Estructura del Proyecto

```
Evac04BP/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/BataPeru/
│   │   │       ├── BataPeruApplication.java
│   │   │       ├── controller/          # Controladores REST
│   │   │       │   ├── CategoriaController.java
│   │   │       │   ├── DireccionController.java
│   │   │       │   ├── MarcaController.java
│   │   │       │   ├── PedidoController.java
│   │   │       │   ├── ProductoController.java
│   │   │       │   └── UsuarioController.java
│   │   │       ├── dto/                 # Data Transfer Objects
│   │   │       │   ├── CategoriaDTO.java
│   │   │       │   ├── ColorDTO.java
│   │   │       │   ├── ComentarioDTO.java
│   │   │       │   ├── DetallePedidoDTO.java
│   │   │       │   ├── DireccionDTO.java
│   │   │       │   ├── ImagenProductoDTO.java
│   │   │       │   ├── MarcaDTO.java
│   │   │       │   ├── PedidoDTO.java
│   │   │       │   ├── ProductoDTO.java
│   │   │       │   ├── TallaDTO.java
│   │   │       │   ├── UsuarioDTO.java
│   │   │       │   └── VarianteProductoDTO.java
│   │   │       ├── entity/              # Entidades JPA
│   │   │       │   ├── Categoria.java
│   │   │       │   ├── Color.java
│   │   │       │   ├── Comentario.java
│   │   │       │   ├── DetallePedido.java
│   │   │       │   ├── Direccion.java
│   │   │       │   ├── ImagenProducto.java
│   │   │       │   ├── Marca.java
│   │   │       │   ├── Pedido.java
│   │   │       │   ├── Producto.java
│   │   │       │   ├── Talla.java
│   │   │       │   ├── Usuario.java
│   │   │       │   └── VarianteProducto.java
│   │   │       ├── mapper/              # Mappers Entity <-> DTO
│   │   │       │   ├── CategoriaMapper.java
│   │   │       │   ├── ColorMapper.java
│   │   │       │   ├── ComentarioMapper.java
│   │   │       │   ├── DetallePedidoMapper.java
│   │   │       │   ├── DireccionMapper.java
│   │   │       │   ├── ImagenProductoMapper.java
│   │   │       │   ├── MarcaMapper.java
│   │   │       │   ├── PedidoMapper.java
│   │   │       │   ├── ProductoMapper.java
│   │   │       │   ├── TallaMapper.java
│   │   │       │   ├── UsuarioMapper.java
│   │   │       │   └── VarianteProductoMapper.java
│   │   │       ├── repository/          # Repositorios Spring Data
│   │   │       │   ├── CategoriaRepository.java
│   │   │       │   ├── ColorRepository.java
│   │   │       │   ├── ComentarioRepository.java
│   │   │       │   ├── DetallePedidoRepository.java
│   │   │       │   ├── DireccionRepository.java
│   │   │       │   ├── ImagenProductoRepository.java
│   │   │       │   ├── MarcaRepository.java
│   │   │       │   ├── PedidoRepository.java
│   │   │       │   ├── ProductoRepository.java
│   │   │       │   ├── TallaRepository.java
│   │   │       │   ├── UsuarioRepository.java
│   │   │       │   └── VarianteProductoRepository.java
│   │   │       └── service/             # Servicios de negocio
│   │   │           ├── CategoriaService.java
│   │   │           ├── ColorService.java
│   │   │           ├── ComentarioService.java
│   │   │           ├── DireccionService.java
│   │   │           ├── ImagenProductoService.java
│   │   │           ├── MarcaService.java
│   │   │           ├── PedidoService.java
│   │   │           ├── ProductoService.java
│   │   │           ├── TallaService.java
│   │   │           ├── UsuarioService.java
│   │   │           └── VarianteProductoService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/example/BataPeru/
│               └── BataPeruApplicationTests.java
├── API_DOCUMENTATION.md
├── DATABASE_README.md
├── GUIA_SERVICIOS_REPOSITORIOS.md
├── pom.xml
└── README.md
```

## 🗄️ Entidades Principales

### Usuario
Gestión de usuarios del sistema con autenticación y perfil.

**Campos principales:**
- Email único
- Contraseña hasheada
- Nombres y apellidos
- Teléfono
- Fecha de registro

**Relaciones:**
- Múltiples direcciones de envío
- Historial de pedidos
- Comentarios y reseñas

### Producto
Catálogo de productos con información detallada.

**Campos principales:**
- Nombre y descripción
- Precio regular y precio de oferta
- Género (Hombre/Mujer/Niño)
- Material
- Estado activo/inactivo

**Relaciones:**
- Pertenece a una marca
- Pertenece a una categoría
- Tiene múltiples variantes (talla + color)
- Tiene múltiples imágenes por color
- Recibe comentarios y reseñas

### VarianteProducto
Combinación específica de producto + talla + color con su propio stock.

**Campos principales:**
- SKU único de la variante
- Cantidad en stock

**Relaciones:**
- Pertenece a un producto
- Tiene una talla específica
- Tiene un color específico
- Se incluye en detalles de pedido

### Pedido
Órdenes de compra de los clientes.

**Campos principales:**
- Estado del pedido (Pendiente, Procesando, Enviado, Entregado)
- Total a pagar
- Método de pago
- ID de transacción
- Fecha del pedido

**Relaciones:**
- Pertenece a un usuario
- Tiene una dirección de envío
- Contiene múltiples detalles (líneas del pedido)

### Categoría
Sistema jerárquico de categorías (soporta subcategorías).

**Campos principales:**
- Nombre
- Slug (URL amigable)
- Nivel de jerarquía
- ID del padre (para subcategorías)

### Marca
Marcas de los productos.

**Campos principales:**
- Nombre
- Logo URL
- Slug (URL amigable)

## 🌐 API Endpoints

**Base URL:** `http://localhost:8081/api`

### Autenticación y Usuarios

#### Registrar Usuario
```http
POST /api/auth/registro
Content-Type: application/json

{
  "email": "usuario@example.com",
  "contrasenaHash": "password123",
  "nombres": "Juan",
  "apellidos": "Pérez",
  "telefono": "999888777"
}
```

#### Iniciar Sesión
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "usuario@example.com",
  "password": "password123"
}
```

### Productos

#### Listar Todos los Productos
```http
GET /api/productos
```

#### Obtener Producto por ID
```http
GET /api/productos/{id}
```

#### Listar Productos por Categoría
```http
GET /api/productos/categoria/{categoriaId}
```

#### Listar Productos por Marca
```http
GET /api/productos/marca/{marcaId}
```

#### Crear Producto
```http
POST /api/productos
Content-Type: application/json

{
  "nombre": "Zapatilla Urbana",
  "descripcion": "Zapatilla cómoda para uso diario",
  "precioRegular": 129.90,
  "genero": "HOMBRE",
  "material": "Lona",
  "marcaId": 1,
  "categoriaId": 2
}
```

#### Actualizar Producto
```http
PUT /api/productos/{id}
```

#### Eliminar Producto
```http
DELETE /api/productos/{id}
```

### Categorías

#### Listar Todas las Categorías
```http
GET /api/categorias
```

#### Obtener Categoría por ID
```http
GET /api/categorias/{id}
```

#### Crear Categoría
```http
POST /api/categorias
Content-Type: application/json

{
  "nombre": "Zapatillas",
  "slug": "zapatillas",
  "nivel": 1
}
```

### Marcas

#### Listar Todas las Marcas
```http
GET /api/marcas
```

#### Obtener Marca por ID
```http
GET /api/marcas/{id}
```

#### Crear Marca
```http
POST /api/marcas
Content-Type: application/json

{
  "nombre": "North Star",
  "slug": "north-star",
  "logoUrl": "https://example.com/logo.png"
}
```

### Direcciones

#### Listar Direcciones del Usuario
```http
GET /api/direcciones/usuario/{usuarioId}
```

#### Obtener Dirección Principal
```http
GET /api/direcciones/usuario/{usuarioId}/principal
```

#### Crear Dirección
```http
POST /api/direcciones
Content-Type: application/json

{
  "usuarioId": 1,
  "direccionCalle": "Av. Principal 123",
  "ciudad": "Lima",
  "provincia": "Lima",
  "codigoPostal": "15001",
  "pais": "Perú",
  "esPrincipal": true
}
```

### Pedidos

#### Listar Pedidos del Usuario
```http
GET /api/pedidos/usuario/{usuarioId}
```

#### Obtener Pedido por ID
```http
GET /api/pedidos/{id}
```

#### Crear Pedido
```http
POST /api/pedidos
Content-Type: application/json

{
  "usuarioId": 1,
  "direccionEnvioId": 1,
  "metodoPago": "Tarjeta de Crédito",
  "detalles": [
    {
      "varianteProductoId": 10,
      "cantidad": 2,
      "precioUnitario": 129.90
    }
  ]
}
```

#### Actualizar Estado del Pedido
```http
PUT /api/pedidos/{id}/estado
Content-Type: application/json

{
  "estado": "ENVIADO"
}
```

## 📚 Documentación Adicional

El proyecto incluye documentación detallada adicional:

- **[API_DOCUMENTATION.md](API_DOCUMENTATION.md)**: Documentación completa de todos los endpoints con ejemplos
- **[DATABASE_README.md](DATABASE_README.md)**: Estructura detallada de la base de datos y configuración
- **[GUIA_SERVICIOS_REPOSITORIOS.md](GUIA_SERVICIOS_REPOSITORIOS.md)**: Guía para desarrolladores sobre servicios y repositorios

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas (Layered Architecture):

```
┌─────────────────────────────────────┐
│         Controller Layer            │  ← API REST Endpoints
├─────────────────────────────────────┤
│           Service Layer             │  ← Lógica de Negocio
├─────────────────────────────────────┤
│         Repository Layer            │  ← Acceso a Datos
├─────────────────────────────────────┤
│          Entity Layer               │  ← Modelo de Datos
└─────────────────────────────────────┘
           ↕
      ┌─────────┐
      │  MySQL  │
      └─────────┘
```

### Patrones de Diseño Utilizados

- **DTO (Data Transfer Object)**: Para transferencia de datos entre capas
- **Repository Pattern**: Para abstracción del acceso a datos
- **Service Layer Pattern**: Para encapsular lógica de negocio
- **Mapper Pattern**: Para conversión entre entidades y DTOs (MapStruct)
- **Dependency Injection**: Inversión de control con Spring

## 🧪 Testing

Ejecutar las pruebas:

```bash
mvn test
```

Ejecutar las pruebas con cobertura:

```bash
mvn clean test jacoco:report
```

## 🛠️ Desarrollo

### Compilar sin ejecutar tests

```bash
mvn clean install -DskipTests
```

### Ejecutar en modo desarrollo (Hot Reload)

```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=true"
```

### Generar ejecutable JAR

```bash
mvn clean package
```

El archivo JAR estará en: `target/BataPeru-0.0.1-SNAPSHOT.jar`

## 🔍 Solución de Problemas

### Error de conexión a MySQL

**Problema:** `Communications link failure`

**Solución:**
1. Verifica que MySQL esté ejecutándose
2. Confirma el puerto (por defecto 3306)
3. Verifica usuario y contraseña en `application.properties`

### Error al generar mappers

**Problema:** MapStruct no genera las implementaciones

**Solución:**
```bash
mvn clean compile
```

### Puerto 8081 ya en uso

**Problema:** `Port 8081 is already in use`

**Solución:**
Cambia el puerto en `application.properties`:
```properties
server.port=8082
```

## 📈 Mejoras Futuras

- [ ] Implementar Spring Security para autenticación JWT
- [ ] Agregar paginación y ordenamiento en listados
- [ ] Implementar caché con Redis
- [ ] Agregar sistema de búsqueda con Elasticsearch
- [ ] Implementar notificaciones por email
- [ ] Agregar sistema de cupones y descuentos
- [ ] Implementar integración con pasarelas de pago
- [ ] Agregar sistema de wishlists (listas de deseos)
- [ ] Implementar panel de administración
- [ ] Agregar soporte para múltiples idiomas (i18n)

## 🤝 Contribución

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

### Código de Conducta

Por favor, lee nuestro [Código de Conducta](CODE_OF_CONDUCT.md) antes de contribuir.

## 👥 Autores

- **Tu Nombre** - *Desarrollo Inicial* - [Bimesito](https://github.com/Bimesito)

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - mira el archivo [LICENSE](LICENSE) para más detalles.

## 🙏 Agradecimientos

- Spring Boot por el excelente framework
- MapStruct por el mapeo automático de objetos
- Lombok por reducir el código boilerplate
- La comunidad de Spring por la documentación y soporte

## 📞 Contacto

Para preguntas o sugerencias, por favor abre un issue en el repositorio.

---

⭐️ Si este proyecto te fue útil, considera darle una estrella en GitHub!

**Desarrollado con ❤️ usando Spring Boot**


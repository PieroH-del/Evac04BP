# BataPeru - Sistema de E-commerce

## Configuración de Base de Datos

### Requisitos Previos
- SQL Server instalado y corriendo
- Usuario: `sa`
- Contraseña: `123456`
- Puerto: `1433` (puerto por defecto de SQL Server)

### Configuración

1. **Crear la base de datos en SQL Server:**
```sql
CREATE DATABASE BataPeruDB;
```

2. **La aplicación está configurada para:**
   - Conectarse a SQL Server en `localhost:1433`
   - Base de datos: `BataPeruDB`
   - Usuario: `sa`
   - Contraseña: `123456`
   - Hibernate DDL Auto: `update` (creará las tablas automáticamente)

## Estructura de Entidades

El proyecto incluye las siguientes entidades según el diagrama de base de datos:

### 1. **Usuario** (`usuarios`)
- Gestión de usuarios del sistema
- Campos: id, email, contraseña_hash, nombres, apellidos, teléfono, fecha_registro
- Relaciones: direcciones, pedidos, reseñas

### 2. **Direccion** (`direcciones`)
- Direcciones de envío de los usuarios
- Campos: id, usuario_id, direccion_calle, ciudad, provincia, codigo_postal, pais, es_principal

### 3. **Marca** (`marcas`)
- Marcas de productos
- Campos: id, nombre, logo_url, slug
- Relaciones: productos

### 4. **Categoria** (`categorias`)
- Categorías de productos (soporta jerarquía con id_padre)
- Campos: id, id_padre, nombre, slug, nivel
- Relaciones: productos

### 5. **Producto** (`productos`)
- Productos principales
- Campos: id, marca_id, categoria_id, nombre, descripcion, sku_base, precio_regular, precio_oferta, genero, material_capellada, material_forro, material_suela, activo, fecha_creacion
- Relaciones: variantes, imágenes, reseñas

### 6. **Talla** (`tallas`)
- Tallas disponibles
- Campos: id, valor, region
- Relaciones: variantes

### 7. **Color** (`colores`)
- Colores disponibles
- Campos: id, nombre, codigo_hex
- Relaciones: variantes, imágenes

### 8. **VarianteProducto** (`variantes_producto`)
- Variantes de productos (combinación de producto + talla + color)
- Campos: id, producto_id, talla_id, color_id, sku_variante, stock_cantidad
- Relaciones: detalles_pedido

### 9. **ImagenProducto** (`imagenes_producto`)
- Imágenes de productos por color
- Campos: id, producto_id, color_id, url_imagen, es_portada

### 10. **Pedido** (`pedidos`)
- Pedidos de los clientes
- Campos: id, usuario_id, estado, total_pagar, direccion_envio_id, metodo_pago, id_transaccion, fecha_pedido
- Relaciones: detalles

### 11. **DetallePedido** (`detalles_pedido`)
- Líneas de detalle de los pedidos
- Campos: id, pedido_id, variante_producto_id, cantidad, precio_unitario

### 12. **Resena** (`resenas`)
- Reseñas de productos por usuarios
- Campos: id, usuario_id, producto_id, puntuacion, comentario, fecha

## Tecnologías Utilizadas

- **Spring Boot 4.0.0**
- **Spring Data JPA**
- **SQL Server** (Microsoft SQL Server)
- **Lombok** (para reducir código boilerplate)
- **Maven** (gestor de dependencias)

## Características de JPA

- **Auto-generación de ID**: Todas las entidades usan `GenerationType.IDENTITY`
- **Relaciones bidireccionales**: Implementadas entre entidades relacionadas
- **Tipos de datos apropiados**: 
  - `BigDecimal` para precios
  - `LocalDateTime` para fechas
  - `Boolean` para flags
  - `TEXT` para descripciones largas

## Cómo ejecutar

1. Asegúrate de que SQL Server está corriendo
2. Crea la base de datos `BataPeruDB`
3. Ejecuta la aplicación:
```bash
mvn spring-boot:run
```

4. Hibernate creará automáticamente todas las tablas según las entidades definidas

## Notas

- El modo `spring.jpa.hibernate.ddl-auto=update` está configurado para crear y actualizar automáticamente el esquema de la base de datos
- Se recomienda cambiar a `validate` en producción
- Las consultas SQL se mostrarán en la consola (`spring.jpa.show-sql=true`)


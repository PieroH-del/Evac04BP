# 📘 Guía para Crear Servicios y Repositorios - BataPeru

Esta guía proporciona información detallada sobre las variables y campos importantes de cada entidad para facilitar la creación de **Repositories**, **Services** e **Implementations**.

---

## 📋 Índice por Tabla

1. [usuarios](#1-usuarios)
2. [direcciones](#2-direcciones)
3. [marcas](#3-marcas)
4. [categorias](#4-categorias)
5. [productos](#5-productos)
6. [colores](#6-colores)
7. [tallas](#7-tallas)
8. [variantes_producto](#8-variantes_producto)
9. [imagenes_producto](#9-imagenes_producto)
10. [pedidos](#10-pedidos)
11. [detalles_pedido](#11-detalles_pedido)
12. [comentarios](#12-comentarios)

---

## 1. usuarios

### 📊 Entidad: `Usuario.java`
### 📦 DTO: `UsuarioDTO.java`
### 🔄 Mapper: `UsuarioMapper.java`

### Variables Importantes:

| Campo | Tipo | Descripción | Uso en Servicios |
|-------|------|-------------|------------------|
| **id** | Long | Identificador único | Búsqueda por ID, actualización, eliminación |
| **email** | String | Email único del usuario | **Búsqueda por email**, validación de unicidad, login |
| **contrasenaHash** | String | Contraseña hasheada | Autenticación, actualización de contraseña |
| **nombres** | String | Nombres del usuario | Filtros, búsquedas, personalización |
| **apellidos** | String | Apellidos del usuario | Filtros, búsquedas, concatenación con nombres |
| **telefono** | String | Teléfono de contacto | Notificaciones, validación |
| **fechaRegistro** | LocalDateTime | Fecha de creación | Reportes, filtros por fecha, auditoría |
| **direcciones** | List\<Direccion\> | Direcciones del usuario | Obtener direcciones asociadas |
| **pedidos** | List\<Pedido\> | Pedidos del usuario | Historial de compras |
| **comentarios** | List\<Comentario\> | Comentarios del usuario | Reseñas y calificaciones |

### 🔍 Métodos Sugeridos para Repository:
```java
Optional<Usuario> findByEmail(String email);
List<Usuario> findByNombresContainingOrApellidosContaining(String nombres, String apellidos);
List<Usuario> findByFechaRegistroBetween(LocalDateTime inicio, LocalDateTime fin);
boolean existsByEmail(String email);
Long countByFechaRegistroAfter(LocalDateTime fecha);
```

### 💼 Operaciones Importantes en Service:
- Registro de usuarios (validar email único)
- Login/Autenticación
- Actualización de perfil
- Cambio de contraseña
- Obtener historial de pedidos
- Gestión de direcciones del usuario

---

## 2. direcciones

### 📊 Entidad: `Direccion.java`
### 📦 DTO: `DireccionDTO.java`
### 🔄 Mapper: `DireccionMapper.java`

### Variables Importantes:

| Campo | Tipo | Descripción | Uso en Servicios |
|-------|------|-------------|------------------|
| **id** | Long | Identificador único | CRUD básico |
| **usuarioId** | Long (FK) | ID del usuario propietario | **Filtrar direcciones por usuario** |
| **direccionCalle** | String | Calle y número | Visualización, validación |
| **ciudad** | String | Ciudad | Filtros geográficos |
| **provincia** | String | Provincia/Estado | Filtros, cálculo de envío |
| **codigoPostal** | String | Código postal | Validación, cálculo de envío |
| **pais** | String | País | Validación, restricciones de envío |
| **esPrincipal** | Boolean | Dirección principal del usuario | **Obtener dirección por defecto** |

### 🔍 Métodos Sugeridos para Repository:
```java
List<Direccion> findByUsuarioId(Long usuarioId);
Optional<Direccion> findByUsuarioIdAndEsPrincipal(Long usuarioId, Boolean esPrincipal);
List<Direccion> findByCiudadAndProvincia(String ciudad, String provincia);
Long countByUsuarioId(Long usuarioId);
void deleteByUsuarioIdAndId(Long usuarioId, Long id);
```

### 💼 Operaciones Importantes en Service:
- Listar direcciones de un usuario
- Obtener dirección principal
- Establecer dirección como principal (desmarcar otras)
- Validar que el usuario no tenga más de X direcciones
- Eliminar dirección (verificar que no sea la única)

---

## 3. marcas

### 📊 Entidad: `Marca.java`
### 📦 DTO: `MarcaDTO.java`
### 🔄 Mapper: `MarcaMapper.java`

### Variables Importantes:

| Campo | Tipo | Descripción | Uso en Servicios |
|-------|------|-------------|------------------|
| **id** | Long | Identificador único | CRUD básico |
| **nombre** | String | Nombre de la marca | **Búsqueda, filtros, validación unicidad** |
| **logoUrl** | String | URL del logo | Visualización en catálogo |
| **activo** | Boolean | Estado de la marca | **Filtrar marcas activas/inactivas** |
| **fechaCreacion** | LocalDateTime | Fecha de creación | Auditoría, reportes |
| **productos** | List\<Producto\> | Productos de la marca | Catálogo por marca |

### 🔍 Métodos Sugeridos para Repository:
```java
List<Marca> findByActivo(Boolean activo);
Optional<Marca> findByNombre(String nombre);
boolean existsByNombre(String nombre);
List<Marca> findByNombreContaining(String nombre);
Long countByActivo(Boolean activo);
```

### 💼 Operaciones Importantes en Service:
- Listar marcas activas para catálogo
- Crear marca (validar nombre único)
- Activar/Desactivar marca
- Buscar marcas por nombre
- Obtener productos por marca

---

## 4. categorias

### 📊 Entidad: `Categoria.java`
### 📦 DTO: `CategoriaDTO.java`
### 🔄 Mapper: `CategoriaMapper.java`

### Variables Importantes:

| Campo | Tipo | Descripción | Uso en Servicios |
|-------|------|-------------|------------------|
| **id** | Long | Identificador único | CRUD básico |
| **nombre** | String | Nombre de la categoría | **Búsqueda, filtros, validación unicidad** |
| **productos** | List\<Producto\> | Productos de la categoría | Catálogo por categoría |

### 🔍 Métodos Sugeridos para Repository:
```java
Optional<Categoria> findByNombre(String nombre);
List<Categoria> findByNombreContaining(String nombre);
boolean existsByNombre(String nombre);
List<Categoria> findAll(); // Listar todas las categorías
Long countByNombre(String nombre);
```

### 💼 Operaciones Importantes en Service:
- Listar todas las categorías
- Crear categoría (validar nombre único)
- Buscar categorías por nombre
- Obtener productos por categoría
- Actualizar categoría
- Eliminar categoría (validar que no tenga productos asociados)

---

## 5. productos

### 📊 Entidad: `Producto.java`
### 📦 DTO: `ProductoDTO.java`
### 🔄 Mapper: `ProductoMapper.java`

### Variables Importantes:

| Campo | Tipo | Descripción | Uso en Servicios |
|-------|------|-------------|------------------|
| **id** | Long | Identificador único | CRUD básico |
| **marcaId** | Long (FK) | ID de la marca | **Filtrar productos por marca** |
| **categoriaId** | Long (FK) | ID de la categoría | **Filtrar productos por categoría** |
| **nombre** | String | Nombre del producto | **Búsqueda, filtros** |
| **descripcion** | String (TEXT) | Descripción detallada | Visualización |
| **precioRegular** | BigDecimal | Precio regular | **Cálculos, filtros por precio** |
| **genero** | String | Género (Hombre/Mujer/Unisex) | **Filtros de catálogo** |
| **material** | String | Material del producto | Filtros, especificaciones |
| **activo** | Boolean | Estado del producto | **Mostrar solo activos en catálogo** |
| **fechaCreacion** | LocalDateTime | Fecha de creación | Ordenamiento, productos nuevos |
| **variantes** | List\<VarianteProducto\> | Variantes del producto | Verificar stock disponible |
| **imagenes** | List\<ImagenProducto\> | Imágenes del producto | Galería |
| **comentarios** | List\<Comentario\> | Comentarios/reseñas | Calificación promedio |

### 🔍 Métodos Sugeridos para Repository:
```java
List<Producto> findByActivo(Boolean activo);
List<Producto> findByMarcaId(Long marcaId);
List<Producto> findByCategoriaId(Long categoriaId);
List<Producto> findByGenero(String genero);
List<Producto> findByNombreContaining(String nombre);
List<Producto> findByPrecioRegularBetween(BigDecimal min, BigDecimal max);
List<Producto> findByActivoAndMarcaIdAndCategoriaId(Boolean activo, Long marcaId, Long categoriaId);
List<Producto> findByFechaCreacionAfter(LocalDateTime fecha); // Productos nuevos
```

### 💼 Operaciones Importantes en Service:
- Búsqueda avanzada (por marca, categoría, género, precio)
- Obtener productos activos para catálogo
- Calcular calificación promedio desde comentarios
- Verificar disponibilidad de stock (desde variantes)
- Obtener productos relacionados (misma categoría/marca)
- Obtener imagen principal (esPortada = true)

---

## 6. colores

### 📊 Entidad: `Color.java`
### 📦 DTO: `ColorDTO.java`
### 🔄 Mapper: `ColorMapper.java`

### Variables Importantes:

| Campo | Tipo | Descripción | Uso en Servicios |
|-------|------|-------------|------------------|
| **id** | Long | Identificador único | CRUD básico |
| **nombre** | String | Nombre del color | **Búsqueda, filtros** |
| **codigoHex** | String | Código hexadecimal (#RRGGBB) | Visualización en UI |
| **variantes** | List\<VarianteProducto\> | Variantes con este color | Filtros de productos por color |

### 🔍 Métodos Sugeridos para Repository:
```java
Optional<Color> findByNombre(String nombre);
List<Color> findByNombreContaining(String nombre);
boolean existsByNombre(String nombre);
List<Color> findAll(); // Para selector de filtros
```

### 💼 Operaciones Importantes en Service:
- Listar todos los colores disponibles
- Crear color (validar nombre y código hex únicos)
- Obtener colores disponibles para un producto específico

---

## 7. tallas

### 📊 Entidad: `Talla.java`
### 📦 DTO: `TallaDTO.java`
### 🔄 Mapper: `TallaMapper.java`

### Variables Importantes:

| Campo | Tipo | Descripción | Uso en Servicios |
|-------|------|-------------|------------------|
| **id** | Long | Identificador único | CRUD básico |
| **valor** | String | Valor de la talla (38, 40, M, L) | **Búsqueda, ordenamiento** |
| **region** | String | Región (US, EU, UK) | **Filtros, conversión de tallas** |
| **variantes** | List\<VarianteProducto\> | Variantes con esta talla | Filtros de productos por talla |

### 🔍 Métodos Sugeridos para Repository:
```java
List<Talla> findByRegion(String region);
Optional<Talla> findByValorAndRegion(String valor, String region);
List<Talla> findByValorContaining(String valor);
List<Talla> findAllByOrderByValorAsc(); // Ordenadas
```

### 💼 Operaciones Importantes en Service:
- Listar tallas por región
- Convertir tallas entre regiones (US ↔ EU)
- Obtener tallas disponibles para un producto específico
- Validar que la talla existe antes de crear variante

---

## 8. variantes_producto

### 📊 Entidad: `VarianteProducto.java`
### 📦 DTO: `VarianteProductoDTO.java`
### 🔄 Mapper: `VarianteProductoMapper.java`

### Variables Importantes:

| Campo | Tipo | Descripción | Uso en Servicios |
|-------|------|-------------|------------------|
| **id** | Long | Identificador único | CRUD básico |
| **productoId** | Long (FK) | ID del producto | **Obtener variantes de un producto** |
| **tallaId** | Long (FK) | ID de la talla | **Filtrar por talla** |
| **colorId** | Long (FK) | ID del color | **Filtrar por color** |
| **stockCantidad** | Integer | Cantidad en stock | **Verificar disponibilidad, gestión inventario** |
| **detallesPedido** | List\<DetallePedido\> | Detalles de pedidos | Historial de ventas |

### 🔍 Métodos Sugeridos para Repository:
```java
List<VarianteProducto> findByProductoId(Long productoId);
Optional<VarianteProducto> findByProductoIdAndTallaIdAndColorId(Long productoId, Long tallaId, Long colorId);
List<VarianteProducto> findByProductoIdAndColorId(Long productoId, Long colorId);
List<VarianteProducto> findByStockCantidadGreaterThan(Integer cantidad); // Con stock
List<VarianteProducto> findByStockCantidadLessThanEqual(Integer cantidad); // Stock bajo
boolean existsByProductoIdAndTallaIdAndColorId(Long productoId, Long tallaId, Long colorId);
```

### 💼 Operaciones Importantes en Service:
- Obtener variantes disponibles de un producto (stock > 0)
- Verificar disponibilidad antes de agregar al carrito
- Reducir stock al confirmar pedido (transaccional)
- Aumentar stock al cancelar pedido
- Alertar stock bajo (< X unidades)
- Validar que no exista combinación duplicada (producto+talla+color)

---

## 9. imagenes_producto

### 📊 Entidad: `ImagenProducto.java`
### 📦 DTO: `ImagenProductoDTO.java`
### 🔄 Mapper: `ImagenProductoMapper.java`

### Variables Importantes:

| Campo | Tipo | Descripción | Uso en Servicios |
|-------|------|-------------|------------------|
| **id** | Long | Identificador único | CRUD básico |
| **productoId** | Long (FK) | ID del producto | **Obtener imágenes de un producto** |
| **colorId** | Long (FK) | ID del color (puede ser null) | **Filtrar imágenes por color** |
| **urlImagen** | String | URL de la imagen | Visualización |

### 🔍 Métodos Sugeridos para Repository:
```java
List<ImagenProducto> findByProductoId(Long productoId);
List<ImagenProducto> findByProductoIdAndColorId(Long productoId, Long colorId);
Optional<ImagenProducto> findFirstByProductoId(Long productoId); // Primera imagen
void deleteByProductoIdAndId(Long productoId, Long id);
Long countByProductoId(Long productoId);
```

### 💼 Operaciones Importantes en Service:
- Obtener todas las imágenes de un producto
- Filtrar imágenes por color seleccionado
- Obtener primera imagen de un producto (para miniatura)
- Eliminar imagen
- Upload de imágenes (integración con storage)
- Validar que el producto tenga al menos una imagen

---

## 10. pedidos

### 📊 Entidad: `Pedido.java`
### 📦 DTO: `PedidoDTO.java`
### 🔄 Mapper: `PedidoMapper.java`

### Variables Importantes:

| Campo | Tipo | Descripción | Uso en Servicios |
|-------|------|-------------|------------------|
| **id** | Long | Identificador único | Seguimiento de pedido |
| **usuarioId** | Long (FK) | ID del usuario | **Historial de pedidos por usuario** |
| **estado** | String | Estado del pedido | **Filtrar por estado, actualizar estado** |
| **totalPagar** | BigDecimal | Total del pedido | Reportes, facturación |
| **direccionEnvioId** | Long | ID dirección de envío | Información de entrega |
| **metodoPago** | String | Método de pago usado | Reportes financieros |
| **fechaPedido** | LocalDateTime | Fecha del pedido | **Ordenamiento, filtros por fecha** |
| **detalles** | List\<DetallePedido\> | Líneas del pedido | Información completa del pedido |

### Estados Comunes:
- PENDIENTE
- CONFIRMADO
- PROCESANDO
- ENVIADO
- ENTREGADO
- CANCELADO

### 🔍 Métodos Sugeridos para Repository:
```java
List<Pedido> findByUsuarioId(Long usuarioId);
List<Pedido> findByUsuarioIdOrderByFechaPedidoDesc(Long usuarioId);
List<Pedido> findByEstado(String estado);
List<Pedido> findByFechaPedidoBetween(LocalDateTime inicio, LocalDateTime fin);
Optional<Pedido> findByIdAndUsuarioId(Long id, Long usuarioId);
List<Pedido> findByEstadoIn(List<String> estados); // Múltiples estados
BigDecimal sumTotalPagarByFechaPedidoBetween(LocalDateTime inicio, LocalDateTime fin);
```

### 💼 Operaciones Importantes en Service:
- Crear pedido desde carrito (transaccional: crear pedido + detalles + reducir stock)
- Actualizar estado del pedido
- Cancelar pedido (devolver stock si está en estado cancelable)
- Calcular total desde detalles
- Obtener historial de pedidos por usuario
- Reportes de ventas por período
- Notificaciones al cambiar estado

---

## 11. detalles_pedido

### 📊 Entidad: `DetallePedido.java`
### 📦 DTO: `DetallePedidoDTO.java`
### 🔄 Mapper: `DetallePedidoMapper.java`

### Variables Importantes:

| Campo | Tipo | Descripción | Uso en Servicios |
|-------|------|-------------|------------------|
| **id** | Long | Identificador único | CRUD básico |
| **pedidoId** | Long (FK) | ID del pedido | **Obtener detalles de un pedido** |
| **varianteProductoId** | Long (FK) | ID de la variante | Información del producto |
| **cantidad** | Integer | Cantidad comprada | **Cálculos, validación stock** |
| **precioUnitario** | BigDecimal | Precio al momento de compra | **Cálculo de subtotal** |

### 🔍 Métodos Sugeridos para Repository:
```java
List<DetallePedido> findByPedidoId(Long pedidoId);
List<DetallePedido> findByVarianteProductoId(Long varianteProductoId);
Long countByVarianteProductoId(Long varianteProductoId); // Productos más vendidos
@Query("SELECT SUM(d.cantidad) FROM DetallePedido d WHERE d.varianteProductoId = :varianteId")
Integer sumCantidadByVarianteProductoId(Long varianteId);
```

### 💼 Operaciones Importantes en Service:
- Calcular subtotal (cantidad × precioUnitario)
- Validar stock disponible antes de crear detalle
- Obtener productos más vendidos
- Calcular total del pedido (suma de subtotales)
- Congelar precio al momento de compra (no usar precio actual)

---

## 12. comentarios

### 📊 Entidad: `Comentario.java`
### 📦 DTO: `ComentarioDTO.java`
### 🔄 Mapper: `ComentarioMapper.java`

### Variables Importantes:

| Campo | Tipo | Descripción | Uso en Servicios |
|-------|------|-------------|------------------|
| **id** | Long | Identificador único | CRUD básico |
| **usuarioId** | Long (FK) | ID del usuario | **Validar autor, filtrar comentarios por usuario** |
| **productoId** | Long (FK) | ID del producto | **Obtener comentarios de un producto** |
| **puntuacion** | Integer | Calificación (1-5 estrellas) | **Calcular promedio de calificación** |
| **comentario** | String (TEXT) | Texto del comentario | Visualización |
| **fecha** | LocalDateTime | Fecha del comentario | Ordenamiento, filtros |

### 🔍 Métodos Sugeridos para Repository:
```java
List<Comentario> findByProductoIdOrderByFechaDesc(Long productoId);
List<Comentario> findByUsuarioId(Long usuarioId);
boolean existsByUsuarioIdAndProductoId(Long usuarioId, Long productoId);
@Query("SELECT AVG(c.puntuacion) FROM Comentario c WHERE c.productoId = :productoId")
Double calcularPromedioCalificacion(Long productoId);
Long countByProductoId(Long productoId);
List<Comentario> findByPuntuacion(Integer puntuacion);
```

### 💼 Operaciones Importantes en Service:
- Crear comentario (validar que el usuario compró el producto)
- Validar que el usuario no comente más de una vez por producto
- Calcular calificación promedio del producto
- Obtener comentarios recientes de un producto
- Moderar comentarios (aprobar/rechazar)
- Reportar comentarios inapropiados

---

## 🏗️ Estructura Sugerida de Archivos

Para cada entidad, se recomienda crear:

```
📁 repository/
   └── UsuarioRepository.java (interface extends JpaRepository)

📁 service/
   ├── UsuarioService.java (interface con métodos de negocio)
   └── impl/
       └── UsuarioServiceImpl.java (implementación)
```

### Ejemplo de Repository:
```java
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
```

### Ejemplo de Service (Interface):
```java
public interface UsuarioService {
    UsuarioDTO crear(UsuarioDTO usuarioDTO);
    UsuarioDTO obtenerPorId(Long id);
    UsuarioDTO obtenerPorEmail(String email);
    List<UsuarioDTO> listarTodos();
    UsuarioDTO actualizar(Long id, UsuarioDTO usuarioDTO);
    void eliminar(Long id);
}
```

### Ejemplo de ServiceImpl:
```java
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioMapper usuarioMapper;
    
    @Override
    public UsuarioDTO crear(UsuarioDTO usuarioDTO) {
        // Validar que el email no exista
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new BusinessException("El email ya está registrado");
        }
        
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario = usuarioRepository.save(usuario);
        
        return usuarioMapper.toDTO(usuario);
    }
    
    // ... otros métodos
}
```

---

## 🔐 Consideraciones Importantes

### Transaccionalidad
- Usar `@Transactional` en operaciones que modifiquen múltiples entidades
- Ejemplo: Crear pedido (Pedido + DetallesPedido + Actualizar Stock)

### Validaciones
- Validar unicidad (email, nombre, etc.)
- Validar existencia de FK antes de crear/actualizar
- Validar stock antes de crear pedido
- Validar permisos (usuario solo puede modificar sus datos)

### Manejo de Errores
- Usar excepciones personalizadas (NotFoundException, BusinessException, etc.)
- No exponer detalles internos al cliente

### Performance
- Usar paginación para listados grandes
- Considerar índices en campos de búsqueda frecuente
- Usar proyecciones cuando no se necesiten todos los campos

---

## 📊 Resumen de Relaciones

| Entidad | Relaciones Importantes |
|---------|------------------------|
| Usuario | → Direcciones, Pedidos, Comentarios |
| Producto | → Marca, Categoria, Variantes, Imágenes, Comentarios |
| VarianteProducto | → Producto, Talla, Color |
| Pedido | → Usuario, DetallesPedido |
| DetallePedido | → Pedido, VarianteProducto |
| Comentario | → Usuario, Producto |

---

**¡Usa esta guía como referencia para implementar los servicios y repositorios!** 🚀

**Recuerda**: Los DTOs y Mappers ya están creados, solo necesitas crear los Repositories, Services e Implementations siguiendo esta guía.


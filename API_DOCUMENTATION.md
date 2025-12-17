# 📚 Documentación de API - Bata Perú Clone

Esta documentación detalla los endpoints disponibles en el backend para el clon de la tienda Bata.

**Base URL:** `http://localhost:8081/api`

**Última actualización:** 17 de Diciembre 2025

---

## ✅ Health Check

### Verificar Estado del Servidor
Endpoint para verificar que la API está funcionando correctamente.

*   **Método:** `GET`
*   **Endpoint:** `/health`
*   **URL completa:** `http://localhost:8081/api/health`
*   **Respuesta (200 OK):**
    ```json
    {
      "status": "UP",
      "message": "BataPeru API está funcionando correctamente"
    }
    ```

### Test Simple
*   **Método:** `GET`
*   **Endpoint:** `/test`
*   **URL completa:** `http://localhost:8081/api/test`
*   **Respuesta (200 OK):** `"API funcionando correctamente"`

---

## 🔐 Autenticación y Usuarios (`/auth`)

### 1. Registrar Nuevo Usuario
Crea una cuenta para un cliente nuevo.

*   **Método:** `POST`
*   **Endpoint:** `/auth/registro`
*   **URL completa:** `http://localhost:8081/api/auth/registro`
*   **Headers:** `Content-Type: application/json`
*   **Body (JSON):**
    ```json
    {
      "email": "juan.perez@example.com",
      "contrasenaHash": "password123",
      "nombres": "Juan",
      "apellidos": "Perez",
      "telefono": "999888777"
    }
    ```
*   **Respuesta (201 Created):**
    ```json
    {
      "id": 1,
      "email": "juan.perez@example.com",
      "contrasenaHash": "password123",
      "nombres": "Juan",
      "apellidos": "Perez",
      "telefono": "999888777",
      "fechaRegistro": "2025-12-16T22:00:00",
      "direccionesIds": null,
      "pedidosIds": null
    }
    ```
*   **Errores posibles:**
    *   `400 Bad Request`: "El email es obligatorio" o "La contraseña es obligatoria"
    *   `400 Bad Request`: "El correo electrónico ya está en uso"

### 2. Iniciar Sesión
Valida credenciales y retorna los datos del usuario.

*   **Método:** `POST`
*   **Endpoint:** `/auth/login`
*   **URL completa:** `http://localhost:8081/api/auth/login`
*   **Headers:** `Content-Type: application/json`
*   **Body (JSON):**
    ```json
    {
      "email": "juan.perez@example.com",
      "password": "password123"
    }
    ```
*   **Respuesta (200 OK):**
    ```json
    {
      "id": 1,
      "email": "juan.perez@example.com",
      "contrasenaHash": "password123",
      "nombres": "Juan",
      "apellidos": "Perez",
      "telefono": "999888777",
      "fechaRegistro": "2025-12-16T22:00:00",
      "direccionesIds": [1, 2],
      "pedidosIds": [101, 102]
    }
    ```
*   **Error (401 Unauthorized):** `"Credenciales inválidas"`

### 📝 Notas sobre el UsuarioDTO:
*   **Campo `contrasenaHash`:** Se usa tanto para registro como para login. 
    *   ⚠️ **Advertencia de Seguridad:** Actualmente la contraseña se almacena en texto plano. Se recomienda implementar BCrypt para hash de contraseñas en producción.
*   **Campo `fechaRegistro`:** Se genera automáticamente en el servidor al crear el usuario.
*   **Campos `direccionesIds` y `pedidosIds`:** Listas de IDs relacionados. Serán `null` para usuarios recién creados.

---

## 👟 Productos (`/productos`)

### 1. Listar Todos los Productos
Obtiene el catálogo completo.

*   **Método:** `GET`
*   **Endpoint:** `/productos`
*   **Respuesta (200 OK):**
    ```json
    [
      {
        "id": 10,
        "nombre": "Zapatilla Urbana North Star",
        "descripcion": "Zapatilla cómoda para uso diario.",
        "precioRegular": 129.90,
        "genero": "HOMBRE",
        "material": "Lona",
        "marcaId": 1,
        "categoriaId": 2,
        "imagenesIds": [101, 102],
        "variantesIds": [501, 502]
      },
      ...
    ]
    ```

### 2. Obtener Detalle de Producto
Obtiene información detallada de un producto específico.

*   **Método:** `GET`
*   **Endpoint:** `/productos/{id}`
*   **Ejemplo:** `/productos/10`
*   **Respuesta (200 OK):** Objeto `ProductoDTO`.

### 3. Filtrar por Categoría
Lista productos que pertenecen a una categoría específica (ej. "Zapatillas", "Sandalias").

*   **Método:** `GET`
*   **Endpoint:** `/productos/categoria/{categoriaId}`
*   **Ejemplo:** `/productos/categoria/2`

### 4. Filtrar por Género
Lista productos de un género específico (HOMBRE, MUJER, UNISEX, NIÑO, NIÑA).

*   **Método:** `GET`
*   **Endpoint:** `/productos/genero/{genero}`
*   **URL completa:** `http://localhost:8081/api/productos/genero/HOMBRE`
*   **Ejemplo:** `/productos/genero/MUJER`
*   **Nota:** La búsqueda es case-insensitive (puede ser "hombre", "Hombre", "HOMBRE")
*   **Respuesta (200 OK):** Lista de objetos `ProductoDTO` filtrados por género.

### 5. Crear Producto (Admin)
*   **Método:** `POST`
*   **Endpoint:** `/productos`
*   **Body (JSON):**
    ```json
    {
      "nombre": "Mocasín Cuero",
      "descripcion": "Elegante y formal",
      "precioRegular": 249.90,
      "genero": "HOMBRE",
      "material": "Cuero",
      "activo": true,
      "marcaId": 1,
      "categoriaId": 3
    }
    ```

---

## 🛒 Pedidos (`/pedidos`)

### 1. Crear Pedido (Checkout)
Registra una compra. **Nota:** Esto descuenta automáticamente el stock de las variantes seleccionadas.

*   **Método:** `POST`
*   **Endpoint:** `/pedidos`
*   **Body (JSON):**
    ```json
    {
      "usuarioId": 1,
      "direccionEnvioId": 5,
      "metodoPago": "TARJETA_CREDITO",
      "detalles": [
        {
          "varianteProductoId": 501,
          "cantidad": 1
        },
        {
          "varianteProductoId": 505,
          "cantidad": 2
        }
      ]
    }
    ```
*   **Respuesta (201 Created):**
    ```json
    {
      "id": 1001,
      "usuarioId": 1,
      "estado": "PENDIENTE",
      "totalPagar": 389.70,
      "fechaPedido": "2023-10-27T12:30:00Z",
      "detallesIds": [2001, 2002]
    }
    ```

### 2. Historial de Pedidos de Usuario
*   **Método:** `GET`
*   **Endpoint:** `/pedidos/usuario/{usuarioId}`

### 3. Ver Detalle de Pedido
*   **Método:** `GET`
*   **Endpoint:** `/pedidos/{id}`

### 4. Actualizar Estado (Admin)
Cambia el estado del pedido (ej. PENDIENTE -> ENVIADO).

*   **Método:** `PUT`
*   **Endpoint:** `/pedidos/{id}/estado`
*   **Body (Texto plano):** `ENVIADO`

---

## 📍 Direcciones (`/direcciones`)

### 1. Listar Direcciones de Usuario
*   **Método:** `GET`
*   **Endpoint:** `/direcciones/usuario/{usuarioId}`

### 2. Agregar Dirección
*   **Método:** `POST`
*   **Endpoint:** `/direcciones`
*   **Body (JSON):**
    ```json
    {
      "usuarioId": 1,
      "direccionCalle": "Av. Larco 123",
      "ciudad": "Lima",
      "provincia": "Lima",
      "codigoPostal": "15074",
      "pais": "Perú",
      "esPrincipal": true
    }
    ```

### 3. Eliminar Dirección
*   **Método:** `DELETE`
*   **Endpoint:** `/direcciones/{id}`

---

## 🏷️ Categorías (`/categorias`)

### 1. Listar Todas las Categorías
*   **Método:** `GET`
*   **Endpoint:** `/categorias`
*   **URL completa:** `http://localhost:8081/api/categorias`
*   **Respuesta (200 OK):**
    ```json
    [
      {
        "id": 1,
        "nombre": "Zapatillas",
        "descripcion": "Calzado deportivo y casual",
        "imagenUrl": "https://ejemplo.com/zapatillas.jpg",
        "activo": true
      },
      {
        "id": 2,
        "nombre": "Sandalias",
        "descripcion": "Calzado fresco para verano",
        "imagenUrl": "https://ejemplo.com/sandalias.jpg",
        "activo": true
      }
    ]
    ```

### 2. Crear Nueva Categoría
*   **Método:** `POST`
*   **Endpoint:** `/categorias`
*   **URL completa:** `http://localhost:8081/api/categorias`
*   **Headers:** `Content-Type: application/json`
*   **Body (JSON):**
    ```json
    {
      "nombre": "Botas",
      "descripcion": "Calzado para temporadas frías",
      "imagenUrl": "https://ejemplo.com/botas.jpg",
      "activo": true
    }
    ```
*   **Respuesta (200 OK):** Retorna el objeto `CategoriaDTO` creado con su ID.

### 3. Eliminar Categoría
*   **Método:** `DELETE`
*   **Endpoint:** `/categorias/{id}`
*   **URL completa:** `http://localhost:8081/api/categorias/1`
*   **Respuesta (204 No Content):** Sin contenido en el body.

---

## 🏢 Marcas (`/marcas`)

### 1. Listar Todas las Marcas
*   **Método:** `GET`
*   **Endpoint:** `/marcas`
*   **URL completa:** `http://localhost:8081/api/marcas`
*   **Respuesta (200 OK):**
    ```json
    [
      {
        "id": 1,
        "nombre": "Bata",
        "logoUrl": "https://ejemplo.com/logo-bata.png",
        "activo": true
      },
      {
        "id": 2,
        "nombre": "North Star",
        "logoUrl": "https://ejemplo.com/logo-northstar.png",
        "activo": true
      }
    ]
    ```

### 2. Crear Nueva Marca
*   **Método:** `POST`
*   **Endpoint:** `/marcas`
*   **URL completa:** `http://localhost:8081/api/marcas`
*   **Headers:** `Content-Type: application/json`
*   **Body (JSON):**
    ```json
    {
      "nombre": "Bubblegummers",
      "logoUrl": "https://ejemplo.com/logo-bubblegummers.png",
      "activo": true
    }
    ```
*   **Respuesta (200 OK):** Retorna el objeto `MarcaDTO` creado con su ID.

### 3. Eliminar Marca
*   **Método:** `DELETE`
*   **Endpoint:** `/marcas/{id}`
*   **URL completa:** `http://localhost:8081/api/marcas/1`
*   **Respuesta (204 No Content):** Sin contenido en el body.

---

## 🏷️ Categorías y Marcas

### Categorías (`/categorias`)
*   `GET /categorias`: Listar todas.
*   `POST /categorias`: Crear nueva (Body: `{"nombre": "Zapatillas"}`).
*   `DELETE /categorias/{id}`: Eliminar.

### Marcas (`/marcas`)
*   `GET /marcas`: Listar todas.
*   `POST /marcas`: Crear nueva (Body: `{"nombre": "Bata", "logoUrl": "..."}`).
*   `DELETE /marcas/{id}`: Eliminar.

---

## ⚠️ Notas para Frontend

1.  **Manejo de IDs:** La mayoría de las relaciones en los DTOs se manejan por IDs (ej. `marcaId`, `variantesIds`). Si necesitas mostrar el nombre de la marca en la lista de productos, deberás cruzar la información con la lista de marcas o hacer una petición adicional si el DTO no incluye el nombre explícito.
2.  **Variantes:** Un producto tiene "Variantes" (Talla + Color + Stock). Al comprar, debes enviar el ID de la **Variante**, no del Producto padre.
3.  **Imágenes:** El endpoint de productos devuelve una lista de IDs de imágenes (`imagenesIds`). Deberás tener una lógica para recuperar las URLs de esas imágenes si no están embebidas.

---

## 🔄 Cambios Recientes

### 🆕 17 de Diciembre 2025 - Correcciones de LazyInitializationException y Nuevas Funcionalidades:

1. **Solución Global de LazyInitializationException:**
   - ✅ Agregado `GlobalExceptionHandler` para manejo centralizado de errores
   - ✅ Todos los métodos de lectura ahora usan `@Transactional(readOnly = true)`
   - ✅ Métodos de creación crean DTOs manualmente para evitar problemas de sesión
   - Servicios corregidos: `MarcaService`, `CategoriaService`, `ColorService`, `TallaService`, `UsuarioService`, `ProductoService`, `DireccionService`, `PedidoService`, `ComentarioService`, `VarianteProductoService`, `ImagenProductoService`, `DetallePedidoService`

2. **Nuevo Endpoint - Filtrado de Productos por Género:**
   - `GET /productos/genero/{genero}` - Filtra productos por género
   - Búsqueda case-insensitive (acepta HOMBRE, hombre, Hombre, etc.)
   - Géneros válidos: HOMBRE, MUJER, UNISEX, NIÑO, NIÑA

3. **Mejoras en el Manejo de Errores:**
   - Respuestas JSON estructuradas para todos los errores
   - Incluye timestamp, status, error type, message, path y details
   - Manejo específico para:
     - `DataIntegrityViolationException` (400) - Violaciones de unicidad o constraints
     - `IllegalArgumentException` (400) - Argumentos inválidos
     - `NullPointerException` (500) - Valores nulos inesperados
     - `Exception` genérica (500) - Otros errores

4. **Validaciones Mejoradas:**
   - Validación de nombre obligatorio en `MarcaController`
   - Logging detallado en `MarcaService` para diagnóstico
   - Valor por defecto `true` para campo `activo` si es null

5. **Ejemplos de Respuestas de Error:**
   ```json
   {
     "timestamp": "2025-12-17T07:57:08.192557879",
     "status": 500,
     "error": "Error interno del servidor",
     "message": "Cannot lazily initialize collection...",
     "details": "org.hibernate.LazyInitializationException",
     "path": "/api/marcas"
   }
   ```

### ✅ 16 de Diciembre 2025 - Implementaciones Iniciales:

1. **Health Check Endpoints:**
   - Agregado `/api/health` para verificar el estado del servidor
   - Agregado `/api/test` para pruebas rápidas

2. **Actualización del UsuarioDTO:**
   - Campo `password` renombrado a `contrasenaHash` para mayor claridad
   - Agregado campo `direccionesIds` (lista de IDs de direcciones del usuario)
   - Agregado campo `pedidosIds` (lista de IDs de pedidos del usuario)
   - Campo `fechaRegistro` ahora se genera automáticamente en el servidor

3. **Mejoras en Autenticación:**
   - Mensajes de error más descriptivos en registro y login
   - Validación de campos obligatorios (email y contraseña)
   - Respuesta de error personalizada cuando las credenciales son inválidas

4. **Configuración CORS:**
   - La API ahora permite peticiones desde cualquier origen
   - Métodos permitidos: GET, POST, PUT, DELETE, OPTIONS
   - Headers permitidos: todos (*)

5. **Correcciones en Servicios:**
   - `MarcaService`: Métodos renombrados a español (`obtenerTodos()`, `crear()`, `eliminar()`)
   - `UsuarioRepository`: Método actualizado a `findByEmailAndContrasenaHash()`
   - MapStruct mappers regenerados correctamente

### 🔒 Notas de Seguridad:

⚠️ **IMPORTANTE:** Las contraseñas actualmente se almacenan en texto plano. Para un entorno de producción se recomienda:
- Implementar BCrypt para hash de contraseñas
- Implementar JWT para autenticación con tokens
- Agregar validación de complejidad de contraseñas
- Agregar rate limiting para prevenir ataques de fuerza bruta
- Implementar refresh tokens para sesiones persistentes

### 📝 Próximas Mejoras Recomendadas:

1. ✅ ~~Agregar filtros por género en productos~~ (Implementado)
2. Implementar paginación en endpoints que retornan listas grandes
3. Agregar más filtros avanzados en productos (precio, marca, material)
4. Implementar caché para mejorar rendimiento
5. Agregar validaciones con anotaciones (@Valid, @NotNull, @Email, etc.)
6. Documentar con Swagger/OpenAPI para exploración interactiva
7. Implementar DTOs de respuesta separados de DTOs de entrada
8. Agregar auditoria (createdBy, modifiedBy, timestamps)
9. Implementar búsqueda de texto completo en productos
10. Agregar endpoints de estadísticas para el panel de administración

---

## 🛠️ Configuración del Servidor

**Puerto:** 8081  
**Base de Datos:** MySQL - BataPeruDB  
**Puerto MySQL:** 3306  
**Usuario:** root  
**JPA:** Hibernate con actualización automática del esquema

### Variables de Entorno (application.properties):
```properties
spring.application.name=BataPeru
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/BataPeruDB
spring.datasource.username=root
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 📞 Soporte y Contacto

Para reportar problemas o sugerencias sobre la API, contactar al equipo de desarrollo.

**Versión de la API:** 1.1.0  
**Última actualización:** 17 de Diciembre 2025


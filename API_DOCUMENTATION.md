# 📚 Documentación de API - Bata Perú Clone

Esta documentación detalla los endpoints disponibles en el backend para el clon de la tienda Bata.

**Base URL:** `http://localhost:8080/api`

---

## 🔐 Autenticación y Usuarios (`/auth`)

### 1. Registrar Nuevo Usuario
Crea una cuenta para un cliente nuevo.

*   **Método:** `POST`
*   **Endpoint:** `/auth/registro`
*   **Body (JSON):**
    ```json
    {
      "email": "juan.perez@example.com",
      "password": "password123",
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
      "nombres": "Juan",
      "apellidos": "Perez",
      "telefono": "999888777",
      "fechaRegistro": "2023-10-27T10:00:00Z"
    }
    ```

### 2. Iniciar Sesión
Valida credenciales y retorna los datos del usuario.

*   **Método:** `POST`
*   **Endpoint:** `/auth/login`
*   **Body (JSON):**
    ```json
    {
      "email": "juan.perez@example.com",
      "password": "password123"
    }
    ```
*   **Respuesta (200 OK):** Retorna el objeto `UsuarioDTO` (igual que en registro).
*   **Error (401 Unauthorized):** Credenciales incorrectas.

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

### 4. Crear Producto (Admin)
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

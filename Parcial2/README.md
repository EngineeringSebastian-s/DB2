
# Documentación del Proyecto FashionLine

## Descripción General
FashionLine es un sistema de gestión de productos orientado a una tienda de moda. Está compuesto por una API desarrollada en Java con Spring Boot, un frontend basado en ReactJS y Bootstrap, y una base de datos alojada en MongoDB Atlas. También incluye soporte para entornos locales de desarrollo y despliegue en la nube.

---

## Tecnologías Utilizadas

### **Backend (API)**
- **Lenguaje:** Java
- **Framework:** Spring Boot
  - Gestión de dependencias con Maven.
  - Controladores REST para la gestión de endpoints.
  - Seguridad mediante JWT para autenticación y autorización.
- **Base de Datos:** MongoDB
  - En entorno de producción, se utiliza MongoDB Atlas con la base de datos `fashionline`.
  - En entorno local, se incluye un script de inicialización (`init.js`) que configura la base de datos con datos iniciales.
  - Conexión mediante Spring Data MongoDB y repositorios extendidos de `MongoRepository`.
- **Documentación:** Swagger (OpenAPI) para documentar y probar los endpoints.

**Dockerización del Entorno Local:**
- La API está preparada para ejecutarse en un entorno Dockerizado.
- Imagen oficial de MongoDB utilizada para la base de datos local.
  ```Dockerfile
  # Usar la imagen oficial de MongoDB
  FROM mongo:latest

  # Copiar el script de inicialización al directorio especial de MongoDB
  COPY init.js /docker-entrypoint-initdb.d/

  # Exponer el puerto de MongoDB
  EXPOSE 27017
  ```
- La API también tiene soporte local mediante Docker:
  ```Dockerfile
  # Uso de imagen base con Java 17
  FROM openjdk:17-slim

  # Directorio donde se colocará la aplicación en el contenedor
  WORKDIR /app

  # Copiar el archivo jar del proyecto al directorio /app en el contenedor
  COPY target/FashionLine-API-0.0.1-SNAPSHOT.jar /app/api-fashionline.jar

  # Exponer el puerto que usa la aplicación
  EXPOSE 8091

  # Comando para ejecutar aplicación
  CMD ["java","-jar","/app/api-fashionline.jar"]
  ```

**Despliegue en la Nube:**
- **API alojada en:** [Render](https://render.com) ([Enlace a la API](https://api-fashionline.onrender.com/))
- **Base de datos alojada en:** [MongoDB Atlas](https://www.mongodb.com/atlas) ([Enlace a la Base de Datos](https://fashionline-us-east-1.s54jy.mongodb.net))

---

### **Frontend**
- **Framework:** ReactJS
  - Configuración y desarrollo con Vite para optimizar el entorno de desarrollo.
  - Tipado con TypeScript para mayor robustez.
- **Estilo:** Bootstrap 5
  - Componentes responsivos y personalizables para crear una interfaz amigable.
- **Rutas:** React Router para la navegación entre páginas.
- **Gestión de Estado:** Context API para el manejo de autenticación y datos compartidos.

**Dockerización del Entorno Local:**
- El frontend está preparado para ejecutarse en un entorno Dockerizado.
  ```Dockerfile
  # Usar la imagen oficial de Node.js 20
  FROM node:20-alpine

  # Directorio de trabajo dentro del contenedor
  WORKDIR /app

  # Copiar los archivos de package.json y package-lock.json al contenedor
  COPY package.json package-lock.json ./

  # Instalar la versión específica de npm (10.8.2)
  RUN npm install -g npm@10.8.2

  # Instalar las dependencias del proyecto
  RUN npm install

  # Copiar el resto de los archivos al contenedor
  COPY . ./

  # Compilar TypeScript si es necesario
  RUN npm run build

  # Instalar el paquete 'serve' para servir los archivos estáticos
  RUN npm install -g serve

  # Exponer el puerto en el que se va a correr la aplicación (puerto por defecto 5173)
  EXPOSE 5173

  # Comando para arrancar el servidor estático de producción
  CMD ["serve", "-s", "dist", "-l", "5173"] 
  ```

**Despliegue en la Nube:**
- **Frontend alojado en:** [Netlify](https://www.netlify.com) ([Enlace a la Web](https://fashionline.netlify.app/))

---

## Arquitectura
El proyecto está estructurado en tres capas principales:

1. **Base de Datos:**
   - MongoDB es el motor de base de datos utilizado.
   - En producción, MongoDB Atlas maneja la base de datos `fashionline`.
   - En desarrollo local, se utiliza un contenedor Docker con un script de inicialización (`init.js`).

2. **API:**
   - Desarrollada en Java con Spring Boot.
   - Incluye controladores para la gestión de usuarios, productos y autenticación.
   - Servicios (Services) para implementar lógica de negocio y validaciones.
   - Repositorios (Repositories) para interactuar con MongoDB.

3. **Frontend:**
   - ReactJS y Bootstrap se combinan para crear una interfaz dinámica y atractiva.
   - Componentes reutilizables y navegación fluida entre vistas.


## Estructura del Proyecto

Este proyecto está compuesto por dos partes principales: **Backend (API)** y **Frontend**. A continuación, se detalla la estructura de carpetas y archivos para cada una de estas secciones, así como su propósito.

### 1. **Estructura de la API (Backend)**

```
E:.
├───.idea
├───.mvn
├───src
│   └───main
│       ├───java
│       │   └───fashionline
│       │       └───com
│       │           └───api
│       │               ├───Controllers
│       │               │   ├─── AuthController.java
│       │               │   ├─── ProductController.java
│       │               │   ├─── UserController.java
│       │               ├───Models
│       │               │   ├───DAO
│       │               │   │   ├─── Repository
│       │               │   │   └─── Service
│       │               │   ├───DTO
│       │               │   ├───Entity
│       │               ├───Security
│       │               │   ├─── headers
│       │               │   └─── jwt
│       │               └───Validation
│       └───resources
│           └─── application.properties
└───target
```

#### Descripción de Carpetas en el Backend:

- **`Controllers`**: Contiene los controladores de la API, que son responsables de manejar las solicitudes HTTP. Los controladores definen los puntos finales de la API y los métodos que se ejecutan cuando se accede a esos puntos finales. Ejemplos: `AuthController.java`, `ProductController.java`, `UserController.java`.

- **`Models`**: Define la estructura de los datos y la lógica de negocio del backend. Está subdividido en:
  - **`DAO`**: Contiene los repositorios que interactúan con la base de datos. Aquí se definen las clases que manejan las consultas a la base de datos.
  - **`DTO`**: Los objetos de transferencia de datos que se usan para intercambiar información entre el frontend y el backend.
  - **`Entity`**: Define las entidades que corresponden a las tablas en la base de datos (por ejemplo, `Product`, `User`, `Category`).
  
- **`Security`**: Contiene las configuraciones relacionadas con la seguridad, como la autenticación y autorización, incluyendo la implementación de JWT para el manejo de sesiones y control de acceso.

- **`Validation`**: Contiene las clases encargadas de validar los datos entrantes. Estas clases definen las reglas de validación para las entradas de los usuarios, como las clases `VProduct` y `VUser`.

- **`resources`**: Esta carpeta contiene archivos de configuración y recursos. Un archivo clave aquí es `application.properties`, que almacena la configuración del servidor, como la configuración de la base de datos y la seguridad.

### 2. **Estructura del Frontend (Web)**

```
E:.
├───public
├───src
│   ├───api
│   ├───assets
│   ├───components
│   ├───contexts
│   ├───pages
│   ├───routes
│   ├───types
│   └───utils
```

#### Descripción de Carpetas en el Frontend:

- **`api`**: Esta carpeta contiene el código relacionado con la comunicación entre el frontend y la API del backend. Archivos como `Api.tsx` y `Endpoints.tsx` gestionan las solicitudes HTTP, configurando las rutas a la API y el manejo de errores.

- **`assets`**: Contiene archivos estáticos que se usan en la aplicación web, como imágenes o íconos, almacenados en subcarpetas como `images/`.

- **`components`**: Los componentes de la interfaz de usuario (UI) que son reutilizables en diferentes partes de la aplicación. Ejemplos incluyen el `Header.tsx`, `Footer.tsx` y `Navbar.tsx`. Los componentes pueden ser más complejos, como el `Navbar`, que tiene una carpeta propia (`Navbar`), que incluye la interfaz (`INavbar.ts`) y la implementación (`Navbar.tsx`).

- **`contexts`**: Implementa el contexto de React, que se utiliza para gestionar el estado global de la aplicación, como la autenticación de usuarios a través de `AuthContext.tsx`.

- **`pages`**: Contiene las páginas principales de la aplicación, que son componentes grandes que representan diferentes vistas. Por ejemplo, `App.tsx`, `Login.tsx`, `Home.tsx`.

- **`routes`**: Define las rutas de la aplicación, mapeando las URLs a los componentes correspondientes. Incluye páginas como `ErrorPage.tsx` y `ProductTable.tsx`.

- **`types`**: Contiene definiciones de tipos TypeScript para asegurar la correcta gestión de tipos de datos en la aplicación. Ejemplo: `ApiResponse.tsx`.

- **`utils`**: Almacena funciones de utilidad reutilizables, como las operaciones CRUD definidas en `Crud.tsx`, que se encargan de la lógica para crear, leer, actualizar y eliminar datos.

### 3. **Estructura de la Base de Datos**

La base de datos está configurada en MongoDB y se utiliza para almacenar la información relacionada con los usuarios y los productos en el sistema de *FashionLine*. A continuación, se detalla la estructura de la base de datos, con las colecciones y los documentos que contiene.

### 1. Colección: `usuarios`

Esta colección almacena la información de los usuarios registrados en la plataforma. Cada documento en la colección representa un usuario con los siguientes campos:

- `email`: Correo electrónico del usuario, que actúa como identificador único.
- `password`: Contraseña del usuario, almacenada de forma segura utilizando hash.
- `_class`: Clase Java correspondiente a este documento, en este caso `fashionline.com.api.Models.Entity.User`.

**Ejemplo de documento en la colección `usuarios`:**

```json
{
    "email": "juan.perez@example.com",
    "password": "$2a$12$4n181KR5etTwn1Qp1ka2je79lrxXYhVuUa3kqvQfQPDx1V2LcCTKu",
    "_class": "fashionline.com.api.Models.Entity.User"
}
```

### 2. Colección: `productos`

Esta colección contiene los productos disponibles en la tienda. Cada documento en la colección describe un producto con los siguientes campos:

- `name`: Nombre del producto.
- `description`: Descripción detallada del producto.
- `price`: Precio del producto.
- `stock`: Cantidad de unidades disponibles en inventario.
- `category`: Categoría a la que pertenece el producto, como *CLOTHING*, *FOOTWEAR*, *ACCESSORIES*, etc.
- `size`: Talla del producto (ej. S, M, L, XL, UNIQUE).

**Ejemplo de documentos en la colección `productos`:**

```json
{
    "name": "Camisa Casual Blanca",
    "description": "Camisa de algodón con diseño clásico ideal para cualquier ocasión.",
    "price": 150000,
    "stock": 30,
    "category": "CLOTHING",
    "size": "M"
}
```

```json
{
    "name": "Jean Slim Azul",
    "description": "Pantalón de mezclilla de corte ajustado para un look moderno.",
    "price": 200000,
    "stock": 20,
    "category": "CLOTHING",
    "size": "L"
}
```

## Ejecucción del Proyecto

Esta documentación describe cómo levantar y ejecutar el proyecto fashionline en tu entorno local utilizando Docker Compose. Se describen los servicios involucrados, sus configuraciones y cómo ejecutar los contenedores.

### **Requisitos Previos**
Asegúrate de tener instalados los siguientes programas:
- **Docker**: [Instrucciones de instalación](https://docs.docker.com/get-docker/)
- **Docker Compose**: Viene integrado con Docker Desktop, pero si usas Linux o configuraciones personalizadas, asegúrate de tener Docker Compose instalado. [Instrucciones de instalación](https://docs.docker.com/compose/install/)

### **Estructura del Proyecto**
El archivo `docker-compose.yml` define los servicios que componen la infraestructura del proyecto:
- **MongoDB**: La base de datos que maneja toda la información relacionada con los productos y usuarios.
- **API fashionline**: El backend de la aplicación que interactúa con la base de datos y expone los endpoints REST.
- **Web fashionline**: La aplicación frontend que se comunica con la API para mostrar información de los productos y permitir la interacción con el sistema.

### **Descripción de Servicios en `docker-compose.yml`**

#### 1. **Servicio para la Base de Datos (MongoDB)**
Este servicio ejecuta un contenedor MongoDB, con la configuración necesaria para iniciar la base de datos `Fashionline`.

```yaml
  db-fashionline:
    image: sebastian190030/db-fashionline:latest
    container_name: fashionline-db
    ports:
      - "27018:27017"
    volumes:
      - mongo_data:/data/db
    networks:
      - fashionline-network
```
**Configuración clave:**
- **Imagen**: Usa la imagen de Docker `sebastian190030/db-fashionline:latest`.
- **Puertos**: Expone el puerto 27017 del contenedor a 27018 en el host para acceder a la base de datos localmente.
- **Volúmenes**: Se utiliza un volumen (`mongo_data`) para persistir los datos de la base de datos.
- **Redes**: El contenedor se conecta a la red `fashionline-network` para comunicarse con otros servicios.

#### 2. **Servicio para la API (Backend)**
Este servicio ejecuta el contenedor de la API de fashionline, configurando las variables de entorno necesarias para conectarse a la base de datos MongoDB y manejar la lógica de autenticación y operaciones de la API.

```yaml
api-fashionline:
  image: sebastian190030/api-fashionline:latest
  container_name: fashionline-api
  environment:
      - APP_NAME=fashionline-API
      - PORT=8091
      - TITLE=fashionline API
      - DESCRIPTION=Documentación de la API REST de fashionline
      - VERSION=1.0.0
      - AUTHOR=fashionline Developers
      - DATA_CONNECTION_METHOD=mongodb
      - DATA_SOURCE_USERNAME=admin
      - DATA_SOURCE_PASSWORD=admin
      - DATA_SOURCE_DOMAIN=db-fashionline:27017
      - DATA_SOURCE_DB=fashionline
      - DATA_PARAMS=directConnection=true&serverSelectionTimeoutMS=100000&socketTimeoutMS=10000&appName=mongo
      - SECURITY_JWT_SECRET_KEY=c8e9b6803afbcfa6edd9569c94c75ff4b144622b0a0570a636dffd62c24a3476
      - SECURITY_JWT_EXPIRATION=86400000
      - SECURITY_PUBLIC_ROUTES=/auth/login,/auth/verify
      - HEADER_CORS_ALLOWED_ORIGINS=http://localhost:5173
      - DEBUGGER_MODE=INFO
  ports:
    - "8091:8091"
  depends_on:
    - db-fashionline
  networks:
    - fashionline-network
```
**Configuración clave:**
- **Imagen**: Usa la imagen de Docker `sebastian190030/api-fashionline:latest`.
- **Variables de entorno**: Incluye detalles sobre el nombre de la API, configuración JWT, detalles de conexión a la base de datos MongoDB (`localhost:27018`), y CORS para la aplicación frontend.
- **Dependencias**: El servicio depende de `db-fashionline` para asegurar que la base de datos esté lista antes de iniciar la API.
- **Puertos**: Expone el puerto 8091 para que la API sea accesible desde el navegador.

#### 3. **Servicio para la Aplicación Web (Frontend)**
Este servicio ejecuta el contenedor de la aplicación web, configurando la URL base de la API para que el frontend se comunique con el backend.

```yaml
web-fashionline:
  image: sebastian190030/web-fashionline:latest
  container_name: fashionline-web
  environment:
    - VITE_API_BASE_URL=http://localhost:8091
  ports:
    - "5173:5173"
  networks:
    - fashionline-network
```
**Configuración clave:**
- **Imagen**: Usa la imagen de Docker `sebastian190030/web-fashionline:latest`.
- **Variables de entorno**: Configura la variable `VITE_API_BASE_URL` para que el frontend se comunique con la API de fashionline a través de `http://localhost:8091`.
- **Puertos**: Expone el puerto 5173 para acceder a la aplicación web.

#### 4. **Volúmenes**
Para garantizar la persistencia de los datos de la base de datos MongoDB, se define un volumen:
```yaml
volumes:
  mongo_data:
```
El volumen `mongo_data` se utiliza para almacenar los datos de MongoDB en el sistema local.

#### 5. **Redes**
Se define una red llamada `fashionline-network` para que los contenedores puedan comunicarse entre sí.

```yaml
networks:
  fashionline-network:
    driver: bridge
```

### **Cómo Ejecutar el Proyecto**

#### **Paso 1: Clonar o Descarga el Proyecto**
Asegúrate de tener todos los archivos necesarios en tu máquina local, incluyendo el archivo `docker-compose.yml`.

#### **Paso 2: Realiza el pull de las imágenes**

   ```bash
   docker pull sebastian190030/db-fashionline:latest
   docker pull sebastian190030/api-fashionline:latest
   docker pull sebastian190030/web-fashionline:latest
   ```

#### **Paso 3: Iniciar los Servicios con Docker Compose**

Desde el directorio donde se encuentra tu archivo `docker-compose.yml`, ejecuta el siguiente comando para iniciar los contenedores:

```bash
docker-compose -p fashionline up -d
```

Este comando:
- **`-p fashionline`**: Le da el nombre `fashionline` al proyecto, lo que afecta los nombres de los contenedores, redes y volúmenes.
- **`up -d`**: Levanta los contenedores en segundo plano (modo "detached").

#### **Paso 4: Acceder a los Servicios**

- **Base de Datos (MongoDB)**: Accede a la base de datos usando la URI de conexión `mongodb://admin:admin@localhost:27018/fashionline/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongo`.
- **API fashionline**: Accede a la API en `http://localhost:8091`.
- **Aplicación Web**: Accede a la aplicación frontend en `http://localhost:5173`.

#### **Paso 5: Detener los Servicios**

Para detener todos los servicios, simplemente ejecuta:

```bash
docker-compose -p fashionline down
```

Este comando detiene y elimina todos los contenedores, redes y volúmenes definidos en el archivo `docker-compose.yml`.

## Justificación de la elección de la base de datos NoSQL

### **Selección de la Base de Datos NoSQL**  
**Base de datos NOSQL:** MongoDB  
**Justificación:**

1. **Flexibilidad en el manejo de datos:**
   MongoDB utiliza un modelo de datos basado en documentos JSON (BSON internamente), lo que permite manejar información de manera flexible y dinámica. Esta característica es especialmente importante para una tienda de ropa, donde los productos tienen características que varían constantemente, como tallas, colores, materiales, precios, entre otros. Los esquemas de MongoDB no son rígidos, lo que significa que se pueden agregar nuevos atributos a los productos sin tener que modificar una estructura de tabla fija, como ocurre en las bases de datos relacionales. Además, este modelo permite la inclusión de datos más complejos o menos estructurados, como imágenes o descripciones extendidas.

2. **Escalabilidad horizontal:**
   MongoDB está diseñado para escalar horizontalmente, lo que significa que es capaz de distribuir grandes volúmenes de datos a través de múltiples servidores (sharding). Esto es crucial en un contexto de negocio en crecimiento, como una tienda de ropa que puede expandir su catálogo o su base de clientes a medida que se expande. A medida que la cantidad de productos, clientes o transacciones aumente, MongoDB permite que el sistema crezca sin comprometer el rendimiento. Esto se traduce en una alta disponibilidad y menor riesgo de caída del sistema durante picos de tráfico o expansión de la base de datos.

3. **Consultas y agregaciones poderosas:**
   A pesar de ser una base de datos NoSQL, MongoDB ofrece un conjunto avanzado de herramientas para realizar consultas complejas y agregaciones. La capacidad de realizar búsquedas por atributos específicos, como nombre, categoría, precio, color, o incluso atributos más avanzados como rangos de precios o filtros por más de un criterio, es una característica valiosa para gestionar el inventario de productos de manera eficiente. Además, MongoDB permite realizar agregaciones complejas, como obtener el total de ventas por producto o categoría, identificar los productos más vendidos, o realizar análisis sobre tendencias de compra. Estas capacidades son esenciales para generar reportes que ayuden a la toma de decisiones estratégicas.

4. **Alto rendimiento para lecturas y escrituras:**
   MongoDB proporciona un alto rendimiento tanto para operaciones de lectura como de escritura. Esto es particularmente importante para una tienda de ropa que constantemente agrega nuevos productos, actualiza el stock o registra nuevas compras. Las operaciones de escritura son rápidas y eficientes, y la base de datos maneja grandes volúmenes de datos sin que se vea afectada la velocidad de respuesta. A su vez, la optimización de las consultas permite una experiencia de usuario rápida, lo cual es crucial para las tiendas en línea.

5. **Fácil integración con tecnologías modernas:**
   MongoDB se integra de manera sencilla con frameworks populares de desarrollo backend como Node.js, Python, y Java (En este caso). Esto facilita el trabajo de los desarrolladores, que pueden utilizar su conocimiento de estas tecnologías para crear aplicaciones de forma rápida y eficiente. La gran comunidad y el soporte de MongoDB aseguran que los desarrolladores puedan encontrar soluciones a problemas y mantenerse actualizados con las mejores prácticas. Además, su compatibilidad con diferentes herramientas de análisis de datos, como BI Connector, permite realizar informes y análisis avanzados sin complicaciones.

6. **Alta disponibilidad y resiliencia:**
   MongoDB ofrece características como replicación y distribución geográfica de datos, lo que garantiza la alta disponibilidad de la base de datos. En un negocio de e-commerce o tienda física con ventas online, la disponibilidad continua es crucial para no perder ventas ni causar frustración en los clientes. En caso de fallos en uno de los servidores, MongoDB es capaz de mantener la disponibilidad de los datos y reducir los tiempos de inactividad.

7. **Costos y eficiencia operativa:**
   Al ser una base de datos de código abierto, MongoDB permite reducir costos iniciales de implementación y mantenimiento. Además, su arquitectura orientada a documentos facilita la administración y operación en comparación con bases de datos tradicionales que requieren la administración de tablas complejas y relaciones estrictas. En combinación con su capacidad para escalar de manera eficiente, MongoDB ofrece una solución rentable para empresas en crecimiento.
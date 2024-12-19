
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

---

## Ejecución del Proyecto

### **Entorno Local**
1. Clona el repositorio de GitHub.
2. Configura las variables de entorno en el archivo `.env`.
3. Levanta los servicios de base de datos con Docker:
   ```bash
   docker-compose up -d
   ```
4. Ejecuta la API localmente:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Ejecuta el frontend:
   ```bash
   npm install
   npm run dev
   ```

### **Despliegue en Producción**
- **Frontend:** Disponible en Netlify.
- **API:** Disponible en Render.
- **Base de Datos:** MongoDB Atlas.





# **Demostración Local de Fashionline con Docker Compose**

Esta documentación describe cómo levantar y ejecutar el proyecto fashionline en tu entorno local utilizando Docker Compose. Se describen los servicios involucrados, sus configuraciones y cómo ejecutar los contenedores.

## **Requisitos Previos**
Asegúrate de tener instalados los siguientes programas:
- **Docker**: [Instrucciones de instalación](https://docs.docker.com/get-docker/)
- **Docker Compose**: Viene integrado con Docker Desktop, pero si usas Linux o configuraciones personalizadas, asegúrate de tener Docker Compose instalado. [Instrucciones de instalación](https://docs.docker.com/compose/install/)

## **Estructura del Proyecto**
El archivo `docker-compose.yml` define los servicios que componen la infraestructura del proyecto:
- **MongoDB**: La base de datos que maneja toda la información relacionada con los cultivos y sensores.
- **API fashionline**: El backend de la aplicación que interactúa con la base de datos y expone los endpoints REST.
- **Web fashionline**: La aplicación frontend que se comunica con la API para mostrar información de los cultivos y permitir la interacción con el sistema.

## **Descripción de Servicios en `docker-compose.yml`**

### 1. **Servicio para la Base de Datos (MongoDB)**
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

### 2. **Servicio para la API (Backend)**
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

### 3. **Servicio para la Aplicación Web (Frontend)**
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

### 4. **Volúmenes**
Para garantizar la persistencia de los datos de la base de datos MongoDB, se define un volumen:
```yaml
volumes:
  mongo_data:
```
El volumen `mongo_data` se utiliza para almacenar los datos de MongoDB en el sistema local.

### 5. **Redes**
Se define una red llamada `fashionline-network` para que los contenedores puedan comunicarse entre sí.

```yaml
networks:
  fashionline-network:
    driver: bridge
```

## **Cómo Ejecutar el Proyecto**

### **Paso 1: Clonar o Descarga el Proyecto**
Asegúrate de tener todos los archivos necesarios en tu máquina local, incluyendo el archivo `docker-compose.yml`.

### **Paso 2: Realiza el pull de las imágenes**

   ```bash
   docker pull sebastian190030/db-fashionline:latest
   docker pull sebastian190030/api-fashionline:latest
   docker pull sebastian190030/web-fashionline:latest
   ```

### **Paso 3: Iniciar los Servicios con Docker Compose**

Desde el directorio donde se encuentra tu archivo `docker-compose.yml`, ejecuta el siguiente comando para iniciar los contenedores:

```bash
docker-compose -p fashionline up -d
```

Este comando:
- **`-p fashionline`**: Le da el nombre `fashionline` al proyecto, lo que afecta los nombres de los contenedores, redes y volúmenes.
- **`up -d`**: Levanta los contenedores en segundo plano (modo "detached").

### **Paso 4: Acceder a los Servicios**

- **Base de Datos (MongoDB)**: Accede a la base de datos usando la URI de conexión `mongodb://admin:admin@localhost:27018/fashionline/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongo`.
- **API fashionline**: Accede a la API en `http://localhost:8091`.
- **Aplicación Web**: Accede a la aplicación frontend en `http://localhost:5173`.

### **Paso 5: Detener los Servicios**

Para detener todos los servicios, simplemente ejecuta:

```bash
docker-compose -p fashionline down
```

Este comando detiene y elimina todos los contenedores, redes y volúmenes definidos en el archivo `docker-compose.yml`.
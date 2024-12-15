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
      - SERVER_TOMCAT_TIMEOUT=600000
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
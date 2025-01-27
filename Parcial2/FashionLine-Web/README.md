# FashionLine-Web

## **Deployment**

### 1. **Creación de la Imagen Docker**


2. **Ejecuta el siguiente comando** para construir la imagen Docker a partir del `Dockerfile`:

   ```bash
   docker build --platform linux/amd64 -t sebastian190030/web-fashionline .
   ```
    - Esto construirá la imagen **`web-fashionline`** con el tag `latest`.
    -  Esto asegura que la imagen será compatible con la arquitectura `amd64` (común en servidores y equipos de desarrollo).
---

### 3. **Publicación de la Imagen en Docker Hub**

1. **Inicia sesión en Docker Hub** desde la terminal:

   ```bash
   docker login
   ```

    - Introduce tu usuario **Docker Hub** (`sebastian190030`) y la contraseña cuando se te solicite.

2. **Sube la imagen** a tu repositorio en Docker Hub:

   ```bash
   docker push sebastian190030/web-fashionline:latest
   ```

    - Este comando subirá la imagen al repositorio público en Docker Hub con el nombre `sebastian190030/web-fashionline` y el tag `latest`.
    - Si la imagen no tiene un tag explícito, Docker usará el tag `latest` por defecto.
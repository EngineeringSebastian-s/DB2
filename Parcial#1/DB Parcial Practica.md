# Documentación de la Base de Datos para tienda_en_linea

## Creación de la base de datos

```javascript
use tienda_en_linea
```

## 2. Creación de Colección de Productos

En MongoDB, las colecciones no necesitan ser explícitamente creadas antes de insertar documentos, pero se deja creación manual

```javascript
db.createCollection("Productos")
```

## 3. Insertar productos

### a) Producto uno

```json
db.productos.insertOne({
  "nombre": "Televisor",
  "categoria": "Electrónica",
  "precio": 299.99,
  "stock": 50,
  "proveedor": {
    "nombre": "Sony",
    "pais": "Japón"
  }
});
```

#### Resultado

```javascript
{
  acknowledged: true,
  insertedId: ObjectId('66fc04d44ee9f9ba042710bc')
}
```

### b) Producto dos

```json
db.productos.insertOne({
  "nombre": "Cafetera",
  "categoria": "Electrodomésticos",
  "precio": 89.99,
  "stock": 100,
  "proveedor": {
    "nombre": "Philips",
    "pais": "Países Bajos"
  }
});
```

#### Resultado

```javascript
{
  acknowledged: true,
  insertedId: ObjectId('66fc04dc4ee9f9ba042710bd')
}
```

### c) Producto tres

```json
db.productos.insertOne({
  "nombre": "Portátil",
  "categoria": "Informática",
  "precio": 1099.99,
  "stock": 30,
  "proveedor": {
    "nombre": "HP",
    "pais": "Estados Unidos"
  }
});
```

#### Resultado

```javascript
{
  acknowledged: true,
  insertedId: ObjectId('66fc04e24ee9f9ba042710be')
}
```

### d) Producto cuatro

```json
db.productos.insertOne({
  "nombre": "Smartphone",
  "categoria": "Electrónica",
  "precio": 499.99,
  "stock": 70,
  "proveedor": {
    "nombre": "Samsung",
    "pais": "Corea del Sur"
  }
});
```

#### Resultado

```json
{
  acknowledged: true,
  insertedId: ObjectId('66fc04e84ee9f9ba042710bf')
}
```

### e) Producto cinco

```json
db.productos.insertOne({
  "nombre": "Aspiradora",
  "categoria": "Electrodomésticos",
  "precio": 199.99,
  "stock": 80,
  "proveedor": {
    "nombre": "LG",
    "pais": "Corea del Sur"
  }
});
```

#### Resultado

```javascript
{
  acknowledged: true,
  insertedId: ObjectId('66fc04f14ee9f9ba042710c0')
}
```

## 4. Consultas

### Listar productos insertados

```javascript
db.productos.find().pretty()
```

#### Resultado:

```javascript
[
  {
    _id: ObjectId('66fc04d44ee9f9ba042710bc'),
    nombre: 'Televisor',
    categoria: 'Electrónica',
    precio: 299.99,
    stock: 50,
    proveedor: { nombre: 'Sony', pais: 'Japón' }
  },
  {
    _id: ObjectId('66fc04dc4ee9f9ba042710bd'),
    nombre: 'Cafetera',
    categoria: 'Electrodomésticos',
    precio: 89.99,
    stock: 100,
    proveedor: { nombre: 'Philips', pais: 'Países Bajos' }
  },
  {
    _id: ObjectId('66fc04e24ee9f9ba042710be'),
    nombre: 'Portátil',
    categoria: 'Informática',
    precio: 1099.99,
    stock: 30,
    proveedor: { nombre: 'HP', pais: 'Estados Unidos' }
  },
  {
    _id: ObjectId('66fc04e84ee9f9ba042710bf'),
    nombre: 'Smartphone',
    categoria: 'Electrónica',
    precio: 499.99,
    stock: 70,
    proveedor: { nombre: 'Samsung', pais: 'Corea del Sur' }
  },
  {
    _id: ObjectId('66fc04f14ee9f9ba042710c0'),
    nombre: 'Aspiradora',
    categoria: 'Electrodomésticos',
    precio: 199.99,
    stock: 80,
    proveedor: { nombre: 'LG', pais: 'Corea del Sur' }
  }
]
```

### 2. Consulta para encontrar todos los productos que tengan un precio mayor a $100:
```javascript
db.productos.find({ "precio": { $gt: 100 } }).pretty();
```

#### Resultado:
```javascript
[
  {
    _id: ObjectId('66fc04d44ee9f9ba042710bc'),
    nombre: 'Televisor',
    categoria: 'Electrónica',
    precio: 299.99,
    stock: 50,
    proveedor: { nombre: 'Sony', pais: 'Japón' }
  },
  {
    _id: ObjectId('66fc04e24ee9f9ba042710be'),
    nombre: 'Portátil',
    categoria: 'Informática',
    precio: 1099.99,
    stock: 30,
    proveedor: { nombre: 'HP', pais: 'Estados Unidos' }
  },
  {
    _id: ObjectId('66fc04e84ee9f9ba042710bf'),
    nombre: 'Smartphone',
    categoria: 'Electrónica',
    precio: 499.99,
    stock: 70,
    proveedor: { nombre: 'Samsung', pais: 'Corea del Sur' }
  },
  {
    _id: ObjectId('66fc04f14ee9f9ba042710c0'),
    nombre: 'Aspiradora',
    categoria: 'Electrodomésticos',
    precio: 199.99,
    stock: 80,
    proveedor: { nombre: 'LG', pais: 'Corea del Sur' }
  }
]
```

### 3. Consulta para encontrar los productos que tienen "Electrónica" como categoría:
```javascript
db.productos.find({ "categoria": "Electrónica" }).pretty();
```
#### Resultado:
```javascript
[
  {
    _id: ObjectId('66fc04d44ee9f9ba042710bc'),
    nombre: 'Televisor',
    categoria: 'Electrónica',
    precio: 299.99,
    stock: 50,
    proveedor: { nombre: 'Sony', pais: 'Japón' }
  },
  {
    _id: ObjectId('66fc04e84ee9f9ba042710bf'),
    nombre: 'Smartphone',
    categoria: 'Electrónica',
    precio: 499.99,
    stock: 70,
    proveedor: { nombre: 'Samsung', pais: 'Corea del Sur' }
  }
]
```

### 4. Consulta para encontrar productos cuyo stock sea menor a 50:
```javascript
db.productos.find({ "stock": { $lt: 50 } }).pretty();
```

#### Resultado:
```javascript
[
  {
    _id: ObjectId('66fc04e24ee9f9ba042710be'),
    nombre: 'Portátil',
    categoria: 'Informática',
    precio: 1099.99,
    stock: 30,
    proveedor: { nombre: 'HP', pais: 'Estados Unidos' }
  }
]
```

### 5. Actualizar el stock de un producto:

```javascript
db.productos.updateOne(
  { "nombre": "Televisor" },
  { $set: { "stock": 45 } }
);
```

#### Resultado:
```javascript
{
  acknowledged: true,
  insertedId: null,
  matchedCount: 1,
  modifiedCount: 1,
  upsertedCount: 0
}

[
  {
    _id: ObjectId('66fc04d44ee9f9ba042710bc'),
    nombre: 'Televisor',
    categoria: 'Electrónica',
    precio: 299.99,
    stock: 45,
    proveedor: { nombre: 'Sony', pais: 'Japón' }
  }
]
```

### 6. Agregar una nueva calificación a un producto:
Supongamos que deseas agregar una nueva calificación al "Televisor":
```javascript
db.productos.updateOne(
  { "nombre": "Televisor" },
  { $push: { "calificaciones": { "usuario": "Usuario3", "puntuacion": 4, "comentario": "Buen producto" } } } 
);
```

#### Resultado:
```javascript
{
  acknowledged: true,
  insertedId: null,
  matchedCount: 1,
  modifiedCount: 1,
  upsertedCount: 0
}

[
  {
    _id: ObjectId('66fc04d44ee9f9ba042710bc'),
    nombre: 'Televisor',
    categoria: 'Electrónica',
    precio: 299.99,
    stock: 45,
    proveedor: { nombre: 'Sony', pais: 'Japón' },
    calificaciones: [
      {
        usuario: 'Usuario3',
        puntuacion: 4,
        comentario: 'Buen producto'
      }
    ]
  }
]
```

### 7. Borrar productos que sean de la categoría "Electrodomésticos" y cuyo stock sea mayor a 90:
```javascript
db.productos.deleteMany({ "categoria": "Electrodomésticos", "stock": { $gt: 90 } });
```

#### Resultado:
```javascript
{ acknowledged: true, deletedCount: 1 }

[
  {
    _id: ObjectId('66fc04f14ee9f9ba042710c0'),
    nombre: 'Aspiradora',
    categoria: 'Electrodomésticos',
    precio: 199.99,
    stock: 80,
    proveedor: { nombre: 'LG', pais: 'Corea del Sur' }
  }
]
```

### 8. Obtener el producto con la puntuación promedio más alta:

```javascript
db.productos.aggregate([
  { $unwind: "$calificaciones" },
  { $group: { _id: "$nombre", promedio: { $avg: "$calificaciones.puntuacion" } } },
  { $sort: { promedio: -1 } },
  { $limit: 1 }
]);
```

#### Resultado:
```javascript
[ { _id: 'Televisor', promedio: 4 } ]
```

### 9. Contar cuántos productos hay por cada categoría:
```javascript
db.productos.aggregate([
  { $group: { _id: "$categoria", total: { $sum: 1 } } }
]);
```

#### Resultado:
```javascript
[
  { _id: 'Electrónica', total: 2 },
  { _id: 'Informática', total: 1 },
  { _id: 'Electrodomésticos', total: 1 }
]
```

### 10. Encontrar los productos que fueron agregados durante el año 2023:

```javascript
db.productos.find({ "fecha_agregado": { $gte: ISODate("2023-01-01T00:00:00Z"), $lt: ISODate("2024-01-01T00:00:00Z") } }).pretty();
```

#### Resultado:
```javascript

```

#### Modificacion de fecha agregado para consulta:
```javascript
db.productos.updateMany({}, { $set: { "fecha_agregado": ISODate("2023-01-15T00:00:00Z") } });
```

#### Resultado:
```javascript
[
  {
    _id: ObjectId('66fc04d44ee9f9ba042710bc'),
    nombre: 'Televisor',
    categoria: 'Electrónica',
    precio: 299.99,
    stock: 45,
    proveedor: { nombre: 'Sony', pais: 'Japón' },
    calificaciones: [
      {
        usuario: 'Usuario3',
        puntuacion: 4,
        comentario: 'Buen producto'
      }
    ],
    fecha_agregado: ISODate('2023-01-15T00:00:00.000Z')
  },
  {
    _id: ObjectId('66fc04e24ee9f9ba042710be'),
    nombre: 'Portátil',
    categoria: 'Informática',
    precio: 1099.99,
    stock: 30,
    proveedor: { nombre: 'HP', pais: 'Estados Unidos' },
    fecha_agregado: ISODate('2023-01-15T00:00:00.000Z')
  },
  {
    _id: ObjectId('66fc04e84ee9f9ba042710bf'),
    nombre: 'Smartphone',
    categoria: 'Electrónica',
    precio: 499.99,
    stock: 70,
    proveedor: { nombre: 'Samsung', pais: 'Corea del Sur' },
    fecha_agregado: ISODate('2023-01-15T00:00:00.000Z')
  },
  {
    _id: ObjectId('66fc04f14ee9f9ba042710c0'),
    nombre: 'Aspiradora',
    categoria: 'Electrodomésticos',
    precio: 199.99,
    stock: 80,
    proveedor: { nombre: 'LG', pais: 'Corea del Sur' },
    fecha_agregado: ISODate('2023-01-15T00:00:00.000Z')
  }
]
```

## Consola

```bash
Clink v1.6.21 is available.
- To apply the update, run 'clink update'.
- To stop checking for updates, run 'clink set clink.autoupdate off'.
- To view the release notes, visit the Releases page:
  https://github.com/chrisant996/clink/releases

C:\Program Files (x86)\cmder
λ mongosh
Current Mongosh Log ID: 66fc044d4ee9f9ba042710bb
Connecting to:          mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.3.0
Using MongoDB:          7.0.14
Using Mongosh:          2.3.0
mongosh 2.3.1 is available for download: https://www.mongodb.com/try/download/shell

For mongosh info see: https://www.mongodb.com/docs/mongodb-shell/

------
   The server generated these startup warnings when booting
   2024-09-12T08:25:54.704-05:00: Access control is not enabled for the database. Read and write access to data and configuration is unrestricted
------

test> use tienda_en_linea
switched to db tienda_en_linea
tienda_en_linea> db.createCollection("Productos")
{ ok: 1 }
tienda_en_linea> show collections;
Productos
tienda_en_linea> db.productos.insertOne({"nombre": "Televisor", "categoria": "Electrónica", "precio": 299.99, "stock": 50, "proveedor": {"nombre": "Sony", "pais": "Japón"}});
{
  acknowledged: true,
  insertedId: ObjectId('66fc04d44ee9f9ba042710bc')
}
tienda_en_linea> db.productos.insertOne({"nombre": "Cafetera", "categoria": "Electrodomésticos", "precio": 89.99, "stock": 100, "proveedor": {"nombre": "Philips", "pais": "Países Bajos"}});
{
  acknowledged: true,
  insertedId: ObjectId('66fc04dc4ee9f9ba042710bd')
}
tienda_en_linea> db.productos.insertOne({"nombre": "Portátil", "categoria": "Informática", "precio": 1099.99, "stock": 30, "proveedor": {"nombre": "HP", "pais": "Estados Unidos"}});
{
  acknowledged: true,
  insertedId: ObjectId('66fc04e24ee9f9ba042710be')
}
tienda_en_linea> db.productos.insertOne({"nombre": "Smartphone", "categoria": "Electrónica", "precio": 499.99, "stock": 70, "proveedor": {"nombre": "Samsung", "pais": "Corea del Sur"}});
{
  acknowledged: true,
  insertedId: ObjectId('66fc04e84ee9f9ba042710bf')
}
tienda_en_linea> db.productos.insertOne({"nombre": "Aspiradora", "categoria": "Electrodomésticos", "precio": 199.99, "stock": 80, "proveedor": {"nombre": "LG", "pais": "Corea del Sur"}});
{
  acknowledged: true,
  insertedId: ObjectId('66fc04f14ee9f9ba042710c0')
}
tienda_en_linea> db.productos.find().pretty()
[
  {
    _id: ObjectId('66fc04d44ee9f9ba042710bc'),
    nombre: 'Televisor',
    categoria: 'Electrónica',
    precio: 299.99,
    stock: 50,
    proveedor: { nombre: 'Sony', pais: 'Japón' }
  },
  {
    _id: ObjectId('66fc04dc4ee9f9ba042710bd'),
    nombre: 'Cafetera',
    categoria: 'Electrodomésticos',
    precio: 89.99,
    stock: 100,
    proveedor: { nombre: 'Philips', pais: 'Países Bajos' }
  },
  {
    _id: ObjectId('66fc04e24ee9f9ba042710be'),
    nombre: 'Portátil',
    categoria: 'Informática',
    precio: 1099.99,
    stock: 30,
    proveedor: { nombre: 'HP', pais: 'Estados Unidos' }
  },
  {
    _id: ObjectId('66fc04e84ee9f9ba042710bf'),
    nombre: 'Smartphone',
    categoria: 'Electrónica',
    precio: 499.99,
    stock: 70,
    proveedor: { nombre: 'Samsung', pais: 'Corea del Sur' }
  },
  {
    _id: ObjectId('66fc04f14ee9f9ba042710c0'),
    nombre: 'Aspiradora',
    categoria: 'Electrodomésticos',
    precio: 199.99,
    stock: 80,
    proveedor: { nombre: 'LG', pais: 'Corea del Sur' }
  }
]
tienda_en_linea> db.productos.find({ "precio": { $gt: 100 } }).pretty();
[
  {
    _id: ObjectId('66fc04d44ee9f9ba042710bc'),
    nombre: 'Televisor',
    categoria: 'Electrónica',
    precio: 299.99,
    stock: 50,
    proveedor: { nombre: 'Sony', pais: 'Japón' }
  },
  {
    _id: ObjectId('66fc04e24ee9f9ba042710be'),
    nombre: 'Portátil',
    categoria: 'Informática',
    precio: 1099.99,
    stock: 30,
    proveedor: { nombre: 'HP', pais: 'Estados Unidos' }
  },
  {
    _id: ObjectId('66fc04e84ee9f9ba042710bf'),
    nombre: 'Smartphone',
    categoria: 'Electrónica',
    precio: 499.99,
    stock: 70,
    proveedor: { nombre: 'Samsung', pais: 'Corea del Sur' }
  },
  {
    _id: ObjectId('66fc04f14ee9f9ba042710c0'),
    nombre: 'Aspiradora',
    categoria: 'Electrodomésticos',
    precio: 199.99,
    stock: 80,
    proveedor: { nombre: 'LG', pais: 'Corea del Sur' }
  }
]
tienda_en_linea> db.productos.find({ "categoria": "Electrónica" }).pretty();
[
  {
    _id: ObjectId('66fc04d44ee9f9ba042710bc'),
    nombre: 'Televisor',
    categoria: 'Electrónica',
    precio: 299.99,
    stock: 50,
    proveedor: { nombre: 'Sony', pais: 'Japón' }
  },
  {
    _id: ObjectId('66fc04e84ee9f9ba042710bf'),
    nombre: 'Smartphone',
    categoria: 'Electrónica',
    precio: 499.99,
    stock: 70,
    proveedor: { nombre: 'Samsung', pais: 'Corea del Sur' }
  }
]
tienda_en_linea> db.productos.find({ "stock": { $lt: 50 } }).pretty();
[
  {
    _id: ObjectId('66fc04e24ee9f9ba042710be'),
    nombre: 'Portátil',
    categoria: 'Informática',
    precio: 1099.99,
    stock: 30,
    proveedor: { nombre: 'HP', pais: 'Estados Unidos' }
  }
]
tienda_en_linea>

tienda_en_linea> db.productos.updateOne({ "nombre": "Televisor" },{ $set: { "stock": 45 } });
{
  acknowledged: true,
  insertedId: null,
  matchedCount: 1,
  modifiedCount: 1,
  upsertedCount: 0
}
tienda_en_linea> db.productos.find({ "nombre": "Televisor" }).pretty();
[
  {
    _id: ObjectId('66fc04d44ee9f9ba042710bc'),
    nombre: 'Televisor',
    categoria: 'Electrónica',
    precio: 299.99,
    stock: 45,
    proveedor: { nombre: 'Sony', pais: 'Japón' }
  }
]
tienda_en_linea> db.productos.updateOne({ "nombre": "Televisor" },{ $push: { "calificaciones": { "usuario": "Usuario3","puntuacion": 4, "comentario": "Buen producto" } } } );
{
  acknowledged: true,
  insertedId: null,
  matchedCount: 1,
  modifiedCount: 1,
  upsertedCount: 0
}
tienda_en_linea> db.productos.find({ "nombre": "Televisor" }).pretty();
[
  {
    _id: ObjectId('66fc04d44ee9f9ba042710bc'),
    nombre: 'Televisor',
    categoria: 'Electrónica',
    precio: 299.99,
    stock: 45,
    proveedor: { nombre: 'Sony', pais: 'Japón' },
    calificaciones: [
      {
        usuario: 'Usuario3',
        puntuacion: 4,
        comentario: 'Buen producto'
      }
    ]
  }
]
tienda_en_linea> db.productos.deleteMany({ "categoria": "Electrodomésticos", "stock": { $gt: 90 } });
{ acknowledged: true, deletedCount: 1 }
tienda_en_linea> db.productos.find({ "categoria": "Electrodomésticos" }).pretty();
[
  {
    _id: ObjectId('66fc04f14ee9f9ba042710c0'),
    nombre: 'Aspiradora',
    categoria: 'Electrodomésticos',
    precio: 199.99,
    stock: 80,
    proveedor: { nombre: 'LG', pais: 'Corea del Sur' }
  }
]
tienda_en_linea> db.productos.aggregate([{ $unwind: "$calificaciones" },{ $group: { _id: "$nombre", promedio: { $avg: "$calificaciones.puntuacion" } } },{ $sort: { promedio: -1 } },{ $limit: 1 }]);
[ { _id: 'Televisor', promedio: 4 } ]
tienda_en_linea> db.productos.aggregate([{ $group: { _id: "$categoria", total: { $sum: 1 } } }]);
[
  { _id: 'Electrónica', total: 2 },
  { _id: 'Informática', total: 1 },
  { _id: 'Electrodomésticos', total: 1 }
]
tienda_en_linea> db.productos.find({ "fecha_agregado": { $gte: ISODate("2023-01-01T00:00:00Z"), $lt: ISODate("2024-01-01T00:00:00Z") } }).pretty();

tienda_en_linea>db.productos.updateMany({}, { $set: { "fecha_agregado": ISODate("2023-01-15T00:00:00Z") } });
{
  acknowledged: true,
  insertedId: null,
  matchedCount: 4,
  modifiedCount: 4,
  upsertedCount: 0
}
tienda_en_linea>db.productos.find({ "fecha_agregado": { $gte: ISODate("2023-01-01T00:00:00Z"), $lt: ISODate("2024-01-01T00:00:00Z") } }).pretty();
[
  {
    _id: ObjectId('66fc04d44ee9f9ba042710bc'),
    nombre: 'Televisor',
    categoria: 'Electrónica',
    precio: 299.99,
    stock: 45,
    proveedor: { nombre: 'Sony', pais: 'Japón' },
    calificaciones: [
      {
        usuario: 'Usuario3',
        puntuacion: 4,
        comentario: 'Buen producto'
      }
    ],
    fecha_agregado: ISODate('2023-01-15T00:00:00.000Z')
  },
  {
    _id: ObjectId('66fc04e24ee9f9ba042710be'),
    nombre: 'Portátil',
    categoria: 'Informática',
    precio: 1099.99,
    stock: 30,
    proveedor: { nombre: 'HP', pais: 'Estados Unidos' },
    fecha_agregado: ISODate('2023-01-15T00:00:00.000Z')
  },
  {
    _id: ObjectId('66fc04e84ee9f9ba042710bf'),
    nombre: 'Smartphone',
    categoria: 'Electrónica',
    precio: 499.99,
    stock: 70,
    proveedor: { nombre: 'Samsung', pais: 'Corea del Sur' },
    fecha_agregado: ISODate('2023-01-15T00:00:00.000Z')
  },
  {
    _id: ObjectId('66fc04f14ee9f9ba042710c0'),
    nombre: 'Aspiradora',
    categoria: 'Electrodomésticos',
    precio: 199.99,
    stock: 80,
    proveedor: { nombre: 'LG', pais: 'Corea del Sur' },
    fecha_agregado: ISODate('2023-01-15T00:00:00.000Z')
  }
]
tienda_en_linea>
```
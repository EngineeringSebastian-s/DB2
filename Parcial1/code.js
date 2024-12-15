// Cambiar a la base de datos tienda_en_linea
use tienda_en_linea

// Crear la colección Productos
db.createCollection("Productos")

// Insertar productos
db.productos.insertOne({"nombre": "Televisor", "categoria": "Electrónica", "precio": 299.99, "stock": 50, "proveedor": {"nombre": "Sony", "pais": "Japón"}})
db.productos.insertOne({"nombre": "Cafetera", "categoria": "Electrodomésticos", "precio": 89.99, "stock": 100, "proveedor": {"nombre": "Philips", "pais": "Países Bajos"}})
db.productos.insertOne({"nombre": "Portátil", "categoria": "Informática", "precio": 1099.99, "stock": 30, "proveedor": {"nombre": "HP", "pais": "Estados Unidos"}})
db.productos.insertOne({"nombre": "Smartphone", "categoria": "Electrónica", "precio": 499.99, "stock": 70, "proveedor": {"nombre": "Samsung", "pais": "Corea del Sur"}})
db.productos.insertOne({"nombre": "Aspiradora", "categoria": "Electrodomésticos", "precio": 199.99, "stock": 80, "proveedor": {"nombre": "LG", "pais": "Corea del Sur"}})

// Consultar todos los productos
db.productos.find().pretty()

// Consultar productos con precio mayor a 100
db.productos.find({ "precio": { $gt: 100 } }).pretty()

// Consultar productos de la categoría "Electrónica"
db.productos.find({ "categoria": "Electrónica" }).pretty()

// Consultar productos con stock menor a 50
db.productos.find({ "stock": { $lt: 50 } }).pretty()

// Actualizar el stock de un producto
db.productos.updateOne({ "nombre": "Televisor" }, { $set: { "stock": 45 } })

// Consultar el producto actualizado
db.productos.find({ "nombre": "Televisor" }).pretty()

// Agregar una nueva calificación a un producto
db.productos.updateOne({ "nombre": "Televisor" }, { $push: { "calificaciones": { "usuario": "Usuario3", "puntuacion": 4, "comentario": "Buen producto" } } })

// Consultar el producto con la nueva calificación
db.productos.find({ "nombre": "Televisor" }).pretty()

// Borrar productos de la categoría Electrodomésticos con stock mayor a 90
db.productos.deleteMany({ "categoria": "Electrodomésticos", "stock": { $gt: 90 } })

// Consultar productos de la categoría Electrodomésticos después de la eliminación
db.productos.find({ "categoria": "Electrodomésticos" }).pretty()

// Obtener el producto con la puntuación promedio más alta
db.productos.aggregate([{ $unwind: "$calificaciones" }, { $group: { _id: "$nombre", promedio: { $avg: "$calificaciones.puntuacion" } } }, { $sort: { promedio: -1 } }, { $limit: 1 }])

// Contar productos por categoría
db.productos.aggregate([{ $group: { _id: "$categoria", total: { $sum: 1 } } }])

// Consultar productos agregados en el año 2023 (una vez que se tenga el campo fecha_agregado)
db.productos.find({ "fecha_agregado": { $gte: ISODate("2023-01-01T00:00:00Z"), $lt: ISODate("2024-01-01T00:00:00Z") } }).pretty()

// Agregar fecha_agregado a todos los productos existentes
db.productos.updateMany({}, { $set: { "fecha_agregado": ISODate("2023-01-15T00:00:00Z") } })

// Consultar productos agregados en el año 2023 después de actualizar
db.productos.find({ "fecha_agregado": { $gte: ISODate("2023-01-01T00:00:00Z"), $lt: ISODate("2024-01-01T00:00:00Z") } }).pretty()

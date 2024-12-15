# Documentación de la Base de Datos para la Biblioteca Municipal en MongoDB

## 1. Creación Base de datos

```
use biblioteca
```

## 2. Creación de colecciones necesarias

En MongoDB, las colecciones no necesitan ser explícitamente creadas antes de insertar documentos, pero se deja creación manual

### Colección de Usuarios

#### Creación colección

```mongodb
db.createCollection("Usuarios")
```

### Colección de Libros

### Creación colección

```mongodb
db.createCollection("Libros")
```

## 3. Insercción de Documentos

#### Inserción de Usuarios

Cada usuario tiene los siguientes campos:
- `nombre`: El nombre del usuario.
- `apellido`: El apellido del usuario.
- `correo`: Correo electrónico del usuario, con dominios variados.
- `librosPrestados`: Una lista de objetos que representan los libros prestados por el usuario, con los campos:
  - `idLibro`: El ID del libro prestado (relacionado con la colección de Libros).
  - `fechaPrestamo`: Fecha en la que se prestó el libro.


```mongodb
db.Usuarios.insertMany([
  {
    nombre: "Carlos",
    apellido: "González",
    correo: "carlos.gonzalez@medellin.gov.co",
    librosPrestados: [
      { idLibro: ObjectId("64d0c0a6f4c6c527a45d1234"), fechaPrestamo: new Date("2024-01-15") },
      { idLibro: ObjectId("64d0c0a6f4c6c527a45d5678"), fechaPrestamo: new Date("2024-02-03") }
    ]
  },
  {
    nombre: "Lucía",
    apellido: "Martínez",
    correo: "lucia.martinez@elpoli.edu.co",
    librosPrestados: [
      { idLibro: ObjectId("64d0c0a6f4c6c527a45d2345"), fechaPrestamo: new Date("2024-03-12") }
    ]
  },
  {
    nombre: "Andrés",
    apellido: "Salazar",
    correo: "andres.salazar@gmail.com",
    librosPrestados: [
      { idLibro: ObjectId("64d0c0a6f4c6c527a45d3456"), fechaPrestamo: new Date("2024-05-22") }
    ]
  },
  {
    nombre: "María",
    apellido: "Rodríguez",
    correo: "maria.rodriguez@medellin.gov.co",
    librosPrestados: [
      { idLibro: ObjectId("64d0c0a6f4c6c527a45d4567"), fechaPrestamo: new Date("2024-07-01") }
    ]
  },
  {
    nombre: "Juan",
    apellido: "López",
    correo: "juan.lopez@elpoli.edu.co",
    librosPrestados: [
      { idLibro: ObjectId("64d0c0a6f4c6c527a45d5678"), fechaPrestamo: new Date("2024-08-15") },
      { idLibro: ObjectId("64d0c0a6f4c6c527a45d6789"), fechaPrestamo: new Date("2024-09-05") }
    ]
  }
])
```

### Inserción de Libros

Cada libro tiene los siguientes campos:
- `titulo`: Título del libro.
- `autor`: Autor del libro.
- `genero`: Género literario del libro.
- `anioPublicacion`: Año de publicación del libro.
- `editorial`:  Editorial que publicó el libro.
- `disponibilidad`: Si el libro está disponible para préstamo (true/false).


```mongodb
db.Libros.insertMany([
  {
    titulo: "Don Quijote de la Mancha",
    autor: "Miguel de Cervantes",
    genero: "Novela",
    anioPublicacion: 1605,
    editorial: "Francisco de Robles",
    disponibilidad: true
  },
  {
    titulo: "Moby Dick",
    autor: "Herman Melville",
    genero: "Aventura",
    anioPublicacion: 1851,
    editorial: "Harper & Brothers",
    disponibilidad: true
  },
  {
    titulo: "Orgullo y Prejuicio",
    autor: "Jane Austen",
    genero: "Romance",
    anioPublicacion: 1813,
    editorial: "T. Egerton",
    disponibilidad: true
  },
  {
    titulo: "Cien Años de Soledad",
    autor: "Gabriel García Márquez",
    genero: "Realismo mágico",
    anioPublicacion: 1967,
    editorial: "Editorial Sudamericana",
    disponibilidad: false
  },
  {
    titulo: "1984",
    autor: "George Orwell",
    genero: "Distopía",
    anioPublicacion: 1949,
    editorial: "Secker & Warburg",
    disponibilidad: true
  },
  {
    titulo: "El Gran Gatsby",
    autor: "F. Scott Fitzgerald",
    genero: "Tragedia",
    anioPublicacion: 1925,
    editorial: "Charles Scribner's Sons",
    disponibilidad: true
  },
  {
    titulo: "La Odisea",
    autor: "Homero",
    genero: "Épico",
    anioPublicacion: -800,
    editorial: "Desconocida",
    disponibilidad: true
  },
  {
    titulo: "Crimen y Castigo",
    autor: "Fiódor Dostoyevski",
    genero: "Psicológico",
    anioPublicacion: 1866,
    editorial: "The Russian Messenger",
    disponibilidad: true
  },
  {
    titulo: "El Retrato de Dorian Gray",
    autor: "Oscar Wilde",
    genero: "Gótico",
    anioPublicacion: 1890,
    editorial: "Lippincott's Monthly Magazine",
    disponibilidad: false
  },
  {
    titulo: "En Busca del Tiempo Perdido",
    autor: "Marcel Proust",
    genero: "Modernismo",
    anioPublicacion: 1913,
    editorial: "Grasset",
    disponibilidad: true
  }
])

db.Libros.insertMany([
  {
    titulo: "El Principito",
    autor: "Antoine de Saint-Exupéry",
    genero: "Fábula",
    anioPublicacion: 1997,
    editorial: "Reynal & Hitchcock",
    disponibilidad: true
  },
  {
    titulo: "La Casa de los Espíritus",
    autor: "Isabel Allende",
    genero: "Realismo mágico",
    anioPublicacion: 1997,
    editorial: "Sudamericana",
    disponibilidad: true
  }
])


```

## 4. Actualizar el apellido por “Pérez” en un usuario.

### Comando

```mongodb
db.Usuarios.updateOne(
  { nombre: "Juan" },            
  { $set: { apellido: "Pérez" } }
)
```

### Resultado

```bash
{ "acknowledged" : true, "matchedCount" : 1, "modifiedCount" : 1 }
```

## 5. Consultar los libros con año de publicación de “1997”.


### Comando
```mongodb
db.Libros.find({ anioPublicacion: 1997 })
```

### Resultado

```bash
[                                               
  {                                             
    _id: ObjectId('66e063ea74f99765ac2710cb'),  
    titulo: 'El Principito',                    
    autor: 'Antoine de Saint-Exupéry',          
    genero: 'Fábula',                           
    anioPublicacion: 1997,                      
    editorial: 'Reynal & Hitchcock',            
    disponibilidad: true                        
  },                                            
  {                                             
    _id: ObjectId('66e063ea74f99765ac2710cc'),  
    titulo: 'La Casa de los Espíritus',         
    autor: 'Isabel Allende',                    
    genero: 'Realismo mágico',                  
    anioPublicacion: 1997,                      
    editorial: 'Sudamericana',                  
    disponibilidad: true                        
  }                                             
]                                               
```

### 6. Eliminar un libro con autor “Gabriel García Márquez”.

### Comando
```mongodb
db.Libros.deleteOne({ autor: "Gabriel García Márquez" })
```

### Resultado

```bash
{ "acknowledged" : true, "deletedCount" : 1 }
```
# Registro de Consola


```bash

Clink v1.6.21 is available.
- To apply the update, run 'clink update'.
- To stop checking for updates, run 'clink set clink.autoupdate off'.
- To view the release notes, visit the Releases page:
  https://github.com/chrisant996/clink/releases

C:\Program Files (x86)\cmder
λ mongosh
Current Mongosh Log ID: 66e05cb274f99765ac2710bb
Connecting to:          mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.3.0
Using MongoDB:          7.0.14
Using Mongosh:          2.3.0
mongosh 2.3.1 is available for download: https://www.mongodb.com/try/download/shell

For mongosh info see: https://www.mongodb.com/docs/mongodb-shell/

------
   The server generated these startup warnings when booting
   2024-09-05T19:17:55.320-05:00: Access control is not enabled for the database. Read and write access to data and configuration is unrestricted
------

test> use biblioteca
switched to db biblioteca
biblioteca> db.createCollection("Usuarios")
{ ok: 1 }
biblioteca> db.createCollection("Libros")
{ ok: 1 }
biblioteca> db.Usuarios.insertMany([   {     nombre: "Carlos",     apellido: "González",     correo: "carlos.gonzalez@mebiblioteca> db.Usuarios.insertMany([   {     nombre: "Carlos",     apellido: "González",     correo: "carlos.gonzalez@mebiblioteca> db.Usuarios.insertMany([   {     nombre: "Carlos",     apellido: "González",     correo: "carlos.gonzalez@medellin.gov.co",     librosPrestados: [       { idLibro: ObjectId("64d0c0a6f4c6c527a45d1234"), fechaPrestamo: new Date("2024-01-15") },       { idLibro: ObjectId("64d0c0a6f4c6c527a45d5678"), fechaPrestamo: new Date("2024-02-03") }     ]   },   {     nombre: "Lucía",     apellido: "Martínez",     correo: "lucia.martinez@elpoli.edu.co",     librosPrestados: [       { idLibro: ObjectId("64d0c0a6f4c6c527a45d2345"), fechaPrestamo: new Date("2024-03-12") }     ]   },   {     nombre: "Andrés",     apellido: "Salazar",     correo: "andres.salazar@gmail.com",     librosPrestados: [       { idLibro: ObjectId("64d0c0a6f4c6c527a45d3456"), fechaPrestamo: new Date("2024-05-22") }     ]   },   {     nombre: "María",     apellido: "Rodríguez",     correo: "maria.rodriguez@medellin.gov.co",     librosPrestados: [       { idLibro: ObjectId("64d0c0a6f4c6c527a45d4567"), fechaPrestamo: new Date("2024-07-01") }     ]   },   {     nombre: "Juan",     apellido: "López",     correo: "juan.lopez@elpoli.edu.co",     librosPrestados: [       { idLibro: ObjectId("64d0c0a6f4c6c527a45d5678"), fechaPrestamo: new Date("2024-08-15") },       { idLibro: ObjectId("64d0c0a6f4c6c527a45d6789"), fechaPrestamo: new Date("2024-09-05") }     ]   } ])
{
  acknowledged: true,
  insertedIds: {
    '0': ObjectId('66e062e774f99765ac2710bc'),
    '1': ObjectId('66e062e774f99765ac2710bd'),
    '2': ObjectId('66e062e774f99765ac2710be'),
    '3': ObjectId('66e062e774f99765ac2710bf'),
    '4': ObjectId('66e062e774f99765ac2710c0')
  }
}
biblioteca> db.Libros.insertMany([   {     titulo: "Don Quijote de la Mancha",     autor: "Miguel de Cervantes",     genbiblioteca> db.Libros.insertMany([   {     titulo: "Don Quijote de la Mancha",     autor: "Miguel de Cervantes",     genbiblioteca> db.Libros.insertMany([   {     titulo: "Don Quijote de la Mancha",     autor: "Miguel de Cervantes",     genbiblioteca> db.Libros.insertMany([   {     titulo: "Don Quijote de la Mancha",     autor: "Miguel de Cervantes",
 genero: "Novela",     anioPublicacion: 1605,     editorial: "Francisco de Robles",     disponibilidad: true   },   {     titulo: "Moby Dick",     autor: "Herman Melville",     genero: "Aventura",     anioPublicacion: 1851,     editorial: "Harper & Brothers",     disponibilidad: true   },   {     titulo: "Orgullo y Prejuicio",     autor: "Jane Austen",     genero: "Romance",     anioPublicacion: 1813,     editorial: "T. Egerton",     disponibilidad: true   },   {     titulo: "Cien Años de Soledad",     autor: "Gabriel García Márquez",     genero: "Realismo mágico",     anioPublicacion: 1967,     editorial: "Editorial Sudamericana",     disponibilidad: false   },   {     titulo: "1984",     autor: "George Orwell",     genero: "Distopía",     anioPublicacion: 1949,     editorial: "Secker & Warburg",     disponibilidad: true   },   {     titulo: "El Gran Gatsby",     autor: "F. Scott Fitzgerald",     genero: "Tragedia",     anioPublicacion: 1925,     editorial: "Charles Scribner's Sons",     disponibilidad: true   },   {     titulo: "La Odisea",     autor: "Homero",
genero: "Épico",     anioPublicacion: -800,     editorial: "Desconocida",     disponibilidad: true   },   {     titulo:
"Crimen y Castigo",     autor: "Fiódor Dostoyevski",     genero: "Psicológico",     anioPublicacion: 1866,     editorial: "The Russian Messenger",     disponibilidad: true   },   {     titulo: "El Retrato de Dorian Gray",     autor: "Oscar Wilde",     genero: "Gótico",     anioPublicacion: 1890,     editorial: "Lippincott's Monthly Magazine",     disponibilidad: false   },   {     titulo: "En Busca del Tiempo Perdido",     autor: "Marcel Proust",     genero: "Modernismo",

 anioPublicacion: 1913,     editorial: "Grasset",     disponibilidad: true   } ])
{
  acknowledged: true,
  insertedIds: {
    '0': ObjectId('66e062f674f99765ac2710c1'),
    '1': ObjectId('66e062f674f99765ac2710c2'),
    '2': ObjectId('66e062f674f99765ac2710c3'),
    '3': ObjectId('66e062f674f99765ac2710c4'),
    '4': ObjectId('66e062f674f99765ac2710c5'),
    '5': ObjectId('66e062f674f99765ac2710c6'),
    '6': ObjectId('66e062f674f99765ac2710c7'),
    '7': ObjectId('66e062f674f99765ac2710c8'),
    '8': ObjectId('66e062f674f99765ac2710c9'),
    '9': ObjectId('66e062f674f99765ac2710ca')
  }
}
biblioteca> db.getCollectionNames()
[ 'Libros', 'Usuarios' ]
biblioteca> db.Libros.find().pretty()
[
  {
    _id: ObjectId('66e062f674f99765ac2710c1'),
    titulo: 'Don Quijote de la Mancha',
    autor: 'Miguel de Cervantes',
    genero: 'Novela',
    anioPublicacion: 1605,
    editorial: 'Francisco de Robles',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c2'),
    titulo: 'Moby Dick',
    autor: 'Herman Melville',
    genero: 'Aventura',
    anioPublicacion: 1851,
    editorial: 'Harper & Brothers',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c3'),
    titulo: 'Orgullo y Prejuicio',
    autor: 'Jane Austen',
    genero: 'Romance',
    anioPublicacion: 1813,
    editorial: 'T. Egerton',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c4'),
    titulo: 'Cien Años de Soledad',
    autor: 'Gabriel García Márquez',
    genero: 'Realismo mágico',
    anioPublicacion: 1967,
    editorial: 'Editorial Sudamericana',
    disponibilidad: false
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c5'),
    titulo: '1984',
    autor: 'George Orwell',
    genero: 'Distopía',
    anioPublicacion: 1949,
    editorial: 'Secker & Warburg',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c6'),
    titulo: 'El Gran Gatsby',
    autor: 'F. Scott Fitzgerald',
    genero: 'Tragedia',
    anioPublicacion: 1925,
    editorial: "Charles Scribner's Sons",
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c7'),
    titulo: 'La Odisea',
    autor: 'Homero',
    genero: 'Épico',
    anioPublicacion: -800,
    editorial: 'Desconocida',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c8'),
    titulo: 'Crimen y Castigo',
    autor: 'Fiódor Dostoyevski',
    genero: 'Psicológico',
    anioPublicacion: 1866,
    editorial: 'The Russian Messenger',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c9'),
    titulo: 'El Retrato de Dorian Gray',
    autor: 'Oscar Wilde',
    genero: 'Gótico',
    anioPublicacion: 1890,
    editorial: "Lippincott's Monthly Magazine",
    disponibilidad: false
  },
  {
    _id: ObjectId('66e062f674f99765ac2710ca'),
    titulo: 'En Busca del Tiempo Perdido',
    autor: 'Marcel Proust',
    genero: 'Modernismo',
    anioPublicacion: 1913,
    editorial: 'Grasset',
    disponibilidad: true
  }
]
biblioteca> db.Usuarios.find().pretty()
[
  {
    _id: ObjectId('66e062e774f99765ac2710bc'),
    nombre: 'Carlos',
    apellido: 'González',
    correo: 'carlos.gonzalez@medellin.gov.co',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d1234'),
        fechaPrestamo: ISODate('2024-01-15T00:00:00.000Z')
      },
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d5678'),
        fechaPrestamo: ISODate('2024-02-03T00:00:00.000Z')
      }
    ]
  },
  {
    _id: ObjectId('66e062e774f99765ac2710bd'),
    nombre: 'Lucía',
    apellido: 'Martínez',
    correo: 'lucia.martinez@elpoli.edu.co',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d2345'),
        fechaPrestamo: ISODate('2024-03-12T00:00:00.000Z')
      }
    ]
  },
  {
    _id: ObjectId('66e062e774f99765ac2710be'),
    nombre: 'Andrés',
    apellido: 'Salazar',
    correo: 'andres.salazar@gmail.com',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d3456'),
        fechaPrestamo: ISODate('2024-05-22T00:00:00.000Z')
      }
    ]
  },
  {
    _id: ObjectId('66e062e774f99765ac2710bf'),
    nombre: 'María',
    apellido: 'Rodríguez',
    correo: 'maria.rodriguez@medellin.gov.co',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d4567'),
        fechaPrestamo: ISODate('2024-07-01T00:00:00.000Z')
      }
    ]
  },
  {
    _id: ObjectId('66e062e774f99765ac2710c0'),
    nombre: 'Juan',
    apellido: 'López',
    correo: 'juan.lopez@elpoli.edu.co',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d5678'),
        fechaPrestamo: ISODate('2024-08-15T00:00:00.000Z')
      },
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d6789'),
        fechaPrestamo: ISODate('2024-09-05T00:00:00.000Z')
      }
    ]
  }
]
biblioteca> db.Usuarios.updateOne(   { nombre: "Juan" },   { $set: { apellido: "Pérez" } } )
{
  acknowledged: true,
  insertedId: null,
  matchedCount: 1,
  modifiedCount: 1,
  upsertedCount: 0
}
biblioteca> db.Usuarios.find().pretty()
[
  {
    _id: ObjectId('66e062e774f99765ac2710bc'),
    nombre: 'Carlos',
    apellido: 'González',
    correo: 'carlos.gonzalez@medellin.gov.co',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d1234'),
        fechaPrestamo: ISODate('2024-01-15T00:00:00.000Z')
      },
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d5678'),
        fechaPrestamo: ISODate('2024-02-03T00:00:00.000Z')
      }
    ]
  },
  {
    _id: ObjectId('66e062e774f99765ac2710bd'),
    nombre: 'Lucía',
    apellido: 'Martínez',
    correo: 'lucia.martinez@elpoli.edu.co',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d2345'),
        fechaPrestamo: ISODate('2024-03-12T00:00:00.000Z')
      }
    ]
  },
  {
    _id: ObjectId('66e062e774f99765ac2710be'),
    nombre: 'Andrés',
    apellido: 'Salazar',
    correo: 'andres.salazar@gmail.com',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d3456'),
        fechaPrestamo: ISODate('2024-05-22T00:00:00.000Z')
      }
    ]
  },
  {
    _id: ObjectId('66e062e774f99765ac2710bf'),
    nombre: 'María',
    apellido: 'Rodríguez',
    correo: 'maria.rodriguez@medellin.gov.co',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d4567'),
        fechaPrestamo: ISODate('2024-07-01T00:00:00.000Z')
      }
    ]
  },
  {
    _id: ObjectId('66e062e774f99765ac2710c0'),
    nombre: 'Juan',
    apellido: 'Pérez',
    correo: 'juan.lopez@elpoli.edu.co',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d5678'),
        fechaPrestamo: ISODate('2024-08-15T00:00:00.000Z')
      },
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d6789'),
        fechaPrestamo: ISODate('2024-09-05T00:00:00.000Z')
      }
    ]
  }
]
biblioteca> db.Libros.find({ anioPublicacion: 1997 })

biblioteca> db.Libros.insertMany([   {     titulo: "El Principito",     autor: "Antoine de Saint-Exupéry",     genero: "Fábula",     anioPublicacion: 1997,     editorial: "Reynal & Hitchcock",     disponibilidad: true   },   {     titulo: "La Casa de los Espíritus",     autor: "Isabel Allende",     genero: "Realismo mágico",     anioPublicacion: 1997,     ededitorial: "Sudamericana",     disponibilidad: true   } ])
{
  acknowledged: true,
  insertedIds: {
    '0': ObjectId('66e063ea74f99765ac2710cb'),
    '1': ObjectId('66e063ea74f99765ac2710cc')
  }
}
biblioteca> db.Libros.find({ anioPublicacion: 1997 })
[
  {
    _id: ObjectId('66e063ea74f99765ac2710cb'),
    titulo: 'El Principito',
    autor: 'Antoine de Saint-Exupéry',
    genero: 'Fábula',
    anioPublicacion: 1997,
    editorial: 'Reynal & Hitchcock',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e063ea74f99765ac2710cc'),
    titulo: 'La Casa de los Espíritus',
    autor: 'Isabel Allende',
    genero: 'Realismo mágico',
    anioPublicacion: 1997,
    editorial: 'Sudamericana',
    disponibilidad: true
  }
]
biblioteca> db.Libros.deleteOne({ autor: "Gabriel García Márquez" })
{ acknowledged: true, deletedCount: 1 }
biblioteca> db.Usuarios.find().pretty()
[
  {
    _id: ObjectId('66e062e774f99765ac2710bc'),
    nombre: 'Carlos',
    apellido: 'González',
    correo: 'carlos.gonzalez@medellin.gov.co',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d1234'),
        fechaPrestamo: ISODate('2024-01-15T00:00:00.000Z')
      },
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d5678'),
        fechaPrestamo: ISODate('2024-02-03T00:00:00.000Z')
      }
    ]
  },
  {
    _id: ObjectId('66e062e774f99765ac2710bd'),
    nombre: 'Lucía',
    apellido: 'Martínez',
    correo: 'lucia.martinez@elpoli.edu.co',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d2345'),
        fechaPrestamo: ISODate('2024-03-12T00:00:00.000Z')
      }
    ]
  },
  {
    _id: ObjectId('66e062e774f99765ac2710be'),
    nombre: 'Andrés',
    apellido: 'Salazar',
    correo: 'andres.salazar@gmail.com',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d3456'),
        fechaPrestamo: ISODate('2024-05-22T00:00:00.000Z')
      }
    ]
  },
  {
    _id: ObjectId('66e062e774f99765ac2710bf'),
    nombre: 'María',
    apellido: 'Rodríguez',
    correo: 'maria.rodriguez@medellin.gov.co',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d4567'),
        fechaPrestamo: ISODate('2024-07-01T00:00:00.000Z')
      }
    ]
  },
  {
    _id: ObjectId('66e062e774f99765ac2710c0'),
    nombre: 'Juan',
    apellido: 'Pérez',
    correo: 'juan.lopez@elpoli.edu.co',
    librosPrestados: [
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d5678'),
        fechaPrestamo: ISODate('2024-08-15T00:00:00.000Z')
      },
      {
        idLibro: ObjectId('64d0c0a6f4c6c527a45d6789'),
        fechaPrestamo: ISODate('2024-09-05T00:00:00.000Z')
      }
    ]
  }
]
biblioteca> db.Libros.find().pretty()
[
  {
    _id: ObjectId('66e062f674f99765ac2710c1'),
    titulo: 'Don Quijote de la Mancha',
    autor: 'Miguel de Cervantes',
    genero: 'Novela',
    anioPublicacion: 1605,
    editorial: 'Francisco de Robles',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c2'),
    titulo: 'Moby Dick',
    autor: 'Herman Melville',
    genero: 'Aventura',
    anioPublicacion: 1851,
    editorial: 'Harper & Brothers',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c3'),
    titulo: 'Orgullo y Prejuicio',
    autor: 'Jane Austen',
    genero: 'Romance',
    anioPublicacion: 1813,
    editorial: 'T. Egerton',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c5'),
    titulo: '1984',
    autor: 'George Orwell',
    genero: 'Distopía',
    anioPublicacion: 1949,
    editorial: 'Secker & Warburg',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c6'),
    titulo: 'El Gran Gatsby',
    autor: 'F. Scott Fitzgerald',
    genero: 'Tragedia',
    anioPublicacion: 1925,
    editorial: "Charles Scribner's Sons",
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c7'),
    titulo: 'La Odisea',
    autor: 'Homero',
    genero: 'Épico',
    anioPublicacion: -800,
    editorial: 'Desconocida',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c8'),
    titulo: 'Crimen y Castigo',
    autor: 'Fiódor Dostoyevski',
    genero: 'Psicológico',
    anioPublicacion: 1866,
    editorial: 'The Russian Messenger',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e062f674f99765ac2710c9'),
    titulo: 'El Retrato de Dorian Gray',
    autor: 'Oscar Wilde',
    genero: 'Gótico',
    anioPublicacion: 1890,
    editorial: "Lippincott's Monthly Magazine",
    disponibilidad: false
  },
  {
    _id: ObjectId('66e062f674f99765ac2710ca'),
    titulo: 'En Busca del Tiempo Perdido',
    autor: 'Marcel Proust',
    genero: 'Modernismo',
    anioPublicacion: 1913,
    editorial: 'Grasset',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e063ea74f99765ac2710cb'),
    titulo: 'El Principito',
    autor: 'Antoine de Saint-Exupéry',
    genero: 'Fábula',
    anioPublicacion: 1997,
    editorial: 'Reynal & Hitchcock',
    disponibilidad: true
  },
  {
    _id: ObjectId('66e063ea74f99765ac2710cc'),
    titulo: 'La Casa de los Espíritus',
    autor: 'Isabel Allende',
    genero: 'Realismo mágico',
    anioPublicacion: 1997,
    editorial: 'Sudamericana',
    disponibilidad: true
  }
]
biblioteca>
```
db = db.getSiblingDB('admin');

db.createUser({
    user: 'admin',
    pwd: 'admin',
    roles: [
        {role: 'root', db: 'admin'}
    ]
});

db = db.getSiblingDB('fashionline');

db.createCollection('usuarios');
db.usuarios.insertMany([
    {
        "email": "juan.perez@example.com",
        "password": "$2a$12$4n181KR5etTwn1Qp1ka2je79lrxXYhVuUa3kqvQfQPDx1V2LcCTKu",
        "_class": "fashionline.com.api.Models.Entity.User"
    }
]);

db.createCollection('productos');
db.productos.insertMany([
        {
            "name": "Camisa Casual Blanca",
            "description": "Camisa de algodón con diseño clásico ideal para cualquier ocasión.",
            "price": 150000,
            "stock": 30,
            "category": "CLOTHING",
            "size": "M"
        },
        {
            "name": "Jean Slim Azul",
            "description": "Pantalón de mezclilla de corte ajustado para un look moderno.",
            "price": 200000,
            "stock": 20,
            "category": "CLOTHING",
            "size": "32"
        },
        {
            "name": "Chaqueta de Cuero Negra",
            "description": "Chaqueta de cuero genuino con cierre frontal y bolsillos.",
            "price": 350000,
            "stock": 10,
            "category": "OUTERWEAR",
            "size": "L"
        },
        {
            "name": "Blusa Estampada Floral",
            "description": "Blusa de manga corta con estampado floral y cuello redondo.",
            "price": 120000,
            "stock": 25,
            "category": "WOMEN",
            "size": "M"
        },
        {
            "name": "Zapatos Deportivos Unisex",
            "description": "Calzado deportivo cómodo y ligero para actividades diarias.",
            "price": 180000,
            "stock": 15,
            "category": "FOOTWEAR",
            "size": "42"
        },
        {
            "name": "Bolso Tote Negro",
            "description": "Bolso tote de cuero sintético, ideal para el día a día.",
            "price": 220000,
            "stock": 12,
            "category": "HANDBAGS",
            "size": "UNIQUE"
        },
        {
            "name": "Anillo de Plata 925",
            "description": "Anillo minimalista de plata con acabado brillante.",
            "price": 85000,
            "stock": 50,
            "category": "JEWELRY",
            "size": "6"
        },
        {
            "name": "Conjunto Deportivo para Mujer",
            "description": "Conjunto de top y leggins ideal para entrenamientos.",
            "price": 160000,
            "stock": 18,
            "category": "ACTIVEWEAR",
            "size": "S"
        },
        {
            "name": "Abrigo Largo de Lana",
            "description": "Abrigo elegante de lana para el invierno.",
            "price": 400000,
            "stock": 8,
            "category": "OUTERWEAR",
            "size": "XL"
        },
        {
            "name": "Reloj de Pulsera Clásico",
            "description": "Reloj analógico con correa de cuero genuino.",
            "price": 250000,
            "stock": 10,
            "category": "ACCESSORIES",
            "size": "UNIQUE"
        },
        {
            "name": "Cinturón de Cuero Marrón",
            "description": "Cinturón unisex de cuero con hebilla metálica.",
            "price": 95000,
            "stock": 30,
            "category": "ACCESSORIES",
            "size": "L"
        },
        {
            "name": "Vestido de Verano Estampado",
            "description": "Vestido ligero con estampado floral, perfecto para días cálidos.",
            "price": 140000,
            "stock": 20,
            "category": "WOMEN",
            "size": "M"
        },
        {
            "name": "Sandalias de Cuero",
            "description": "Sandalias cómodas con suela antideslizante.",
            "price": 120000,
            "stock": 15,
            "category": "FOOTWEAR",
            "size": "39"
        },
        {
            "name": "Sombrero Panamá",
            "description": "Sombrero de paja tejido a mano, ideal para días soleados.",
            "price": 110000,
            "stock": 25,
            "category": "ACCESSORIES",
            "size": "UNIQUE"
        },
        {
            "name": "Perfume Floral para Mujer",
            "description": "Fragancia floral fresca para uso diario.",
            "price": 180000,
            "stock": 12,
            "category": "BEAUTY",
            "size": "UNIQUE"
        },
        {
            "name": "Reloj Deportivo Digital",
            "description": "Reloj digital resistente al agua con múltiples funciones.",
            "price": 150000,
            "stock": 20,
            "category": "ACCESSORIES",
            "size": "UNIQUE"
        },
        {
            "name": "Pijama Infantil de Algodón",
            "description": "Pijama suave y cómodo con estampados divertidos.",
            "price": 80000,
            "stock": 18,
            "category": "KIDS",
            "size": "8"
        },
        {
            "name": "Camisa Formal para Hombre",
            "description": "Camisa de vestir con ajuste entallado y cuello clásico.",
            "price": 130000,
            "stock": 25,
            "category": "MEN",
            "size": "L"
        },
        {
            "name": "Sudadera con Capucha",
            "description": "Sudadera unisex de algodón con bolsillos frontales.",
            "price": 110000,
            "stock": 15,
            "category": "CLOTHING",
            "size": "XL"
        },
        {
            "name": "Blazer Casual Azul Marino",
            "description": "Blazer versátil con diseño moderno y elegante.",
            "price": 300000,
            "stock": 10,
            "category": "OUTERWEAR",
            "size": "M"
        },
        {
            "name": "Falda Plisada Beige",
            "description": "Falda midi plisada, ideal para ocasiones casuales y formales.",
            "price": 145000,
            "stock": 20,
            "category": "WOMEN",
            "size": "M"
        },
        {
            "name": "Zapatos Oxford Negros",
            "description": "Zapatos formales de cuero con suela antideslizante.",
            "price": 220000,
            "stock": 15,
            "category": "FOOTWEAR",
            "size": "42"
        },
        {
            "name": "Gorra Deportiva Negra",
            "description": "Gorra ajustable con diseño deportivo.",
            "price": 45000,
            "stock": 30,
            "category": "ACCESSORIES",
            "size": "UNIQUE"
        },
        {
            "name": "Camiseta de Entrenamiento",
            "description": "Camiseta transpirable, perfecta para rutinas de ejercicio.",
            "price": 75000,
            "stock": 25,
            "category": "ACTIVEWEAR",
            "size": "L"
        },
        {
            "name": "Abrigo Impermeable Azul",
            "description": "Abrigo resistente al agua con capucha integrada.",
            "price": 280000,
            "stock": 12,
            "category": "OUTERWEAR",
            "size": "XL"
        },
        {
            "name": "Vestido de Noche Rojo",
            "description": "Vestido elegante con detalles en encaje.",
            "price": 320000,
            "stock": 8,
            "category": "WOMEN",
            "size": "S"
        },
        {
            "name": "Zapatillas de Tela Gris",
            "description": "Zapatillas casuales cómodas y ligeras.",
            "price": 95000,
            "stock": 20,
            "category": "FOOTWEAR",
            "size": "40"
        },
        {
            "name": "Bufanda de Lana Gris",
            "description": "Bufanda tejida, perfecta para días fríos.",
            "price": 60000,
            "stock": 18,
            "category": "ACCESSORIES",
            "size": "UNIQUE"
        },
        {
            "name": "Blazer Formal Negro",
            "description": "Blazer clásico para hombres con bolsillos interiores.",
            "price": 280000,
            "stock": 10,
            "category": "MEN",
            "size": "M"
        },
        {
            "name": "Conjunto Deportivo Infantil",
            "description": "Conjunto de sudadera y pantalón para niños.",
            "price": 100000,
            "stock": 22,
            "category": "KIDS",
            "size": "10"
        },
        {
            "name": "Perfume para Hombre",
            "description": "Fragancia intensa con notas amaderadas.",
            "price": 250000,
            "stock": 10,
            "category": "BEAUTY",
            "size": "UNIQUE"
        },
        {
            "name": "Camisa Cuadros Verde",
            "description": "Camisa de algodón con patrón de cuadros y botones frontales.",
            "price": 120000,
            "stock": 30,
            "category": "CLOTHING",
            "size": "M"
        },
        {
            "name": "Leggins Negros Deportivos",
            "description": "Leggins elásticos y cómodos para entrenamientos.",
            "price": 80000,
            "stock": 25,
            "category": "ACTIVEWEAR",
            "size": "S"
        },
        {
            "name": "Zapatos de Charol Beige",
            "description": "Zapatos elegantes con acabado brillante.",
            "price": 200000,
            "stock": 18,
            "category": "FOOTWEAR",
            "size": "38"
        },
        {
            "name": "Sombrero de Lana Marrón",
            "description": "Sombrero clásico, ideal para días frescos.",
            "price": 95000,
            "stock": 15,
            "category": "ACCESSORIES",
            "size": "UNIQUE"
        },
        {
            "name": "Bolso de Mano Beige",
            "description": "Bolso compacto y elegante para uso diario.",
            "price": 190000,
            "stock": 12,
            "category": "HANDBAGS",
            "size": "UNIQUE"
        },
        {
            "name": "Collar de Oro Rosado",
            "description": "Collar elegante con diseño minimalista.",
            "price": 450000,
            "stock": 5,
            "category": "JEWELRY",
            "size": "UNIQUE"
        },
        {
            "name": "Chaleco Reflectivo",
            "description": "Chaleco para actividades al aire libre o ciclismo.",
            "price": 65000,
            "stock": 20,
            "category": "ACTIVEWEAR",
            "size": "L"
        },
        {
            "name": "Pantalón de Tela Beige",
            "description": "Pantalón formal con diseño moderno.",
            "price": 130000,
            "stock": 25,
            "category": "CLOTHING",
            "size": "32"
        },
        {
            "name": "Camisa Infantil Estampada",
            "description": "Camisa ligera con estampado de animales.",
            "price": 60000,
            "stock": 30,
            "category": "KIDS",
            "size": "6"
        },
        {
            "name": "Jersey de Lana Azul",
            "description": "Jersey cálido con diseño trenzado.",
            "price": 160000,
            "stock": 15,
            "category": "OUTERWEAR",
            "size": "L"
        },
        {
            "name": "Brazalete Deportivo",
            "description": "Brazalete ajustable para llevar el teléfono durante el entrenamiento.",
            "price": 40000,
            "stock": 50,
            "category": "ACCESSORIES",
            "size": "UNIQUE"
        },
        {
            "name": "Tacones Negros de Charol",
            "description": "Zapatos de tacón con diseño clásico y cómodo.",
            "price": 210000,
            "stock": 10,
            "category": "FOOTWEAR",
            "size": "37"
        },
        {
            "name": "Pijama de Seda Rosa",
            "description": "Pijama elegante y suave al tacto.",
            "price": 180000,
            "stock": 20,
            "category": "LINGERIE",
            "size": "M"
        },
        {
            "name": "Abrigo de Piel Sintética",
            "description": "Abrigo largo y elegante para invierno.",
            "price": 400000,
            "stock": 5,
            "category": "OUTERWEAR",
            "size": "XL"
        },
        {
            "name": "Zapatos de Correr",
            "description": "Zapatillas deportivas para running.",
            "price": 180000,
            "stock": 12,
            "category": "FOOTWEAR",
            "size": "41"
        },
        {
            "name": "Reloj de Acero Inoxidable",
            "description": "Reloj analógico resistente al agua.",
            "price": 300000,
            "stock": 8,
            "category": "ACCESSORIES",
            "size": "UNIQUE"
        },
        {
            "name": "Mono Corto para Mujer",
            "description": "Mono de tela ligera con tirantes ajustables.",
            "price": 130000,
            "stock": 18,
            "category": "WOMEN",
            "size": "S"
        },
        {
            "name": "Sudadera Oversize Gris",
            "description": "Sudadera cómoda con ajuste holgado.",
            "price": 100000,
            "stock": 25,
            "category": "CLOTHING",
            "size": "XL"
        },
        {
            "name": "Traje Formal Azul Marino",
            "description": "Traje completo de dos piezas para ocasiones especiales.",
            "price": 450000,
            "stock": 10,
            "category": "MEN",
            "size": "L"
        }
    ]
);
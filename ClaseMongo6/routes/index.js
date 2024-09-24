const express = require('express');
const router = express.Router();
const ModelTienda = require('../models/producto'); // Modelo de Mongoose

// Obtener todos los productos
router.get("/tienda", async (req, res) => {
    try {
        const productos = await ModelTienda.find();
        res.status(200).json(productos);
    } catch (error) {
        console.error('Error al obtener los productos:', error);
        res.status(500).json({ error: 'Hubo un problema al obtener los productos.' });
    }
});

// Guardar un nuevo producto (POST)
router.post("/guardar", async (req, res) => {
    const { nombre, precio, descripcion } = req.body; // Obtén los datos del cuerpo de la solicitud

    try {
        const nuevoProducto = new ModelTienda({
            nombre,
            precio,
            descripcion
        });

        await nuevoProducto.save();
        res.status(201).json({ mensaje: 'Producto guardado exitosamente.' });
    } catch (error) {
        console.error('Error al guardar el producto:', error);
        res.status(500).json({ error: 'Hubo un problema al guardar el producto.' });
    }
});

// Actualizar un producto (PUT)
router.put("/actualizar/:id", async (req, res) => {
    const { id } = req.params; // ID del producto a actualizar
    const { nuevoNombre, nuevoPrecio, nuevaDescripcion } = req.body; // Nuevos datos

    try {
        const productoActualizado = await ModelTienda.findByIdAndUpdate(
            id, 
            {
                nombre: nuevoNombre,
                precio: nuevoPrecio,
                descripcion: nuevaDescripcion
            }, 
            { new: true } // Para devolver el documento actualizado
        );

        if (!productoActualizado) {
            return res.status(404).json({ error: 'Producto no encontrado.' });
        }

        res.status(200).json({ mensaje: 'Producto actualizado exitosamente.', productoActualizado });
    } catch (error) {
        console.error('Error al actualizar el producto:', error);
        res.status(500).json({ error: 'Hubo un problema al actualizar el producto.' });
    }
});

// Eliminar un producto (DELETE)
router.delete("/eliminar/:id", async (req, res) => {
    const { id } = req.params; // ID del producto a eliminar

    try {
        const productoEliminado = await ModelTienda.findByIdAndDelete(id);

        if (!productoEliminado) {
            return res.status(404).json({ error: 'Producto no encontrado.' });
        }

        res.status(200).json({ mensaje: 'Producto eliminado exitosamente.' });
    } catch (error) {
        console.error('Error al eliminar el producto:', error);
        res.status(500).json({ error: 'Hubo un problema al eliminar el producto.' });
    }
});

module.exports = router;

const express = require('express')
const router = express.Router();
const ModelTienda = require('../models/producto')


router.get("/tienda", async(req, res) => {
    try {
        const productos = await ModelTienda.find();
        res.status(200).json(productos);
    } catch (error) {
        console.error('Error al obtener los productos:', error);
        res.status(500).json({ error: 'Hubo un problema al obtener los productos.' });
    }
    });

module.exports = router;
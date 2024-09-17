const mongoose = require('mongoose')

const tiendaSchema = new mongoose.Schema({
    nombre:{
        type: String,
        require: true,
        trim: true
    },
    precio:{
        type: Number,
        require: true,
    }
});

const ModelTienda = mongoose.model('productos', tiendaSchema);

module.exports = ModelTienda;


const mongoose = require('mongoose');
const dbconnect = () => {
    mongoose.connect('mongodb://localhost:27017/tienda',{
        useNewUrlParser: true,
        useUnifiedTopology: true,
    })
    .then(()=>{
        console.log("conectado a mongo")

    })
    .catch(err => {
        console.log("error al conectar")
    })
}
module.exports = dbconnect
// используем библиотеку express
const express = require('express');
const bodyParser = require('body-parser');
// создаем объект express
const app = express();
app.use(bodyParser.urlencoded({ extended: true }));
require('./app/routes')(app);
// говорим, что мы раздаем папку public
app.use(express.static('public'));
// говорим, что запускаемся на порту 131
app.listen(131);
console.log("Server started at 131");
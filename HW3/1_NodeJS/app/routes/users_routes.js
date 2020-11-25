bodyParser = require('body-parser').json();
const { Client } = require('pg');
const connection = new Client({
    user: 'postgres',
    host: 'localhost',
    password: 'zxcdfg270301',
    database: 'db-website-summer-practise'
});

module.exports = function (app) {
    app.get('/resume', (request, response) => {
        var result = {
            "first_name": "Данияр",
            "last_name": "Идрисов",
            "age": 19,
            "education": "До 6 класса я учился в средней общеобразовательной школе №34, а потом, сдав вступительные экзамены, я поступил в 7 класс Лицея №131, который впоследствии закончил (Не имея троек в аттестате!!!).",
            "hobbies": "Я занимался профессионально шахматами 8 лет. Играл на региональных и Всероссийских турнирах. Был чемпионом Казани как в личном, так и в командном зачете. Также я играю в Казанской футбольной любительской лиге (футбол люблю крч). А еще я учился в музыкальной школе. Ну и собственно говоря, люблю программировать!",
            "profession": "Я знаю такие языки программирования, как Java, Python и С++. Имел опыт в проектной деятельности. Умею разрабатывать сайты, верстать. Также умею работать с базами данных и знаю язык SQL-запросов.",
        }
        response.setHeader("Content-Type", "application/json");
        response.send(JSON.stringify(result));
    });

    app.post('/request', bodyParser, (request, response) => {
        let req = request.body;
        const client = new Client(connection);
        client.connect();
        client.query("INSERT INTO requests(email, first_name, last_name, request) VALUES ($1, $2, $3, $4) RETURNING *;", [req.email, req.first_name, req.last_name, req.request], (error, result) => {
            if (error) {
                console.log(error.stack);
            } else {
                console.log(result.rows[0]);
            }
        });
        response.send('Ваша заявка успешно отправлена!')
    });

    app.get('/requests', (request, response) => {
        const client = new Client(connection)
        client.connect();
        client.query('SELECT email, first_name, last_name FROM requests;',
            [], (error, result) => {
                if (error) {
                    console.log(error.stack);
                } else {
                    console.log(result.rows[0]);
                }
                response.setHeader("Content-Type", "application/json");
                response.json(result.rows);
            });
    });

}
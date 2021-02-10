const express = require('express');
const app = express();
const port = 3001;


const path = require('path');
app.use(express.static(path.join(__dirname, 'build')));


const mysql = require('mysql');
const conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'root',
    database: 'clinic_management_application'
});


const body_parser = require('body-parser');
app.use(body_parser.json());
app.use(body_parser.urlencoded({ extended: true }));

const cors = require('cors');
app.use(cors());


app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'build', 'index.html'));
});

app.get('/comps/:id', (req, res) => {
    let id = Number(req.params.id);
    conn.query(`SELECT * FROM Complaint WHERE patient_id=${id} ORDER BY created_at DESC;`, (err, result, fields) => {
        if (err)
            throw err;
        res.send(result);
    });
});

app.listen(port, () => {
    console.log('App has started...')
});
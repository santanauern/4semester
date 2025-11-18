const express = require('express');
const soma = require('./soma');

const app = express();
app.use(express.json());

app.get('/saudacao', (req, res) => {
  res.json({ mensagem: "Olá! API funcionando com CI..." });
});

app.post('/soma', (req, res) => {
  const { a, b } = req.body;
  res.json({ resultado: soma(a, b) });
});

module.exports = app;

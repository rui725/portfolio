var express = require('express');
var router = express.Router();
var exampledata = require('../exampledata.json');
/* GET home page. */
router.get('/', function (req, res) {
    res.render('index', { title: 'Express', name: 'Rui Rafael Rosillas', exampledata: exampledata });
});

module.exports = router;
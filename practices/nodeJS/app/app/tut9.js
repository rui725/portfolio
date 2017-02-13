//some core modules
//fs is a module for file handling
var fs = require("fs");
fs.writeFileSync("tut9file.txt", "I have written a txt file");
console.log(fs.readFileSync("tut9file.txt").toString());

//path is a module for paths
var path = require("path");
websiteHomePage = "Destop/Rui//index.html";
websiteContact = "Destop//Rui\/about.html";
console.log(path.normalize(websiteHomePage));
console.log(path.normalize(websiteContact));
//director name
console.log(path.dirname(websiteHomePage));
//name file
console.log(path.basename(websiteHomePage));
// name of extension file

// set interval runs forver while setTimeout runs once
setInterval(function () {
    console.log("WOW");
}, 2000);
console.log(path.extname(websiteHomePage));

console.log(__dirname);
console.log(__filename);
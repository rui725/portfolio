var movies = require("./tut8extension");

var User1Movies = movies();
User1Movies.favMovies = "Wazzup";
console.log("USER 1 favourite movie is: "+User1Movies.favMovies);
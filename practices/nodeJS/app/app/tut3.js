//reference to object
var Rui = {
    stuff: "game",
    color: "dark/blue",
}

var Person = Rui;
Person.stuff = "anime"; // basically the game changes to anime 
console.log(Person.stuff);
console.log(Person.color);

//comparing relational operators
console.log(19 == "19"); // returns true becoz it only compares the value not the type
console.log(19 === "19"); // returns false becoz it compares both value and type


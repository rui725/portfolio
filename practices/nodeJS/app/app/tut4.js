// this is an object
var Rui = {
    printMe: function () {
        console.log("Hi I'm Rui");
        console.log(this === Rui); //returns true becoz the this keyword basically refers to the one calling it
        console.log(this === global); // returns false becoz this was not called using global
    }

}

Rui.printMe(); // printMe was called through Rui 

function DoSomething() {
    console.log("I'm doing something");
    console.log(this === global); // returns true becoz it was called in global
    console.log(this === Rui); // returns false becoz it was not called using Rui

}
DoSomething(); //called using global
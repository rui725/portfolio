/* ONE WAY 
//public function becoz of export
function printMovie1() {
    console.log("MOVIE 1");
}
//public function  becoz of export
function printMovie2() {
    console.log("MOVIE 2");
}

//private function becoz not exported
function printMovie3() {
    console.log("none");
}

module.exports.movie1 = printMovie1;
module.exports.movie2 = printMovie2;

*/

/* ALTERNATIVE WAY */
// all the function and variable or objects inside are public
module.exports = {
    printMovie1: function () {
        console.log("MOVIE 1 ");
    },
    printMovie2: function () {
        console.log("MOvie 2");
    },
    iamVar:"I am a variable"

}
// all the function and variable or objects outside are private
function printMovie3() {
    console.log("MOVIE 3");
}
// PROTOTYPE basically you can add attributes on an object like it was part of it.

function User() {
    this.name = ""; // so it can be used by anyone who calls it
    this.health = 100;
    this.giveLife = function (targetPlayer) {
        targetPlayer.health += 1;
        console.log(this.name + "give 1 life to " + targetPlayer.name);
    }
}

var Rui = new User;
var Michelle = new User;
Rui.name = "rui";
Michelle.name = "michelle";
Rui.giveLife(Michelle);

console.log(Michelle.name + ": " + Michelle.health);
console.log(Rui.name + ": " + Rui.health);

// user prototypes

User.prototype.uppercut = function (targetPlayer) {
    targetPlayer.health -= 3;
    console.log(this.name + " uppercuts " + targetPlayer.name);
};

Rui.uppercut(Michelle);

console.log(Michelle.name + ": " + Michelle.health);
console.log(Rui.name + ": " + Rui.health);

User.prototype.magic = 60;

console.log(Rui.magic);
console.log(Michelle.magic);

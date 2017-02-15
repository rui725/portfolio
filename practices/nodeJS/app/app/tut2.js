// asynchrounous requests

function placeAnOrder(ordernumber) {
    console.log('Customer order', ordernumber);
    cookAndDeliverFood(function () {
        console.log('Delivered Food - Order:', ordernumber);
    });
}

function cookAndDeliverFood(callback) {
    setTimeout(callback, 5000);
}

placeAnOrder(1);
placeAnOrder(2);
placeAnOrder(3);
placeAnOrder(5);
placeAnOrder(4);

// simple server
var http = require("http");

function onRequest(request, response) {
    console.log("request" + request.url);
    response.writeHead(200, { "Context-Type": "text/plain" });
    response.write("<h1> FIRST SERVER CREATED USING NODE JS AND IT IS UP</h1>");
    //must be included to show that the response has ended and ready to send
    response.end();
}
//listens on port 8888 and does function for response and request using onRequest
http.createServer(onRequest).listen(8888);
console.log("Server is UP");
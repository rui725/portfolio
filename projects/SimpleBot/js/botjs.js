/*

    Authored By Rui Rafael Rosillas
	GitHub: rui725
	Javascript that handles the chatbox

*/

// handles pressing enter in TextArea
/*
    param:   none
	return:  none
	Authored by Rui Rafael Rosillas
*/
$('document').ready(function(){
	$('textarea#message').keyup(function(e){
		if(e.keyCode==13){
			var text = $('textarea#message').value;
			if(text != ''){
				$('#sendB').click();	
			}
			
		}
	});
});

/* used for session id if bot requires
//initiates se_id
var se_id = 0;
*/

// handles sending and responses for the chatbox
/*
    param:   none
	return:  none
	Authored by Rui Rafael Rosillas
*/

function sendMessage() {
	//httprequest
	var xhttp = new XMLHttpRequest();
	
	//text message
	var message = document.getElementById("message").value;
	
	//add text to chatbox (id 0 user id, 1 bot)
	var usermsg = addMessageToChatbox(message, 0);
	//clears message
	document.getElementById("message").value = "";
	
	// get chatbox value
	var chatboxval = document.getElementById("chatbox").innerHTML;
	var chatbox = document.getElementById("chatbox");
	chatbox.innerHTML = chatboxval + usermsg;
	
	/* optional use for other bots 
	//	using api.motion.ai
	// random session_id
	if(this.se_id == 0){
	    this.se_id = Math.random();
	    this.se_id.toString(36);
	    this.se_id = this.se_id.toString(36).substr(2,9);
	}
	
	//message = message.replace(/ /g, "+");
	//xhttp.open("GET", "https://api.motion.ai/messageBot?msg=" + encodeURIComponent(message)+ "&session=" + this.se_id+ "&bot=67463&key=a91eb15d0cfebbdd8baab9c257ff4bcc", true);
	*/
	
	// using Botlibre
	xhttp.open("POST","http://www.botlibre.com/rest/api/chat", true);
	xhttp.setRequestHeader("Content-Type", "application/xml");
	var xml =  "<chat instance=\"165\" application=\"5880804394997444354\" ><message>" + message+"</message></chat>";
	
	console.log(xhttp);
	xhttp.send(xml);
    
	xhttp.onreadystatechange = function(){		
		if(this.readyState==4 && this.status == 200){
			var text = xhttp.responseText;
			var botmsg = addMessageToChatbox(text, 1);
			chatboxval = document.getElementById("chatbox").innerHTML;
			chatbox.innerHTML = chatboxval + botmsg;
			chatbox.scrollTop = chatbox.scrollHeight;
		}
	}
	chatbox.scrollTop = chatbox.scrollHeight;
}

// adds message for user and bot responses in the chatbox
/*
    param:   message - string of text to be displayed
			 user - type of user 
	             0 - user
				 1 - bot
	return:  html - generated html code with the message 
	Authored by Rui Rafael Rosillas
*/
function addMessageToChatbox(message, user){
	var chatbox = document.getElementById("chatbox").value;
	var t = new Date();
	var t = t.getHours() + ":" + t.getMinutes();
	html = "<div id='tmsg' width='100%'>";
	if(user == 0){
		html +="<div class=\"user\">";
	    html +="<div id=\"text\">";
		html += message;
		html += "</div>";
	    html += "<div id=\"time\"><small><small>";
		html += "Sent "+t;
		html += "</small></small>";
		html += "</div></div>";
	} else {
		html +="<div class=\"bot\">";
	    html +="<div id=\"text\">";
		html += message;
		html += "</div>";
	    html += "<div id=\"time\"><small><small>";
		html += "Sent "+t;
		html += "</small></small>";
		html +="</div></div>";
	}
	html += "</div><br/><br/><br/><br />"
	return html;
}
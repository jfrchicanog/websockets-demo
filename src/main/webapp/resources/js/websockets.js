var websocket;
function conecta() {
	 websocket = new WebSocket ("ws://localhost:8080/websockets-demo-1.0.0/prueba");
	 websocket.onmessage = entraMensaje;
	 websocket.onopen = function () {
		 console.log("Websocket abierto");
	 }
	 websocket.onclose = function () {
		 console.log("Websocket cerrado");
	 }
	 websocket.onerror = function () {
		 console.log("Error en Websocket")
	 }
}

function entraMensaje(evento) {
	mensaje = evento.data
	console.log("Recibido mensaje: "+mensaje);
	document.getElementById("salidaTexto").innerHTML += (mensaje +"\n");
}


function mandaMensaje() {
	var texto = document.getElementById("entradaTexto").value;
	websocket.send(texto);
	document.getElementById("entradaTexto").value = "";
	console.log("Mando " + texto+ " al servidor");
}

window.addEventListener("load", conecta, false);

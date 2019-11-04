/**
 * Metodos para actulizar los pulsadores de Coiaca
 */


//cambiar color fondo del boton depenndiendo del Estado de todos los botones
function cargarColorbotones(serial){
	console.log("llego a cargar los botones");
	for(var i=0; i<serial.length; i++){
		console.log("se busca el boton: "+ "boton1"+serial[i]);
		var x = document.getElementById("boton1"+serial[i]).firstChild.data;
		if(x=="Encendido"){
			console.log("Boton Encendido");
			  document.getElementById("boton1"+serial[i]).style.backgroundColor = "#2653d4";	
			  document.getElementById("boton1"+serial[i]).disabled = false;
		}
		else if(x=="Apagado"){
			console.log("Boton Apagado");
			document.getElementById("boton1"+serial[i]).style.backgroundColor = "#A9A9A9";
			document.getElementById("boton1"+serial[i]).disabled = false;
			
		}
		else if(x=="Desconocido"){
			console.log("Boton Desconocido");
			document.getElementById("boton1"+serial[i]).style.backgroundColor = "#A9A9A9";
			document.getElementById("boton1"+serial[i]).disabled = true;
		}
		
		if(document.getElementById("boton2"+serial[i])!=null){
			var x = document.getElementById("boton2"+serial[i]).firstChild.data;
			if(x=="Encendido"){
				console.log("Boton Encendido");
				  document.getElementById("boton2"+serial[i]).style.backgroundColor = "#2653d4";	
				  document.getElementById("boton2"+serial[i]).disabled = false;
			}
			else if(x=="Apagado"){
				console.log("Boton Apagado");
				document.getElementById("boton2"+serial[i]).style.backgroundColor = "#A9A9A9";
				document.getElementById("boton2"+serial[i]).disabled = false;
				
			}
			else if(x=="Desconocido"){
				console.log("Boton Desconocido");
				document.getElementById("boton2"+serial[i]).style.backgroundColor = "#A9A9A9";
				document.getElementById("boton2"+serial[i]).disabled = true;
			}
			
		}
	}
}

//cambiar color fondo del boton depenndiendo del Estado de todos los botones
function cargarColorUnboton(serial){
	console.log("llego a cargar un boton");
		console.log("se busca el boton: "+ "boton1"+serial);
//		setTimeout(function(){ colorear(serial) }, 1000);
		colorear(serial);
}

function colorear(serial){
	var x = document.getElementById("boton1"+serial).firstChild.data;
	console.log("contenido a colorear: " + x);
	if(x=="Encendido"){
		console.log("Boton Encendido");
		  document.getElementById("boton1"+serial).style.backgroundColor = "#2653d4";	
		  document.getElementById("boton1"+serial).disabled = false;
	}
	else if(x=="Apagado"){
		console.log("Boton Apagado");
		document.getElementById("boton1"+serial).style.backgroundColor = "#A9A9A9";
		document.getElementById("boton1"+serial).disabled = false;
		
	}
	else if(x=="Desconocido"){
		console.log("Boton Desconocido");
		document.getElementById("boton1"+serial).style.backgroundColor = "#A9A9A9";
		document.getElementById("boton1"+serial).disabled = true;
	}
	
	if(document.getElementById("boton2"+serial)!=null){
		var x = document.getElementById("boton2"+serial).firstChild.data;
		if(x=="Encendido"){
			console.log("Boton Encendido");
			  document.getElementById("boton2"+serial).style.backgroundColor = "#2653d4";	
			  document.getElementById("boton2"+serial).disabled = false;
		}
		else if(x=="Apagado"){
			console.log("Boton Apagado");
			document.getElementById("boton2"+serial).style.backgroundColor = "#A9A9A9";
			document.getElementById("boton2"+serial).disabled = false;
			
		}
		else if(x=="Desconocido"){
			console.log("Boton Desconocido");
			document.getElementById("boton2"+serial).style.backgroundColor = "#A9A9A9";
			document.getElementById("boton2"+serial).disabled = true;
		}
		
	}
}
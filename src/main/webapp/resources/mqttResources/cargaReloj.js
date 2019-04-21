	//Funcion para Cargar los relojes

	function updateWiget(){
		var gaugeDataHum = {'data': 50.0}
		var gaugeDataTempC = {'data': 25.0}
		var gaugeDataHiC = {'data': 27.0}
		var gaugeDataTempF = {'data': 80.0}
		var gaugeDataHiF = {'data': 82.0}

	// create a chart and set options
	// note that via the c3.js API we bind the chart to the element with id equal to chart1
	var chart = c3.generate({
		bindto: '#chartHum',
		data: {
			json: gaugeDataHum,
			type: 'gauge',
		},
		gauge: {
			label:{
				//returning here the value and not the ratio
				format: function(value, ratio){ return value;}
			},
			min: 0,
			max: 100,
			width: 15,
			units: 'Percentage' //this is only the text for the label
		}
});

var chart1 = c3.generate({
		bindto: '#chartTempC',
		data: {
			json: gaugeDataTempC,
			type: 'gauge',
		},
		gauge: {
			label:{
				//returning here the value and not the ratio
				format: function(value, ratio){ return value;}
			},
			min: 0,
			max: 60,
			width: 15,
			units: 'Celsius' //this is only the text for the label
		}
});

var chart2 = c3.generate({
		bindto: '#chartHiC',
		data: {
			json: gaugeDataHiC,
			type: 'gauge',
		},
		gauge: {
			label:{
				//returning here the value and not the ratio
				format: function(value, ratio){ return value;}
			},
			min: 0,
			max: 70,
			width: 15,
			units: 'Celsius' //this is only the text for the label
		}
	});


var chart3 = c3.generate({
		bindto: '#chartTempF',
		data: {
			json: gaugeDataTempF,
			type: 'gauge',
		},
		gauge: {
			label:{
				//returning here the value and not the ratio
				format: function(value, ratio){ return value;}
			},
			min: 0,
			max: 140,
			width: 15,
			units: 'Fahrenheit' //this is only the text for the label
		}
});

var chart4 = c3.generate({
		bindto: '#chartHiF',
		data: {
			json: gaugeDataHiF,
			type: 'gauge',
		},
		gauge: {
			label:{
				//returning here the value and not the ratio
				format: function(value, ratio){ return value;}
			},
			min: 0,
			max: 150,
			width: 15,
			units: 'Fahrenheit' //this is only the text for the label
		}
	});
}







// Called when a message arrives
function onMessageArrived(message) {
    console.log("onMessageArrived: " + message.payloadString);
	var inputAll= message.payloadString;

	try {	
	
	var dataObj = JSON.parse(inputAll)
	var state = dataObj.tempC; 
    document.getElementById("messages").innerHTML += '<span>Topic: ' + message.destinationName + '  | ' + inputAll + '</span><br/>';


	var gaugeDataHum = {'data': dataObj.hum}
	var gaugeDataTempC = {'data': dataObj.tempC}
	var gaugeDataHiC = {'data': dataObj.hiC}
	var gaugeDataTempF = {'data': dataObj.tempF}
	var gaugeDataHiF = {'data': dataObj.hiF}

	var progreso =  animateprogress("humedad",dataObj.hum);
	var progreso =  animateprogress("temperaturac",dataObj.tempC);
	var progreso =  animateprogress("sensacionc",dataObj.hiC);
	var progreso =  animateprogress("temperaturaf",dataObj.tempF);
	var progreso =  animateprogress("sensacionf",dataObj.hiF);
	// create a chart and set options
	// note that via the c3.js API we bind the chart to the element with id equal to chart1
	
	var chart = c3.generate({
		bindto: '#chartHum',
		data: {
			json: gaugeDataHum,
			type: 'gauge',
		},
		gauge: {
			label:{
				//returning here the value and not the ratio
				format: function(state, ratio){ return state;}
			},
			min: 0,
			max: 100,
			width: 15,
			units: 'value' //this is only the text for the label
		}
	});

var chart1 = c3.generate({
		bindto: '#chartTempC',
		data: {
			json: gaugeDataTempC,
			type: 'gauge',
		},
		gauge: {
			label:{
				//returning here the value and not the ratio
				format: function(value, ratio){ return value;}
			},
			min: 0,
			max: 60,
			width: 15,
			units: 'value' //this is only the text for the label
		}
});

var chart2 = c3.generate({
		bindto: '#chartHiC',
		data: {
			json: gaugeDataHiC,
			type: 'gauge',
		},
		gauge: {
			label:{
				//returning here the value and not the ratio
				format: function(value, ratio){ return value;}
			},
			min: 0,
			max: 70,
			width: 15,
			units: 'value' //this is only the text for the label
		}
	});


var chart3 = c3.generate({
		bindto: '#chartTempF',
		data: {
			json: gaugeDataTempF,
			type: 'gauge',
		},
		gauge: {
			label:{
				//returning here the value and not the ratio
				format: function(value, ratio){ return value;}
			},
			min: 0,
			max: 140,
			width: 15,
			units: 'value' //this is only the text for the label
		}
});

var chart4 = c3.generate({
		bindto: '#chartHiF',
		data: {
			json: gaugeDataHiF,
			type: 'gauge',
		},
		gauge: {
			label:{
				//returning here the value and not the ratio
				format: function(value, ratio){ return value;}
			},
			min: 0,
			max: 150,
			width: 15,
			units: 'value' //this is only the text for the label
		}
	});
	
}
catch(err) {
  document.getElementById("messages").innerHTML = err.message;
}

}

function animateprogress (id, val){		
	var getRequestAnimationFrame = function () {  /* <------- Declaro getRequestAnimationFrame intentando obtener la máxima compatibilidad con todos los navegadores */
		return window.requestAnimationFrame ||
		window.webkitRequestAnimationFrame ||   
		window.mozRequestAnimationFrame ||
		window.oRequestAnimationFrame ||
		window.msRequestAnimationFrame ||
		function ( callback ){
			window.setTimeout(enroute, 1 / 60 * 1000);
		};
		
	};
	
	var fpAnimationFrame = getRequestAnimationFrame();   
	var i = 0.0;
	var animacion = function () {
		
	if (i<=val) 
		{
			var valorr = i+"%";
			var titulo = id+"s";
			document.getElementById(id).style.width = valorr;
			var paragraph = document.getElementById(titulo);
			paragraph.innerHTML = id+"<span class='float-right'>"+val+"</span>";     /* <---- Incremento el porcentaje y lo muestro en la etiqueta span */
			i++;
			fpAnimationFrame(animacion);          /* <------------------ Mientras que el contador no llega al porcentaje fijado la función vuelve a llamarse con fpAnimationFrame     */
		}
										
	}

		fpAnimationFrame(animacion);   /*  <---- Llamo la función animación por primera vez usando fpAnimationFrame para que se ejecute a 60fps  */
				
}


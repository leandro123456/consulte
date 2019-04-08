
function sleep(milliseconds) {
	alert("llego al sleep");
 var start = new Date().getTime();
 for (var i = 0; i < 1e7; i++) {
  if ((new Date().getTime() - start) > milliseconds) {
   break;
  }
 }
}



function cargarDivs() {
	alert("llego");
	var indicadores = '<div class="col-lg-6 mb-4"><div class="card shadow mb-4"><div class="card-header py-3"><h6 class="m-0 font-weight-bold text-primary">Sensor de Temperatura Y Humedad</h6></div><div class="card-body"><h4 id="humedads" class="small font-weight-bold"></h4><div class="progress mb-4"><div class="progress-bar" id="humedad" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div></div><h4 id="temperaturacs" class="small font-weight-bold">Temperatura °C</h4><div class="progress mb-4"><div id="temperaturac" class="progress-bar bg-info" role="progressbar" aria-valuemin="0" aria-valuemax="70"></div></div><h4 id="sensacioncs" class="small font-weight-bold">Sensacion Termica °C</h4><div class="progress mb-4"><div id="sensacionc" class="progress-bar  bg-info" role="progressbar" aria-valuemin="0" aria-valuemax="70"></div></div><h4 id="temperaturafs" class="small font-weight-bold">Temperatura °F</h4><div class="progress mb-4"><div id="temperaturaf" class="progress-bar bg-info" role="progressbar" aria-valuemin="0" aria-valuemax="140"></div></div><h4 id="sensacionfs" class="small font-weight-bold">Sensacion Termica °F</h4><div class="progress"><div id="sensacionf" class="progress-bar bg-info" role="progressbar" aria-valuemin="0" aria-valuemax="140"></div></div></div></div></div>';
	document.getElementById("cargadora").innerHTML = indicadores;
}
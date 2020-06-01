/**
 * Descargar Imagenes desde imagen en Base64
 */

 function downloadQRCodeImage(serial) {
	 //carga de la imagen
	 var urlsendInformation = $(location).attr('pathname') + "/doorman/generateqr/"+serial;
		$.ajax({ url : urlsendInformation,
			contentType: "application/json",
			dataType: 'json',
			success: function(data){
				console.log("llego la respuesta");
				descargarJPG(data.nombre,data.result);
			}});
 }

 
 function descargarJPG(nombre,valor){
	 var qrCodeInBase64 = window.atob(valor);
     var qrCodeLen = qrCodeInBase64.length;
     var qrCodeBytes = new Uint8Array(qrCodeLen);
     for (var i = 0; i < qrCodeLen; i++) {
        var ascii = qrCodeInBase64.charCodeAt(i);
        qrCodeBytes[i] = ascii;
     }
     var qrCodeBuffer = new Blob([qrCodeBytes]);
     var link = document.createElement('a');
     link.href = window.URL.createObjectURL(qrCodeBuffer);
     var fileName = "Doorman_" + nombre + ".jpg";
     link.download = fileName;
     link.click();
 }
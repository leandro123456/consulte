// Call the dataTables jQuery plugin
//$(document).ready(function() {
//  $('#dataTable').DataTable();
//});
$(document).ready(function() {
	$('#dataTable').dataTable( {
	         "language": {
	           "info": "Mostrando _START_ de _END_ .Total  _TOTAL_ dispositivos"
	         }
	      } );
	
//	$('#dataTable').dataTable( {
//        "language": {
//        	"infoEmpty": "No se encontraron dispositivos"
//        }
//     } );
	
	    });





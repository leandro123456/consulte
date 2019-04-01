$(document).ready(function(){
	//Lista de usuarios que no van a poder agregar los roles:
	// ADMINDP+, OPERDP+, USERDP+
	var listUnauthorizedUsers1 = [
			roleAdminDP,
			roleAdminSR
		];


	for (var i = 0; i < listUnauthorizedUsers1.length; i++) {
		if (listUnauthorizedUsers1[i]) {
			var UnauthorizedUser1 = true;
		}
	}

	if (!notEmptyUser || UnauthorizedUser1) {
		$("#ADMINDP+").remove();

		$("#OPERDP+").remove();

		$("#USERDP+").remove();
	}



	//Lista de usuarios que no van a poder agregar los roles:
	// ADMINSR, OPERSR, USERSR 
	var listUnauthorizedUsers2 = [
			roleAdminDPPlus,
			roleAdminDP
		];


	for (var i = 0; i < listUnauthorizedUsers2.length; i++) {
		if (listUnauthorizedUsers2[i]) {
			var UnauthorizedUser2 = true;
		}
	}

	if (!notEmptyUser || UnauthorizedUser2) {
		$("#ADMINSR").remove();

		$("#OPERSR").remove();

		$("#USERSR").remove();
	}



	//Lista de usuarios que no van a poder agregar los roles:
	// ADMINDP, OPERDP, USERDP
	var listUnauthorizedUsers3 = [
			roleAdminDPPlus,
			roleAdminSR
		];


	for (var i = 0; i < listUnauthorizedUsers3.length; i++) {
		if (listUnauthorizedUsers3[i]) {
			var UnauthorizedUser3 = true;
		}
	}

	if (!notEmptyUser || UnauthorizedUser3) {
		$("#ADMINDP").remove();

		$("#OPERDP").remove();

		$("#USERDP").remove();
	}

	//Lista de usuarios que no van a poder agregar los roles:
	// OPER, USER
	var listUnauthorizedUsers4 = [
			roleAdminDP,
			roleAdminDPPlus,
			roleAdminSR
		];


	for (var i = 0; i < listUnauthorizedUsers4.length; i++) {
		if (listUnauthorizedUsers4[i]) {
			var UnauthorizedUser4 = true;
		}
	}

	if (!notEmptyUser || UnauthorizedUser4) {
		$("#OPER").remove();

		$("#USER").remove();
	}
});
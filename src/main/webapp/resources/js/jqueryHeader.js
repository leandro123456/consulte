$(document).ready(function(){
		//Lista de usuarios que no van a poder ver el item "Provisioning" en el header
		var listUnauthorizedUsers1 = [
			roleAdminDPPlus,
			roleOperDPPlus,
			roleUserDPPlus,
			roleUser,
			roleUserDP,
			roleUserSR
		];


		for (var i = 0; i < listUnauthorizedUsers1.length; i++) {
			if (listUnauthorizedUsers1[i]) {
				var UnauthorizedUser1 = true;
			}
		}

		if (!notEmptyUser || UnauthorizedUser1) {
			$("#provisioning").remove();
		}

		//Lista de usuarios que no van a poder ver el item "Provisioning DP+" en el header
		var listUnauthorizedUsers6 = [
			roleAdminDP,
			roleOperDP,
			roleUserDP,
			roleAdminSR,
			roleOperSR,
			roleUserSR,
		];


		for (var i = 0; i < listUnauthorizedUsers6.length; i++) {
			if (listUnauthorizedUsers6[i]) {
				var UnauthorizedUser6 = true;
			}
		}

		if (!notEmptyUser || UnauthorizedUser6) {
			$("#provisioningDPPlus").remove();
		}

		//Lista de usuarios que no van a poder ver el item "IPP Manager DP+" en el header
		var listUnauthorizedUsers7 = [
			roleAdminDP,
			roleOperDP,
			roleUserDP,
			roleAdminSR,
			roleOperSR,
			roleUserSR,
		];


		for (var i = 0; i < listUnauthorizedUsers7.length; i++) {
			if (listUnauthorizedUsers7[i]) {
				var UnauthorizedUser7 = true;
			}
		}

		if (!notEmptyUser || UnauthorizedUser7) {
			$("#ippManagerDPPlus").remove();
		}


		//Lista de usuarios que no van a poder ver el item "Template Explorer", "IPP Generation",
		//"IPP Explorer", "AMDDP" en el header
		var listUnauthorizedUsers2 = [
			roleAdminDPPlus,
			roleOperDPPlus,
			roleUserDPPlus,
			roleAdminSR,
			roleOperSR,
			roleUserSR
		];


		for (var i = 0; i < listUnauthorizedUsers2.length; i++) {
			if (listUnauthorizedUsers2[i]) {
				var UnauthorizedUser2 = true;
			}
		}

		if (!notEmptyUser || UnauthorizedUser2) {
			$("#templateExplorer").remove();
			$("#ippGeneration").remove();
			$("#ippExplorer").remove();
			$("#amdDP").remove();
		}


		//Lista de usuarios que no van a poder ver el item "eUICC Manager y AMDSR" en el header
		var listUnauthorizedUsers3 = [
			roleAdminDP,
			roleOperDP,
			roleUserDP,
			roleAdminDPPlus,
			roleOperDPPlus,
			roleUserDPPlus
		];


		for (var i = 0; i < listUnauthorizedUsers3.length; i++) {
			if (listUnauthorizedUsers3[i]) {
				var UnauthorizedUser3 = true;
			}
		}

		if (!notEmptyUser || UnauthorizedUser3) {
			$("#euiccSearch").remove();
			$("#amdSR").remove();
		}


		//Lista de usuarios que no van a poder ver el item "General Configuration" y 
		//"View Logs" en el header
		var listUnauthorizedUsers4 = [
			roleAdminDPPlus,
			roleOperDPPlus,
			roleUser,
			roleUserDP,
			roleUserDPPlus,
			roleUserSR,
		];


		for (var i = 0; i < listUnauthorizedUsers4.length; i++) {
			if (listUnauthorizedUsers4[i]) {
				var UnauthorizedUser4 = true;
			}
		}

		if (!notEmptyUser || UnauthorizedUser4) {
			$("#generalConfiguration").remove();
			$("#viewLogs").remove();
		}

		//Lista de usuarios que no van a poder ver el item "IPP Explorer" en el header
		var listUnauthorizedUsers5 = [
			roleAdminDPPlus,
			roleOperDPPlus,
			roleUserDPPlus,
			roleAdminSR,
			roleOperSR,
			roleUser,
			roleUserDP,
			roleUserSR,
		];


		for (var i = 0; i < listUnauthorizedUsers5.length; i++) {
			if (listUnauthorizedUsers5[i]) {
				var UnauthorizedUser5 = true;
			}
		}

		if (!notEmptyUser || UnauthorizedUser5) {
			$("#ippGeneration").remove();
		}
	}
)

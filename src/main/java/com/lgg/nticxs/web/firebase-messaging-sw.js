importScripts('https://www.gstatic.com/firebasejs/7.5.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.5.0/firebase-messaging.js');


const applicationServerPublicKey = 'BDKEV8dGaExs2CjrNlkVYZ3L6AuHCCSNt4ELNRSkPHZZnztf1Lf082Q8QmNut7VzTICNaGrjxSp58En2f6jNmbE';

console.log("LLEGO 544 FIREBASE");

firebase.initializeApp({
	   'messagingSenderId': '368274022300'
	 });
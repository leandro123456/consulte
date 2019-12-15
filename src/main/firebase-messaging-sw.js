//'use strict';
//console.log("encontro el Service Worker");
//
importScripts('https://www.gstatic.com/firebasejs/3.5.2/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/3.5.2/firebase-messaging.js');

firebase.initializeApp({
	  'messagingSenderId': '368274022300'
	});

const messaging = firebase.messaging();

messaging.setBackgroundMessageHandler(function(payload) {
	  console.log('[firebase-messaging-sw.js] Received background message++++++++++++++ ', payload);
	  // Customize notification here
	  const notificationTitle = 'Background Message Title';
	  const notificationOptions = {
	    body: 'Background Message body.'
	  };

	  return self.registration.showNotification(notificationTitle,
	      notificationOptions);
	});
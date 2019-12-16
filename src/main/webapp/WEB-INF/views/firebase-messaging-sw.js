//'use strict';
console.log("encontro el Service Worker");

importScripts('https://www.gstatic.com/firebasejs/3.5.2/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/3.5.2/firebase-messaging.js');

// Your web app's Firebase configuration
var firebaseConfig = {
  apiKey: "AIzaSyAUrwGTRCz98u4Tg38iWtKKx-zJEKKH78M",
  authDomain: "cdash-1274d.firebaseapp.com",
  databaseURL: "https://cdash-1274d.firebaseio.com",
  projectId: "cdash-1274d",
  storageBucket: "cdash-1274d.appspot.com",
  messagingSenderId: "368274022300",
  appId: "1:368274022300:web:95be4383f5eef61b0ff259"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();

messaging.setBackgroundMessageHandler(function(payload) {
	  console.log('[firebase-messaging-sw.js] Received background message********* ', payload);
	  // Customize notification here
	  const notificationTitle = 'Background Message Title';
	  const notificationOptions = {
	    body: 'Background Message body.'
	  };

	  return self.registration.showNotification(notificationTitle,
	      notificationOptions);
	});
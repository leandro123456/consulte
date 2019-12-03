const applicationServerPublicKey = 'BDKEV8dGaExs2CjrNlkVYZ3L6AuHCCSNt4ELNRSkPHZZnztf1Lf082Q8QmNut7VzTICNaGrjxSp58En2f6jNmbE';


let isSubscribed = false;
let swRegistration = null;

function urlB64ToUint8Array(base64String) {
  const padding = '='.repeat((4 - base64String.length % 4) % 4);
  const base64 = (base64String + padding)
    .replace(/\-/g, '+')
    .replace(/_/g, '/');

  const rawData = window.atob(base64);
  const outputArray = new Uint8Array(rawData.length);

  for (let i = 0; i < rawData.length; ++i) {
    outputArray[i] = rawData.charCodeAt(i);
  }
  return outputArray;
}


if ('serviceWorker' in navigator && 'PushManager' in window) {
	  console.log('Service Worker and Push is supported-+-');
	  navigator.serviceWorker.register('https://www.cdash.space/resources/firebase-messaging-sw.js')
	  .then(function(swReg) {
	    console.log('Service Worker is registered'+ swReg);
	    swRegistration = swReg;
	  })
	  .catch(function(error) {
	    console.log('Service Worker Error'+ error);
	  });
	} else {
	  console.log('Push messaging is not supported');
	  
	}



//Your web app's Firebase configuration
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
    messaging
        .requestPermission()
        .then(function () {
            console.log("Notification permission granted.");
            console.log("Token: "+ messaging.getToken());
            return messaging.getToken()
        })
        .then(function(token) {
            console.log("token is : " + token);
        })
        .catch(function (err) {
            console.log("Unable to get permission to notify."+ err);
        });
    messaging.onMessage(function(payload) {
        console.log("Message received. ", payload);
        const {title, ...options} = payload.notification;
        navigator.serviceWorker.ready.then(registration => {
            registration.showNotification(title, options);
        });
    });
//import "https://www.gstatic.com/firebasejs/7.2.1/firebase-app.js";
//import "https://www.gstatic.com/firebasejs/7.2.1/firebase-messaging.js";

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




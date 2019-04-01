package com.lgg.nticxs.web.helper;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.KeyStore;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
//import java.util.Date;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.ws.addressing.AttributedURIType;

import com.lgg.nticxs.web.utils.Settings;
import com.lgg.nticxs.web.utils.WSLogger;

//import scala.util.matching.Regex;


/**
 * Created by movasim on 19/09/16.
 */

public class ES4Helper {
	
	private static WSLogger logger = new WSLogger();

//	private static ES4SmSr getES4SmSr(String url) {
//		JaxWsProxyFactoryBean jaxWsProxyFactory = new JaxWsProxyFactoryBean();
//		jaxWsProxyFactory.setServiceClass(ES4SmSr.class);
//		jaxWsProxyFactory.setAddress(url);
//		jaxWsProxyFactory.setBindingId("http://schemas.xmlsoap.org/wsdl/soap12/");
//		ES4SmSr service = (ES4SmSr) jaxWsProxyFactory.create();
//		if (Settings.getInstance().isMutualAuth()) {
//			try {
//				authenticate(service);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return service;
//	}

	public static void prepareSmSrChange(String eid, String origin, String destiny) {
//		SRDAO srdao=new SRDAO();
//		SR srReceiver=srdao.retrieveByName(destiny);
//		SR srDonor =srdao.retrieveByName(origin);
//		if(srReceiver!=null && srDonor!=null){
//			ES4SmSr eS4SmSr = getES4SmSr(srReceiver.getUrlEs4());
//			ES4PrepareSMSRChangeRequest req = new ES4PrepareSMSRChangeRequest();
//			req.setEid(Utils.hexStringToByteArray(eid));
//			req.setCurrentSMSRid(srDonor.getSmsrId());
//			req.setFunctionCallIdentifier(Utils.randomHex());
//
//			eS4SmSr.es4PrepareSMSRChange(req, getFrom(srReceiver), getTo(srReceiver.getUrlEs4(), srReceiver.getSmsrId()), getMessageId(), getPrepareAction(), getReplyTo());
//		}else{
//			
//			logger.logger("ERROR", "SM-WEB", "", "", "", "prepareSmSrChange()", eid, "", "EID: " + eid + ", Origin: " + origin + ", Destiny: " + destiny, "srReceiver: "+srReceiver);
//			logger.logger("ERROR", "SM-WEB", "", "", "", "prepareSmSrChange()", eid, "", "EID: " + eid + ", Origin: " + origin + ", Destiny: " + destiny, "srDonor: "+srDonor);
//			logger.logger("ERROR", "SM-WEB", "", "", "", "prepareSmSrChange()", eid, "", "EID: " + eid + ", Origin: " + origin + ", Destiny: " + destiny, "Error Destiny or Donor are NULL");
//		}

	}

	public static void smSrChange(String eid,String origin, String destiny) {
//		SRDAO srdao=new SRDAO();
//		SR srReceiver=srdao.retrieveByName(destiny);
//		SR srDonor =srdao.retrieveByName(origin);
//		if(srReceiver!=null && srDonor!=null){
//			ES4SmSr eS4SmSr = getES4SmSr(srDonor.getUrlEs4());
//			ES4SMSRChangeRequest req = new ES4SMSRChangeRequest();
//			req.setFunctionCallIdentifier(Utils.randomHex());
//			req.setValidityPeriod(BigInteger.valueOf(10000));
//			req.setEid(Utils.hexStringToByteArray(eid));
//			req.setTargetSmsrId(srReceiver.getSmsrId());
//
//			eS4SmSr.es4SMSRChange(req, getFrom(srReceiver), getTo(srDonor.getUrlEs4(),srReceiver.getSmsrId()), getMessageId(), getPrepareActionSrchange(), getReplyTo());
//		}else{
//			
//			logger.logger("ERROR", "SM-WEB", "", "", "", "smSrChange()", eid, "", "EID: " + eid + ", Origin: " + origin + ", Destiny: " + destiny, "srReceiver: "+srReceiver);
//			logger.logger("ERROR", "SM-WEB", "", "", "", "smSrChange()", eid, "", "EID: " + eid + ", Origin: " + origin + ", Destiny: " + destiny, "srDonor: "+srDonor);
//			logger.logger("ERROR", "SM-WEB", "", "", "", "smSrChange()", eid, "", "EID: " + eid + ", Origin: " + origin + ", Destiny: " + destiny, "Error Destiny or Donor are NULL");
//		}
	}


//	private static EndpointReferenceType getFrom(SR srReceiver) {
//		EndpointReferenceType from = new EndpointReferenceType();
//
//		String fromAdress = "https://159.203.69.244:8090/MNO" + "?EntityId="+"1.3.6.1.4.1.50201"; //mnoId Cubic
//		if(srReceiver.getUrlEs4().contains("112.35.1.89"))
//			fromAdress=fromAdress+" ?UserName=Cubic1"; // ?UserName=Cubic1
//		from.setAddress(new AttributedURIType(fromAdress));
//		return from;
//	}

//	private static AttributedURIType getPrepareAction() {
//		return new AttributedURIType("http://gsma.com/ES4/eUICCManagement/ES4-PrepareSMSRChange");
//	}
//
//	private static AttributedURIType getPrepareActionSrchange() {
//		return new AttributedURIType("http://gsma.com/ES4/eUICCManagement/ES4-SMSRChange");
//	}
//
//
//
//	private static AttributedURIType getTo(String url, String receiverId) {
//		url=url+"?EntityId="+receiverId;
//		return new AttributedURIType(url);
//	}
//
//	private static AttributedURIType getMessageId() {
//		AttributedURIType message = new AttributedURIType();
//		//FIXME cambiar transactionId
//		String transactionId = "trId";
//		String now = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
//		String messageId = "https://159.203.69.244:8090/MNO/es4?TransactionId=" + transactionId + "?MessageDate=" + now;
//		message.setValue(messageId);
//		return message;
//	}
//
//	private static EndpointReferenceType getReplyTo() {
//		EndpointReferenceType replyTo = new EndpointReferenceType();
//		replyTo.setAddress(new AttributedURIType("https://159.203.69.244:8090/MNO/es4"));
//		return replyTo;
//	}


	/**             Mutual Authentication**/
	private static void authenticate(Object service) throws Exception {
		Client cxfClient = ClientProxy.getClient(service);
		HTTPConduit conduit = (HTTPConduit) cxfClient.getConduit();

		//trust any server, quick and easy, not the focus of this problem 
		TrustManager[] simpleTrustManager = new TrustManager[]{new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		}};

//		KeyStore ks = KeyStore.getInstance("JKS");
//		FileInputStream in = new FileInputStream(Settings.getInstance().getKeystore());
//		ks.load(in, Settings.getInstance().getKeystorePass().toCharArray());
//		in.close();
//		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
//		keyManagerFactory.init(ks, Settings.getInstance().getKeystorePass().toCharArray());
//		KeyManager[] keyManagers = new KeyManager[]{keyManagerFactory.getKeyManagers()[0]};
//
//		TLSClientParameters tlsParams = new TLSClientParameters();
//		tlsParams.setTrustManagers(simpleTrustManager);
//		tlsParams.setKeyManagers(keyManagers);
//		tlsParams.setSecureSocketProtocol("TLSv1.2");
//		tlsParams.setCertAlias(Settings.getInstance().getKeystoreAlias());
//		tlsParams.setDisableCNCheck(true);
//		conduit.setTlsClientParameters(tlsParams);
	}

}

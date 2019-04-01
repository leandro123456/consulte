package com.lgg.nticxs.web.helper;

import com.lgg.nticxs.web.utils.Settings;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;


import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by movasim on 19/09/16.
 */
public class ES1Helper {

//    private static ES1SmSr getES1SmSr() {
//        JaxWsProxyFactoryBean jaxWsProxyFactory = new JaxWsProxyFactoryBean();
//        jaxWsProxyFactory.setServiceClass(ES1SmSr.class);
//        jaxWsProxyFactory.setAddress(Settings.getInstance().getSR_URL());
//        ES1SmSr service= (ES1SmSr) jaxWsProxyFactory.create();
//        if (Settings.getInstance().isMutualAuth()) {
//        	try {
//        		authenticate(service);
//        	} catch (Exception e) {
//        		e.printStackTrace();
//        	}
//        }
//        return service;
//    }
//
//    public static void registerEis(EISType eis) {
//        ES1SmSr eS1SmSr = getES1SmSr();
//        ES1RegisterEISRequest req = new ES1RegisterEISRequest();
//        req.setValidityPeriod(BigInteger.valueOf(600));
//        req.setFunctionCallIdentifier("1");
//        req.setEis(eis);
//        eS1SmSr.es1RegisterEIS(req, null, null, null, null, null);
//    }
    
		/**		Mutual Authentication**/
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

//KeyStore ks = KeyStore.getInstance("JKS"); 
//FileInputStream in = new FileInputStream(Settings.getInstance().getKeystore());
//ks.load(in, Settings.getInstance().getKeystorePass().toCharArray());
//in.close(); 
//KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509"); 
//keyManagerFactory.init(ks, Settings.getInstance().getKeystorePass().toCharArray());
//KeyManager[] keyManagers = new KeyManager[]{keyManagerFactory.getKeyManagers()[0]}; 
//
//TLSClientParameters tlsParams = new TLSClientParameters(); 
//tlsParams.setTrustManagers(simpleTrustManager); 
//tlsParams.setKeyManagers(keyManagers); 
//tlsParams.setSecureSocketProtocol("TLSv1"); 
//tlsParams.setCertAlias(Settings.getInstance().getKeystoreAlias()); 
//tlsParams.setDisableCNCheck(true);
//conduit.setTlsClientParameters(tlsParams);
}
}

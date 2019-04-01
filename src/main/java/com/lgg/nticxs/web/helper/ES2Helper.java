package com.lgg.nticxs.web.helper;



import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.KeyStore;
import java.util.List;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.lgg.nticxs.web.utils.Settings;

/**
 * Created by movasim on 19/09/16.
 */
public class ES2Helper {

//    private static ES2SmDp getES2SmDp() {
//        JaxWsProxyFactoryBean jaxWsProxyFactory = new JaxWsProxyFactoryBean();
//        jaxWsProxyFactory.setServiceClass(ES2SmDp.class);
//        jaxWsProxyFactory.setAddress(Settings.getInstance().getDP_URL());
//        ES2SmDp service=(ES2SmDp) jaxWsProxyFactory.create();
//        if (Settings.getInstance().isMutualAuth()) {
//        	try {
//        		authenticate(service);
//        	} catch (Exception e) {
//        		e.printStackTrace();
//        	}
//        }
//        return service;
//    }

    public static void downloadProfile(String eid, String iccid, String smSrId, boolean enable) {
//        ES2SmDp eS2SmDp = getES2SmDp();
//
//        ES2DownloadProfileRequest req = new ES2DownloadProfileRequest();
//        req.setEid(Utils.hexStringToByteArray(eid));
//        req.setFunctionCallIdentifier("1");
//        req.setValidityPeriod(BigInteger.valueOf(600));
//        req.setEnableProfile(enable);
//        req.setIccid(iccid);
//        req.setProfileType("ES03T");
//        req.setSmSrId(smSrId);
//
//        eS2SmDp.es2DownloadProfile(req, null, null, null, null, null);
    }

    public static void enableProfile(String eid, String iccid, String smSrId) {
//        ES2SmDp eS2SmDp = getES2SmDp();
//
//        ES2EnableProfileRequest req = new ES2EnableProfileRequest();
//        req.setIccid(iccid);
//        req.setEid(Utils.hexStringToByteArray(eid));
//        req.setSmSrId(smSrId);
//        req.setFunctionCallIdentifier("1");
//        req.setValidityPeriod(BigInteger.valueOf(600));
//
//        eS2SmDp.es2EnableProfile(req, null, null, null, null, null);
    }

    public static void disableProfile(String eid, String iccid, String smSrId) {
//        ES2SmDp eS2SmDp = getES2SmDp();
//
//        ES2DisableProfileRequest req = new ES2DisableProfileRequest();
//        req.setIccid(iccid);
//        req.setEid(Utils.hexStringToByteArray(eid));
//        req.setSmsrId(smSrId);
//        req.setFunctionCallIdentifier("1");
//        req.setValidityPeriod(BigInteger.valueOf(600));
//
//        eS2SmDp.es2DisableProfile(req, null, null, null, null, null);
    }

    public static void deleteProfile(String eid, String iccid, String smSrId) {
//        ES2SmDp eS2SmDp = getES2SmDp();
//        ES2DeleteProfileRequest req = new ES2DeleteProfileRequest();
//        req.setIccid(iccid);
//        req.setEid(Utils.hexStringToByteArray(eid));
//        req.setSmsrId(smSrId);
//        req.setFunctionCallIdentifier("1");
//        req.setValidityPeriod(BigInteger.valueOf(600));
//        eS2SmDp.es2DeleteProfile(req, null, null, null, null, null);
    }
    
//    public static void updatePol2(String eid,  String smSrId, String iccid, List<POL2RuleType> rules){
//    	ES2SmDp eS2SmDp = getES2SmDp();
//    	ES2UpdatePolicyRulesRequest req= new ES2UpdatePolicyRulesRequest();
//    	req.setEid(Utils.hexStringToByteArray(eid));
//    	req.setIccid(iccid);
//    	req.setFunctionCallIdentifier("1");
//        req.setValidityPeriod(BigInteger.valueOf(600));
//        req.setSmsrId(smSrId);
//        POL2Type pol2= new POL2Type();
//        pol2.setRule(rules);
//        req.setPol2(pol2);
//        eS2SmDp.es2UpdatePolicyRules(req, null, null, null, null, null);
        
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

//        KeyStore ks = KeyStore.getInstance("JKS"); 
//        FileInputStream in = new FileInputStream(Settings.getInstance().getKeystore());
//        ks.load(in, Settings.getInstance().getKeystorePass().toCharArray());
//        in.close(); 
//        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509"); 
//        keyManagerFactory.init(ks, Settings.getInstance().getKeystorePass().toCharArray());
//        KeyManager[] keyManagers = new KeyManager[]{keyManagerFactory.getKeyManagers()[0]}; 
//
//        TLSClientParameters tlsParams = new TLSClientParameters(); 
//        tlsParams.setTrustManagers(simpleTrustManager); 
//        tlsParams.setKeyManagers(keyManagers); 
//        tlsParams.setSecureSocketProtocol("TLSv1"); 
//        tlsParams.setCertAlias(Settings.getInstance().getKeystoreAlias()); 
//        tlsParams.setDisableCNCheck(true);
//        conduit.setTlsClientParameters(tlsParams);
	}
}

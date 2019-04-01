package com.lgg.nticxs.web.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by movasim on 07/09/16.
 */
public class SimpleCard {

    private String eid;
    private String eumId;
    private String productionDate;
    private String platformType;
    private String platformVersion;
    private String remainingMemory;
    private String availableMemoryForProfiles;
    private String smSrId;
    private String ecasd;
    private String eUiccCapabilities;
    private String cattpSupport;
    private String cattpVersion;
    private String httpSupport;
    private String httpVersion;
    private String securePacketVersion;
    private String remoteProvisioningVersion;
    private String eumCertificated;
    private String signatureAlgorithm;
    private String signature;

    private List<SimpleProfileCard> simpleProfileCards;

    
//    public SimpleCard(Card card) {
//        this.eid = bytesToHex(card.getEumSignedInfo().getEid());
//        this.eumId = card.getEumSignedInfo().getEumId();
//        this.productionDate = card.getEumSignedInfo().getProductionDate().toString();
//        this.platformType = card.getEumSignedInfo().getPlatformType();
//        this.platformVersion = card.getEumSignedInfo().getPlatformVersion();
//        this.remainingMemory = card.getRemainingMemory().toString();
//        this.availableMemoryForProfiles = card.getMemoryForProfiles().toString();
//        this.smSrId = card.getSmsrId();
//        //this.ecasd = card.getEumSignedInfo().getEcasd();
//        this.eUiccCapabilities = card.getEumSignedInfo().getEuiccCapabilities().toString();
//
//
//        this.cattpSupport = String.valueOf(card.getEumSignedInfo().getEuiccCapabilities().isCattpSupport());
//        this.cattpVersion = card.getEumSignedInfo().getEuiccCapabilities().getCattpVersion();
//        this.httpSupport = String.valueOf(card.getEumSignedInfo().getEuiccCapabilities().isHttpSupport());
//        this.httpVersion = String.valueOf(card.getEumSignedInfo().getEuiccCapabilities().getHttpVersion());
//        this.securePacketVersion = String.valueOf(card.getEumSignedInfo().getEuiccCapabilities().getSecurePacketVersion());
//        /*this.remoteProvisioningVersion = ;
//        this.eumCnt iertificated = ;
//        this.signatureAlgorithm = card.get;
//        this.signature =;
//        */
//
//        simpleProfileCards = new ArrayList<SimpleProfileCard>();
//
//        for (int i = 0; i < card.getProfiles().size(); i++) {
//            simpleProfileCards.add(new SimpleProfileCard(card.getProfiles().get(i)));
//        }
//
//    }

    public String getEid() {
        return eid;
    }

    public String getSmSrId() {
        return smSrId;
    }

    public String getEumId() {
        return eumId;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public String getPlatformType() {
        return platformType;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getRemainingMemory() {
        return remainingMemory;
    }

    public String getAvailableMemoryForProfiles() {
        return availableMemoryForProfiles;
    }

    public String getEcasd() {
        return ecasd;
    }

    public String geteUiccCapabilities() {
        return eUiccCapabilities;
    }

    public String getCattpSupport() {
        return cattpSupport;
    }

    public String getCattpVersion() {
        return cattpVersion;
    }

    public String getHttpSupport() {
        return httpSupport;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public String getSecurePacketVersion() {
        return securePacketVersion;
    }

    public String getRemoteProvisioningVersion() {
        return remoteProvisioningVersion;
    }

    public String getEumCertificated() {
        return eumCertificated;
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public String getSignature() {
        return signature;
    }

    public List<SimpleProfileCard> getSimpleProfileCards() {
        return simpleProfileCards;
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}

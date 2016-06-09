package com.OMCB.Model;

import java.util.*;

/**
 * Created by atchowdhury on 10/26/2015.
 */
public class BCF {

    public String getBSC() {
        return BSC;
    }

    public void setBSC(String BSC) {
        this.BSC = BSC;
    }

    private String BSC=null;

    String BCF=null;
    public void setBCF(String a){
        this.BCF=a;
    }
    public String getBCF(){
        return this.BCF;
    }

private String distName=null;

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    private String version=null;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<ExternalAlarms> getExternalAlarmList() {
        return externalAlarmList;
    }

    public void setExternalAlarmList(List<ExternalAlarms> externalAlarmList) {
        this.externalAlarmList = externalAlarmList;
    }


    private String name=null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private String adminState;
    public String getAdminState() {
        return adminState;
    }

    public void setAdminState(String adminState) {
        this.adminState = adminState;
    }

    private String autoUnlAllowed=null;
    public String getAutoUnlAllowed() {
        return autoUnlAllowed;
    }

    public void setAutoUnlAllowed(String autoUnlAllowed) {
        this.autoUnlAllowed = autoUnlAllowed;
    }

    private String bcfHdlcIdList=null;

    public String getBcfHdlcIdList() {
        return bcfHdlcIdList;
    }

    public void setBcfHdlcIdList(String bcfHdlcIdList) {
        this.bcfHdlcIdList = bcfHdlcIdList;
    }


    private String bcfType=null;


    public String getBcfType() {
        return bcfType;
    }

    public void setBcfType(String bcfType) {
        this.bcfType = bcfType;
    }



    private String btsSiteSubtype=null;



    public String getBtsSiteSubtype() {
        return btsSiteSubtype;
    }

    public void setBtsSiteSubtype(String btsSiteSubtype) {
        this.btsSiteSubtype = btsSiteSubtype;
    }

    private String clockSource=null;

    public String getClockSource() {
        return clockSource;
    }

    public void setClockSource(String clockSource) {
        this.clockSource = clockSource;
    }


    List<ExternalAlarms> externalAlarmList=new ArrayList<ExternalAlarms>();

    private String flexiEdgeAdditional2E1T1Usage=null;


    public String getFlexiEdgeAdditional2E1T1Usage() {
        return flexiEdgeAdditional2E1T1Usage;
    }

    public void setFlexiEdgeAdditional2E1T1Usage(String flexiEdgeAdditional2E1T1Usage) {
        this.flexiEdgeAdditional2E1T1Usage = flexiEdgeAdditional2E1T1Usage;
    }

    private String flexiEdgeTrsAbisGroomingUsage=null;


    public String getFlexiEdgeTrsAbisGroomingUsage() {
        return flexiEdgeTrsAbisGroomingUsage;
    }

    public void setFlexiEdgeTrsAbisGroomingUsage(String flexiEdgeTrsAbisGroomingUsage) {
        this.flexiEdgeTrsAbisGroomingUsage = flexiEdgeTrsAbisGroomingUsage;
    }

    private String lapdLinkName=null;


    public String getLapdLinkName() {
        return lapdLinkName;
    }

    public void setLapdLinkName(String lapdLinkName) {
        this.lapdLinkName = lapdLinkName;
    }

    private String rxDifferenceLimit=null;


    public String getRxDifferenceLimit() {
        return rxDifferenceLimit;
    }

    public void setRxDifferenceLimit(String rxDifferenceLimit) {
        this.rxDifferenceLimit = rxDifferenceLimit;
    }
    private String synchEnabled=null;


    public String getSynchEnabled() {
        return synchEnabled;
    }

    public void setSynchEnabled(String synchEnabled) {
        this.synchEnabled = synchEnabled;
    }


    private String csdapid1="0";
    private String csdapid2="0";
    private String csdapid3="0";
    private String csdapid4="0";

    public String getCsdapid1() {
        return csdapid1;
    }

    public void setCsdapid1(String csdapid1) {
        this.csdapid1 = csdapid1;
    }

    public String getCsdapid2() {
        return csdapid2;
    }

    public void setCsdapid2(String csdapid2) {
        this.csdapid2 = csdapid2;
    }

    public String getCsdapid3() {
        return csdapid3;
    }

    public void setCsdapid3(String csdapid3) {
        this.csdapid3 = csdapid3;
    }

    public String getCsdapid4() {
        return csdapid4;
    }

    public void setCsdapid4(String csdapid4) {
        this.csdapid4 = csdapid4;
    }
}


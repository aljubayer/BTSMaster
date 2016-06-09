package com.OMCB.Model;
import com.OMCB.DAL.FullSiteBCFManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.sql.rowset.JdbcRowSet;
import java.sql.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

/**
 * Created by atchowdhury on 10/26/2015.
 */
public class LAPD {
private String Bsc="";


    public String getBsc() {
        return Bsc;
    }

    public void setBsc(String bsc) {
        Bsc = bsc;
    }

    public String getBCF() {
        return BCF;
    }

    public void setBCF(String BCF) {
        this.BCF = BCF;
    }

    private String BCF="";

private String PLMN="";

    public String getPLMN() {
        return PLMN;
    }

    public void setPLMN(String PLMN) {
        this.PLMN = PLMN;
    }

private String LAPD="";

    public String getLAPD() {
        return LAPD;
    }

    public void setLAPD(String LAPD) {
        this.LAPD = LAPD;
    }

    private String abisSigChannelSubSlot="";

    public String getAbisSigChannelSubSlot() {
        return abisSigChannelSubSlot;
    }

    public void setAbisSigChannelSubSlot(String abisSigChannelSubSlot) {
        this.abisSigChannelSubSlot = abisSigChannelSubSlot;
    }


    private String bitRate="";

    public String getBitRate() {
        return bitRate;
    }

    public void setBitRate(String bitRate) {
        this.bitRate = bitRate;
    }

    private String abisSigChannelTimeSlotPcm="";


    public String getAbisSigChannelTimeSlotPcm() {
        return abisSigChannelTimeSlotPcm;
    }

    public void setAbisSigChannelTimeSlotPcm(String abisSigChannelTimeSlotPcm) {
        this.abisSigChannelTimeSlotPcm = abisSigChannelTimeSlotPcm;
    }

    private String abisSigChannelTimeSlotTsl="";

    public String getAbisSigChannelTimeSlotTsl() {
        return abisSigChannelTimeSlotTsl;
    }

    public void setAbisSigChannelTimeSlotTsl(String abisSigChannelTimeSlotTsl) {
        this.abisSigChannelTimeSlotTsl = abisSigChannelTimeSlotTsl;
    }

    private String adminState="";


    public String getAdminState() {
        return adminState;
    }

    public void setAdminState(String adminState) {
        this.adminState = adminState;
    }


    private String dChannelType="";

    public String getdChannelType() {
        return dChannelType;
    }

    public void setdChannelType(String dChannelType) {
        this.dChannelType = dChannelType;
    }

    private String logicalBCSUAddress="";

    public String getLogicalBCSUAddress() {
        return logicalBCSUAddress;
    }

    public void setLogicalBCSUAddress(String logicalBCSUAddress) {
        this.logicalBCSUAddress = logicalBCSUAddress;
    }

    private String name="";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private String parameterSetNumber="";

    public String getParameterSetNumber() {
        return parameterSetNumber;
    }

    public void setParameterSetNumber(String parameterSetNumber) {
        this.parameterSetNumber = parameterSetNumber;
    }

    private String sapi="";


    public String getSapi() {
        return sapi;
    }

    public void setSapi(String sapi) {
        this.sapi = sapi;
    }

    private String tei="";

    public String getTei() {
        return tei;
    }

    public void setTei(String tei) {
        this.tei = tei;
    }

    public String getNewPCM() {
        return NewPCM;
    }

    public void setNewPCM(String newPCM) {
        NewPCM = newPCM;
    }

    private String NewPCM="";


    public void FetchLapdData(Map<String,Map<String,Map<String,String>>> aInputDictionary1) throws SQLException, ParserConfigurationException, SAXException, IOException, XMLStreamException, FileNotFoundException, DocumentException, ParseException {


        Map<String, Map<String, Map<String, String>>> fullSiteSwapList = new HashMap<String, Map<String, Map<String, String>>>();
        Map<String, Map<String, Map<String, String>>> onlyTRXSwapList = new HashMap<String, Map<String, Map<String, String>>>();
        //Map<String, Map<String, Map<String, String>>> onlyTRXSwapList = new HashMap<String, Map<String, Map<String, String>>>();


        String partSQL = "";
        //String oldPcm="";
        List<BscBcfLapd> lapdBCFMappingList = new ArrayList<com.OMCB.Model.BscBcfLapd>();

        for (Map.Entry<String, Map<String, Map<String, String>>> amp : aInputDictionary1.entrySet()) {

            // System.out.println(amp.getKey() + "/" + amp.getValue());


            for (Map.Entry<String, Map<String, Map<String, String>>> bscEntry : aInputDictionary1.entrySet()) {
                String Bsc = bscEntry.getKey();
                System.out.println(Bsc + "--->");
                // ...
                for (Map.Entry<String, Map<String, String>> bcfEntry : bscEntry.getValue().entrySet()) {
                    String Bcf = bcfEntry.getKey();
                    System.out.println(Bcf + "--->");
                    Map<String, String> pcmList = bcfEntry.getValue();
                    for (Map.Entry<String, String> Pcm : pcmList.entrySet()) {
                        BscBcfLapd lapdBCFmap = new BscBcfLapd();
                        lapdBCFmap.BSC = Bsc;
                        lapdBCFmap.BCF = Bcf;


                        String oldPcm = Pcm.getKey();
                        lapdBCFmap.PCM = oldPcm;
                        lapdBCFmap.NewPCM = Pcm.getValue();
                        lapdBCFMappingList.add(lapdBCFmap);
                        System.out.println(Pcm.getKey() + "---> old PCM  " + Bsc + "--" + Bcf);
                        System.out.println(Pcm.getValue() + "---> New PCM");

                        partSQL += "(bsc=" + Bsc + " and abisSigChannelTimeSlotPcm=" + oldPcm + ") or";

                    }


                }


            }


            //for(Map.Entry<String,Map<String,String>> aMap:amp.entrySet()){}
        }
        partSQL = partSQL.substring(0, partSQL.length() - 2);
       // System.out.println(partSQL);
        String Query = "select plmn,bsc,lapd,abisSigChannelSubSlot,bitRate,abisSigChannelTimeSlotPcm,abisSigChannelTimeSlotTsl,adminState,dChannelType,name,parameterSetNumber,sapi,tei from lapd where " + partSQL + "";
        DBConnection aDBConnection = new DBConnection();
        JdbcRowSet aResultSet =
                aDBConnection.SelectSql(Query);


        List<LAPD> lapdList = new ArrayList<LAPD>();
        while (aResultSet.next()) {
            LAPD aLapd = new LAPD();
            aLapd.setPLMN(aResultSet.getString("plmn"));
            aLapd.setBsc(aResultSet.getString("bsc"));
            aLapd.setLAPD(aResultSet.getString("lapd"));
            aLapd.setAbisSigChannelSubSlot(aResultSet.getString("abisSigChannelSubSlot"));
            aLapd.setBitRate(aResultSet.getString("bitRate"));
            aLapd.setAbisSigChannelTimeSlotPcm(aResultSet.getString("abisSigChannelTimeSlotPcm"));
            aLapd.setAbisSigChannelTimeSlotTsl(aResultSet.getString("abisSigChannelTimeSlotTsl"));
            aLapd.setAdminState(aResultSet.getString("adminState"));
            aLapd.setdChannelType(aResultSet.getString("dChannelType"));
            aLapd.setName(aResultSet.getString("name"));
            aLapd.setParameterSetNumber(aResultSet.getString("parameterSetNumber"));
            aLapd.setSapi(aResultSet.getString("sapi"));
            aLapd.setTei(aResultSet.getString("tei"));


            lapdList.add(aLapd);
            // print the results
            //  System.out.println("One Added");

        }
        aDBConnection.CloseConnection();


        List<LAPD> lapdBCFAllinfoList = new ArrayList<com.OMCB.Model.LAPD>();
        List<LAPD> sapi62OMUList = new ArrayList<com.OMCB.Model.LAPD>();

        for (LAPD lapd : lapdList) {


            for (BscBcfLapd lapdBCF : lapdBCFMappingList) {
               // System.out.println(lapdBCF.BSC.trim() + " " + lapd.getBsc().trim());
                if (lapdBCF.BSC.trim().equals(lapd.getBsc().trim()) && lapdBCF.PCM.trim().equals(lapd.getAbisSigChannelTimeSlotPcm().trim())) {
                    LAPD aLapd = new LAPD();
                    aLapd = lapd;
                    aLapd.setBCF(lapdBCF.BCF);
                    aLapd.setNewPCM(lapdBCF.NewPCM);
                    if (aLapd.getSapi().equals("62")) {
                        sapi62OMUList.add(aLapd);
                    }
                    lapdBCFAllinfoList.add(aLapd);


                }
            }

        }

//Map<String,String> otherSites=new HashMap<String, String>();

        for (LAPD sapiLapd : sapi62OMUList) {


            Map<String, String> aPcmmap = aInputDictionary1.get(sapiLapd.Bsc).get(sapiLapd.BCF);
            if (aPcmmap.size() > 0) {
                if (fullSiteSwapList.size() == 0) {
                    Map<String, Map<String, String>> aBcfPcmMap1 = new HashMap<String, Map<String, String>>();
                    aBcfPcmMap1.put(sapiLapd.BCF, aPcmmap);
                    fullSiteSwapList.put(sapiLapd.Bsc, aBcfPcmMap1);
                } else {
                    if(!fullSiteSwapList.containsKey(sapiLapd.Bsc)){
                       Map<String, Map<String, String>> aBcfPcmMap1 = new HashMap<String, Map<String, String>>();
                        aBcfPcmMap1.put(sapiLapd.BCF, aPcmmap);
                        fullSiteSwapList.put(sapiLapd.Bsc, aBcfPcmMap1);

                    }
                    else if (!fullSiteSwapList.get(sapiLapd.Bsc).containsKey(sapiLapd.BCF)) {
                        Map<String, Map<String, String>> aBcfPcmMap1 = new HashMap<String, Map<String, String>>();
                        aBcfPcmMap1.put(sapiLapd.BCF, aPcmmap);
                        fullSiteSwapList.get(sapiLapd.Bsc).put(sapiLapd.BCF, aPcmmap);
                    }
                    // fullSiteSwapList.putAll(sapiLapd.Bsc, aBcfPcmMap1);
                }
            }
        }


      FullSiteBCFManager aFullSiteBCFManager=new FullSiteBCFManager();

        aFullSiteBCFManager.CreateFullSiteXml(fullSiteSwapList,lapdBCFAllinfoList);
        

    }



}

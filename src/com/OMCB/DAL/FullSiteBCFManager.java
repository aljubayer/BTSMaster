/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.OMCB.DAL;

import com.OMCB.Model.*;
import com.mysql.jdbc.ResultSetMetaData;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sql.rowset.JdbcRowSet;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.dom4j.DocumentException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class FullSiteBCFManager {

    public void CreateFullSiteXml(Map<String, Map<String, Map<String, String>>> fullSiteSwapList, List<LAPD> lapdBCFAllinfoList) throws ParserConfigurationException, SAXException, IOException, XMLStreamException, FileNotFoundException, DocumentException, SQLException, ParseException {

        for (Map.Entry<String, Map<String, Map<String, String>>> amp : fullSiteSwapList.entrySet()) {
            for (Map.Entry<String, Map<String, Map<String, String>>> bscEntry : fullSiteSwapList.entrySet()) {
                String Bsc = bscEntry.getKey();
                String bscQuery = "Select bsc,name from bsc where bsc=" + Bsc + ";";
                DBConnection aDBConnection = new DBConnection();

                JdbcRowSet aResultSet = aDBConnection.SelectSql(bscQuery);

                BSC aBsc = new BSC();
                while (aResultSet.next()) {

                    aBsc.setBSC(aResultSet.getString("bsc"));
                    aBsc.setName(aResultSet.getString("name"));

                }
                aDBConnection.CloseConnection();

                System.out.println(Bsc + "--->");
                for (Map.Entry<String, Map<String, String>> bcfEntry : bscEntry.getValue().entrySet()) {
                    String Bcf = bcfEntry.getKey();
                    System.out.println(Bcf + "--->");
                    Map<String, String> pcmList = bcfEntry.getValue();

                    List<BTS> aBTSList = new ArrayList<BTS>();
                    List<TRX> aTrxList = new ArrayList<TRX>();
                    List<String> dapList = new ArrayList<String>();
                    List<String> csdapList = new ArrayList<String>();

                    String distName = "PLMN-PLMN/BSC-" + Bsc + "/BCF-" + Bcf + "";
                    XmlSAX xmlParser = new XmlSAX(distName);

                    XMLReader xr = XMLReaderFactory.createXMLReader();

                    xr.setContentHandler(xmlParser);

                    xr.parse(new InputSource("D://Flexi_" + CurrDate() + "//" + aBsc.getName() + "_" + CurrDate() + ".xml"));

                    BcfCsdap aBcfCsdap = xmlParser.GetBCF();

                    String BcfString = GetBCFString(aBcfCsdap);

                    try {
                        aDBConnection.CloseConnection();

                    } catch (Exception e) {
                        System.out.println("DB Connection Error...");
                    }

                    aDBConnection.OpenDBConnection();
                    String getBtsQuery = "select * from bts where bsc=" + Bsc + " and bcf=" + Bcf;
                    String getTRXQueryS = "select * from trx where bsc=" + Bsc + " and bcf=" + Bcf;
                    if (aDBConnection.conn1.isClosed()) {
                        aDBConnection.OpenDBConnection();
                    }
                    JdbcRowSet btsRowSet = aDBConnection.SelectSql(getBtsQuery);
                    while (btsRowSet.next()) {
                        BTS aBTS = new BTS();

                        aBTS.BTSid = btsRowSet.getString("BTS");
                        aBTS.BSC = btsRowSet.getString("BSC");
                        aBTS.BCF = btsRowSet.getString("BCF");
                        aBTS.frequencyBandInUse = btsRowSet.getString("frequencyBandInUse");
                        aBTS.frequencyBandInUse = btsRowSet.getString("frequencyBandInUse");
                        aBTS.gprsMsTxPwrMaxCCH1x00 = btsRowSet.getString("gprsMsTxPwrMaxCCH1x00");
                        aBTS.minMsTxPower = btsRowSet.getString("minMsTxPower");
                        aBTS.msTxPwrMaxCCH1x00 = btsRowSet.getString("msTxPwrMaxCCH1x00");
                        aBTS.msTxPwrMaxGSM1x00 = btsRowSet.getString("msTxPwrMaxGSM1x00");
                        aBTS.name = btsRowSet.getString("name");
                        aBTS.adaptiveLaAlgorithm = btsRowSet.getString("adaptiveLaAlgorithm");
                        aBTS.adminState = btsRowSet.getString("adminState");
                        aBTS.allowIMSIAttachDetach = btsRowSet.getString("allowIMSIAttachDetach");
                        aBTS.amhLowerLoadThreshold = btsRowSet.getString("amhLowerLoadThreshold");
                        aBTS.amhMaxLoadOfTgtCell = btsRowSet.getString("amhMaxLoadOfTgtCell");
                        aBTS.amhTrhoGuardTime = btsRowSet.getString("amhTrhoGuardTime");
                        aBTS.amhUpperLoadThreshold = btsRowSet.getString("amhUpperLoadThreshold");
                        aBTS.amrConfFrCodecModeSet = btsRowSet.getString("amrConfFrCodecModeSet");
                        aBTS.amrConfFrDlThreshold1 = btsRowSet.getString("amrConfFrDlThreshold1");
                        aBTS.amrConfFrDlThreshold2 = btsRowSet.getString("amrConfFrDlThreshold2");
                        aBTS.amrConfFrDlThreshold3 = btsRowSet.getString("amrConfFrDlThreshold3");
                        aBTS.amrConfFrHysteresis1 = btsRowSet.getString("amrConfFrHysteresis1");
                        aBTS.amrConfFrHysteresis2 = btsRowSet.getString("amrConfFrHysteresis2");
                        aBTS.amrConfFrHysteresis3 = btsRowSet.getString("amrConfFrHysteresis3");
                        aBTS.amrConfFrInitCodecMode = btsRowSet.getString("amrConfFrInitCodecMode");
                        aBTS.amrConfFrStartMode = btsRowSet.getString("amrConfFrStartMode");
                        aBTS.amrConfFrUlThreshold1 = btsRowSet.getString("amrConfFrUlThreshold1");
                        aBTS.amrConfFrUlThreshold2 = btsRowSet.getString("amrConfFrUlThreshold2");
                        aBTS.amrConfFrUlThreshold3 = btsRowSet.getString("amrConfFrUlThreshold3");
                        aBTS.amrConfHrCodecModeSet = btsRowSet.getString("amrConfHrCodecModeSet");
                        aBTS.amrConfHrDlThreshold1 = btsRowSet.getString("amrConfHrDlThreshold1");
                        aBTS.amrConfHrDlThreshold2 = btsRowSet.getString("amrConfHrDlThreshold2");
                        aBTS.amrConfHrDlThreshold3 = btsRowSet.getString("amrConfHrDlThreshold3");
                        aBTS.amrConfHrHysteresis1 = btsRowSet.getString("amrConfHrHysteresis1");
                        aBTS.amrConfHrHysteresis2 = btsRowSet.getString("amrConfHrHysteresis2");
                        aBTS.amrConfHrHysteresis3 = btsRowSet.getString("amrConfHrHysteresis3");
                        aBTS.amrConfHrInitCodecMode = btsRowSet.getString("amrConfHrInitCodecMode");
                        aBTS.amrConfHrStartMode = btsRowSet.getString("amrConfHrStartMode");
                        aBTS.amrConfHrUlThreshold1 = btsRowSet.getString("amrConfHrUlThreshold1");
                        aBTS.amrConfHrUlThreshold2 = btsRowSet.getString("amrConfHrUlThreshold2");
                        aBTS.amrConfHrUlThreshold3 = btsRowSet.getString("amrConfHrUlThreshold3");
                        aBTS.amrHoFrInHoThrDlRxQual = btsRowSet.getString("amrHoFrInHoThrDlRxQual");
                        aBTS.amrHoFrSupReuBadCiThr = btsRowSet.getString("amrHoFrSupReuBadCiThr");
                        aBTS.amrHoFrSupReuGoodCiThr = btsRowSet.getString("amrHoFrSupReuGoodCiThr");
                        aBTS.amrHoFrThrDlRxQual = btsRowSet.getString("amrHoFrThrDlRxQual");
                        aBTS.amrHoFrThrUlRxQual = btsRowSet.getString("amrHoFrThrUlRxQual");
                        aBTS.amrHoHrInHoThrDlRxQual = btsRowSet.getString("amrHoHrInHoThrDlRxQual");
                        aBTS.amrHoHrSupReuBadCiThr = btsRowSet.getString("amrHoHrSupReuBadCiThr");
                        aBTS.amrHoHrSupReuGoodCiThr = btsRowSet.getString("amrHoHrSupReuGoodCiThr");
                        aBTS.amrHoHrThrDlRxQual = btsRowSet.getString("amrHoHrThrDlRxQual");
                        aBTS.amrHoHrThrUlRxQual = btsRowSet.getString("amrHoHrThrUlRxQual");
                        aBTS.amrPocFrPcLThrDlRxQual = btsRowSet.getString("amrPocFrPcLThrDlRxQual");
                        aBTS.amrPocFrPcLThrUlRxQual = btsRowSet.getString("amrPocFrPcLThrUlRxQual");
                        aBTS.amrPocFrPcUThrDlRxQual = btsRowSet.getString("amrPocFrPcUThrDlRxQual");
                        aBTS.amrPocFrPcUThrUlRxQual = btsRowSet.getString("amrPocFrPcUThrUlRxQual");
                        aBTS.amrPocHrPcLThrDlRxQual = btsRowSet.getString("amrPocHrPcLThrDlRxQual");
                        aBTS.amrPocHrPcLThrUlRxQual = btsRowSet.getString("amrPocHrPcLThrUlRxQual");
                        aBTS.amrPocHrPcUThrDlRxQual = btsRowSet.getString("amrPocHrPcUThrDlRxQual");
                        aBTS.amrPocHrPcUThrUlRxQual = btsRowSet.getString("amrPocHrPcUThrUlRxQual");
                        aBTS.amrSegLoadDepTchRateLower = btsRowSet.getString("amrSegLoadDepTchRateLower");
                        aBTS.amrSegLoadDepTchRateUpper = btsRowSet.getString("amrSegLoadDepTchRateUpper");
                        aBTS.bsIdentityCodeBCC = btsRowSet.getString("bsIdentityCodeBCC");
                        aBTS.bsIdentityCodeNCC = btsRowSet.getString("bsIdentityCodeNCC");
                        aBTS.btsLoadInSeg = btsRowSet.getString("btsLoadInSeg");
                        aBTS.btsLoadThreshold = btsRowSet.getString("btsLoadThreshold");
                        aBTS.btsMeasAver = btsRowSet.getString("btsMeasAver");
                        aBTS.btsSpLoadDepTchRateLower = btsRowSet.getString("btsSpLoadDepTchRateLower");
                        aBTS.btsSpLoadDepTchRateUpper = btsRowSet.getString("btsSpLoadDepTchRateUpper");
                        aBTS.callReestablishmentAllowed = btsRowSet.getString("callReestablishmentAllowed");
                        aBTS.cellBarQualify = btsRowSet.getString("cellBarQualify");
                        aBTS.cellBarred = btsRowSet.getString("cellBarred");
                        aBTS.cellId = btsRowSet.getString("cellId");
                        aBTS.cellLoadForChannelSearch = btsRowSet.getString("cellLoadForChannelSearch");
                        aBTS.cellNumberInBtsHw = btsRowSet.getString("cellNumberInBtsHw");
                        aBTS.cellReselectHysteresis = btsRowSet.getString("cellReselectHysteresis");
                        aBTS.cellReselectOffset = btsRowSet.getString("cellReselectOffset");
                        aBTS.cellReselectParamInd = btsRowSet.getString("cellReselectParamInd");
                        aBTS.cnThreshold = btsRowSet.getString("cnThreshold");
                        aBTS.cs3Cs4Enabled = btsRowSet.getString("cs3Cs4Enabled");
                        aBTS.dedicatedGPRScapacity = btsRowSet.getString("dedicatedGPRScapacity");
                        aBTS.defaultGPRScapacity = btsRowSet.getString("defaultGPRScapacity");
                        aBTS.directGPRSAccessBts = btsRowSet.getString("directGPRSAccessBts");
                        aBTS.diversityUsed = btsRowSet.getString("diversityUsed");
                        aBTS.dlNoiseLevel = btsRowSet.getString("dlNoiseLevel");
                        aBTS.downgradeGuardTimeHSCSD = btsRowSet.getString("downgradeGuardTimeHSCSD");
                        aBTS.drInUse = btsRowSet.getString("drInUse");
                        aBTS.drMethod = btsRowSet.getString("drMethod");
                        aBTS.dtxMode = btsRowSet.getString("dtxMode");
                        aBTS.earlySendingIndication = btsRowSet.getString("earlySendingIndication");
                        aBTS.egprsEnabled = btsRowSet.getString("egprsEnabled");
                        aBTS.egprsInitMcsAckMode = btsRowSet.getString("egprsInitMcsAckMode");
                        aBTS.egprsInitMcsUnAckMode = btsRowSet.getString("egprsInitMcsUnAckMode");
                        aBTS.egprsLinkAdaptEnabled = btsRowSet.getString("egprsLinkAdaptEnabled");
                        aBTS.egprsMaxBlerAckMode = btsRowSet.getString("egprsMaxBlerAckMode");
                        aBTS.egprsMaxBlerUnAckMode = btsRowSet.getString("egprsMaxBlerUnAckMode");
                        aBTS.egprsMeanBepOffset8psk = btsRowSet.getString("egprsMeanBepOffset8psk");
                        aBTS.egprsMeanBepOffsetGmsk = btsRowSet.getString("egprsMeanBepOffsetGmsk");
                        aBTS.emergencyCallRestricted = btsRowSet.getString("emergencyCallRestricted");
                        aBTS.fddQMin = btsRowSet.getString("fddQMin");
                        aBTS.fddQOffset = btsRowSet.getString("fddQOffset");
                        aBTS.forcedHrCiAverPeriod = btsRowSet.getString("forcedHrCiAverPeriod");
                        aBTS.forcedHrModeHysteresis = btsRowSet.getString("forcedHrModeHysteresis");
                        aBTS.gprsEnabled = btsRowSet.getString("gprsEnabled");
                        aBTS.gprsMsTxpwrMaxCCH = btsRowSet.getString("gprsMsTxpwrMaxCCH");
                        aBTS.gprsNonBCCHRxlevLower = btsRowSet.getString("gprsNonBCCHRxlevLower");
                        aBTS.gprsNonBCCHRxlevUpper = btsRowSet.getString("gprsNonBCCHRxlevUpper");
                        aBTS.gprsRxlevAccessMin = btsRowSet.getString("gprsRxlevAccessMin");
                        aBTS.hcsPriorityClass = btsRowSet.getString("hcsPriorityClass");
                        aBTS.hcsThreshold = btsRowSet.getString("hcsThreshold");
                        aBTS.hoppingMode = btsRowSet.getString("hoppingMode");
                        aBTS.hoppingSequenceNumber1 = btsRowSet.getString("hoppingSequenceNumber1");
                        aBTS.hoppingSequenceNumber2 = btsRowSet.getString("hoppingSequenceNumber2");
                        aBTS.inactEndTimeHour = btsRowSet.getString("inactEndTimeHour");
                        aBTS.inactEndTimeMinute = btsRowSet.getString("inactEndTimeMinute");
                        aBTS.inactStartTimeHour = btsRowSet.getString("inactStartTimeHour");
                        aBTS.inactStartTimeMinute = btsRowSet.getString("inactStartTimeMinute");
                        aBTS.inactWeekDays = btsRowSet.getString("inactWeekDays");
                        aBTS.csAckDl = btsRowSet.getString("csAckDl");
                        aBTS.csAckUl = btsRowSet.getString("csAckUl");
                        aBTS.csUnackDl = btsRowSet.getString("csUnackDl");
                        aBTS.csUnackUl = btsRowSet.getString("csUnackUl");
                        aBTS.interferenceAveragingProcessAverPeriod = btsRowSet.getString("interferenceAveragingProcessAverPeriod");
                        aBTS.interferenceAveragingProcessBoundary0 = btsRowSet.getString("interferenceAveragingProcessBoundary0");
                        aBTS.interferenceAveragingProcessBoundary1 = btsRowSet.getString("interferenceAveragingProcessBoundary1");
                        aBTS.interferenceAveragingProcessBoundary2 = btsRowSet.getString("interferenceAveragingProcessBoundary2");
                        aBTS.interferenceAveragingProcessBoundary3 = btsRowSet.getString("interferenceAveragingProcessBoundary3");
                        aBTS.interferenceAveragingProcessBoundary4 = btsRowSet.getString("interferenceAveragingProcessBoundary4");
                        aBTS.interferenceAveragingProcessBoundary5 = btsRowSet.getString("interferenceAveragingProcessBoundary5");
                        aBTS.limForTriggeringOscDhrMultiplexing = btsRowSet.getString("limForTriggeringOscDhrMultiplexing");
                        aBTS.locationAreaIdLAC = btsRowSet.getString("locationAreaIdLAC");
                        aBTS.locationAreaIdMCC = btsRowSet.getString("locationAreaIdMCC");
                        aBTS.locationAreaIdMNC = btsRowSet.getString("locationAreaIdMNC");
                        aBTS.lowerLimitCellLoadHSCSD = btsRowSet.getString("lowerLimitCellLoadHSCSD");
                        aBTS.maioOffset = btsRowSet.getString("maioOffset");
                        aBTS.maioStep = btsRowSet.getString("maioStep");
                        aBTS.masterBcf = btsRowSet.getString("masterBcf");
                        aBTS.maxGPRSCapacity = btsRowSet.getString("maxGPRSCapacity");
                        aBTS.maxNumberOfRepetition = btsRowSet.getString("maxNumberOfRepetition");
                        aBTS.maxNumberRetransmission = btsRowSet.getString("maxNumberRetransmission");
                        aBTS.maxQueueLength = btsRowSet.getString("maxQueueLength");
                        aBTS.maxTimeLimitDirectedRetry = btsRowSet.getString("maxTimeLimitDirectedRetry");
                        aBTS.measListUsedDuringMeas = btsRowSet.getString("measListUsedDuringMeas");
                        aBTS.minExhaustHSCSD = btsRowSet.getString("minExhaustHSCSD");
                        aBTS.minHSCSDcapacityCell = btsRowSet.getString("minHSCSDcapacityCell");
                        aBTS.minTimeLimitDirectedRetry = btsRowSet.getString("minTimeLimitDirectedRetry");
                        aBTS.msMaxDistInCallSetup = btsRowSet.getString("msMaxDistInCallSetup");
                        aBTS.msPriorityUsedInQueueing = btsRowSet.getString("msPriorityUsedInQueueing");
                        aBTS.msTxPwrMaxCCH = btsRowSet.getString("msTxPwrMaxCCH");
                        aBTS.msTxPwrMaxGSM = btsRowSet.getString("msTxPwrMaxGSM");
                        aBTS.multiBandCell = btsRowSet.getString("multiBandCell");
                        aBTS.multiBandCellReporting = btsRowSet.getString("multiBandCellReporting");
                        aBTS.nbrOfSlotsSpreadTrans = btsRowSet.getString("nbrOfSlotsSpreadTrans");
                        aBTS.newEstabCausesSupport = btsRowSet.getString("newEstabCausesSupport");
                        aBTS.noOfBlocksForAccessGrant = btsRowSet.getString("noOfBlocksForAccessGrant");
                        aBTS.noOfMFramesBetweenPaging = btsRowSet.getString("noOfMFramesBetweenPaging");
                        aBTS.nonBCCHLayerOffset = btsRowSet.getString("nonBCCHLayerOffset");
                        aBTS.nwName = btsRowSet.getString("nwName");
                        aBTS.pcuCsHopping = btsRowSet.getString("pcuCsHopping");
                        aBTS.pcuCsNonHopping = btsRowSet.getString("pcuCsNonHopping");
                        aBTS.pcuDlBlerCpHopping = btsRowSet.getString("pcuDlBlerCpHopping");
                        aBTS.pcuDlBlerCpNonHop = btsRowSet.getString("pcuDlBlerCpNonHop");
                        aBTS.pcuDlLaRiskLevel = btsRowSet.getString("pcuDlLaRiskLevel");
                        aBTS.pcuUlBlerCpHopping = btsRowSet.getString("pcuUlBlerCpHopping");
                        aBTS.pcuUlBlerCpNonHop = btsRowSet.getString("pcuUlBlerCpNonHop");
                        aBTS.pcuUlLaRiskLevel = btsRowSet.getString("pcuUlLaRiskLevel");
                        aBTS.penaltyTime = btsRowSet.getString("penaltyTime");
                        aBTS.powerOffset = btsRowSet.getString("powerOffset");
                        aBTS.preferBCCHfreqGPRS2 = btsRowSet.getString("preferBCCHfreqGPRS2");
                        aBTS.qSearchI = btsRowSet.getString("qSearchI");
                        aBTS.qSearchP = btsRowSet.getString("qSearchP");
                        aBTS.queuePriorityNonUrgentHo = btsRowSet.getString("queuePriorityNonUrgentHo");
                        aBTS.queuePriorityUsed = btsRowSet.getString("queuePriorityUsed");
                        aBTS.queueingPriorityCall = btsRowSet.getString("queueingPriorityCall");
                        aBTS.queueingPriorityHandover = btsRowSet.getString("queueingPriorityHandover");
                        aBTS.raReselectHysteresis = btsRowSet.getString("raReselectHysteresis");
                        aBTS.rac = btsRowSet.getString("rac");
                        aBTS.radioLinkTimeout = btsRowSet.getString("radioLinkTimeout");
                        aBTS.radioLinkTimeoutAmr = btsRowSet.getString("radioLinkTimeoutAmr");
                        aBTS.radioLinkTimeoutAmrHrUlIncreaseStep = btsRowSet.getString("radioLinkTimeoutAmrHrUlIncreaseStep");
                        aBTS.radioLinkTimeoutAmrUlIncreaseStep = btsRowSet.getString("radioLinkTimeoutAmrUlIncreaseStep");
                        aBTS.radioLinkTimeoutUlIncreaseStep = btsRowSet.getString("radioLinkTimeoutUlIncreaseStep");
                        aBTS.rxLevAccessMin = btsRowSet.getString("rxLevAccessMin");
                        aBTS.scaleOrd = btsRowSet.getString("scaleOrd");
                        aBTS.segmentId = btsRowSet.getString("segmentId");
                        aBTS.segmentName = btsRowSet.getString("segmentName");
                        aBTS.smsCbUsed = btsRowSet.getString("smsCbUsed");
                        aBTS.stircEnabled = btsRowSet.getString("stircEnabled");
                        aBTS.tchRateIntraCellHo = btsRowSet.getString("tchRateIntraCellHo");
                        aBTS.temporaryOffset = btsRowSet.getString("temporaryOffset");
                        aBTS.throughputFactor_cs1cs4dlcs = btsRowSet.getString("throughputFactor_cs1cs4dlcs");
                        aBTS.throughputFactor_cs1cs4ulcs = btsRowSet.getString("throughputFactor_cs1cs4ulcs");
                        aBTS.throughputFactor_mcs1mcs4ulcs = btsRowSet.getString("throughputFactor_mcs1mcs4ulcs");
                        aBTS.throughputFactor_mcs1mcs9dlcs = btsRowSet.getString("throughputFactor_mcs1mcs9dlcs");
                        aBTS.throughputFactor_mcs1mcs9ulcs = btsRowSet.getString("throughputFactor_mcs1mcs9ulcs");
                        aBTS.timeLimitCall = btsRowSet.getString("timeLimitCall");
                        aBTS.timeLimitHandover = btsRowSet.getString("timeLimitHandover");
                        aBTS.timerPeriodicUpdateMs = btsRowSet.getString("timerPeriodicUpdateMs");
                        aBTS.trxPriorityInTchAlloc = btsRowSet.getString("trxPriorityInTchAlloc");
                        aBTS.ulNoiseLevel = btsRowSet.getString("ulNoiseLevel");
                        aBTS.upgradeGainHSCSD = btsRowSet.getString("upgradeGainHSCSD");
                        aBTS.upgradeGuardTimeHSCSD = btsRowSet.getString("upgradeGuardTimeHSCSD");
                        aBTS.upperLimitCellLoadHSCSD = btsRowSet.getString("upperLimitCellLoadHSCSD");
                        aBTS.upperLimitRegularLoadHSCSD = btsRowSet.getString("upperLimitRegularLoadHSCSD");
                        aBTS.usedMobileAllocation = btsRowSet.getString("usedMobileAllocation");
                        aBTS.PLMNS = btsRowSet.getString("PLMN");

                        if (aBTS.BTSid.equals(aBTS.segmentId)) {
                            aBTS.MBC = false;
                        } else {
                            aBTS.MBC = true;
                        }
                        aBTSList.add(aBTS);
                    }

                    JdbcRowSet atrxRowSet = aDBConnection.SelectSql(getTRXQueryS);
                    while (atrxRowSet.next()) {
                        TRX aTrx = new TRX();
                        aTrx.PLMN = atrxRowSet.getString("PLMN");
                        aTrx.BSC = atrxRowSet.getString("BSC");
                        aTrx.BTS = atrxRowSet.getString("BTS");
                        aTrx.TRX = atrxRowSet.getString("TRX");
                        aTrx.name = atrxRowSet.getString("name");
                        aTrx.adminState = atrxRowSet.getString("adminState");
                        aTrx.channel0AdminState = atrxRowSet.getString("channel0AdminState");
                        aTrx.channel0Pcm = pcmList.get(atrxRowSet.getString("channel0Pcm"));
                        aTrx.channel0Subslot = atrxRowSet.getString("channel0Subslot");
                        aTrx.channel0Tsl = atrxRowSet.getString("channel0Tsl");
                        aTrx.channel0Type = atrxRowSet.getString("channel0Type");
                        aTrx.channel1AdminState = atrxRowSet.getString("channel1AdminState");
                        aTrx.channel1Pcm = pcmList.get(atrxRowSet.getString("channel1Pcm"));
                        aTrx.channel1Subslot = atrxRowSet.getString("channel1Subslot");
                        aTrx.channel1Tsl = atrxRowSet.getString("channel1Tsl");
                        aTrx.channel1Type = atrxRowSet.getString("channel1Type");
                        aTrx.channel2AdminState = atrxRowSet.getString("channel2AdminState");
                        aTrx.channel2Pcm = pcmList.get(atrxRowSet.getString("channel2Pcm"));
                        aTrx.channel2Subslot = atrxRowSet.getString("channel2Subslot");
                        aTrx.channel2Tsl = atrxRowSet.getString("channel2Tsl");
                        aTrx.channel2Type = atrxRowSet.getString("channel2Type");
                        aTrx.channel3AdminState = atrxRowSet.getString("channel3AdminState");
                        aTrx.channel3Pcm = pcmList.get(atrxRowSet.getString("channel3Pcm"));
                        aTrx.channel3Subslot = atrxRowSet.getString("channel3Subslot");
                        aTrx.channel3Tsl = atrxRowSet.getString("channel3Tsl");
                        aTrx.channel3Type = atrxRowSet.getString("channel3Type");
                        aTrx.channel4AdminState = atrxRowSet.getString("channel4AdminState");
                        aTrx.channel4Pcm = pcmList.get(atrxRowSet.getString("channel4Pcm"));
                        aTrx.channel4Subslot = atrxRowSet.getString("channel4Subslot");
                        aTrx.channel4Tsl = atrxRowSet.getString("channel4Tsl");
                        aTrx.channel4Type = atrxRowSet.getString("channel4Type");
                        aTrx.channel5AdminState = atrxRowSet.getString("channel5AdminState");
                        aTrx.channel5Pcm = pcmList.get(atrxRowSet.getString("channel5Pcm"));
                        aTrx.channel5Subslot = atrxRowSet.getString("channel5Subslot");
                        aTrx.channel5Tsl = atrxRowSet.getString("channel5Tsl");
                        aTrx.channel5Type = atrxRowSet.getString("channel5Type");
                        aTrx.channel6AdminState = atrxRowSet.getString("channel6AdminState");
                        aTrx.channel6Pcm = pcmList.get(atrxRowSet.getString("channel6Pcm"));
                        aTrx.channel6Subslot = atrxRowSet.getString("channel6Subslot");
                        aTrx.channel6Tsl = atrxRowSet.getString("channel6Tsl");
                        aTrx.channel6Type = atrxRowSet.getString("channel6Type");
                        aTrx.channel7AdminState = atrxRowSet.getString("channel7AdminState");
                        aTrx.channel7Pcm = pcmList.get(atrxRowSet.getString("channel7Pcm"));
                        aTrx.channel7Subslot = atrxRowSet.getString("channel7Subslot");
                        aTrx.channel7Tsl = atrxRowSet.getString("channel7Tsl");
                        aTrx.channel7Type = atrxRowSet.getString("channel7Type");

                        aTrx.daPool_ID = atrxRowSet.getString("daPool_ID");
                        if (!aTrx.daPool_ID.equals("65535")) {
                            if (!dapList.contains(aTrx.daPool_ID)) {
                                dapList.add(aTrx.daPool_ID);
                            }
                        }
                        aTrx.gprsEnabledTrx = atrxRowSet.getString("gprsEnabledTrx");
                        aTrx.halfRateSupport = atrxRowSet.getString("halfRateSupport");
                        aTrx.initialFrequency = atrxRowSet.getString("initialFrequency");
                        aTrx.lapdLinkName = atrxRowSet.getString("lapdLinkName");
                        aTrx.subslotsForSignalling = atrxRowSet.getString("subslotsForSignalling");
                        aTrx.tsc = atrxRowSet.getString("tsc");
                        aTrx.preferredBcchMark = atrxRowSet.getString("preferredBcchMark");

                        aTrxList.add(aTrx);

                    }

                    String allTrxString = "";

                    for (TRX aTrx : aTrxList) {
                        String aTrxStrngS = " <managedObject class=\"TRX\" distName=\"" + aTrx.PLMN + "\"  operation=\"create\">\n";

                        aTrxStrngS += "<p name=\"name\">" + aTrx.name + "</p>\n";
                        aTrxStrngS += "<p name=\"adminState\">" + aTrx.adminState + "</p>\n";

                        aTrxStrngS += "<p name=\"channel0AdminState\">" + aTrx.channel0AdminState + "</p>\n";

                        aTrxStrngS += "<p name=\"channel0Pcm\">" + aTrx.channel0Pcm + "</p>\n";
                        aTrxStrngS += "<p name=\"channel0Subslot\">" + aTrx.channel0Subslot + "</p>\n";
                        aTrxStrngS += "<p name=\"channel0Tsl\">" + aTrx.channel0Tsl + "</p>\n";
                        aTrxStrngS += "<p name=\"channel0Type\">" + aTrx.channel0Type + "</p>\n";
                        aTrxStrngS += "<p name=\"channel1AdminState\">" + aTrx.channel1AdminState + "</p>\n";

                        aTrxStrngS += "<p name=\"channel1Pcm\">" + aTrx.channel1Pcm + "</p>\n";
                        aTrxStrngS += "<p name=\"channel1Subslot\">" + aTrx.channel1Subslot + "</p>\n";
                        aTrxStrngS += "<p name=\"channel1Tsl\">" + aTrx.channel1Tsl + "</p>\n";
                        aTrxStrngS += "<p name=\"channel1Type\">" + aTrx.channel1Type + "</p>\n";
                        aTrxStrngS += "<p name=\"channel2AdminState\">" + aTrx.channel2AdminState + "</p>\n";

                        aTrxStrngS += "<p name=\"channel2Pcm\">" + aTrx.channel2Pcm + "</p>\n";
                        aTrxStrngS += "<p name=\"channel2Subslot\">" + aTrx.channel2Subslot + "</p>\n";
                        aTrxStrngS += "<p name=\"channel2Tsl\">" + aTrx.channel2Tsl + "</p>\n";
                        aTrxStrngS += "<p name=\"channel2Type\">" + aTrx.channel2Type + "</p>\n";
                        aTrxStrngS += "<p name=\"channel3AdminState\">" + aTrx.channel3AdminState + "</p>\n";

                        aTrxStrngS += "<p name=\"channel3Pcm\">" + aTrx.channel3Pcm + "</p>\n";
                        aTrxStrngS += "<p name=\"channel3Subslot\">" + aTrx.channel3Subslot + "</p>\n";
                        aTrxStrngS += "<p name=\"channel3Tsl\">" + aTrx.channel3Tsl + "</p>\n";
                        aTrxStrngS += "<p name=\"channel3Type\">" + aTrx.channel3Type + "</p>\n";
                        aTrxStrngS += "<p name=\"channel4AdminState\">" + aTrx.channel4AdminState + "</p>\n";

                        aTrxStrngS += "<p name=\"channel4Pcm\">" + aTrx.channel4Pcm + "</p>\n";
                        aTrxStrngS += "<p name=\"channel4Subslot\">" + aTrx.channel4Subslot + "</p>\n";
                        aTrxStrngS += "<p name=\"channel4Tsl\">" + aTrx.channel4Tsl + "</p>\n";
                        aTrxStrngS += "<p name=\"channel4Type\">" + aTrx.channel4Type + "</p>\n";
                        aTrxStrngS += "<p name=\"channel5AdminState\">" + aTrx.channel5AdminState + "</p>\n";

                        aTrxStrngS += "<p name=\"channel5Pcm\">" + aTrx.channel5Pcm + "</p>\n";
                        aTrxStrngS += "<p name=\"channel5Subslot\">" + aTrx.channel5Subslot + "</p>\n";
                        aTrxStrngS += "<p name=\"channel5Tsl\">" + aTrx.channel5Tsl + "</p>\n";
                        aTrxStrngS += "<p name=\"channel5Type\">" + aTrx.channel5Type + "</p>\n";
                        aTrxStrngS += "<p name=\"channel6AdminState\">" + aTrx.channel6AdminState + "</p>\n";

                        aTrxStrngS += "<p name=\"channel6Pcm\">" + aTrx.channel6Pcm + "</p>\n";
                        aTrxStrngS += "<p name=\"channel6Subslot\">" + aTrx.channel6Subslot + "</p>\n";
                        aTrxStrngS += "<p name=\"channel6Tsl\">" + aTrx.channel6Tsl + "</p>\n";
                        aTrxStrngS += "<p name=\"channel6Type\">" + aTrx.channel6Type + "</p>\n";
                        aTrxStrngS += "<p name=\"channel7AdminState\">" + aTrx.channel7AdminState + "</p>\n";

                        aTrxStrngS += "<p name=\"channel7Pcm\">" + aTrx.channel7Pcm + "</p>\n";
                        aTrxStrngS += "<p name=\"channel7Subslot\">" + aTrx.channel7Subslot + "</p>\n";
                        aTrxStrngS += "<p name=\"channel7Tsl\">" + aTrx.channel7Tsl + "</p>\n";
                        aTrxStrngS += "<p name=\"channel7Type\">" + aTrx.channel7Type + "</p>\n";
                        aTrxStrngS += "<p name=\"daPool_ID\">" + aTrx.daPool_ID + "</p>\n";

                        aTrxStrngS += "<p name=\"gprsEnabledTrx\">" + aTrx.gprsEnabledTrx + "</p>\n";
                        aTrxStrngS += "<p name=\"halfRateSupport\">" + aTrx.halfRateSupport + "</p>\n";
                        aTrxStrngS += "<p name=\"initialFrequency\">" + aTrx.initialFrequency + "</p>\n";
                        aTrxStrngS += "<p name=\"lapdLinkName\">" + aTrx.lapdLinkName + "</p>\n";

                        aTrxStrngS += "<p name=\"preferredBcchMark\">" + aTrx.preferredBcchMark + "</p>\n";
                        aTrxStrngS += "<p name=\"subslotsForSignalling\">" + aTrx.subslotsForSignalling + "</p>\n";

                        aTrxStrngS += "<p name=\"tsc\">" + aTrx.tsc + "</p>\n</managedobject>\n";

                        allTrxString += aTrxStrngS + "\n";

                    }

                    List<DAP> aDapList = new ArrayList<DAP>();
                    List<CSDAP> aCsdapList = new ArrayList<CSDAP>();

                    aDBConnection.OpenDBConnection();

                    String BtsString = BcfString + "\n" + GetBtsString(aBTSList);
                    BtsString += GetLapdString(lapdBCFAllinfoList, Bsc, Bcf);
                    BtsString += allTrxString + "\n";

                    if (dapList.size() > 0) {
                        String queryDapPart = "(";
                        for (String aDapS : dapList) {
                            queryDapPart += "DAP=" + aDapS + " or ";
                        }

                        queryDapPart = queryDapPart.substring(0, queryDapPart.length() - 3);
                        queryDapPart += " )";

                        String dapQueryS = "Select * from dap where bsc=" + Bsc + " and " + queryDapPart;

                        JdbcRowSet aDapRowSet = aDBConnection.SelectSql(dapQueryS);

                        while (aDapRowSet.next()) {
                            DAP aDap = new DAP();

                            aDap.setBSC(aDapRowSet.getString("BSC"));
                            aDap.setDAP(aDapRowSet.getString("DAP"));
                            aDap.setPLMN(aDapRowSet.getString("PLMN"));
                            aDap.setBcsuID(aDapRowSet.getString("bcsuID"));
                            aDap.setFirstTSL(aDapRowSet.getString("firstTSL"));
                            aDap.setLastTSL(aDapRowSet.getString("lastTSL"));
                            aDap.setPcmCircuit_ID(pcmList.get(aDapRowSet.getString("pcmCircuit_ID")));
                            aDap.setPcuID(aDapRowSet.getString("pcuID"));

                            aDapList.add(aDap);
                        }
                        String allDapString = "";
                        for (DAP aDap : aDapList) {
                            String aDapString = " <managedObject class=\"DAP\" distName=\"" + aDap.getPLMN() + "\"  operation=\"create\">\n";
                            aDapString += "<p name=\"bcsuID\">" + aDap.getBcsuID() + "</p>\n";
                            aDapString += "<p name=\"firstTSL\">" + aDap.getFirstTSL() + "</p>\n";
                            aDapString += "<p name=\"lastTSL\">" + aDap.getLastTSL() + "</p>\n";
                            aDapString += "<p name=\"pcmCircuit_ID\">" + aDap.getPcmCircuit_ID() + "</p>\n";
                            aDapString += "<p name=\"pcuID\">" + aDap.getPcuID() + "</p>\n</managedobject>\n";
                            allDapString += aDapString + "\n";
                        }
                        BtsString += allDapString + "\n";

                    }

                    if (aBcfCsdap.CsdapList.size() > 0) {
                        String queryCsdapPart = "(";
                        for (String aCsdap : aBcfCsdap.CsdapList) {
                            queryCsdapPart += "CSDAP=" + aCsdap + " or ";
                        }

                        queryCsdapPart = queryCsdapPart.substring(0, queryCsdapPart.length() - 3);
                        queryCsdapPart += " )";

                        String csdapQuery = "Select * from csdap where bsc=" + Bsc + " and " + queryCsdapPart;

                        JdbcRowSet aCsdapRowSet = aDBConnection.SelectSql(csdapQuery);

                        while (aCsdapRowSet.next()) {
                            CSDAP aCsdap = new CSDAP();

                            aCsdap.setBSC(aCsdapRowSet.getString("BSC"));
                            aCsdap.setCSDAP(aCsdapRowSet.getString("CSDAP"));
                            aCsdap.setPLMN(aCsdapRowSet.getString("PLMN"));
                            aCsdap.setBcfAbisIF(aCsdapRowSet.getString("bcfAbisIF"));
                            aCsdap.setFirstTSL(aCsdapRowSet.getString("firstTSL"));
                            aCsdap.setLastTSL(aCsdapRowSet.getString("lastTSL"));
                            aCsdap.setPcmCircuit_ID(pcmList.get(aCsdapRowSet.getString("pcmCircuit_ID")));
                            aCsdap.setBcfTslShift(aCsdapRowSet.getString("bcfTslShift"));

                            aCsdapList.add(aCsdap);
                        }

                        String allCsCSDAPString = "";
                        for (CSDAP aCSDAP : aCsdapList) {
                            String aCsCSDAPString = " <managedObject class=\"CSDAP\" distName=\"" + aCSDAP.getPLMN() + "\"  operation=\"create\">\n";
                            aCsCSDAPString += "<p name=\"bcfAbisIF\">" + aCSDAP.getBcfAbisIF() + "</p>\n";
                            aCsCSDAPString += "<p name=\"firstTSL\">" + aCSDAP.getFirstTSL() + "</p>\n";
                            aCsCSDAPString += "<p name=\"lastTSL\">" + aCSDAP.getLastTSL() + "</p>\n";
                            aCsCSDAPString += "<p name=\"pcmCircuit_ID\">" + aCSDAP.getPcmCircuit_ID() + "</p>\n";
                            aCsCSDAPString += "<p name=\"bcfTslShift\">" + aCSDAP.getBcfTslShift() + "</p>\n</managedobject>\n";
                            allCsCSDAPString += aCsCSDAPString + "\n";
                        }
                        BtsString += allCsCSDAPString + "\n";

                    }

                    String HocPocString = GetHOCString(aDBConnection, Bsc, Bcf) + "\n" + GetPOCString(aDBConnection, Bsc, Bcf);
                    //String PocString=GetPOCString(aDBConnection, Bsc, Bcf);

                    BtsString += "\n" + HocPocString;
                    String StartSatring = "<?xml version=\"1.0\"?>\n<!DOCTYPE raml SYSTEM 'raml20.dtd'>\n<raml version=\"2.0\" xmlns=\"raml20.xsd\">\n<cmData type=\"plan\">\n<header>\n<log dateTime=\"\" action=\"created\" user=\"blOMC\" appInfo=\"blNokiaTool\"/>\n</header>\n";
                    StartSatring += BtsString;
                    StartSatring += GetADCEString(aDBConnection, Bsc, Bcf) + "\n";
                    StartSatring += GetADJWString(aDBConnection, Bsc, Bcf) + "\n";
                    StartSatring += "\n </cmData>\n</raml>";
                    WriteXml(StartSatring, aBcfCsdap.aBcfList.get(0).getName());

                }

            }

        }

    }

    public String GetBCFString(BcfCsdap aBcfCsdap) {
        aBcfCsdap.aBcfList.get(0).getBCF();
        List<ExternalAlarms> externalAlarmses = aBcfCsdap.aBcfList.get(0).getExternalAlarmList();
        String BCfString = "<managedObject class=\"BCF\" distName=\"PLMN-PLMN/BSC-" + aBcfCsdap.aBcfList.get(0).getBSC() + "/BCF-" + aBcfCsdap.aBcfList.get(0).getBCF() + "\" operation=\"update\">\n";
        BCfString += "<list name=\"externalAlarmDefinition\">\n";
        for (ExternalAlarms aAlarms : externalAlarmses) {
            BCfString += " <item>\n";
            BCfString += "<p name=\"inputId\">" + aAlarms.inputId + "</p>\n";
            BCfString += "<p name=\"inputTextId\">" + aAlarms.inputTextId + "</p>\n";
            BCfString += "<p name=\"polarity\">" + aAlarms.polarity + "</p>\n";
            BCfString += "<p name=\"reportingRoute\">" + aAlarms.reportingRoute + "</p>\n";
            BCfString += "<p name=\"severity\">" + aAlarms.severity + "</p>\n";
            BCfString += " </item>\n";

        }
        BCfString += " </list>\n";
        BCfString += "</managedObject>\n";

        return BCfString;
    }

    public String GetHOCString(DBConnection aDBConnection, String Bsc, String Bcf) throws SQLException {
        ResultSet aResultSet1 = aDBConnection.SelectQueryResultSet("Select * from hoc where bsc=" + Bsc + " and bcf=" + Bcf);
        ResultSetMetaData hocColumns = (ResultSetMetaData) aResultSet1.getMetaData();
        int columnCount = hocColumns.getColumnCount();

        List<String> HocColumnNameList = new ArrayList<String>();
        String HocString = "";
        for (int i = 1; i < columnCount + 1; i++) {
            HocColumnNameList.add(hocColumns.getColumnName(i));
        }
        while (aResultSet1.next()) {
            for (int i = 0; i < columnCount - 4; i++) {

                if (i == 0) {
                    HocString += "<managedObject class=\"HOC\" distName=\"" + aResultSet1.getString(HocColumnNameList.get(i)) + "\" operation=\"create\">\n";
                }
                try {
                    if (i > 4) {
                        if (!(aResultSet1.getString(HocColumnNameList.get(i)).isEmpty())) {
                            HocString += "<p name=\"" + HocColumnNameList.get(i) + "\">" + aResultSet1.getString(HocColumnNameList.get(i)) + "</p>\n";
                        }

                    }
                } catch (Exception e) {

                }
                if (i == columnCount - 5) {

                    HocString += "<list name=\"lowerCiLimit\">\n<p>30</p>\n<p>25</p>\n<p>20</p>\n<p>17</p>\n<p>13</p>\n<p>9</p>\n</list>\n";
                    HocString += "<list name=\"priorityAdjStep\">\n<p>3</p>\n<p>1</p>\n<p>0</p>\n<p>-1</p>\n<p>-2</p>\n<p>-5</p>\n<p>-8</p>\n</list>\n";
                    HocString += "</managedObject>\n";

                }

            }
        }

        return HocString;
    }

    public String GetPOCString(DBConnection aDBConnection, String Bsc, String Bcf) throws SQLException {
        ResultSet aResultSet1 = aDBConnection.SelectQueryResultSet("Select * from poc where bsc=" + Bsc + " and bcf=" + Bcf);
        ResultSetMetaData pocColumns = (ResultSetMetaData) aResultSet1.getMetaData();
        int columnCount = pocColumns.getColumnCount();

        List<String> PocColumnNameList = new ArrayList<String>();
        String PocString = "";
        for (int i = 1; i < columnCount + 1; i++) {
            PocColumnNameList.add(pocColumns.getColumnName(i));
        }
        while (aResultSet1.next()) {
            for (int i = 0; i < columnCount - 5; i++) {

                if (i == 0) {
                    PocString += "<managedObject class=\"POC\" distName=\"" + aResultSet1.getString(PocColumnNameList.get(i)) + "\" operation=\"create\">\n";
                }
                if (i > 4) {
                    try {
                        if (!(aResultSet1.getString(PocColumnNameList.get(i)).isEmpty())) {
                            PocString += "<p name=\"" + PocColumnNameList.get(i) + "\">" + aResultSet1.getString(PocColumnNameList.get(i)) + "</p>\n";
                        }
                    } catch (Exception e) {

                    }
                }
                if (i == columnCount - 6) {

                    PocString += "<list name=\"lowerCiLimit\">\n<p>30</p>\n<p>25</p>\n<p>20</p>\n<p>17</p>\n<p>13</p>\n<p>9</p>\n</list>\n";
                    PocString += "<list name=\"priorityAdjStep\">\n<p>3</p>\n<p>1</p>\n<p>0</p>\n<p>-1</p>\n<p>-2</p>\n<p>-5</p>\n<p>-8</p>\n</list>\n";
                    PocString += "</managedObject>\n";
                }

            }
        }

        return PocString;
    }

    public String GetBtsString(List<BTS> aBtsList) {

        String allBtsString = "";
        for (BTS aBts : aBtsList) {

            String aBtsString = "\n";

            if (!aBts.MBC) {
                aBtsString += "<managedObject class=\"BTS\" distName=\"" + aBts.PLMNS + "\" operation=\"create\">\n<list name=\"notAllowedAccessClasses\">\n<p>0</p>\n<p>0</p>\n<p>0</p>\n<p>0</p>\n<p>0</p>\n<p>0</p>\n<p>0</p>\n<p>0</p>\n<p>0</p>\n<p>0</p>\n<p>0</p>\n<p>0</p>\n<p>0</p>\n<p>0</p>\n<p>0</p>\n<p>0</p>\n</list>\n<list name=\"plmnPermitted\">\n<p>1</p>\n<p>1</p>\n<p>1</p>\n<p>1</p>\n<p>1</p>\n<p>1</p>\n<p>1</p>\n<p>1</p>\n</list>\n";

                aBtsString += "<p name=\"frequencyBandInUse\">" + aBts.frequencyBandInUse + "</p>\n";
                aBtsString += "<p name=\"gprsMsTxPwrMaxCCH1x00\">" + aBts.gprsMsTxPwrMaxCCH1x00 + "</p>\n";
                aBtsString += "<p name=\"minMsTxPower\">" + aBts.minMsTxPower + "</p>\n";
                aBtsString += "<p name=\"msTxPwrMaxCCH1x00\">" + aBts.msTxPwrMaxCCH1x00 + "</p>\n";
                aBtsString += "<p name=\"msTxPwrMaxGSM1x00\">" + aBts.msTxPwrMaxGSM1x00 + "</p>\n";
                aBtsString += "<p name=\"name\">" + aBts.name + "</p>\n";
                aBtsString += "<p name=\"adaptiveLaAlgorithm\">" + aBts.adaptiveLaAlgorithm + "</p>\n";
                aBtsString += "<p name=\"adminState\">" + aBts.adminState + "</p>\n";
                aBtsString += "<p name=\"allowIMSIAttachDetach\">" + aBts.allowIMSIAttachDetach + "</p>\n";
                aBtsString += "<p name=\"amhLowerLoadThreshold\">" + aBts.amhLowerLoadThreshold + "</p>\n";
                aBtsString += "<p name=\"amhMaxLoadOfTgtCell\">" + aBts.amhMaxLoadOfTgtCell + "</p>\n";
                aBtsString += "<p name=\"amhTrhoGuardTime\">" + aBts.amhTrhoGuardTime + "</p>\n";
                aBtsString += "<p name=\"amhUpperLoadThreshold\">" + aBts.amhUpperLoadThreshold + "</p>\n";
                aBtsString += "<p name=\"amrConfFrCodecModeSet\">" + aBts.amrConfFrCodecModeSet + "</p>\n";
                aBtsString += "<p name=\"amrConfFrDlThreshold1\">" + aBts.amrConfFrDlThreshold1 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrDlThreshold2\">" + aBts.amrConfFrDlThreshold2 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrDlThreshold3\">" + aBts.amrConfFrDlThreshold3 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrHysteresis1\">" + aBts.amrConfFrHysteresis1 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrHysteresis2\">" + aBts.amrConfFrHysteresis2 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrHysteresis3\">" + aBts.amrConfFrHysteresis3 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrInitCodecMode\">" + aBts.amrConfFrInitCodecMode + "</p>\n";
                aBtsString += "<p name=\"amrConfFrStartMode\">" + aBts.amrConfFrStartMode + "</p>\n";
                aBtsString += "<p name=\"amrConfFrUlThreshold1\">" + aBts.amrConfFrUlThreshold1 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrUlThreshold2\">" + aBts.amrConfFrUlThreshold2 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrUlThreshold3\">" + aBts.amrConfFrUlThreshold3 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrCodecModeSet\">" + aBts.amrConfHrCodecModeSet + "</p>\n";
                aBtsString += "<p name=\"amrConfHrDlThreshold1\">" + aBts.amrConfHrDlThreshold1 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrDlThreshold2\">" + aBts.amrConfHrDlThreshold2 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrDlThreshold3\">" + aBts.amrConfHrDlThreshold3 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrHysteresis1\">" + aBts.amrConfHrHysteresis1 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrHysteresis2\">" + aBts.amrConfHrHysteresis2 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrHysteresis3\">" + aBts.amrConfHrHysteresis3 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrInitCodecMode\">" + aBts.amrConfHrInitCodecMode + "</p>\n";
                aBtsString += "<p name=\"amrConfHrStartMode\">" + aBts.amrConfHrStartMode + "</p>\n";
                aBtsString += "<p name=\"amrConfHrUlThreshold1\">" + aBts.amrConfHrUlThreshold1 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrUlThreshold2\">" + aBts.amrConfHrUlThreshold2 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrUlThreshold3\">" + aBts.amrConfHrUlThreshold3 + "</p>\n";
                aBtsString += "<p name=\"amrHoFrInHoThrDlRxQual\">" + aBts.amrHoFrInHoThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrHoFrSupReuBadCiThr\">" + aBts.amrHoFrSupReuBadCiThr + "</p>\n";
                aBtsString += "<p name=\"amrHoFrSupReuGoodCiThr\">" + aBts.amrHoFrSupReuGoodCiThr + "</p>\n";
                aBtsString += "<p name=\"amrHoFrThrDlRxQual\">" + aBts.amrHoFrThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrHoFrThrUlRxQual\">" + aBts.amrHoFrThrUlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrHoHrInHoThrDlRxQual\">" + aBts.amrHoHrInHoThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrHoHrSupReuBadCiThr\">" + aBts.amrHoHrSupReuBadCiThr + "</p>\n";
                aBtsString += "<p name=\"amrHoHrSupReuGoodCiThr\">" + aBts.amrHoHrSupReuGoodCiThr + "</p>\n";
                aBtsString += "<p name=\"amrHoHrThrDlRxQual\">" + aBts.amrHoHrThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrHoHrThrUlRxQual\">" + aBts.amrHoHrThrUlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocFrPcLThrDlRxQual\">" + aBts.amrPocFrPcLThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocFrPcLThrUlRxQual\">" + aBts.amrPocFrPcLThrUlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocFrPcUThrDlRxQual\">" + aBts.amrPocFrPcUThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocFrPcUThrUlRxQual\">" + aBts.amrPocFrPcUThrUlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocHrPcLThrDlRxQual\">" + aBts.amrPocHrPcLThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocHrPcLThrUlRxQual\">" + aBts.amrPocHrPcLThrUlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocHrPcUThrDlRxQual\">" + aBts.amrPocHrPcUThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocHrPcUThrUlRxQual\">" + aBts.amrPocHrPcUThrUlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrSegLoadDepTchRateLower\">" + aBts.amrSegLoadDepTchRateLower + "</p>\n";
                aBtsString += "<p name=\"amrSegLoadDepTchRateUpper\">" + aBts.amrSegLoadDepTchRateUpper + "</p>\n";
                aBtsString += "<p name=\"bsIdentityCodeBCC\">" + aBts.bsIdentityCodeBCC + "</p>\n";
                aBtsString += "<p name=\"bsIdentityCodeNCC\">" + aBts.bsIdentityCodeNCC + "</p>\n";
                aBtsString += "<p name=\"btsLoadInSeg\">" + aBts.btsLoadInSeg + "</p>\n";
                aBtsString += "<p name=\"btsLoadThreshold\">" + aBts.btsLoadThreshold + "</p>\n";
                aBtsString += "<p name=\"btsMeasAver\">" + aBts.btsMeasAver + "</p>\n";
                aBtsString += "<p name=\"btsSpLoadDepTchRateLower\">" + aBts.btsSpLoadDepTchRateLower + "</p>\n";
                aBtsString += "<p name=\"btsSpLoadDepTchRateUpper\">" + aBts.btsSpLoadDepTchRateUpper + "</p>\n";
                aBtsString += "<p name=\"callReestablishmentAllowed\">" + aBts.callReestablishmentAllowed + "</p>\n";
                aBtsString += "<p name=\"cellBarQualify\">" + aBts.cellBarQualify + "</p>\n";
                aBtsString += "<p name=\"cellBarred\">" + aBts.cellBarred + "</p>\n";
                aBtsString += "<p name=\"cellId\">" + aBts.cellId + "</p>\n";
                aBtsString += "<p name=\"cellLoadForChannelSearch\">" + aBts.cellLoadForChannelSearch + "</p>\n";
                aBtsString += "<p name=\"cellNumberInBtsHw\">" + aBts.cellNumberInBtsHw + "</p>\n";
                aBtsString += "<p name=\"cellReselectHysteresis\">" + aBts.cellReselectHysteresis + "</p>\n";
                aBtsString += "<p name=\"cellReselectOffset\">" + aBts.cellReselectOffset + "</p>\n";
                aBtsString += "<p name=\"cellReselectParamInd\">" + aBts.cellReselectParamInd + "</p>\n";
                aBtsString += "<p name=\"cnThreshold\">" + aBts.cnThreshold + "</p>\n";
                aBtsString += "<p name=\"cs3Cs4Enabled\">" + aBts.cs3Cs4Enabled + "</p>\n";
                aBtsString += "<p name=\"dedicatedGPRScapacity\">" + aBts.dedicatedGPRScapacity + "</p>\n";
                aBtsString += "<p name=\"defaultGPRScapacity\">" + aBts.defaultGPRScapacity + "</p>\n";
                aBtsString += "<p name=\"directGPRSAccessBts\">" + aBts.directGPRSAccessBts + "</p>\n";
                aBtsString += "<p name=\"diversityUsed\">" + aBts.diversityUsed + "</p>\n";
                aBtsString += "<p name=\"dlNoiseLevel\">" + aBts.dlNoiseLevel + "</p>\n";
                aBtsString += "<p name=\"downgradeGuardTimeHSCSD\">" + aBts.downgradeGuardTimeHSCSD + "</p>\n";
                aBtsString += "<p name=\"drInUse\">" + aBts.drInUse + "</p>\n";
                aBtsString += "<p name=\"drMethod\">" + aBts.drMethod + "</p>\n";
                aBtsString += "<p name=\"dtxMode\">" + aBts.dtxMode + "</p>\n";
                aBtsString += "<p name=\"earlySendingIndication\">" + aBts.earlySendingIndication + "</p>\n";
                aBtsString += "<p name=\"egprsEnabled\">" + aBts.egprsEnabled + "</p>\n";
                aBtsString += "<p name=\"egprsInitMcsAckMode\">" + aBts.egprsInitMcsAckMode + "</p>\n";
                aBtsString += "<p name=\"egprsInitMcsUnAckMode\">" + aBts.egprsInitMcsUnAckMode + "</p>\n";
                aBtsString += "<p name=\"egprsLinkAdaptEnabled\">" + aBts.egprsLinkAdaptEnabled + "</p>\n";
                aBtsString += "<p name=\"egprsMaxBlerAckMode\">" + aBts.egprsMaxBlerAckMode + "</p>\n";
                aBtsString += "<p name=\"egprsMaxBlerUnAckMode\">" + aBts.egprsMaxBlerUnAckMode + "</p>\n";
                aBtsString += "<p name=\"egprsMeanBepOffset8psk\">" + aBts.egprsMeanBepOffset8psk + "</p>\n";
                aBtsString += "<p name=\"egprsMeanBepOffsetGmsk\">" + aBts.egprsMeanBepOffsetGmsk + "</p>\n";
                aBtsString += "<p name=\"emergencyCallRestricted\">" + aBts.emergencyCallRestricted + "</p>\n";
                aBtsString += "<p name=\"fddQMin\">" + aBts.fddQMin + "</p>\n";
                aBtsString += "<p name=\"fddQOffset\">" + aBts.fddQOffset + "</p>\n";
                aBtsString += "<p name=\"forcedHrCiAverPeriod\">" + aBts.forcedHrCiAverPeriod + "</p>\n";
                aBtsString += "<p name=\"forcedHrModeHysteresis\">" + aBts.forcedHrModeHysteresis + "</p>\n";
                aBtsString += "<p name=\"gprsEnabled\">" + aBts.gprsEnabled + "</p>\n";
                aBtsString += "<p name=\"gprsMsTxpwrMaxCCH\">" + aBts.gprsMsTxpwrMaxCCH + "</p>\n";
                aBtsString += "<p name=\"gprsNonBCCHRxlevLower\">" + aBts.gprsNonBCCHRxlevLower + "</p>\n";
                aBtsString += "<p name=\"gprsNonBCCHRxlevUpper\">" + aBts.gprsNonBCCHRxlevUpper + "</p>\n";
                aBtsString += "<p name=\"gprsRxlevAccessMin\">" + aBts.gprsRxlevAccessMin + "</p>\n";
                aBtsString += "<p name=\"hcsPriorityClass\">" + aBts.hcsPriorityClass + "</p>\n";
                aBtsString += "<p name=\"hcsThreshold\">" + aBts.hcsThreshold + "</p>\n";
                aBtsString += "<p name=\"hoppingMode\">" + aBts.hoppingMode + "</p>\n";
                aBtsString += "<p name=\"hoppingSequenceNumber1\">" + aBts.hoppingSequenceNumber1 + "</p>\n";
                aBtsString += "<p name=\"hoppingSequenceNumber2\">" + aBts.hoppingSequenceNumber2 + "</p>\n";
                aBtsString += "<p name=\"inactEndTimeHour\">" + aBts.inactEndTimeHour + "</p>\n";
                aBtsString += "<p name=\"inactEndTimeMinute\">" + aBts.inactEndTimeMinute + "</p>\n";
                aBtsString += "<p name=\"inactStartTimeHour\">" + aBts.inactStartTimeHour + "</p>\n";
                aBtsString += "<p name=\"inactStartTimeMinute\">" + aBts.inactStartTimeMinute + "</p>\n";
                aBtsString += "<p name=\"inactWeekDays\">" + aBts.inactWeekDays + "</p>\n";
                aBtsString += "<p name=\"csAckDl\">" + aBts.csAckDl + "</p>\n";
                aBtsString += "<p name=\"csAckUl\">" + aBts.csAckUl + "</p>\n";
                aBtsString += "<p name=\"csUnackDl\">" + aBts.csUnackDl + "</p>\n";
                aBtsString += "<p name=\"csUnackUl\">" + aBts.csUnackUl + "</p>\n";
                aBtsString += "<p name=\"interferenceAveragingProcessAverPeriod\">" + aBts.interferenceAveragingProcessAverPeriod + "</p>\n";
                aBtsString += "<p name=\"interferenceAveragingProcessBoundary0\">" + aBts.interferenceAveragingProcessBoundary0 + "</p>\n";
                aBtsString += "<p name=\"interferenceAveragingProcessBoundary1\">" + aBts.interferenceAveragingProcessBoundary1 + "</p>\n";
                aBtsString += "<p name=\"interferenceAveragingProcessBoundary2\">" + aBts.interferenceAveragingProcessBoundary2 + "</p>\n";
                aBtsString += "<p name=\"interferenceAveragingProcessBoundary3\">" + aBts.interferenceAveragingProcessBoundary3 + "</p>\n";
                aBtsString += "<p name=\"interferenceAveragingProcessBoundary4\">" + aBts.interferenceAveragingProcessBoundary4 + "</p>\n";
                aBtsString += "<p name=\"interferenceAveragingProcessBoundary5\">" + aBts.interferenceAveragingProcessBoundary5 + "</p>\n";
                aBtsString += "<p name=\"limForTriggeringOscDhrMultiplexing\">" + aBts.limForTriggeringOscDhrMultiplexing + "</p>\n";
                aBtsString += "<p name=\"locationAreaIdLAC\">" + aBts.locationAreaIdLAC + "</p>\n";
                aBtsString += "<p name=\"locationAreaIdMCC\">" + aBts.locationAreaIdMCC + "</p>\n";
                aBtsString += "<p name=\"locationAreaIdMNC\">" + aBts.locationAreaIdMNC + "</p>\n";
                aBtsString += "<p name=\"lowerLimitCellLoadHSCSD\">" + aBts.lowerLimitCellLoadHSCSD + "</p>\n";
                aBtsString += "<p name=\"maioOffset\">" + aBts.maioOffset + "</p>\n";
                aBtsString += "<p name=\"maioStep\">" + aBts.maioStep + "</p>\n";
                aBtsString += "<p name=\"masterBcf\">" + aBts.masterBcf + "</p>\n";
                aBtsString += "<p name=\"maxGPRSCapacity\">" + aBts.maxGPRSCapacity + "</p>\n";
                aBtsString += "<p name=\"maxNumberOfRepetition\">" + aBts.maxNumberOfRepetition + "</p>\n";
                aBtsString += "<p name=\"maxNumberRetransmission\">" + aBts.maxNumberRetransmission + "</p>\n";
                aBtsString += "<p name=\"maxQueueLength\">" + aBts.maxQueueLength + "</p>\n";
                aBtsString += "<p name=\"maxTimeLimitDirectedRetry\">" + aBts.maxTimeLimitDirectedRetry + "</p>\n";
                aBtsString += "<p name=\"measListUsedDuringMeas\">" + aBts.measListUsedDuringMeas + "</p>\n";
                aBtsString += "<p name=\"minExhaustHSCSD\">" + aBts.minExhaustHSCSD + "</p>\n";
                aBtsString += "<p name=\"minHSCSDcapacityCell\">" + aBts.minHSCSDcapacityCell + "</p>\n";
                aBtsString += "<p name=\"minTimeLimitDirectedRetry\">" + aBts.minTimeLimitDirectedRetry + "</p>\n";
                aBtsString += "<p name=\"msMaxDistInCallSetup\">" + aBts.msMaxDistInCallSetup + "</p>\n";
                aBtsString += "<p name=\"msPriorityUsedInQueueing\">" + aBts.msPriorityUsedInQueueing + "</p>\n";
                aBtsString += "<p name=\"msTxPwrMaxCCH\">" + aBts.msTxPwrMaxCCH + "</p>\n";
                aBtsString += "<p name=\"msTxPwrMaxGSM\">" + aBts.msTxPwrMaxGSM + "</p>\n";
                aBtsString += "<p name=\"multiBandCell\">" + aBts.multiBandCell + "</p>\n";
                aBtsString += "<p name=\"multiBandCellReporting\">" + aBts.multiBandCellReporting + "</p>\n";
                aBtsString += "<p name=\"nbrOfSlotsSpreadTrans\">" + aBts.nbrOfSlotsSpreadTrans + "</p>\n";
                aBtsString += "<p name=\"newEstabCausesSupport\">" + aBts.newEstabCausesSupport + "</p>\n";
                aBtsString += "<p name=\"noOfBlocksForAccessGrant\">" + aBts.noOfBlocksForAccessGrant + "</p>\n";
                aBtsString += "<p name=\"noOfMFramesBetweenPaging\">" + aBts.noOfMFramesBetweenPaging + "</p>\n";
                aBtsString += "<p name=\"nonBCCHLayerOffset\">" + aBts.nonBCCHLayerOffset + "</p>\n";
                aBtsString += "<p name=\"nwName\">" + aBts.nwName + "</p>\n";
                aBtsString += "<p name=\"pcuCsHopping\">" + aBts.pcuCsHopping + "</p>\n";
                aBtsString += "<p name=\"pcuCsNonHopping\">" + aBts.pcuCsNonHopping + "</p>\n";
                aBtsString += "<p name=\"pcuDlBlerCpHopping\">" + aBts.pcuDlBlerCpHopping + "</p>\n";
                aBtsString += "<p name=\"pcuDlBlerCpNonHop\">" + aBts.pcuDlBlerCpNonHop + "</p>\n";
                aBtsString += "<p name=\"pcuDlLaRiskLevel\">" + aBts.pcuDlLaRiskLevel + "</p>\n";
                aBtsString += "<p name=\"pcuUlBlerCpHopping\">" + aBts.pcuUlBlerCpHopping + "</p>\n";
                aBtsString += "<p name=\"pcuUlBlerCpNonHop\">" + aBts.pcuUlBlerCpNonHop + "</p>\n";
                aBtsString += "<p name=\"pcuUlLaRiskLevel\">" + aBts.pcuUlLaRiskLevel + "</p>\n";
                aBtsString += "<p name=\"penaltyTime\">" + aBts.penaltyTime + "</p>\n";
                aBtsString += "<p name=\"powerOffset\">" + aBts.powerOffset + "</p>\n";
                aBtsString += "<p name=\"preferBCCHfreqGPRS2\">" + aBts.preferBCCHfreqGPRS2 + "</p>\n";
                aBtsString += "<p name=\"qSearchI\">" + aBts.qSearchI + "</p>\n";
                aBtsString += "<p name=\"qSearchP\">" + aBts.qSearchP + "</p>\n";
                aBtsString += "<p name=\"queuePriorityNonUrgentHo\">" + aBts.queuePriorityNonUrgentHo + "</p>\n";
                aBtsString += "<p name=\"queuePriorityUsed\">" + aBts.queuePriorityUsed + "</p>\n";
                aBtsString += "<p name=\"queueingPriorityCall\">" + aBts.queueingPriorityCall + "</p>\n";
                aBtsString += "<p name=\"queueingPriorityHandover\">" + aBts.queueingPriorityHandover + "</p>\n";
                aBtsString += "<p name=\"raReselectHysteresis\">" + aBts.raReselectHysteresis + "</p>\n";
                aBtsString += "<p name=\"rac\">" + aBts.rac + "</p>\n";
                aBtsString += "<p name=\"radioLinkTimeout\">" + aBts.radioLinkTimeout + "</p>\n";
                aBtsString += "<p name=\"radioLinkTimeoutAmr\">" + aBts.radioLinkTimeoutAmr + "</p>\n";
                aBtsString += "<p name=\"radioLinkTimeoutAmrHrUlIncreaseStep\">" + aBts.radioLinkTimeoutAmrHrUlIncreaseStep + "</p>\n";
                aBtsString += "<p name=\"radioLinkTimeoutAmrUlIncreaseStep\">" + aBts.radioLinkTimeoutAmrUlIncreaseStep + "</p>\n";
                aBtsString += "<p name=\"radioLinkTimeoutUlIncreaseStep\">" + aBts.radioLinkTimeoutUlIncreaseStep + "</p>\n";
                aBtsString += "<p name=\"rxLevAccessMin\">" + aBts.rxLevAccessMin + "</p>\n";
                aBtsString += "<p name=\"scaleOrd\">" + aBts.scaleOrd + "</p>\n";
                aBtsString += "<p name=\"segmentId\">" + aBts.segmentId + "</p>\n";
                aBtsString += "<p name=\"segmentName\">" + aBts.segmentName + "</p>\n";
                aBtsString += "<p name=\"smsCbUsed\">" + aBts.smsCbUsed + "</p>\n";
                aBtsString += "<p name=\"stircEnabled\">" + aBts.stircEnabled + "</p>\n";
                aBtsString += "<p name=\"tchRateIntraCellHo\">" + aBts.tchRateIntraCellHo + "</p>\n";
                aBtsString += "<p name=\"temporaryOffset\">" + aBts.temporaryOffset + "</p>\n";
                aBtsString += "<p name=\"throughputFactor_cs1cs4dlcs\">" + aBts.throughputFactor_cs1cs4dlcs + "</p>\n";
                aBtsString += "<p name=\"throughputFactor_cs1cs4ulcs\">" + aBts.throughputFactor_cs1cs4ulcs + "</p>\n";
                aBtsString += "<p name=\"throughputFactor_mcs1mcs4ulcs\">" + aBts.throughputFactor_mcs1mcs4ulcs + "</p>\n";
                aBtsString += "<p name=\"throughputFactor_mcs1mcs9dlcs\">" + aBts.throughputFactor_mcs1mcs9dlcs + "</p>\n";
                aBtsString += "<p name=\"throughputFactor_mcs1mcs9ulcs\">" + aBts.throughputFactor_mcs1mcs9ulcs + "</p>\n";
                aBtsString += "<p name=\"timeLimitCall\">" + aBts.timeLimitCall + "</p>\n";
                aBtsString += "<p name=\"timeLimitHandover\">" + aBts.timeLimitHandover + "</p>\n";
                aBtsString += "<p name=\"timerPeriodicUpdateMs\">" + aBts.timerPeriodicUpdateMs + "</p>\n";
                aBtsString += "<p name=\"trxPriorityInTchAlloc\">" + aBts.trxPriorityInTchAlloc + "</p>\n";
                aBtsString += "<p name=\"ulNoiseLevel\">" + aBts.ulNoiseLevel + "</p>\n";
                aBtsString += "<p name=\"upgradeGainHSCSD\">" + aBts.upgradeGainHSCSD + "</p>\n";
                aBtsString += "<p name=\"upgradeGuardTimeHSCSD\">" + aBts.upgradeGuardTimeHSCSD + "</p>\n";
                aBtsString += "<p name=\"upperLimitCellLoadHSCSD\">" + aBts.upperLimitCellLoadHSCSD + "</p>\n";
                aBtsString += "<p name=\"upperLimitRegularLoadHSCSD\">" + aBts.upperLimitRegularLoadHSCSD + "</p>\n";
                aBtsString += "<p name=\"usedMobileAllocation\">" + aBts.usedMobileAllocation + "</p>\n";

            } else {
                aBtsString += "<managedObject class=\"BTS\" distName=\"" + aBts.PLMNS + "\" operation=\"create\">\n";
                aBtsString += "<p name=\"frequencyBandInUse\">" + aBts.frequencyBandInUse + "</p>\n";
                aBtsString += "<p name=\"minMsTxPower\">" + aBts.minMsTxPower + "</p>\n";
                aBtsString += "<p name=\"name\">" + aBts.name + "</p>\n";
                aBtsString += "<p name=\"adaptiveLaAlgorithm\">" + aBts.adaptiveLaAlgorithm + "</p>\n";
                aBtsString += "<p name=\"adminState\">" + aBts.adminState + "</p>\n";
                aBtsString += "<p name=\"amrConfFrCodecModeSet\">" + aBts.amrConfFrCodecModeSet + "</p>\n";
                aBtsString += "<p name=\"amrConfFrDlThreshold1\">" + aBts.amrConfFrDlThreshold1 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrDlThreshold2\">" + aBts.amrConfFrDlThreshold2 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrDlThreshold3\">" + aBts.amrConfFrDlThreshold3 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrHysteresis1\">" + aBts.amrConfFrHysteresis1 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrHysteresis2\">" + aBts.amrConfFrHysteresis2 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrHysteresis3\">" + aBts.amrConfFrHysteresis3 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrInitCodecMode\">" + aBts.amrConfFrInitCodecMode + "</p>\n";
                aBtsString += "<p name=\"amrConfFrStartMode\">" + aBts.amrConfFrStartMode + "</p>\n";
                aBtsString += "<p name=\"amrConfFrUlThreshold1\">" + aBts.amrConfFrUlThreshold1 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrUlThreshold2\">" + aBts.amrConfFrUlThreshold2 + "</p>\n";
                aBtsString += "<p name=\"amrConfFrUlThreshold3\">" + aBts.amrConfFrUlThreshold3 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrCodecModeSet\">" + aBts.amrConfHrCodecModeSet + "</p>\n";
                aBtsString += "<p name=\"amrConfHrDlThreshold1\">" + aBts.amrConfHrDlThreshold1 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrDlThreshold2\">" + aBts.amrConfHrDlThreshold2 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrDlThreshold3\">" + aBts.amrConfHrDlThreshold3 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrHysteresis1\">" + aBts.amrConfHrHysteresis1 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrHysteresis2\">" + aBts.amrConfHrHysteresis2 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrHysteresis3\">" + aBts.amrConfHrHysteresis3 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrInitCodecMode\">" + aBts.amrConfHrInitCodecMode + "</p>\n";
                aBtsString += "<p name=\"amrConfHrStartMode\">" + aBts.amrConfHrStartMode + "</p>\n";
                aBtsString += "<p name=\"amrConfHrUlThreshold1\">" + aBts.amrConfHrUlThreshold1 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrUlThreshold2\">" + aBts.amrConfHrUlThreshold2 + "</p>\n";
                aBtsString += "<p name=\"amrConfHrUlThreshold3\">" + aBts.amrConfHrUlThreshold3 + "</p>\n";
                aBtsString += "<p name=\"amrHoFrInHoThrDlRxQual\">" + aBts.amrHoFrInHoThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrHoFrSupReuBadCiThr\">" + aBts.amrHoFrSupReuBadCiThr + "</p>\n";
                aBtsString += "<p name=\"amrHoFrSupReuGoodCiThr\">" + aBts.amrHoFrSupReuGoodCiThr + "</p>\n";
                aBtsString += "<p name=\"amrHoFrThrDlRxQual\">" + aBts.amrHoFrThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrHoFrThrUlRxQual\">" + aBts.amrHoFrThrUlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrHoHrInHoThrDlRxQual\">" + aBts.amrHoHrInHoThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrHoHrSupReuBadCiThr\">" + aBts.amrHoHrSupReuBadCiThr + "</p>\n";
                aBtsString += "<p name=\"amrHoHrSupReuGoodCiThr\">" + aBts.amrHoHrSupReuGoodCiThr + "</p>\n";
                aBtsString += "<p name=\"amrHoHrThrDlRxQual\">" + aBts.amrHoHrThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrHoHrThrUlRxQual\">" + aBts.amrHoHrThrUlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocFrPcLThrDlRxQual\">" + aBts.amrPocFrPcLThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocFrPcLThrUlRxQual\">" + aBts.amrPocFrPcLThrUlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocFrPcUThrDlRxQual\">" + aBts.amrPocFrPcUThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocFrPcUThrUlRxQual\">" + aBts.amrPocFrPcUThrUlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocHrPcLThrDlRxQual\">" + aBts.amrPocHrPcLThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocHrPcLThrUlRxQual\">" + aBts.amrPocHrPcLThrUlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocHrPcUThrDlRxQual\">" + aBts.amrPocHrPcUThrDlRxQual + "</p>\n";
                aBtsString += "<p name=\"amrPocHrPcUThrUlRxQual\">" + aBts.amrPocHrPcUThrUlRxQual + "</p>\n";
                aBtsString += "<p name=\"bsIdentityCodeBCC\">" + aBts.bsIdentityCodeBCC + "</p>\n";
                aBtsString += "<p name=\"bsIdentityCodeNCC\">" + aBts.bsIdentityCodeNCC + "</p>\n";
                aBtsString += "<p name=\"btsLoadInSeg\">" + aBts.btsLoadInSeg + "</p>\n";
                aBtsString += "<p name=\"cellId\">" + aBts.cellId + "</p>\n";
                aBtsString += "<p name=\"cellNumberInBtsHw\">" + aBts.cellNumberInBtsHw + "</p>\n";
                aBtsString += "<p name=\"cnThreshold\">" + aBts.cnThreshold + "</p>\n";
                aBtsString += "<p name=\"cs3Cs4Enabled\">" + aBts.cs3Cs4Enabled + "</p>\n";
                aBtsString += "<p name=\"dedicatedGPRScapacity\">" + aBts.dedicatedGPRScapacity + "</p>\n";
                aBtsString += "<p name=\"defaultGPRScapacity\">" + aBts.defaultGPRScapacity + "</p>\n";
                aBtsString += "<p name=\"diversityUsed\">" + aBts.diversityUsed + "</p>\n";
                aBtsString += "<p name=\"dlNoiseLevel\">" + aBts.dlNoiseLevel + "</p>\n";
                aBtsString += "<p name=\"egprsEnabled\">" + aBts.egprsEnabled + "</p>\n";
                aBtsString += "<p name=\"forcedHrCiAverPeriod\">" + aBts.forcedHrCiAverPeriod + "</p>\n";
                aBtsString += "<p name=\"forcedHrModeHysteresis\">" + aBts.forcedHrModeHysteresis + "</p>\n";
                aBtsString += "<p name=\"gprsNonBCCHRxlevLower\">" + aBts.gprsNonBCCHRxlevLower + "</p>\n";
                aBtsString += "<p name=\"gprsNonBCCHRxlevUpper\">" + aBts.gprsNonBCCHRxlevUpper + "</p>\n";
                aBtsString += "<p name=\"hoppingMode\">" + aBts.hoppingMode + "</p>\n";
                aBtsString += "<p name=\"hoppingSequenceNumber1\">" + aBts.hoppingSequenceNumber1 + "</p>\n";
                aBtsString += "<p name=\"hoppingSequenceNumber2\">" + aBts.hoppingSequenceNumber2 + "</p>\n";
                aBtsString += "<p name=\"inactEndTimeHour\">" + aBts.inactEndTimeHour + "</p>\n";
                aBtsString += "<p name=\"inactEndTimeMinute\">" + aBts.inactEndTimeMinute + "</p>\n";
                aBtsString += "<p name=\"inactStartTimeHour\">" + aBts.inactStartTimeHour + "</p>\n";
                aBtsString += "<p name=\"inactStartTimeMinute\">" + aBts.inactStartTimeMinute + "</p>\n";
                aBtsString += "<p name=\"inactWeekDays\">" + aBts.inactWeekDays + "</p>\n";
                aBtsString += "<p name=\"csAckDl\">" + aBts.csAckDl + "</p>\n";
                aBtsString += "<p name=\"csAckUl\">" + aBts.csAckUl + "</p>\n";
                aBtsString += "<p name=\"csUnackDl\">" + aBts.csUnackDl + "</p>\n";
                aBtsString += "<p name=\"csUnackUl\">" + aBts.csUnackUl + "</p>\n";
                aBtsString += "<p name=\"interferenceAveragingProcessBoundary0\">" + aBts.interferenceAveragingProcessBoundary0 + "</p>\n";
                aBtsString += "<p name=\"interferenceAveragingProcessBoundary1\">" + aBts.interferenceAveragingProcessBoundary1 + "</p>\n";
                aBtsString += "<p name=\"interferenceAveragingProcessBoundary2\">" + aBts.interferenceAveragingProcessBoundary2 + "</p>\n";
                aBtsString += "<p name=\"interferenceAveragingProcessBoundary3\">" + aBts.interferenceAveragingProcessBoundary3 + "</p>\n";
                aBtsString += "<p name=\"interferenceAveragingProcessBoundary4\">" + aBts.interferenceAveragingProcessBoundary4 + "</p>\n";
                aBtsString += "<p name=\"interferenceAveragingProcessBoundary5\">" + aBts.interferenceAveragingProcessBoundary5 + "</p>\n";
                aBtsString += "<p name=\"limForTriggeringOscDhrMultiplexing\">" + aBts.limForTriggeringOscDhrMultiplexing + "</p>\n";
                aBtsString += "<p name=\"locationAreaIdLAC\">" + aBts.locationAreaIdLAC + "</p>\n";
                aBtsString += "<p name=\"locationAreaIdMCC\">" + aBts.locationAreaIdMCC + "</p>\n";
                aBtsString += "<p name=\"locationAreaIdMNC\">" + aBts.locationAreaIdMNC + "</p>\n";
                aBtsString += "<p name=\"lowerLimitCellLoadHSCSD\">" + aBts.lowerLimitCellLoadHSCSD + "</p>\n";
                aBtsString += "<p name=\"maioOffset\">" + aBts.maioOffset + "</p>\n";
                aBtsString += "<p name=\"maioStep\">" + aBts.maioStep + "</p>\n";
                aBtsString += "<p name=\"masterBcf\">" + aBts.masterBcf + "</p>\n";
                aBtsString += "<p name=\"maxGPRSCapacity\">" + aBts.maxGPRSCapacity + "</p>\n";
                aBtsString += "<p name=\"minHSCSDcapacityCell\">" + aBts.minHSCSDcapacityCell + "</p>\n";
                aBtsString += "<p name=\"nonBCCHLayerOffset\">" + aBts.nonBCCHLayerOffset + "</p>\n";
                aBtsString += "<p name=\"nwName\">" + aBts.nwName + "</p>\n";
                aBtsString += "<p name=\"segmentId\">" + aBts.segmentId + "</p>\n";
                aBtsString += "<p name=\"stircEnabled\">" + aBts.stircEnabled + "</p>\n";
                aBtsString += "<p name=\"throughputFactor_cs1cs4dlcs\">" + aBts.throughputFactor_cs1cs4dlcs + "</p>\n";
                aBtsString += "<p name=\"throughputFactor_cs1cs4ulcs\">" + aBts.throughputFactor_cs1cs4ulcs + "</p>\n";
                aBtsString += "<p name=\"throughputFactor_mcs1mcs4ulcs\">" + aBts.throughputFactor_mcs1mcs4ulcs + "</p>\n";
                aBtsString += "<p name=\"throughputFactor_mcs1mcs9dlcs\">" + aBts.throughputFactor_mcs1mcs9dlcs + "</p>\n";
                aBtsString += "<p name=\"throughputFactor_mcs1mcs9ulcs\">" + aBts.throughputFactor_mcs1mcs9ulcs + "</p>\n";
                aBtsString += "<p name=\"ulNoiseLevel\">" + aBts.ulNoiseLevel + "</p>\n";
                aBtsString += "<p name=\"upperLimitCellLoadHSCSD\">" + aBts.upperLimitCellLoadHSCSD + "</p>\n";
                aBtsString += "<p name=\"upperLimitRegularLoadHSCSD\">" + aBts.upperLimitRegularLoadHSCSD + "</p>\n";
                aBtsString += "<p name=\"usedMobileAllocation\">" + aBts.usedMobileAllocation + "</p>\n";

            }

            aBtsString += "</managedobject>\n";
            allBtsString += aBtsString + "\n";

        }

        return allBtsString;
    }

    public String GetADCEString(DBConnection aDBConnection, String Bsc, String Bcf) throws SQLException {
        ResultSet aResultSet1 = aDBConnection.SelectQueryResultSet("Select * from adce where bsc=" + Bsc + " and bcf=" + Bcf);
        ResultSetMetaData adceColumns = (ResultSetMetaData) aResultSet1.getMetaData();
        int columnCount = adceColumns.getColumnCount();

        List<String> ADCEColumnNameList = new ArrayList<String>();
        String AdceString = "";
        for (int i = 1; i < columnCount + 1; i++) {
            ADCEColumnNameList.add(adceColumns.getColumnName(i));
        }
        while (aResultSet1.next() || aResultSet1.wasNull()) {
            for (int i = 0; i < columnCount - 1; i++) {

                if (i == 0) {
                    AdceString += "<managedObject class=\"ADCE\" distName=\"" + aResultSet1.getString(ADCEColumnNameList.get(i)) + "\" operation=\"create\">\n";
                }
                if (i > 4) {
                    try {
                        if (!(aResultSet1.getString(ADCEColumnNameList.get(i)).isEmpty())) {
                            AdceString += "<p name=\"" + ADCEColumnNameList.get(i) + "\">" + aResultSet1.getString(ADCEColumnNameList.get(i)) + "</p>\n";
                        }
                    } catch (Exception e) {

                    }
                }
                if (i == columnCount - 2) {
                    AdceString += "</managedObject>\n";
                }
            }
        }

        return AdceString;
    }

    public String GetADJWString(DBConnection aDBConnection, String Bsc, String Bcf) throws SQLException {
        ResultSet aResultSet1 = aDBConnection.SelectQueryResultSet("Select * from ADJW where bsc=" + Bsc + " and bcf=" + Bcf);
        ResultSetMetaData ADJWColumns = (ResultSetMetaData) aResultSet1.getMetaData();
        int columnCount = ADJWColumns.getColumnCount();

        List<String> ADJWColumnNameList = new ArrayList<String>();
        String ADJWString = "";
        for (int i = 1; i < columnCount + 1; i++) {
            ADJWColumnNameList.add(ADJWColumns.getColumnName(i));
        }
        while (aResultSet1.next() || aResultSet1.wasNull()) {
            for (int i = 0; i < columnCount - 1; i++) {

                if (i == 0) {
                    ADJWString += "<managedObject class=\"ADJW\" distName=\"" + aResultSet1.getString(ADJWColumnNameList.get(i)) + "\" operation=\"create\">\n";
                }
                if (i > 4) {
                    try {
                        if (!(aResultSet1.getString(ADJWColumnNameList.get(i)).isEmpty())) {
                            ADJWString += "<p name=\"" + ADJWColumnNameList.get(i) + "\">" + aResultSet1.getString(ADJWColumnNameList.get(i)) + "</p>\n";
                        }
                    } catch (Exception e) {

                    }
                }

                if (i == columnCount - 2) {
                    ADJWString += "</managedObject>\n";
                }
            }
        }
        return ADJWString;
    }

    public String GetLapdString(List<LAPD> lapdBCFAllinfoList, String Bsc, String Bcf) {
        String allLapdString = "";

        for (LAPD aLapd : lapdBCFAllinfoList) {
            if (aLapd.getBCF().equals(Bcf) && aLapd.getBsc().equals(Bsc) && !aLapd.getSapi().equals("62")) {
                String aLapdString = "<managedObject class=\"LAPD\" distName=\"" + aLapd.getPLMN() + "\" operation=\"create\">\n";
                aLapdString += "<p name=\"abisSigChannelSubSlot\">" + aLapd.getAbisSigChannelSubSlot() + "</p>\n";
                aLapdString += "<p name=\"bitRate\">" + aLapd.getBitRate() + "</p>\n";
                aLapdString += "<p name=\"abisSigChannelTimeSlotPcm\">" + aLapd.getAbisSigChannelTimeSlotPcm() + "</p>\n";
                aLapdString += "<p name=\"abisSigChannelTimeSlotTsl\">" + aLapd.getAbisSigChannelTimeSlotTsl() + "</p>\n";
                aLapdString += "<p name=\"adminState\">" + aLapd.getAdminState() + "</p>\n";
                aLapdString += "<p name=\"dChannelType\">" + aLapd.getdChannelType() + "</p>\n";
                aLapdString += "<p name=\"name\">" + aLapd.getName() + "</p>\n";
                aLapdString += "<p name=\"parameterSetNumber\">" + aLapd.getParameterSetNumber() + "</p>\n";
                aLapdString += "<p name=\"sapi\">" + aLapd.getSapi() + "</p>\n";
                aLapdString += "<p name=\"tei\">" + aLapd.getTei() + "</p>\n";
                aLapdString += "</managedobject>\n";
                allLapdString += aLapdString + "\n";
            }
        }

        return allLapdString;
    }

    public String WriteXml(String XmlString, String BcfName) throws FileNotFoundException, IOException {
        PrintWriter out = new PrintWriter("filename.txt");
        BufferedWriter writer = null;
        writer = new BufferedWriter(new FileWriter("D:\\input\\" + BcfName + ".xml"));
        writer.write(XmlString);
        writer.close();
        return BcfName + ".xml  Writen To Disk";
    }

    public static String CurrDate() {
        Date d = new Date();

        String p = d.toString();

        String month = p.substring(4, 7);
        String day = p.substring(8, 10);
        String year = p.substring(26, 28);

        if (month.equals("Jan")) {
            month = "01";
        } else if (month.equals("Feb")) {
            month = "02";
        } else if (month.equals("Mar")) {
            month = "03";
        } else if (month.equals("Apr")) {
            month = "04";
        } else if (month.equals("May")) {
            month = "05";
        } else if (month.equals("Jun")) {
            month = "06";
        } else if (month.equals("Jul")) {
            month = "07";
        } else if (month.equals("Aug")) {
            month = "08";
        } else if (month.equals("Sep")) {
            month = "09";
        } else if (month.equals("Oct")) {
            month = "10";
        } else if (month.equals("Nov")) {
            month = "11";
        } else if (month.equals("Dec")) {
            month = "12";
        }

        String date = day + month + year;

        System.out.println("time is " + p);
        return date;
    }

}

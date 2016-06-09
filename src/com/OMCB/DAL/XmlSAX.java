/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.OMCB.DAL;

import com.OMCB.Model.BCF;
import com.OMCB.Model.BCFVerifyer;
import com.OMCB.Model.BcfCsdap;
import com.OMCB.Model.ExternalAlarms;
import java.io.CharArrayWriter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author atchowdhury
 */
public class XmlSAX  extends DefaultHandler  {

    public XmlSAX(String dname){
     distName=dname;
    }
    String distName;
 String currentElement = null;
 List<BCF> aBCFList=new ArrayList<BCF>();
 List<String> csdapList=new ArrayList<String>();
    List<ExternalAlarms> aAlarmList=new ArrayList<ExternalAlarms>();
    ExternalAlarms aAlarm;
 public boolean isBCF=false;
 HashMap elementValues = new HashMap();
   BCF aBCF;
   BCFVerifyer aBCFVerifyer;
    private CharArrayWriter contents = new CharArrayWriter();

    public BcfCsdap GetBCF(){
        BcfCsdap aBcfCsdap=new BcfCsdap();
        aBcfCsdap.aBcfList=aBCFList;
        aBcfCsdap.CsdapList=csdapList;
    return aBcfCsdap;
    }

    @Override
      public void startElement( String namespaceURI,String localName,String qName,
               Attributes attr)  throws SAXException {
       
        currentElement = qName;
         elementValues.put(currentElement, new StringBuffer());
       //  distName="PLMN-PLMN/BSC-324532/BCF-172";
          if( localName.equals("managedObject") && attr.getValue("class").equals("BCF")&& attr.getValue("distName").equals(distName)){
      // currentBCF=new bcfDTO(); //initialize
             //aBCFVerifyer=;
             aBCFVerifyer=new BCFVerifyer();
             aBCF=new  BCF();
             aBCF.setDistName(attr.getValue(2));
             aBCF.setBSC(attr.getValue(2).split("-|/")[3]);
             aBCF.setBCF(attr.getValue(2).split("-|/")[5]);
             aBCF.setVersion(attr.getValue(1));
              System.out.println("Reading BSC XML for: "+attr.getValue(2));

       isBCF=true;
       
       ///add it here
      }
//          else{
//             isBCF=false;
//          }
//System.out.println(attr.getValue("name"));
         if(isBCF){
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("name")){  aBCFVerifyer.isName=true;

}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("adminState")){
    aBCFVerifyer.isAdminstate=true;
//System.out.println(aBCFVerifyer.isAdminstate);
}

if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("autoUnlAllowed")){
    aBCFVerifyer.isAutounlallowed=true;
}


if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("bcfType")){
    aBCFVerifyer.isBcftype=true;
}

if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("clockSource")){
    aBCFVerifyer.isClocksource=true;
}
if( localName.equals("list") && attr.getValue(0).equals("externalAlarmDefinition")){
    aBCFVerifyer.isExternalalarmdefinition=true;

   // System.out.println("External Alarm On");
}


//if( localName.equals("list") && aBCFVerifyer.isExternalalarmdefinition){
//    aBCFVerifyer.isExternalalarmdefinition=false;
//
//    System.out.println("External Alarm OFF"+localName+" "+attr.getValue(0));
//}
if( localName.equals("item") && aBCFVerifyer.isExternalalarmdefinition && !aBCFVerifyer.isExternalAlarmItem){
    aBCFVerifyer.isExternalAlarmItem=true;


}

if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("inputId") && aBCFVerifyer.isExternalAlarmItem){
    aBCFVerifyer.isInputid=true;
}

if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("inputTextId") && aBCFVerifyer.isExternalAlarmItem){
    aBCFVerifyer.isInputtextid=true;
}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("inputTextId") && aBCFVerifyer.isExternalAlarmItem){
    aBCFVerifyer.isInputtextid=true;
    }
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("polarity") && aBCFVerifyer.isExternalAlarmItem){
    aBCFVerifyer.isPolarity=true;
    }
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("reportingRoute") && aBCFVerifyer.isExternalAlarmItem){
    aBCFVerifyer.isReportingroute=true;
    }

if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("severity") && aBCFVerifyer.isExternalAlarmItem){
    aBCFVerifyer.isSeverity=true;
    }

//if( localName.equals("item") && aBCFVerifyer.isExternalalarmdefinition && aBCFVerifyer.isExternalAlarmItem){
//    aBCFVerifyer.isExternalAlarmItem=false;
//   // aAlarmList.add(aAlarm);
//}

if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("flexiEdgeAdditional2E1T1Usage")){
    aBCFVerifyer.isFlexiedgeadditional2E1T1Usage=true;
}

if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("flexiEdgeTrsAbisGroomingUsage")){
    aBCFVerifyer.isFlexiedgetrsabisgroomingusage=true;
}
if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("lapdLinkName")){
    aBCFVerifyer.isLapdlinkname=true;
}

if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("rxDifferenceLimit")){
    aBCFVerifyer.isRxdifferencelimit=true;
}



        if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("synchEnabled")){
    aBCFVerifyer.isSynchenabled=true;
}

       if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("csdapId1")){
    aBCFVerifyer.isCsdapid1=true;
}
       if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("csdapId2")){
    aBCFVerifyer.isCsdapid2=true;
}

       if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("csdapId3")){
    aBCFVerifyer.isCsdapid3=true;
}

       if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("csdapId3")){
    aBCFVerifyer.isCsdapid3=true;
}

       if( localName.equals("p") && attr.getValue(0)!=null && attr.getValue("name").
        equals("csdapId4")){
    aBCFVerifyer.isCsdapid4=true;
}









//System.out.println("value Start--Tag-->"+localName+"--->value--"+attr.getValue(0));

}


      }

    @Override
    public void endElement( String namespaceURI,String localName,
          String qName) throws SAXException {

    String value = ((StringBuffer)(elementValues.get(qName))).toString();
if(isBCF && qName.equals("managedObject")){
//System.out.println(qName);
isBCF=false;

aBCFList.add(aBCF);
}else if(isBCF){
//System.out.println("endElement--->"+qName);

if(qName.equalsIgnoreCase("List") && aBCFVerifyer.isExternalalarmdefinition){
    aBCF.setExternalAlarmList(aAlarmList);

    aBCFVerifyer.isExternalalarmdefinition=false;
//System.out.println("External Alarm Off--->"+qName +"  "+ aAlarmList.size());
}

if(qName.equalsIgnoreCase("item") && aBCFVerifyer.isExternalAlarmItem){
  //  aBCF.setExternalAlarmList(aAlarmList);

    aBCFVerifyer.isExternalAlarmItem=false;
//System.out.println("External Alarm Off--->"+qName +"  "+ aAlarmList.size());
}



if(aBCFVerifyer.isAdminstate) {//System.out.println("value--"+value);
aBCFVerifyer.isAdminstate=false;
        }
if(aBCFVerifyer.isName){
aBCFVerifyer.isName=false;
}

if(aBCFVerifyer.isAutounlallowed){
aBCFVerifyer.isAutounlallowed=false;
}
if(aBCFVerifyer.isBcftype){
aBCFVerifyer.isBcftype=false;
}

if(aBCFVerifyer.isClocksource){
aBCFVerifyer.isClocksource=false;
}


if(aBCFVerifyer.isInputid){
   aBCFVerifyer.isInputid=false;
}

if(aBCFVerifyer.isInputtextid){
   aBCFVerifyer.isInputtextid=false;
}

if(aBCFVerifyer.isCsdapid1){
aBCFVerifyer.isCsdapid1=false;
}

if(aBCFVerifyer.isCsdapid2){
aBCFVerifyer.isCsdapid2=false;
}

if(aBCFVerifyer.isCsdapid3){
aBCFVerifyer.isCsdapid3=false;
}

if(aBCFVerifyer.isCsdapid4){
aBCFVerifyer.isCsdapid4=false;
}

if(aBCFVerifyer.isPolarity){
   aBCFVerifyer.isPolarity=false;
}

if(aBCFVerifyer.isSeverity){
   aBCFVerifyer.isSeverity=false;
}

if(aBCFVerifyer.isReportingroute){
   aBCFVerifyer.isReportingroute=false;
}

if(aBCFVerifyer.isFlexiedgeadditional2E1T1Usage){
   aBCFVerifyer.isFlexiedgeadditional2E1T1Usage=false;
}


if(aBCFVerifyer.isFlexiedgetrsabisgroomingusage){
   aBCFVerifyer.isFlexiedgetrsabisgroomingusage=false;
}

if(aBCFVerifyer.isLapdlinkname){
   aBCFVerifyer.isLapdlinkname=false;
}

if(aBCFVerifyer.isRxdifferencelimit){
   aBCFVerifyer.isRxdifferencelimit=false;
}


if(aBCFVerifyer.isSynchenabled){
   aBCFVerifyer.isSynchenabled=false;
}
















}

  //  if(!value.equals("")){
       
  //  if(isBCF){
//.out.println("value--"+value);
        

 //   }






 //   }


    }

    @Override
       public void characters(char ch[], int start, int length)
    throws SAXException {
        if(isBCF){
//        System.out.println("start characters : " +
//            new String(ch, start, length));
        String elementValue=new String(ch, start, length);
        if(aBCFVerifyer.isName){
            aBCF.setName(elementValue);
        //System.out.println("BCF isAdminstate Paisi. value:"+new String(ch, start, length));
        }
        
         if(aBCFVerifyer.isAdminstate){
            aBCF.setAdminState(elementValue);
        //System.out.println("BCF isAdminstate Paisi. value:"+new String(ch, start, length));
        }

         if(aBCFVerifyer.isAutounlallowed){
            aBCF.setAutoUnlAllowed(elementValue);
            }

         if(aBCFVerifyer.isBcftype){
            aBCF.setBcfType(elementValue);
            }

          if(aBCFVerifyer.isClocksource){
            aBCF.setClockSource(elementValue);
            }

         if(aBCFVerifyer.isInputid){
              aAlarm=new ExternalAlarms();
            aAlarm.inputId=elementValue;
            //System.out.println("Alarm Input ID :"+aAlarm.inputId);
            }

        if(aBCFVerifyer.isInputtextid){
            aAlarm.inputTextId=elementValue;
            }

           if(aBCFVerifyer.isPolarity){
            aAlarm.polarity=elementValue;
            }

         if(aBCFVerifyer.isReportingroute){
            aAlarm.reportingRoute=elementValue;
            }

         if(aBCFVerifyer.isSeverity){
            aAlarm.severity=elementValue;
            aAlarmList.add(aAlarm);
            //System.out.println("List Size"+aAlarmList.size());
            }

           if(aBCFVerifyer.isFlexiedgeadditional2E1T1Usage){
            aBCF.setFlexiEdgeAdditional2E1T1Usage(elementValue);
            }

           if(aBCFVerifyer.isFlexiedgetrsabisgroomingusage){
            aBCF.setFlexiEdgeTrsAbisGroomingUsage(elementValue);
            }

        if(aBCFVerifyer.isLapdlinkname){
        aBCF.setLapdLinkName(elementValue);
        }


          if(aBCFVerifyer.isRxdifferencelimit){
        aBCF.setRxDifferenceLimit(elementValue);
        }

          if(aBCFVerifyer.isSynchenabled){
        aBCF.setSynchEnabled(elementValue);
        }

        if(aBCFVerifyer.isCsdapid1){
        aBCF.setCsdapid1(elementValue);
        if(!elementValue.equals("0") && elementValue!=null && !elementValue.isEmpty() ){
            if(!csdapList.contains(elementValue)){
            csdapList.add(elementValue);
            }
        }
        }
         if(aBCFVerifyer.isCsdapid2){
        aBCF.setCsdapid2(elementValue);
           if(!elementValue.equals("0") && elementValue!=null && !elementValue.isEmpty() ){
            if(!csdapList.contains(elementValue)){
            csdapList.add(elementValue);
            }

        }
         }
         if(aBCFVerifyer.isCsdapid3){
        aBCF.setCsdapid3(elementValue);
         if(!elementValue.equals("0") && elementValue!=null && !elementValue.isEmpty() ){
            if(!csdapList.contains(elementValue)){
            csdapList.add(elementValue);
            }

        }

        }
         if(aBCFVerifyer.isCsdapid4){
        aBCF.setCsdapid4(elementValue);
         if(!elementValue.equals("0") && elementValue!=null && !elementValue.isEmpty() ){
            if(!csdapList.contains(elementValue)){
            csdapList.add(elementValue);
            }

        }
        }




    }


    }}





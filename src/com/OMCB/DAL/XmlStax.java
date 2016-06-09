/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.OMCB.DAL;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author atchowdhury
 */
public class XmlStax {
 boolean bFirstName = false;
      boolean bLastName = false;
      boolean bNickName = false;
      boolean bMarks = false;

public void staxParserTest() throws FileNotFoundException, XMLStreamException, MalformedURLException, IOException{

        XMLInputFactory factory = XMLInputFactory.newInstance();
         XMLEventReader eventReader =
         factory.createXMLEventReader(new FileReader("D:\\input\\BSFR01_F_031115.xml"));

        // URL url = new URL("D:\\input\\BSFR01_F_031115.xml");
        // InputStream in = url.openStream();
    XMLInputFactory factory1 = XMLInputFactory.newInstance();
    XMLStreamReader parser = factory1.createXMLStreamReader(new FileReader("D:\\input\\BSFR01_F_031115.xml"));

int i=0;
boolean isBCF=false;
    while (parser.hasNext()) {

      int event = parser.next();
      //XMLStreamReader parserCopy=parser;
      if (event == XMLStreamConstants.START_ELEMENT) {
          isBCF=false;
           i++;
        //System.out.println(parser.getLocalName());

        if (parser.getLocalName().equals("managedObject")) {
          String href = parser.getAttributeValue(null, "class");
          String distName = parser.getAttributeValue(null, "distName");
        //  parser.getElementText();
          if (href.equals("BCF") &&distName.equals("PLMN-PLMN/BSC-324532/BCF-172")){
            System.out.println(href+" "+distName);
          isBCF=true;
          }


        }
      }
      if(event == XMLStreamConstants.CHARACTERS){
          if(isBCF){

                System.out.println("This is The BCF");
                System.out.println("Localname = "+parser.getText());


          }

      }
    }



//XMLInputFactory factory = XMLInputFactory.newInstance();
//XMLStreamReader r = factory.createXMLStreamReader(new FileReader("D:\\input\\BSFR01_F_031115.xml"));
//try {
//      int event = r.getEventType();
//      while (true) {
//            switch (event) {
//            case XMLStreamConstants.START_DOCUMENT:
//                  System.out.println("Start Document.");
//                  break;
//            case XMLStreamConstants.START_ELEMENT:
//                  System.out.println("Start Element: " + r.getName());
//                  for(int i = 0, n = r.getAttributeCount(); i < n; ++i)
//                        System.out.println("Attribute: " + r.getAttributeName(i)
//                              + "=" + r.getAttributeValue(i));
//
//                  break;
//            case XMLStreamConstants.CHARACTERS:
//                  if (r.isWhiteSpace())
//                        break;
//
//                  System.out.println("Text: " + r.getText());
//                  break;
//            case XMLStreamConstants.END_ELEMENT:
//                  System.out.println("End Element:" + r.getName());
//                  break;
//            case XMLStreamConstants.END_DOCUMENT:
//                  System.out.println("End Document.");
//                  break;
//            }
//
//            if (!r.hasNext())
//                  break;
//
//            event = r.next();
//      }
//} finally {
//      r.close();
//}











//
//
//    XMLInputFactory factory2 = XMLInputFactory.newInstance();
//         XMLEventReader eventReader1 =factory2.createXMLEventReader(new FileReader("D:\\input\\BSFR01_F_031115.xml"));
//         boolean isBCF=false;
//
//         while(eventReader.hasNext()){
//               XMLEvent event = eventReader.nextEvent();
//              // isBCF=false;
//               switch(event.getEventType()){
//                  case XMLStreamConstants.START_ELEMENT:
//                     StartElement startElement = event.asStartElement();
//                     String qName = startElement.getName().getLocalPart();
// Iterator<Attribute> attributes = startElement.getAttributes();
//String rollNo = attributes.next().getValue();
//                             if (qName.equals("managedObject")) {
//                                 System.out.println("managedObject" +rollNo);}
//         // String href = parser.getAttributeValue(null, "class");
//         // String distName = parser.getAttributeValue(null, "distName");
//        //  parser.getElementText();
////          if (href.equals("BCF") &&distName.equals("PLMN-PLMN/BSC-324532/BCF-172")){
////            System.out.println(href+" "+distName);
////          isBCF=true;}}
//
//
//
//                     break;
//                  case XMLStreamConstants.CHARACTERS:
//                     Characters characters = event.asCharacters();
//
//
//                     if(bFirstName){
//                        System.out.println("First Name: "
//                        + characters.getData());
//                        bFirstName = false;
//                     }
//                     if(isBCF){
//                         System.out.println("Last Name: "
//                        + characters.getData());
//                        isBCF = false;
//                     }
//                     if(bLastName){
//                        System.out.println("Last Name: "
//                        + characters.getData());
//                        bLastName = false;
//                     }
//                     if(bNickName){
//                        System.out.println("Nick Name: "
//                        + characters.getData());
//                        bNickName = false;
//                     }
//                     if(bMarks){
//                        System.out.println("Marks: "
//                        + characters.getData());
//                        bMarks = false;
//                     }
//                     break;
//                  case  XMLStreamConstants.END_ELEMENT:
//                     EndElement endElement = event.asEndElement();
//                     if(endElement.getName().getLocalPart().equalsIgnoreCase("student")){
//                        System.out.println("End Element : student");
//                        System.out.println();
//                     }
//                     break;
//
//
//
//
//











       //  InputStream xmlInputStream = getClass().getResourceAsStream("D:\\input\\BSFR01_F_031115.xml");






//         while(eventReader.hasNext()){
//
//             XMLEvent event = eventReader.nextEvent();
//
//           //  System.out.println(event);
//             switch(event.getEventType()){
//                  case XMLStreamConstants.START_ELEMENT:
//                     StartElement startElement = event.asStartElement();
//                     Iterator<Attribute> attribue = event.asStartElement().getAttributes();
//                     String qName = startElement.getName().getLocalPart();
//                      String qAttribute = startElement.getAttributes().toString();
//                     //System.out.println(qAttribute);
//                       System.out.println(qName);
//                     if (qName.equalsIgnoreCase("ManagedObject")) {
//                        System.out.println("Start Element : BCF");
//                        eventReader.getAttributeLocalName(0);
//                         System.out.println(qName);
//                        Iterator<Attribute> attributes = startElement.getAttributes();
//                       // String rollNo = attributes.next().getValue();
//                       // System.out.println("Roll No : " + rollNo);
//                     }
////                     else if (qName.equalsIgnoreCase("firstname")) {
////                        bFirstName = true;
////                     } else if (qName.equalsIgnoreCase("lastname")) {
////                        bLastName = true;
////                     } else if (qName.equalsIgnoreCase("nickname")) {
////                        bNickName = true;
////                     }
////                     else if (qName.equalsIgnoreCase("marks")) {
////                        bMarks = true;
////                     }
//                     break;
//             }

//         }
}



         }

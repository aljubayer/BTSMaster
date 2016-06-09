/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.OMCB.DAL;

import java.util.jar.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author atchowdhury
 */

class XmlHandlerFlexi extends DefaultHandler {

   boolean bFirstName = false;
   boolean bLastName = false;
   boolean bNickName = false;
   boolean bMarks = false;
  //  private Object attr;


   public void startElement(String uri,
      String localName, String qName, Attributes attr)
         throws SAXException {
      if (qName.equalsIgnoreCase("student")) {
         String rollNo = attr.getValue("rollno");
         System.out.println("Roll No : " + rollNo);
      } else if (qName.equalsIgnoreCase("firstname")) {
         bFirstName = true;
      } else if (qName.equalsIgnoreCase("lastname")) {
         bLastName = true;
      } else if (qName.equalsIgnoreCase("nickname")) {
         bNickName = true;
      }
      else if (qName.equalsIgnoreCase("marks")) {
         bMarks = true;
      }
// System.out.println("HEllo");
//        if( qName.equals("managedObject") && attr.getValue("class").equals("BCF")){
//  System.out.println("HEllo");
//       ///add it here
//      }


   }

//   @Override
//   public void endElement(String uri,
//      String localName, String qName) throws SAXException {
//      if (qName.equalsIgnoreCase("student")) {
//         System.out.println("End Element :" + qName);
//      }
//   }
//
//   @Override
//   public void characters(char ch[],
//      int start, int length) throws SAXException {
//      if (bFirstName) {
//         System.out.println("First Name: "
//         + new String(ch, start, length));
//         bFirstName = false;
//      } else if (bLastName) {
//         System.out.println("Last Name: "
//         + new String(ch, start, length));
//         bLastName = false;
//      } else if (bNickName) {
//         System.out.println("Nick Name: "
//         + new String(ch, start, length));
//         bNickName = false;
//      } else if (bMarks) {
//         System.out.println("Marks: "
//         + new String(ch, start, length));
//         bMarks = false;
//      }
//   }
}

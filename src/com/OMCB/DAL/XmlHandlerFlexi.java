/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.OMCB.DAL;

import java.util.jar.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
        } else if (qName.equalsIgnoreCase("marks")) {
            bMarks = true;
        }

    }
}

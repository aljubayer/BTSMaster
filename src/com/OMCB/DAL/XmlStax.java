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

public class XmlStax {

    boolean bFirstName = false;
    boolean bLastName = false;
    boolean bNickName = false;
    boolean bMarks = false;

    public void staxParserTest() throws FileNotFoundException, XMLStreamException, MalformedURLException, IOException {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader
                = factory.createXMLEventReader(new FileReader("D:\\input\\BSFR01_F_031115.xml"));

        // URL url = new URL("D:\\input\\BSFR01_F_031115.xml");
        // InputStream in = url.openStream();
        XMLInputFactory factory1 = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory1.createXMLStreamReader(new FileReader("D:\\input\\BSFR01_F_031115.xml"));

        int i = 0;
        boolean isBCF = false;
        while (parser.hasNext()) {

            int event = parser.next();

            if (event == XMLStreamConstants.START_ELEMENT) {
                isBCF = false;
                i++;

                if (parser.getLocalName().equals("managedObject")) {
                    String href = parser.getAttributeValue(null, "class");
                    String distName = parser.getAttributeValue(null, "distName");

                    if (href.equals("BCF") && distName.equals("PLMN-PLMN/BSC-324532/BCF-172")) {
                        System.out.println(href + " " + distName);
                        isBCF = true;
                    }

                }
            }
            if (event == XMLStreamConstants.CHARACTERS) {
                if (isBCF) {

                    System.out.println("This is The BCF");
                    System.out.println("Localname = " + parser.getText());

                }

            }
        }
    }
}

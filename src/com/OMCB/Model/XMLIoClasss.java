/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.OMCB.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class XMLIoClasss {

    public void XmlReadProcess() throws FileNotFoundException, DocumentException {

        SAXReader reader = new SAXReader();
        Document document = reader.read(new FileInputStream("D:\\input\\BSFR01_F_031115.xml"));
        Element rootElement = document.getRootElement();
        XPath xpathSelector = DocumentHelper.createXPath("/managedobject[@class='BCF']");

        List results = xpathSelector.selectNodes(document);
        System.out.println(results.size());
        for (Iterator iter = results.iterator(); iter.hasNext();) {
            Element element = (Element) iter.next();
            System.out.println(element.getName());
        }
    }
}

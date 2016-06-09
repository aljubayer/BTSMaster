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


/**
 *
 * @author atchowdhury
 */
public class XMLIoClasss {

 public void XmlReadProcess() throws FileNotFoundException, DocumentException{




     SAXReader reader = new SAXReader();
        Document document = reader.read(new FileInputStream("D:\\input\\BSFR01_F_031115.xml"));
        Element rootElement=document.getRootElement();
        XPath xpathSelector = DocumentHelper.createXPath("/managedobject[@class='BCF']");

         List results = xpathSelector.selectNodes(document);
         System.out.println(results.size());
    for ( Iterator iter = results.iterator(); iter.hasNext(); ) {
      Element element = (Element) iter.next();
      System.out.println(element.getName());
    }



          // root = this.doc.getRootElement();
//    Iterator elementIterator = rootElement.elementIterator();
//    while(elementIterator.hasNext()){
//      Element elmeent = (Element)elementIterator.next();
//      System.out.println(elmeent.getName());
//    }
  }



//          for ( Iterator i = rootElement.elementIterator(); i.hasNext(); ) {
//           // Element element = (Element) i.next();
//                    System.out.println(i);
//
//            // do something
//        }

//
//          for ( Iterator i = rootElement.attributeIterator(); i.hasNext(); ) {
//            Attribute attribute = (Attribute) i.next();
//            System.out.println(attribute);
//            // do something
//        }
//        System.out.println(rootElement);

       // System.out.println("Totam Nodes  --?"+document.selectNodes("//managedobject/@class").size());
        //node.

         // System.out.println("Total Vehicles: " + document.selectNodes("//vehicles/vehicle").size());
         //  System.out.println("No. of cars: " + document.selectNodes("//*[class= 'BCF']").size
       //System.out.println("No. of ManagedObjects: " + document.selectNodes(rootElement).size());
     //   System.out.println("No. of cars: " + document.selectNodes("//vehicles/vehicle[@type='car']").size());
     // Node value = document.selectSingleNode("*[local-name='BCF']");

//System.out.println(value);

        //   http://stackoverflow.com/questions/10981198/dom4j-java-how-to-get-all-elements-parent-or-children-within-root-that-have-s

//           You can use XPath for that using //while or the name() function and a wildcard node *: //*[name() = 'while']
//
//List list = document.selectNodes("//*[name() = 'while']"); // or "//while"
//int numberOfNodes = list.size();
//for (Iterator iter = list.iterator(); iter.hasNext(); ) {
//    // do something
//}

//   DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//    DocumentBuilder db = dbf.newDocumentBuilder();
//
//    Document dom=null;
//			//parse using builder to get DOM representation of the XML file
//			dom = db.parse("D:\\input\\BSFR01_F_031115.xml");
//
//                        Element docEle = dom.getDocumentElement();
//                        NodeList nl = docEle.getElementsByTagName("managedobject");
//                        if(nl != null && nl.getLength() > 0) {
//			for(int i = 0 ; i < nl.getLength();i++) {
//
//				//get the employee element
//				Element el = (Element)nl.item(i);
//                                System.out.println(el.getAttribute("class"));
//
//				//get the Employee object
//				//Employee e = getEmployee(el);
//
//				//add it to list
//				//myEmpls.add(e);
//			}
//		}






}


// }



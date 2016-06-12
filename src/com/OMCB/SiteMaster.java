package com.OMCB;

import com.OMCB.Model.AutomatedTelnetClient;
import com.OMCB.Model.BCF;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import com.OMCB.BLL.ReadInputFile;
import com.mysql.jdbc.StringUtils;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.apache.commons.net.telnet.TelnetClient;
import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

public class SiteMaster {

    public static void main(String[] args) throws SQLException, IOException, NoSuchFieldException, ParserConfigurationException, SAXException, XMLStreamException, FileNotFoundException, DocumentException, ParseException {

        System.out.println("Site Master Executing.....");

        ReadInputFile aInputReader = new ReadInputFile();

        aInputReader.ReadInput();

    }
}

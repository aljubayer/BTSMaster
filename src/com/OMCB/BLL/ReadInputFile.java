package com.OMCB.BLL;

import com.OMCB.Model.InputData;
import com.OMCB.Model.LAPD;
import com.OMCB.Model.MapInputDataToDataStructure;
import java.io.*;
import java.io.IOException.*;
import java.io.FileNotFoundException.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

public class ReadInputFile {

    public void ReadInput() throws IOException, NoSuchFieldException, SQLException, ParserConfigurationException, SAXException, XMLStreamException, FileNotFoundException, DocumentException, ParseException {

        List<InputData> aInputList = new ArrayList<InputData>() {
        };

        String content = null;
        File file = new File("D:\\input\\input.txt"); //for ex foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        String[] inputArray = content.split("\\r?\\n");
        for (String aInputine : inputArray) {

            String[] inputElements = aInputine.split(" ");
            InputData aInputData = new InputData();
            aInputData.BSC = inputElements[0];
            aInputData.BCF = inputElements[1];
            aInputData.PCM1 = inputElements[2];
            aInputData.PCM2 = inputElements[3];
            aInputData.PCM3 = inputElements[4];
            aInputData.PCM4 = inputElements[5];
            aInputData.PCM5 = inputElements[6];
            aInputData.PCM6 = inputElements[7];
            aInputData.PCM7 = inputElements[8];
            aInputData.PCM8 = inputElements[9];

            aInputData.TargetPCM1 = inputElements[10];
            aInputData.TargetPCM2 = inputElements[11];
            aInputData.TargetPCM3 = inputElements[12];
            aInputData.TargetPCM4 = inputElements[13];
            aInputData.TargetPCM5 = inputElements[14];
            aInputData.TargetPCM6 = inputElements[15];
            aInputData.TargetPCM7 = inputElements[16];
            aInputData.TargetPCM8 = inputElements[17];

            aInputList.add(aInputData);

        }

        MapInputDataToDataStructure aDataMapper = new MapInputDataToDataStructure();
        Map<String, Map<String, Map<String, String>>> aInputDictionary1 = aDataMapper.InsertIntoDataStructure(aInputList);

        LAPD lapd = new LAPD();
        lapd.FetchLapdData(aInputDictionary1);
    }
}

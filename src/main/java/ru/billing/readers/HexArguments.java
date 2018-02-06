package ru.billing.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.billing.hextypes.HexArgumentRecord;
import ru.billing.hextypes.HexRequestRecord;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.HashMap;

public class HexArguments {


    private HashMap<String, HexArgumentRecord> HexArgumentRecordHashMap;

    public HexArguments(File vXmlFile) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document docXML = dBuilder.parse(vXmlFile);


            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr4HexArguments = xpath.compile("/CONFIGURATION/DATA/TABLE[NAME='HEX_ARGUMENTS']/ROWSET/ROW");
            NodeList nl4HexArguments = (NodeList) expr4HexArguments.evaluate(docXML, XPathConstants.NODESET);

         //   System.out.println("First  Document. Number of HEX requests: " + nl4HexRequests.getLength());

            // читаем  из аргументы файла
            HexArgumentRecordHashMap  = new HashMap<String, HexArgumentRecord>();
            buildArgumentsMap(HexArgumentRecordHashMap, nl4HexArguments);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // internal

    // пихаем все потроха команд в MAP
    private void buildArgumentsMap (HashMap<String, HexArgumentRecord> iMap, NodeList iNodeList) {



         String ENTITY_GUID = "";
         String NAME = "";
         String SDCT_SDCT_ID = "";
         String DEFAULT_VALUE = "";
         String TRANSFORMER = "";


        for (int i=0 ; i<iNodeList.getLength(); i++) {

            NodeList currentItem = iNodeList.item(i).getChildNodes();
            for (int j=0; j < currentItem.getLength(); j++) {
                Node currentElement =  currentItem.item(j);

                if (currentElement.getNodeName().equals("ENTITY_GUID"))
                {ENTITY_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("NAME"))
                {NAME = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("SDCT_SDCT_ID"))
                {SDCT_SDCT_ID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("DEFAULT_VALUE"))
                {DEFAULT_VALUE = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("TRANSFORMER"))
                {TRANSFORMER = currentElement.getTextContent();}


            }
            iMap.put(ENTITY_GUID,new HexArgumentRecord(
                            ENTITY_GUID,
                            NAME,
                            SDCT_SDCT_ID,
                            DEFAULT_VALUE,
                            TRANSFORMER
                    )
            );
        }

    }


    // public


    public HashMap<String, HexArgumentRecord> getHexArgumentRecordHashMap() {
        return HexArgumentRecordHashMap;
    }

    public HexArgumentRecord getHexArgumentRecordByGUID (String GUID) {
        return HexArgumentRecordHashMap.get(GUID);
    }



}

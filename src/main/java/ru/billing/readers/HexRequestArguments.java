package ru.billing.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.billing.hextypes.HexRequestArgumentRecord;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.HashMap;

public class HexRequestArguments {


    private HashMap<String, HexRequestArgumentRecord> HexRequestArgumentRecordHashMap;

    public HexRequestArguments(File vXmlFile) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document docXML = dBuilder.parse(vXmlFile);


            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr4HexRequestArguments = xpath.compile("/CONFIGURATION/DATA/TABLE[NAME='HEX_REQUEST_ARGUMENTS']/ROWSET/ROW");
            NodeList nl4HexRequestArguments = (NodeList) expr4HexRequestArguments.evaluate(docXML, XPathConstants.NODESET);

            // читаем  из аргументы файла
            HexRequestArgumentRecordHashMap  = new HashMap<String, HexRequestArgumentRecord>();
            buildRequestArgumentsMap(HexRequestArgumentRecordHashMap, nl4HexRequestArguments);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // internal

    // пихаем все потроха команд в MAP
    private void buildRequestArgumentsMap (HashMap<String, HexRequestArgumentRecord> iMap, NodeList iNodeList) {



        String REQ_ENTITY_GUID;
        String ARGUMENT_NAME;
        String ARG_ENTITY_GUID;
        String MANDATORY_FLAG;

        for (int i=0 ; i<iNodeList.getLength(); i++) {
             REQ_ENTITY_GUID   = "";
             ARGUMENT_NAME   = "";
             ARG_ENTITY_GUID  = "";
             MANDATORY_FLAG       = "";
            NodeList currentItem = iNodeList.item(i).getChildNodes();
            for (int j=0; j < currentItem.getLength(); j++) {
                Node currentElement =  currentItem.item(j);

                if (currentElement.getNodeName().equals("REQ_ENTITY_GUID"))
                {REQ_ENTITY_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("ARGUMENT_NAME"))
                {ARGUMENT_NAME = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("ARG_ENTITY_GUID"))
                {ARG_ENTITY_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("MANDATORY_FLAG"))
                {MANDATORY_FLAG = currentElement.getTextContent();}

            }
            iMap.put(REQ_ENTITY_GUID + ARGUMENT_NAME,new HexRequestArgumentRecord(
                    REQ_ENTITY_GUID,
                    ARGUMENT_NAME,
                    ARG_ENTITY_GUID,
                    MANDATORY_FLAG
                    )
            );
        }

    }


    // public


    public HashMap<String, HexRequestArgumentRecord> getHexRequestArgumentRecordHashMap() {
        return HexRequestArgumentRecordHashMap;
    }

    public HexRequestArgumentRecord getHexRequestArgumentRecordByGUID (String GUID) {
        return HexRequestArgumentRecordHashMap.get(GUID);
    }



}

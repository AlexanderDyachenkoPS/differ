package ru.billing.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.billing.hextypes.HexArcRelationRecord;
import ru.billing.hextypes.HexCommandGraphRecord;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.HashMap;

public class HexCollectionHeader {


    String COLLECTION_UID;
    String COLLECTION_VERSION;
    String COLLECTION_NAME;
    String COLLECTION_FILENAME;
    String HEX_VERSION;
    String HEX_SCR_VERSION;

    public HexCollectionHeader(File vXmlFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document docXML = dBuilder.parse(vXmlFile);

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr4CollectionHeader = xpath.compile("/CONFIGURATION/HEADER/APPLICATION");
            NodeList nl4CollectionHeader = (NodeList) expr4CollectionHeader.evaluate(docXML, XPathConstants.NODESET);

            buildCollectionHeader( nl4CollectionHeader);
            // читаем потроха команд из второго файла
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // internal

    // пихаем все потроха команд в MAP
    private void buildCollectionHeader ( NodeList iNodeList) {

        String COLLECTION_UID       = "";
        String COLLECTION_VERSION   = "";
        String COLLECTION_NAME      = "";
        String COLLECTION_FILENAME  = "";
        String HEX_VERSION          = "";
        String HEX_SCR_VERSION      = "";




        for (int i=0 ; i<iNodeList.getLength(); i++) {

            NodeList currentItem = iNodeList.item(i).getChildNodes();
            for (int j=0; j < currentItem.getLength(); j++) {
                Node currentElement =  currentItem.item(j);

                if (currentElement.getNodeName().equals("COLLECTION_UID"))
                {this.COLLECTION_UID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("COLLECTION_VERSION"))
                {this.COLLECTION_VERSION = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("COLLECTION_NAME"))
                {this.COLLECTION_NAME = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("COLLECTION_FILENAME"))
                {this.COLLECTION_FILENAME = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("HEX_VERSION"))
                {this.HEX_VERSION = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("HEX_SCR_VERSION"))
                {this.HEX_SCR_VERSION = currentElement.getTextContent();}
            }
        }
    }

    public String getCOLLECTION_UID() {
        return COLLECTION_UID;
    }

    public String getCOLLECTION_VERSION() {
        return COLLECTION_VERSION;
    }

    public String getCOLLECTION_NAME() {
        return COLLECTION_NAME;
    }

    public String getCOLLECTION_FILENAME() {
        return COLLECTION_FILENAME;
    }

    public String getHEX_VERSION() {
        return HEX_VERSION;
    }

    public String getHEX_SCR_VERSION() {
        return HEX_SCR_VERSION;
    }
}

package ru.billing.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.billing.hextypes.HexArcRequestRecord;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.HashMap;

public class HexArcRequest {

    private HashMap<String, HexArcRequestRecord> HexArcRequestRecordHashMap;

    public HexArcRequest(File vXmlFile) {
      //  System.out.println("================================");
      //  System.out.println(this.toString()+". Read XML files");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document docXML = dBuilder.parse(vXmlFile);

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr4HexArcRequest = xpath.compile("/CONFIGURATION/DATA/TABLE[NAME='HEX_ARC_REQUESTS']/ROWSET/ROW");
            NodeList nl4HexArcRequest = (NodeList) expr4HexArcRequest.evaluate(docXML, XPathConstants.NODESET);

         //   System.out.println("First  Document. Number of HEX ArcRequest: " + nl4HexArcRequest.getLength());
            // читаем потроха команд из первого файла
            HexArcRequestRecordHashMap  = new HashMap<String, HexArcRequestRecord>();
            buildArcRequestMap(HexArcRequestRecordHashMap, nl4HexArcRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // internal

    // пихаем все потроха команд в MAP
    private void buildArcRequestMap (HashMap<String, HexArcRequestRecord> iMap, NodeList iNodeList) {

        String HARC_ID = "";
        String SUBCOMMAND_GUID = "";
        String SUB_COLLECTION_GUID = "";
        String REQUEST_GUID = "";
        String REQ_COLLECTION_GUID = "";
        String PREDICATE_GUID = "";
        String PRD_COLLECTION_GUID = "";


      //  System.out.println(iNodeList.getLength());

        for (int i=0 ; i<iNodeList.getLength(); i++) {
             HARC_ID = "";
             SUBCOMMAND_GUID = "";
             SUB_COLLECTION_GUID = "";
             REQUEST_GUID = "";
             REQ_COLLECTION_GUID = "";
             PREDICATE_GUID = "";
             PRD_COLLECTION_GUID = "";
            NodeList currentItem = iNodeList.item(i).getChildNodes();
            for (int j=0; j < currentItem.getLength(); j++) {
                Node currentElement =  currentItem.item(j);

                if (currentElement.getNodeName().equals("HARC_HARC_ID"))
                {HARC_ID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("SUBCOMMAND_GUID"))
                {SUBCOMMAND_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("SUB_COLLECTION_GUID"))
                {SUB_COLLECTION_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("REQUEST_GUID"))
                {REQUEST_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("REQ_COLLECTION_GUID"))
                {REQ_COLLECTION_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("PRD_COLLECTION_GUID"))
                {PRD_COLLECTION_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("PREDICATE_GUID"))
                {PREDICATE_GUID = currentElement.getTextContent();}

            }
            iMap.put(HARC_ID,new HexArcRequestRecord(
                HARC_ID,
                            SUBCOMMAND_GUID,
                            SUB_COLLECTION_GUID,
            REQUEST_GUID,
            REQ_COLLECTION_GUID,
             PREDICATE_GUID,
             PRD_COLLECTION_GUID
                    )
            );
        }
      //  System.out.println("iMap :"+iMap.size());
    }


    // public
    public  HashMap<String, HexArcRequestRecord> getHexArcRequest() {
        return HexArcRequestRecordHashMap;
    }
    public HexArcRequestRecord getArcRequestRecordByHARC_HARC_ID (String iHARC_HARC_ID) {
        return HexArcRequestRecordHashMap.get(iHARC_HARC_ID);
    }


}

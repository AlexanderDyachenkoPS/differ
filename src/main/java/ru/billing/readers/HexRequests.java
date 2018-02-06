package ru.billing.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.billing.hextypes.HexRequestRecord;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.HashMap;

public class HexRequests {


    private HashMap<String, HexRequestRecord> HexRequestRecordHashMap;

    public HexRequests(File vXmlFile) {
     //   System.out.println("================================");
     //   System.out.println(this.toString()+". Read XML files");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document docXML = dBuilder.parse(vXmlFile);


            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr4HexRequests = xpath.compile("/CONFIGURATION/DATA/TABLE[NAME='HEX_REQUESTS']/ROWSET/ROW");
            NodeList nl4HexRequests = (NodeList) expr4HexRequests.evaluate(docXML, XPathConstants.NODESET);

         //   System.out.println("First  Document. Number of HEX requests: " + nl4HexRequests.getLength());

            // читаем потроха команд из  файла
            HexRequestRecordHashMap  = new HashMap<String, HexRequestRecord>();
            buildRequestsMap(HexRequestRecordHashMap, nl4HexRequests);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // internal

    // пихаем все потроха команд в MAP
    private void buildRequestsMap (HashMap<String, HexRequestRecord> iMap, NodeList iNodeList) {

        String ENTITY_GUID   = "";
        String EQTYPE_GUID   = "";
        String HRQT_HRQT_ID  = "";
        String REQUEST       = "";
        String SDCT_SDCT_ID  = "";

        for (int i=0 ; i<iNodeList.getLength(); i++) {
             ENTITY_GUID   = "";
             EQTYPE_GUID   = "";
             HRQT_HRQT_ID  = "";
             REQUEST       = "";
             SDCT_SDCT_ID  = "";
            NodeList currentItem = iNodeList.item(i).getChildNodes();
            for (int j=0; j < currentItem.getLength(); j++) {
                Node currentElement =  currentItem.item(j);

                if (currentElement.getNodeName().equals("ENTITY_GUID"))
                {ENTITY_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("EQTYPE_GUID"))
                {EQTYPE_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("HRQT_HRQT_ID"))
                {HRQT_HRQT_ID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("REQUEST"))
                {REQUEST = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("REQUEST_CLOB"))
                {REQUEST = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("SDCT_SDCT_ID"))
                {SDCT_SDCT_ID = currentElement.getTextContent();}

            }
            iMap.put(ENTITY_GUID,new HexRequestRecord(
                            ENTITY_GUID,
                            EQTYPE_GUID,
                            HRQT_HRQT_ID,
                            REQUEST,
                            SDCT_SDCT_ID
                    )
            );
        }

    }


    // public
    public  HashMap<String, HexRequestRecord> getHexRequests() {
        return HexRequestRecordHashMap;
    }

    public HexRequestRecord getHexRequestRecordByGUID (String GUID) {
        return HexRequestRecordHashMap.get(GUID);
    }



}

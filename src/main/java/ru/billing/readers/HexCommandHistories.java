package ru.billing.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.billing.hextypes.HexCommanHistoryRecord;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HexCommandHistories {


    private HashMap<String, HexCommanHistoryRecord> HexCommanHistoryRecordHashMap;


    public HexCommandHistories(File vXmlFile) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document docXML = dBuilder.parse(vXmlFile);

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr4HexCommandHistories = xpath.compile("/CONFIGURATION/DATA/TABLE[NAME='HEX_COMMAND_HISTORIES']/ROWSET/ROW");
            NodeList nl4HexCommandHistories = (NodeList) expr4HexCommandHistories.evaluate(docXML, XPathConstants.NODESET);

           // System.out.println("First  Document. Number of HEX CommanHistories: " + nl4HexCommandHistories.getLength());
            // читаем потроха команд из первого файла
            HexCommanHistoryRecordHashMap  = new HashMap<String, HexCommanHistoryRecord>();
            buildCommandHistoriesMap(HexCommanHistoryRecordHashMap, nl4HexCommandHistories);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // internal

    // пихаем все потроха команд в MAP
    private void buildCommandHistoriesMap (HashMap<String, HexCommanHistoryRecord> iMap, NodeList iNodeList) {

        String ENTITY_GUID   = "";
        String HARC_HARC_ID   = "";

        for (int i=0 ; i<iNodeList.getLength(); i++) {
             ENTITY_GUID   = "";
             HARC_HARC_ID   = "";
            NodeList currentItem = iNodeList.item(i).getChildNodes();
            for (int j=0; j < currentItem.getLength(); j++) {
                Node currentElement =  currentItem.item(j);

                if (currentElement.getNodeName().equals("ENTITY_GUID"))
                {ENTITY_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("HARC_HARC_ID"))
                {HARC_HARC_ID = currentElement.getTextContent();}

            }
            iMap.put(ENTITY_GUID,new HexCommanHistoryRecord(
                            ENTITY_GUID,
                            HARC_HARC_ID
                    )
            );
        }

    }


    // public
    public  HashMap<String, HexCommanHistoryRecord> getHexCommandHistories() {
        return HexCommanHistoryRecordHashMap;
    }

    public HexCommanHistoryRecord getHexCommandHistoryRecordByGUID (String GUID) {
        return HexCommanHistoryRecordHashMap.get(GUID);
    }

    public String getHexCommandHistoryRecordByHARC_HARC_ID (String iHARC_HARC_ID) {
        HexCommanHistoryRecord vHexCommanHistoryRecord;
        String guid;
        Set set;
        Iterator iterator;
        set = HexCommanHistoryRecordHashMap.entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            guid = mentry.getKey().toString();
            vHexCommanHistoryRecord = (HexCommanHistoryRecord) mentry.getValue();
            if (vHexCommanHistoryRecord.getHARC_HARC_ID().equals(iHARC_HARC_ID)) {
                return guid;
            }
        }
        return "";
    }


}

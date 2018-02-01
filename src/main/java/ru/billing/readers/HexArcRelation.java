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

public class HexArcRelation {


    HexCommandGraphRecord Graph;

    private HashMap<String, HexArcRelationRecord> HexArcRelationRecordHashMap;


    public HexArcRelation(File vXmlFile) {
       // System.out.println("================================");
       // System.out.println(this.toString()+". Read XML files");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document docXML = dBuilder.parse(vXmlFile);

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr4HexArcRelation = xpath.compile("/CONFIGURATION/DATA/TABLE[NAME='HEX_ARC_RELATIONS']/ROWSET/ROW");
            NodeList nl4HexArcRelation = (NodeList) expr4HexArcRelation.evaluate(docXML, XPathConstants.NODESET);

         //   System.out.println("First  Document. Number of HEX ArcRelation: " + nl4HexArcRelation.getLength());

            // читаем потроха команд из первого файла
            HexArcRelationRecordHashMap  = new HashMap<String, HexArcRelationRecord>();
            buildArcRelationMap(HexArcRelationRecordHashMap, nl4HexArcRelation);
            // читаем потроха команд из второго файла
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // internal

    // пихаем все потроха команд в MAP
    private void buildArcRelationMap (HashMap<String, HexArcRelationRecord> iMap, NodeList iNodeList) {

        String PREDICATE_GUID   = "";
        String HARC_HARC_ID   = "";
        String NEXT_HARC_ID   = "";

     //   System.out.println(iNodeList.getLength());

        for (int i=0 ; i<iNodeList.getLength(); i++) {

            NodeList currentItem = iNodeList.item(i).getChildNodes();
            for (int j=0; j < currentItem.getLength(); j++) {
                Node currentElement =  currentItem.item(j);

                if (currentElement.getNodeName().equals("PREDICATE_GUID"))
                {PREDICATE_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("HARC_HARC_ID"))
                {HARC_HARC_ID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("NEXT_HARC_ID"))
                {NEXT_HARC_ID = currentElement.getTextContent();}

            }
            iMap.put(NEXT_HARC_ID,new HexArcRelationRecord(
                            PREDICATE_GUID,
                            HARC_HARC_ID,
                            NEXT_HARC_ID
                    )
            );
        }
       // System.out.println("iMap :"+iMap.size());
    }

    // public
    public  HashMap<String, HexArcRelationRecord> getHexArcRelation() {
        return HexArcRelationRecordHashMap;
    }
    public HexArcRelationRecord getArcRelationRecordByGUID (String iHARC_HARC_ID) {
        return HexArcRelationRecordHashMap.get(iHARC_HARC_ID);
    }


}

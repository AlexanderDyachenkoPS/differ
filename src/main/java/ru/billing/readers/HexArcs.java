package ru.billing.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.billing.hextypes.HexArcRecord;
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

public class HexArcs {

    /*
<HARC_ID>6426</HARC_ID>
<CRITICAL_FLAG>0</CRITICAL_FLAG>
<FINAL_FLAG>0</FINAL_FLAG>
<ERROR_IGNORE_FLAG>0</ERROR_IGNORE_FLAG>
<PRINT_OUT_FLAG>0</PRINT_OUT_FLAG>

    */



    private HashMap<String, HexArcRecord> HexArcsRecordHashMap;


    public HexArcs(File vXmlFile) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document docXML = dBuilder.parse(vXmlFile);

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr4HexArcs = xpath.compile("/CONFIGURATION/DATA/TABLE[NAME='HEX_ARCS']/ROWSET/ROW");
            NodeList nl4HexArcs = (NodeList) expr4HexArcs.evaluate(docXML, XPathConstants.NODESET);

         //   System.out.println("First  Document. Number of HEX ArcRelation: " + nl4HexArcRelation.getLength());

            // читаем потроха команд из первого файла
            HexArcsRecordHashMap  = new HashMap<String, HexArcRecord>();
            buildArcsMap(HexArcsRecordHashMap, nl4HexArcs);
            // читаем потроха команд из второго файла
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // internal

    // пихаем все потроха команд в MAP
    private void buildArcsMap (HashMap<String, HexArcRecord> iMap, NodeList iNodeList) {

        String HARC_ID   = "";
        String CRITICAL_FLAG   = "";
        String ERROR_IGNORE_FLAG   = "";
        String FINAL_FLAG   = "";
        String PRINT_OUT_TAG   = "";
        String PRINT_OUT_FLAG   = "";


     //   System.out.println(iNodeList.getLength());

        for (int i=0 ; i<iNodeList.getLength(); i++) {

            NodeList currentItem = iNodeList.item(i).getChildNodes();
            for (int j=0; j < currentItem.getLength(); j++) {
                Node currentElement =  currentItem.item(j);

                if (currentElement.getNodeName().equals("HARC_ID"))
                {HARC_ID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("CRITICAL_FLAG"))
                {CRITICAL_FLAG = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("PRINT_OUT_TAG"))
                {CRITICAL_FLAG = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("FINAL_FLAG"))
                {FINAL_FLAG = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("ERROR_IGNORE_FLAG"))
                {ERROR_IGNORE_FLAG = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("PRINT_OUT_FLAG"))
                {PRINT_OUT_FLAG = currentElement.getTextContent();}

            }
            iMap.put(HARC_ID,new HexArcRecord(
                   HARC_ID,
                    CRITICAL_FLAG,
                    FINAL_FLAG,
                    ERROR_IGNORE_FLAG,
                    PRINT_OUT_FLAG,
                    PRINT_OUT_TAG
                    )
            );
        }

    }

    // public

    public HashMap<String, HexArcRecord> getHexArcsRecordHashMap() {
        return HexArcsRecordHashMap;
    }

    public HexArcRecord getArcRecordByGUID (String iHARC_HARC_ID) {
        return HexArcsRecordHashMap.get(iHARC_HARC_ID);
    }


}

package ru.billing.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ru.billing.hextypes.HexPredicateRecord;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.HashMap;

public class HexPredicates {


    private HashMap<String, HexPredicateRecord> HexPredicateRecordHashMap;

    public HexPredicates(File vXmlFile) {
       // System.out.println("================================");
       // System.out.println(this.toString()+". Read XML files");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document docXML = dBuilder.parse(vXmlFile);


            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr4HexPredicates = xpath.compile("/CONFIGURATION/DATA/TABLE[NAME='HEX_PREDICATES']/ROWSET/ROW");
            NodeList nl4HexPredicates = (NodeList) expr4HexPredicates.evaluate(docXML, XPathConstants.NODESET);

          //  System.out.println("First  Document. Number of HEX Predicates: " + nl4HexPredicates.getLength());
            // читаем потроха команд из первого файла
            HexPredicateRecordHashMap  = new HashMap<String, HexPredicateRecord>();
            buildPredicatesMap(HexPredicateRecordHashMap, nl4HexPredicates);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // internal

    // пихаем все потроха команд в MAP
    private void buildPredicatesMap (HashMap<String, HexPredicateRecord> iMap, NodeList iNodeList) {

        String ENTITY_GUID   = "";
        String HPRC_HPRC_ID   = "";
        String HPRT_HPRT_ID  = "";
        String NEGATION     ="";
        String PREDICATE     = "";
        String SDCT_SDCT_ID  = "";

        for (int i=0 ; i<iNodeList.getLength(); i++) {
             ENTITY_GUID   = "";
             HPRC_HPRC_ID   = "";
             HPRT_HPRT_ID  = "";
             NEGATION     ="";
             PREDICATE     = "";
             SDCT_SDCT_ID  = "";

            NodeList currentItem = iNodeList.item(i).getChildNodes();
            for (int j=0; j < currentItem.getLength(); j++) {
                Node currentElement =  currentItem.item(j);

                if (currentElement.getNodeName().equals("ENTITY_GUID"))
                {ENTITY_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("HPRC_HPRC_ID"))
                {HPRC_HPRC_ID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("HPRT_HPRT_ID"))
                {HPRT_HPRT_ID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("NEGATION"))
                {NEGATION = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("PREDICATE"))
                {PREDICATE = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("PREDICATE_CLOB"))
                {PREDICATE = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("SDCT_SDCT_ID"))
                {SDCT_SDCT_ID = currentElement.getTextContent();}

            }

            iMap.put(ENTITY_GUID,new HexPredicateRecord(
                            ENTITY_GUID,
                            HPRC_HPRC_ID,
                            HPRT_HPRT_ID,
                            NEGATION,
                            PREDICATE,
                            SDCT_SDCT_ID
                    )
            );
        }

    }


    // public
    public  HashMap<String, HexPredicateRecord> getHexPredicates() {
        return HexPredicateRecordHashMap;
    }
    public HexPredicateRecord getHexPredicateRecordByGUID (String GUID) {
        return HexPredicateRecordHashMap.get(GUID);
    }

}

package ru.billing.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.billing.hextypes.HexCommandRecord;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.HashMap;

public class HexCommands {


    private HashMap<String, HexCommandRecord> HexCommandsHashMap;


    public HexCommands(File vXmlFile) {
      //  System.out.println("================================");
      //  System.out.println(this.toString()+". Read XML files");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document docXML = dBuilder.parse(vXmlFile);

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr4HexCommandHistories = xpath.compile("/CONFIGURATION/DATA/TABLE[NAME='HEX_COMMANDS']/ROWSET/ROW");
            NodeList nl4HexCommands = (NodeList) expr4HexCommandHistories.evaluate(docXML, XPathConstants.NODESET);

         //   System.out.println("First  Document. Number of HEX Commands: " + nl4HexCommands.getLength());
            // читаем потроха команд из первого файла
            HexCommandsHashMap  = new HashMap<String, HexCommandRecord>();
            buildCommandsMap(HexCommandsHashMap, nl4HexCommands);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // internal

    // пихаем все потроха команд в MAP
    private void buildCommandsMap (HashMap<String, HexCommandRecord> iMap, NodeList iNodeList) {

        String ENTITY_GUID = "";
        String NAME = "";
        String CODE = "";
        String SDCT_SDCT_ID = "";
        String PERSISTENT_CONNECTIONS_FLAG = "";
        String INTERNAL_COMMAND = "";

        for (int i=0 ; i<iNodeList.getLength(); i++) {

            NodeList currentItem = iNodeList.item(i).getChildNodes();
            for (int j=0; j < currentItem.getLength(); j++) {
                Node currentElement =  currentItem.item(j);

                if (currentElement.getNodeName().equals("ENTITY_GUID"))
                {ENTITY_GUID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("NAME"))
                {NAME = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("CODE"))
                {CODE = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("SDCT_SDCT_ID"))
                {SDCT_SDCT_ID = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("PERSISTENT_CONNECTIONS_FLAG"))
                {PERSISTENT_CONNECTIONS_FLAG = currentElement.getTextContent();}

                if (currentElement.getNodeName().equals("INTERNAL_COMMAND"))
                {INTERNAL_COMMAND = currentElement.getTextContent();}

            }
            iMap.put(ENTITY_GUID,new HexCommandRecord(
                     ENTITY_GUID,
                     NAME,
                     CODE,
                     SDCT_SDCT_ID,
                     PERSISTENT_CONNECTIONS_FLAG,
                     INTERNAL_COMMAND
                    )
            );
        }

    }


    // public
    public  HashMap<String, HexCommandRecord> getHexCommands() {
        return HexCommandsHashMap;
    }

    public HexCommandRecord getHexCommandHistoryRecordByGUID (String GUID) {
        return HexCommandsHashMap.get(GUID);
    }


}

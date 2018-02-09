package ru.billing.checkers;

import ru.billing.XMLReader;
import ru.billing.hextypes.HexRequestRecord;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class CheckUnusedRequests {

    XMLReader xmlreader;


    private void printSwimlane () {
        System.out.println("==---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------==");
    }

    public CheckUnusedRequests(XMLReader xmlreader) {
        this.xmlreader = xmlreader;
        printSwimlane ();
        System.out.println("Warning! Potential Garbage! Here are requests which are nowhere used in the current collection");
        System.out.println("Please check list of requests. Remember, they can be used in commands which are not included in this collection");
        printSwimlane ();
        findUnusedRequests ();
    }



    private void findUnusedRequests () {
        Set set;
        Iterator iterator;
        set = xmlreader.getSecondHexRequests().getHexRequests().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexRequestRecord hreq = (HexRequestRecord) mentry.getValue();
             if (xmlreader.getSecondCommandsByRequestGUID(hreq.getENTITY_GUID()).isEmpty()) {System.out.println("Request: "+hreq.getSDCT_SDCT_ID());}

        }
    }

}

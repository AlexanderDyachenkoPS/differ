package ru.billing.checkers;

import ru.billing.XMLReader;
import ru.billing.hextypes.HexArgumentRecord;
import ru.billing.hextypes.HexRequestRecord;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class CheckUnbindedArguments {

    XMLReader xmlreader;

    public CheckUnbindedArguments(XMLReader xmlreader) {
        this.xmlreader = xmlreader;
        findUnbindedArguments ();
    }

    private boolean isArgumentBindedRequest (String iReqGUID, String iArgName) {
        return xmlreader.getSecondHexRequestArguments().getHexRequestArgumentRecordHashMap().containsKey(iReqGUID+iArgName);
    }

    private ArrayList<String> findUnbindRequestsByArgument (String iArg) {
        ArrayList<String> reqs = new ArrayList<String>();
        Set set;
        Iterator iterator;
        set = xmlreader.getSecondHexRequests().getHexRequests().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexRequestRecord hreq = (HexRequestRecord) mentry.getValue();
            if (
                    hreq.getREQUEST().contains(iArg) &&
                            (!isArgumentBindedRequest(hreq.getENTITY_GUID(),iArg))
               ) {

                System.out.println(hreq.getSDCT_SDCT_ID());
            }
        }
        return reqs;
    }

    private void findUnbindedArguments () {
        Set set;
        Iterator iterator;
        set = xmlreader.getSecondHexArguments().getHexArgumentRecordHashMap().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexArgumentRecord harg = (HexArgumentRecord) mentry.getValue();
            if (!(harg.getTRANSFORMER().isEmpty())) {
                    System.out.println(harg.getNAME()+"  >> "+harg.getSDCT_SDCT_ID() +" >>"+ harg.getTRANSFORMER());
                    ArrayList<String> reqs = findUnbindRequestsByArgument(harg.getNAME());

            }
        }
    }

}

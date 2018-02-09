package ru.billing.checkers;

import ru.billing.XMLReader;
import ru.billing.hextypes.HexArgumentRecord;
import ru.billing.hextypes.HexRequestRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class CheckUnbindedArguments {

    XMLReader xmlreader;


    private void printSwimlane () {
        System.out.println("==---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------==");
    }

    public CheckUnbindedArguments(XMLReader xmlreader) {
        this.xmlreader = xmlreader;
        printSwimlane ();
        System.out.println("Warning! Potential BUGS! Here are arguments which have a transformer. If these arguments are used in any requests, they MUST be bonded together. ");
        System.out.println("Please attentively check list of arguments and requests in which these arguments are used but not bonded with corresponding requests");
        printSwimlane ();
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
                reqs.add(hreq.getSDCT_SDCT_ID());
               // System.out.println("Request: " + hreq.getSDCT_SDCT_ID());
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
                   // System.out.println("Argument: " + harg.getNAME()+"  >> "+harg.getSDCT_SDCT_ID() +" >>"+ harg.getTRANSFORMER());
                    ArrayList<String> reqs = findUnbindRequestsByArgument(harg.getNAME());
                    if (reqs.size()>0) {
                        System.out.println("Argument: NAME=" + harg.getNAME()+"  Description="+harg.getSDCT_SDCT_ID() +" Transformer="+ harg.getTRANSFORMER());
                        String[] reqsArray ;
                        reqsArray = reqs.toArray(new String[reqs.size()]);
                        for (int j=0; j<reqsArray.length; j++) System.out.println("     |_>>> Please check if argument is bonded to this request :" + reqsArray[j]);
                        printSwimlane ();
                    }

            }

        }
    }

}

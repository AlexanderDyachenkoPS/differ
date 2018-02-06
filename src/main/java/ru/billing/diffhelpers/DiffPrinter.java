package ru.billing.diffhelpers;


import ru.billing.XMLReader;
import ru.billing.differs.*;
import ru.billing.hextypes.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DiffPrinter {

    XMLReader vxmlReader;

    public DiffPrinter (XMLReader iXMLReader) {
        this.vxmlReader=iXMLReader;
    }

    private void printSwimlane () {
        System.out.println("==---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------==");
    }

    public void printHeaders (Headr ihdr) {
        System.out.println(String.format("%50s", "") + "Compare collections");
        printSwimlane();
        System.out.println(String.format("%-100s",ihdr.getfHeadr().getCOLLECTION_FILENAME())+ihdr.getsHeadr().getCOLLECTION_FILENAME()
        );


        System.out.println(String.format("%-100s",ihdr.getfHeadr().getCOLLECTION_NAME())+ihdr.getsHeadr().getCOLLECTION_NAME()
        );


        System.out.println(String.format("%-100s",ihdr.getfHeadr().getCOLLECTION_UID())+ihdr.getsHeadr().getCOLLECTION_UID()
        );


        System.out.println(String.format("%-100s",ihdr.getfHeadr().getCOLLECTION_VERSION())+ihdr.getsHeadr().getCOLLECTION_VERSION()
        );

        printSwimlane();
    }


    public void printArgumentsDiff (
            DiffArguments dArg
    ) {

        System.out.println(String.format("%50s", "") + "Compare Arguments");
        printSwimlane();
        System.out.println(String.format("%50s", "") + "Added or removed Arguments");
        printSwimlane();
        Set set;
        Iterator iterator;
        set = dArg.getLeftOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();

            HexArgumentRecord harg = (HexArgumentRecord) mentry.getValue();
            System.out.println(harg.getSDCT_SDCT_ID());
        }
        set = dArg.getRightOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexArgumentRecord harg = (HexArgumentRecord) mentry.getValue();
            System.out.println(String.format("%100s", "") +  harg.getSDCT_SDCT_ID());
        }
        printSwimlane();
        System.out.println(String.format("%50s", "") + "Changed Arguments");
        printSwimlane();
        set = dArg.getfreqDiffs().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexArgumentRecord harg = (HexArgumentRecord) mentry.getValue();
            System.out.println(String.format("%50s", "") + harg.getSDCT_SDCT_ID());

        }
        printSwimlane();
    }

    public void printReqestsDiff (
                                    DiffRequests dReq
                                 ) {

        System.out.println(String.format("%50s", "") + "Compare Requests");
        printSwimlane();
        System.out.println(String.format("%50s", "") + "Added or removed Requests");
        printSwimlane();
        Set set;
        Iterator iterator;
        set = dReq.getLeftOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            //System.out.println(mentry.getKey());
            HexRequestRecord hreq = (HexRequestRecord) mentry.getValue();
            System.out.println(hreq.getSDCT_SDCT_ID());
        }
        set = dReq.getRightOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexRequestRecord hreq = (HexRequestRecord) mentry.getValue();
            System.out.println(String.format("%100s", "") +  hreq.getSDCT_SDCT_ID());
        }
        printSwimlane();
        System.out.println(String.format("%50s", "") + "Changed Requests");
        printSwimlane();
        set = dReq.getfreqDiffs().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexRequestRecord hreq = (HexRequestRecord) mentry.getValue();
            System.out.println(String.format("%50s", "") + hreq.getSDCT_SDCT_ID());

        }
        printSwimlane();
    }


    public void printPredicatesDiff (
            DiffPredicates dPrd
    ) {
        System.out.println(String.format("%50s", "") + "Compare Predicates");
        printSwimlane();

        System.out.println(String.format("%50s", "") + "Added or removed Predicates");
        printSwimlane();
        Set set;
        Iterator iterator;
        set = dPrd.getLeftOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexPredicateRecord hprd = (HexPredicateRecord) mentry.getValue();
            System.out.println(hprd.getSDCT_SDCT_ID());
        }

        set = dPrd.getRightOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexPredicateRecord hprd = (HexPredicateRecord) mentry.getValue();
            System.out.println(String.format("%100s", "") +  hprd.getSDCT_SDCT_ID());
        }

        printSwimlane();
        System.out.println(String.format("%50s", "") + "Changed Predicates");
        printSwimlane();
        set = dPrd.getfprdDiffs().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexPredicateRecord hprd = (HexPredicateRecord) mentry.getValue();
            System.out.println(String.format("%50s", "") + hprd.getSDCT_SDCT_ID());
        }

    }

    public void printCommandsDiff (
            DiffCommands dCom
    ) {
        System.out.println(String.format("%50s", "") + "Compare Commands ");
        printSwimlane();
        System.out.println(String.format("%50s", "") + "Added or removed Commands");
        printSwimlane();
        Set set;
        Iterator iterator;
        set = dCom.getLeftOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexCommandRecord hcmd = (HexCommandRecord) mentry.getValue();
            System.out.println(hcmd.getNAME());
        }

        set = dCom.getRightOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexCommandRecord hcmd = (HexCommandRecord) mentry.getValue();
            System.out.println(String.format("%100s", "") +  hcmd.getNAME());
        }

        printSwimlane();
        System.out.println(String.format("%50s", "") + "Changed Commands");
        printSwimlane();

        set = dCom.getfcomDiffs().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexCommandRecord hcmd = (HexCommandRecord) mentry.getValue();
            System.out.println(String.format("%50s", "") + hcmd.getNAME());

        }
    }

    public void printCommandAlgorothmsDiff (
            DiffCommandAlgs dCom
    ) {


        Set set;
        Iterator iterator;
        System.out.println(String.format("%50s", "") + "Changed Command Algorithms");
        printSwimlane();
        set = dCom.getfcomDiffs().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexCommanHistoryRecord halg = (HexCommanHistoryRecord) mentry.getValue();
           // vxmlReader.getFirstHexCommands().getHexCommands().get(halg.getENTITY_GUID()).getNAME()
            System.out.println(String.format("%50s", "") + vxmlReader.getFirstHexCommands().getHexCommands().get(halg.getENTITY_GUID()).getNAME()
            );

        }
    }
}

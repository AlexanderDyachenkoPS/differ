package ru.billing.checkers;

import ru.billing.XMLReader;
import ru.billing.hextypes.HexPredicateRecord;
import ru.billing.hextypes.HexRequestRecord;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class CheckUnusedPredicates {

    XMLReader xmlreader;


    private void printSwimlane () {
        System.out.println("==---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------==");
    }

    public CheckUnusedPredicates(XMLReader xmlreader) {
        this.xmlreader = xmlreader;
        printSwimlane ();
        System.out.println("Warning! Potential Garbage! Here are predicates which are nowhere used in the current collection");
        System.out.println("Please check list of predicates. Remember, they can be used in commands which are not included in this collection");
        printSwimlane ();
        findUnusedPredicates ();
    }



    private void findUnusedPredicates () {
        Set set;
        Iterator iterator;
        set = xmlreader.getSecondHexPredicates().getHexPredicates().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HexPredicateRecord hprd = (HexPredicateRecord) mentry.getValue();
             if (
                     (
                     (hprd.getHPRC_HPRC_ID().equals("1") || hprd.getHPRC_HPRC_ID().equals("2")) &&
                     xmlreader.getSecondCommandsByPredicateGUID(hprd.getENTITY_GUID()).isEmpty()
                     )
                ) {System.out.println("Predicate: "+hprd.getSDCT_SDCT_ID());}

        }
    }

}

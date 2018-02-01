package ru.billing.diffhelpers;


import ru.billing.differs.DiffCommandAlgs;
import ru.billing.differs.DiffCommands;
import ru.billing.differs.DiffPredicates;
import ru.billing.differs.DiffRequests;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DiffPrinter {

    public void printReqestsDiff (
                                    DiffRequests dReq
                                 ) {
        System.out.println("Requests that were deleted : " + dReq.getLeftOuter().size());
        System.out.println("--------------------");
        Set set;
        Iterator iterator;
        set = dReq.getLeftOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("- Request : "+ mentry.getKey());
        }
        System.out.println("Requests that were added : " + dReq.getRightOuter().size());
        System.out.println("---------------------");
        set = dReq.getRightOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("+ Request : "+ mentry.getKey());
        }

        System.out.println("Requests that were changed : " + dReq.getfreqDiffs().size());
        System.out.println("---------------------");
        set = dReq.getfreqDiffs().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("/ Request : "+ mentry.getKey());
        }
    }


    public void printPredicatesDiff (
            DiffPredicates dPrd
    ) {
        System.out.println("Predicates that were deleted : " + dPrd.getLeftOuter().size());
        System.out.println("--------------------");
        Set set;
        Iterator iterator;
        set = dPrd.getLeftOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("- Predicate : "+ mentry.getKey());
        }
        System.out.println("Predicates that were added : " + dPrd.getRightOuter().size());
        System.out.println("---------------------");
        set = dPrd.getRightOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("+ Predicate : "+ mentry.getKey());
        }

        System.out.println("Predicates that were changed : " + dPrd.getfprdDiffs().size());
        System.out.println("---------------------");
        set = dPrd.getfprdDiffs().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("/ Predicate : "+ mentry.getKey());
        }
    }

    public void printCommandsDiff (
            DiffCommands dCom
    ) {
        System.out.println("Commands that were deleted : " + dCom.getLeftOuter().size());
        System.out.println("--------------------");
        Set set;
        Iterator iterator;
        set = dCom.getLeftOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("- Command : "+ mentry.getKey());
        }
        System.out.println("Commands that were added : " + dCom.getRightOuter().size());
        System.out.println("---------------------");
        set = dCom.getRightOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("+ Command : "+ mentry.getKey());
        }

        System.out.println("Commands that were changed : " + dCom.getfcomDiffs().size());
        System.out.println("---------------------");
        set = dCom.getfcomDiffs().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("/ Command : "+ mentry.getKey());
        }
    }

    public void printCommandAlgorothmsDiff (
            DiffCommandAlgs dCom
    ) {


        Set set;
        Iterator iterator;
        System.out.println("Command Algorithms that were changed : " + dCom.getfcomDiffs().size());
        System.out.println("---------------------");
        set = dCom.getfcomDiffs().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("/ Command's Algorithm : "+ mentry.getKey());
        }
    }
}

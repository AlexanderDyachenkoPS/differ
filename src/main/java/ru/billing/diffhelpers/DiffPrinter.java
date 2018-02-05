package ru.billing.diffhelpers;


import ru.billing.differs.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DiffPrinter {

    private void printSwimlane () {
        System.out.println("==-------------------------------------------------------------------------------------==");
    }

    public void printHeaders (Headr ihdr) {
        System.out.println("Compare collections");
        printSwimlane();
        System.out.print(String.format("%-50s",ihdr.getfHeadr().getCOLLECTION_FILENAME()));
        System.out.println(String.format("%-50s",ihdr.getsHeadr().getCOLLECTION_FILENAME()));

        System.out.print(String.format("%-50s",ihdr.getfHeadr().getCOLLECTION_NAME()));
        System.out.println(String.format("%-50s",ihdr.getsHeadr().getCOLLECTION_NAME()));

        System.out.print(String.format("%-50s",ihdr.getfHeadr().getCOLLECTION_UID()));
        System.out.println(String.format("%-50s",ihdr.getsHeadr().getCOLLECTION_UID()));

        System.out.print(String.format("%-50s",ihdr.getfHeadr().getCOLLECTION_VERSION()));
        System.out.println(String.format("%-50s",ihdr.getsHeadr().getCOLLECTION_VERSION()));
        printSwimlane();
    }

    public void printReqestsDiff (
                                    DiffRequests dReq
                                 ) {

        System.out.println("Compare Requests");
        printSwimlane();
        Set set;
        Iterator iterator;
        set = dReq.getLeftOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println(mentry.getKey());
        }
        set = dReq.getRightOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println(String.format("%82s", mentry.getKey()));
        }

        set = dReq.getfreqDiffs().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();

            System.out.print(String.format("%-50s",mentry.getKey()));
            System.out.println(String.format("%-50s",mentry.getKey()));

        }
        printSwimlane();
    }


    public void printPredicatesDiff (
            DiffPredicates dPrd
    ) {
        System.out.println("Compare Predicates");
        printSwimlane();
        Set set;
        Iterator iterator;
        set = dPrd.getLeftOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println( mentry.getKey());
        }

        set = dPrd.getRightOuter().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println(String.format("%82s", mentry.getKey()));
        }


        set = dPrd.getfprdDiffs().entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.print(String.format("%-50s",mentry.getKey()));
            System.out.println(String.format("%-50s",mentry.getKey()));
        }
        printSwimlane();
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

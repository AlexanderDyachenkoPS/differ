package ru.billing;


import ru.billing.XMLReader;
import ru.billing.checkers.CheckUnbindedArguments;
import ru.billing.checkers.CheckUnusedPredicates;
import ru.billing.checkers.CheckUnusedRequests;

public class Checker {

    XMLReader xmlreader;

    Checker(XMLReader iXMLReader) {
        this.xmlreader = iXMLReader;
        printHeader ();
        runChecks();
    }


    private void printSwimlane () {
        System.out.println("==---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------==");
    }

    private void printHeader () {
        printSwimlane ();
        System.out.println("Checks and Warnings");
        printSwimlane ();
    }

    private void runChecks () {

        CheckUnbindedArguments checkUnbindedArguments = new CheckUnbindedArguments(xmlreader);
        CheckUnusedRequests checkUnusedRequests       = new CheckUnusedRequests(xmlreader);
        CheckUnusedPredicates checkUnusedPredicates       = new CheckUnusedPredicates(xmlreader);
    }

}

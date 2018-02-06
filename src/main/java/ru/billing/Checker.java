package ru.billing;


import ru.billing.XMLReader;
import ru.billing.checkers.CheckUnbindedArguments;
import ru.billing.differs.*;
import ru.billing.diffhelpers.DiffPrinter;

public class Checker {

    XMLReader xmlreader;

    Checker(XMLReader iXMLReader) {
        this.xmlreader = iXMLReader;
        runChecks();
    }

    private void runChecks () {
       CheckUnbindedArguments checkUnbindedArguments = new CheckUnbindedArguments(xmlreader);
    }

}

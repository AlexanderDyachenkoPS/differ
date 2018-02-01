package ru.billing;


import ru.billing.differs.DiffCommandAlgs;
import ru.billing.differs.DiffCommands;
import ru.billing.differs.DiffPredicates;
import ru.billing.differs.DiffRequests;
import ru.billing.diffhelpers.DiffPrinter;

public class Comparer {

    XMLReader xmlreader;

///// DIFFS BEGIN
    DiffRequests        dReq;
    DiffPredicates      dPrd;
    DiffCommands        dCom;
    DiffCommandAlgs     dAlg;
///// DIFFS END


    Comparer (XMLReader iXMLReader) {
        this.xmlreader = iXMLReader;
        diffMaps();
        diffPrint();
    }



    private void diffMaps () {

        dReq = new DiffRequests();
        dReq.findDiffRequests(
                xmlreader.getFirstHexRequests().getHexRequests(),
                xmlreader.getSecondHexRequests().getHexRequests()
        );

        dPrd = new DiffPredicates();
        dPrd.findDiffPredicates(
                xmlreader.getFirstHexPredicates().getHexPredicates(),
                xmlreader.getSecondHexPredicates().getHexPredicates()
        );

        dCom = new DiffCommands();
        dCom.findDiffCommands(xmlreader.getFirstHexCommands().getHexCommands(),xmlreader.getSecondHexCommands().getHexCommands());

        dAlg = new DiffCommandAlgs(
                xmlreader.getFirstHexRequests(),
                xmlreader.getSecondHexRequests(),
                xmlreader.getFirstHexPredicates(),
                xmlreader.getSecondHexPredicates()
        );
        dAlg.findDiffCommandAlorithms(
                xmlreader.getFirstHexCommandHistories().getHexCommandHistories(),
                xmlreader.getSecondHexCommandHistories().getHexCommandHistories()
        );
    }

    private void diffPrint () {

        DiffPrinter dPrint = new DiffPrinter ();
        dPrint.printReqestsDiff(dReq);
        dPrint.printPredicatesDiff(dPrd);
        dPrint.printCommandsDiff(dCom);
        dPrint.printCommandAlgorothmsDiff(dAlg);
    }
}

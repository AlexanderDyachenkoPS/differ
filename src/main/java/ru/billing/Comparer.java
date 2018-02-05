package ru.billing;


import ru.billing.differs.*;
import ru.billing.diffhelpers.DiffPrinter;

public class Comparer {

    XMLReader xmlreader;

///// DIFFS BEGIN
    Headr hdr;


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

        hdr = new Headr (xmlreader.getFirstHexCollectionHeader(),
                         xmlreader.getSecondHexCollectionHeader()
        );

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
        dPrint.printHeaders(hdr);
        dPrint.printReqestsDiff(dReq);
        dPrint.printPredicatesDiff(dPrd);
    //    dPrint.printCommandsDiff(dCom);
    //    dPrint.printCommandAlgorothmsDiff(dAlg);
    }
}

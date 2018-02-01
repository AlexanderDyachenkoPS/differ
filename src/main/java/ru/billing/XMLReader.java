package ru.billing;




import ru.billing.hextypes.HexCommanHistoryRecord;
import ru.billing.readers.*;


import java.io.File;
import java.util.HashMap;

public class XMLReader {

    File firstXmlFile;
    File secondXmlFile;

    ///// MAPS BEGIN
    HexRequests     firstHexRequests;
    HexRequests     secondHexRequests;

    HexPredicates     firstHexPredicates;
    HexPredicates     secondHexPredicates;

    HexCommandHistories firstHexCommandHistories;
    HexCommandHistories secondHexCommandHistories;

    HexArcRelation  firstHexArcRelation;
    HexArcRelation  secondHexArcRelation;

    HexCommandGraph firstHexCommandGraph;
    HexCommandGraph secondHexCommandGraph;

    HexArcRequest firstHexArcRequest;
    HexArcRequest secondHexArcRequest;

    HexCommands firstHexCommands;
    HexCommands secondHexCommands;
///// MAPS END

/*///// DIFFS BEGIN
    DiffRequests   dReq;
    DiffPredicates dPrd;
    DiffCommands   dCom;
    DiffCommandAlgs   dAlg;
///// DIFFS END*/


   /* GeneralHelpers gH;*/

    XMLReader(File vfirstXmlFile, File vsecondXmlFile) {

        this.firstXmlFile   =   vfirstXmlFile;
        this.secondXmlFile  =   vsecondXmlFile;
        readFiles();

        /*diffMaps();
        diffPrint();*/
    }

    // private
    private void readFiles () {
        this.firstHexRequests  = new HexRequests(firstXmlFile);
        this.secondHexRequests = new HexRequests(secondXmlFile);

        this.firstHexPredicates  = new HexPredicates(firstXmlFile);
        this.secondHexPredicates = new HexPredicates(secondXmlFile);

        this.firstHexCommandHistories  = new HexCommandHistories(firstXmlFile);
        this.secondHexCommandHistories = new HexCommandHistories(secondXmlFile);

        this.firstHexArcRelation  = new HexArcRelation(firstXmlFile);
        this.secondHexArcRelation = new HexArcRelation(secondXmlFile);

        this.firstHexArcRequest  = new HexArcRequest(firstXmlFile);
        this.secondHexArcRequest = new HexArcRequest(secondXmlFile);

        this.firstHexCommandGraph  = new HexCommandGraph(
                this.firstHexCommandHistories.getHexCommandHistories(),
                this.firstHexArcRelation.getHexArcRelation(),
                this.firstHexRequests,
                this.firstHexPredicates,
                this.firstHexArcRequest
        );
        this.secondHexCommandGraph = new HexCommandGraph(
                this.secondHexCommandHistories.getHexCommandHistories(),
                this.secondHexArcRelation.getHexArcRelation(),
                this.secondHexRequests,
                this.secondHexPredicates,
                this.secondHexArcRequest
        );

        this.firstHexCommands  = new HexCommands(firstXmlFile);
        this.secondHexCommands = new HexCommands(secondXmlFile);

    }

////////////////getters

    public HexRequests getFirstHexRequests() {
        return firstHexRequests;
    }

    public HexRequests getSecondHexRequests() {
        return secondHexRequests;
    }

    public HexPredicates getFirstHexPredicates() {
        return firstHexPredicates;
    }

    public HexPredicates getSecondHexPredicates() {
        return secondHexPredicates;
    }

    public HexCommandHistories getFirstHexCommandHistories() {
        return firstHexCommandHistories;
    }

    public HexCommandHistories getSecondHexCommandHistories() {
        return secondHexCommandHistories;
    }

    public HexArcRelation getFirstHexArcRelation() {
        return firstHexArcRelation;
    }

    public HexArcRelation getSecondHexArcRelation() {
        return secondHexArcRelation;
    }

    public HexCommandGraph getFirstHexCommandGraph() {
        return firstHexCommandGraph;
    }

    public HexCommandGraph getSecondHexCommandGraph() {
        return secondHexCommandGraph;
    }

    public HexArcRequest getFirstHexArcRequest() {
        return firstHexArcRequest;
    }

    public HexArcRequest getSecondHexArcRequest() {
        return secondHexArcRequest;
    }

    public HexCommands getFirstHexCommands() {
        return firstHexCommands;
    }

    public HexCommands getSecondHexCommands() {
        return secondHexCommands;
    }

    public HashMap<String,HexCommanHistoryRecord> getFirstCommandsByRequestGUID (String GUID) { return null;

    }
    public HashMap<String,HexCommanHistoryRecord> getSecondCommandsByRequestGUID (String GUID) {return null;


    }
    public HashMap<String,HexCommanHistoryRecord> getFirstCommandsByPredicateGUID (String GUID) {return null;


    }
    public HashMap<String,HexCommanHistoryRecord> getSecondCommandsByPredicateGUID (String GUID) {return null;


    }

}

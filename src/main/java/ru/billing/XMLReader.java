package ru.billing;




import ru.billing.hextypes.HexArcRequestRecord;
import ru.billing.hextypes.HexCommanHistoryRecord;
import ru.billing.hextypes.HexCommandRecord;
import ru.billing.readers.*;


import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class XMLReader {

    File firstXmlFile;
    File secondXmlFile;

    ///// MAPS BEGIN
    HexRequests firstHexRequests;
    HexRequests secondHexRequests;

    HexPredicates firstHexPredicates;
    HexPredicates secondHexPredicates;

    HexCommandHistories firstHexCommandHistories;
    HexCommandHistories secondHexCommandHistories;

    HexArcRelation firstHexArcRelation;
    HexArcRelation secondHexArcRelation;

    HexCommandGraph firstHexCommandGraph;
    HexCommandGraph secondHexCommandGraph;

    HexArcRequest firstHexArcRequest;
    HexArcRequest secondHexArcRequest;

    HexCommands firstHexCommands;
    HexCommands secondHexCommands;


    HexArguments firstHexArguments;
    HexArguments secondHexArguments;


    HexRequestArguments firstHexRequestArguments;
    HexRequestArguments secondHexRequestArguments;


    HexCollectionHeader firstHexCollectionHeader;
    HexCollectionHeader secondHexCollectionHeader;
///// MAPS END

/*///// DIFFS BEGIN
    DiffRequests   dReq;
    DiffPredicates dPrd;
    DiffCommands   dCom;
    DiffCommandAlgs   dAlg;
///// DIFFS END*/


   /* GeneralHelpers gH;*/

    XMLReader(File vfirstXmlFile, File vsecondXmlFile) {

        this.firstXmlFile = vfirstXmlFile;
        this.secondXmlFile = vsecondXmlFile;
        readFiles();
        getFirstCommandsByRequestGUID("DA20F5C991D345289C84751488EDF6D6");
        getFirstCommandsByRequestGUID("F03554B75B8D4B4CB98287E44CBB23C9");
System.out.println("=======================>>>>>>>>>>>>>>>>>>>>>>>>>");
        getSecondCommandsByRequestGUID("DA20F5C991D345289C84751488EDF6D6");
        getSecondCommandsByRequestGUID("F03554B75B8D4B4CB98287E44CBB23C9");
        System.out.println("=======================>>>>>>>>>>>>>>>>>>>>>>>>>");
        getFirstCommandsByPredicateGUID("196C63182CAB632AE053A111630A8E5E");
        getSecondCommandsByPredicateGUID("196C63182CAB632AE053A111630A8E5E");

        System.out.println(getFirstHexCollectionHeader().getCOLLECTION_NAME());
        System.out.println(getSecondHexCollectionHeader().getCOLLECTION_NAME());
        /*diffMaps();
        diffPrint();*/
    }

    // private
    private void readFiles() {
        this.firstHexRequests = new HexRequests(firstXmlFile);
        this.secondHexRequests = new HexRequests(secondXmlFile);

        this.firstHexPredicates = new HexPredicates(firstXmlFile);
        this.secondHexPredicates = new HexPredicates(secondXmlFile);

        this.firstHexCommandHistories = new HexCommandHistories(firstXmlFile);
        this.secondHexCommandHistories = new HexCommandHistories(secondXmlFile);

        this.firstHexArcRelation = new HexArcRelation(firstXmlFile);
        this.secondHexArcRelation = new HexArcRelation(secondXmlFile);

        this.firstHexArcRequest = new HexArcRequest(firstXmlFile);
        this.secondHexArcRequest = new HexArcRequest(secondXmlFile);

        this.firstHexCommandGraph = new HexCommandGraph(
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

        this.firstHexCommands = new HexCommands(firstXmlFile);
        this.secondHexCommands = new HexCommands(secondXmlFile);

        this.firstHexArguments = new HexArguments(firstXmlFile);
        this.secondHexArguments = new HexArguments(secondXmlFile);

        this.firstHexRequestArguments = new HexRequestArguments(firstXmlFile);
        this.secondHexRequestArguments = new HexRequestArguments(secondXmlFile);

        this.firstHexCollectionHeader = new HexCollectionHeader(firstXmlFile);
        this.secondHexCollectionHeader = new HexCollectionHeader(secondXmlFile);

    }

////////////////getters


    public HexArguments getFirstHexArguments() {
        return firstHexArguments;
    }

    public HexArguments getSecondHexArguments() {
        return secondHexArguments;
    }

    public HexRequestArguments getFirstHexRequestArguments() {
        return firstHexRequestArguments;
    }

    public HexRequestArguments getSecondHexRequestArguments() {
        return secondHexRequestArguments;
    }

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

    public HexCollectionHeader getFirstHexCollectionHeader() {
        return firstHexCollectionHeader;
    }

    public HexCollectionHeader getSecondHexCollectionHeader() {
        return secondHexCollectionHeader;
    }

    public String getFirstCommandByHARC_HARC_ID(String iHARC_HARC_ID) {
        return this.firstHexCommandGraph.getCmdRootHarcByHARC_HARC_ID(iHARC_HARC_ID);
    }


    public HashMap<String, HexCommanHistoryRecord> getFirstCommandsByRequestGUID(String iGUID) {
        System.out.println("Request GUID: ===============");
        System.out.println("Request GUID: " + iGUID);

        HashMap<String, HexCommanHistoryRecord> cmds = new HashMap<String, HexCommanHistoryRecord>();
        String HARC_HARC_ID;
        String cmdHARC_HARC_ID;
        String cmdGUID;
        //смотрим по HEX_ARC_REQUESTS
        Set set;
        set = getFirstHexArcRequest().getHexArcRequest().entrySet();
        Iterator iterator = set.iterator();
        ;
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HARC_HARC_ID = mentry.getKey().toString();
            HexArcRequestRecord harcRecord = (HexArcRequestRecord) mentry.getValue();
            if (harcRecord.getREQUEST_GUID().equals(iGUID)) {
                // получаем реально команду
                cmdHARC_HARC_ID = firstHexCommandGraph.getCmdRootHarcByHARC_HARC_ID(HARC_HARC_ID);
                cmdGUID = firstHexCommandHistories.getHexCommandHistoryRecordByHARC_HARC_ID(cmdHARC_HARC_ID);
                cmds.put(cmdGUID, firstHexCommandHistories.getHexCommandHistoryRecordByGUID(cmdGUID));

                System.out.println("Command GUID: " + " " + cmdHARC_HARC_ID + " " + cmdGUID);
            }
        }
        return cmds;
    }

    //

    public HashMap<String, HexCommanHistoryRecord> getSecondCommandsByRequestGUID(String iGUID) {
        System.out.println("Request GUID: ===============");
        System.out.println("Request GUID: " + iGUID);

        HashMap<String, HexCommanHistoryRecord> cmds = new HashMap<String, HexCommanHistoryRecord>();
        String HARC_HARC_ID;
        String cmdHARC_HARC_ID;
        String cmdGUID;
        //смотрим по HEX_ARC_REQUESTS
        Set set;
        set = getSecondHexArcRequest().getHexArcRequest().entrySet();
        Iterator iterator = set.iterator();
        ;
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            HARC_HARC_ID = mentry.getKey().toString();
            HexArcRequestRecord harcRecord = (HexArcRequestRecord) mentry.getValue();
            if (harcRecord.getREQUEST_GUID().equals(iGUID)) {
                // получаем реально команду
                cmdHARC_HARC_ID = secondHexCommandGraph.getCmdRootHarcByHARC_HARC_ID(HARC_HARC_ID);
                cmdGUID = secondHexCommandHistories.getHexCommandHistoryRecordByHARC_HARC_ID(cmdHARC_HARC_ID);
                cmds.put(cmdGUID, secondHexCommandHistories.getHexCommandHistoryRecordByGUID(cmdGUID));

                System.out.println("Command GUID: " + " " + cmdHARC_HARC_ID + " " + cmdGUID);
            }
        }
        return cmds;
    }

      public HashMap<String,HexCommanHistoryRecord> getFirstCommandsByPredicateGUID (String iGUID) {
        System.out.println("Predicate GUID: ===============");
        System.out.println("Predicate GUID: " + iGUID);

    HashMap<String, HexCommanHistoryRecord> cmds = new HashMap<String, HexCommanHistoryRecord>();
    String HARC_HARC_ID;
    String cmdHARC_HARC_ID;
    String cmdGUID;
    //смотрим по HEX_ARC_REQUESTS
    Set set;
    set = getFirstHexArcRequest().getHexArcRequest().entrySet();
    Iterator iterator = set.iterator();
    ;
        while (iterator.hasNext()) {
        Map.Entry mentry = (Map.Entry) iterator.next();
        HARC_HARC_ID = mentry.getKey().toString();
        HexArcRequestRecord harcRecord = (HexArcRequestRecord) mentry.getValue();
        if (harcRecord.getPREDICATE_GUID().equals(iGUID)) {
            // получаем реально команду
            cmdHARC_HARC_ID = firstHexCommandGraph.getCmdRootHarcByHARC_HARC_ID(HARC_HARC_ID);
            cmdGUID = firstHexCommandHistories.getHexCommandHistoryRecordByHARC_HARC_ID(cmdHARC_HARC_ID);
            cmds.put(cmdGUID, firstHexCommandHistories.getHexCommandHistoryRecordByGUID(cmdGUID));

            System.out.println("Command GUID: " + " " + cmdHARC_HARC_ID + " " + cmdGUID);
        }
    }
        return cmds;

     }
      public HashMap<String,HexCommanHistoryRecord> getSecondCommandsByPredicateGUID (String iGUID) {

          System.out.println("Predicate GUID: ===============");
          System.out.println("Predicate GUID: " + iGUID);

          HashMap<String, HexCommanHistoryRecord> cmds = new HashMap<String, HexCommanHistoryRecord>();
          String HARC_HARC_ID;
          String cmdHARC_HARC_ID;
          String cmdGUID;
          //смотрим по HEX_ARC_REQUESTS
          Set set;
          set = getSecondHexArcRequest().getHexArcRequest().entrySet();
          Iterator iterator = set.iterator();
          ;
          while (iterator.hasNext()) {
              Map.Entry mentry = (Map.Entry) iterator.next();
              HARC_HARC_ID = mentry.getKey().toString();
              HexArcRequestRecord harcRecord = (HexArcRequestRecord) mentry.getValue();
              if (harcRecord.getPREDICATE_GUID().equals(iGUID)) {
                  // получаем реально команду
                  cmdHARC_HARC_ID = secondHexCommandGraph.getCmdRootHarcByHARC_HARC_ID(HARC_HARC_ID);
                  cmdGUID = secondHexCommandHistories.getHexCommandHistoryRecordByHARC_HARC_ID(cmdHARC_HARC_ID);
                  cmds.put(cmdGUID, secondHexCommandHistories.getHexCommandHistoryRecordByGUID(cmdGUID));

                  System.out.println("Command GUID: " + " " + cmdHARC_HARC_ID + " " + cmdGUID);
              }
          }
          return cmds;
    }

//}


}
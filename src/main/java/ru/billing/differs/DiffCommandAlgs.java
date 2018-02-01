package ru.billing.differs;


import org.jgrapht.graph.DefaultEdge;
import ru.billing.hextypes.*;
import ru.billing.readers.HexPredicates;
import ru.billing.readers.HexRequests;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DiffCommandAlgs {

    private static String cmdPa;

    HexRequests firstHexRequests;
    HexRequests     secondHexRequests;

    HexPredicates firstHexPredicates;
    HexPredicates     secondHexPredicates;

    private HashMap<String, HexCommanHistoryRecord>  fOuter;
    private HashMap<String, HexCommanHistoryRecord>  sOuter;

    private HashMap<String, HexCommanHistoryRecord>  fcomDiffs;
    private HashMap<String, HexCommanHistoryRecord>  scomDiffs;

    public DiffCommandAlgs(
                                HexRequests       ifirstHexRequests,
                                HexRequests       isecondHexRequests,

                                HexPredicates     ifirstHexPredicates,
                                HexPredicates     isecondHexPredicates
                          ) {

        this.firstHexRequests = ifirstHexRequests;
        this.secondHexRequests = isecondHexRequests;

        this.firstHexPredicates = ifirstHexPredicates;
        this.secondHexPredicates = isecondHexPredicates;


        this.fOuter = new HashMap<String, HexCommanHistoryRecord> ();
        this.sOuter = new HashMap<String, HexCommanHistoryRecord> ();

        this.fcomDiffs = new HashMap<String, HexCommanHistoryRecord> ();
        this.scomDiffs = new HashMap<String, HexCommanHistoryRecord> ();
    }

    public void findDiffCommandAlorithms (
                                HashMap<String, HexCommanHistoryRecord> fMap,
                                HashMap<String, HexCommanHistoryRecord> sMap
                            ){
        Set set;
        Iterator iterator;
        set = fMap.entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (
                    sMap.containsKey(mentry.getKey().toString())
                    ){
                        String guid = mentry.getKey().toString();
                        HexCommanHistoryRecord fchrec = fMap.get(guid);
                        HexCommanHistoryRecord schrec = sMap.get(guid);
                            if ( ! (fchrec.compareTo(schrec) == 0 )) {
                                fcomDiffs.put(
                                        guid,
                                        fchrec
                                );
                                scomDiffs.put(
                                        guid,
                                        fchrec
                                );
                            }
                     }

        }

    }

    public HashMap<String, HexCommanHistoryRecord>  getLeftOuter () {return fOuter;}
    public HashMap<String, HexCommanHistoryRecord>  getRightOuter () {return sOuter;}

    public HashMap<String, HexCommanHistoryRecord>  getfcomDiffs () {return fcomDiffs;}
    public HashMap<String, HexCommanHistoryRecord>  getscomDiffs () {return scomDiffs;}
}

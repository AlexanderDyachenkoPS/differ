package ru.billing.differs;


import ru.billing.hextypes.HexRequestRecord;

import java.util.*;

public class DiffRequests {

    private HashMap<String,HexRequestRecord> fOuter;
    private HashMap<String,HexRequestRecord> sOuter;

    private HashMap<String,HexRequestRecord> freqDiffs;
    private HashMap<String,HexRequestRecord> sreqDiffs;

    public DiffRequests () {
        this.fOuter = new HashMap<String,HexRequestRecord>();
        this.sOuter = new HashMap<String,HexRequestRecord>();
        this.freqDiffs = new HashMap<String,HexRequestRecord>();
        this.sreqDiffs = new HashMap<String,HexRequestRecord>();
    }
    public void findDiffRequests (
                                HashMap<String, HexRequestRecord> fMap,
                                HashMap<String, HexRequestRecord> sMap
                            ){
        Set set;
        Iterator iterator;
        set = fMap.entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (!(sMap.containsKey(mentry.getKey().toString()))) {
                fOuter.put(
                            mentry.getKey().toString(),
                            (HexRequestRecord) mentry.getValue()
                          );
            }
        }

        set = sMap.entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (!(fMap.containsKey(mentry.getKey().toString()))) {
                sOuter.put(
                            mentry.getKey().toString(),
                           (HexRequestRecord) mentry.getValue()
                        );
            }
        }

        set = fMap.entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (
                    sMap.containsKey(mentry.getKey().toString())
                ) {
                String guid = mentry.getKey().toString();
                HexRequestRecord fReq =  fMap.get(guid);
                HexRequestRecord sReq =  sMap.get(guid);

                if ( ! (fReq.compareTo(sReq) == 0 )) {
                    freqDiffs.put(
                            guid,
                            fReq
                    );
                    sreqDiffs.put(
                            guid,
                            sReq
                    );
                }
            }
        }
    }

    public HashMap<String,HexRequestRecord> getLeftOuter () {return fOuter;}
    public HashMap<String,HexRequestRecord> getRightOuter () {return sOuter;}

    public HashMap<String,HexRequestRecord> getfreqDiffs() {return freqDiffs;}
    public HashMap<String,HexRequestRecord> getsreqDiffs () {return sreqDiffs;}
}

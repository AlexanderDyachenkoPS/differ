package ru.billing.differs;


import ru.billing.hextypes.HexArgumentRecord;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DiffArguments {

    private HashMap<String,HexArgumentRecord> fOuter;
    private HashMap<String,HexArgumentRecord> sOuter;

    private HashMap<String,HexArgumentRecord> freqDiffs;
    private HashMap<String,HexArgumentRecord> sreqDiffs;

    public DiffArguments() {
        this.fOuter = new HashMap<String,HexArgumentRecord>();
        this.sOuter = new HashMap<String,HexArgumentRecord>();
        this.freqDiffs = new HashMap<String,HexArgumentRecord>();
        this.sreqDiffs = new HashMap<String,HexArgumentRecord>();
    }
    public void findDiffArguments (
                                HashMap<String, HexArgumentRecord> fMap,
                                HashMap<String, HexArgumentRecord> sMap
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
                            (HexArgumentRecord) mentry.getValue()
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
                           (HexArgumentRecord) mentry.getValue()
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
                HexArgumentRecord fReq =  fMap.get(guid);
                HexArgumentRecord sReq =  sMap.get(guid);

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

    public HashMap<String,HexArgumentRecord> getLeftOuter () {return fOuter;}
    public HashMap<String,HexArgumentRecord> getRightOuter () {return sOuter;}

    public HashMap<String,HexArgumentRecord> getfreqDiffs() {return freqDiffs;}
    public HashMap<String,HexArgumentRecord> getsreqDiffs () {return sreqDiffs;}
}

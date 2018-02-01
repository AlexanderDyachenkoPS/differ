package ru.billing.differs;


import ru.billing.hextypes.HexCommandRecord;

import java.util.*;

public class DiffCommands {

    private HashMap<String, HexCommandRecord>  fOuter;
    private HashMap<String, HexCommandRecord>  sOuter;

    private HashMap<String, HexCommandRecord>  fcomDiffs;
    private HashMap<String, HexCommandRecord>  scomDiffs;

    public DiffCommands() {
        this.fOuter = new HashMap<String, HexCommandRecord> ();
        this.sOuter = new HashMap<String, HexCommandRecord> ();

        this.fcomDiffs = new HashMap<String, HexCommandRecord> ();
        this.scomDiffs = new HashMap<String, HexCommandRecord> ();
    }
    public void findDiffCommands (
                                HashMap<String, HexCommandRecord> fMap,
                                HashMap<String, HexCommandRecord> sMap
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
                        (HexCommandRecord) mentry.getValue()
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
                        (HexCommandRecord) mentry.getValue()
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
                HexCommandRecord fcom =  fMap.get(guid);
                HexCommandRecord scom =  sMap.get(guid);
 //
                if ( ! (fcom.compareTo(scom) == 0 )) {
                    fcomDiffs.put(
                            guid,
                            fcom
                    );
                    scomDiffs.put(
                            guid,
                            scom
                    );
                }
            }
        }
    }

    public HashMap<String, HexCommandRecord>  getLeftOuter () {return fOuter;}
    public HashMap<String, HexCommandRecord>  getRightOuter () {return sOuter;}

    public HashMap<String, HexCommandRecord>  getfcomDiffs () {return fcomDiffs;}
    public HashMap<String, HexCommandRecord>  getscomDiffs () {return scomDiffs;}
}

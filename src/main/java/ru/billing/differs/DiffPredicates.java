package ru.billing.differs;


import ru.billing.hextypes.HexPredicateRecord;

import java.util.*;

public class DiffPredicates {

    private HashMap<String, HexPredicateRecord> fOuter;
    private HashMap<String, HexPredicateRecord> sOuter;

    private HashMap<String,HexPredicateRecord> fprdDiffs;
    private HashMap<String,HexPredicateRecord> sprdDiffs;

    public DiffPredicates() {
        this.fOuter = new HashMap<String, HexPredicateRecord>();
        this.sOuter = new HashMap<String, HexPredicateRecord>();

        this.fprdDiffs = new HashMap<String, HexPredicateRecord>();
        this.sprdDiffs = new HashMap<String, HexPredicateRecord>();
    }
    public void findDiffPredicates (
                                HashMap<String, HexPredicateRecord> fMap,
                                HashMap<String, HexPredicateRecord> sMap
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
                        (HexPredicateRecord) mentry.getValue()
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
                        (HexPredicateRecord) mentry.getValue()
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
                HexPredicateRecord fprd =  fMap.get(guid);
                HexPredicateRecord sprd =  sMap.get(guid);

                if ( ! (fprd.compareTo(sprd) == 0 )) {
                    fprdDiffs.put(
                            guid,
                            fprd
                    );
                    sprdDiffs.put(
                            guid,
                            sprd
                    );
                }
            }
        }
    }

    public HashMap<String, HexPredicateRecord> getLeftOuter () {return fOuter;}
    public HashMap<String, HexPredicateRecord> getRightOuter () {return sOuter;}

    public HashMap<String,HexPredicateRecord> getfprdDiffs() {return fprdDiffs;}
    public HashMap<String,HexPredicateRecord> getsprdDiffs () {return sprdDiffs;}
}

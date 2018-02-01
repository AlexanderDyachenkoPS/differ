package ru.billing.hextypes;

public class HexPredicateRecord implements Comparable<HexPredicateRecord> {
    private String ENTITY_GUID;

    public String getENTITY_GUID() {
        return ENTITY_GUID;
    }

    public String getHPRC_HPRC_ID() {
        return HPRC_HPRC_ID;
    }

    public String getHPRT_HPRT_ID() {
        return HPRT_HPRT_ID;
    }

    public String getNEGATION() {
        return NEGATION;
    }

    public String getPREDICATE() {
        return PREDICATE;
    }

    public String getSDCT_SDCT_ID() {
        return SDCT_SDCT_ID;
    }

    private String HPRC_HPRC_ID;
    private String HPRT_HPRT_ID;
    private String NEGATION;
    private String PREDICATE;
    private String SDCT_SDCT_ID;

    public HexPredicateRecord(String iENTITY_GUID,
                              String iHPRC_HPRC_ID,
                              String iHPRT_HPRT_ID,
                              String iNEGATION,
                              String iPREDICATE,
                              String iSDCT_SDCT_ID) {
        this.ENTITY_GUID = iENTITY_GUID;
        this.HPRC_HPRC_ID = iHPRC_HPRC_ID;
        this.HPRT_HPRT_ID = iHPRT_HPRT_ID;
        this.NEGATION = iNEGATION;
        this.PREDICATE = iPREDICATE;
        this.SDCT_SDCT_ID = iSDCT_SDCT_ID;
    }





    @Override
    public int compareTo(HexPredicateRecord otherHexPredicateRecord) {
        //compare name
        try {
        if (
                        this.getPREDICATE().equals(otherHexPredicateRecord.getPREDICATE())
                ) {return 0;}
        else {return -1;}
    } catch (Exception e) {   System.out.println(this.getENTITY_GUID() + " exception " + e.toString()); return -1;}

}

}

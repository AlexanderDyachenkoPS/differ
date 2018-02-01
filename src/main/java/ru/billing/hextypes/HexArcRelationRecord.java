package ru.billing.hextypes;

public class HexArcRelationRecord {


    private String PREDICATE_GUID;
    private String HARC_HARC_ID;
    private String NEXT_HARC_ID;

    public HexArcRelationRecord(String iPREDICATE_GUID,
                                String iHARC_HARC_ID,
                                String iNEXT_HARC_ID
    ) {
        this.PREDICATE_GUID                 = iPREDICATE_GUID;
        this.HARC_HARC_ID                   = iHARC_HARC_ID;
        this.NEXT_HARC_ID                   = iNEXT_HARC_ID;
    }
    public String  getHARC_HARC_ID ()                           {return this.HARC_HARC_ID;}
    public String  getNEXT_HARC_ID ()                           {return this.NEXT_HARC_ID;}
    public String  getPREDICATE_GUID ()                           {return this.PREDICATE_GUID;}
}

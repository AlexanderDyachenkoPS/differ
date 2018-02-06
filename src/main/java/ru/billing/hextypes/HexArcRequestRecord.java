package ru.billing.hextypes;

public class HexArcRequestRecord {

    private String HARC_ID;
    private String SUBCOMMAND_GUID;
    private String SUB_COLLECTION_GUID;
    private String REQUEST_GUID;
    private String REQ_COLLECTION_GUID;
    private String PREDICATE_GUID;
    private String PRD_COLLECTION_GUID;

    public HexArcRequestRecord(String iHARC_ID,
                               String iSUBCOMMAND_GUID,
                               String iSUB_COLLECTION_GUID,
                               String iREQUEST_GUID,
                               String iREQ_COLLECTION_GUID,
                               String iPREDICATE_GUID,
                               String iPRD_COLLECTION_GUID) {
        this.HARC_ID                    = iHARC_ID;
        this.SUBCOMMAND_GUID            = iSUBCOMMAND_GUID;
        this.SUB_COLLECTION_GUID        = iSUB_COLLECTION_GUID;
        this.REQUEST_GUID               = iREQUEST_GUID;
        this.REQ_COLLECTION_GUID        = iREQ_COLLECTION_GUID;
        this.PREDICATE_GUID             = iPREDICATE_GUID;
        this.PRD_COLLECTION_GUID        = iPRD_COLLECTION_GUID;

    }


    public String  getHARC_ID ()                    {return this.HARC_ID;}

    public String getSUBCOMMAND_GUID() {
        return SUBCOMMAND_GUID;
    }

    public String getSUB_COLLECTION_GUID() {
        return SUB_COLLECTION_GUID;
    }

    public String  getREQUEST_GUID ()              {return this.REQUEST_GUID;}
    public String  getREQ_COLLECTION_GUID ()                 {return this.REQ_COLLECTION_GUID;}
    public String  getPREDICATE_GUID ()          {return this.PREDICATE_GUID;}
    public String  getPRD_COLLECTION_GUID ()             {return this.PRD_COLLECTION_GUID;}


}


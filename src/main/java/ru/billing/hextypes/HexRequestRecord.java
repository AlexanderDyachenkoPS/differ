package ru.billing.hextypes;

public class HexRequestRecord implements Comparable<HexRequestRecord> {
    private String ENTITY_GUID;
    private String EQTYPE_GUID;
    private String HRQT_HRQT_ID;
    private String REQUEST;
    private String SDCT_SDCT_ID;

    public HexRequestRecord(String iENTITY_GUID,
                            String iEQTYPE_GUID,
                            String iHRQT_HRQT_ID,
                            String iREQUEST,
                            String iSDCT_SDCT_ID) {
        this.ENTITY_GUID = iENTITY_GUID;
        this.EQTYPE_GUID = iEQTYPE_GUID;
        this.HRQT_HRQT_ID = iHRQT_HRQT_ID;
        this.REQUEST = iREQUEST;
        this.SDCT_SDCT_ID = iSDCT_SDCT_ID;
    }


    public String getENTITY_GUID() {
        return this.ENTITY_GUID;
    }

    public String getEQTYPE_GUID() {
        return this.EQTYPE_GUID;
    }

    public String getHRQT_HRQT_ID() {
        return this.HRQT_HRQT_ID;
    }

    public String getREQUEST() {
        return this.REQUEST;
    }

    public String getSDCT_SDCT_ID() {
        return this.SDCT_SDCT_ID;
    }


    @Override
    public int compareTo(HexRequestRecord otherHexRequestRecord) {
        //compare name
        try {
        if (
                        this.getREQUEST().equals(otherHexRequestRecord.getREQUEST())
                ) {return 0;}
        else {return -1;}
    } catch (Exception e) {   System.out.println(this.getENTITY_GUID() + " exception " + e.toString()); return -1;}

}

}

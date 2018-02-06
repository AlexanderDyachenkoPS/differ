package ru.billing.hextypes;

public class HexArgumentRecord implements Comparable<HexArgumentRecord>{


    private String ENTITY_GUID;
    private String NAME;
    private String SDCT_SDCT_ID;
    private String DEFAULT_VALUE;
    private String TRANSFORMER;

    public HexArgumentRecord(String iENTITY_GUID,
                             String iNAME,
                             String iSDCT_SDCT_ID,
                             String iDEFAULT_VALUE,
                             String iTRANSFORMER) {
        this.ENTITY_GUID                    = iENTITY_GUID;
        this.NAME                         = iNAME;
        this.SDCT_SDCT_ID                 = iSDCT_SDCT_ID;
        this.DEFAULT_VALUE          = iDEFAULT_VALUE;
        this.TRANSFORMER             = iTRANSFORMER;

    }

    public String getENTITY_GUID() {
        return ENTITY_GUID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getSDCT_SDCT_ID() {
        return SDCT_SDCT_ID;
    }

    public String getDEFAULT_VALUE() {
        return DEFAULT_VALUE;
    }

    public String getTRANSFORMER() {
        return TRANSFORMER;
    }

    @Override
    public int compareTo(HexArgumentRecord otherHexArgumentRecord) {
        //compare name
        try {
            if (
                            this.DEFAULT_VALUE.equals(otherHexArgumentRecord.getDEFAULT_VALUE()) &&
                            this.NAME.equals(otherHexArgumentRecord.getNAME()) &&
                            this.SDCT_SDCT_ID.equals(otherHexArgumentRecord.getSDCT_SDCT_ID()) &&
                            this.TRANSFORMER.equals(otherHexArgumentRecord.getTRANSFORMER())
                    ) {return 0;}
            else {return -1;}
        } catch (Exception e) {   System.out.println(this.getENTITY_GUID() + " exception " + e.toString()); return -1;}

    }
}


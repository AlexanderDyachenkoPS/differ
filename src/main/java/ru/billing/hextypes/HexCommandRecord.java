package ru.billing.hextypes;

public class HexCommandRecord implements Comparable<HexCommandRecord>{
    private String ENTITY_GUID;
    private String NAME;
    private String CODE;
    private String SDCT_SDCT_ID;
    private String PERSISTENT_CONNECTIONS_FLAG;
    private String INTERNAL_COMMAND;
    public HexCommandRecord (String iENTITY_GUID,
                      String iNAME,
                      String iCODE,
                      String iSDCT_SDCT_ID,
                      String iPERSISTENT_CONNECTIONS_FLAG,
                      String iINTERNAL_COMMAND) {
        this.ENTITY_GUID                    = iENTITY_GUID;
        this.NAME                           = iNAME;
        this.CODE                           = iCODE;
        this.SDCT_SDCT_ID                   = iSDCT_SDCT_ID;
        this.PERSISTENT_CONNECTIONS_FLAG    = iPERSISTENT_CONNECTIONS_FLAG;
        this.INTERNAL_COMMAND               = iINTERNAL_COMMAND;
    }


    public String  getENTITY_GUID ()                    {return this.ENTITY_GUID;}
    public String  getNAME ()                           {return this.NAME;}
    public String  getCODE ()                           {return this.CODE;}
    public String  getSDCT_SDCT_ID ()                   {return this.SDCT_SDCT_ID;}
    public String  getPERSISTENT_CONNECTIONS_FLAG ()    {return this.PERSISTENT_CONNECTIONS_FLAG;}
    public String  getINTERNAL_COMMAND ()               {return this.INTERNAL_COMMAND;}

    @Override
    public int compareTo(HexCommandRecord otherHexCommandRecord) {
        //compare name
        try {
            if (
                    this.getCODE().equals(otherHexCommandRecord.getCODE()) &&
                    this.getNAME().equals(otherHexCommandRecord.getNAME()) &&
                    this.getPERSISTENT_CONNECTIONS_FLAG().equals(otherHexCommandRecord.getPERSISTENT_CONNECTIONS_FLAG())       &&
                    this.getINTERNAL_COMMAND().equals(otherHexCommandRecord.getINTERNAL_COMMAND())

                    ) {return 0;}
            else {return -1;}
        } catch (Exception e) {   System.out.println(this.getENTITY_GUID() + " exception " + e.toString()); return -1;}

    }

}

package ru.billing.hextypes;

public class HexArcRecord {


    private String HARC_ID;
    private String CRITICAL_FLAG;
    private String FINAL_FLAG;
    private String ERROR_IGNORE_FLAG;
    private String PRINT_OUT_FLAG;
    private String PRINT_OUT_TAG;
    public HexArcRecord (String iHARC_ID,
                             String iCRITICAL_FLAG,
                             String iFINAL_FLAG,
                             String iERROR_IGNORE_FLAG,
                             String iPRINT_OUT_FLAG,
                             String iPRINT_OUT_TAG) {
        this.HARC_ID                    = iHARC_ID;
        this.CRITICAL_FLAG              = iCRITICAL_FLAG;
        this.FINAL_FLAG                 = iFINAL_FLAG;
        this.ERROR_IGNORE_FLAG          = iERROR_IGNORE_FLAG;
        this.PRINT_OUT_FLAG             = iPRINT_OUT_FLAG;
        this.PRINT_OUT_TAG              = iPRINT_OUT_TAG;
    }


    public String  getHARC_ID ()                    {return this.HARC_ID;}
    public String  getCRITICAL_FLAG ()              {return this.CRITICAL_FLAG;}
    public String  getFINAL_FLAG ()                 {return this.FINAL_FLAG;}
    public String  getERROR_IGNORE_FLAG ()          {return this.ERROR_IGNORE_FLAG;}
    public String  getPRINT_OUT_FLAG ()             {return this.PRINT_OUT_FLAG;}
    public String  getPRINT_OUT_TAG ()              {return this.PRINT_OUT_TAG;}

}


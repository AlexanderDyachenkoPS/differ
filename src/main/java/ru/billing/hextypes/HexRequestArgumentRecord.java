package ru.billing.hextypes;

public class HexRequestArgumentRecord {

    private String REQ_ENTITY_GUID;
    private String ARGUMENT_NAME;
    private String ARG_ENTITY_GUID;
    private String MANDATORY_FLAG;


    public HexRequestArgumentRecord(String iREQ_ENTITY_GUID,
                                    String iARGUMENT_NAME,
                                    String iARG_ENTITY_GUID,
                                    String iMANDATORY_FLAG) {
        this.REQ_ENTITY_GUID                    = iREQ_ENTITY_GUID;
        this.ARGUMENT_NAME                         = iARGUMENT_NAME;
        this.ARG_ENTITY_GUID                 = iARG_ENTITY_GUID;
        this.MANDATORY_FLAG          = iMANDATORY_FLAG;

    }

    public String getREQ_ENTITY_GUID() {
        return REQ_ENTITY_GUID;
    }

    public String getARGUMENT_NAME() {
        return ARGUMENT_NAME;
    }

    public String getARG_ENTITY_GUID() {
        return ARG_ENTITY_GUID;
    }

    public String getMANDATORY_FLAG() {
        return MANDATORY_FLAG;
    }
}


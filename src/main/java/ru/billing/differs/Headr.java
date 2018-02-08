package ru.billing.differs;


import ru.billing.readers.HexCollectionHeader;

public class Headr {

    private HexCollectionHeader fHeadr;
    private HexCollectionHeader sHeadr;

    public Headr(HexCollectionHeader fHexCollectionHeader,
                 HexCollectionHeader sHexCollectionHeader
        ) {
        this.fHeadr = fHexCollectionHeader;
        this.sHeadr = sHexCollectionHeader;

    }

    public HexCollectionHeader getfHeadr() {
        return fHeadr;
    }

    public HexCollectionHeader getsHeadr() {
        return sHeadr;
    }
}

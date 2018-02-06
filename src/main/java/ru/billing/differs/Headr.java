package ru.billing.differs;


import ru.billing.hextypes.HexRequestRecord;
import ru.billing.readers.HexCollectionHeader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

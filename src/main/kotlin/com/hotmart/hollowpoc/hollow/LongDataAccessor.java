package com.hotmart.hollowpoc.hollow;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.data.AbstractHollowDataAccessor;
import com.netflix.hollow.core.index.key.PrimaryKey;
import com.netflix.hollow.core.read.engine.HollowReadStateEngine;

@SuppressWarnings("all")
public class LongDataAccessor extends AbstractHollowDataAccessor<HLong> {

    public static final String TYPE = "HLong";
    private UserAPI api;

    public LongDataAccessor(HollowConsumer consumer) {
        super(consumer, TYPE);
        this.api = (UserAPI)consumer.getAPI();
    }

    public LongDataAccessor(HollowReadStateEngine rStateEngine, UserAPI api) {
        super(rStateEngine, TYPE);
        this.api = api;
    }

    public LongDataAccessor(HollowReadStateEngine rStateEngine, UserAPI api, String ... fieldPaths) {
        super(rStateEngine, TYPE, fieldPaths);
        this.api = api;
    }

    public LongDataAccessor(HollowReadStateEngine rStateEngine, UserAPI api, PrimaryKey primaryKey) {
        super(rStateEngine, TYPE, primaryKey);
        this.api = api;
    }

    @Override public HLong getRecord(int ordinal){
        return api.getHLong(ordinal);
    }

}
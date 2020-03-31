package com.hotmart.hollowpoc.hollow;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class DatePrimaryKeyIndex extends AbstractHollowUniqueKeyIndex<UserAPI, Date> {

    public DatePrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, true);
    }

    public DatePrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh) {
        this(consumer, isListenToDataRefresh, ((HollowObjectSchema)consumer.getStateEngine().getNonNullSchema("Date")).getPrimaryKey().getFieldPaths());
    }

    public DatePrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, true, fieldPaths);
    }

    public DatePrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh, String... fieldPaths) {
        super(consumer, "Date", isListenToDataRefresh, fieldPaths);
    }

    public Date findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if(ordinal == -1)
            return null;
        return api.getDate(ordinal);
    }

}
package com.hotmart.hollowpoc.hollow;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class UserPrimaryKeyIndex extends AbstractHollowUniqueKeyIndex<UserAPI, User> {

    public UserPrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, true);
    }

    public UserPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh) {
        this(consumer, isListenToDataRefresh, ((HollowObjectSchema)consumer.getStateEngine().getNonNullSchema("User")).getPrimaryKey().getFieldPaths());
    }

    public UserPrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, true, fieldPaths);
    }

    public UserPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh, String... fieldPaths) {
        super(consumer, "User", isListenToDataRefresh, fieldPaths);
    }

    public User findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if(ordinal == -1)
            return null;
        return api.getUser(ordinal);
    }

}
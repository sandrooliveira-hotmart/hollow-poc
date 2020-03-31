package com.hotmart.hollowpoc.hollow;

import com.netflix.hollow.api.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class UserDelegateLookupImpl extends HollowObjectAbstractDelegate implements UserDelegate {

    private final UserTypeAPI typeAPI;

    public UserDelegateLookupImpl(UserTypeAPI typeAPI) {
        this.typeAPI = typeAPI;
    }

    public int getIdOrdinal(int ordinal) {
        return typeAPI.getIdOrdinal(ordinal);
    }

    public int getUserCodeOrdinal(int ordinal) {
        return typeAPI.getUserCodeOrdinal(ordinal);
    }

    public int getNameOrdinal(int ordinal) {
        return typeAPI.getNameOrdinal(ordinal);
    }

    public int getDescriptionOrdinal(int ordinal) {
        return typeAPI.getDescriptionOrdinal(ordinal);
    }

    public int getDateBornOrdinal(int ordinal) {
        return typeAPI.getDateBornOrdinal(ordinal);
    }

    public UserTypeAPI getTypeAPI() {
        return typeAPI;
    }

    @Override
    public HollowObjectSchema getSchema() {
        return typeAPI.getTypeDataAccess().getSchema();
    }

    @Override
    public HollowObjectTypeDataAccess getTypeDataAccess() {
        return typeAPI.getTypeDataAccess();
    }

}
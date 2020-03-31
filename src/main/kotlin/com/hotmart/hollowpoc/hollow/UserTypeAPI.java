package com.hotmart.hollowpoc.hollow;

import com.netflix.hollow.api.custom.HollowObjectTypeAPI;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;

@SuppressWarnings("all")
public class UserTypeAPI extends HollowObjectTypeAPI {

    private final UserDelegateLookupImpl delegateLookupImpl;

    public UserTypeAPI(UserAPI api, HollowObjectTypeDataAccess typeDataAccess) {
        super(api, typeDataAccess, new String[] {
            "id",
            "userCode",
            "name",
            "description",
            "dateBorn"
        });
        this.delegateLookupImpl = new UserDelegateLookupImpl(this);
    }

    public int getIdOrdinal(int ordinal) {
        if(fieldIndex[0] == -1)
            return missingDataHandler().handleReferencedOrdinal("User", ordinal, "id");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[0]);
    }

    public LongTypeAPI getIdTypeAPI() {
        return getAPI().getLongTypeAPI();
    }

    public int getUserCodeOrdinal(int ordinal) {
        if(fieldIndex[1] == -1)
            return missingDataHandler().handleReferencedOrdinal("User", ordinal, "userCode");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[1]);
    }

    public StringTypeAPI getUserCodeTypeAPI() {
        return getAPI().getStringTypeAPI();
    }

    public int getNameOrdinal(int ordinal) {
        if(fieldIndex[2] == -1)
            return missingDataHandler().handleReferencedOrdinal("User", ordinal, "name");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[2]);
    }

    public StringTypeAPI getNameTypeAPI() {
        return getAPI().getStringTypeAPI();
    }

    public int getDescriptionOrdinal(int ordinal) {
        if(fieldIndex[3] == -1)
            return missingDataHandler().handleReferencedOrdinal("User", ordinal, "description");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[3]);
    }

    public StringTypeAPI getDescriptionTypeAPI() {
        return getAPI().getStringTypeAPI();
    }

    public int getDateBornOrdinal(int ordinal) {
        if(fieldIndex[4] == -1)
            return missingDataHandler().handleReferencedOrdinal("User", ordinal, "dateBorn");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[4]);
    }

    public DateTypeAPI getDateBornTypeAPI() {
        return getAPI().getDateTypeAPI();
    }

    public UserDelegateLookupImpl getDelegateLookupImpl() {
        return delegateLookupImpl;
    }

    @Override
    public UserAPI getAPI() {
        return (UserAPI) api;
    }

}
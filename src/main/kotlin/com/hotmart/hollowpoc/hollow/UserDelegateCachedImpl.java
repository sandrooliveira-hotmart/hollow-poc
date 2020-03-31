package com.hotmart.hollowpoc.hollow;

import com.netflix.hollow.api.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.schema.HollowObjectSchema;
import com.netflix.hollow.api.custom.HollowTypeAPI;
import com.netflix.hollow.api.objects.delegate.HollowCachedDelegate;

@SuppressWarnings("all")
public class UserDelegateCachedImpl extends HollowObjectAbstractDelegate implements HollowCachedDelegate, UserDelegate {

    private final int idOrdinal;
    private final int userCodeOrdinal;
    private final int nameOrdinal;
    private final int descriptionOrdinal;
    private final int dateBornOrdinal;
    private UserTypeAPI typeAPI;

    public UserDelegateCachedImpl(UserTypeAPI typeAPI, int ordinal) {
        this.idOrdinal = typeAPI.getIdOrdinal(ordinal);
        this.userCodeOrdinal = typeAPI.getUserCodeOrdinal(ordinal);
        this.nameOrdinal = typeAPI.getNameOrdinal(ordinal);
        this.descriptionOrdinal = typeAPI.getDescriptionOrdinal(ordinal);
        this.dateBornOrdinal = typeAPI.getDateBornOrdinal(ordinal);
        this.typeAPI = typeAPI;
    }

    public int getIdOrdinal(int ordinal) {
        return idOrdinal;
    }

    public int getUserCodeOrdinal(int ordinal) {
        return userCodeOrdinal;
    }

    public int getNameOrdinal(int ordinal) {
        return nameOrdinal;
    }

    public int getDescriptionOrdinal(int ordinal) {
        return descriptionOrdinal;
    }

    public int getDateBornOrdinal(int ordinal) {
        return dateBornOrdinal;
    }

    @Override
    public HollowObjectSchema getSchema() {
        return typeAPI.getTypeDataAccess().getSchema();
    }

    @Override
    public HollowObjectTypeDataAccess getTypeDataAccess() {
        return typeAPI.getTypeDataAccess();
    }

    public UserTypeAPI getTypeAPI() {
        return typeAPI;
    }

    public void updateTypeAPI(HollowTypeAPI typeAPI) {
        this.typeAPI = (UserTypeAPI) typeAPI;
    }

}
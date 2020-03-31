package com.hotmart.hollowpoc.hollow;

import com.netflix.hollow.api.objects.HollowObject;
import com.netflix.hollow.core.schema.HollowObjectSchema;

import com.netflix.hollow.tools.stringifier.HollowRecordStringifier;

@SuppressWarnings("all")
public class User extends HollowObject {

    public User(UserDelegate delegate, int ordinal) {
        super(delegate, ordinal);
    }

    public HLong getId() {
        int refOrdinal = delegate().getIdOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getHLong(refOrdinal);
    }

    public HString getUserCode() {
        int refOrdinal = delegate().getUserCodeOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getHString(refOrdinal);
    }

    public HString getName() {
        int refOrdinal = delegate().getNameOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getHString(refOrdinal);
    }

    public HString getDescription() {
        int refOrdinal = delegate().getDescriptionOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getHString(refOrdinal);
    }

    public Date getDateBorn() {
        int refOrdinal = delegate().getDateBornOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getDate(refOrdinal);
    }

    public UserAPI api() {
        return typeApi().getAPI();
    }

    public UserTypeAPI typeApi() {
        return delegate().getTypeAPI();
    }

    protected UserDelegate delegate() {
        return (UserDelegate)delegate;
    }

}
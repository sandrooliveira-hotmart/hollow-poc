package com.hotmart.hollowpoc.hollow;

import com.netflix.hollow.api.objects.delegate.HollowObjectDelegate;


@SuppressWarnings("all")
public interface UserDelegate extends HollowObjectDelegate {

    public int getIdOrdinal(int ordinal);

    public int getUserCodeOrdinal(int ordinal);

    public int getNameOrdinal(int ordinal);

    public int getDescriptionOrdinal(int ordinal);

    public int getDateBornOrdinal(int ordinal);

    public UserTypeAPI getTypeAPI();

}
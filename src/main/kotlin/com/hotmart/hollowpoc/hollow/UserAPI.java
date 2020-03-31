package com.hotmart.hollowpoc.hollow;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.Map;
import com.netflix.hollow.api.consumer.HollowConsumerAPI;
import com.netflix.hollow.api.custom.HollowAPI;
import com.netflix.hollow.core.read.dataaccess.HollowDataAccess;
import com.netflix.hollow.core.read.dataaccess.HollowTypeDataAccess;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.read.dataaccess.HollowListTypeDataAccess;
import com.netflix.hollow.core.read.dataaccess.HollowSetTypeDataAccess;
import com.netflix.hollow.core.read.dataaccess.HollowMapTypeDataAccess;
import com.netflix.hollow.core.read.dataaccess.missing.HollowObjectMissingDataAccess;
import com.netflix.hollow.core.read.dataaccess.missing.HollowListMissingDataAccess;
import com.netflix.hollow.core.read.dataaccess.missing.HollowSetMissingDataAccess;
import com.netflix.hollow.core.read.dataaccess.missing.HollowMapMissingDataAccess;
import com.netflix.hollow.api.objects.provider.HollowFactory;
import com.netflix.hollow.api.objects.provider.HollowObjectProvider;
import com.netflix.hollow.api.objects.provider.HollowObjectCacheProvider;
import com.netflix.hollow.api.objects.provider.HollowObjectFactoryProvider;
import com.netflix.hollow.api.sampling.HollowObjectCreationSampler;
import com.netflix.hollow.api.sampling.HollowSamplingDirector;
import com.netflix.hollow.api.sampling.SampleResult;
import com.netflix.hollow.core.util.AllHollowRecordCollection;

@SuppressWarnings("all")
public class UserAPI extends HollowAPI  {

    private final HollowObjectCreationSampler objectCreationSampler;

    private final DateTypeAPI dateTypeAPI;
    private final LongTypeAPI longTypeAPI;
    private final StringTypeAPI stringTypeAPI;
    private final UserTypeAPI userTypeAPI;

    private final HollowObjectProvider dateProvider;
    private final HollowObjectProvider longProvider;
    private final HollowObjectProvider stringProvider;
    private final HollowObjectProvider userProvider;

    public UserAPI(HollowDataAccess dataAccess) {
        this(dataAccess, Collections.<String>emptySet());
    }

    public UserAPI(HollowDataAccess dataAccess, Set<String> cachedTypes) {
        this(dataAccess, cachedTypes, Collections.<String, HollowFactory<?>>emptyMap());
    }

    public UserAPI(HollowDataAccess dataAccess, Set<String> cachedTypes, Map<String, HollowFactory<?>> factoryOverrides) {
        this(dataAccess, cachedTypes, factoryOverrides, null);
    }

    public UserAPI(HollowDataAccess dataAccess, Set<String> cachedTypes, Map<String, HollowFactory<?>> factoryOverrides, UserAPI previousCycleAPI) {
        super(dataAccess);
        HollowTypeDataAccess typeDataAccess;
        HollowFactory factory;

        objectCreationSampler = new HollowObjectCreationSampler("Date","Long","String","User");

        typeDataAccess = dataAccess.getTypeDataAccess("Date");
        if(typeDataAccess != null) {
            dateTypeAPI = new DateTypeAPI(this, (HollowObjectTypeDataAccess)typeDataAccess);
        } else {
            dateTypeAPI = new DateTypeAPI(this, new HollowObjectMissingDataAccess(dataAccess, "Date"));
        }
        addTypeAPI(dateTypeAPI);
        factory = factoryOverrides.get("Date");
        if(factory == null)
            factory = new DateHollowFactory();
        if(cachedTypes.contains("Date")) {
            HollowObjectCacheProvider previousCacheProvider = null;
            if(previousCycleAPI != null && (previousCycleAPI.dateProvider instanceof HollowObjectCacheProvider))
                previousCacheProvider = (HollowObjectCacheProvider) previousCycleAPI.dateProvider;
            dateProvider = new HollowObjectCacheProvider(typeDataAccess, dateTypeAPI, factory, previousCacheProvider);
        } else {
            dateProvider = new HollowObjectFactoryProvider(typeDataAccess, dateTypeAPI, factory);
        }

        typeDataAccess = dataAccess.getTypeDataAccess("Long");
        if(typeDataAccess != null) {
            longTypeAPI = new LongTypeAPI(this, (HollowObjectTypeDataAccess)typeDataAccess);
        } else {
            longTypeAPI = new LongTypeAPI(this, new HollowObjectMissingDataAccess(dataAccess, "Long"));
        }
        addTypeAPI(longTypeAPI);
        factory = factoryOverrides.get("Long");
        if(factory == null)
            factory = new LongHollowFactory();
        if(cachedTypes.contains("Long")) {
            HollowObjectCacheProvider previousCacheProvider = null;
            if(previousCycleAPI != null && (previousCycleAPI.longProvider instanceof HollowObjectCacheProvider))
                previousCacheProvider = (HollowObjectCacheProvider) previousCycleAPI.longProvider;
            longProvider = new HollowObjectCacheProvider(typeDataAccess, longTypeAPI, factory, previousCacheProvider);
        } else {
            longProvider = new HollowObjectFactoryProvider(typeDataAccess, longTypeAPI, factory);
        }

        typeDataAccess = dataAccess.getTypeDataAccess("String");
        if(typeDataAccess != null) {
            stringTypeAPI = new StringTypeAPI(this, (HollowObjectTypeDataAccess)typeDataAccess);
        } else {
            stringTypeAPI = new StringTypeAPI(this, new HollowObjectMissingDataAccess(dataAccess, "String"));
        }
        addTypeAPI(stringTypeAPI);
        factory = factoryOverrides.get("String");
        if(factory == null)
            factory = new StringHollowFactory();
        if(cachedTypes.contains("String")) {
            HollowObjectCacheProvider previousCacheProvider = null;
            if(previousCycleAPI != null && (previousCycleAPI.stringProvider instanceof HollowObjectCacheProvider))
                previousCacheProvider = (HollowObjectCacheProvider) previousCycleAPI.stringProvider;
            stringProvider = new HollowObjectCacheProvider(typeDataAccess, stringTypeAPI, factory, previousCacheProvider);
        } else {
            stringProvider = new HollowObjectFactoryProvider(typeDataAccess, stringTypeAPI, factory);
        }

        typeDataAccess = dataAccess.getTypeDataAccess("User");
        if(typeDataAccess != null) {
            userTypeAPI = new UserTypeAPI(this, (HollowObjectTypeDataAccess)typeDataAccess);
        } else {
            userTypeAPI = new UserTypeAPI(this, new HollowObjectMissingDataAccess(dataAccess, "User"));
        }
        addTypeAPI(userTypeAPI);
        factory = factoryOverrides.get("User");
        if(factory == null)
            factory = new UserHollowFactory();
        if(cachedTypes.contains("User")) {
            HollowObjectCacheProvider previousCacheProvider = null;
            if(previousCycleAPI != null && (previousCycleAPI.userProvider instanceof HollowObjectCacheProvider))
                previousCacheProvider = (HollowObjectCacheProvider) previousCycleAPI.userProvider;
            userProvider = new HollowObjectCacheProvider(typeDataAccess, userTypeAPI, factory, previousCacheProvider);
        } else {
            userProvider = new HollowObjectFactoryProvider(typeDataAccess, userTypeAPI, factory);
        }

    }

    public void detachCaches() {
        if(dateProvider instanceof HollowObjectCacheProvider)
            ((HollowObjectCacheProvider)dateProvider).detach();
        if(longProvider instanceof HollowObjectCacheProvider)
            ((HollowObjectCacheProvider)longProvider).detach();
        if(stringProvider instanceof HollowObjectCacheProvider)
            ((HollowObjectCacheProvider)stringProvider).detach();
        if(userProvider instanceof HollowObjectCacheProvider)
            ((HollowObjectCacheProvider)userProvider).detach();
    }

    public DateTypeAPI getDateTypeAPI() {
        return dateTypeAPI;
    }
    public LongTypeAPI getLongTypeAPI() {
        return longTypeAPI;
    }
    public StringTypeAPI getStringTypeAPI() {
        return stringTypeAPI;
    }
    public UserTypeAPI getUserTypeAPI() {
        return userTypeAPI;
    }
    public Collection<Date> getAllDate() {
        return new AllHollowRecordCollection<Date>(getDataAccess().getTypeDataAccess("Date").getTypeState()) {
            protected Date getForOrdinal(int ordinal) {
                return getDate(ordinal);
            }
        };
    }
    public Date getDate(int ordinal) {
        objectCreationSampler.recordCreation(0);
        return (Date)dateProvider.getHollowObject(ordinal);
    }
    public Collection<HLong> getAllHLong() {
        return new AllHollowRecordCollection<HLong>(getDataAccess().getTypeDataAccess("Long").getTypeState()) {
            protected HLong getForOrdinal(int ordinal) {
                return getHLong(ordinal);
            }
        };
    }
    public HLong getHLong(int ordinal) {
        objectCreationSampler.recordCreation(1);
        return (HLong)longProvider.getHollowObject(ordinal);
    }
    public Collection<HString> getAllHString() {
        return new AllHollowRecordCollection<HString>(getDataAccess().getTypeDataAccess("String").getTypeState()) {
            protected HString getForOrdinal(int ordinal) {
                return getHString(ordinal);
            }
        };
    }
    public HString getHString(int ordinal) {
        objectCreationSampler.recordCreation(2);
        return (HString)stringProvider.getHollowObject(ordinal);
    }
    public Collection<User> getAllUser() {
        return new AllHollowRecordCollection<User>(getDataAccess().getTypeDataAccess("User").getTypeState()) {
            protected User getForOrdinal(int ordinal) {
                return getUser(ordinal);
            }
        };
    }
    public User getUser(int ordinal) {
        objectCreationSampler.recordCreation(3);
        return (User)userProvider.getHollowObject(ordinal);
    }
    public void setSamplingDirector(HollowSamplingDirector director) {
        super.setSamplingDirector(director);
        objectCreationSampler.setSamplingDirector(director);
    }

    public Collection<SampleResult> getObjectCreationSamplingResults() {
        return objectCreationSampler.getSampleResults();
    }

}

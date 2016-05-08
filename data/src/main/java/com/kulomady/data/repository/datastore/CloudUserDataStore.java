package com.kulomady.data.repository.datastore;

import com.kulomady.data.cache.UserCache;
import com.kulomady.data.entity.UserEntity;
import com.kulomady.data.net.UserRestApi;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 *
 * Created by kulomady on 5/6/16.
 */
public class CloudUserDataStore implements UserDataStore{
    private final UserRestApi restApi;
    private final UserCache userCache;

    private final Action1<UserEntity> saveToCacheAction = userEntity ->{
        if (userEntity != null) {
            CloudUserDataStore.this.userCache.put(userEntity);
        }
    };

    public CloudUserDataStore(UserRestApi restApi, UserCache userCache) {
        this.restApi = restApi;
        this.userCache = userCache;
    }


    /**
     * Get an {@link Observable} which will emit a List of {@link UserEntity}.
     */
    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return this.restApi.userEntityList();
    }

    /**
     * Get an {@link Observable} which will emit a {@link UserEntity} by its id.
     *
     * @param userId The id to retrieve user data.
     */
    @Override
    public Observable<UserEntity> userEntityDetails(int userId) {
        return restApi.userEntityById(userId).doOnNext(saveToCacheAction);
    }
}

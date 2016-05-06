package com.kulomady.data.repository.datastore;

import com.kulomady.data.entity.UserEntity;

import java.util.List;

import rx.Observable;


/**
 * Interface that represents a data store from where data is retrieved.
 * Created by kulomady on 5/6/16.
 */
public interface UserDataStore {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link UserEntity}.
     */
    Observable<List<UserEntity>> userEntityList();


    /**
     * Get an {@link rx.Observable} which will emit a {@link UserEntity} by its id.
     *
     * @param userId The id to retrieve user data.
     */
    Observable<UserEntity> userEntityDetails(final int userId);
}

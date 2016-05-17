package com.kulomady.data.repository;

import com.kulomady.data.entity.mapper.UserEntityDataMapper;
import com.kulomady.data.repository.datastore.userStore.UserDataStore;
import com.kulomady.data.repository.datastore.userStore.UserDataStoreFactory;
import com.kulomady.domain.User;
import com.kulomady.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 *
 * Created by kulomady on 5/6/16.
 */
/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements UserRepository {

    private final UserDataStoreFactory userDataStoreFactory;
    private final UserEntityDataMapper userEntityDataMapper;

    /**
     * Constructs a {@link UserRepository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     * @param userEntityDataMapper {@link UserEntityDataMapper}.
     */
    @Inject
    public UserDataRepository(UserDataStoreFactory dataStoreFactory,
                              UserEntityDataMapper userEntityDataMapper) {
        this.userDataStoreFactory = dataStoreFactory;
        this.userEntityDataMapper = userEntityDataMapper;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override public Observable<List<User>> users() {
        //we always get all users from the cloud
        final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
        return userDataStore.userEntityList()
                .map(userEntities -> this.userEntityDataMapper.transform(userEntities));
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override public Observable<User> user(int userId) {
        final UserDataStore userDataStore = this.userDataStoreFactory.create(userId);
        return userDataStore.userEntityDetails(userId)
                .map(userEntity -> this.userEntityDataMapper.transform(userEntity));
    }
}

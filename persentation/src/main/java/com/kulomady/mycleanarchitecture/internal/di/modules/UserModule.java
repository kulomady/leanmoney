package com.kulomady.mycleanarchitecture.internal.di.modules;

/**
 * Dagger module that provides user related collaborators.
 * Created by kulomady on 5/6/16.
 */

import com.kulomady.domain.executor.PostExecutionThread;
import com.kulomady.domain.executor.ThreadExecutor;
import com.kulomady.domain.interactor.GetUserDetails;
import com.kulomady.domain.interactor.GetUserList;
import com.kulomady.domain.interactor.UseCase;
import com.kulomady.domain.repository.UserRepository;
import com.kulomady.mycleanarchitecture.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;


@Module
public class UserModule {
    private int userId = -1;

    public UserModule() {}

    public UserModule(int userId) {
        this.userId = userId;
    }

    @Provides @PerActivity @Named("userList")
    UseCase provideGetUserListUseCase(GetUserList getUserList){
        return getUserList;
    }

    @Provides @PerActivity @Named("userDetails") UseCase provideGetUserDetailsUseCase(
            UserRepository userRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {

        return new GetUserDetails(userId, userRepository, threadExecutor, postExecutionThread);
    }
}

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
import com.kulomady.mycleanarchitecture.internal.di.PerActivity1;
import com.kulomady.mycleanarchitecture.model.TestModelDagger2;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class SigninModule {
    private String email = "email";
    private String password ="password";

    public SigninModule() {}

    public SigninModule(String email,String password) {
       this.email = email;
        this.password = password;
    }

    @Provides @PerActivity1
    TestModelDagger2 provideTestModel2(){
        return new TestModelDagger2(email, password);
    }
}

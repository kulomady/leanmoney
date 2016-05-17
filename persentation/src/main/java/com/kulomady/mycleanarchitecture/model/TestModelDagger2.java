package com.kulomady.mycleanarchitecture.model;

import com.kulomady.mycleanarchitecture.internal.di.PerActivity;
import com.kulomady.mycleanarchitecture.internal.di.PerActivity1;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * Created by kulomady on 5/9/16.
 */

@PerActivity1
public class TestModelDagger2 {
    private String email;
    private String password;


    public TestModelDagger2(){}

    @Inject
    public TestModelDagger2(String email, String password){
        this.email = email;
        this.password = password;


    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return password;
    }
}

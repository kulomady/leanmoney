package com.kulomady.data.net;

import com.kulomady.data.config.DataConfig;
import com.kulomady.data.entity.UserEntity;
import com.kulomady.data.executor.LoggingInterceptor;

import org.w3c.dom.Comment;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


/**
 *
 * Created by kulomady on 5/8/16.
 */
public class UserService{

    private UserApi userApi;

    public UserService() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataConfig.API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        userApi = retrofit.create(UserApi.class);
    }


    public UserApi getApi() {
        return userApi;
    }

    public interface UserApi {

        @Headers("Content-Type: application/json; charset=utf-8")
        @GET("users.json")
        Observable<List<UserEntity>> userEntityList();

        @GET("{id}")
        Observable<UserEntity> userEntityById(@Path("id") String userId);
    }

}

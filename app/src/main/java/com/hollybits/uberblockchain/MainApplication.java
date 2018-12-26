package com.hollybits.uberblockchain;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import io.paperdb.Paper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainApplication extends Application {


    private static Retrofit retrofit;
    private static ServerRequests serverRequests;

    public static String USER_ID = "UserID";

    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(this);
        retrofitInit();


    }


    private void retrofitInit() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(ServerRequests.BASE_LOCAL) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create(gson)) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        serverRequests = retrofit.create(ServerRequests.class);
    }

    public static ServerRequests getServerRequests() {
        return serverRequests;
    }

}
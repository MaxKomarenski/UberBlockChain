package com.hollybits.uberblockchain;

import com.hollybits.uberblockchain.model.LoginRequest;
import com.hollybits.uberblockchain.model.Order;
import com.hollybits.uberblockchain.model.Parcel;
import com.hollybits.uberblockchain.model.ParcelCreation;
import com.hollybits.uberblockchain.model.RegistrationRequest;
import com.hollybits.uberblockchain.model.RegistrationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerRequests {

    String BASE_LOCAL = "http://10.0.2.2:8080/";

    @POST("/register")
    Call<RegistrationResponse> registerNewUser(@Body RegistrationRequest registrationRequest);

    @POST("/login")
    Call<Long> login(@Body LoginRequest loginRequest);

    @POST("/newParcelCreation")
    Call<Void> createNewParcel(@Body ParcelCreation creation);

    @GET("/parcels")
    Call<List<Parcel>> getAllParcels();

    @POST("/becomeCourier")
    Call<Void> becomeCourier(@Query("parcelId") Long id, @Query("userId") Long userId);

    @POST("/deliverOrder")
    Call<Void> deliverOrder(@Query("parcelId") Long parcelId);

    @GET("/getParcelsToDeliver")
    Call<List<Parcel>> getParcelToDeliver(@Query("id") Long id);
}

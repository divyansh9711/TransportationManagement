package com.example.divyanshsingh.transportationmanagement.API;


import com.example.divyanshsingh.transportationmanagement.payloads.LocationPayload;
import com.example.divyanshsingh.transportationmanagement.payloads.UserPayload;
import com.example.divyanshsingh.transportationmanagement.payloads.VehiclePayload;
import com.example.divyanshsingh.transportationmanagement.response.LocationResponse;
import com.example.divyanshsingh.transportationmanagement.response.UserResponse;
import com.example.divyanshsingh.transportationmanagement.response.VehicleResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
/**
 * @author Divyansh Singh
 *
 */
public interface ApiInterface {

    @POST("location/getLocation")
    Call<LocationResponse> getLocation(@Body LocationPayload payload);
    @POST("location/createLocation")
    Call<LocationResponse> createLocation(@Body LocationPayload payload);
    @POST("user/getUser")
    Call<UserResponse> getUser(@Body UserPayload payload);
    @POST("user/createUser")
    Call<UserResponse> createUser(@Body UserPayload payload);
    @POST("vehicle/getVehicle")
    Call<VehicleResponse> getVehicle(@Body VehiclePayload payload);
}

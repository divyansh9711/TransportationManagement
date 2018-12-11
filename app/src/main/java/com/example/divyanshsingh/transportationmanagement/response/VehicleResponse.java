package com.example.divyanshsingh.transportationmanagement.response;

import com.example.divyanshsingh.transportationmanagement.models.Vehicle;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VehicleResponse {
    @SerializedName("baseResponse")
    @Expose
    private BaseResponse baseResponse;
    @SerializedName("vehicle")
    @Expose
    private Vehicle vehicle;
    @SerializedName("vehicleList")
    @Expose
    private ArrayList<Vehicle> vehicleList;

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }
    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }
    public void setVehicleList(ArrayList<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

}

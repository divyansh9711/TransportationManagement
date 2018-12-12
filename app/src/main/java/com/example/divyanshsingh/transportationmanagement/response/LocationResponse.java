package com.example.divyanshsingh.transportationmanagement.response;

import com.example.divyanshsingh.transportationmanagement.models.Location;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * @author Divyansh Singh
 *
 */
public class LocationResponse {

    @SerializedName("baseResponse")
    @Expose
    private BaseResponse baseResponse;
    @SerializedName("locationResponse")
    @Expose
    private Location location;
    @SerializedName("locationList")
    @Expose
    private List<Location> locationList;

    public LocationResponse() {}

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public Location getLocation(Location payload) {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

}

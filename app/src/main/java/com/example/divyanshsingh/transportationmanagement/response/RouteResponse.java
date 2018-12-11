package com.example.divyanshsingh.transportationmanagement.response;

import com.example.divyanshsingh.transportationmanagement.models.Route;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
/**
 * @author Divyansh Singh
 *
 */
public class RouteResponse {
    @SerializedName("baseResponse")
    @Expose
    private BaseResponse baseResponse;
    @SerializedName("route")
    @Expose
    private Route route;
    @SerializedName("routeList")
    @Expose
    private ArrayList<Route> routeList;

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }
    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }
    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }
    public ArrayList<Route> getRouteList() {
        return routeList;
    }
    public void setRouteList(ArrayList<Route> routeList) {
        this.routeList = routeList;
    }

}

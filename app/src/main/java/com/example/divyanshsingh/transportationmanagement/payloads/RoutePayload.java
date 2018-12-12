package com.example.divyanshsingh.transportationmanagement.payloads;

import com.example.divyanshsingh.transportationmanagement.models.Route;
/**
 * @author Divyansh Singh
 *
 */
public class RoutePayload extends Payload {
    private Route route;
    private String routeId;

    public RoutePayload(){super();}
    public RoutePayload(String routeId){this.routeId = routeId;}

    public String getRouteId() {
        return routeId;
    }
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }


}

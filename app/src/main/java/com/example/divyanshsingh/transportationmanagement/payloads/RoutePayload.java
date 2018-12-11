package com.example.divyanshsingh.transportationmanagement.payloads;

import com.example.divyanshsingh.transportationmanagement.models.Route;
/**
 * @author Divyansh Singh
 *
 */
public class RoutePayload extends Payload {
    private Route route;
    private String routeId;

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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    private String id;
}

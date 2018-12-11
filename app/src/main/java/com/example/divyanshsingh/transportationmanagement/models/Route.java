package com.example.divyanshsingh.transportationmanagement.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Route extends Profile implements Parcelable{
    @SerializedName("subRoutes")
    @Expose
    private List<Route> subRoutes;
    @SerializedName("startLocation")
    @Expose
    private Location startLocation;
    @SerializedName("endLocation")
    @Expose
    private Location endLocation;
    @SerializedName("timing")
    @Expose
    private List<Timing> timing;
    @SerializedName("fare")
    @Expose
    private String fare;
    @SerializedName("interval")
    @Expose
    private Timing interval;

    protected Route(Parcel in) {
        super(in);
        subRoutes = in.createTypedArrayList(Route.CREATOR);
        startLocation = in.readParcelable(Location.class.getClassLoader());
        endLocation = in.readParcelable(Location.class.getClassLoader());
        fare = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(subRoutes);
        dest.writeParcelable(startLocation, flags);
        dest.writeParcelable(endLocation, flags);
        dest.writeString(fare);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Route> CREATOR = new Creator<Route>() {
        @Override
        public Route createFromParcel(Parcel in) {
            return new Route(in);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
        }
    };

    public Timing getInterval() {
        return interval;
    }
    public void setInterval(Timing interval) {
        this.interval = interval;
    }
    public List<Route> getSubRoutes() {
        return subRoutes;
    }
    public void setSubRoutes(List<Route> subRoutes) {
        this.subRoutes = subRoutes;
    }
    public Location getStartLocation() {
        return startLocation;
    }
    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }
    public Location getEndLocation() {
        return endLocation;
    }
    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }
    public List<Timing> getTiming() {
        return timing;
    }
    public void setTiming(List<Timing> timing) {
        this.timing = timing;
    }
    public String getFare() {
        return fare;
    }
    public void setFare(String fare) {
        this.fare = fare;
    }

}

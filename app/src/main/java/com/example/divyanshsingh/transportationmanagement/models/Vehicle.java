package com.example.divyanshsingh.transportationmanagement.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Divyansh Singh
 *
 */
public class Vehicle extends Profile implements Parcelable {
    @SerializedName("vehicleName")
    @Expose
    private String vehicleName;
    @SerializedName("vehicleRegNum")
    @Expose
    private String vehicleRegNum;
    @SerializedName("vehicleType")
    @Expose
    private Category vehicleType;
    @SerializedName("owner")
    @Expose
    private User owner;
    @SerializedName("driver")
    @Expose
    private User driver;
    @SerializedName("route")
    @Expose
    private Route route;
    @SerializedName("startLocation")
    @Expose
    private Location startLocation;
    @SerializedName("endLocation")
    @Expose
    private Location endLocation;
    @SerializedName("timing")
    @Expose
    private Timing timing;
    @SerializedName("sub")
    @Expose
    private List<Route> sub;

    protected Vehicle(Parcel in) {
        super(in);
        vehicleName = in.readString();
        vehicleRegNum = in.readString();
        vehicleType = in.readParcelable(Category.class.getClassLoader());
        owner = in.readParcelable(User.class.getClassLoader());
        driver = in.readParcelable(User.class.getClassLoader());
        route = in.readParcelable(Route.class.getClassLoader());
        startLocation = in.readParcelable(Location.class.getClassLoader());
        endLocation = in.readParcelable(Location.class.getClassLoader());
        timing = in.readParcelable(Timing.class.getClassLoader());
        sub = in.createTypedArrayList(Route.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeString(vehicleName);
        dest.writeString(vehicleRegNum);
        dest.writeParcelable(vehicleType, flags);
        dest.writeParcelable(owner, flags);
        dest.writeParcelable(driver, flags);
        dest.writeParcelable(route, flags);
        dest.writeParcelable(startLocation, flags);
        dest.writeParcelable(endLocation, flags);
        dest.writeParcelable(timing, flags);
        dest.writeTypedList(sub);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    public List<Route> getSub() {
        return sub;
    }
    public void setSub(List<Route> sub) {
        this.sub = sub;
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
    public User getDriver() {
        return driver;
    }
    public void setDriver(User driver) {
        this.driver = driver;
    }
    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
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
    public Timing getTiming() {
        return timing;
    }
    public void setTiming(Timing timing) {
        this.timing = timing;
    }
    public String getVehicleName() {
        return vehicleName;
    }
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
    public String getVehicleRegNum() {
        return vehicleRegNum;
    }
    public void setVehicleRegNum(String vehicleRegNum) {
        this.vehicleRegNum = vehicleRegNum;
    }
    public Category getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(Category vehicleType) {
        this.vehicleType = vehicleType;
    }
}

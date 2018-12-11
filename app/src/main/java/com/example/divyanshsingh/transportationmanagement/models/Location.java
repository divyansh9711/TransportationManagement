package com.example.divyanshsingh.transportationmanagement.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location extends Profile implements Parcelable {
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("zipcode")
    @Expose
    private String zipCode;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("locationCategory")
    @Expose
    private Category locationCategory;  //648: default
    @SerializedName("country")
    @Expose
    private String country;

    public Location() {
        super();
    }



    public Location(String latitude, String longitude, String locality, String zipCode, String city, String state,
                    String country) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.locality = locality;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.country = country;
    }


    protected Location(Parcel in) {
        super(in);
        latitude = in.readString();
        longitude = in.readString();
        locality = in.readString();
        zipCode = in.readString();
        city = in.readString();
        state = in.readString();
        country = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(locality);
        dest.writeString(zipCode);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public Category getLocationCategory() {
        return locationCategory;
    }



    public void setLocationCategory(Category locationCategory) {
        this.locationCategory = locationCategory;
    }



    public String getState() {
        return state;
    }
    public void setState(String string) {
        state = string;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLocality() {
        return locality;
    }
    public void setLocality(String locality) {
        this.locality = locality;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}

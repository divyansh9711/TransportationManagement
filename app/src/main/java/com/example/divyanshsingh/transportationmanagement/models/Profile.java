package com.example.divyanshsingh.transportationmanagement.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile implements Parcelable {
    @SerializedName("uId")
    @Expose
    private String uId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("analytics")
    @Expose
    private Analytics analytics;
    @SerializedName("creator")
    @Expose
    private CreatorInfo creator;

    public Profile() {}

    protected Profile(Parcel in) {
        uId = in.readString();
        title = in.readString();
        info = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uId);
        dest.writeString(title);
        dest.writeString(info);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public String getuId() {
        return uId;
    }
    public void setuId(String uId) {
        this.uId = uId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Media getMedia() {
        return media;
    }
    public void setMedia(Media media) {
        this.media = media;
    }
    public Analytics getAnalytics() {
        return analytics;
    }
    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }

    public CreatorInfo getCreator() {
        return creator;
    }
    public void setCreator(CreatorInfo creator) {
        this.creator = creator;
    }

}

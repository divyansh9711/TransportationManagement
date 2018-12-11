package com.example.divyanshsingh.transportationmanagement.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatorInfo implements Parcelable{
    @SerializedName("createTime")
    @Expose
    private Timing createTime;
    @SerializedName("creatorId")
    @Expose
    private String creatorId;
    @SerializedName("creatorName")
    @Expose
    private String creatorName;


    protected CreatorInfo(Parcel in) {
        creatorId = in.readString();
        creatorName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(creatorId);
        dest.writeString(creatorName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CreatorInfo> CREATOR = new Creator<CreatorInfo>() {
        @Override
        public CreatorInfo createFromParcel(Parcel in) {
            return new CreatorInfo(in);
        }

        @Override
        public CreatorInfo[] newArray(int size) {
            return new CreatorInfo[size];
        }
    };

    public Timing getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timing createTime) {
        this.createTime = createTime;
    }
    public String getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
    public String getCreatorName() {
        return creatorName;
    }
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }


}

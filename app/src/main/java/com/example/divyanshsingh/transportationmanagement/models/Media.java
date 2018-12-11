package com.example.divyanshsingh.transportationmanagement.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Media implements Parcelable{
    @SerializedName("mediaList")
    @Expose
    private List<Media> mediaList;
    @SerializedName("mediaUrl")
    @Expose
    private String mediaUrl;
    @SerializedName("mediaUrls")
    @Expose
    private List<String> mediaUrls;
    @SerializedName("mediaInfo")
    @Expose
    private String mediaInfo;
    @SerializedName("mediaId")
    @Expose
    private String mediaId;
    @SerializedName("mediaTitle")
    @Expose
    private String mediaTitle;
    @SerializedName("mediaCategory")
    @Expose
    private Category mediaCategory;


    protected Media(Parcel in) {
        mediaList = in.createTypedArrayList(Media.CREATOR);
        mediaUrl = in.readString();
        mediaUrls = in.createStringArrayList();
        mediaInfo = in.readString();
        mediaId = in.readString();
        mediaTitle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mediaList);
        dest.writeString(mediaUrl);
        dest.writeStringList(mediaUrls);
        dest.writeString(mediaInfo);
        dest.writeString(mediaId);
        dest.writeString(mediaTitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    public List<Media> getMediaList() {
        return mediaList;
    }
    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }
    public String getMediaUrl() {
        return mediaUrl;
    }
    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
    public List<String> getMediaUrls() {
        return mediaUrls;
    }
    public void setMediaUrls(List<String> mediaUrls) {
        this.mediaUrls = mediaUrls;
    }
    public String getMediaInfo() {
        return mediaInfo;
    }
    public void setMediaInfo(String mediaInfo) {
        this.mediaInfo = mediaInfo;
    }
    public String getMediaId() {
        return mediaId;
    }
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    public String getMediaTitle() {
        return mediaTitle;
    }
    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }
    public Category getMediaCategory() {
        return mediaCategory;
    }
    public void setMediaCategory(Category mediaCategory) {
        this.mediaCategory = mediaCategory;
    }
}

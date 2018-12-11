package com.example.divyanshsingh.transportationmanagement.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable{
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("categoryInfo")
    @Expose
    private String categoryInfo;
    @SerializedName("categoryTitle")
    @Expose
    private String categoryTitle;


    protected Category(Parcel in) {
        categoryId = in.readString();
        categoryInfo = in.readString();
        categoryTitle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categoryId);
        dest.writeString(categoryInfo);
        dest.writeString(categoryTitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryInfo() {
        return categoryInfo;
    }
    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
    }
    public String getCategoryTitle() {
        return categoryTitle;
    }
    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}

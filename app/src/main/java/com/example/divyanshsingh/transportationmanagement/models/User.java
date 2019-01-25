package com.example.divyanshsingh.transportationmanagement.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User extends Profile implements Parcelable {
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("userMobile")
    @Expose
    private String userMobile;
    @SerializedName("userEmail")
    @Expose
    private String userEmail;
    @SerializedName("userImage")
    @Expose
    private String userImage;
    @SerializedName("gender")
    @Expose
    private Category gender;
    @SerializedName("userCategory")
    @Expose
    private Category userCategory;

    public User(){}

    protected User(Parcel in) {
        super(in);
        firstName = in.readString();
        lastName = in.readString();
        userName = in.readString();
        userMobile = in.readString();
        userEmail = in.readString();
        userImage = in.readString();
        gender = in.readParcelable(Category.class.getClassLoader());
        userCategory = in.readParcelable(Category.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(userName);
        dest.writeString(userMobile);
        dest.writeString(userEmail);
        dest.writeString(userImage);
        dest.writeParcelable(gender, flags);
        dest.writeParcelable(userCategory, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setName(String fullName){
        int index = fullName.indexOf(" ");
        firstName = fullName.substring(0,index);
        lastName = fullName.substring(index + 1,fullName.length()-1);
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserMobile() {
        return userMobile;
    }
    public void setUserMobile(String userPh) {
        this.userMobile = userPh;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserImage() {
        return userImage;
    }
    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
    public Category getGender() {
        return gender;
    }
    public void setGender(Category gender) {
        this.gender = gender;
    }
    public Category getUserCategory() {
        return userCategory;
    }
    public void setUserCategory(Category userCategory) {
        this.userCategory = userCategory;
    }

}

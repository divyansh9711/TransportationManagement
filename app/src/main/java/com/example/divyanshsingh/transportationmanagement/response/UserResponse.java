package com.example.divyanshsingh.transportationmanagement.response;

import com.example.divyanshsingh.transportationmanagement.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserResponse {
    @SerializedName("baseResponse")
    @Expose
    private BaseResponse baseResponse;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("userList")
    @Expose
    private ArrayList<User> userList;

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }
    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public ArrayList<User> getUserList() {
        return userList;
    }
    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

}

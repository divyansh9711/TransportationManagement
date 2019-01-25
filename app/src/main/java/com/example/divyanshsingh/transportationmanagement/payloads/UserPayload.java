package com.example.divyanshsingh.transportationmanagement.payloads;

import com.example.divyanshsingh.transportationmanagement.models.User;
import com.example.divyanshsingh.transportationmanagement.models.Vehicle;
/**
 * @author Divyansh Singh
 *
 */
public class UserPayload extends Payload {

    private User user;

    public UserPayload (){super();}
    
    public UserPayload(User user) {
        this.user = user;
    }

    public UserPayload( String info, User user) {
        super(info);
        this.user = user;
    }


}

package com.example.divyanshsingh.transportationmanagement.API;
/**
 * @author Divyansh Singh
 *
 */
public class APIError {
    private int statusCode;
    private int code;
    private String message;
    private String error;
    private String errorMessage;
    private String status;
    private Object data;
    private int status900=900;

    /**
     * @param statusCode
     * @param message
     */
    public APIError(int statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
    }


    /**
     * @return
     */
    public int getStatusCode() {
        if (statusCode == 0)
            if (code != 0)
                return code;
            else
                statusCode = status900;
        return statusCode;
    }


    /**
     * @return
     */
    public String getMessage() {
        if (message == null) {
            if (error != null)
                return error;
            else if (errorMessage != null)
                return errorMessage;
            else
                return ResponseResolver.UNEXPECTED_ERROR_OCCURRED;
        } else
            return message;
    }


    /**
     * @return
     */
    public boolean isEmptyObject() {
        if ((statusCode == 0) && (code == 0) && (message == null) && (error == null) && (errorMessage == null) && (status == null) && (data == null)){
            return true;
        }
        else {
            return false;
        }
    }
}

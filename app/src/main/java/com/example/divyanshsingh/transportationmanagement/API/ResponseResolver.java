package com.example.divyanshsingh.transportationmanagement.API;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.util.SortedList;
import android.util.Log;

import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ResponseResolver<T> implements Callback<T> {
    private Context context;
    private Boolean showLoading = false;
    private Boolean showError = false;

    private final static String NO_INTERNET_MESSAGE = "No internet connection. Please try again later.";
    private final static String REMOTE_SERVER_FAILED_MESSAGE = "Somethings wrong with your network. Please try again after checking your connection.";
    public final static String UNEXPECTED_ERROR_OCCURRED = "Something went wrong. Please try again later";
    private final static String PARSING_ERROR = "Parsing error";
    private final static String FILE_NOT_FOUND_ERROR = "File not found";

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    private int status900 = 900;
    private int error401 = 401;
    private ProgressDialog dialog;

    /**
     * @param activity
     */
    public ResponseResolver(Context activity) {
        this.context=activity;
        dialog = new ProgressDialog(activity);
    }


    /**
     * @param activity
     * @param showLoading
     */
    public ResponseResolver(Context activity, Boolean showLoading) {
        this.context=activity;
        this.showLoading = showLoading;
        if (showLoading) {
            dialog = new ProgressDialog(activity);
            dialog.show();
        }
    }

    /**
     * @param activity
     * @param showLoading
     * @param loadingTxt  : change it to change loading text under progress bar
     */
    public ResponseResolver(Context activity, Boolean showLoading, String loadingTxt) {
        this.context=activity;
        this.showLoading = showLoading;
        if (showLoading) {
            dialog = new ProgressDialog(activity);
            dialog.show();
        }
    }


    /**
     * @param activity
     * @param showLoading
     * @param showError
     */
    public ResponseResolver(Context activity, Boolean showLoading, Boolean showError) {
        this.context=activity;
        this.showLoading = showLoading;
        this.showError = showError;
        if (showLoading) {
            dialog = new ProgressDialog(activity);
            dialog.setMessage("Loading...! Please Wait.");
            dialog.setCancelable(false);
            dialog.show();
        }

    }

    public abstract void success(T t);

    public abstract void failure(APIError error);

    @Override
    public void onResponse(Call<T> t, Response<T> tResponse) {
        if (dialog!=null&&dialog.isShowing()){
            try {
                dialog.dismiss();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (tResponse.isSuccessful())
            success(tResponse.body());
        else
            fireError(ErrorUtils.parseError(tResponse));
    }

    @Override
    public void onFailure(Call<T> t, Throwable throwable) {
        if (dialog!=null&&dialog.isShowing())
            dialog.dismiss();
        fireError(new APIError(status900, resolveNetworkError(throwable)));
    }


    /**
     * @param apiError
     */
    public void fireError(APIError apiError) {
        failure(apiError);
        if (showError) {
            if (context != null) {
                if (checkAuthorizationError(apiError)) {
                    return;
                }
            }

        }

        failure(apiError);
    }

    /**
     * @param apiError
     * @return
     */
    public Boolean checkAuthorizationError(APIError apiError) {


        if (showError) {

            //SingleBtnDialog.with(context).setMessage(apiError.getMessage()).hideHeading().show();

        }

        return true;
    }

    /**
     * Method resolve network errors
     *
     * @param cause
     * @return
     */

    private String resolveNetworkError(Throwable cause) {
        Log.e("resolveNetworkError =", String.valueOf(cause.toString()));

        if (cause instanceof UnknownHostException)
            return NO_INTERNET_MESSAGE;
        else if (cause instanceof SocketTimeoutException)
            return REMOTE_SERVER_FAILED_MESSAGE;
        else if (cause instanceof ConnectException)
            return NO_INTERNET_MESSAGE;
        else if (cause instanceof JsonSyntaxException)
            return PARSING_ERROR;
        else if (cause instanceof FileNotFoundException)
            return FILE_NOT_FOUND_ERROR;
        return UNEXPECTED_ERROR_OCCURRED;
    }

}

package com.example.divyanshsingh.transportationmanagement.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.divyanshsingh.transportationmanagement.R;

/**
 * Created By Divyansh Singh
 */
public class SingleButtonError {

    private Dialog dialog;
    private Context context;

    private SingleButtonError(Context context) {
        if (dialog != null) {
            dialog.dismiss();
        }
        // custom dialog
        dialog = new Dialog(context);
        this.context = context;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.custom_single_btn_layout);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Button btnOk = dialog.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    public static SingleButtonError with(Context context) {
        return new SingleButtonError(context);
    }

    public SingleButtonError setMessage(String msg) {
        if (dialog != null) {
            TextView tvMessage = dialog.findViewById(R.id.tv_message);
            tvMessage.setText(msg);
        }
        return this;
    }

    public SingleButtonError setCancelable(boolean bool) {
        if (dialog != null) {
            dialog.setCancelable(false);
        }
        return this;
    }

    public SingleButtonError setCancelableOnTouchOutside(boolean bool) {
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(false);
        }
        return this;
    }

    public SingleButtonError setHeading(String heading) {
        if (dialog != null) {
            TextView tvMessage = dialog.findViewById(R.id.tv_heading);
            tvMessage.setText(heading);
            tvMessage.setVisibility(View.VISIBLE);
        }
        return this;
    }
    public SingleButtonError hideHeading() {
        if (dialog != null) {
            TextView tvMessage = dialog.findViewById(R.id.tv_heading);
            tvMessage.setVisibility(View.GONE);
        }
        return this;
    }
    public SingleButtonError setOptionPositive(String optionPositive) {
        if (dialog != null) {
            Button btnOk = dialog.findViewById(R.id.btn_ok);
            btnOk.setText(optionPositive);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
        return this;
    }

    public SingleButtonError setCallback(final OnActionPerformed onActionPerformed) {
        if (dialog != null && onActionPerformed != null) {
            Button btnOk = dialog.findViewById(R.id.btn_ok);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    onActionPerformed.positive();
                }
            });
        }
        return this;
    }

    public Dialog show() {
        if (dialog != null&& !((AppCompatActivity)context).isFinishing()) {
            dialog.show();
            return dialog;
        }
        return null;
    }

    public interface OnActionPerformed {
        void positive();


    }
}

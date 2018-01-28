package com.jain.vidhyasagarsant.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtils {

    public static ProgressDialog mProgressDialog = null;

    public static void displayProgressDialog(Context context, String message) {
        if (mProgressDialog == null && context != null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage(message);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.show();
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(false);
        }
    }

    public static void cancelProgressDialog() {
        if (mProgressDialog != null) {
            try {
                mProgressDialog.dismiss();
                mProgressDialog.cancel();
                mProgressDialog = null;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}

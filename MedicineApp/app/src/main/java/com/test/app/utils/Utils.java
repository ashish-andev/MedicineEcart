package com.test.app.utils;

import android.widget.Toast;

import com.test.app.MyApplication;

import java.io.IOException;
import java.io.InputStream;


public final class Utils {

    private Utils() {
        // This class is not publicly instantiable


    }


    public static String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = MyApplication.getInstance().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static void showToast(String message) {
        try {
            Toast.makeText(MyApplication.getInstance(), message, Toast.LENGTH_LONG).show();
        } catch (Throwable ignored) {
        }
    }

}

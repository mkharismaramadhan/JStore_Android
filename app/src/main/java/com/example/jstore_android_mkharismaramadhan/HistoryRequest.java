package com.example.jstore_android_mkharismaramadhan;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class HistoryRequest extends StringRequest {
    private static final String HISTORY_URL = "http://10.0.2.2:8080/invoicehistorycust/";

    public HistoryRequest(int id_customer, Response.Listener<String> listener) {

        super(Method.GET, HISTORY_URL+id_customer, listener, null);
        Log.d("", "History Request: "+id_customer);

    }
}

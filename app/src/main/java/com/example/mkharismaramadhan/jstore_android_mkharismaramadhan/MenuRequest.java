package com.example.mkharismaramadhan.jstore_android_mkharismaramadhan;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MenuRequest extends StringRequest {
    private static final String Regis_URL = "http://10.10.52.135:8080/items";
    private Map<String, String> params;

    public MenuRequest(int id, Response.Listener<String> listener){
        super(Method.GET, Regis_URL, listener, null);
        params = new HashMap<>();
        params.put("id", Integer.toString(id));
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}

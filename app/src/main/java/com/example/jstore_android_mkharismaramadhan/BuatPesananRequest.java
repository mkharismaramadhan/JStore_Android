package com.example.jstore_android_mkharismaramadhan;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class BuatPesananRequest extends StringRequest {
    private Map<String, String> params;
    public BuatPesananRequest(String item, String id, String url, Response.Listener<String> listener) {
        super(Method.POST, url, listener, null);
        params = new HashMap<>();
        this.params.put("listitem", item);
        this.params.put("id",id);
    }
    //sellinstallment
    public BuatPesananRequest(String item,String period, String id, String url, Response.Listener<String> listener) {
        super(Method.POST, url, listener, null);
        params = new HashMap<>();
        params.put("listitem", item);
        params.put("period",period);
        params.put("id",id);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}

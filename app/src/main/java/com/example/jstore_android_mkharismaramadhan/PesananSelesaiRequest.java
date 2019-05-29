package com.example.jstore_android_mkharismaramadhan;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PesananSelesaiRequest extends StringRequest {
    private static final String REGIS_URL = "http://10.0.2.2:8080/finishtransaction";
    private Map<String, String> params;

    public PesananSelesaiRequest(String id, Response.Listener<String> listener) {
        super(Method.POST, REGIS_URL, listener, null);
        params = new HashMap<>();
        params.put("id",id);
    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}

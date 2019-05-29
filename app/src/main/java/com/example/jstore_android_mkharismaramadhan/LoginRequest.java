package com.example.jstore_android_mkharismaramadhan;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest
{
    private static final String Login_URL = "http://10.0.2.2:8080/logincust";
    private Map<String, String> params;

    public LoginRequest(String email, String password, Response.Listener<String> listener)
    {
        super(Method.POST, Login_URL, listener, null);
        params  = new HashMap<>();
        params.put("email",email);
        params.put("password",password);
    }

    @Override
    protected Map<String, String> getParams()
    {
        return params;
    }
}

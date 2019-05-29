package com.example.jstore_android_mkharismaramadhan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HistoryActivity extends AppCompatActivity {
    private ArrayList<Item> listItem = new ArrayList<>();

    private ArrayList<Invoice> invoices = new ArrayList<>();

    private static int currentUserId;
    private static String currentUserName;

    TextView tvHistory;
    RecyclerView rvHistory;
    Toolbar toolbar;

    public static int getCustomerId() {
        return currentUserId;
    }


    public static String getCustomerName() {
        return currentUserName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUserId = extras.getInt("currentUserId");
            currentUserName = extras.getString("currentUserName");
        }

        tvHistory = findViewById(R.id.tvHistory);
        rvHistory = findViewById(R.id.rvHistory);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initRecyclerView();


    }

    private void initRecyclerView() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    Log.d("reg_resp", "Register Response: " + response.toString());
                    for (int i = 0; i < jsonResponse.length(); i++) {
                        JSONObject invoice = jsonResponse.getJSONObject(i);
                        int id = invoice.getInt("id");
                        String date = invoice.getString("date");
                        JSONArray item_json = invoice.getJSONArray("item");

                        System.out.println("item json"+item_json.opt(1));
                        System.out.println(item_json.isNull(1));

                        Log.d("items", String.valueOf(item_json.length()));

                        ArrayList<String> items = new ArrayList<>();
                        for (int y = 0; y < item_json.length(); y++) {
                            for (Item item : listItem) {
                                if (item.getId() == Integer.valueOf(item_json.get(y).toString().trim())) {
                                    items.add(item.getName());
                                }
                            }
                        }

                        Log.d("items", String.valueOf(items));
                        String invoiceType = invoice.getString("invoiceType");
                        String invoiceStatus = invoice.getString("invoiceStatus");
                        Integer totalPrice = invoice.getInt("totalPrice");

//                        System.out.println("items ="+items.get(0));
                        if (invoiceStatus.equals("Paid")) {
                            Invoice temp = new Invoice(id, date, items, totalPrice, invoiceType, invoiceStatus);
                            temp.setActive(false);
                            invoices.add(temp);
                        } else if (invoiceStatus.equals("Unpaid")) {
                            Invoice temp = new Invoice(id, date, items, totalPrice, invoiceType, invoiceStatus);
                            temp.setActive(true);
                            invoices.add(temp);
                        } else if (invoiceStatus.equals("Installment")) {
                            int installmentPeriod = invoice.getInt("installmentPeriod");
                            int installmentPrice = invoice.getInt("installmentPrice");
                            Invoice temp = new Invoice(id, date, items, totalPrice, invoiceType, invoiceStatus, installmentPeriod, installmentPrice);
                            temp.setActive(true);
                            invoices.add(temp);
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HistoryRecyclerViewAdapter adapter = new HistoryRecyclerViewAdapter(invoices, HistoryActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                rvHistory.setLayoutManager(layoutManager);
                rvHistory.setAdapter(adapter);

            }

        };
        HistoryRequest request = new HistoryRequest(currentUserId, responseListener);
        RequestQueue historyQueue = new Volley().newRequestQueue(HistoryActivity.this);
        historyQueue.add(request);


    }


}

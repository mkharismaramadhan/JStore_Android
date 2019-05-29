package com.example.jstore_android_mkharismaramadhan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.OrderViewHolder> {
    private ArrayList<Invoice> invoices;
    private Context context;

    public HistoryRecyclerViewAdapter(ArrayList<Invoice> invoices, Context context) {
        this.invoices = invoices;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_order_historyitem, viewGroup, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {

        final Invoice invoice = invoices.get(i);
        StringBuilder text = new StringBuilder();
        text = text.append(invoices.get(i).getItem().get(0));
        if (invoice.getItem().size() >= 1) {
            for (int x = 1; x < invoice.getItem().size(); x++) {
                text.append(", ").append(invoice.getItem().get(x));
            }
        }
        orderViewHolder.tvHistoryItem.setText(text.toString());

        orderViewHolder.tvHistoryItem.setText("Invoice "+invoice.getId());
        orderViewHolder.tvHistoryItem.setText(invoices.get(i).getItem()+"");
        orderViewHolder.tvHistoryPrice.setText("Rp. " + (invoice.getTotalPrice()));
        if (invoice.getInvoiceStatus().equals("Unpaid"))
        {
            orderViewHolder.tvHistoryCategory.setText("Paid (Later)");
        }
        else {
            orderViewHolder.tvHistoryCategory.setText(invoice.getInvoiceStatus());
        }
        Log.d("totalPrice", String.valueOf(invoice.getTotalPrice()));


        final String invoiceStat=invoice.getInvoiceStatus();

    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvHistoryItem, tvHistoryCategory, tvHistoryPrice;
        ConstraintLayout layout;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHistoryItem= itemView.findViewById(R.id.tvItemHistory);
            tvHistoryPrice = itemView.findViewById(R.id.tvPriceHistory);
            tvHistoryCategory = itemView.findViewById(R.id.tvPaymentHistory);
            layout = itemView.findViewById(R.id.layout);

        }
    }
}

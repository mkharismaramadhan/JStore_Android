package com.example.jstore_android_mkharismaramadhan;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.OrderViewHolder> {
    private ArrayList<Invoice> invoices;
    private Context context;

    public OrderRecyclerViewAdapter(ArrayList<Invoice> invoices, Context context) {
        this.invoices = invoices;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_order_listitem, viewGroup, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {

        final Invoice invoice = invoices.get(i);

        orderViewHolder.tvItem.setText(invoices.get(i).getItem()+"");
        orderViewHolder.tvCategory.setText(invoice.getInvoiceStatus());
        Log.d("totalPrice", String.valueOf(invoice.getTotalPrice()));
        orderViewHolder.tvPrice.setText("Rp. " + (invoice.getTotalPrice()));


        String itemName=invoices.get(i).getItem().toString();
        final String invoiceStat=invoice.getInvoiceStatus();

        orderViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SelesaiPesananActivity.class);
                intent.putExtra("id", invoice.getId());
                Log.d("", "invoice ID: "+invoice.getId());

                intent.putExtra("currentUserId",MainActivity.getCustomerId());
                intent.putExtra("currentUserName", MainActivity.getCustomerName());
                intent.putExtra("item_name", invoice.getItem().toString());
                intent.putExtra("invoice_date", invoice.getDate());
                intent.putExtra("total_price", invoice.getTotalPrice());
                intent.putExtra("invoice_status", invoice.getInvoiceStatus());
                intent.putExtra("invoice_type", invoice.getInvoiceType());
                Log.d("", "stats"+invoice.getInvoiceStatus());
                intent.putExtra("item_price", invoice.getTotalPrice());
                if(invoice.getInvoiceStatus().equals("Installment")) {
                    intent.putExtra("st_due_period", "Installment Period");
                    intent.putExtra("due_period", invoice.getInstallmentPeriod()+"Months");
                    intent.putExtra("item_price", invoice.getInstallmentPrice());
                }
                else if (invoice.getInvoiceStatus().equals("Unpaid"))//pay later
                {
                    intent.putExtra("st_due_period", "Due Date");
                    intent.putExtra("due_period", invoice.getDueDate()+"");
                    intent.putExtra("item_price", invoice.getTotalPrice());
                }

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem, tvCategory, tvPrice;
        ConstraintLayout layout;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCategory = itemView.findViewById(R.id.tvPayment);
            layout = itemView.findViewById(R.id.layout);

        }

    }
}

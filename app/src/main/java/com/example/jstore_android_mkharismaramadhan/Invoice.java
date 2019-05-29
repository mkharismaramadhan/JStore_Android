package com.example.jstore_android_mkharismaramadhan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Invoice {
    private int id;
    private String date;
    private boolean isActive;
    private ArrayList<String> items;
    private int totalPrice;
    private String invoiceType;
    private String invoiceStatus;
    private int installmentPeriod;
    private int installmentPrice;
    private String dueDate;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public Invoice(int id, String date, ArrayList<String> items, int totalPrice, String invoiceType, String invoiceStatus) {
        this.id = id;
        this.date = date;
        this.items = items;
        this.totalPrice = totalPrice;
        this.invoiceType = invoiceType;
        this.invoiceStatus = invoiceStatus;
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, 1);
        dueDate=sdf.format(calendar.getTime());
        this.setDueDate(dueDate);
        this.dueDate=getDueDate();
    }

    public Invoice(int id, String date, ArrayList<String> items, int totalPrice, String invoiceType, String invoiceStatus, int installmentPeriod, int installmentPrice) {
        this.id = id;
        this.date = date;
        this.items = items;
        this.totalPrice = totalPrice;
        this.invoiceType = invoiceType;
        this.invoiceStatus = invoiceStatus;
        this.installmentPeriod = installmentPeriod;
        this.installmentPrice = installmentPrice;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }


    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate)
    {
        this.dueDate=dueDate;

    }


    public boolean isActive() {
        return isActive;
    }

    public ArrayList<String> getItem() {
        return items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public int getInstallmentPeriod() {
        return installmentPeriod;
    }

    public int getInstallmentPrice() {
        return installmentPrice;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}
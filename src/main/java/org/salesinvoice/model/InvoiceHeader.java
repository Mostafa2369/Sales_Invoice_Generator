package org.salesinvoice.model;

import java.util.ArrayList;

public class InvoiceHeader {

    private int invoiceNum;
    private String invoiceDate;
    private String customerName;

    private ArrayList<InvoiceLines> mInvoicesLines;

    public InvoiceHeader(int invoiceNum, String invoiceDate, String customerName, ArrayList<InvoiceLines> mInvoicesLines) {
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.mInvoicesLines = mInvoicesLines;
    }

    public InvoiceHeader(int invoiceNum, String invoiceDate, String customerName) {
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;

    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<InvoiceLines> getmInvoicesLines() {
        return mInvoicesLines;
    }
}

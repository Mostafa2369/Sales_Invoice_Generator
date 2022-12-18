package org.salesinvoice.model;

public class InvoiceLines {
    private String itemName;
    private Double itemPrice;
    private int count;

    public InvoiceLines(String itemName, Double itemPrice, int count) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
    }

    public String getItemName() {
        return itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public int getCount() {
        return count;
    }

}

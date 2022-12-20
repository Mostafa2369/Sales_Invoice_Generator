package org.salesinvoice.view.table;

import org.salesinvoice.model.FileOperations;
import org.salesinvoice.model.InvoiceHeader;
import org.salesinvoice.model.InvoiceLines;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class ItemsTable extends JTable {
    private JTable table;
    private FileOperations invoicesData;
    private String[] cols = {"No.", "Item Name", "Item Price", "Count", "Item Total"};


    public ItemsTable(String[][]data) throws IOException {
        table = new JTable(data, cols);
    }

    public ItemsTable(){
        table = new JTable();
    }

    public JTable getTable() {




        return table;
    }

    public String[][] data(String invoiceNumS) throws IOException {
        invoicesData = new FileOperations();
        int invoiceNum = Integer.valueOf(invoiceNumS);
        String[][] dataList=new String[1][1];
        String path = "InvoiceHeader.csv";
        ArrayList<InvoiceHeader> invoicesList = new ArrayList<InvoiceHeader>();

        invoicesList = invoicesData.readFile(path,"InvoiceLine.csv");

        for (int i = 0; i < invoicesList.size(); i++) {
              if(invoicesList.get(i).getInvoiceNum()==invoiceNum){
          dataList = new String[invoicesList.get(i).getmInvoicesLines().size()][5];
            for (int j = 0; j < invoicesList.get(i).getmInvoicesLines().size(); j++) {

                dataList[j][0] = String.valueOf(invoicesList.get(i).getInvoiceNum());
                dataList[j][1] = invoicesList.get(i).getmInvoicesLines().get(j).getItemName();
                dataList[j][2] = String.valueOf(invoicesList.get(i).getmInvoicesLines().get(j).getItemPrice());
                dataList[j][3] = String.valueOf(invoicesList.get(i).getmInvoicesLines().get(j).getCount());
                dataList[j][4] = String.valueOf(invoicesList.get(i).getmInvoicesLines().get(j).getCount() *
                        invoicesList.get(i).getmInvoicesLines().get(j).getItemPrice());

            }
            break;
              }


        }
        return dataList;
    }

}

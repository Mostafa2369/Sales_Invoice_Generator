package org.salesinvoice.view.table;

import org.salesinvoice.model.FileOperations;
import org.salesinvoice.model.InvoiceHeader;
import org.salesinvoice.model.InvoiceLines;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class InvoicesTable extends JTable {
    private JTable table;
    private  FileOperations invoicesData;
    private String [] cols = {"No.","Date","Customer","Total"};


    public InvoicesTable() throws IOException {

        table = new JTable(data("InvoiceHeader.csv"),cols);

}
    public InvoicesTable(String[][] data) throws IOException {

        table = new JTable(data,cols);

    }

    public JTable getTable() {




        return table;
    }

    public String[][] data(String path1) throws IOException {
       invoicesData = new FileOperations();

        String path=path1;
        ArrayList<InvoiceHeader> invoicesList = new ArrayList<InvoiceHeader>();

        invoicesList=invoicesData.readFile(path);
        String[][] dataList =new String[invoicesList.size()][4];
        for (int i = 0; i<invoicesList.size() ; i++) {

                dataList[i][0]= String.valueOf(invoicesList.get(i).getInvoiceNum());
            dataList[i][1]=invoicesList.get(i).getInvoiceDate().replace('-','/');
            dataList[i][2]= String.valueOf(invoicesList.get(i).getCustomerName());
            dataList[i][3]=getTotalInvoice(invoicesList.get(i).getmInvoicesLines());




        }
   return  dataList;
    }
    private String getTotalInvoice (ArrayList<InvoiceLines> invoicesList){
         int total = 0;
        for (int i = 0; i < invoicesList.size(); i++) {
            total+=(invoicesList.get(i).getCount()*invoicesList.get(i).getItemPrice());
        }
      return String.valueOf(total);
    }


}

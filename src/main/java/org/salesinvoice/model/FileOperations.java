package org.salesinvoice.model;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.dom4j.CDATA;

public class FileOperations extends Component {

    private static ArrayList<InvoiceHeader> mReadFile;
    private static ArrayList<InvoiceLines> mReadItems;
   private static FileOperations data;
   public FileOperations(){

   }

    public  ArrayList<InvoiceHeader> readFile(String path,String path2) throws IOException {

        mReadFile = new ArrayList<InvoiceHeader>();


        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] invoiceValues = line.split(",");

                mReadItems = new ArrayList<InvoiceLines>();
                try (BufferedReader br1 = new BufferedReader(new FileReader(path2))) {
                    String lineItem;
                    while ((lineItem = br1.readLine()) != null) {

                        String[] itmeValues = lineItem.split(",");
                        if ((invoiceValues[0].replace("\"","")).equals(itmeValues[0])) {

                            InvoiceLines rowItem = new InvoiceLines(itmeValues[1], Double.parseDouble(itmeValues[2]), Integer.parseInt(itmeValues[3]));
                            mReadItems.add(rowItem);
                        }

                    }
                }

                        mReadFile.add(new InvoiceHeader(Integer.parseInt(invoiceValues[0].replace("\"","")), invoiceValues[1].replace("\"",""), invoiceValues[2].replace("\"",""), mReadItems));


            }
        }

        return mReadFile;
    }

    public  void writeFile(ArrayList<InvoiceHeader> readFile,String path) throws IOException {

        FileOperations s = new FileOperations();
        CSVWriter invoiceWriter = new CSVWriter(new FileWriter(path));

        ArrayList<String[]> invoiceData = new ArrayList<String[]>();
        for (int i = 0; i < readFile.size(); ++i) {
            invoiceData.add(new String[]{Integer.toString(readFile.get(i).getInvoiceNum()), readFile.get(i).getInvoiceDate(), readFile.get(i).getCustomerName()});

        }
        invoiceWriter.writeAll(invoiceData);

        invoiceWriter.close();

    }


    public  void testRead(String path) throws IOException {
     data=new FileOperations();
        ArrayList<InvoiceHeader> test = data.readFile(path,"InvoiceLine.csv");
        for (int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i).getInvoiceNum());
            System.out.println("{");
            System.out.println(test.get(i).getInvoiceDate().replace('-','/')+", "+test.get(i).getCustomerName());
            for (int j = 0; j <test.get(i).getmInvoicesLines().size() ; j++) {
                System.out.println(test.get(i).getmInvoicesLines().get(j).getItemName()+", "+
                        test.get(i).getmInvoicesLines().get(j).getItemPrice()+", "+test.get(i).getmInvoicesLines().get(j).getCount());
            }
            System.out.println("}");
            System.out.println("");
            System.out.println("");
        }

    }

    public static void main(String[] args) throws IOException {
        data = new FileOperations();
        String path = "InvoiceHeader.csv";
       data.testRead(path);

    }
}

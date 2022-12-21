package org.salesinvoice.view;

import com.opencsv.CSVWriter;
import org.salesinvoice.model.FileOperations;
import org.salesinvoice.model.InvoiceHeader;
import org.salesinvoice.model.InvoiceLines;
import org.salesinvoice.view.button.Cancel;
import org.salesinvoice.view.button.CreateNewInvoice;
import org.salesinvoice.view.button.DeleteInvoice;
import org.salesinvoice.view.button.Save;
import org.salesinvoice.view.table.InvoicesTable;
import org.salesinvoice.view.table.ItemsTable;
import org.salesinvoice.view.textfield.CustomerName;
import org.salesinvoice.view.textfield.InvoiceDate;
import org.salesinvoice.view.menuBar.MenuBar;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {
    InvoicesTable mInvoicesTable;
    Cancel mCancel;
    int s1 = 0;
    ItemsTable mItemsTable;
    String totalInvoiceDta = "";
    JTable invoicesTable;
    JScrollPane e;
    CustomerName mCustomerName;
    String invoiceNum = "";
    InvoiceDate mInvoiceDate;
    MenuBar mMenuBar;
    String s;
    ItemsTable itemTableList;
    CreateNewInvoice mCreateNewInvoice;
    DeleteInvoice mDeleteInvoice;
    JScrollPane k;
    Save mSave;
    JLabel invoiceTotalNum;
    JLabel invoiceNumberNum;

    public MainFrame() throws IOException {
        super("Sales Invoices Generator");
        e = new JScrollPane();
        itemTableList = new ItemsTable();
        mInvoicesTable = new InvoicesTable();
        mItemsTable = new ItemsTable();
        invoicesTable = mInvoicesTable.getTable();
        JTable itemTable = mItemsTable.getTable();
        mCancel = new Cancel();
        mCustomerName = new CustomerName();
        mInvoiceDate = new InvoiceDate();
        mMenuBar = new MenuBar();
        mCreateNewInvoice = new CreateNewInvoice();
        mDeleteInvoice = new DeleteInvoice();
        mSave = new Save();
        setLayout(null);
        JLabel d = new JLabel("Invoices Table");
        d.setBounds(46, 20, 100, 13);
        add(d);
        k = new JScrollPane();
        k.setBounds(46, 38, 604, 567);
        add(k);
        itemTableList = new ItemsTable();
        e = new JScrollPane(itemTableList.getTable());

        e.setBounds(710, 288, 604, 186);
        add(e);
        mCancel.getCancel().addActionListener(this);
        mCancel.getCancel().setActionCommand("Delete Item");
        add(mCancel.getCancel());
        mCreateNewInvoice.getmCreateNewInvoice().addActionListener(this);
        mCreateNewInvoice.getmCreateNewInvoice().setActionCommand("create");
        add(mCreateNewInvoice.getmCreateNewInvoice());
        add(mDeleteInvoice.getmDeleteInvoice());
        mDeleteInvoice.getmDeleteInvoice().addActionListener(this);
        mDeleteInvoice.getmDeleteInvoice().setActionCommand("delete");
        mSave.getmSave().addActionListener(this);
        mSave.getmSave().setActionCommand("createItem");
        add(mSave.getmSave());
        JLabel invoiceNumber = new JLabel("Invoice Number   ");
        invoiceNumber.setBounds(722, 74, 100, 13);
        add(invoiceNumber);
        JLabel invoiceDate = new JLabel("Invoice Date");
        invoiceDate.setBounds(722, 112, 100, 13);
        add(invoiceDate);
        JLabel customerName = new JLabel("Customer Name");
        customerName.setBounds(722, 159, 100, 13);
        add(customerName);
        JLabel totalInvoice = new JLabel("Invoice Total");
        totalInvoice.setBounds(722, 200, 87, 13);
        add(totalInvoice);
        JLabel invoiceItems = new JLabel("Invoice Items");
        invoiceItems.setBounds(710, 265, 100, 13);
        add(invoiceItems);
        add(mCustomerName.getCustomerName());
        add(new JLabel("Invoice Date: "));
        add(mInvoiceDate.getInvoiceDate());
        invoiceTotalNum = new JLabel(""); //invoice total
        invoiceTotalNum.setBounds(854, 200, 100, 13);
        add(invoiceTotalNum);
        invoiceNumberNum = new JLabel(""); //Invoice number
        invoiceNumberNum.setBounds(854, 74, 100, 13);
        add(invoiceNumberNum);
        mMenuBar.getLoadFileItem().addActionListener(this);
        mMenuBar.getLoadFileItem().setActionCommand("load");
        mMenuBar.getSaveFileItem().addActionListener(this);
        mMenuBar.getSaveFileItem().setActionCommand("save");
        setJMenuBar(mMenuBar.getMenuBar());
        invoiceTableListener();
        // setBounds(100, 100, 958, 537);
        setSize(1400, 800);
        setLocation(50, 50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "load":
                try {
                    loadData();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "save":
                try {
                    saveData();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                break;
            case "createItem":
                try {
                    createItem();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Delete Item":
                try {
                    deleteItem();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "delete":
                try {
                    deleteInvoice();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "create":
                try {
                    createButton();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;

        }

    }

    public void invoiceTableListener() {

        invoicesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {

                if (invoicesTable.getSelectedRow() > -1) {

                    s = invoicesTable.getValueAt(invoicesTable.getSelectedRow(), 0).toString();
                    ItemsTable table = new ItemsTable();
                    try {
                        remove(e);
                        itemTableList = new ItemsTable(table.data(s));
                        e = new JScrollPane(itemTableList.getTable());
                        e.setBounds(710, 288, 604, 186);
                        add(e);
                        // System.out.println(itemListener());

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        InvoicesTable data = new InvoicesTable();
                        String[][] dataList = data.data("InvoiceHeader.csv", "InvoiceLine.csv");
                        for (int i = 0; i < dataList.length; i++) {
                            if (dataList[i][0].equals(s)) {
                                invoiceNum = dataList[i][0];

                                mInvoiceDate.getInvoiceDate().setText(dataList[i][1]);
                                mCustomerName.getCustomerName().setText(dataList[i][2]);
                                totalInvoiceDta = dataList[i][3];
                                invoiceTotalNum.setText(totalInvoiceDta);
                                invoiceNumberNum.setText(invoiceNum);


                                break;
                            }
                        }


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }
            }


        });


    }

    public void loadData() throws IOException {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);
        String path = "";
        if (result == JFileChooser.APPROVE_OPTION) {
            path = fc.getSelectedFile().getPath();

        }
        JFileChooser fc2 = new JFileChooser();
        int result2 = fc.showOpenDialog(this);
        String path2 = "";
        if (result == JFileChooser.APPROVE_OPTION) {
            path2 = fc.getSelectedFile().getPath();

        }
        InvoicesTable table = new InvoicesTable();
        String[][] data = table.data(path, path2);
        remove(k);
        mInvoicesTable = new InvoicesTable(data);
        invoicesTable = mInvoicesTable.getTable();
        k = new JScrollPane(invoicesTable);
        k.setBounds(46, 38, 604, 567);
        add(k);


        invoiceTableListener();
    }

    public ArrayList<InvoiceHeader> invoiceTableToList(JTable table) {
        String invoNum;
        String date;
        String name;
        ArrayList<InvoiceHeader> invoiceList = new ArrayList<InvoiceHeader>();

        for (int row = 0; row < table.getModel().getRowCount(); row++) {

            invoNum = (String) table.getModel().getValueAt(row, 0);
            date = (String) table.getModel().getValueAt(row, 1);
            name = (String) table.getModel().getValueAt(row, 2);
            invoiceList.add(new InvoiceHeader(Integer.parseInt(invoNum), date, name));

        }

        return invoiceList;
    }

    public void saveData() throws IOException {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);
        String path = "";
        if (result == JFileChooser.APPROVE_OPTION) {
            path = fc.getSelectedFile().getPath();
        }
        FileOperations get = new FileOperations();
        get.writeFile(invoiceTableToList(invoicesTable), path);

    }

    public void createItem() throws IOException {
        ItemsTable table = new ItemsTable();
        JTextField xField = new JTextField(20);
        JTextField yField = new JTextField(20);
        JTextField zField = new JTextField(20);
        String name1 = "";
        String price = "";
        String count = "";
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Item Name: "));
        myPanel.add(xField);
        myPanel.add(new JLabel("Item Price: "));
        myPanel.add(zField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Count: "));
        myPanel.add(yField);
        add(myPanel);
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Item Information", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            name1 = xField.getText();
            price = yField.getText();
            count = zField.getText();
        }


        //read from item csv
        ArrayList<String[]> invoiceData = new ArrayList<String[]>();
        try (BufferedReader br1 = new BufferedReader(new FileReader("InvoiceLine.csv"))) {
            String lineItem;
            while ((lineItem = br1.readLine()) != null) {
                String[] itmeValues = lineItem.split(",");
                invoiceData.add(new String[]{itmeValues[0].replace("\"", ""), itmeValues[1].replace("\"", "")
                        , itmeValues[2].replace("\"", ""), itmeValues[3].replace("\"", "")});
            }
        }
        //----------------------------------------------
        //todo add item in row
        invoiceData.add(new String[]{s, name1, price, count});

        //write on csv
        FileOperations w = new FileOperations();
        CSVWriter invoiceWriter = new CSVWriter(new FileWriter("InvoiceLine.csv"));
        invoiceWriter.writeAll(invoiceData);
        invoiceWriter.close();
        //---------------------------------------------
        remove(e);
        itemTableList = new ItemsTable(table.data(s));
        e = new JScrollPane(itemTableList.getTable());
        e.setBounds(710, 288, 604, 186);
        add(e);

        InvoicesTable table2 = new InvoicesTable();
        String[][] data = table2.data("InvoiceHeader.csv", "InvoiceLine.csv");
        remove(k);
        mInvoicesTable = new InvoicesTable(data);
        invoicesTable = mInvoicesTable.getTable();
        k = new JScrollPane(invoicesTable);
        k.setBounds(46, 38, 604, 567);
        add(k);

        InvoicesTable data22 = new InvoicesTable();
        String[][] dataList = data22.data("InvoiceHeader.csv", "InvoiceLine.csv");
        for (int i = 0; i < dataList.length; i++) {
            if (dataList[i][0].equals(s)) {
                invoiceNum = dataList[i][0];

                mInvoiceDate.getInvoiceDate().setText(dataList[i][1]);
                mCustomerName.getCustomerName().setText(dataList[i][2]);
                totalInvoiceDta = dataList[i][3];
                invoiceTotalNum.setText(totalInvoiceDta);
                invoiceNumberNum.setText(invoiceNum);


                break;
            }
        }

        invoiceTableListener();


    }

    public void deleteItem() throws IOException {
        int o = itemTableList.getTable().getSelectedRow();


        String itemName = "";
        if (itemTableList.getTable().getSelectedRow() > -1) {

            itemName = itemTableList.getTable().getValueAt(o, 1).toString();


        }

        ArrayList<String[]> invoiceData = new ArrayList<String[]>();
        try (BufferedReader br1 = new BufferedReader(new FileReader("InvoiceLine.csv"))) {
            String lineItem;
            while ((lineItem = br1.readLine()) != null) {
                String[] itmeValues = lineItem.split(",");
                // System.out.println(itmeValues[1].replace("\"", "")+" "+itemName);
                if (!(itmeValues[1].replace("\"", "")).equals(itemName)) {
                    invoiceData.add(new String[]{itmeValues[0].replace("\"", ""), itmeValues[1].replace("\"", "")
                            , itmeValues[2].replace("\"", ""), itmeValues[3].replace("\"", "")});
                }

            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        //write on csv
        FileOperations w = new FileOperations();
        CSVWriter invoiceWriter = null;
        try {
            invoiceWriter = new CSVWriter(new FileWriter("InvoiceLine.csv"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        invoiceWriter.writeAll(invoiceData);
        try {
            invoiceWriter.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        //---------------------------------------------




        ItemsTable table = new ItemsTable();
        itemTableList = new ItemsTable(table.data(s));
        remove(e);
        e = new JScrollPane(itemTableList.getTable());
        e.setBounds(710, 288, 604, 186);
        add(e);

        InvoicesTable table2 = new InvoicesTable();
        String[][] data = table2.data("InvoiceHeader.csv", "InvoiceLine.csv");
        remove(k);
        mInvoicesTable = new InvoicesTable(data);
        invoicesTable = mInvoicesTable.getTable();
        k = new JScrollPane(invoicesTable);
        k.setBounds(46, 38, 604, 567);
        add(k);

        InvoicesTable data22 = new InvoicesTable();
        String[][] dataList = data22.data("InvoiceHeader.csv", "InvoiceLine.csv");
        for (int i = 0; i < dataList.length; i++) {
            if (dataList[i][0].equals(s)) {
                invoiceNum = dataList[i][0];

                mInvoiceDate.getInvoiceDate().setText(dataList[i][1]);
                mCustomerName.getCustomerName().setText(dataList[i][2]);
                totalInvoiceDta = dataList[i][3];
                invoiceTotalNum.setText(totalInvoiceDta);
                invoiceNumberNum.setText(invoiceNum);


                break;
            }
        }



        invoiceTableListener();
    }

    public void deleteInvoice() throws IOException {
        String invoNum;
        String date;
        String name;
        String total;

        ArrayList<InvoiceHeader> invoiceListFile = new ArrayList<InvoiceHeader>();
        String[][] data = new String[invoicesTable.getModel().getRowCount() - 1][4];
        int index = 0;
        for (int row = 0; row < invoicesTable.getModel().getRowCount(); row++) {

            if (!((String) invoicesTable.getModel().getValueAt(row, 0)).equals(s)) {
                invoNum = (String) invoicesTable.getModel().getValueAt(row, 0);
                date = (String) invoicesTable.getModel().getValueAt(row, 1);
                name = (String) invoicesTable.getModel().getValueAt(row, 2);
                total = (String) invoicesTable.getModel().getValueAt(row, 3);
                data[index][0] = (String) invoicesTable.getModel().getValueAt(row, 0);
                data[index][1] = (String) invoicesTable.getModel().getValueAt(row, 1);
                data[index][2] = (String) invoicesTable.getModel().getValueAt(row, 2);
                data[index][3] = (String) invoicesTable.getModel().getValueAt(row, 3);

                invoiceListFile.add(new InvoiceHeader(Integer.parseInt(invoNum), date, name));
                index++;
            }

        }
        //writeFile take data

        remove(k);
        mInvoicesTable = new InvoicesTable(data);
        invoicesTable = mInvoicesTable.getTable();
        k = new JScrollPane(invoicesTable);
        k.setBounds(46, 38, 604, 567);
        add(k);

        FileOperations f = new FileOperations();
        f.writeFile(invoiceListFile, "InvoiceHeader.csv");

        mInvoiceDate.getInvoiceDate().setText("");
        mCustomerName.getCustomerName().setText("");
        invoiceTotalNum.setText("");
        invoiceNumberNum.setText("");
        itemTableList = new ItemsTable();
        remove(e);
        e = new JScrollPane(itemTableList.getTable());
        e.setBounds(710, 288, 604, 186);
        add(e);

        InvoicesTable table = new InvoicesTable();
        remove(k);
        String[][] data2 = table.data("InvoiceHeader.csv", "InvoiceLine.csv");

        mInvoicesTable = new InvoicesTable(data2);
        invoicesTable = mInvoicesTable.getTable();
        k = new JScrollPane(invoicesTable);
        k.setBounds(46, 38, 604, 567);
        add(k);

        invoiceTableListener();


    }

    public void createButton() throws IOException {
        JTextField xField = new JTextField(20);
        JTextField yField = new JTextField(20);
        String name1 = "";
        String Date = "";
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Customer Name:"));
        myPanel.add(xField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Date:"));
        myPanel.add(yField);
        add(myPanel);
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Customer name and Date", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            name1 = xField.getText();
            Date = yField.getText();
        }
        String invoNum;
        String date;
        String name;
        String total;
        String invNum = "";
        ArrayList<InvoiceHeader> invoiceListFile = new ArrayList<InvoiceHeader>();
        String[][] data = new String[invoicesTable.getModel().getRowCount() + 1][4];

        for (int row = 0; row < invoicesTable.getModel().getRowCount(); row++) {
            invoNum = (String) invoicesTable.getModel().getValueAt(row, 0);
            date = (String) invoicesTable.getModel().getValueAt(row, 1);
            name = (String) invoicesTable.getModel().getValueAt(row, 2);
            invNum = (String) invoicesTable.getModel().getValueAt(row, 0);
            data[row][0] = (String) invoicesTable.getModel().getValueAt(row, 0);
            data[row][1] = (String) invoicesTable.getModel().getValueAt(row, 1);
            data[row][2] = (String) invoicesTable.getModel().getValueAt(row, 2);
            data[row][3] = (String) invoicesTable.getModel().getValueAt(row, 3);

            invoiceListFile.add(new InvoiceHeader(Integer.parseInt(invoNum), date, name));
        }
        //writeFile take data
        invoiceListFile.add(new InvoiceHeader((Integer.parseInt(invNum)) + 1, Date, name1));
        data[invoicesTable.getModel().getRowCount()][0] = Integer.toString((Integer.parseInt(invNum) + 1));
        data[invoicesTable.getModel().getRowCount()][1] = Date;
        data[invoicesTable.getModel().getRowCount()][2] = name1;
        data[invoicesTable.getModel().getRowCount()][3] = "0";
        remove(k);
        mInvoicesTable = new InvoicesTable(data);
        invoicesTable = mInvoicesTable.getTable();
        k = new JScrollPane(invoicesTable);
        k.setBounds(46, 38, 604, 567);
        add(k);
        FileOperations f = new FileOperations();
        f.writeFile(invoiceListFile, "InvoiceHeader.csv");
        mInvoiceDate.getInvoiceDate().setText("");
        mCustomerName.getCustomerName().setText("");
        invoiceTotalNum.setText("");
        invoiceNumberNum.setText("");
        itemTableList = new ItemsTable();
        remove(e);
        e = new JScrollPane(itemTableList.getTable());
        e.setBounds(710, 288, 604, 186);
        add(e);
        invoiceTableListener();
    }




}


package org.salesinvoice.view.textfield;

import javax.swing.*;

public class InvoiceDate extends JTextField {

    JTextField invoiceDate;
    public InvoiceDate() {

        invoiceDate = new JTextField(15);
        invoiceDate.setBounds(854, 112, 477, 19);
    }

    public JTextField getInvoiceDate() {
        return invoiceDate;
    }
}

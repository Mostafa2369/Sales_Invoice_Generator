package org.salesinvoice.view.textfield;

import javax.swing.JTextField;

public class CustomerName extends JTextField {

     JTextField customerName;
    public CustomerName() {

        customerName = new JTextField(15);
        customerName.setBounds(854, 159, 477, 19);
    }

    public JTextField getCustomerName() {
        return customerName;
    }
}

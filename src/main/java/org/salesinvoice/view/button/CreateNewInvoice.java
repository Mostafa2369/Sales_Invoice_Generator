package org.salesinvoice.view.button;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewInvoice extends JButton  {

   JButton mCreateNewInvoice;
    public CreateNewInvoice() {
        mCreateNewInvoice = new JButton("Create New Invoice");
        mCreateNewInvoice.setBounds(166, 684, 160, 29);
    }



    public JButton getmCreateNewInvoice() {
        return mCreateNewInvoice;
    }
}

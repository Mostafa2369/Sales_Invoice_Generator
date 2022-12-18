package org.salesinvoice.view.button;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteInvoice extends JButton  {
       JButton mDeleteInvoice;

    public DeleteInvoice() {
        mDeleteInvoice = new JButton("Delete Invoice");

        mDeleteInvoice.setBounds(425, 684, 130, 29);

    }


    public JButton getmDeleteInvoice() {
        return mDeleteInvoice;
    }
}

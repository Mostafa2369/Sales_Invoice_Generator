package org.salesinvoice.view.button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cancel extends JButton  {
   JButton cancel;

    public Cancel() {

        cancel = new JButton("Cancel");
        cancel.setBounds(1106, 684, 109, 29);

    }



    public JButton getCancel() {

        return cancel;
    }
}

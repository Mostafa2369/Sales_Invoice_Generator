package org.salesinvoice.view.button;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Save extends JButton  {
    JButton mSave;

    public Save() {

        mSave= new JButton("Save");

        mSave.setBounds(834, 684, 109, 29);
    }


    public JButton getmSave() {
        return mSave;
    }
}

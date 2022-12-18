package org.salesinvoice.view.menuBar;


import javax.swing.*;

public class MenuBar extends JMenuBar {
    JMenuBar menuBar;
    JMenuItem loadFileItem;

    JMenuItem saveFileItem;
    JMenu fileMenu;

    public MenuBar() {
        loadFileItem = new JMenuItem("Load File", 'L');
        saveFileItem = new JMenuItem("Save File", 'S');
        fileMenu = new JMenu("File");
        fileMenu.add(loadFileItem);
        fileMenu.add(saveFileItem);
        menuBar = new JMenuBar();
        menuBar.add(fileMenu);


    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JMenuItem getSaveFileItem() {
        return saveFileItem;
    }

    public JMenuItem getLoadFileItem() {
        return loadFileItem;
    }
}

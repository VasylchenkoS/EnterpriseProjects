package com.vasylchenko.beans;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SwingTableOut extends JFrame {
    private static DefaultTableModel newModel;

    public static void start() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object columnNames[] = {"CollectionName", "DataCount", "add", "get",
                "remove", "contains", "populate", "iterator.add", "iterator.remove"};
        newModel = new DefaultTableModel(null, columnNames);
        JTable table = new JTable(newModel);
        table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        for (int i = 0; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(920, 400);
        frame.setVisible(true);
    }

    public static void createTable(String name, int dataCount, double[] data) {
        newModel.addRow(new Object[]{name, dataCount, data[0], data[1], data[2], data[3], data[4], data[5], data[6]});
    }
}

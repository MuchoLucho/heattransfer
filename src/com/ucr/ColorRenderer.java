package com.ucr;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ColorRenderer extends JLabel implements TableCellRenderer {

    public ColorRenderer() {
        setOpaque(true); //MUST do this for background to show up.
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (Double.valueOf(table.getValueAt(row, column).toString()) > 900){
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        } else if (Double.valueOf(table.getValueAt(row, column).toString()) > 800) {
            setBackground(Color.ORANGE);
            setForeground(Color.BLACK);
        } else if (Double.valueOf(table.getValueAt(row, column).toString()) > 600) {
            setBackground(Color.YELLOW);
            setForeground(Color.BLACK);
        } else if (Double.valueOf(table.getValueAt(row, column).toString()) > 400) {
            setBackground(Color.GREEN);
            setForeground(Color.BLACK);
        } else {
            setBackground(Color.BLUE);
            setForeground(Color.WHITE);
        }
        return this;
    }
}
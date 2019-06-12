package com.ucr;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PlacaGUI {

    private JTable table;

    public DefaultTableModel generadorMapa(double [][] matriz){
        String[] columnNames = new String[Constantes.getCANT_NODOS_X()];
        Arrays.fill(columnNames, "");
        Object[][] data = new Double[Constantes.getCANT_NODOS_Y()][Constantes.getCANT_NODOS_X()];
        for (int y = 0; y < Constantes.getCANT_NODOS_Y(); y++) {
            for (int x = 0; x < Constantes.getCANT_NODOS_X(); x++) {
                data[y][x] = matriz[y][x];
            }
        }
        return new DefaultTableModel(data, columnNames);
    }

    public PlacaGUI(int x, int y, int iteracion) {
        Logica logica = new Logica();
        logica.iterar(iteracion);
        double [][] matriz = logica.getSnapshot();
        table = new JTable(generadorMapa(matriz));
        JScrollPane pane =new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        for (int i = 0; i < Constantes.getCANT_NODOS_X(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
            table.getColumnModel().getColumn(i).setPreferredWidth(1);
        }
        table.setTableHeader(null);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(10);
        JFrame frame = new JFrame();
        frame.add(pane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        JOptionPane.showMessageDialog(null,"Temperatura del nodo x:"+x+", y:"+y+" tiempo: "+iteracion+" es "+ matriz[y][x]+" K");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
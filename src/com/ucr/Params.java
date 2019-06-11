package com.ucr;

import javax.swing.*;
import java.awt.event.*;

public class Params extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField ladoPlaca_txt;
    private JTextField distNodal_txt;
    private JTextField tempInf_txt;
    private JTextField tempCobre_txt;
    private JTextField tempAceIzq_txt;
    private JTextField tempAceCent_txt;
    private JTextField tempAceDer_txt;
    private JTextField tiempo_txt;
    private JLabel ladoPlaca;
    private JLabel distNodal;
    private JLabel tempInf;
    private JLabel tempCobre;
    private JPanel tempAcero;
    private JLabel tempAceCent;
    private JLabel tempAceDer;
    private JLabel tiempo;
    private JLabel tempAceIzq;

    public Params() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        Constantes.setDIST_NODAL(Double.valueOf(distNodal_txt.getText()));
        Constantes.setLADO_PLACA(Double.valueOf(ladoPlaca_txt.getText()));
        Constantes.setTEMP_INF(Double.valueOf(tempInf_txt.getText()));
        Constantes.setTEMP_COB(Double.valueOf(tempCobre_txt.getText()));
        Constantes.setTEMP_ACER_IZQ(Double.valueOf(tempAceIzq_txt.getText()));
        Constantes.setTEMP_ACER_CENT(Double.valueOf(tempAceCent_txt.getText()));
        Constantes.setTEMP_ACER_DER(Double.valueOf(tempAceDer_txt.getText()));
        Constantes.setTIEMPO(Integer.valueOf(tiempo_txt.getText()));
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        Params dialog = new Params();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

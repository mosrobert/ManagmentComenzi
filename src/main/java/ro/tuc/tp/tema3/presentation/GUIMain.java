package ro.tuc.tp.tema3.presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIMain extends JFrame{
    private JButton clientButton;
    private JButton comandaButton;
    private JButton produsButton;
    private JPanel jPanel;


    public GUIMain() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(jPanel);
        this.pack();
        /**
         * butonul pentru client
         */
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIClient clientForm=new GUIClient("Clienti");
                ControllerClient contrl=new ControllerClient(clientForm);

                clientForm.setLocationRelativeTo(null);
                clientForm.setBounds(100,100,800,600);
                clientForm.setVisible(true);
            }
        });
        /**
         * butonul pentru produs
         */
        produsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame produsForm=new GUIProdus("Produse");
                produsForm.setLocationRelativeTo(null);
                produsForm.setBounds(100,100,800,600);
                produsForm.setVisible(true);
            }
        });
        /**
         * butonul pentru comanda
         */
        comandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame comandaForm=new GUIComanda();
                comandaForm.setLocationRelativeTo(null);
                comandaForm.setBounds(100,100,800,600);
                comandaForm.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame mainForm=new GUIMain();
        mainForm.setLocationRelativeTo(null);
        mainForm.setBounds(100,100,450,450);
        mainForm.setVisible(true);
    }
}

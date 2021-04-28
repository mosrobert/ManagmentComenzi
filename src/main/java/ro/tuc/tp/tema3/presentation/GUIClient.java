package ro.tuc.tp.tema3.presentation;

import ro.tuc.tp.tema3.DataModels.Client;
import ro.tuc.tp.tema3.dao.ClientDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

public class GUIClient extends JFrame {
    private JPanel jPanel;
    private JTable tabelClienti;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton insertButton;
    private DefaultTableModel tableModel;
    private ClientDAO clienti = new ClientDAO();
    private JScrollPane scrollPane;
    private JTextField idTF;
    private JTextField numeTF;
    private JTextField adresaTF;

    public GUIClient(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setContentPane(jPanel);
        this.pack();
        try {
            createTable("ro.tuc.tp.tema3.DataModels.Client");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * creează un tabel cu informațiile din baza de date
     * @param s
     * @throws ClassNotFoundException
     */
    private void createTable(String s) throws ClassNotFoundException {
        tableModel = new DefaultTableModel();
        tabelClienti.setFillsViewportHeight(true);

        Class cls = Class.forName(s);
        Field[] fields = cls.getDeclaredFields();
        Object column = new Object();
        for (Field field : fields) {
            field.setAccessible(true);
            column = field.getName();
            tableModel.addColumn(column);
        }
        tabelClienti.setModel(tableModel);
    }

    public void addDeleteButtonAction(ActionListener ac) {

        deleteButton.addActionListener(ac);

    }

    public void addInsertButtonAction(ActionListener ac) {

        insertButton.addActionListener(ac);

    }

    public void addUpdateButtonAction(ActionListener ac) {
        updateButton.addActionListener(ac);
    }

    public void addRefreshButtonAction(ActionListener ac) {
        refreshButton.addActionListener(ac);
    }

    public JTable getTabelClienti() {
        return tabelClienti;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JButton getInsertButton() {
        return insertButton;
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public ClientDAO getClienti() {
        return clienti;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTextField getIdTF() {
        return idTF;
    }

    public JTextField getNumeTF() {
        return numeTF;
    }

    public JTextField getAdresaTF() {
        return adresaTF;
    }
}

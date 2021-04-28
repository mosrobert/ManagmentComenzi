package ro.tuc.tp.tema3.presentation;

import ro.tuc.tp.tema3.DataModels.Produs;
import ro.tuc.tp.tema3.dao.ProdusDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

public class GUIProdus extends JFrame {
    private JPanel jPanel;
    private JTable tabelProduse;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton insertButton;
    private static DefaultTableModel tableModel;
    private final ProdusDAO comenzi=new ProdusDAO();
    private JScrollPane scrollPane;
    private JTextField numeTF;
    private JTextField cantitateTF;
    private JTextField pretTF;
    private JTextField idTF;

    /**
     * se pune titlul pentru pagina deschisa si se creează tabelul
     * @param title numele care se doreste pentru fereastra
     */
    public GUIProdus(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setContentPane(jPanel);
        this.pack();

        try {
            createTable("ro.tuc.tp.tema3.DataModels.Produs");
        } catch (Exception e) {
            e.printStackTrace();
        }

        produs_refreshButtonExecute();
        produs_updateButtonExecute();
        produs_insertButtonExecute();
        produs_deleteButtonExecute();

    }

    /**
     * se creează tabelul
     * @throws ClassNotFoundException
     */
    private void createTable(String s) throws ClassNotFoundException {
        tableModel=new DefaultTableModel();
        tabelProduse.setFillsViewportHeight(true);

        Class cls=Class.forName(s);
        Field[]fields=cls.getDeclaredFields();
        Object column=new Object();
        for(Field field:fields){
            field.setAccessible(true);
            column=field.getName();
            tableModel.addColumn(column);
        }
        tabelProduse.setModel(tableModel);
    }

    /**
     * crearea butonului pentru update la produse
     */
    private void produs_updateButtonExecute() {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowId= tabelProduse.getSelectedRow();
                int fostulcomenzid= (int) tabelProduse.getValueAt(selectedRowId,0);
                int comenzid=Integer.parseInt(idTF.getText());
                String numeId = numeTF.getText();
                double pretId = Double.parseDouble(pretTF.getText());
                int cantitate = Integer.parseInt(cantitateTF.getText());
                Produs p=new Produs();
                p.setId(comenzid);
                p.setNume(numeId);
                p.setPret(pretId);
                p.setCantitate(cantitate);
                try {
                    comenzi.update(p,fostulcomenzid);
                } catch (ClassNotFoundException | IllegalAccessException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
//                tableModel.setRowCount(0);
                tableModel.getDataVector().removeAllElements();
                tableModel.fireTableDataChanged();
                List<Produs> listaProdus=comenzi.findAll();
                for (Produs pr2 :
                        listaProdus) {
                    tableModel.addRow(new Object[]{pr2.getId(),pr2.getNume(),pr2.getCantitate(), pr2.getPret()});
                }
            }
        });
    }

    /**
     * Crearea butonului pentru insert la produse
     */
    private void produs_insertButtonExecute() {
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int comenzid=Integer.parseInt(idTF.getText());
                String numeId = numeTF.getText();
                double pretId = Double.parseDouble(pretTF.getText());
                int cantitate = Integer.parseInt(cantitateTF.getText());

                Produs p=new Produs();
                p.setId(comenzid);
                p.setNume(numeId);
                p.setPret(pretId);
                p.setCantitate(cantitate);
                comenzi.insert(p);

//                tableModel.setRowCount(0);
                tableModel.getDataVector().removeAllElements();
                tableModel.fireTableDataChanged();
                List<Produs> listaProdus=comenzi.findAll();
                for (Produs pr2 :
                        listaProdus) {
                    tableModel.addRow(new Object[]{pr2.getId(),pr2.getNume(),pr2.getCantitate(), pr2.getPret()});
                }
            }
        });
    }

    /**
     * crearea butonului pentru delete
     */
    private void produs_deleteButtonExecute() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowId= tabelProduse.getSelectedRow();
                int comenzid= (int) tabelProduse.getValueAt(selectedRowId,0);
                String numeId= (String) tabelProduse.getValueAt(selectedRowId,1);
                double pretId= (double) tabelProduse.getValueAt(selectedRowId,3);
                int cantitate= (int) tabelProduse.getValueAt(selectedRowId,2);

                Produs p=new Produs();
                p.setId(comenzid);
                p.setNume(numeId);
                p.setPret(pretId);
                p.setCantitate(cantitate);

                comenzi.delete(p);
//                tableModel.setRowCount(0);

                tableModel.getDataVector().removeAllElements();
                tableModel.fireTableDataChanged();
                List<Produs> listaProdus=comenzi.findAll();
                for (Produs pr2 :
                        listaProdus) {
                    tableModel.addRow(new Object[]{pr2.getId(),pr2.getNume(),pr2.getCantitate(), pr2.getPret()});
                }

            }
        });
    }

    /**
     * crearea butonului de refresh, elimina toate elementele si apoi le adauga in tabel
     */
    private void produs_refreshButtonExecute() {
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                tableModel.setRowCount(0);
                tableModel.getDataVector().removeAllElements();
                tableModel.fireTableDataChanged();
                List<Produs> listaProdus=comenzi.findAll();
                for (Produs p :
                        listaProdus) {
                    tableModel.addRow(new Object[]{p.getId(),p.getNume(),p.getCantitate(), p.getPret()});
                }
            }
        });
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public JTable getTabelProduse() {
        return tabelProduse;
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

    public static DefaultTableModel getTableModel() {
        return tableModel;
    }

    public ProdusDAO getComenzi() {
        return comenzi;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTextField getNumeTF() {
        return numeTF;
    }

    public JTextField getCantitateTF() {
        return cantitateTF;
    }

    public JTextField getPretTF() {
        return pretTF;
    }

    public JTextField getIdTF() {
        return idTF;
    }
}

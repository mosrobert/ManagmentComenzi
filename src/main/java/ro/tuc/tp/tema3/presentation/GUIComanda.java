package ro.tuc.tp.tema3.presentation;

import ro.tuc.tp.tema3.DataModels.Comanda;
import ro.tuc.tp.tema3.DataModels.Produs;
import ro.tuc.tp.tema3.bll.ComandaBLL;
import ro.tuc.tp.tema3.bll.ProdusBLL;
import ro.tuc.tp.tema3.dao.ClientDAO;
import ro.tuc.tp.tema3.dao.ComandaDAO;
import ro.tuc.tp.tema3.dao.ProdusDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

public class GUIComanda extends JFrame {
    private JPanel jPanel;
    private JTable tabelcomenzi;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton insertButton;
    private DefaultTableModel tableModel;
    private ComandaBLL comenzi=new ComandaBLL();
    private JScrollPane scrollPane;
    private JTextField clientTF;
    private JTextField cantitateTF;
    private JTextField produsTF;
    private JTextField idTF;

    /**
     * se pune titlul pentru pagina deschisa si se creează tabelul
     */
    public GUIComanda(){
        super("Comenzi");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setContentPane(jPanel);
        this.pack();
        try {
            createTable("ro.tuc.tp.tema3.DataModels.Comanda");
        } catch (Exception e) {
            e.printStackTrace();
        }
        comanda_refreshButtonExecute();
        comanda_updateButtonExecute();
        comanda_insertButtonExecute();
        comanda_deleteButtonExecute();


    }

    /**
     * se creează  tabelul
     * @throws ClassNotFoundException
     */
    private void createTable(String s) throws ClassNotFoundException {
        tableModel=new DefaultTableModel();
        tabelcomenzi.setFillsViewportHeight(true);

        Class cls=Class.forName(s);
        Field[]fields=cls.getDeclaredFields();
        Object column=new Object();
        for(Field field:fields){
            field.setAccessible(true);
            column=field.getName();
            tableModel.addColumn(column);
        }
        tabelcomenzi.setModel(tableModel);
    }

    /**
     * comanda pentru stergerea unei comenzi din tabel
     */
    private void comanda_deleteButtonExecute() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowId=tabelcomenzi.getSelectedRow();
                int comenzid= (int) tabelcomenzi.getValueAt(selectedRowId,0);
                int clientId= (int) tabelcomenzi.getValueAt(selectedRowId,1);
                int produsId= (int) tabelcomenzi.getValueAt(selectedRowId,2);
                int cantitate= (int) tabelcomenzi.getValueAt(selectedRowId,3);

                Comanda cl=new Comanda();
                cl.setId(comenzid);
                cl.setClient(clientId);
                cl.setProdus(produsId);
                cl.setCantitate(cantitate);

                comenzi.delete(cl);
//                tableModel.setRowCount(0);

                tableModel.getDataVector().removeAllElements();
                tableModel.fireTableDataChanged();
                List<Comanda> listaComanda=comenzi.findAll();
                for (Comanda cl2 :
                        listaComanda) {
                    tableModel.addRow(new Object[]{cl2.getId(),cl2.getClient(),cl2.getCantitate(), cl2.getProdus(), cl2.getPretTotal()});
                }

            }
        });
    }

    /**
     * comanda pentru inserarea in tabel a unei comenzi
     */
    private void comanda_insertButtonExecute() {
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int comenzid = Integer.parseInt(idTF.getText());
                int clientId = Integer.parseInt(clientTF.getText());
                int produsId = Integer.parseInt(produsTF.getText());
                int cantitate = Integer.parseInt(cantitateTF.getText());

                ProdusDAO prod = new ProdusDAO();
                ClientDAO cli=new ClientDAO();
                int cantitateRamasa = prod.findById(produsId).getCantitate();
                int clientexist=cli.findById(clientId).getId();
                if(clientexist!=clientId)
                {
                    Frame frame = new Frame();
                    JOptionPane.showMessageDialog(frame, "Acest client nu exista");
                }
                else if (cantitate > cantitateRamasa) {
                    Frame frame = new Frame();
                    JOptionPane.showMessageDialog(frame, "Cantitatea introdusa este prea mare");
                } else {


                    Comanda cl = new Comanda();
                    cl.setId(comenzid);
                    cl.setClient(clientId);
                    cl.setProdus(produsId);
                    cl.setCantitate(cantitate);

                    Produs produs;
                    ProdusBLL produsBLL=new ProdusBLL();
                    produs=produsBLL.findProdusById(produsId);
                    double pret=produs.getPret()*cantitate;
                    cl.setPretTotal(pret);
                    produs.setCantitate(produs.getCantitate()-cantitate);
                    try {
                        produsBLL.update(produs,produs.getId());
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                    comenzi.insert(cl);
                    comenzi.factura(cl);
//                tableModel.setRowCount(0);
                    tableModel.getDataVector().removeAllElements();
                    tableModel.fireTableDataChanged();
                    List<Comanda> listaComanda = comenzi.findAll();
                    for (Comanda cl2 :
                            listaComanda) {
                        tableModel.addRow(new Object[]{cl2.getId(), cl2.getClient(), cl2.getCantitate(), cl2.getProdus(), cl2.getPretTotal()});
                    }
                }
            }
        });
    }

    /**
     * comanda pentru editarea unei comenzi din tabel
     */
    private void comanda_updateButtonExecute() {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowId=tabelcomenzi.getSelectedRow();
                int fostulcomenzid= (int) tabelcomenzi.getValueAt(selectedRowId,0);
                int comenzid=Integer.parseInt(idTF.getText());
                int clientId = Integer.parseInt(clientTF.getText());
                int produsId = Integer.parseInt(produsTF.getText());
                int cantitate = Integer.parseInt(cantitateTF.getText());
                Comanda cl=new Comanda();
                cl.setId(comenzid);
                cl.setClient(clientId);
                cl.setProdus(produsId);
                cl.setCantitate(cantitate);
                try {
                    comenzi.update(cl,fostulcomenzid);
                } catch (ClassNotFoundException | IllegalAccessException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
//                tableModel.setRowCount(0);
                tableModel.getDataVector().removeAllElements();
                tableModel.fireTableDataChanged();
                List<Comanda> listaComanda=comenzi.findAll();
                for (Comanda cl2 :
                        listaComanda) {
                    tableModel.addRow(new Object[]{cl2.getId(),cl2.getClient(),cl2.getCantitate(), cl2.getProdus(), cl2.getPretTotal()});
                }
            }
        });
    }

    /**
     * comanda pentru butonul de refresh, afiseaza elementele din baza de date
     */
    private void comanda_refreshButtonExecute() {
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                tableModel.setRowCount(0);
                tableModel.getDataVector().removeAllElements();
                tableModel.fireTableDataChanged();
                List<Comanda> listaComanda=comenzi.findAll();
                for (Comanda cl :
                        listaComanda) {
                    tableModel.addRow(new Object[]{cl.getId(),cl.getClient(),cl.getCantitate(), cl.getProdus(), cl.getPretTotal()});
                }
            }
        });
    }

}

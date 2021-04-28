package ro.tuc.tp.tema3.presentation;

import ro.tuc.tp.tema3.DataModels.Client;
import ro.tuc.tp.tema3.bll.ClientBLL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControllerClient {
    GUIClient guiClient;

    public ControllerClient(GUIClient view) {
        guiClient = view;
        guiClient.addRefreshButtonAction(new RefreshAction());
        guiClient.addUpdateButtonAction(new UpdateAction());
        guiClient.addInsertButtonAction(new InsertAction());
        guiClient.addDeleteButtonAction(new DeleteAction());
    }

    class RefreshAction implements ActionListener {

        @Override
        /**
         * se sterg elementele existente in tabel si se afiseaza iar tabelul integral
         */
        public void actionPerformed(ActionEvent e) {
            guiClient.getTableModel().getDataVector().removeAllElements();
            guiClient.getTableModel().fireTableDataChanged();
            ClientBLL clienti = new ClientBLL();
            List<Client> listaClienti = clienti.findAll();
            for (Client cl :
                    listaClienti) {
                guiClient.getTableModel().addRow(new Object[]{cl.getId(), cl.getName(), cl.getAdresa()});
            }
        }
    }

    class DeleteAction implements ActionListener {

        @Override
        /**
         * se sterge un client din baza de date dupa selectia lui si apasarea butonului delete
         */
        public void actionPerformed(ActionEvent e) {
            int selectedRowId = guiClient.getTabelClienti().getSelectedRow();
            int clientId = (int) guiClient.getTabelClienti().getValueAt(selectedRowId, 0);
            String clientName = (String) guiClient.getTabelClienti().getValueAt(selectedRowId, 1);
            String clientAdress = (String) guiClient.getTabelClienti().getValueAt(selectedRowId, 2);

            ClientBLL clienti = new ClientBLL();
            Client cl = new Client();
            cl.setId(clientId);
            cl.setName(clientName);
            cl.setAdresa(clientAdress);
            clienti.delete(cl);

//                tableModel.setRowCount(0);
            guiClient.getTableModel().getDataVector().removeAllElements();
            guiClient.getTableModel().fireTableDataChanged();
            List<Client> listaClienti = clienti.findAll();
            for (Client cl2 :
                    listaClienti) {
                guiClient.getTableModel().addRow(new Object[]{cl2.getId(), cl2.getName(), cl2.getAdresa()});
            }
        }
    }

    class InsertAction implements ActionListener {

        @Override
        /**
         * se inserează in baza de date un nou client
         */
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(guiClient.getIdTF().getText());
            String nume = guiClient.getNumeTF().getText();
            String adresa = guiClient.getAdresaTF().getText();
            ClientBLL clienti = new ClientBLL();
            Client cl = new Client();
            cl.setId(id);
            cl.setName(nume);
            cl.setAdresa(adresa);
            clienti.insert(cl);

//                tableModel.setRowCount(0);
            guiClient.getTableModel().getDataVector().removeAllElements();
            guiClient.getTableModel().fireTableDataChanged();
            List<Client> listaClienti = clienti.findAll();
            for (Client cl2 :
                    listaClienti) {
                guiClient.getTableModel().addRow(new Object[]{cl2.getId(), cl2.getName(), cl2.getAdresa()});
            }
        }
    }


    class UpdateAction implements ActionListener {

        @Override
        /**
         * se modifică datele unui client din baza de date prin introducerea datelor noi in textfield-uri si apasarea butonului de update
         */
        public void actionPerformed(ActionEvent e) {
            int selectedRowId = guiClient.getTabelClienti().getSelectedRow();
            int fostulClientId = (int) guiClient.getTabelClienti().getValueAt(selectedRowId, 0);
            int clientId = Integer.parseInt(guiClient.getIdTF().getText());
            String clientName = guiClient.getNumeTF().getText();
            String clientAdress = guiClient.getAdresaTF().getText();
            //
            ClientBLL clienti = new ClientBLL();
            Client cl = new Client();
            cl.setId(clientId);
            cl.setName(clientName);
            cl.setAdresa(clientAdress);
            try {
                clienti.update(cl, fostulClientId);
            } catch (ClassNotFoundException | IllegalAccessException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

//                tableModel.setRowCount(0);
            guiClient.getTableModel().getDataVector().removeAllElements();
            guiClient.getTableModel().fireTableDataChanged();
            List<Client> listaClienti = clienti.findAll();
            for (Client cl2 :
                    listaClienti) {
                guiClient.getTableModel().addRow(new Object[]{cl2.getId(), cl2.getName(), cl2.getAdresa()});
            }
        }
    }
}

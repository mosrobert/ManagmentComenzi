package ro.tuc.tp.tema3.bll;

import ro.tuc.tp.tema3.DataModels.Client;
import ro.tuc.tp.tema3.DataModels.Comanda;
import ro.tuc.tp.tema3.DataModels.Produs;
import ro.tuc.tp.tema3.dao.ComandaDAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.NoSuchElementException;

public class ComandaBLL {
    private ComandaDAO comandaDAO;

    public ComandaBLL() {


        comandaDAO = new ComandaDAO();
    }

    /**
     * caută o comandă după id
     * @param id primește id-ul pe care vrea sa il caute
     * @return returnează comanda gasita dupa id-ul introdus
     */
    public Comanda findStudentById(int id) {
        Comanda com = comandaDAO.findById(id);
        if (com == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return com;
    }
    public List<Comanda> findAll(){
        return  comandaDAO.findAll();
    }

    /**
     * se apelează metoda
     * @param com primeste ca parametru o comanda
     * @param id primeste un id specific comenzii
     * @return returnează o noua comanda modificată
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public Comanda update(Comanda com, int id) throws ClassNotFoundException, IllegalAccessException {
        Comanda com2=comandaDAO.update(com,id);
        return com2;
    }

    /**
     * se sterge o comanda existenta
     * @param com primeste ca parametru comanda care se doreste a fi stearsa
     */
    public void delete(Comanda com){
        comandaDAO.delete(com);
    }

    /**
     * se inserează o nouă comanda
     * @param com primeste ca parametru o comanda
     * @return returnează comanda modificată
     */
    public Comanda insert(Comanda com){
        Comanda com2=comandaDAO.insert(com);
        return com2;
    }

    /**
     * se creează factura pentru o comanda data
     * @param com se da ca parametru o comanda
     */
    public void factura(Comanda com){
        String factura="Factura_nr_"+com.getId()+".txt";
        File fileOutput = new File(factura);
        FileWriter write = null;
        try {
            write = new FileWriter(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(write);

        ClientBLL cbll=new ClientBLL();
        Client client=cbll.findClientById(com.getClient());
        ProdusBLL pbll=new ProdusBLL();
        Produs produs=pbll.findProdusById(com.getProdus());

        String continut="Factura "+com.getId()+"\n"+
                "Cumparator: "+client.getName()+"\n"+
                "Produs: "+produs.getNume()+"\n"+
                "Pret/buc: "+produs.getPret()+"   Cantitate: "+com.getCantitate()+"\n"+
                "Pret total: "+com.getPretTotal();
        pw.println(continut);
        pw.close();
    }
}

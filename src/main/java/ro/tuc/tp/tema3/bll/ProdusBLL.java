package ro.tuc.tp.tema3.bll;

import ro.tuc.tp.tema3.DataModels.Client;
import ro.tuc.tp.tema3.DataModels.Produs;
import ro.tuc.tp.tema3.dao.ClientDAO;
import ro.tuc.tp.tema3.dao.ProdusDAO;

import java.util.NoSuchElementException;

public class ProdusBLL {
    private ProdusDAO produsDAO;

    public ProdusBLL() {


        produsDAO = new ProdusDAO();
    }

    /**
     * gaseste un produs după id-ul introdus
     * @param id se da ca parametru un id pentru care sa se afiseze
     * @return se returnează produsul specific id-ului
     */
    public Produs findProdusById(int id) {
        Produs prod = produsDAO.findById(id);
        if (prod == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return prod;
    }

    /**
     * se face modificarea datelor unui produs
     * @param prod primeste ca parametru un produs
     * @param id primeste parametru un id specific produsului
     * @return returnează produsul modificat
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public Produs update(Produs prod, int id) throws ClassNotFoundException, IllegalAccessException {
        Produs prod2=produsDAO.update(prod,id);
        return prod2;
    }

    /**
     * se apelează metoda de stergere din AbstractDAO
     * @param prod se da ca parametru produsul care se doreste sters
     */
    public void delete(Produs prod){
        produsDAO.delete(prod);
    }

    /**
     * se apelează metoda de inserare din AbstractDAO
     * @param prod se da ca parametru un produs care se doreste adaugat
     * @return returnează produsul adaugat
     */
    public Produs insert(Produs prod){
        Produs prod2=produsDAO.insert(prod);
        return prod2;
    }
}

package ro.tuc.tp.tema3.bll;

import ro.tuc.tp.tema3.DataModels.Client;
import ro.tuc.tp.tema3.dao.ClientDAO;

import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private ClientDAO clientDAO;

    public ClientBLL() {


        clientDAO = new ClientDAO();
    }

    /**
     * caută un client după id
     * @param id primește un id ca parametru
     * @return
     */
    public Client findClientById(int id) {
        Client cl = clientDAO.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return cl;
    }
    public List<Client> findAll(){
        List<Client> clienti=clientDAO.findAll();
        return clienti;
    }

    /**
     * apelează metoda din AbstractDAO pentru a edita informațiile despre client
     * @param c primește un client c
     * @param id primeste un id specific clientului
     * @return returnează un nou client cu datele modificate
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public Client update(Client c,int id) throws ClassNotFoundException, IllegalAccessException {
        Client c2=clientDAO.update(c,id);
        return c2;
    }

    /**
     * apelează metoda din AbstractDAO
     * @param c primeste un client care va fi șters
     */
    public void delete(Client c){
        clientDAO.delete(c);
    }

    /**
     * apelează metoda insert din AbstractDAO
     * @param c primește un client
     * @return va returna un client cu datele introduse
     */
    public Client insert(Client c){
        Client c2=clientDAO.insert(c);
        return c2;
    }
}

package ro.tuc.tp.tema3;

import ro.tuc.tp.tema3.DataModels.Client;
import ro.tuc.tp.tema3.bll.ClientBLL;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ClientBLL cl=new ClientBLL();
    Client c2=cl.findClientById(3);
       // System.out.println(c2);
        //c2.setId(4);
        //cl.insert(c2);
        cl.delete(c2);

        List<Client> Clienti=cl.findAll();
        for(Client c:Clienti){
            System.out.println(c);
        }
    }
}

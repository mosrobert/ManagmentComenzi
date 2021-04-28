package ro.tuc.tp.tema3.DataModels;

public class Produs {
    private int id;
    private String nume;
    private int cantitate;
    private double pret;
    public Produs(){

    }

    /**
     * se atribuie fiecarui atribut o valoare primită ca parametru
     * @param id valoarea transmisă ca parametru
     * @param cantitate valoarea transmisă ca parametru
     * @param pret valoarea transmisă ca parametru
     * @param nume valoarea transmisă ca parametru
     */
    public Produs(int id, int cantitate, double pret, String nume)
    {
        this.id=id;
        this.cantitate=cantitate;
        this.pret=pret;
        this.nume=nume;
    }

    /**
     *
     * @return returnează id-ul produsului
     */
    public int getId() {
        return id;
    }

    /**
     * setează o valoare id-ului transmisă ca parametru
     * @param id valoarea care se da pentru a fi setata
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * se returnează cantitatea produsului
     * @return returnează cantitatea
     */
    public int getCantitate() {
        return cantitate;
    }

    /**
     * setează pentru cantitate o valoare transmisă ca parametru
     * @param cantitate valoarea care se atribuie cantitatii
     */
    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    /**
     *
     * @return returnează pretul produsului
     */
    public double getPret() {
        return pret;
    }

    /**
     * setează la pretul produsului o valoare transmisă ca parametru
     * @param pret valoarea care se da pentru a fi setată
     */
    public void setPret(double pret) {
        this.pret = pret;
    }

    /**
     *
     * @return returnează numele produsului
     */
    public String getNume() {
        return nume;
    }

    /**
     * setează numelui o valoare transmisă ca parametru
     * @param nume valoarea transmisă ca parametru
     */
    public void setNume(String nume) {
        this.nume = nume;
    }
}

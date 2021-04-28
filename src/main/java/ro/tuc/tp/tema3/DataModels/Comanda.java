package ro.tuc.tp.tema3.DataModels;

public class Comanda {
    private int id;
    private int client;
    private int cantitate;
    private int produs;
    private double pretTotal;
    public Comanda(){

    }

    /**
     * se atribuie fiecarui atribut valoarea primita ca paramaetru
     * @param id valoarea care se da pentru a se atribui
     * @param cantitate valoarea care se da pentru a se atribui
     * @param client valoarea care se da pentru a se atribui
     * @param produs valoarea care se da pentru a se atribui
     * @param pretTotal valoarea care se da pentru a se atribui
     */
    public Comanda(int id, int cantitate, int client, int produs, double pretTotal)
    {
        this.cantitate=cantitate;
        this.client=client;
        this.produs=produs;
        this.id=id;
        this.pretTotal=pretTotal;
    }

    /**
     *
     * @return se returnează pretul total
     */
    public double getPretTotal() {
        return pretTotal;
    }

    /**
     * se setează o valoare primită ca parametru pentru pretul total
     * @param pretTotal valoarea care se atribuie pretului total
     */
    public void setPretTotal(double pretTotal) {
        this.pretTotal = pretTotal;
    }

    /**
     *
     * @return returnează valoarea id-ului
     */
    public int getId() {
        return id;
    }

    /**
     * setează o valoarea pentru id
     * @param id primeste o valoare care sa se atribuie id-ului
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return returnează o valoare pentru cantitate
     */
    public int getCantitate() {
        return cantitate;
    }

    /**
     * setează cantitatii o valoare primită ca parametru
     * @param cantitate
     */
    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    /**
     *
     * @return returnează id-ul clientului
     */
    public int getClient() {
        return client;
    }

    /**
     * setează clientului valoarea primită ca parametru
     * @param client
     */
    public void setClient(int client) {
        this.client = client;
    }

    /**
     *
     * @return returnează id-ul produsului
     */
    public int getProdus() {
        return produs;
    }

    /**
     * setează id-ului produsului o valoare primită ca parametru
     * @param produs
     */
    public void setProdus(int produs) {
        this.produs = produs;
    }
}


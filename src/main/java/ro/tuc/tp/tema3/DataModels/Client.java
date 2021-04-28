package ro.tuc.tp.tema3.DataModels;

public class Client {
    /**
     * id-ul clientului
     */
    private int id;
    /**
     * numele clientului
     */
    private String name;
    /**
     * adresa la care locuieste clientul
     */
    private String adresa;

    /**
     * creează un obiect de tipul client
     */
    public Client(){

    }

    /**
     * seteaza informațiile despre client cu cele date ca parametru
     * @param id id-ul se doreste a fi setat clientului
     * @param name numele care se doreste a fi setat clietului
     * @param adresa adresa care se doreste a fi setata clientului
     */
    public Client(int id, String name, String adresa)
    {
        this.id=id;
        this.name=name;
        this.adresa=adresa;
    }

    /**
     *
     * @return returnează id-ul clientului
     */
    public int getId() {
        return id;
    }

    /**
     * setează id-ul cu valoarea transmisă ca parametru
     * @param id valoarea care se doreste atribuita id-ului
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return returnează numele clientului
     */
    public String getName() {
        return name;
    }

    /**
     * setează numele clientului cu valoarea transmisa ca parametru
     * @param name numele care va fi atribuit clientului
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return returnează adresa clientului
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * setează adresa clientului cu valoarea transmisă ca parametru
     * @param adresa valoarea care se da pentru modificarea adresei
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }

}

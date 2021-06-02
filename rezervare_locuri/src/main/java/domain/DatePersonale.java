package domain;

public class DatePersonale extends Entity<Long>{
    private long id;
    private long id_client;
    private String nume;
    private String prenume;
    private String numar_telefon;
    private String alte_mentiuni;
    private Integer varsta;

    public DatePersonale(long id,long id_client,String nume,String prenume,String numar_telefon,String alte_mentiuni,Integer varsta){
        this.id = id;
        this.id_client = id_client;
        this.nume = nume;
        this.prenume = prenume;
        this.numar_telefon = numar_telefon;
        this.alte_mentiuni = alte_mentiuni;
        this.varsta = varsta;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    public DatePersonale(){

    }

    @Override
    public String toString() {
        return "DatePersonale{" +
                "nume=" + nume + "";
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_client() {
        return id_client;
    }

    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNumar_telefon() {
        return numar_telefon;
    }

    public void setNumar_telefon(String numar_telefon) {
        this.numar_telefon = numar_telefon;
    }

    public String getAlte_mentiuni() {
        return alte_mentiuni;
    }

    public void setAlte_mentiuni(String alte_mentiuni) {
        this.alte_mentiuni = alte_mentiuni;
    }



}

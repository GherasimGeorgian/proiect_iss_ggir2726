package domain;

import java.sql.Timestamp;

public class Rezervare extends Entity<Long>{
    private long id;
    private long id_loc;
    private long id_client;
    private long id_date_personale;
    private long id_reprezentatie;
    private Timestamp data_rezervare;

    public Rezervare(Long id,long id_loc, long id_client,long id_date_personale,Timestamp data_rezervare,long id_reprezentatie){
        this.id = id;
        this.id_loc = id_loc;
        this.id_client = id_client;
        this.id_date_personale = id_date_personale;
        this.data_rezervare = data_rezervare;
        this.id_reprezentatie = id_reprezentatie;

    }



    public Rezervare(){

    }
    @Override
    public String toString() {
        return "Rezervare{" +
                "id=" + id + "";
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_loc() {
        return id_loc;
    }

    public void setId_loc(long id_loc) {
        this.id_loc = id_loc;
    }

    public long getId_client() {
        return id_client;
    }

    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

    public long getId_date_personale() {
        return id_date_personale;
    }

    public void setId_date_personale(long id_date_personale) {
        this.id_date_personale = id_date_personale;
    }

    public Timestamp getData_rezervare() {
        return data_rezervare;
    }

    public void setData_rezervare(Timestamp data_rezervare) {
        this.data_rezervare = data_rezervare;
    }
    public long getId_reprezentatie() {
        return id_reprezentatie;
    }

    public void setId_reprezentatie(long id_reprezentatie) {
        this.id_reprezentatie = id_reprezentatie;
    }
}

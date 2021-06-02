package domain;

import java.sql.Timestamp;

public class Reprezentatie extends Entity<Long>{
    private long id;

    private String nume;
    private Integer rating;
    private Timestamp durata;
    public Reprezentatie(Long id,String nume, Integer rating,Timestamp durata){
        this.id = id;
        this.nume = nume;
        this.rating = rating;
        this.durata = durata;

    }
    public Reprezentatie(){

    }
    @Override
    public String toString() {
        return "Reprezentatie{" +
                "id=" + id + "nume: " + nume + " rating "+ rating + " durata: " + durata.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Timestamp getDurata() {
        return durata;
    }

    public void setDurata(Timestamp durata) {
        this.durata = durata;
    }
}

package domain;

import javax.persistence.*;
import java.io.Serializable;
public class Loc extends Entity<Long>{
    private long id;
    private Integer randul;
    private Integer loja;
    private Integer numar;
    private float pret;
    private StareLoc stare;
    private long id_reprezentatie;
    public Loc(long id,Integer randul,Integer loja,Integer numar,float pret,StareLoc stare,long id_reprezentatie){
        this.id = id;
        this.randul = randul;
        this.loja = loja;
        this.numar = numar;
        this.pret = pret;
        this.stare = stare;
        this.id_reprezentatie = id_reprezentatie;
    }

    public long getId_reprezentatie() {
        return id_reprezentatie;
    }

    public void setId_reprezentatie(long id_reprezentatie) {
        this.id_reprezentatie = id_reprezentatie;
    }

    public Loc(){

    }
    @Override
    public String toString() {
        return "Loc{" +
                "id=" + id + " " + randul + " " + loja + " "+ numar + " " + pret + "stare: " + stare.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getRandul() {
        return randul;
    }

    public void setRandul(Integer randul) {
        this.randul = randul;
    }



    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    public Integer getNumar() {
        return numar;
    }

    public void setNumar(Integer numar) {
        this.numar = numar;
    }


    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public StareLoc getStare() {
        return stare;
    }

    public void setStare(StareLoc stare) {
        this.stare = stare;
    }


}
